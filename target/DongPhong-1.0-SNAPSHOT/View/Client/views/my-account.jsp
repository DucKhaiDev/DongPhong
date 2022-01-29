<%--
  User: duckhaidev
  Date: 1/25/2022
  Time: 2:50 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/View/Client/assets" var="url" />

<%@ page import="Entity.User" %>

<!DOCTYPE html>
<html>
<head>
    <title>Thông tin cá nhân</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="${url}/images/icons/favicon.png"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${url}/vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${url}/fonts/fontawesome-free-5.15.4-web/css/all.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${url}/fonts/iconic/css/material-design-iconic-font.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${url}/fonts/linearicons-v1.0.0/icon-font.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${url}/vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${url}/vendor/css-hamburgers/hamburgers.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${url}/vendor/animsition/css/animsition.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${url}/vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${url}/vendor/perfect-scrollbar/perfect-scrollbar.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${url}/css/util.css">
    <link rel="stylesheet" type="text/css" href="${url}/css/main.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${url}/css/my-account-stylesheet.css">
</head>
<body class="animsition">

<!-- Header -->
<jsp:include page="header-v4.jsp"></jsp:include>

<!-- Cart -->
<jsp:include page="cart.jsp"></jsp:include>

<!-- Content page -->
<div class="container rounded bg-white mt-5 mb-5">
    <c:url value="/member/my-account" var="myaccount"></c:url>
    <form action="${myaccount}" method="post" enctype="multipart/form-data">
        <div class="row">
            <div class="col-md-1"></div>
            <div class="col-md-3 border-right">
                <div class="d-flex flex-column align-items-center text-center p-3 py-5">
                    <c:url value="/image?fname=${sessionScope.account.AVATAR}" var="avatarUrl"></c:url>
                    <img class="rounded-circle mt-5 mb-1" width="150px" height="150px" src="${avatarUrl}" alt="Ảnh đại diện">
                    <input class="text-center mb-3" type="file" name="update_avatar" />
                    <span class="font-weight-bold mb-2">${sessionScope.account.LASTNAME} ${sessionScope.account.FIRSTNAME}</span>
                    <span class="text-black-50">ID:&nbsp;${sessionScope.account.USER_ID}</span>
                </div>
            </div>
            <div class="col-md-7 border-right">
                <div class="p-3 py-5">
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <h4 class="text-right">Thông tin cá nhân</h4>
                    </div>
                    <div class="row mt-2">
                        <div class="col-md-6"><label class="labels">Họ</label><input type="text" class="form-control" name="update_lastname" placeholder="${sessionScope.account.LASTNAME}"></div>
                        <div class="col-md-6"><label class="labels">Tên</label><input type="text" class="form-control" name="update_firstname" placeholder="${sessionScope.account.FIRSTNAME}"></div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-md-12 mb-3"><label class="labels">Tên đăng nhập</label><input type="text" class="form-control" placeholder="${sessionScope.account.USERNAME}" readonly></div>
                        <div class="col-md-12 mb-3"><label class="labels">Email</label><input type="text" class="form-control" name="update_email" placeholder="${sessionScope.account.EMAIL}"></div>
                        <div class="col-md-12 mb-3"><label class="labels">Địa chỉ</label><input type="text" class="form-control" name="update_address" placeholder="${sessionScope.account.ADDRESS}"></div>
                        <div class="col-md-12"><label class="labels">Số điện thoại</label><input type="text" class="form-control" name="update_phone" placeholder="${sessionScope.account.PHONE}"></div>
                    </div>
                </div>
            </div>
            <div class="col-md-1"></div>
        </div>
        <div class="row">
            <div class="col-md-1"></div>
            <div class="col-md-3 border-right pb-5">
                <div class="mt-5 text-center"><button class="btn btn-primary profile-button" type="button" onclick="window.location.href='${pageContext.request.contextPath}/change-password'"><i class="fa fa-redo-alt"></i>&nbsp;Mật khẩu</button></div>
            </div>
            <div class="col-md-7 border-right pb-5 row">
                <div class="mt-5 text-center col-md-6"><button class="btn btn-primary profile-button float-r" type="submit"><i class="fa fa-user-edit"></i>&nbsp;Cập nhật</button></div>
                <div class="mt-5 text-center col-md-6"><button class="btn btn-primary profile-button float-l" type="reset"><i class="fa fa-backspace"></i>&nbsp;Hủy bỏ</button></div>
            </div>
            <div class="col-md-1"></div>
        </div>
    </form>
</div>

<!-- Footer -->
<jsp:include page="footer.jsp"></jsp:include>

<!--===============================================================================================-->
<script src="${url}/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script src="${url}/vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
<script src="${url}/vendor/bootstrap/js/popper.js"></script>
<script src="${url}/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="${url}/vendor/select2/select2.min.js"></script>
<script>
    $(".js-select2").each(function(){
        $(this).select2({
            minimumResultsForSearch: 20,
            dropdownParent: $(this).next('.dropDownSelect2')
        });
    })
</script>
<!--===============================================================================================-->
<script src="${url}/vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
<!--===============================================================================================-->
<script src="${url}/vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
<script>
    $('.js-pscroll').each(function(){
        $(this).css('position','relative');
        $(this).css('overflow','hidden');
        var ps = new PerfectScrollbar(this, {
            wheelSpeed: 1,
            scrollingThreshold: 1000,
            wheelPropagation: false,
        });

        $(window).on('resize', function(){
            ps.update();
        })
    });
</script>
<!--===============================================================================================-->
<script src="${url}/js/main.js"></script>

</body>
</html>
