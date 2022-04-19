<%@ page import="java.util.Calendar" %>
<%@ page import="Entity.ProImage" %>
<%@ page import="Entity.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.math.RoundingMode" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.Locale" %>
<%@ page import="Services.deploy.WLItemService" %>
<%@ page import="Entity.WishList" %>
<%@ page import="Services.deploy.ProImageService" %>
<%--
  Author: duckhaidev
  Date: 1/11/2022
  Time: 5:17 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Trang Chủ</title>
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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/util.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/main.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/custom.css">
</head>
<body class="animsition">

<!-- Header -->
<jsp:include page="header.jsp"/>

<!-- Cart -->
<jsp:include page="cart.jsp"/>

<!-- WishList -->
<jsp:include page="wishList.jsp"/>

<!-- Slider -->
<jsp:include page="slider.jsp"/>

<!-- Banner -->
<div class="sec-banner bg0 p-t-80 p-b-50">
    <div class="container">
        <div class="row">
            <div class="col-md-6 col-xl-4 p-b-30 m-lr-auto">
                <!-- Block1 -->
                <div class="block1 wrap-pic-w">
                    <img src="${pageContext.request.contextPath}/assets/images/Co_Dien.jpg" style="object-fit: cover; height: 210px;" alt="IMG-BANNER">

                    <a href="#" class="block1-txt ab-t-l s-full flex-col-l-sb p-16-38-34-16 trans-03 respon3">
                        <div class="block1-txt-child1 flex-col-l p-all-8 bg-2 w-60">
                            <span class="block1-name ltext-102 trans-04 p-b-8 text-white">
                                Cổ Điển
                            </span>

                            <span class="block1-info stext-102 trans-04 text-white">
                                Classic
                                <%
                                    int year = Calendar.getInstance().get(Calendar.YEAR);
                                    out.print(year);
                                %>
                            </span>
                        </div>

                        <div class="block1-txt-child2 p-b-4 trans-05">
                            <div class="block1-link stext-101 cl0 trans-09">
                                Xem Ngay
                            </div>
                        </div>
                    </a>
                </div>
            </div>

            <div class="col-md-6 col-xl-4 p-b-30 m-lr-auto">
                <!-- Block1 -->
                <div class="block1 wrap-pic-w">
                    <img src="${pageContext.request.contextPath}/assets/images/Tan_Co_Dien.jpg" style="object-fit: cover; height: 210px;" alt="IMG-BANNER">

                    <a href="#" class="block1-txt ab-t-l s-full flex-col-l-sb p-16-38-34-16 trans-03 respon3">
                        <div class="block1-txt-child1 flex-col-l p-all-8 bg-2 w-60">
                            <span class="block1-name ltext-102 trans-04 p-b-8 text-white">
                                Tân Cổ Điển
                            </span>

                            <span class="block1-info stext-102 trans-04 text-white">
									Neoclassical <% out.print(year); %>
								</span>
                        </div>

                        <div class="block1-txt-child2 p-b-4 trans-05">
                            <div class="block1-link stext-101 cl0 trans-09">
                                Xem Ngay
                            </div>
                        </div>
                    </a>
                </div>
            </div>

            <div class="col-md-6 col-xl-4 p-b-30 m-lr-auto">
                <!-- Block1 -->
                <div class="block1 wrap-pic-w">
                    <img src="${pageContext.request.contextPath}/assets/images/Hien_Dai.jpg" style="object-fit: cover; height: 210px;" alt="IMG-BANNER">

                    <a href="#" class="block1-txt ab-t-l s-full flex-col-l-sb p-16-38-34-16 trans-03 respon3">
                        <div class="block1-txt-child1 flex-col-l p-all-8 bg-2 w-60">
                            <span class="block1-name ltext-102 trans-04 p-b-8 text-white">
                                Hiện Đại
                            </span>

                            <span class="block1-info stext-102 trans-04 text-white">
                                Modern <% out.print(year); %>
							</span>
                        </div>

                        <div class="block1-txt-child2 p-b-4 trans-05">
                            <div class="block1-link stext-101 cl0 trans-09">
                                Xem Ngay
                            </div>
                        </div>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Product -->
<section class="bg0 p-t-23 p-b-140">
    <div class="container">
        <div class="p-b-10">
            <h3 class="ltext-103 cl5 soft-emboss-1">
                Sản Phẩm Của Chúng Tôi
            </h3>
        </div>

        <div class="flex-w flex-sb-m p-b-52">
            <div class="flex-w flex-l-m filter-tope-group m-tb-10">
                <button class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5 how-active1 my-products" data-filter="*">
                    Tất Cả Sản Phẩm
                </button>

                <button class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5 my-products" data-filter=".women">
                    Phòng Khách
                </button>

                <button class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5 my-products" data-filter=".men">
                    Phòng Bếp
                </button>

                <button class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5 my-products" data-filter=".bag">
                    Phòng Ngủ
                </button>

                <button class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5 my-products" data-filter=".shoes">
                    Văn Phòng
                </button>

                <button class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5 my-products" data-filter=".watches">
                    Phòng Thờ
                </button>
            </div>
        </div>

        <div class="row">
            <jsp:useBean id="products" scope="request" type="java.util.List"/>
            <c:set var="countLiving" value="0"/>
            <c:set var="countKitchen" value="0"/>
            <c:set var="countBed" value="0"/>
            <c:set var="countOffice" value="0"/>
            <c:set var="countAltar" value="0"/>
            <c:forEach items="${products}" var="product" varStatus="loop">
                <div class="col-sm-6 col-md-3 col-lg-3 p-b-35 women all-product-${loop.index + 1} <c:if test="${product.category.room.roomId == 'LVR'}">living-${countLiving = countLiving + 1} </c:if><c:if test="${product.category.room.roomId == 'KIT'}">kitchen-${countKitchen = countKitchen + 1}</c:if><c:if test="${product.category.room.roomId == 'BED'}">bedroom-${countBed = countBed + 1}</c:if><c:if test="${product.category.room.roomId == 'OFF'}">office-${countOffice = countOffice + 1}</c:if><c:if test="${product.category.room.roomId == 'ALT'}">altar-${countAltar = countAltar + 1}</c:if>">
                    <div class="block2">
                        <div class="block2-pic hov-img0">
                            <%
                                ProImageService imageService = new ProImageService();
                                String reImage = imageService.getProReImage(((Product) pageContext.getAttribute("product")).getProductId());
                                request.setAttribute("reImage", reImage);
                            %>
                            <c:url value="/images/product-images?fname=${reImage}" var="productImg"/>
                            <img class="product-img" src="${productImg}" alt="Hình ảnh">
                            <a href="#" class="block2-btn flex-c-m stext-103 cl2 size-102 bg0 bor2 hov-btn1 p-lr-15 trans-04 js-show-modal">Xem Qua</a>
                        </div>
                        <div class="block2-txt flex-w flex-t p-t-14">
                            <div class="block2-txt-child1 flex-col-l ">
                                <a href="${pageContext.request.contextPath}/products/product-detail?id=${product.productId}" class="product-name stext-104 cl4 hov-cl1 trans-04 js-name-b2 m-b-6">${product.productName}</a>
                            </div>
                            <form action="<c:url value="/wishlist/add"/>" method="get" class="block2-txt-child2 flex-r p-t-3">
                                <!--Sign url-->
                                <input type="hidden" class="input-add-item" name="forwardTo">
                                <!--Sign product-->
                                <input type="hidden" name="id" value="${product.productId}">

                                <%
                                    WLItemService wlItemService = new WLItemService();
                                    Product product = (Product) pageContext.getAttribute("product");
                                    WishList wishList = (WishList) session.getAttribute("wishList");
                                    boolean existItem = wishList != null && wlItemService.checkExistItem(product.getProductId(), wishList.getWishListId());
                                    request.setAttribute("existItem", existItem);
                                %>
                                <button type="button" class="btn-add-item btn-addwish-b2 dis-block pos-relative <c:if test="${!existItem}">js-addwish-b2</c:if>">
                                    <img class="icon-heart1 dis-block trans-04" src="${pageContext.request.contextPath}/assets/images/icons/icon-heart-01.png" <c:if test="${existItem}">style="opacity: 0;" </c:if>alt="ICON">
                                    <img class="icon-heart2 dis-block trans-04 ab-t-l" src="${pageContext.request.contextPath}/assets/images/icons/icon-heart-02.png" <c:if test="${existItem}">style="opacity: 1;" </c:if>alt="ICON">
                                </button>
                            </form>
                        </div>
                        <div class="block2-txt flex-w flex-t">
                            <div class="block2-txt-child1 flex-col-l">
                                <div class="d-flex">
                                    <span class="stext-105 cl3 product-price m-r-12">
                                        <%
                                            BigDecimal price = ((Product) pageContext.getAttribute("product")).getProductPrice();
                                            Locale vie = new Locale("vi", "VN");
                                            NumberFormat dongFormat = NumberFormat.getCurrencyInstance(vie);
                                            String showPrice = dongFormat.format(price);
                                            out.print("<td>" + showPrice + "</td>");
                                        %>
                                    </span>
                                    <%
                                        BigDecimal cost = ((Product) pageContext.getAttribute("product")).getProductCost();
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
                <!-- Modal-->
                <div class="wrap-modal1 js-modal p-t-60 p-b-20">
                    <div class="overlay-modal1 js-hide-modal"></div>

                    <div class="container">
                        <div class="bg0 p-t-60 p-b-30 p-lr-15-lg how-pos3-parent">
                            <button class="how-pos3 hov3 trans-04 js-hide-modal">
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
                                                <form action="<c:url value="/cart/add"/>" method="get" class="size-204 flex-w flex-m respon6-next">
                                                    <!--Sign url-->
                                                    <input type="hidden" class="input-add-item" name="forwardTo">
                                                    <!--Sign product-->
                                                    <input type="hidden" name="id" value="${product.productId}">

                                                    <div class="wrap-num-product flex-w m-r-20 m-tb-10">
                                                        <div class="btn-num-product-down cl8 hov-btn3 trans-04 flex-c-m">
                                                            <i class="fs-16 zmdi zmdi-minus"></i>
                                                        </div>
                                                        <input class="mtext-104 cl3 txt-center num-product" type="number" name="num-product" value="1">
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
                                            <form action="<c:url value="/wishlist/add"/>" method="get" class="flex-m bor9 p-r-10 m-r-11">
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

        <!-- Load more -->
        <div class="flex-c-m flex-w w-full p-t-45">
            <button id="btn-load-more" class="flex-c-m stext-101 cl5 size-103 bg2 bor1 hov-btn1 p-lr-15 trans-04">
                Tải Thêm
            </button>
        </div>
    </div>
</section>

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
        let begin = 12;
        loadMore('all-product-', begin, ${products.size()});
        function loadMore(str, sz, total) {
            for (let i = 1; i <= total; i++) {
                if (i <= sz) {
                    $('.' + str + i).show();
                } else {
                    $('.' + str + i).hide();
                }
            }
        }

        function hideAll() {
            for (let i = 1; i <= ${products.size()}; i++) {
                $('.all-product-' + i).hide();
            }
        }

        const step = 8;
        $('#btn-load-more').on('click', function () {
            let dataFilter = $('.my-products.how-active1').data('filter');
            let total = ${products.size()};
            switch (dataFilter) {
                case '*':
                    dataFilter = 'all-product-';
                    total = ${products.size()};
                    break;
                case '.women':
                    dataFilter = 'living-';
                    total = ${requestScope.productLvr};
                    break;
                case '.men':
                    dataFilter = 'kitchen-';
                    total = ${requestScope.productKit};
                    break;
                case '.bag':
                    dataFilter = 'bedroom-';
                    total = ${requestScope.productBed};
                    break;
                case '.shoes':
                    dataFilter = 'office-';
                    total = ${requestScope.productOff};
                    break;
                default:
                    dataFilter = 'altar-';
                    total = ${requestScope.productAlt};
            }
            hideAll();
            begin += step;
            loadMore(dataFilter, begin, total);
        });

        $('.my-products').each(function () {
            $(this).on('click', function () {
                if ($(this).data('filter') === '*') {
                    hideAll();
                    begin = 12;
                    loadMore('all-product-', begin, ${products.size()});
                }

                //Living room
                if ($(this).data('filter') === '.women') {
                    hideAll();
                    begin = 12;
                    loadMore('living-', begin, ${requestScope.productLvr});
                }

                //Kitchen
                if ($(this).data('filter') === '.men') {
                    hideAll();
                    begin = 12;
                    loadMore('kitchen-', begin, ${requestScope.productKit});
                }

                //Bedroom
                if ($(this).data('filter') === '.bag') {
                    hideAll();
                    begin = 12;
                    loadMore('bedroom-', begin, ${requestScope.productBed});
                }

                //Office
                if ($(this).data('filter') === '.shoes') {
                    hideAll();
                    begin = 12;
                    loadMore('office-', begin, ${requestScope.productOff});
                }

                //Altar
                if ($(this).data('filter') === '.watches') {
                    hideAll();
                    begin = 12;
                    loadMore('altar-', begin, ${requestScope.productAlt});
                }
            });
        });
    });
</script>

</body>
</html>
