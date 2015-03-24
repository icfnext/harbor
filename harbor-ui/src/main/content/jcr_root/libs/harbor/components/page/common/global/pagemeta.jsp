<%@include file="/libs/harbor/components/global.jsp" %>
<bedrock:component className="com.citytechinc.aem.harbor.core.components.content.page.meta.MetaPage" name="metaPage" />

<c:if test="${!metaPage.disableSchemaOrg}">
	<meta itemprop="name" content="${metaPage.pageName}">
	<meta itemprop="description" content="${metaPage.description}">
	<meta itemprop="image" content="${metaPage.fullyQualifiedPageImage}">
</c:if>

<c:if test="${not empty metaPage.publisherHandle}">
	<meta name="twitter:card" content="summary">
	<meta name="twitter:site" content="${metaPage.publisherHandle}">
	<meta name="twitter:title" content="${metaPage.pageName}">
	<meta name="twitter:description" content="${metaPage.description}">
	<meta name="twitter:image" content="${metaPage.fullyQualifiedPageImage}">
	<meta name="twitter:url" content="${metaPage.fullyQualifiedPageUrl}">
</c:if>

<c:if test="${ not empty metaPage.ogType}">
	<meta property="og:title" content="${metaPage.pageName}" />
	<meta property="og:type" content="${metaPage.ogType}" />
	<c:choose>
		<c:when test="${not empty metaPage.cannonicalRef}">
			<meta property="og:url" content="${metaPage.cannonicalRef}" />
		</c:when>
		<c:otherwise>
			<meta property="og:url" content="${metaPage.fullyQualifiedPageUrl}" />
		</c:otherwise>
	</c:choose>	
	<meta property="og:image" content="${metaPage.fullyQualifiedPageImage}" />
	<meta property="og:description" content="${metaPage.description}" />
	<meta property="og:site_name" content="${metaPage.homePageTitle}" />
</c:if>

<c:if test="${not empty metaPage.cannonicalRef}">
	<link rel="canonical" href="${metaPage.cannonicalRef}" />
</c:if>

<c:if test="${not empty metaPage.robotsContent}">
    <meta name="robots" content="${metaPage.robotsContent}">
</c:if>