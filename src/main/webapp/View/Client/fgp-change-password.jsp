<%--
  User: duckhaidev
  Date: 4/28/2022
  Time: 3:24 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Thay Đổi Mật Khẩu - Đồ gỗ Cao cấp Đông Phong</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/images/icons/icon-logo.png"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/fonts/fontawesome-pro-5.15.4-web/css/all.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/fonts/iconic/css/material-design-iconic-font.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/fonts/linearicons-v1.0.0/icon-font.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/vendor/css-hamburgers/hamburgers.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/vendor/animsition/css/animsition.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/vendor/perfect-scrollbar/perfect-scrollbar.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/util.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/main.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/custom.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/css/my-account-stylesheet.css">
</head>
<body class="animation">

<!-- Header -->
<jsp:include page="header-v4.jsp"/>

<!-- Cart -->
<jsp:include page="cart.jsp"/>

<!-- WishList -->
<jsp:include page="wishList.jsp"/>

<!-- Content page -->
<div class="container rounded bg-white mt-5 mb-5" style="width: 480px">
    <c:url value="/change-password" var="changePassword"/>
    <div id="notfound" class="p-3 py-5" style="display: none;">
        <div class="d-flex flex-column justify-content-between text-center">
            <h4>${requestScope.notfound}</h4>
        </div>
    </div>
    <form id="fgp-change-password" action="<c:url value="/change-password/access"/>" method="post">
        <div class="p-3 py-5">
            <div class="d-flex flex-column justify-content-between mb-3">
                <h4>Thay đổi mật khẩu</h4>
                <p class="text-muted" style="color: #FF0000 !important;">${requestScope.cpwMsg}</p>
            </div>
            <div class="row mt-3 d-flex justify-content-center">
                <div class="col-md-10 mb-3"><label class="labels" for="newPassword">Mật khẩu mới</label><input
                        id="newPassword" type="password" class="form-control" name="newPassword" maxlength="32"></div>
                <div class="col-md-10 mb-3"><label class="labels" for="repeatPassword">Nhập lại mật khẩu
                    mới</label><input id="repeatPassword" type="password" class="form-control" name="repeatPassword"
                                      maxlength="32"></div>
                <p class="text-muted" style="color: #FF0000 !important;">${requestScope.cpw_notmatch}</p>
                <div class="col-md-12 mt-4 text-center col-md-6">
                    <button class="btn btn-primary profile-button" type="submit"><i class="fa fa-user-edit"></i>&nbsp;Cập
                        nhật
                    </button>
                </div>
            </div>
        </div>
    </form>
</div>

<!-- Footer -->
<jsp:include page="footer.jsp"/>

<!--===============================================================================================-->
<script src="${pageContext.request.contextPath}/assets/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script src="${pageContext.request.contextPath}/assets/vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
<script src="${pageContext.request.contextPath}/assets/vendor/bootstrap/js/popper.js"></script>
<script src="${pageContext.request.contextPath}/assets/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="${pageContext.request.contextPath}/assets/vendor/select2/select2.min.js"></script>
<script>
    $(".js-select2").each(function () {
        $(this).select2({
            minimumResultsForSearch: 20,
            dropdownParent: $(this).next('.dropDownSelect2')
        });
    })
</script>
<!--===============================================================================================-->
<script src="${pageContext.request.contextPath}/assets/vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
<!--===============================================================================================-->
<script src="${pageContext.request.contextPath}/assets/vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
<script>
    $('.js-pscroll').each(function () {
        $(this).css('position', 'relative');
        $(this).css('overflow', 'hidden');
        const ps = new PerfectScrollbar(this, {
            wheelSpeed: 1,
            scrollingThreshold: 1000,
            wheelPropagation: false,
        });

        $(window).on('resize', function () {
            ps.update();
        })
    });
</script>
<!--===============================================================================================-->
<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
<!--===============================================================================================-->
<script src="${pageContext.request.contextPath}/assets/js/custom.js"></script>
<!--===============================================================================================-->
<script>
    $(function () {
        if ('${requestScope.notfound}' !== '') {
            $('#notfound').show();
            $('#fgp-change-password').hide();
        }
    })
</script>
<!--===============================================================================================-->
<script src="${pageContext.request.contextPath}/assets/vendor/sweetalert/sweetalert.min.js"></script>
<script>
    $(function () {
        const message = '${requestScope.changeSuccess}';
        if (message !== '') {
            swal({
                text: '${requestScope.changeSuccess}',
                icon: 'success'
            }).then(function () {
                location.href = '${pageContext.request.contextPath}/login';
            });
        }
    });
</script>

</body>
</html>
