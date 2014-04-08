<%@include file="/libs/harbor/components/global.jsp" %>
<c:set var="curtabs" scope="page" value="${tabs}"/>

<div class="tabs-container" class="tabs" id="${curtabs.uniqueId}-tabs-container">
    <ul class="nav nav-tabs" data-tabs="tabs">
        <c:forEach var="curTab" items="${curtabs.tabs}" varStatus="status">
            <c:set var="dataPathString" value="data-path='${curtabs.path}'"/>
            <li id="${curTab.uniqueId}" data-name="${curTab.name}"
                class="${status.first? 'active':'' }"  ${isEditMode? dataPathString : ''}>
                <a href="#tabs-${curTab.uniqueId}" data-toggle="tab">${curTab.title}</a>
            </li>
        </c:forEach>
    </ul>

    <div class="tab-content">
        <c:forEach var="curTab" items="${curtabs.tabs}" varStatus="status">
            <div id="tabs-${curTab.uniqueId}" class="tab-pane ${status.first ? 'active' : ''}">
                <section class="${isEditMode || isDesignMode ? 'tab-par-author': ''}">
                    <cq:include path="${curTab.name}" resourceType="harbor/components/content/tabs/tab"/>
                </section>
            </div>
        </c:forEach>
    </div>
</div>

<c:if test="${isEditMode}">
    <script type="text/javascript">
        $(document).ready(
            function ($) {
                $(document).ready(function () {
                    Harbor.Components.Tabs.initTabsSorting("${curtabs.uniqueId}");
                });
        });
    </script>
</c:if>
