<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach items="${renderableItems}" var="curListItem">
    ${curListItem.renderedItem}
</c:forEach>
