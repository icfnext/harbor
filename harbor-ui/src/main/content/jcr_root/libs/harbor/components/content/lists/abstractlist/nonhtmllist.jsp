<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach items="${items}" var="curListItem">
    ${curListItem.renderedItem}
</c:forEach>
