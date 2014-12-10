<%@include file="/libs/harbor/components/global.jsp" %>

<c:set var="accordionParentId" value="${accordion.uniqueId}" scope="request" />
<div class="panel-group" role="tablist" aria-multiselectable="true">
    <c:forEach var="currItem" items="${accordion.items}" varStatus="status">
        <c:set var="accordionItemOpen" value="${isEditMode or (status.first and accordion.openFirstItem)}" scope="request" />
        <cq:include path="${currItem.name}" resourceType="${currItem.resource.resourceType}"/>
    </c:forEach>
</div>
<%--
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
--%>