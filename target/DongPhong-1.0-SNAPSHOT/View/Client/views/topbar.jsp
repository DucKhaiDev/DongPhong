<%--
  User: is2vi
  Date: 1/23/2022
  Time: 8:50 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/View/Client/assets" var="url" />
<div class="top-bar">
    <div class="content-topbar flex-sb-m h-full container">
        <div class="left-top-bar flex-w h-full">
                <a href="#" class="flex-c-m trans-04 p-lr-25">Giới Thiệu</a>
                <a href="#" class="flex-c-m trans-04 p-lr-25">Hướng Dẫn</a>
                <a href="#" class="flex-c-m trans-04 p-lr-25">Chính Sách Bảo Hành</a>
                <a href="#" class="flex-c-m trans-04 p-lr-25">Tin Tức</a>
                <a href="#" class="flex-c-m trans-04 p-lr-25">Liên Hệ</a>
        </div>

        <c:choose>
            <c:when test="${sessionScope.account == null}">
                <div class="right-top-bar flex-w h-full">
                    <a href="${pageContext.request.contextPath }/login" class="flex-c-m trans-04 p-lr-25" style="border-left: none">
                <span>
                    Đăng Nhập
                    <i class="fa fa-sign-in-alt"></i>
                </span>
                    </a>

                    <a href="${pageContext.request.contextPath }/register" class="flex-c-m trans-04 p-lr-25" style="border-right: none">
                <span>
                    Đăng Ký
                    <i class="fa fa-user-plus"></i>
                </span>
                    </a>
                </div>
            </c:when>
            <c:otherwise>
                <div class="right-top-bar flex-w h-full">
                    <a href="${pageContext.request.contextPath }/member/my-account" class="flex-c-m trans-04 p-lr-25" style="border-left: none">
                <span>
                    ${sessionScope.account.USERNAME}
                    <i class="fa fa-user"></i>
                </span>
                    </a>

                    <a href="${pageContext.request.contextPath }/logout" class="flex-c-m trans-04 p-lr-25" style="border-right: none">
                <span>
                    Đăng Xuất
                    <i class="fa fa-sign-out-alt"></i>
                </span>
                    </a>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>
