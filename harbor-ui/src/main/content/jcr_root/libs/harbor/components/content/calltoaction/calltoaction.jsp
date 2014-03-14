<%@include file="/libs/harbor/components/global.jsp" %>
<ct:component className="com.citytechinc.cq.harbor.components.content.calltoaction.CallToAction" name="cta"/>



<button id="callToActionButton" class="btn ${cta.size} ${cta.style}" data-toggle="modal" data-target="#callToActionModal"><c:out value="${cta.text}"/></button>


<div class="modal fade cta-modal" <c:if test="${CQ.WCM.isEditMode || CQ.WCM.isDesignMode}">data-keyboard="false"</c:if><c:if test="${CQ.WCM.isPublish || CQ.WCM.isPreviewMode}">tabindex="-1"</c:if>  role="dialog" id="callToActionModal">

<!-- Modal -->


	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body">
				<cq:include path="modal-container-par" resourceType="foundation/components/parsys" />
			</div>
		</div>
	</div>
</div>


	<script type="text/javascript">
		Harbor.Components.CallToAction.changeEditableContext( '${cta.path}/modal-container-par' );

	</script>
