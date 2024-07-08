<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach items="${list}" var="item">
    <div>${item.name}</div>
</c:forEach>