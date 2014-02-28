<%@include file="/libs/harbor/components/global.jsp" %>
<ct:component className="com.citytechinc.cq.harbor.components.content.calltoaction.CallToAction" name="cta"/>

<button class="btn ${cta.size} ${cta.style}"><c:out value="${cta.text}"/></button>