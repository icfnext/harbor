<%@include file="/libs/harbor/components/global.jsp" %>
<%@page session="false"%><body class="<c:forEach var="currentClassName" items="${componentContext.cssClassNames}">${currentClassName} </c:forEach>">

<cq:include script="content.jsp"/>

<cq:include path="cloudservices" resourceType="cq/cloudserviceconfigs/components/servicecomponents"/>

</body>
