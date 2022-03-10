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
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="category" scope="request" type="Entity.Category"/>
<jsp:useBean id="totalNumber" scope="request" type="java.lang.Integer"/>
<jsp:useBean id="pageSize" scope="request" type="java.lang.Integer"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>${category.categoryName}</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/images/icons/icon-logo.png"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/fonts/fontawesome-pro-5.15.4-web/css/all.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/fonts/iconic/css/material-design-iconic-font.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/fonts/linearicons-v1.0.0/icon-font.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/vendor/css-hamburgers/hamburgers.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/vendor/animsition/css/animsition.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/vendor/daterangepicker/daterangepicker.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/vendor/slick/slick.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/vendor/MagnificPopup/magnific-popup.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/vendor/perfect-scrollbar/perfect-scrollbar.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/pagination.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/util.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/main.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/custom.css">
</head>
<body class="animsition">

<!-- Header -->
<jsp:include page="header-v4.jsp"/>

<!-- Cart -->
<jsp:include page="cart.jsp"/>

<!-- WishList -->
<jsp:include page="wishList.jsp"/>

<!-- Product -->
<div class="bg0 m-t-23 p-b-140">
    <div class="container">
        <div class="flex-w flex-sb-m p-b-36">
            <div class="flex-w flex-l-m filter-tope-group m-tb-10">
                <ul class="breadcrumb">
                    <li><a href="${pageContext.request.contextPath}">Trang chủ</a></li>
                    <li><a href="#">${category.room.roomName}</a></li>
                    <li><a href="${pageContext.request.contextPath}/products/category?id=${category.categoryId}">${category.categoryName}</a></li>
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

            <!-- Filter -->
            <div class="dis-none panel-filter w-full p-t-10">
                <div class="wrap-filter flex-w bg6 p-lr-40 p-t-27 p-lr-15-sm w-fit-content float-right m-r-60">
                    <ul>
                        <li class="p-b-6">
                            <a id="filter-link-default" href="#" class="filter-link stext-106 trans-04 filter-link-active">
                                Mặc định
                            </a>
                        </li>

                        <li class="p-b-6">
                            <a id="filter-link-priceAsc" href="#" class="filter-link stext-106 trans-04">
                                Giá: Thấp đến Cao
                            </a>
                        </li>

                        <li class="p-b-6">
                            <a id="filter-link-priceDesc" href="#" class="filter-link stext-106 trans-04">
                                Giá: Cao đến Thấp
                            </a>
                        </li>
                    </ul>
                </div>
            </div>

            <!-- Search product -->
            <div class="dis-none panel-search w-full p-t-10 p-b-15">
                <div class="bor8 dis-flex p-l-15">
                    <button id="search-product-button" class="size-113 flex-c-m fs-16 cl2 hov-cl1 trans-04">
                        <i class="zmdi zmdi-search"></i>
                    </button>

                    <label for="search-product"></label><input id="search-product" class="mtext-107 cl2 size-114 plh2 p-r-15" type="text" name="search-product" placeholder="Tìm kiếm">
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
                                            <input id="brand-${index}" class="filter-input" type="checkbox" name="brand" value="${brand.brandId}"><i></i>${brand.brandName}
                                            <small>
                                                <a>
                                                    (<%
                                                    String categoryId = ((Category) request.getAttribute("category")).getCategoryId();
                                                    String brandId = ((Brand) pageContext.getAttribute("brand")).getBrandId();
                                                    out.print(new ProductService().countProduct(categoryId, brandId));
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
                                    <input class="filter-input" type="radio" name="rating" id="stars-rating-5" value="5">
                                    <label for="stars-rating-5"><i class="fa fa-star"></i></label>
                                    <input class="filter-input" type="radio" name="rating" id="stars-rating-4" value="4">
                                    <label for="stars-rating-4"><i class="fa fa-star"></i></label>
                                    <input class="filter-input" type="radio" name="rating" id="stars-rating-3" value="3">
                                    <label for="stars-rating-3"><i class="fa fa-star"></i></label>
                                    <input class="filter-input" type="radio" name="rating" id="stars-rating-2" value="2">
                                    <label for="stars-rating-2"><i class="fa fa-star"></i></label>
                                    <input class="filter-input" type="radio" name="rating" id="stars-rating-1" value="1">
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
                <div class="row content">
                    <jsp:useBean id="products" scope="request" type="java.util.List"/>
                    <c:forEach items="${products}" var="product" varStatus="loop">
                        <div class="col-sm-6 col-md-4 col-lg-4 p-b-35 women">
                            <div class="block2">
                                <div class="block2-pic hov-img0">
                                    <%
                                        ProImageService imageService = new ProImageService();
                                        String reImage = imageService.getProReImage(((Product) pageContext.getAttribute("product")).getProductId());
                                        request.setAttribute("reImage", reImage);
                                    %>
                                    <c:url value="/images/product-images?fname=${reImage}" var="productImg"/>
                                    <img class="product-img" src="${productImg}" alt="Hình ảnh">
                                    <a href="#" class="block2-btn flex-c-m stext-103 cl2 size-102 bg0 bor2 hov-btn1 p-lr-15 trans-04 js-show-modal${loop.index + 1}">Xem</a>
                                </div>
                                <div class="block2-txt flex-w flex-t p-t-14">
                                    <div class="block2-txt-child1 flex-col-l ">
                                        <a href="product-detail.jsp" class="product-name stext-104 cl4 hov-cl1 trans-04 js-name-b2 m-b-6">${product.productName}</a>
                                    </div>
                                    <form id="wishlist-add-1" action="<c:url value="/wishlist/add"/>" method="get" class="block2-txt-child2 flex-r p-t-3">
                                        <!--Sign url-->
                                        <input type="hidden" class="input-add-item" name="forwardTo">

                                        <!--Sign product-->
                                        <input type="hidden" name="id" value="${product.productId}">

                                        <button type="button" class="btn-add-item btn-addwish-b2 dis-block pos-relative js-addwish-b2">
                                            <img class="icon-heart1 dis-block trans-04" src="${pageContext.request.contextPath}/assets/images/icons/icon-heart-01.png" alt="ICON">
                                            <img class="icon-heart2 dis-block trans-04 ab-t-l" src="${pageContext.request.contextPath}/assets/images/icons/icon-heart-02.png" alt="ICON">
                                        </button>
                                    </form>
                                </div>
                                <div class="block2-txt flex-w flex-t">
                                    <div class="block2-txt-child1 flex-col-l ">
                                        <div class="d-flex">
                                            <span class="stext-105 cl3 product-price m-r-12">
                                                <%
                                                    BigDecimal price = new BigDecimal(((Product) pageContext.getAttribute("product")).getProductPrice());
                                                    Locale vie = new Locale("vi", "VN");
                                                    NumberFormat dongFormat = NumberFormat.getCurrencyInstance(vie);
                                                    String showPrice = dongFormat.format(price);
                                                    out.print("<td>" + showPrice + "</td>");
                                                %>
                                            </span>
                                            <%
                                                BigDecimal cost = new BigDecimal(((Product) pageContext.getAttribute("product")).getProductCost());
                                                String showCost = dongFormat.format(cost);
                                            %>
                                            <c:if test="${product.productCost != '0' && product.productCost != product.productPrice}">
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
                                        <img src="${pageContext.request.contextPath}/assets/images/icons/icon-close.png" alt="CLOSE">
                                    </button>

                                    <div class="row">
                                        <div class="col-md-6 col-lg-7 p-b-30">
                                            <div class="p-l-25 p-r-30 p-lr-0-lg">
                                                <div class="wrap-slick3 flex-sb flex-w">
                                                    <div class="wrap-slick3-dots"></div>
                                                    <div class="wrap-slick3-arrows flex-sb-m flex-w"></div>

                                                    <div class="slick3 gallery-lb">
                                                        <%
                                                            List<ProImage> images = imageService.getProImage(((Product) pageContext.getAttribute("product")).getProductId());
                                                            request.setAttribute("images", images);
                                                        %>
                                                        <c:forEach items="${images}" var="image">
                                                            <c:url var="imageUrl" value="/images/product-images?fname=${image.imageName}"/>
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
                                                <h4 class="mtext-105 cl2 js-name-detail p-b-14">${product.productName}</h4>
                                                <span class="product-price mtext-106 cl2 m-r-16"><% out.print(showPrice); %></span>
                                                <span class="product-cost mtext-106 cl2 m-r-16"><% out.print(showCost); %></span>
                                                <span class="product-sale-off mtext-106 cl2"><% out.print("(-" + percentage + "%)"); %></span>
                                                <p class="stext-102 cl3 p-t-23">${product.productDescription}</p>
                                                <!--  -->
                                                <div class="p-t-33">
                                                    <div class="flex-w flex-r-m p-b-10">
                                                        <form id="cart-add" action="<c:url value="/cart/add"/>" method="get" class="size-204 flex-w flex-m respon6-next">
                                                            <!--Sign url-->
                                                            <input type="hidden" class="input-add-item" name="forwardTo">
                                                            <!--Sign product-->
                                                            <input type="hidden" name="id" value="${product.productId}">

                                                            <div class="wrap-num-product flex-w m-r-20 m-tb-10">
                                                                <label for="num-product" style="display: none"></label>
                                                                <div class="btn-num-product-down cl8 hov-btn3 trans-04 flex-c-m">
                                                                    <i class="fs-16 zmdi zmdi-minus"></i>
                                                                </div>
                                                                <input id="num-product" class="mtext-104 cl3 txt-center num-product" type="number" name="num-product" value="1">
                                                                <div class="btn-num-product-up cl8 hov-btn3 trans-04 flex-c-m">
                                                                    <i class="fs-16 zmdi zmdi-plus"></i>
                                                                </div>
                                                            </div>

                                                            <button type="button" class="btn-add-item flex-c-m stext-101 cl0 size-101 bg1 bor1 hov-btn1 p-lr-15 trans-04 js-addcart-detail">
                                                                Thêm vào giỏ hàng
                                                            </button>
                                                        </form>
                                                    </div>
                                                </div>

                                                <!--  -->
                                                <div class="flex-w flex-m p-l-100 p-t-40 respon7">
                                                    <form id="wishlist-add-2" action="<c:url value="/wishlist/add"/>" method="get" class="flex-m bor9 p-r-10 m-r-11">
                                                        <!--Sign url-->
                                                        <input type="hidden" class="input-add-item" name="forwardTo">
                                                        <!--Sign product-->
                                                        <input type="hidden" name="id" value="${product.productId}">

                                                        <button type="button" class="btn-add-item fs-14 cl3 hov-cl1 trans-04 lh-10 p-lr-5 p-tb-2 js-addwish-detail tooltip100" data-tooltip="Thêm vào Danh sách yêu thích">
                                                            <i class="zmdi zmdi-favorite"></i>
                                                        </button>
                                                    </form>

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
                <div class="row d-flex justify-content-end">
                    <div class="col">
                        <!-- Pagination -->
                        <div id="pagination" class="float-right"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
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
    $(".js-select2").each(function(){
        $(this).select2({
            minimumResultsForSearch: 20,
            dropdownParent: $(this).next('.dropDownSelect2')
        });
    })
</script>
<!--===============================================================================================-->
<script src="${pageContext.request.contextPath}/assets/vendor/daterangepicker/moment.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
<script src="${pageContext.request.contextPath}/assets/vendor/slick/slick.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/slick-custom.js"></script>
<!--===============================================================================================-->
<script src="${pageContext.request.contextPath}/assets/vendor/parallax100/parallax100.js"></script>
<script>
    $('.parallax100').parallax100();
</script>
<!--===============================================================================================-->
<script src="${pageContext.request.contextPath}/assets/vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
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
<script src="${pageContext.request.contextPath}/assets/vendor/isotope/isotope.pkgd.min.js"></script>
<!--===============================================================================================-->
<script src="${pageContext.request.contextPath}/assets/vendor/sweetalert/sweetalert.min.js"></script>
<script>
    $('.js-addwish-b2, .js-addwish-detail').on('click', function(e){
        e.preventDefault();
    });

    $('.js-addwish-b2').each(function(){
        const nameProduct = $(this).parent().parent().find('.js-name-b2').html();
        $(this).on('click', function(){
            swal(nameProduct, "đã được thêm vào danh sách yêu thích!", "success").then(function () {
                $('#wishlist-add-1').submit();
            });

            $(this).addClass('js-addedwish-b2');
            $(this).off('click');
        });
    });

    $('.js-addwish-detail').each(function(){
        const nameProduct = $(this).parent().parent().parent().find('.js-name-detail').html();

        $(this).on('click', function(){
            swal(nameProduct, "đã được thêm vào danh sách yêu thích!", "success").then(function () {
                $('#wishlist-add-2').submit();
            });

            $(this).addClass('js-addedwish-detail');
            $(this).off('click');
        });
    });

    /*---------------------------------------------*/

    $('.js-addcart-detail').each(function(){
        const nameProduct = $(this).parent().parent().parent().parent().find('.js-name-detail').html();
        $(this).on('click', function(){
            swal(nameProduct, "đã được thêm vào giỏ hàng!", "success").then(function () {
                $('#cart-add').submit();
            });
        });
    });

</script>
<!--===============================================================================================-->
<script src="${pageContext.request.contextPath}/assets/vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
<script>
    $('.js-pscroll').each(function(){
        $(this).css('position','relative');
        $(this).css('overflow','hidden');
        const ps = new PerfectScrollbar(this, {
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
<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
<!--===============================================================================================-->
<script src="${pageContext.request.contextPath}/assets/js/custom.js"></script>
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
<script src="${pageContext.request.contextPath}/assets/js/pagination.min.js"></script>
<script>
    $(function () {
        //Phân trang
        const p = $('#pagination');
        p.pagination({
            dataSource: function (done) {
                let result = [];
                for (let i = 0; i < ${totalNumber}; i++) {
                    result.push(i);
                }
                done(result);
            },
            pageSize: ${pageSize},
            className: 'paginationjs-big',
            afterInit: function () {
                //Sau khi load trang mới thì đặt trang hiện tại = trang đã lưu trong sessionScope (nếu trang đã lưu != 1)
                const currPage = sessionStorage.getItem('currPage');
                if (currPage !== null) {
                    p.pagination('go', currPage);
                }
            },
            afterPageOnClick: function () {
                //Chuyển đến trang mới
                const currPage = p.pagination('getSelectedPageNum');
                sessionStorage.setItem('currPage', currPage);
                const url = new URL(location.href);
                const params = url.searchParams;
                params.delete('page');
                if (currPage > 1) {
                    params.append('page', sessionStorage.getItem('currPage'));
                }

                location.href = url.href;
            }
        });
    });
</script>

</body>
</html>