<%@include file="/libs/harbor/components/global.jsp" %>
<%@page session="false"%>




<div class="container">
        <div class="row">
            <div class="col-md-12">
                <header id="masthead" class="site-header">
                    <div class="row">
                        <div class="col-md-4">
                            <a href="/" id="home-link"><cq:include path="site-title" resourceType="harbor/components/content/title" /></a>
                        </div>
                        <div class="col-md-8">
                            <cq:include path="bootstrapmainmannav" resourceType="harbor/components/content/navigation/bootstrapmainmanualnavigation" />
                        </div>
                    </div>
                    <cq:include path="headerpar" resourceType="foundation/components/iparsys"/>
                </div>
            </header>
        </div>
    </div>
</div>