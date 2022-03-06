<%@ page import="Entity.User" %>
<%--
  User: duckhaidev
  Date: 2/14/2022
  Time: 10:53 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar-default navbar-side" role="navigation">
    <div class="sidebar-collapse">
        <ul class="nav" id="main-menu">
            <li class="text-center">
                <c:url value="/images/avatar?fname=${sessionScope.account.avatar}" var="avatarUrl"/>
                <img class="user-image user-img-empty img-responsive" <c:if test="${not empty sessionScope.account.avatar}">src="${avatarUrl}"</c:if> alt="Ảnh đại diện">
            </li>
            <li>
                <a class="active-menu text-center" href="${pageContext.request.contextPath}/admin/user/edit?id=${sessionScope.account.userId}">
                    <%
                        User account = (User) session.getAttribute("account");
                        String displayName = account.getUsername();
                        String firstName = account.getFirstName();
                        String lastName = account.getLastName();

                        if (lastName != null && !lastName.trim().equals("")) {
                            displayName = lastName;

                            if (firstName != null && !firstName.trim().equals("")) {
                                displayName += " " + firstName;
                            }
                        }
                        out.print(displayName);
                    %>
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/admin/user">
                    <i class="fa fa-users fa-3x"></i>
                    QL Tài Khoản
                </a>
            </li>
            <li>
                <a href="#"><i class="fa fa-boxes fa-3x"></i> QL Sản phẩm<span class="fa arrow fa-2x"></span></a>
                <ul class="nav nav-second-level">
                    <li>
                        <a href="${pageContext.request.contextPath}/admin/product">Danh mục Sản phẩm</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/admin/category">Danh mục Loại sản phẩm</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/admin/brand">Danh mục Thương hiệu</a>
                    </li>
                </ul>
            </li>
        </ul>

    </div>

</nav>