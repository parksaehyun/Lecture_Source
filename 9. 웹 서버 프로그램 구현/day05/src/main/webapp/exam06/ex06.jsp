<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="html" value="<script>alert('알림!);</script>" />
<%--${html}--%>
<c:out value="${html}"/>
<c:out value="${str}" default="값 없음"/>