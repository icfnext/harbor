<%@include file="/libs/harbor/components/global.jsp"%>
<!--TODO: figure out why tab's edit bar is not appearing. -->
<ct:component className="com.citytechinc.cq.harbor.components.content.tabs.Tab" name="tab"/>
<div class='${isEditMode || isDesignMode?"tab-par-author" : ""}'>
	<cq:include path="tab-par" resourceType="harbor/components/content/innerParsys" />
</div>