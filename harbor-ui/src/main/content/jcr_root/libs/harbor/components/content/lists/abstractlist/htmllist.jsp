<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach items="${renderableItems}" var="curListItem">
    <li>${curListItem.renderedItem}</li>
</c:forEach>
