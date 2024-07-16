<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:url var="actionUrl" value="/survey/step3" />

<h1>step1</h1>


<form:form method="post" action="${actionUrl}" autocomplete="off" modelAttribute="requestSurvey">
<%--    <form:hidden path="q1" />--%>
<%--    <form:hidden path="q2" />--%>
    질문3: <form:input path="q3" /><br>
    질문4: <form:input path="q4" /><br>
    <button type="submit">제출하기</button>
</form:form>
