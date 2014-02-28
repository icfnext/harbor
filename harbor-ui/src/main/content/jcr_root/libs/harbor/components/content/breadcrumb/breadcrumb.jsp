<%@include file="/libs/harbor/components/global.jsp" %>
<ct:component className="com.citytechinc.cq.harbor.components.content.breadcrumb.Breadcrumb" name="breadcrumb"/>

<ol class="harbor-breadcrumb breadcrumb">
    <c:forEach var="curBreadcrumbItem" items="${breadcrumb.trail}" varStatus="loop">
        <c:choose>
            <c:when test="${!loop.last && !loop.first}">
                <c:set value="${breadcrumb.intermediaryPageDisplayType == 'combo'}" var="displayTypeCombo"></c:set>
                <c:set value="${breadcrumb.intermediaryPageDisplayType == 'title'}" var="displayTypeTitle"></c:set>
                <c:set value="${breadcrumb.intermediaryPageDisplayType == 'icon'}" var="displayTypeIcon"></c:set>
                <c:set value="fa ${breadcrumb.intermediaryPageDelimiter}" var="delimiter"></c:set>
                <c:set value="${curBreadcrumbItem.title}" var="title"></c:set>
                <li><a href="${curbreadcrumbItem.href}"><i
                        class="${displayTypeCombo || displayTypeIcon ? delimiter : ''}"></i> ${displayTypeCombo || displayTypeTitle ? title : ''}
                </a></li>
            </c:when>
            <c:when test="${loop.first}">
                <c:set value="${breadcrumb.rootPageDisplayType == 'combo'}" var="displayTypeCombo"></c:set>
                <c:set value="${breadcrumb.rootPageDisplayType == 'title'}" var="displayTypeTitle"></c:set>
                <c:set value="${breadcrumb.rootPageDisplayType == 'icon'}" var="displayTypeIcon"></c:set>
                <c:set value="${breadcrumb.rootPageDelimiter}" var="delimiter"></c:set>
                <c:set value="${curBreadcrumbItem.title}" var="title"></c:set>
                <li><a href="${curBreadcrumbItem.href}"><i
                        class="${displayTypeIcon ? delimiter : ''}"></i> ${displayTypeCombo || displayTypeTitle ? title : ''}
                </a></li>
            </c:when>
            <c:when test="${loop.last && !breadcrumb.hideCurrentPageInBreadcrumb}">
                <c:set value="${breadcrumb.currentPageDisplayType == 'combo'}" var="displayTypeCombo"></c:set>
                <c:set value="${breadcrumb.currentPageDisplayType == 'title'}" var="displayTypeTitle"></c:set>
                <c:set value="${breadcrumb.currentPageDisplayType == 'icon'}" var="displayTypeIcon"></c:set>
                <c:set value="${breadcrumb.currentPageDelimiter}" var="delimiter"></c:set>
                <c:set value="${curBreadcrumbItem.title}" var="title"></c:set>
                <li class="active"><i
                        class="${displayTypeCombo || displayTypeIcon ? delimiter : ''}"></i> ${displayTypeCombo || displayTypeTitle ? title : ''}
                </li>
            </c:when>
        </c:choose>
    </c:forEach>
</ol>
