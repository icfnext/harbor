<%@include file="/libs/harbor/components/global.jsp" %>

<div class="navbar-collapse collapse" id="${bootstrapMainAutoNavigation.id}">
    <ul class="nav navbar-nav">
        <c:forEach var="currentNode" items="${bootstrapMainAutoNavigation.root.itemsIterator}" varStatus="status">
            <c:choose>
                <c:when test="${currentNode.hasItems}">
                    <li class="dropdown-split-left"><a href="${currentNode.href}">${currentNode.navigationLink.title}</a></li>
                    <li><a href="#" data-toggle="dropdown"><i class="fa fa-caret-down"></i></a>
                        <ul class="dropdown-menu pull-right" role="menu">
                            <c:forEach items="${currentNode.itemsIterator}" var="currentInnerNode">
                                <li><a href="${currentInnerNode.href}">${currentInnerNode.navigationLink.title}</a>
                                    <c:if test="${currentInnerNode.hasItems}">
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
    </ul>
</div>