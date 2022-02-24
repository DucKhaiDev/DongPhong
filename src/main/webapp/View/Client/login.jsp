<%--
  User: duckhaidev
  Date: 1/23/2022
  Time: 10:57 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/assets" var="url" />
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="${url}/images/icons/icon-logo.png"/>
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
    <link rel="stylesheet" type="text/css" href="${url}/css/login-stylesheet.css">
</head>
<body class="animsition">

<!-- Header -->
<jsp:include page="header-v4.jsp"/>

<!-- Cart -->
<jsp:include page="cart.jsp"/>

<!-- Content page -->
<section class="bg0 p-t-62 p-b-60">
    <div id="main-wrapper" class="container">
        <div class="row justify-content-center">
            <div class="col-xl-10">
                <div class="card border-0">
                    <div class="card-body p-0">
                        <div class="row no-gutters">
                            <div class="col-lg-6">
                                <div class="p-5">
                                    <div class="mb-5">
                                        <h3 class="h4 font-weight-bold text-theme">ĐĂNG NHẬP</h3>
                                    </div>
                                    <h6 class="h5 mb-0">Chào mừng bạn quay trở lại!</h6>
                                    <p class="text-muted mt-2 mb-5" style="color: #FF0000 !important;">${requestScope.loginMsg}</p>
                                    <form id="form-login" action="login" method="post">
                                        <div class="form-group">
                                            <label for="username_or_email">Tên đăng nhập hoặc Email</label>
                                            <input type="text" class="form-control" id="username_or_email" name="username_or_email" required="required">
                                        </div>
                                        <div class="form-group">
                                            <label for="password">Mật khẩu</label>
                                            <input type="password" class="form-control" id="password" name="password" required="required">
                                        </div>
                                        <div class="form-group row mb-5">
                                            <div class="col-6">
                                                <label class="checkbox">
                                                    <input class="d-inline-block mr-2" type="checkbox" id="rememberMe" name="rememberMe">
                                                    <label class="d-inline-block" for="rememberMe">Nhớ mật khẩu</label>
                                                </label>
                                            </div>
                                            <div class="col-6">
                                                <a href="#" class="forgot-link float-right text-primary">Quên mật khẩu?</a>
                                            </div>
                                        </div>
                                        <button type="submit" class="btn btn-theme">Đăng nhập</button>
                                    </form>
                                </div>
                            </div>

                            <div class="col-lg-6 d-none d-lg-inline-block">
                                <div class="account-block rounded-right"></div>
                            </div>
                        </div>

                    </div>
                    <!-- end card-body -->
                </div>
                <!-- end card -->

                <p class="text-muted text-center mt-4 mb-0">Chưa có tài khoản? <a href="${pageContext.request.contextPath}/register" class="text-primary ml-1">Đăng ký ngay</a></p>

                <!-- end row -->

            </div>
            <!-- end col -->
        </div>
        <!-- Row -->
    </div>
</section>

<!-- Footer -->
<jsp:include page="footer.jsp"/>

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
