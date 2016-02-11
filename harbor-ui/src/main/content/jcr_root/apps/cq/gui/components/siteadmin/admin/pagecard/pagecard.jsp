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
    <i class="select"></i>
    <c:if test="${movable}">
        <i class="move"></i>
    </c:if> <%

    if (cqPage != null) {
        Map<String, Integer> wfTitles = new HashMap<String, Integer>();

        for (WorkItem item : workItems) {
            String wfTitle = i18n.getVar(item.getNode().getTitle());

            if(!wfTitles.containsKey(wfTitle)) {
                wfTitles.put(wfTitle, 1);
            }
            else {
                wfTitles.put(wfTitle, wfTitles.get(wfTitle) + 1);
            }
        }

        for (Map.Entry<String, Integer> entry : wfTitles.entrySet()) {
        %><div class="coral-Alert coral-Alert--notice">
            <i class="coral-Alert-typeIcon coral-Icon coral-Icon--sizeS coral-Icon--alert"></i>
            <div class="coral-Alert-message"><%= xssAPI.encodeForHTMLAttr(entry.getKey()) %></div>
            <% if(entry.getValue() > 1) { %>
            <span class="u-coral-pullRight coral-Badge coral-Badge--notice"><%= xssAPI.encodeForHTMLAttr(Integer.toString(entry.getValue())) %></span><%
            }%>
        </div><%
        }
        %>

    <% if (getAdminUrl(resource, currentPage).indexOf("harbor") >= 0) { %>
    <a href="<%= xssAPI.getValidHref(resource.getPath() + ".html") %>"  x-cq-linkchecker="skip">
    <%
        } else {
    %>
        <a href="<%= xssAPI.getValidHref(request.getContextPath() + getAdminUrl(resource, currentPage)) %>" itemprop="admin" x-cq-linkchecker="skip">
    <%
        }
    %>
            <%
            if (isNew) {
                %><span class="flag info"><%= i18n.get("New") %></span><%
            }
            %><span class="image"><img itemprop="thumbnail" width="192" src="<%= xssAPI.getValidHref(request.getContextPath() + getThumbnailUrl(cqPage)) %>" alt=""></span>
            <div class="label">
                <div class="main"><%
                    if (isLanguageCopy) {
                        %><p class="descriptor" title="<%= i18n.get("Page is a Language Copy") %>"><%= i18n.get("Language Copy") %></p><%
                    }
                    if (LaunchUtils.isLaunchResourcePath(cqPage.getPath())) {
                        %><p class="descriptor" title="<%= i18n.get("Page is a Launch Copy") %>"><%= i18n.get("Launch Copy") %></p><%
                    }
                    if (liveRelationshipManager.hasLiveRelationship(resource)) {
                        %><p class="descriptor" title="<%= i18n.get("Page is a Live Copy") %>"><%= i18n.get("Live Copy") %></p><%
                    }
                %><h4 class="foundation-collection-item-title" itemprop="title"><%= xssAPI.encodeForHTML(title) %></h4>
                </div>
                <div class="info">
                    <p class="meta-info"><%
                        if (cqPage.isHideInNav()) {
                            %><span class="hideinnav">
                                <i class="coral-Icon coral-Icon--viewOff" title="<%= i18n.get("Hidden in Navigation") %>"></i>
                            </span><%
                        }
                        if (cqPage.isLocked()) {
                            %><span class="islocked">
                                <i class="coral-Icon coral-Icon--lockOn" title="<%= i18n.get("Locked") %>"></i>
                            </span><%
                        }
                        if (commentCount > 0) {
                            %><span class="comments" title="<%= i18n.get("Comments") %>">
                                <i class="coral-Icon coral-Icon--comment"></i>
                                <span class="comment-number"><%= commentCount %></span>
                            </span><%
                        }
                    %></p>
                    <p class="modified" <%= (getAdminUrl(resource, currentPage).contains("harbor"))?"style='display:none;'":""  %> %>%>title="<%= i18n.get("Modified") %>">
                        <i class="coral-Icon coral-Icon--edit"></i><%
                        if (cqPage.getLastModified() != null) {
                            %><span class="date" itemprop="lastmodified" data-timestamp="<%= cqPage.getLastModified().getTimeInMillis() %>">
                            <%= xssAPI.encodeForHTML(formatRelativeDate(cqPage.getLastModified(), rtf)) %><%
                                String modifiedAfterPublishIcon = "";
                                String modifiedAfterPublishStatus = "";

                                if (publishedDate != null && publishedDate.before(cqPage.getLastModified())) {
                                    modifiedAfterPublishIcon = "coral-Icon coral-Icon--alert";
                                    modifiedAfterPublishStatus = i18n.get("Modified since last publication");

                                %><span class="coral-Icon--info-notice info-inline list-only modifiedafterpublish-info <%= modifiedAfterPublishIcon %> coral-Icon--sizeXS"
                                    itemprop="modifiedafterpublish-info"
                                    title="<%= xssAPI.encodeForHTMLAttr(modifiedAfterPublishStatus) %>"></span><%
                                    }
                            %></span>
                            <span class="user" itemprop="lastmodifiedby"><%= xssAPI.encodeForHTML(AuthorizableUtil.getFormattedName(resourceResolver, cqPage.getLastModifiedBy())) %></span><%
                        }
                    %></p><%
                    String publishStatus = "";
                    String publishIcon = "";
                    String publishStatusStyle = "";
                    if (publishedDate != null) {
                        if (!deactivated) {
                            publishStatus = i18n.get("Published");
                            publishIcon = "coral-Icon coral-Icon--globe";
                        } else {
                            publishStatus = i18n.get("Not published");
                            publishIcon = "coral-Icon coral-Icon--globeRemove";
                            publishStatusStyle = "not-published";
                        }
                    } else {
                        publishedDate = Calendar.getInstance();
                        publishedDate.setTimeInMillis(0);
                        publishStatus = i18n.get("Not published");
                        publishIcon = "coral-Icon coral-Icon--globeRemove";
                        publishStatusStyle = "not-published";
                    }

                %><p class="published <%= publishStatusStyle %>" <%= (getAdminUrl(resource, currentPage).contains("harbor"))?"style='display:none;'":""  %> title="<%= publishStatus %>">
                        <i class="<%= publishIcon %>"></i>
                        <span class="date" itemprop="published" data-timestamp="<%= publishedDate.getTimeInMillis() %>">
                            <%= (publishedDate.getTimeInMillis() != 0 && !deactivated) ?
                                    xssAPI.encodeForHTML(formatRelativeDate(publishedDate, rtf)) : publishStatus %>
                            <%
                            // (Un-)Publication is pending in queue?
                            String queueStatus = getPendingPublicationQuicktip(replicationStatus, i18n);
                            if (queueStatus.length() > 0) {
                                %><span
                                    class="coral-Icon--info-notice info-inline list-only queue-info coral-Icon coral-Icon--pending coral-Icon--sizeXS"
                                    itemprop="queue-info"
                                    title="<%= xssAPI.encodeForHTMLAttr(queueStatus) %>"></span><%
                            }

                            // On-/OffTime
                            String onOffTimeStatus = getOnOffTimeStatus(resourceProperties, i18n, request.getLocale());
                            if (onOffTimeStatus.length() > 0) {
                                String onOffTimeIcon = "coral-Icon coral-Icon--clock";
                            %><span class="coral-Icon--info-help info-inline list-only onofftime-info <%= onOffTimeIcon %> coral-Icon--sizeXS"
                                itemprop="onofftime-info"
                                title="<%= xssAPI.encodeForHTMLAttr(onOffTimeStatus) %>"></span><%
                            }

                            // scheduled activation ((de-)activate later)
                            List<Workflow> scheduledWorkflows = getScheduledWorkflows(workflowStatus);
                            String scheduledIcon = (scheduledWorkflows.size() > 0) ? "coral-Icon coral-Icon--calendar" : "";
                            if (scheduledWorkflows.size() > 0 && !deactivated) {
                            %><span class="coral-Icon--info-help info-inline list-only scheduledpublication-info <%= scheduledIcon %> coral-Icon--sizeXS"
                                    itemprop="scheduledpublication-info"
                                    title="<%= xssAPI.encodeForHTMLAttr(getScheduledStatus(scheduledWorkflows, i18n, request.getLocale(), resourceResolver)) %>"></span><%
                            }
                            %></span>
                        <span class="user" itemprop="publishedby"><%= publishedBy != null && !deactivated ? xssAPI.encodeForHTML(publishedBy) : "" %></span>

                </p><%

                    // add the custom data
                    PageInfoAggregator piAggregatorService = sling.getService(PageInfoAggregator.class);
                    LinkedHashMap<String, List<String>> showColumnInfo = (LinkedHashMap<String, List<String>>) request.getAttribute("sites.listView.info.providers");

                    if (piAggregatorService != null
                            && showColumnInfo != null) {

                        Map<String, Object> customPageData = piAggregatorService.getAggregatedPageInfo(slingRequest, resource);

                        for (Map.Entry<String, List<String>> columnInfoEntry : showColumnInfo.entrySet()) {
                            String providerName = columnInfoEntry.getKey();
                            Map<String, Object> providerCustomProperties = (Map<String, Object>) customPageData.get(providerName);

                            if (providerCustomProperties != null) {
                                for (String columnProviderProperty : columnInfoEntry.getValue()) {
                                    Object propValue = providerCustomProperties.get(columnProviderProperty);
                                    Object trendInfo = providerCustomProperties.get(columnProviderProperty + "trend");
                                    if (propValue != null) {
                                        request.setAttribute("sites.listView.info.render.provider", providerName);
                                        request.setAttribute("sites.listView.info.render.providerProperty", columnProviderProperty);
                                        request.setAttribute("sites.listView.info.render.value", propValue.toString());
                                        request.setAttribute("sites.listView.info.render.trend", trendInfo);
                                    %>
                                    <cq:include path="<%=resource.getPath()%>" resourceType="cq/gui/components/siteadmin/admin/listview/columns/analyticscolumnrenderer"/>
                                    <%
                                    }
                                }
                            } else {
                                log.warn("No custom information found for provider '" + providerName + "'!");
                            }
                        }
                    } else {
                        log.debug("No PageInfoAggregator service found and/or no column information found on request attributes, no custom data will be available!");
                    }
                %></div>
            </div>
        </a>
        <div class="foundation-collection-quickactions" data-foundation-collection-quickactions-rel="<%= StringUtils.join(getActionRels(resource, acm, hasAnalytics), " ") %>"><%
            if (hasPermission(acm, resource, Privilege.JCR_READ)) {
                %><button class="foundation-collection-action" data-foundation-collection-action='{"action": "cq.wcm.open", "data": {"cookiePath":"<%= request.getContextPath() %>/","href":"<%= request.getContextPath() %>/bin/wcmcommand?cmd=open&_charset_=utf-8&path={item}"}}'
                    type="button" autocomplete="off" title="<%= i18n.get("Open") %>"
                    ><i class="coral-Icon coral-Icon--edit coral-Icon--sizeXS"></i>
                </button>

                <a title="<%= i18n.get("View Properties") %>" x-cq-linkchecker="skip"
                   href="<%= xssAPI.getValidHref(request.getContextPath() + "/libs/wcm/core/content/sites/properties.html" + Text.escapePath(cqPage.getPath())) %>"
                    ><i class="coral-Icon coral-Icon--infoCircle coral-Icon--sizeXS"></i></a>

                <button class="foundation-collection-action" data-foundation-collection-action='{"action": "cq.wcm.copy"}'
                        type="button" autocomplete="off" title="<%= i18n.get("Copy") %>"
                    ><i class="coral-Icon coral-Icon--copy coral-Icon--sizeXS"></i></button><%
            }

            if (hasPermission(acm, resource, Privilege.JCR_REMOVE_NODE)) {
                %><a title="<%= i18n.get("Move") %>" x-cq-linkchecker="skip"
                     href="<%= xssAPI.getValidHref(request.getContextPath() + "/libs/wcm/core/content/sites/movepagewizard.html" + parentPath + "?item=" + Text.escapePath(cqPage.getPath()) + "&_charset_=utf-8") %>"
                    ><i class="coral-Icon coral-Icon--move coral-Icon--sizeXS"></i></a><%
            }

            if (hasPermission(acm, resource, "crx:replicate")) {
                %><button class="foundation-collection-action" data-foundation-collection-action='{"action": "cq.wcm.publish", "data": {"referenceSrc": "<%= request.getContextPath() %>/libs/wcm/core/content/reference.json?_charset_=utf-8{&path*}", "wizardSrc": "<%= request.getContextPath() %>/libs/wcm/core/content/sites/publishpagewizard.html?_charset_=utf-8{&item*}"}}'
                          type="button" autocomplete="off" title="<%= i18n.get("Publish") %>"
                    ><i class="coral-Icon coral-Icon--globe coral-Icon--sizeXS"></i></button><%
            }
        %></div><%
    } else {
        %>
        <% if (getAdminUrl(resource, currentPage).indexOf("harbor") >= 0) { %>
        <a href="<%= xssAPI.getValidHref(resource.getPath() + ".html") %>"  x-cq-linkchecker="skip">
                <%
            } else {
        %>
            <a href="<%= xssAPI.getValidHref(request.getContextPath() + getAdminUrl(resource, currentPage)) %>" itemprop="admin" x-cq-linkchecker="skip">
                    <%
            }
        %>

            <span class="image">
                <img itemprop="thumbnail" width="192" src="<%= xssAPI.getValidHref(request.getContextPath() + Text.escapePath(resource.getPath()) + ".folderthumbnail.jpg") %>" alt="">
                <i class="show-list coral-Icon coral-Icon--sizeXS coral-Icon--folder"></i>
            </span>
            <div class="label">
                <h4 class="foundation-collection-item-title" itemprop="title"><%= xssAPI.encodeForHTML(title) %></h4>
            </div>
        </a>
        <div class="foundation-collection-quickactions" data-foundation-collection-quickactions-rel="<%= StringUtils.join(getActionRels(resource, acm, hasAnalytics), " ") %>"><%
            if (hasPermission(acm, resource, Privilege.JCR_READ)) {
                %><a title="<%= i18n.get("View Properties") %>" x-cq-linkchecker="skip"
                    href="<%= xssAPI.getValidHref(request.getContextPath() + "/libs/wcm/core/content/sites/folderproperties.html" + Text.escapePath(resource.getPath())) %>"
                    ><i class="coral-Icon coral-Icon--infoCircle coral-Icon--sizeXS"></i></a>

                <button class="foundation-collection-action" data-foundation-collection-action='{"action": "cq.wcm.copy"}'
                    type="button" autocomplete="off" title="<%= i18n.get("Copy") %>"
                    ><i class="coral-Icon coral-Icon--copy coral-Icon--sizeXS"></i></button><%
            }

            if (hasPermission(acm, resource, Privilege.JCR_REMOVE_NODE)) {
                %><a title="<%= i18n.get("Move") %>" x-cq-linkchecker="skip"
                    href="<%= xssAPI.getValidHref(request.getContextPath() + "/libs/wcm/core/content/sites/movepagewizard.html" + parentPath + "?item=" + Text.escapePath(resource.getPath()) + "&_charset_=utf-8") %>"
                    ><i class="coral-Icon coral-Icon--move coral-Icon--sizeXS"></i></a><%
            }

            if (hasPermission(acm, resource, "crx:replicate")) {
                %><button class="foundation-collection-action" data-foundation-collection-action='{"action": "cq.wcm.publish", "data": {"referenceSrc": "<%= request.getContextPath() %>/libs/wcm/core/content/reference.json?_charset_=utf-8{&path*}", "wizardSrc": "<%= request.getContextPath() %>/libs/wcm/core/content/sites/publishpagewizard.html?_charset_=utf-8{&item*}"}}'
                    type="button" autocomplete="off" title="<%= i18n.get("Publish") %>"
                    ><i class="coral-Icon coral-Icon--globe coral-Icon--sizeXS"></i></button><%
            }
        %></div><%
    } %>
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
