<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="util" tagdir="/WEB-INF/tags/utils" %>
<c:url var="loginurl" value="/member/login" />
<c:url var="joinUrl" value="/member/join" />
<c:url var="logoutUrl" value="/member/logout" />

로그인 상태 : ${isLogin} <br>

<%--미 로그인 상태일때 출력되는 부분--%>
<util:GuestOnly>
    <a href="${loginUrl}">로그인</a>
    <a href="${joinUrl}">회원가입</a>
</util:GuestOnly>

<%--로그인 상태일때 출력되는 부분--%>
<util:MemberOnly>
    ${loggedMember.userName}(${loggedMember.email})님 로그인...
    <a href="${logoutUrl}">로그아웃</a>
</util:MemberOnly>

