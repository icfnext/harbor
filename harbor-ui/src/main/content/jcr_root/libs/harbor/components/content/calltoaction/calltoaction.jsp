<%@include file="/libs/harbor/components/global.jsp" %>
<ct:component className="com.citytechinc.cq.harbor.components.content.calltoaction.CallToAction" name="cta"/>

<!-- Call to Action Button -->
<a id="callToActionButton" class="btn ${cta.size} ${cta.style}"
		<c:if test="${cta.openModal}"> data-toggle="modal" data-target="#callToActionModal" </c:if>
		<c:if test="${cta.openWindow || cta.openCurrent}"> href="${cta.linkUrl}" </c:if>
		<c:if test="${cta.openWindow}">target="_blank"</c:if>>
	<c:out value="${cta.text}"/>
</a>

<!-- Modal -->
<c:if test="${cta.openModal}">
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
			$( '#callToActionModal' ).on('show.bs.modal', function() {
				Harbor.Components.editables().changeEditableContext( '${cta.path}/container-par' );
			} );
			$( '#callToActionModal' ).on('hide.bs.modal', function() {
				Harbor.Components.editables().resetEditableContext();
			} );
		} );
	</script>
</c:if>
