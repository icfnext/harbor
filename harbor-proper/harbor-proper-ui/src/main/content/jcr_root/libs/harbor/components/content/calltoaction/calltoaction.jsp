<%@include file="/libs/harbor/components/global.jsp"%>

<c:choose>
	<c:when test="${cta.openModal}">
		<a id="callToActionButton-${cta.id}" role="button" class="btn ${cta.size} ${cta.style} cta-btn" data-toggle="modal" data-target="#callToActionModal-${cta.id}">
			<c:out value="${cta.text}"/>
		</a>
	</c:when>
	<c:when test="${cta.openCurrent||cta.openWindow}">
		<a id="callToActionButton-${cta.id}" role="button" class="btn ${cta.size} ${cta.style} cta-btn" href="${cta.linkUrl}"<c:if test="${cta.openWindow}">target="_blank"</c:if>>
			<c:out value="${cta.text}"/>
		</a>
	</c:when>
	<c:otherwise>
		<a id="callToActionButton-${cta.id}" role="button" class="btn ${cta.size} ${cta.style} cta-btn">
			<c:out value="${cta.text}"/>
		</a>
	</c:otherwise>
</c:choose>

<c:if test="${cta.openModal}">
	<div class="modal fade cta-modal" data-keyboard="false" tabindex="-1"  role="dialog" id="callToActionModal-${cta.id}">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<cq:include path="container-par-${cta.id}" resourceType="foundation/components/parsys"/>
				</div>
			</div>
		</div>
	</div>

	<c:if test="${isEditMode || isDesignMode}">
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
</c:if>
