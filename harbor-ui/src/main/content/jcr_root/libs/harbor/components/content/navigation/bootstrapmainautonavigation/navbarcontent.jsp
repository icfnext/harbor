<%@include file="/libs/harbor/components/global.jsp" %>

<div class="navbar-collapse collapse" id="${bootstrapMainAutoNavigation.id}">
<nav id="site-navigation" class="main-navigation" role="navigation">

    <ul class="nav navbar-nav meun-primary">
        <c:choose>
            <c:when test="${bootstrapMainAutoNavigation.onStructuralPage}">
                <li><a href="#">Navigation not rendered on Structural Page</a></li>
            </c:when>
            <c:otherwise>
                <c:forEach var="currentNode" items="${bootstrapMainAutoNavigation.tree.root.childNodesIterator}" varStatus="status">
                    <c:choose>
                        <c:when test="${currentNode.hasChildNodes && bootstrapMainAutoNavigation.tree.presentMainNavigationItemInDropDown}">
                            <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">${currentNode.navigationLink.title} <i class="fa fa-caret-down"></i></a>
                                <ul class="dropdown-menu pull-right" role="menu">
                                    <li class="section-link"><a href="${currentNode.href}">${currentNode.navigationLink.title}</a></li>
                                    <li role="separator" class="divider"></li>
                                    <c:forEach items="${currentNode.childNodesIterator}" var="currentInnerNode">
                                        <li class="subpage-link"><a href="${currentInnerNode.href}">${currentInnerNode.navigationLink.title}</a>
                                            <c:if test="${currentInnerNode.hasChildNodes}">
                                                <ul><harbor:includeListItems items="${currentInnerNode.items}" itemVar="nestedNavigablePage" script="nestednavitems.jsp"/></ul>
                                            </c:if>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </li>
                        </c:when>
                        <c:when test="${currentNode.hasChildNodes && !bootstrapMainAutoNavigation.tree.presentMainNavigationItemInDropDown}">
                            <li class="dropdown-split-left"><a href="${currentNode.href}">${currentNode.navigationLink.title}</a></li>
                            <li><a href="#" data-toggle="dropdown"><i class="fa fa-caret-down"></i></a>
                                <ul class="dropdown-menu pull-right" role="menu">
                                    <c:forEach items="${currentNode.childNodesIterator}" var="currentInnerNode">
                                        <li class="subpage-link"><a href="${currentInnerNode.href}">${currentInnerNode.navigationLink.title}</a>
                                            <c:if test="${currentInnerNode.hasChildNodes}">
                                                <ul><harbor:includeListItems items="${currentInnerNode.items}" itemVar="nestedNavigablePage" script="nestednavitems.jsp"/></ul>
                                            </c:if>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="${currentNode.href}">${currentNode.navigationLink.title}</a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </c:otherwise>
        </c:choose>

    </ul>
</nav>
</div>