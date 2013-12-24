<%@include file="/apps/videojet-uptime/components/global.jsp" %>

<ct:component className="com.citytechinc.cq.harbor.components.content.columns.FluidColumnRow" name="fluidColumnRow"/>

<c:set var="currow" scope="page" value="${fluidColumnRow}" />

<p style="font-weight: bold; color: #444; font-style: italic">Column Row Component</p>

<div class="row" id="${currow.uniqueId}-column-row">
    <c:forEach var="curcolumn" items="${currow.columns}" varStatus="status">
        <div class="${curcolumn.spanClass} ${curcolumn.offsetClass}" style="width : 80%; padding : 5px; border : 2px solid #CCC; margin-top: 2px;" name="${curcolumn.name}">
            <p style="color: #444; font-style: italic">Column ${status.count}</p>
            <div>
                <cq:include path="${curcolumn.name}" resourceType="harbor/components/content/column" />
            </div>
        </div>
    </c:forEach>
</div>
