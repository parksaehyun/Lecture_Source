<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:url var="loginURL" value="/member/login"/>

<a href="${loginURL}">로그인</a>
<a href="c:url value='/member/join' />">회원가입</a>

<form method="POST" action="${loginURL}">
    이메일 : <input type="text" name="email"><br>
    비밀번호 : <input type="password" name="password"><br>
    <button type="submit">로그인</button>
</form>