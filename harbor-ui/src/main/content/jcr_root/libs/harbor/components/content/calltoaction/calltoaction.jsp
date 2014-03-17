<%@include file="/libs/harbor/components/global.jsp" %>
<ct:component className="com.citytechinc.cq.harbor.components.content.calltoaction.CallToAction" name="cta"/>



<c:if test="${cta.openNewWindow}">
<button id="callToActionButton" class="btn ${cta.size} ${cta.style}" data-toggle="modal" data-target="#callToActionModal">
	<c:out value="${cta.text}"/>
</button>

<!-- Modal -->
<div class="modal fade cta-modal" data-keyboard="false" tabindex="-1"  role="dialog" id="callToActionModal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body">
				<cq:include path="container-par" resourceType="foundation/components/parsys" />
			</div>
		</div>
	</div>
</div>

	<script type="text/javascript">
		jQuery( document ).ready( function( $ ) {
			$( '#callToActionModal' ).on('shown.bs.modal', function() {
				Harbor.Components.editables().changeEditableContext( '${cta.path}/container-par' );
			} );
			$( '#callToActionModal' ).on('hide.bs.modal', function() {
				Harbor.Components.editables().resetEditableContext();
			} );
		} );
	</script>
</c:if>
