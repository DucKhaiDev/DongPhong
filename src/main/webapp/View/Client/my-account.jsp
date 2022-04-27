<%--
  User: duckhaidev
  Date: 1/25/2022
  Time: 2:50 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Thông Tin Cá Nhân - Đồ gỗ Cao cấp Đông Phong</title>
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
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/css/my-account-stylesheet.css">
</head>
<body class="animsition">

<!-- Header -->
<jsp:include page="header-v4.jsp"/>

<!-- Cart -->
<jsp:include page="cart.jsp"/>

<!-- WishList -->
<jsp:include page="wishList.jsp"/>

<!-- Content page -->
<div class="container rounded bg-white mt-5 mb-5">
    <c:url value="/member/my-account" var="myAccount"/>
    <form action="${myAccount}" method="post" enctype="multipart/form-data">
        <div class="row justify-content-center">
            <div class="col-md-3 border-right">
                <div class="d-flex flex-column align-items-center text-center p-3 py-5">
                    <c:url value="/images/avatar?fname=${sessionScope.account.avatar}" var="avatarUrl"/>
                    <img class="avatar user-img-empty rounded-circle mt-5 mb-1" width="150px" height="150px"
                         style="object-fit: cover;"
                         <c:if test="${not empty sessionScope.account.avatar}">src="${avatarUrl}"</c:if> alt="">
                    <input class="text-center mb-3 file-upload" type="file" name="update_avatar"/>
                    <span class="font-weight-bold mb-2">${sessionScope.account.lastName} ${sessionScope.account.firstName}</span>
                    <span class="text-black-50">ID:&nbsp;${sessionScope.account.userId}</span>
                </div>
            </div>
            <div class="col-md-7 border-right">
                <div class="p-3 py-5">
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <h4 class="text-right">Thông tin cá nhân</h4>
                    </div>
                    <div class="row mt-2 mb-3">
                        <div class="col-md-6"><label for="lastName" class="labels">Họ</label><input id="lastName"
                                                                                                    type="text"
                                                                                                    class="form-control"
                                                                                                    name="lastName"
                                                                                                    maxlength="255"
                                                                                                    placeholder="${sessionScope.account.lastName}">
                        </div>
                        <div class="col-md-6"><label for="firstName" class="labels">Tên</label><input id="firstName"
                                                                                                      type="text"
                                                                                                      class="form-control"
                                                                                                      name="firstName"
                                                                                                      maxlength="255"
                                                                                                      placeholder="${sessionScope.account.firstName}">
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-md-12 mb-3"><label for="username" class="labels">Tên đăng nhập</label><input
                                id="username" type="text" class="form-control"
                                placeholder="${sessionScope.account.username}" readonly></div>
                        <div class="col-md-12 mb-3"><label for="email" class="labels">Email</label><input id="email"
                                                                                                          type="text"
                                                                                                          class="form-control"
                                                                                                          name="email"
                                                                                                          maxlength="255"
                                                                                                          placeholder="${sessionScope.account.email}">
                        </div>
                        <div class="col-md-12 mb-3"><label for="address" class="labels">Địa chỉ</label><input
                                id="address" type="text" class="form-control" name="address" maxlength="2000"
                                placeholder="${sessionScope.account.address}"></div>
                        <div class="col-md-12"><label for="phone" class="labels">Số điện thoại</label><input id="phone"
                                                                                                             type="text"
                                                                                                             class="form-control"
                                                                                                             name="phone"
                                                                                                             maxlength="12"
                                                                                                             placeholder="${sessionScope.account.phone}">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row justify-content-center">
            <div class="col-md-3 border-right pb-5">
                <div class="mt-5 text-center">
                    <button class="btn btn-primary profile-button" type="button"
                            onclick="window.location.href='${pageContext.request.contextPath}/change-password'"><i
                            class="fa fa-redo-alt"></i>&nbsp;Mật khẩu
                    </button>
                </div>
            </div>
            <div class="col-md-7 border-right pb-5 row">
                <div class="mt-5 text-center col-md-6">
                    <button class="btn btn-primary profile-button float-r" type="submit"><i class="fa fa-check"></i>&nbsp;Đồng
                        ý
                    </button>
                </div>
                <div class="mt-5 text-center col-md-6">
                    <button class="btn btn-primary profile-button float-l" type="reset"><i class="fa fa-undo-alt"></i>&nbsp;Nhập
                        lại
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

</body>
</html>
