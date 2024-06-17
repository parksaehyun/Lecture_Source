<%@ tag import="java.util.Map" %>
<%@ tag body-content="empty" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ tag dynamic-attributes="attrs" %>
<%
  Map<String, String> attrs = (Map<String, String>) jspContext.getAttribute("attrs"); //  반환값이 object라서 형변환 해줌
    String _size = attrs.getOrDefault("size", "0"); // 없으면 "0"으로 대체
    int size = Integer.parseInt(_size);
%>

<div style="color: ${attrs.color};">
    <%
        for (int i = 0; i < size; i++) {
            out.write("★");
        }
    %>
</div>

<%--color : ${attrs.color}<br>--%>
<%--size : ${attrs.size}<br>--%>