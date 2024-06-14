<%@ page contentType="text/html; charset=UTF-8" %>
<%
    pageContext.setAttribute("num", 10);
//    반환값 Object라서 형변환 해줘야 하지만 el식 쓰면 알아서 형변환 해줌
%>
${100 + 200}<br>
${num == 10 ? "숫자 10 입니다":"숫자 10이 아닙니다"}