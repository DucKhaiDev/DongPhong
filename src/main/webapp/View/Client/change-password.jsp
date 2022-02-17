<%--
  User: duckhaidev
  Date: 1/29/2022
  Time: 9:44 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/assets" var="url" />
<!DOCTYPE html>
<html>
<head>
    <title>Thay đổi mật khẩu</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="${url}/images/icons/favicon.png"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${url}/vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${url}/fonts/fontawesome-pro-5.15.4-web/css/all.min.css">
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
<body class="animation">

<!-- Header -->
<jsp:include page="header-v4.jsp"></jsp:include>

<!-- Cart -->
<jsp:include page="cart.jsp"></jsp:include>

<!-- Content page -->
<div class="container rounded bg-white mt-5 mb-5" style="width: 480px">
    <c:url value="/change-password" var="changePassword"></c:url>
    <form action="${changePassword}" method="post">
        <div class="p-3 py-5">
            <div class="d-flex flex-column justify-content-between mb-3">
                <h4>Thay đổi mật khẩu</h4>
                <p class="text-muted" style="color: #FF0000 !important;">${requestScope.cpwMsg}</p>
            </div>
            <div class="row mt-3 d-flex justify-content-center">
                <div class="col-md-10 mb-3"><label class="labels" for="old_password">Mật khẩu cũ</label><input id="old_password" type="password" class="form-control" name="old_password" maxlength="32"></div>
                <p class="text-muted" style="color: #FF0000 !important;">${requestScope.cpw_incorrect}</p>
                <div class="col-md-10 mb-3"><label class="labels" for="new_password">Mật khẩu mới</label><input id="new_password" type="password" class="form-control" name="new_password" maxlength="32"></div>
                <div class="col-md-10 mb-3"><label class="labels" for="repeat_new_password">Nhập lại mật khẩu mới</label><input id="repeat_new_password" type="password" class="form-control" name="repeat_new_password" maxlength="32"></div>
                <p class="text-muted" style="color: #FF0000 !important;">${requestScope.cpw_notmatch}</p>
                <div class="col-md-12 mt-4 text-center col-md-6"><button class="btn btn-primary profile-button" type="submit"><i class="fa fa-user-edit"></i>&nbsp;Cập nhật</button></div>
            </div>
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
