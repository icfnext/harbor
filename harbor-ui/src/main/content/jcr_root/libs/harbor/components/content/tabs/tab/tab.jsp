<%@include file="/apps/videojet-global/components/global.jsp"%>

<div <c:if test="${isEditMode || isDesignMode}">class="tab-par-author"</c:if>>
	<cq:include path="tab-par" resourceType="videojet-global/components/content/innerParsys" />
</div>