<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:url var="actionUrl" value="/member/login" />
<spring:message var="saveEmailTitle" code='이메일_기억하기' />

<h1>
    <spring:message code="로그인" />
</h1>

<form:form method="post" action="${actionUrl}" autocomplete="off" modelAttribute="requestLogin">
    <%--modelAttribute="requestLogin" : 기본값 커맨드 객체?--%>
    <dl>
        <dt>
            <spring:message code="이메일" />
        </dt>
        <dd>
            <form:input path="email" />
            <form:errors path="email" /> <%--검증 실패 시 메세지 출력--%>
        </dd>
    </dl>
    <dl>
        <dt>
            <spring:message code="비밀번호" />
        </dt>
        <dd>
            <form:password path="password" />
            <form:errors path="password" /> <%--검증 실패 시 메세지 출력--%>
        </dd>
    </dl>
    <div>
        <form:checkbox path="saveEmail" value="true" label="${saveEmailTitle}" />
    </div>

    <form:errors element="div" delimiter="" /> <%--특정필드에 한정하지 않고 커맨드객체 그 자체의 에러가 여기서 출력됨--%>

    <button type="submit">
        <spring:message code="로그인" />
    </button>
</form:form>