<%@include file="/libs/harbor/components/global.jsp" %>

<c:set var="layoutConfig" value="${properties.layoutConfig}" />

<!--  Main Content -->
<div id="content-body" class="container">
    <div class="row">
        <div class="col-md-12">
            <cq:include path="lead" resourceType="foundation/components/parsys" />
        </div>
    </div>
    <div class="row">
        <c:choose>

            <%-- Left Col, Main Col --%>
            <c:when test="${layoutConfig eq 'layout_1'}">
                <div class="col-md-2">
                    <cq:include path="content2" resourceType="foundation/components/iparsys" />
                    <cq:include path="content3" resourceType="foundation/components/parsys" />

                    <c:if test="${!isPreviewMode && isEditMode}">
                        <p class="hideinpreview">Content below is orphaned from another layout.</p>
                        <cq:include path="content4" resourceType="foundation/components/iparsys" />
                        <cq:include path="content5" resourceType="foundation/components/parsys" />
                    </c:if>
                </div>
                <div class="col-md-10">
                    <cq:include path="content1" resourceType="foundation/components/parsys" />
                </div>
            </c:when>
            
            <%-- Right Col, Main Col --%>
            <c:when test="${layoutConfig eq 'layout_2'}">
                <div class="col-md-10">
                    <cq:include path="content1" resourceType="foundation/components/parsys" />
                </div>
                <div class="col-md-2">
                    <cq:include path="content2" resourceType="foundation/components/iparsys" />
                    <cq:include path="content3" resourceType="foundation/components/parsys" />
                    <c:if test="${!isPreviewMode && isEditMode}">
                        <p class="hideinpreview">Content below is orphaned from another layout.</p>
                        <cq:include path="content4" resourceType="foundation/components/iparsys" />
                        <cq:include path="content5" resourceType="foundation/components/parsys" />
                    </c:if>
                </div>
            </c:when>
            
            <%-- Left Col, Main Col, Right Col--%>
            <c:when test="${layoutConfig eq 'layout_3'}">
                <div class="col-md-2">
                    <cq:include path="content2" resourceType="foundation/components/iparsys" />
                    <cq:include path="content3" resourceType="foundation/components/parsys" />
                </div>

                <div class="col-md-8">
                    <cq:include path="content1" resourceType="foundation/components/parsys" />
                </div>
                <div class="col-md-2">
                    <cq:include path="content4" resourceType="foundation/components/iparsys" />
                    <cq:include path="content5" resourceType="foundation/components/parsys" />
                </div>
            </c:when>

            <%--  Main Col, Left Col, Right Col --%>
            <c:when test="${layoutConfig eq 'layout_4'}">
                <div class="col-md-2">
                    <cq:include path="content2" resourceType="foundation/components/iparsys" />
                    <cq:include path="content3" resourceType="foundation/components/parsys" />
                </div>
                <div class="col-md-2">
                    <cq:include path="content4" resourceType="foundation/components/iparsys" />
                    <cq:include path="content5" resourceType="foundation/components/parsys" />
                </div>
                <div class="col-md-8">
                    <cq:include path="content1" resourceType="foundation/components/parsys" />
                </div>
            </c:when>

            <%-- Left Col, Right Col, Main Col --%>
            <c:when test="${layoutConfig eq 'layout_5'}">
                <div class="col-md-8">
                    <cq:include path="content1" resourceType="foundation/components/parsys" />
                </div>
                <div class="col-md-2">
                    <cq:include path="content2" resourceType="foundation/components/iparsys" />
                    <cq:include path="content3" resourceType="foundation/components/parsys" />
                </div>
                <div class="col-md-2">
                    <cq:include path="content4" resourceType="foundation/components/iparsys" />
                    <cq:include path="content5" resourceType="foundation/components/parsys" />
                </div>
            </c:when>

            <%-- default is full width --%>
            <c:otherwise>
                <div class="col-md-12">
                    <cq:include path="content1" resourceType="foundation/components/parsys" />
                </div>
                <c:if test="${!isPreviewMode && isEditMode}">
                    <p class="hideinpreview">Content below is orphaned from another layout.</p>
                    <cq:include path="content2" resourceType="foundation/components/iparsys" />
                    <cq:include path="content3" resourceType="foundation/components/parsys" />
                    <cq:include path="content4" resourceType="foundation/components/iparsys" />
                    <cq:include path="content5" resourceType="foundation/components/parsys" />
                </c:if>
            </c:otherwise>
        </c:choose>

    </div> <!--row -->

</div> <!-- /contentBody -->

