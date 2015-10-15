<%@include file="/libs/harbor/components/global.jsp"%>

<%-- Rendering for the Button Itself --%>
<c:choose>
	<c:when test="${cta.opensAsModal}">
		<a id="${cta.buttonId}" role="button" class="btn ${cta.size} ${cta.style} cta-btn" data-toggle="modal" data-target="#${cta.modalId}">
			<c:out value="${cta.text}" escapeXml="false"/>
		</a>
	</c:when>
	<c:when test="${cta.opensInCurrentWindow||cta.opensInNewWindow}">
		<a id="${cta.buttonId}" role="button" class="btn ${cta.size} ${cta.style} cta-btn" href="${cta.linkTarget.href}"<c:if test="${cta.opensInNewWindow}">target="_blank"</c:if>>
			<c:out value="${cta.text}" escapeXml="false"/>
		</a>
	</c:when>
	<c:otherwise>
		<a id="${cta.buttonId}" role="button" class="btn ${cta.size} ${cta.style} cta-btn">
			<c:out value="${cta.text}" escapeXml="false"/>
		</a>
	</c:otherwise>
</c:choose>

<%-- Rendering for the Modal in the case that the buttons action is to open a Modal --%>
<c:if test="${cta.opensAsModal}">
    <cq:include script="modal.jsp" />
</c:if>
