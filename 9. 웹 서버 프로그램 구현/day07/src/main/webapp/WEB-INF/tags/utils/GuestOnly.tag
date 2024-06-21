<%@ tag import="org.choongang.member.MemberUtil" %>
<%@ tag body-content="scriptless" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<% if (!MemberUtil.isLogin(request)) { %>
<jsp:doBody />
<%  } %>