<%@include file="/libs/harbor/components/global.jsp" %>

<a href="${navElement.elementLinkTarget}">${navElement.elementTitle}</a><a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-caret-down"></i></a>
<ul class="dropdown-menu" id="${navElement.uniqueIdentifier}-dropdown">
    <li><cq:include path="navpar" resourceType="foundation/components/parsys" /></li>
</ul>

<c:if test="${isEditMode || isDesignMode}">
    <script>
        jQuery( document ).ready( function( $ ) {
            $( '#${navElement.uniqueIdentifier}-dropdown' ).parent().on( 'shown.bs.dropdown', function() {
                Harbor.Components.editables.changeEditableContext( '${navElement.path}/navpar' );
            } );

            $( '#${navElement.uniqueIdentifier}-dropdown' ).parent().on( 'hidden.bs.dropdown', function() {
                Harbor.Components.editables.resetEditableContext();
            } );
        } );
    </script>
</c:if>