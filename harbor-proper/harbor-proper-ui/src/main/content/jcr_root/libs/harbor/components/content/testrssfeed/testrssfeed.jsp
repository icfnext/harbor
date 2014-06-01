<%@include file="/libs/harbor/components/global.jsp" %>

<c:if test="${testrssfeed.hasRssFeedResourcePath}">

    <cq:include path="${testrssfeed.rssFeedResourcePath}" resourceType="harbor/components/content/rsschannel" />

</c:if>