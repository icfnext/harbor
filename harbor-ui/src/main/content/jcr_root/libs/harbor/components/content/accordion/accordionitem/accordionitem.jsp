<%@include file="/libs/harbor/components/global.jsp"%>

<div class="${accordionParentId}-accordion-container">
<div class="panel panel-default" data-node-name="${accordionItem.name}">
    <div class="panel-heading" role="tab" id="heading-${accordionItem.uniqueId}">
        <h4 class="panel-title">
            <c:choose>
                <c:when test="${isEditMode or isDesignMode}">
                    ${accordionItem.title}
                </c:when>
                <c:otherwise>
                    <a data-toggle="collapse" data-parent=".${accordionParentId}-accordion-container" href="#${accordionItem.uniqueId}" aria-expanded="true" aria-controls="${accordionItem.uniqueId}">
                        ${accordionItem.title}
                    </a>
                </c:otherwise>
            </c:choose>
        </h4>
    </div>
    <div id="${accordionItem.uniqueId}" class="panel-collapse collapse${isEditMode or isDesignMode or accordionItemOpen ? ' in' : ''}" role="tabpanel" aria-labelledby="heading-${accordionItem.uniqueId}">
        <div class="panel-body">
            <cq:include path="accordionitem-par" resourceType="foundation/components/parsys" />
        </div>
    </div>
</div>
</div>