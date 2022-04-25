<%@ page import="java.util.Locale" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%--
  User: duckhaidev
  Date: 2/14/2022
  Time: 10:52 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-default navbar-cls-top " role="navigation" style="margin-bottom: 0">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="${pageContext.request.contextPath}">Đông Phong</a>
    </div>
    <div style="color: white;
padding: 15px 50px 5px 50px;
float: right;
font-size: 16px;"> Truy cập lần cuối : <%
        Locale vie = new Locale("vi", "VN");
        SimpleDateFormat formatDow = new SimpleDateFormat("EEEE", vie);
        SimpleDateFormat formatDam = new SimpleDateFormat("dd MMMM", vie);
        SimpleDateFormat formatYear = new SimpleDateFormat("yyyy");
        Date date = new Date();
        out.print(formatDow.format(date) + ", ngày " + formatDam.format(date) + " năm " + formatYear.format(date));
    %> &nbsp; <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger square-btn-adjust">Đăng Xuất</a>
    </div>
</nav>