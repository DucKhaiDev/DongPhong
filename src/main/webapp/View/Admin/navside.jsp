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
                <img class="user-image user-img-empty img-responsive"
                     <c:if test="${not empty sessionScope.account.avatar}">src="${avatarUrl}"</c:if> alt="Ảnh đại diện">
            </li>
            <li>
                <a class="active-menu text-center"
                   href="${pageContext.request.contextPath}/admin/user/edit?id=${sessionScope.account.userId}">${sessionScope.displayName}</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/admin">
                    <i class="fa fa-home-alt fa-3x w-25 text-center"></i>
                    Trang Chủ
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/admin/user">
                    <i class="fa fa-users fa-3x w-25 text-center"></i>
                    QL Tài khoản
                </a>
            </li>
            <li>
                <a href="#"><i class="fa fa-boxes fa-3x w-25 text-center"></i> QL Sản phẩm<span
                        class="fa arrow fa-2x"></span></a>
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
            <li>
                <a href="${pageContext.request.contextPath}/admin/order">
                    <i class="fa fa-file-alt fa-3x w-25 text-center"></i>
                    QL Đơn hàng
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/admin/voucher">
                    <i class="fa fa-gift-card fa-3x w-25 text-center"></i>
                    QL Mã giảm giá
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/admin/review">
                    <i class="fa fa-comment-alt-lines fa-3x w-25 text-center"></i>
                    QL Bình luận
                </a>
            </li>
            <li>
                <a href="#"><i class="fa fa-file-chart-line fa-3x w-25 text-center"></i>Báo cáo & Thống kê<span
                        class="fa arrow fa-2x"></span></a>
                <ul class="nav nav-second-level">
                    <li>
                        <a href="${pageContext.request.contextPath}/admin/report">Báo cáo</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/admin/statistic">Thống kê</a>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
</nav>