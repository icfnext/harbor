<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ct:component className="com.citytechinc.cq.harbor.components.content.calltoaction.CallToAction" name="cta"/>

<a id="callToActionButton${cta.id}" role="button" class=" btn ${cta.size} ${cta.style}" <c:if test="${cta.openModal}">data-toggle="modal" data-target="#callToActionModal${cta.id}"</c:if> <c:if test="${cta.openWindow}">target="_blank"</c:if> <c:if test="${cta.openCurrent||cta.openWindow}">href="${cta.linkUrl}"</c:if>>
	<c:out value="${cta.text}"/>
</a>

<c:if test="${cta.openModal}">
<div class="modal fade cta-modal" data-keyboard="false" tabindex="-1"  role="dialog" id="callToActionModal-${cta.id}">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body">
				<cq:include path="container-par-${cta.id}" resourceType="foundation/components/parsys" />
			</div>
		</div>
	</div>
</div>
	<script type="text/javascript">
		jQuery( document ).ready( function( $ ) {
			$( '#callToActionModal-${cta.id}' ).on('show.bs.modal', function() {
				Harbor.Components.editables().changeEditableContext( '${cta.path}/container-par-${cta.id}' );
			} );
			$( '#callToActionModal-${cta.id}' ).on('hide.bs.modal', function() {
				Harbor.Components.editables().resetEditableContext();
			} );
		} );
	</script>
</c:if>
