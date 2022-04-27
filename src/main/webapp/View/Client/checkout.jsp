<%--
  User: duckhaidev
  Date: 3/31/2022
  Time: 4:16 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Thanh Toán - Đồ gỗ Cao cấp Đông Phong</title>
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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/bs-stepper.min.css">
    <!--===============================================================================================-->
    <script src="${pageContext.request.contextPath}/assets/vendor/jquery/jquery-3.2.1.min.js"></script>
    <!-- TABLE STYLES-->
    <link href="${pageContext.request.contextPath}/assets/js/dataTables-bs4/dataTables.bootstrap4.min.css"
          rel="stylesheet"/>
</head>
<body class="animsition">

<!-- Header -->
<jsp:include page="header-v4.jsp"/>

<!-- Cart -->
<jsp:include page="cart.jsp"/>

<!-- WishList -->
<jsp:include page="wishList.jsp"/>

<!-- breadcrumb -->
<div class="container">
    <div class="bread-crumb flex-w p-l-25 p-r-15 p-t-30 p-lr-0-lg">
        <a href="${pageContext.request.contextPath}/welcome" class="stext-109 cl8 hov-cl1 trans-04 font-size-15">
            Trang chủ
            <i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i>
        </a>

        <a href="${pageContext.request.contextPath}/cart" class="stext-109 cl8 hov-cl1 trans-04 font-size-15">
            Giỏ hàng
            <i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i>
        </a>

        <span class="stext-109 cl4 font-size-15">
            Thanh toán
        </span>
    </div>
</div>

<!-- Payment -->
<div class="bg0 p-t-20 p-b-85">
    <div class="container">
        <div class="row">
            <div class="col-md-12 bs-stepper">
                <div class="bs-stepper-header" role="tablist">
                    <!--Your steps here-->
                    <div class="step" data-target="#logins-part">
                        <button type="button" class="step-trigger" role="tab" aria-controls="logins-part"
                                id="logins-part-trigger">
                            <span class="bs-stepper-circle">1</span>
                            <span class="bs-stepper-label">Đăng nhập</span>
                        </button>
                    </div>
                    <div class="line"></div>
                    <div class="step" data-target="#recaddress-part">
                        <button type="button" class="step-trigger" role="tab" aria-controls="recaddress-part"
                                id="recaddress-part-trigger">
                            <span class="bs-stepper-circle">2</span>
                            <span class="bs-stepper-label">Địa chỉ giao hàng</span>
                        </button>
                    </div>
                    <div class="line"></div>
                    <div class="step" data-target="#payment-part">
                        <button type="button" class="step-trigger" role="tab" aria-controls="recaddress-part"
                                id="payment-part-trigger">
                            <span class="bs-stepper-circle">3</span>
                            <span class="bs-stepper-label">Thanh toán</span>
                        </button>
                    </div>
                </div>
                <div class="bs-stepper-content">
                    <!--Your steps content here-->
                    <div id="logins-part" class="content" role="tabpanel" aria-labelledby="logins-part-trigger">
                        <div class="row d-flex justify-content-center m-t-20">
                            <div class="col-md-6 d-flex flex-column align-items-center">
                                <span class="text-center p-b-8">Xin chào ${sessionScope.account.lastName} ${sessionScope.account.firstName}, bạn đã đăng nhập, bạn muốn?</span>
                                <a href="${pageContext.request.contextPath}/logout"
                                   class="btn btn-primary ct-button2 text-uppercase m-b-8" style="width: 250px;">
                                    Đăng nhập tài khoản khác
                                </a>
                                <span class="text-center p-b-8">hoặc</span>
                                <button id="btn-continue" class="btn btn-primary ct-button3 text-uppercase"
                                        style="width: 250px;">
                                    Tiếp tục
                                </button>
                            </div>
                        </div>
                    </div>
                    <form action="<c:url value="/checkout"/>" method="post" id="recaddress-part" class="content"
                          role="tabpanel" aria-labelledby="recaddress-part-trigger">
                        <div class="mb-3 text-center text-uppercase">Thông tin giao hàng</div>
                        <div class="row d-flex justify-content-center m-t-20 p-3 ct-border-1">
                            <div class="col-md-6 mb-3"><label for="fullName" class="labels">Họ và tên</label><input
                                    id="fullName" name="fullName" type="text" class="form-control"
                                    value="${sessionScope.account.lastName} ${sessionScope.account.firstName}"></div>
                            <div class="col-md-6 mb-3"><label for="phone" class="labels">Số điện thoại</label><input
                                    id="phone" name="phone" type="text" class="form-control"
                                    value="${sessionScope.account.phone}"></div>
                            <div class="col-md-6 mb-3">
                                <label for="province" class="labels">Tỉnh/Thành phố</label>
                                <input id="selectedProvince" type="hidden" value="${sessionScope.selectedProvince}">
                                <input type="hidden" name="selectedProvince">
                                <select id="province" name="province" class="w-full form-control" disabled>
                                    <option value="0" selected hidden disabled>Tỉnh/Thành phố</option>
                                </select>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="district" class="labels">Quận/Huyện</label>
                                <input id="selectedDistrict" type="hidden" value="${sessionScope.selectedDistrict}">
                                <input type="hidden" name="selectedDistrict">
                                <select id="district" name="district" class="w-full form-control" disabled>
                                    <option value="0" selected hidden disabled>Quận/Huyện</option>
                                </select>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="ward" class="labels">Phường/Xã</label>
                                <input id="selectedWard" type="hidden" value="${sessionScope.selectedWard}">
                                <input type="hidden" name="selectedWard">
                                <select id="ward" name="ward" class="w-full form-control" disabled>
                                    <option value="0" selected hidden disabled>Phường/Xã</option>
                                </select>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="recaddress" class="labels">Số nhà</label>
                                <input type="hidden" name="recaddress">
                                <input id="recaddress" class="w-full form-control" type="text"
                                       value="${sessionScope.recaddress}" placeholder="Số nhà" disabled>
                            </div>
                        </div>
                        <div class="row m-t-8 m-b-8">
                            <div class="col p-0">Hình thức thanh toán:</div>
                        </div>
                        <div class="row d-flex justify-content-center p-3 ct-border-1">
                            <jsp:useBean id="payments" scope="request" type="java.util.List"/>
                            <c:forEach items="${payments}" var="payment">
                                <div class="col-md-12 d-flex align-items-center">
                                    <input type="radio" id="${payment.paymentId}" name="paymentMethod" class="m-r-12"
                                           value="${payment.paymentId}"
                                           <c:if test="${!payment.paymentStatus}">disabled</c:if>
                                           <c:if test="${payment.paymentId == 4}">checked</c:if>>
                                    <label for="${payment.paymentId}" class="m-0"
                                           <c:if test="${!payment.paymentStatus}">disabled</c:if>>${payment.paymentMethod}
                                        <c:if test="${!payment.paymentStatus}"> (Đang bảo trì)</c:if></label>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="row d-flex">
                            <button id="btn-previous" type="button"
                                    class="col-md-2 btn btn-primary ct-button2 mt-3 text-uppercase">
                                Bước trước
                            </button>
                            <div class="col-md-3"></div>
                            <button id="btn-order" type="submit"
                                    class="col-md-2 btn btn-primary ct-button3 mt-3 text-uppercase">
                                Đặt hàng
                            </button>
                        </div>
                    </form>
                    <div id="payment-part" class="content" role="tabpanel" aria-labelledby="payment-part-trigger">
                        <div class="text-center p-t-20 p-b-20">
                            ${sessionScope.message}
                        </div>
                        <jsp:include page="review-order.jsp"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Footer -->
<jsp:include page="footer.jsp"/>

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
<script src="${pageContext.request.contextPath}/assets/js/axios.min.js"></script>
<!--===============================================================================================-->
<script>
    $(function () {
        const provinces = document.getElementById("province");
        const districts = document.getElementById("district");
        const wards = document.getElementById("ward");

        const Parameter = {
            url: '${pageContext.request.contextPath}/assets/js/vietnam.json',
            method: 'GET',
            responseType: 'application/json'
        };

        const promise = axios(Parameter);
        promise.then(function (result) {
            renderProvince(result.data);
        });

        function renderProvince(data) {
            for (const x of data) {
                provinces.options[provinces.options.length] = new Option(x.Name, x.Id);
            }

            provinces.onchange = function () {
                districts.length = 1;
                wards.length = 1;
                if (this.value !== "") {
                    const result = data.filter(n => n.Id === this.value);

                    for (const k of result[0].Districts) {
                        districts.options[districts.options.length] = new Option(k.Name, k.Id);
                    }
                }
            };

            districts.onchange = function () {
                wards.length = 1;
                const dataProvince = data.filter((n) => n.Id === provinces.value);
                if (this.value !== "") {
                    const dataWards = dataProvince[0].Districts.filter(n => n.Id === this.value)[0].Wards;

                    for (const w of dataWards) {
                        wards.options[wards.options.length] = new Option(w.Name, w.Id);
                    }
                }
            };

            const selectedProvince = $('#selectedProvince').prop('value');
            const selectedDistrict = $('#selectedDistrict').prop('value');
            const selectedWard = $('#selectedWard').prop('value');
            if (selectedProvince !== '') {
                $('#province').val(selectedProvince).trigger('change');
            }
            if (selectedDistrict !== '') {
                $('#district').val(selectedDistrict).trigger('change');
            }
            if (selectedWard !== '') {
                $('#ward').val(selectedWard);
            }
        }
    });
</script>
<!--===============================================================================================-->
<script src="${pageContext.request.contextPath}/assets/js/bs-stepper.min.js"></script>
<script>
    $(function () {
        const stepper = new Stepper($('.bs-stepper')[0]);
        stepper.to(2);
        $('#btn-continue').on('click', function () {
            stepper.to(2);
        });
        $('#btn-previous').on('click', function () {
            stepper.to(1);
        });
        $('#btn-order').on('click', function () {
            $('input[name="selectedProvince"]').val($('#province option:selected').text());
            $('input[name="selectedDistrict"]').val($('#district option:selected').text());
            $('input[name="selectedWard"]').val($('#ward option:selected').text());
            $('input[name="recaddress"]').val($('#recaddress').prop('value'));
        });
        if (<% out.print(session.getAttribute("message") != null); %>) {
            stepper.to(3);
            <% session.removeAttribute("message"); %>
        }
    });
</script>
<!-- DATA TABLE SCRIPTS -->
<script src="${pageContext.request.contextPath}/assets/js/dataTables-bs4/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/dataTables-bs4/dataTables.bootstrap4.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/dataTables-bs4/dataTables.responsive.min.js"></script>
<script>
    $(document).ready(function () {
        $('#dataTable').dataTable({
            "pagingType": "full_numbers"
        });
    });
</script>

</body>
</html>
