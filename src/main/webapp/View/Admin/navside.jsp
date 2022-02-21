<%@ page import="Entity.User" %><%--
  User: duckhaidev
  Date: 2/14/2022
  Time: 10:53 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/assets" var="url" />
<nav class="navbar-default navbar-side" role="navigation">
    <div class="sidebar-collapse">
        <ul class="nav" id="main-menu">
            <li class="text-center">
                <c:url value="/images/avatar?fname=${sessionScope.account.AVATAR}" var="avatarUrl"/>
                <img class="user-image user-img-empty img-responsive" <c:if test="${not empty sessionScope.account.AVATAR}">src="${avatarUrl}"</c:if>>
            </li>
            <li>
                <a class="active-menu text-center" href="${pageContext.request.contextPath}/member/my-account">
                    <%
                        User account = (User) session.getAttribute("account");
                        String displayName = account.getUSERNAME();
                        String firstName = account.getFIRSTNAME();
                        String lastName = account.getLASTNAME();

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
                        <a href="#">Danh mục Loại sản phẩm</a>
                    </li>
                    <li>
                        <a href="#">Danh mục Thương hiệu</a>
                    </li>
                </ul>
            </li>
            <li>
                <a  href="ui.html"><i class="fa fa-desktop fa-3x"></i> UI Elements</a>
            </li>
            <li>
                <a  href="tab-panel.html"><i class="fa fa-qrcode fa-3x"></i> Tabs & Panels</a>
            </li>
            <li  >
                <a   href="chart.html"><i class="fa fa-bar-chart-o fa-3x"></i> Morris Charts</a>
            </li>
            <li  >
                <a  href="table.html"><i class="fa fa-table fa-3x"></i> Table Examples</a>
            </li>
            <li  >
                <a  href="form.html"><i class="fa fa-edit fa-3x"></i> Forms </a>
            </li>


            <li>
                <a href="#"><i class="fa fa-sitemap fa-3x"></i> Multi-Level Dropdown<span class="fa arrow"></span></a>
                <ul class="nav nav-second-level">
                    <li>
                        <a href="#">Second Level Link</a>
                    </li>
                    <li>
                        <a href="#">Second Level Link</a>
                    </li>
                    <li>
                        <a href="#">Second Level Link<span class="fa arrow"></span></a>
                        <ul class="nav nav-third-level">
                            <li>
                                <a href="#">Third Level Link</a>
                            </li>
                            <li>
                                <a href="#">Third Level Link</a>
                            </li>
                            <li>
                                <a href="#">Third Level Link</a>
                            </li>

                        </ul>

                    </li>
                </ul>
            </li>
            <li  >
                <a  href="blank.html"><i class="fa fa-square-o fa-3x"></i> Blank Page</a>
            </li>
        </ul>

    </div>

</nav>