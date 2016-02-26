<%--
  ADOBE CONFIDENTIAL

  Copyright 2014 Adobe Systems Incorporated
  All Rights Reserved.

  NOTICE:  All information contained herein is, and remains
  the property of Adobe Systems Incorporated and its suppliers,
  if any.  The intellectual and technical concepts contained
  herein are proprietary to Adobe Systems Incorporated and its
  suppliers and may be covered by U.S. and Foreign Patents,
  patents in process, and are protected by trade secret or copyright law.
  Dissemination of this information or reproduction of this material
  is strictly forbidden unless prior written permission is obtained
  from Adobe Systems Incorporated.
--%><%
%><%@page session="false"%><%
%><%@page import="com.adobe.cq.contentinsight.ProviderSettingsManager,
                  com.adobe.cq.social.commons.CommentSystem,
                  com.adobe.cq.wcm.launches.utils.LaunchUtils,
                  com.adobe.granite.security.user.util.AuthorizableUtil,
                  com.adobe.granite.ui.components.AttrBuilder,
                  com.adobe.granite.ui.components.ComponentHelper,
                  com.adobe.granite.ui.components.Tag,
                  com.adobe.granite.workflow.exec.WorkItem,
                  com.adobe.granite.workflow.exec.Workflow,
                  com.adobe.granite.workflow.job.AbsoluteTimeoutHandler,
                  com.adobe.granite.workflow.status.WorkflowStatus,
                  com.day.cq.commons.date.RelativeTimeFormat,
                  com.day.cq.commons.servlets.AbstractListServlet,
                  com.day.cq.i18n.I18n,
                  com.day.cq.replication.ReplicationQueue,
                  com.day.cq.replication.ReplicationStatus,
                  com.day.cq.wcm.api.Page,
                  com.day.cq.wcm.api.PageInfoAggregator,
                  com.day.cq.wcm.msm.api.LiveRelationshipManager,
                  org.apache.commons.lang.StringUtils,
                  org.apache.jackrabbit.api.security.user.Authorizable,
                  org.apache.jackrabbit.api.security.user.Group,
                  org.apache.jackrabbit.api.security.user.UserManager,
                  org.apache.jackrabbit.util.Text,
                  org.apache.sling.api.resource.Resource,
                  org.apache.sling.api.resource.ResourceResolver,
                  org.apache.sling.api.resource.ResourceUtil,
                  org.apache.sling.api.resource.ValueMap,
                  javax.jcr.Node,
                  javax.jcr.RepositoryException,
                  javax.jcr.Session,
                  javax.jcr.security.AccessControlManager,
                  javax.jcr.security.Privilege,
                  java.text.DateFormat,
                  java.text.SimpleDateFormat,
                  java.util.ArrayList,
                  java.util.Calendar,
                  java.util.Collections,
                  java.util.Comparator,
                  java.util.LinkedHashMap,
                  java.util.LinkedList,
                  java.util.List,
                  java.util.Locale,
                  java.util.HashMap,
                  java.util.regex.Pattern,
                  java.util.Map" %><%
%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%
%><%@taglib prefix="cq" uri="http://www.day.com/taglibs/cq/1.0" %><%
%><cq:defineObjects /><%

    ComponentHelper cmp = new ComponentHelper(pageContext);
    I18n i18n = cmp.getI18n();

    Authorizable currentUser = resourceResolver.adaptTo(Authorizable.class);
    LiveRelationshipManager liveRelationshipManager = resourceResolver.adaptTo(LiveRelationshipManager.class);
    UserManager userManager = resource.adaptTo(UserManager.class);
    WorkflowStatus workflowStatus = resource.adaptTo(WorkflowStatus.class);
    List<WorkItem> workItems = getWorkItems(currentUser, workflowStatus, userManager);
    ReplicationStatus replicationStatus = resource.adaptTo(ReplicationStatus.class);
    RelativeTimeFormat rtf = new RelativeTimeFormat("r", slingRequest.getResourceBundle(request.getLocale()));

    AccessControlManager acm = null;
    try {
        acm = resourceResolver.adaptTo(Session.class).getAccessControlManager();
    } catch (RepositoryException e) {
        log.error("Unable to get access manager", e);
    }

    String parentPath = slingRequest.getRequestPathInfo().getSuffix();
    if (parentPath == null) {
        parentPath = "/content";
    }

    Page cqPage = resource.adaptTo(Page.class);
    ValueMap resourceProperties;
    boolean isLanguageCopy = false; // TODO: Determine if page is a language copy. Maybe LanguageManager helps.
    int commentCount = 0;
    boolean isNew = false;
    int sortWeight = 0;

    String title;
    if (cqPage != null) {
        resourceProperties = cqPage.getProperties();
        title = cqPage.getTitle();
        commentCount = getCommentCount(cqPage);
        isNew = isNew(cqPage);

        if (cqPage.isHideInNav()) {
            sortWeight += -5;
        }
    } else {
        // if it is not a page it is a folder
        resourceProperties = ResourceUtil.getValueMap(resource);
        title = getFolderTitle(resource);
    }

    sortWeight = getSortWeight(sortWeight, isNew, !workItems.isEmpty());

    Calendar publishedDate = resourceProperties.get("cq:lastReplicated", Calendar.class);
    Calendar rolloutDate = resourceProperties.get("cq:lastRolledout", Calendar.class);

    String publishedBy = AuthorizableUtil.getFormattedName(resourceResolver, resourceProperties.get("cq:lastReplicatedBy", String.class));
    boolean deactivated = "Deactivate".equals(resourceProperties.get("cq:lastReplicationAction", String.class));

    String rolledOutBy = AuthorizableUtil.getFormattedName(resourceResolver, resourceProperties.get("cq:lastRolledoutBy", String.class));

    boolean isLiveCopy = liveRelationshipManager.hasLiveRelationship(resource);

    ProviderSettingsManager providerSettingsManager = sling.getService(ProviderSettingsManager.class);
    boolean hasAnalytics = false;
    if(providerSettingsManager != null) {
        hasAnalytics = providerSettingsManager.hasActiveProviders(resource);
    }

    Tag tag = cmp.consumeTag();
    AttrBuilder attrs = tag.getAttrs();

    attrs.add("itemscope", "itemscope");
    attrs.add("data-path", resource.getPath()); // for compatibility
    attrs.add("data-resourcetype", resource.getResourceType());
    attrs.add("data-timeline", true);
    attrs.add("data-item-title", title);
    attrs.add("data-gridlayout-sortkey", sortWeight);
    attrs.add("data-livecopy", isLiveCopy);

    if (cqPage != null) {
        attrs.addClass("card-page");
        attrs.addClass(cqPage.listChildren().hasNext() ? "stack" : null);
        attrs.add("data-item-type", "page");
        pageContext.setAttribute("movable", true);
    } else {
        attrs.addClass("card-directory");
        attrs.add("data-item-type", "directory");
        // check if this node's parent supports orderable children
        Session session = resourceResolver.adaptTo(Session.class);
        if (parentPath != null && session.nodeExists(parentPath)) {
            Node node = session.getNode(parentPath);
            pageContext.setAttribute("movable", node.getPrimaryNodeType().hasOrderableChildNodes());
        }
    }

%><article <%= attrs.build() %>>


    <a href="<%= xssAPI.getValidHref(resource.getPath() + ".html") %>"  onclick="window.location.href='<%= xssAPI.getValidHref(resource.getPath() + ".html") %>'" x-cq-linkchecker="skip">

            <span class="image"><img itemprop="thumbnail" width="192" src="<%= xssAPI.getValidHref(request.getContextPath() + getThumbnailUrl(cqPage)) %>" alt=""></span>
            <div class="label">
                <div class="main"><h4 class="foundation-collection-item-title" itemprop="title"><%= xssAPI.encodeForHTML(title) %></h4>
                </div>
                <div class="info">


                        <p class="modified"><b><%= cqPage.getProperties().get("jcr:subtitle")==null?"":cqPage.getProperties().get("jcr:subtitle") %> </b></p>
                        <br/>
                        <p class="modified"> <%= cqPage.getProperties().get("jcr:description")==null?"":cqPage.getProperties().get("jcr:description") %> </p>


                   </div>
            </div>
        </a>

        <a href="<%= xssAPI.getValidHref(resource.getPath() + ".html") %>" onclick="window.location.href='<%= xssAPI.getValidHref(resource.getPath() + ".html") %>'"  x-cq-linkchecker="skip">


            <span class="image">
                <img itemprop="thumbnail" width="192" src="<%= xssAPI.getValidHref(request.getContextPath() + Text.escapePath(resource.getPath()) + ".folderthumbnail.jpg") %>" alt="">
                <i class="show-list coral-Icon coral-Icon--sizeXS coral-Icon--folder"></i>
            </span>
            <div class="label">
                <h4 class="foundation-collection-item-title" itemprop="title"><%= xssAPI.encodeForHTML(title) %></h4>
            </div>
        </a>

</article><%!
    private boolean isNew(Page page) {
	    Calendar created = page.getProperties().get("jcr:created", Calendar.class);
	    Calendar lastModified = page.getLastModified();

	    Calendar twentyFourHoursAgo = Calendar.getInstance();
	    twentyFourHoursAgo.add(Calendar.DATE, -1);

	    if (created == null || (lastModified != null && lastModified.before(created))) {
	        created = lastModified;
	    }

        return created != null && twentyFourHoursAgo.before(created);
	}

    private int getSortWeight(int sortWeight, boolean isNew, boolean hasWorkItem) {
        if (isNew) {
            sortWeight += 10;
        }
        if (hasWorkItem) {
            sortWeight += 20;
        }
        return sortWeight;
    }

    private String getThumbnailUrl(Page page) {
        String ck = "";

        ValueMap metadata = page.getProperties("image/file/jcr:content");
        if (metadata != null) {
            Calendar cal = metadata.get("jcr:lastModified", Calendar.class);
            if (cal != null) {
                ck = "" + (cal.getTimeInMillis() / 1000);
            }
        }

        return Text.escapePath(page.getPath()) + ".thumb.319.319.png?ck=" + ck;
    }

    private String getFolderTitle(Resource folder) {
        ValueMap vm = ResourceUtil.getValueMap(folder);
        return vm.get("jcr:content/jcr:title", vm.get("jcr:title", folder.getName()));
    }

    private String getAdminUrl(Resource pageResource, Page requestPage) {
        String base = requestPage.getVanityUrl();

        if (base == null) {
            base = requestPage.getProperties().get("sling:vanityPath", base);
        }

        if (base == null) {
            base = Text.escapePath(requestPage.getPath());
        }

        return base + ".html" + Text.escapePath(pageResource.getPath());
    }

    private int getCommentCount(Page page) {
        Resource commentsResource = page.getContentResource().getChild("alt/comments");

        if (commentsResource != null) {
            CommentSystem commentSystem = commentsResource.adaptTo(CommentSystem.class);

            if (commentSystem != null) {
                return commentSystem.countComments();
            }
        }

        return 0;
    }

    private List<WorkItem> getWorkItems(Authorizable user, WorkflowStatus workflowStatus, UserManager userManager)
        throws RepositoryException {
        List<WorkItem> workItems = new ArrayList<WorkItem>();

        if (workflowStatus != null && workflowStatus.isInRunningWorkflow(true)) {
            List<Workflow> workflows = workflowStatus.getWorkflows(true);
            for (Workflow workflow : workflows) {
                for (WorkItem item : workflow.getWorkItems()) {
                    boolean isAssigned = false;
                    String assigneeId = item.getCurrentAssignee();
                    Authorizable assignee = assigneeId != null ? userManager.getAuthorizable(assigneeId) : null;

                    if (assignee != null) {
                        if (assignee.isGroup()) {
                            Group group = (Group) assignee;
                            isAssigned = group.isMember(user);
                        } else {
                            isAssigned = assignee.getID().equals(user.getID());
                        }
                    }
                    if (isAssigned) {
                        workItems.add(item);
                    }
                }
            }
        }
        return workItems;
    }

    private List<Workflow> getScheduledWorkflows(WorkflowStatus workflowStatus) {
        List<Workflow> scheduledWorkflows = new LinkedList<Workflow>();
        if (workflowStatus != null) {
            List<Workflow> workflows = workflowStatus.getWorkflows(false);
            for (Workflow workflow : workflows) {
                if (isScheduledActivationWorkflow(workflow) || isScheduledDectivationWorkflow(workflow)) {
                    scheduledWorkflows.add(workflow);
                }
            }
        }

        Collections.sort(scheduledWorkflows, new Comparator<Workflow>() {
            // sort by time workflow got started
            public int compare(Workflow o1, Workflow o2) {
                return o1.getTimeStarted().compareTo(o2.getTimeStarted());
            }
        });

        return scheduledWorkflows;
    }

    private String getPendingPublicationQuicktip(ReplicationStatus replicationStatus, I18n i18n) {
        // Publication is pending
        if (replicationStatus == null) return "";
        String qtip = "";
        // get pending information
        int maxQueuePos = -1;
        String actionType = "";
        for (ReplicationQueue.Entry e : replicationStatus.getPending()) {
            if (e.getQueuePosition() > maxQueuePos) {
                maxQueuePos = e.getQueuePosition();
                actionType = e.getAction().getType().getName();
            }
        }
        maxQueuePos = maxQueuePos + 1;
        if (maxQueuePos > 0) {
            String pendingDeactivationStatus = "";
            if ("Activate".equals(actionType)) {
                qtip += i18n.get("Publication Pending. #{0} in Queue.", "", maxQueuePos);
            } else {
                qtip += i18n.get("Unpublication Pending. #{0} in Queue.", "", maxQueuePos);
            }
        }
        return qtip;
    }

    private String getOnOffTimeStatus(ValueMap properties, I18n i18n, Locale locale) {
        // On-/Off time activation
        Calendar onTimeDate = properties.get("onTime", Calendar.class);
        Calendar offTimeDate = properties.get("offTime", Calendar.class);

        String status = "";

        if (onTimeDate != null) {
            status += i18n.get("On Time") + ": ";
            status += formatAbsoluteDate(onTimeDate.getTimeInMillis(), locale);
        }
        if (offTimeDate != null) {
            status += "\n";
            status += i18n.get("Off Time") + ": ";
            status += formatAbsoluteDate(offTimeDate.getTimeInMillis(), locale);
        }
        return status;
    }

    private String getScheduledStatus(List<Workflow> scheduledWorkflows, I18n i18n, Locale locale, ResourceResolver resourceResolver) {
        String status = "";
        int i = 0;
        for (Workflow scheduledWorkflow : scheduledWorkflows) {
            if (i > 0) {
                status += "\n\n";
            }
            if (isScheduledActivationWorkflow(scheduledWorkflow)) {
                status += i18n.get("Publication Pending") + "\n";
                status += i18n.get("Version") + ": ";
                status += scheduledWorkflow.getWorkflowData().getMetaDataMap().get("resourceVersion",
                        String.class) + "\n";
            } else {
                status += i18n.get("Unpublication Pending") + "\n";
            }
            status += i18n.get("Scheduled") + ": ";
            status += formatAbsoluteDate(scheduledWorkflow.getWorkflowData().getMetaDataMap().get(AbsoluteTimeoutHandler.ABS_TIME,
                    Long.class), locale);
            status += " (" + AuthorizableUtil.getFormattedName(resourceResolver, scheduledWorkflow.getInitiator()) + ")";
            i++;
        }
        return status;
    }

    private boolean isScheduledActivationWorkflow(Workflow workflow) {
        if (workflow == null) return false;
        return workflow.getWorkflowModel().getId().equals(AbstractListServlet.ListItem.SCHEDULED_ACTIVATION_WORKFLOW_ID);
    }

    private boolean isScheduledDectivationWorkflow(Workflow workflow) {
        if (workflow == null) return false;
        return workflow.getWorkflowModel().getId().equals(AbstractListServlet.ListItem.SCHEDULED_DEACTIVATION_WORKFLOW_ID);
    }

    private List<String> getActionRels(Resource resource, AccessControlManager acm, boolean hasAnalytics) {
        List<String> actionRels = new ArrayList<String>();

        actionRels.add("cq-siteadmin-admin-actions-create-activator");
        actionRels.add("cq-siteadmin-admin-actions-copy-activator");

        if (hasAnalytics) {
            actionRels.add("cq-siteadmin-admin-actions-open-content-insight-activator");
        }

        Page page = resource.adaptTo(Page.class);

        if (page != null && !page.isLocked()) {
            actionRels.add("cq-siteadmin-admin-actions-lockpage-activator");
        }

        if (page != null && page.isLocked() && page.canUnlock()) {
            actionRels.add("cq-siteadmin-admin-actions-unlockpage-activator");
        }

        if (hasPermission(acm, resource, Privilege.JCR_READ)) {
            if (page != null) {
                actionRels.add("cq-siteadmin-admin-actions-edit-activator");
                actionRels.add("cq-siteadmin-admin-actions-properties-activator");
            } else {
                actionRels.add("cq-siteadmin-admin-actions-folderproperties-activator");
            }
        }

        if (hasPermission(acm, resource, Privilege.JCR_REMOVE_NODE)) {
            actionRels.add("cq-siteadmin-admin-actions-move-activator");
            actionRels.add("cq-siteadmin-admin-actions-delete-activator");
        }

        if (hasPermission(acm, resource, "crx:replicate")) {
            actionRels.add("cq-siteadmin-admin-actions-publish-activator");
            actionRels.add("cq-siteadmin-admin-actions-unpublish-activator");
        }

        return actionRels;
    }

    private boolean hasPermission(AccessControlManager acm, Resource resource, String privilege) {
	    if (acm != null) {
	        try {
	            Privilege p = acm.privilegeFromName(privilege);
	            return acm.hasPrivileges(resource.getPath(), new Privilege[] { p });
	        } catch (RepositoryException ignore) {
	        }
	    }
        return false;
    }

    private String formatRelativeDate(Calendar cal, RelativeTimeFormat rtf) {
        try {
            return rtf.format(cal.getTimeInMillis(), true);
        } catch (IllegalArgumentException e) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(cal.getTime());
        }
    }

    private String formatAbsoluteDate(Long time, Locale locale) {
        return DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, locale).format(time);
    }
%>
