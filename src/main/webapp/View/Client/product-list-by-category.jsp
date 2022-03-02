<%@ page import="Services.deploy.ProductService" %>
<%@ page import="Entity.Category" %>
<%@ page import="Entity.Brand" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="Entity.Product" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.math.RoundingMode" %>
<%@ page import="Services.deploy.ProImageService" %>
<%@ page import="java.util.List" %>
<%@ page import="Entity.ProImage" %>
<%--
  User: duckhaidev
  Date: 2/25/2022
  Time: 8:55 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/assets" var="url" />
<!DOCTYPE html>
<html lang="en">
<head>
    <title>${category.CAT_NAME}</title>
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
    <link rel="stylesheet" type="text/css" href="${url}/vendor/daterangepicker/daterangepicker.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${url}/vendor/slick/slick.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${url}/vendor/MagnificPopup/magnific-popup.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${url}/vendor/perfect-scrollbar/perfect-scrollbar.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${url}/css/util.css">
    <link rel="stylesheet" type="text/css" href="${url}/css/main.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${url}/css/custom.css">
</head>
<body class="animsition">

<!-- Header -->
<jsp:include page="header-v4.jsp"/>

<!-- Cart -->
<jsp:include page="cart.jsp"/>

<!-- Product -->
<div class="bg0 m-t-23 p-b-140">
    <div class="container">
        <div class="flex-w flex-sb-m p-b-36">
            <div class="flex-w flex-l-m filter-tope-group m-tb-10">
                <ul class="breadcrumb">
                    <li><a href="${pageContext.request.contextPath}">Trang chủ</a></li>
                    <li><a href="#">${category.ROOM.ROOM_NAME}</a></li>
                    <li><a href="${pageContext.request.contextPath}/products/category?id=${category.CAT_ID}">${category.CAT_NAME}</a></li>
                </ul>
            </div>

            <div class="flex-w flex-c-m m-tb-10">
                <div class="flex-c-m stext-106 cl6 size-104 bor4 pointer hov-btn3 trans-04 m-r-8 m-tb-4 js-show-filter">
                    <i class="icon-filter cl2 m-r-6 fs-15 trans-04 zmdi zmdi-filter-list"></i>
                    <i class="icon-close-filter cl2 m-r-6 fs-15 trans-04 zmdi zmdi-close dis-none"></i>
                    Sắp xếp
                </div>

                <div class="flex-c-m stext-106 cl6 size-105 bor4 pointer hov-btn3 trans-04 m-tb-4 js-show-search">
                    <i class="icon-search cl2 m-r-6 fs-15 trans-04 zmdi zmdi-search"></i>
                    <i class="icon-close-search cl2 m-r-6 fs-15 trans-04 zmdi zmdi-close dis-none"></i>
                    Tìm kiếm
                </div>
            </div>

            <!-- Search product -->
            <div class="dis-none panel-search w-full p-t-10 p-b-15">
                <div class="bor8 dis-flex p-l-15">
                    <button class="size-113 flex-c-m fs-16 cl2 hov-cl1 trans-04">
                        <i class="zmdi zmdi-search"></i>
                    </button>

                    <input class="mtext-107 cl2 size-114 plh2 p-r-15" type="text" name="search-product" placeholder="Search">
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-3 filter-by-block">
                <h1>BỘ LỌC TÌM KIẾM</h1>

                <div id="accordion-1" class="panel-group">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h2 class="panel-title">
                                <a href="#collapse-1" data-toggle="collapse" data-parent="#accordion-1">
                                    Thương Hiệu
                                    <i class="fa fa-angle-down float-right"></i>
                                </a>
                            </h2>
                        </div>
                        <div id="collapse-1" class="panel-collapse collapse in show">
                            <div class="panel-body">
                                <ul class="list-unstyled checkbox-list">
                                    <c:forEach items="${applicationScope.brands}" var="brand" varStatus="loop">
                                        <li><label class="checkbox">
                                            <c:set var="index" value="${loop.index + 1}"/>
                                            <input id="brand-${index}" class="filter-input" type="checkbox" name="brand" value="${brand.BRA_ID}"><i></i>${brand.BRA_NAME}
                                            <small>
                                                <a>
                                                    (<%
                                                    String CAT_ID = ((Category) request.getAttribute("category")).getCAT_ID();
                                                    String BRA_ID = ((Brand) pageContext.getAttribute("brand")).getBRA_ID();
                                                    out.print(new ProductService().countProduct(CAT_ID, BRA_ID));
                                                %>)
                                                </a>
                                            </small>
                                        </label></li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>

                <div id="accordion-2" class="panel-group">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h2 class="panel-title">
                                <a href="#collapse-2" data-toggle="collapse" data-parent="#accordion-2">
                                    Khoảng Giá
                                    <i class="fa fa-angle-down float-right"></i>
                                </a>
                            </h2>
                        </div>
                        <div id="collapse-2" class="panel-collapse collapse in show">
                            <div class="panel-body">
                                <ul class="list-unstyled checkbox-list">
                                    <li><label class="checkbox">
                                        <input id="price-1" class="filter-input" type="checkbox" name="price" value="lt10"><i></i>< 10,000,000
                                    </label></li>
                                    <li><label class="checkbox">
                                        <input id="price-2" class="filter-input" type="checkbox" name="price" value="10t50"><i></i>10,000,000 - 50,000,000
                                    </label></li>
                                    <li><label class="checkbox">
                                        <input id="price-3" class="filter-input" type="checkbox" name="price" value="50t100"><i></i>50,000,000 - 100,000,000
                                    </label></li>
                                    <li><label class="checkbox">
                                        <input id="price-4" class="filter-input" type="checkbox" name="price" value="100t200"><i></i>100,000,000 - 200,000,000
                                    </label></li>
                                    <li><label class="checkbox">
                                        <input id="price-5" class="filter-input" type="checkbox" name="price" value="200t500"><i></i>200,000,000 - 500,000,000
                                    </label></li>
                                    <li><label class="checkbox">
                                        <input id="price-6" class="filter-input" type="checkbox" name="price" value="mt500"><i></i>> 500,000,000
                                    </label></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>

                <div id="accordion-3" class="panel-group">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h2 class="panel-title">
                                <a href="#collapse-3" data-toggle="collapse" data-parent="#accordion-3">
                                    Đánh Giá
                                    <i class="fa fa-angle-down float-right"></i>
                                </a>
                            </h2>
                        </div>
                        <div id="collapse-3" class="panel-collapse collapse in show">
                            <div class="panel-body d-flex justify-content-center">
                                <div class="stars-ratings stars-ratings-label">
                                    <input class="filter-input" type="radio" name="stars-rating" id="stars-rating-5" value="5">
                                    <label for="stars-rating-5"><i class="fa fa-star"></i></label>
                                    <input class="filter-input" type="radio" name="stars-rating" id="stars-rating-4" value="4">
                                    <label for="stars-rating-4"><i class="fa fa-star"></i></label>
                                    <input class="filter-input" type="radio" name="stars-rating" id="stars-rating-3" value="3">
                                    <label for="stars-rating-3"><i class="fa fa-star"></i></label>
                                    <input class="filter-input" type="radio" name="stars-rating" id="stars-rating-2" value="2">
                                    <label for="stars-rating-2"><i class="fa fa-star"></i></label>
                                    <input class="filter-input" type="radio" name="stars-rating" id="stars-rating-1" value="1">
                                    <label for="stars-rating-1"><i class="fa fa-star"></i></label>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12"><button id="filter-reset" class="btn btn-primary filter-button w-full" type="reset">ĐẶT LẠI</button></div>
                </div>
            </div>

            <div class="col-md-9">
                <div class="row">
                    <c:forEach items="${products}" var="product" varStatus="loop">
                        <div class="col-sm-6 col-md-4 col-lg-4 p-b-35 women">
                            <div class="block2">
                                <div class="block2-pic hov-img0">
                                    <c:url value="/images/product-images?fname=${product.getProReIMG()}" var="productImg"/>
                                    <img class="product-img" src="${productImg}" alt="Hình ảnh">
                                    <a href="#" class="block2-btn flex-c-m stext-103 cl2 size-102 bg0 bor2 hov-btn1 p-lr-15 trans-04 js-show-modal${loop.index + 1}">Xem</a>
                                </div>
                                <div class="block2-txt flex-w flex-t p-t-14">
                                    <div class="block2-txt-child1 flex-col-l ">
                                        <a href="product-detail.jsp" class="product-name stext-104 cl4 hov-cl1 trans-04 js-name-b2 m-b-6">${product.PRO_NAME}</a>
                                    </div>
                                    <div class="block2-txt-child2 flex-r p-t-3">
                                        <a href="#" class="btn-addwish-b2 dis-block pos-relative js-addwish-b2">
                                            <img class="icon-heart1 dis-block trans-04" src="${url}/images/icons/icon-heart-01.png" alt="ICON">
                                            <img class="icon-heart2 dis-block trans-04 ab-t-l" src="${url}/images/icons/icon-heart-02.png" alt="ICON">
                                        </a>
                                    </div>
                                </div>
                                <div class="block2-txt flex-w flex-t">
                                    <div class="block2-txt-child1 flex-col-l ">
                                        <div class="d-flex">
                                            <span class="stext-105 cl3 product-price m-r-12">
                                                <%
                                                    BigDecimal price = new BigDecimal(((Product) pageContext.getAttribute("product")).getPRO_PRICE());
                                                    Locale vie = new Locale("vi", "VN");
                                                    NumberFormat dongFormat = NumberFormat.getCurrencyInstance(vie);
                                                    String showPrice = dongFormat.format(price);
                                                    out.print("<td>" + showPrice + "</td>");
                                                %>
                                            </span>
                                            <%
                                                BigDecimal cost = new BigDecimal(((Product) pageContext.getAttribute("product")).getPRO_COST());
                                                String showCost = dongFormat.format(cost);
                                            %>
                                            <c:if test="${product.PRO_COST != '0' && product.PRO_COST != product.PRO_PRICE}">
                                                <span class="stext-105 cl3 product-cost">
                                                    <% out.print("<td>" + showCost + "</td>"); %>
                                                </span>
                                            </c:if>
                                        </div>
                                    </div>
                                    <%
                                        boolean b = price.compareTo(cost) < 0;
                                        request.setAttribute("b", b);

                                        BigDecimal percentage = new BigDecimal(0);
                                    %>
                                    <c:if test="${b}">
                                        <div class="block2-txt-child2 flex-r p-t-3">
                                        <span class="stext-105 cl3 product-sale-off">
                                            <%
                                                percentage = ((cost.subtract(price)).divide(cost, 2, RoundingMode.HALF_UP)).multiply(new BigDecimal("100")).setScale(0, RoundingMode.UP);
                                                out.print("(-" + percentage + "%)");
                                            %>
                                        </span>
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                        <!-- Modal${loop.index + 1} -->
                        <div class="wrap-modal1 js-modal${loop.index + 1} p-t-60 p-b-20">
                            <div class="overlay-modal1 js-hide-modal${loop.index + 1}"></div>

                            <div class="container">
                                <div class="bg0 p-t-60 p-b-30 p-lr-15-lg how-pos3-parent">
                                    <button class="how-pos3 hov3 trans-04 js-hide-modal${loop.index + 1}">
                                        <img src="${url}/images/icons/icon-close.png" alt="CLOSE">
                                    </button>

                                    <div class="row">
                                        <div class="col-md-6 col-lg-7 p-b-30">
                                            <div class="p-l-25 p-r-30 p-lr-0-lg">
                                                <div class="wrap-slick3 flex-sb flex-w">
                                                    <div class="wrap-slick3-dots"></div>
                                                    <div class="wrap-slick3-arrows flex-sb-m flex-w"></div>

                                                    <div class="slick3 gallery-lb">
                                                        <%
                                                            ProImageService imageService = new ProImageService();
                                                            List<ProImage> images = imageService.getProImage(((Product) pageContext.getAttribute("product")).getPRO_ID());
                                                            request.setAttribute("images", images);
                                                        %>
                                                        <c:forEach items="${images}" var="image">
                                                            <c:url var="imageUrl" value="/images/product-images?fname=${image.IMG_NAME}"/>
                                                            <div class="item-slick3" data-thumb="${imageUrl}">
                                                                <div class="wrap-pic-w pos-relative">
                                                                    <img class="modal-product-image" src="${imageUrl}" alt="Hình ảnh sản phẩm">
                                                                    <a class="flex-c-m size-108 how-pos1 bor0 fs-16 cl10 bg0 hov-btn3 trans-04" href="${imageUrl}">
                                                                        <i class="fa fa-expand"></i>
                                                                    </a>
                                                                </div>
                                                            </div>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="col-md-6 col-lg-5 p-b-30">
                                            <div class="p-r-50 p-t-5 p-lr-0-lg">
                                                <h4 class="mtext-105 cl2 js-name-detail p-b-14">${product.PRO_NAME}</h4>
                                                <span class="product-price mtext-106 cl2 m-r-16"><% out.print(showPrice); %></span>
                                                <span class="product-cost mtext-106 cl2 m-r-16"><% out.print(showCost); %></span>
                                                <span class="product-sale-off mtext-106 cl2"><% out.print("(-" + percentage + "%)"); %></span>
                                                <p class="stext-102 cl3 p-t-23">${product.PRO_DES}</p>
                                                <!--  -->
                                                <div class="p-t-33">
                                                    <div class="flex-w flex-r-m p-b-10">
                                                        <div class="size-204 flex-w flex-m respon6-next">
                                                            <div class="wrap-num-product flex-w m-r-20 m-tb-10">
                                                                <div class="btn-num-product-down cl8 hov-btn3 trans-04 flex-c-m">
                                                                    <i class="fs-16 zmdi zmdi-minus"></i>
                                                                </div>

                                                                <input class="mtext-104 cl3 txt-center num-product" type="number" name="num-product" value="1">

                                                                <div class="btn-num-product-up cl8 hov-btn3 trans-04 flex-c-m">
                                                                    <i class="fs-16 zmdi zmdi-plus"></i>
                                                                </div>
                                                            </div>

                                                            <button class="flex-c-m stext-101 cl0 size-101 bg1 bor1 hov-btn1 p-lr-15 trans-04 js-addcart-detail">
                                                                Thêm vào giỏ hàng
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>

                                                <!--  -->
                                                <div class="flex-w flex-m p-l-100 p-t-40 respon7">
                                                    <div class="flex-m bor9 p-r-10 m-r-11">
                                                        <a href="#" class="fs-14 cl3 hov-cl1 trans-04 lh-10 p-lr-5 p-tb-2 js-addwish-detail tooltip100" data-tooltip="Add to Wishlist">
                                                            <i class="zmdi zmdi-favorite"></i>
                                                        </a>
                                                    </div>

                                                    <a href="#" class="fs-14 cl3 hov-cl1 trans-04 lh-10 p-lr-5 p-tb-2 m-r-8 tooltip100" data-tooltip="Facebook">
                                                        <i class="fab fa-facebook"></i>
                                                    </a>

                                                    <a href="#" class="fs-14 cl3 hov-cl1 trans-04 lh-10 p-lr-5 p-tb-2 m-r-8 tooltip100" data-tooltip="Twitter">
                                                        <i class="fab fa-twitter"></i>
                                                    </a>

                                                    <a href="#" class="fs-14 cl3 hov-cl1 trans-04 lh-10 p-lr-5 p-tb-2 m-r-8 tooltip100" data-tooltip="Google Plus">
                                                        <i class="fab fa-google-plus"></i>
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <div class="row">
                    <!-- Load more -->
                    <div class="flex-c-m flex-w w-full p-t-45">
                        <a href="#" class="flex-c-m stext-101 cl5 size-103 bg2 bor1 hov-btn1 p-lr-15 trans-04">
                            TẢI THÊM
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

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
<script src="${url}/vendor/daterangepicker/moment.min.js"></script>
<script src="${url}/vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
<script src="${url}/vendor/slick/slick.min.js"></script>
<script src="${url}/js/slick-custom.js"></script>
<!--===============================================================================================-->
<script src="${url}/vendor/parallax100/parallax100.js"></script>
<script>
    $('.parallax100').parallax100();
</script>
<!--===============================================================================================-->
<script src="${url}/vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
<script>
    $('.gallery-lb').each(function() { // the containers for all your galleries
        $(this).magnificPopup({
            delegate: 'a', // the selector for gallery item
            type: 'image',
            gallery: {
                enabled:true
            },
            mainClass: 'mfp-fade'
        });
    });
</script>
<!--===============================================================================================-->
<script src="${url}/vendor/isotope/isotope.pkgd.min.js"></script>
<!--===============================================================================================-->
<script src="${url}/vendor/sweetalert/sweetalert.min.js"></script>
<script>
    $('.js-addwish-b2, .js-addwish-detail').on('click', function(e){
        e.preventDefault();
    });

    $('.js-addwish-b2').each(function(){
        var nameProduct = $(this).parent().parent().find('.js-name-b2').html();
        $(this).on('click', function(){
            swal(nameProduct, "đã được thêm vào danh sách yêu thích!", "success");

            $(this).addClass('js-addedwish-b2');
            $(this).off('click');
        });
    });

    $('.js-addwish-detail').each(function(){
        var nameProduct = $(this).parent().parent().parent().find('.js-name-detail').html();

        $(this).on('click', function(){
            swal(nameProduct, "đã được thêm vào danh sách yêu thích!", "success");

            $(this).addClass('js-addedwish-detail');
            $(this).off('click');
        });
    });

    /*---------------------------------------------*/

    $('.js-addcart-detail').each(function(){
        var nameProduct = $(this).parent().parent().parent().parent().find('.js-name-detail').html();
        $(this).on('click', function(){
            swal(nameProduct, "đã được thêm vào giỏ hàng!", "success");
        });
    });

</script>
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
<!--===============================================================================================-->
<script>
    $(function () {
        <c:forEach items="${products}" varStatus="loop">
            <!--show-modal${loop.index + 1}-->
            $('.js-show-modal${loop.index + 1}').on('click',function(e){
                e.preventDefault();
                $('.js-modal${loop.index + 1}').addClass('show-modal');
            });

            $('.js-hide-modal${loop.index + 1}').on('click',function(){
                $('.js-modal${loop.index + 1}').removeClass('show-modal');
            });
        </c:forEach>
    });
</script>
<!--===============================================================================================-->
<script>
    $(function () {
        var url = new URL(location.href);
        var params = url.searchParams;

        if (!params.has('brand')) {
            $('input[name="brand"]').prop('checked', false).each(function () {
                sessionStorage.removeItem(this.id);
            });
        }

        if (!params.has('price')) {
            $('input[name="price"]').prop('checked', false).each(function () {
                sessionStorage.removeItem(this.id);
            });
        }

        if (!params.has('stars-rating')) {
            $('input[name="stars-rating"]').prop('checked', false).each(function () {
                sessionStorage.removeItem(this.id);
            });
        }

        $('#filter-reset').on('click', function () {
            $('.filter-input').prop('checked', false);
            sessionStorage.clear();
            params.delete('brand');
            params.delete('price');
            params.delete('stars-rating');
            window.location = url.href;
        });
    });
</script>
<!--===============================================================================================-->
<script>
    $(document).ready(function () {
        $('input[type="checkbox"], input[type="radio"]').click(function (e) {
            var seasoning = '', tempArray = [];
            $('input[name="brand"]:checked').each(function () {
               tempArray.push($(this).val());
            });
            if (tempArray.length !== 0) {
                seasoning += '&brand=' + tempArray.toString();
                tempArray = [];
            }

           $('input[name="price"]:checked').each(function () {
               tempArray.push($(this).val());
           });
           if (tempArray.length !== 0) {
               seasoning += '&price=' + tempArray.toString();
               tempArray = [];
           }

           $('input[name="stars-rating"]:checked').each(function () {
               tempArray.push($(this).val());
           });
           if (tempArray.length !== 0) {
               seasoning += '&stars-rating=' + tempArray.toString();
               tempArray = [];
           }

            var url = new URL(location.href);
            var params = url.searchParams;
            params.delete('brand');
            params.delete('price');
            params.delete('stars-rating');
            window.location = url.href + seasoning;
       });
    });
</script>
<!--===============================================================================================-->
<script>
    $(function () {
        const inputs = $('input[type="checkbox"], input[type="radio"]');
        inputs.each(function () {
            $(this)
                .prop('checked', sessionStorage.getItem(this.id) === 'true')
                .on('change', function () {
                    sessionStorage.setItem(this.id, this.checked)
                })
                .trigger('change');
        });
    });
</script>

</body>
</html>
