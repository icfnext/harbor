<%@include file="/libs/harbor/components/global.jsp" %>
<c:if test="${isAuthor}">
    <cq:includeClientLib js="harbor.author-common"/>
</c:if>