<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:url var="loginurl" value="/member/login" />
<c:url var="joinUrl" value="/member/join" />
<c:url var="logoutUrl" value="/member/logout" />

<c:if test="${sessionScope.member == null}">
    <%--미 로그인--%>
    <a href="${loginUrl}">로그인</a>
    <a href="${joinUrl}">회원가입</a>
</c:if>
<c:if test="${sessionScope.member != null}">
    ${sessionScope.member.userName}(${sessionScope.member.email})님 로그인...
    <a href="${logoutUrl}">로그아웃</a>
</c:if>

