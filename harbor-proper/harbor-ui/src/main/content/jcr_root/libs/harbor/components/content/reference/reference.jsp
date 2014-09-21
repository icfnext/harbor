<%@include file="/libs/harbor/components/global.jsp" %>

<c:choose>
    <c:when test="${reference.hasParagraphPath}">
        <bedrock:disableAuthor>
            <c:choose>
                <c:when test="${reference.hasSelector}">
                    <sling:include path="${reference.paragraphPath}" addSelectors="${reference.selector}" />
                </c:when>
                <c:otherwise>
                    <sling:include path="${reference.paragraphPath}" />
                </c:otherwise>
            </c:choose>
        </bedrock:disableAuthor>
    </c:when>
    <c:otherwise>
        <c:if test="${isEditMode}">
            <p class="authoring-placeholder">
                Select a Component to Reference
            </p>
        </c:if>
    </c:otherwise>
</c:choose>