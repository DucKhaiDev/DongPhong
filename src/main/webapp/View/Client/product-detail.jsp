<%@ page import="java.util.List" %>
<%@ page import="Entity.ProImage" %>
<%@ page import="Services.deploy.ProImageService" %>
<%@ page import="Entity.Product" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="Services.deploy.UserService" %>
<%@ page import="Services.deploy.ReviewService" %>
<%@ page import="Services.deploy.WLItemService" %>
<%@ page import="Entity.WishList" %>
<%@ page import="java.math.RoundingMode" %>
<%--
  Author: duckhaidev
  Date: 1/11/2022
  Time: 5:40 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="product" scope="request" type="Entity.Product"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>${product.productName}</title>
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
<jsp:include page="header-v4.jsp"/>

<!-- Cart -->
<jsp:include page="cart.jsp"/>

<!-- breadcrumb -->
<div class="container">
    <div class="bread-crumb flex-w p-l-25 p-r-15 p-t-30 p-lr-0-lg">
        <a href="${pageContext.request.contextPath}" class="stext-109 cl8 hov-cl1 trans-04 font-size-15">
            Trang chủ
            <i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i>
        </a>

        <a href="#" class="stext-109 cl8 hov-cl1 trans-04 font-size-15">
            ${product.category.room.roomName}
            <i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i>
        </a>

        <a href="${pageContext.request.contextPath}/products/category?id=${product.category.categoryId}" class="stext-109 cl8 hov-cl1 trans-04 font-size-15">
            ${product.category.categoryName}
            <i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i>
        </a>

        <span class="stext-109 cl4 font-size-15">
				${product.productName}
        </span>
    </div>
</div>

<!-- Product Detail -->
<section class="sec-product-detail bg0 p-t-65 p-b-60">
    <div class="container">
        <div class="row">
            <div class="col-md-6 col-lg-7 p-b-30">
                <div class="p-l-25 p-r-30 p-lr-0-lg">
                    <div class="wrap-slick3 flex-sb flex-w">
                        <div class="wrap-slick3-dots"></div>
                        <div class="wrap-slick3-arrows flex-sb-m flex-w"></div>

                        <div class="slick3 gallery-lb">
                            <%
                                ProImageService imageService = new ProImageService();
                                List<ProImage> images = imageService.getProImage(((Product) request.getAttribute("product")).getProductId());
                                request.setAttribute("images", images);
                            %>
                            <c:forEach items="${images}" var="image">
                                <c:url var="imageUrl" value="/images/product-images?fname=${image.imageName}"/>
                                <div class="item-slick3" data-thumb="${imageUrl}">
                                    <div class="wrap-pic-w pos-relative">
                                        <img class="product-detail-img" src="${imageUrl}" alt="Hình ảnh sản phẩm">

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
                    <h4 class="mtext-105 cl2 js-name-detail p-b-14">
                        ${product.productName}
                    </h4>
                    <label class="font-size-18 m-r-8 d-inline-block">Mã sản phẩm:</label>
                    <span class="mtext-106 cl2">
                        ${product.productId}
                    </span>
                    <hr>

                    <%
                        Locale vie = new Locale("vi", "VN");
                        NumberFormat dongFormat = NumberFormat.getCurrencyInstance(vie);
                        BigDecimal productPrice = new BigDecimal(((Product) request.getAttribute("product")).getProductPrice());
                        BigDecimal productCost = new BigDecimal(((Product) request.getAttribute("product")).getProductCost());
                    %>
                    <div class="d-block">
                        <label class="font-size-18 m-r-8 d-inline-block">Giá bán:</label>
                        <span class="mtext-106 cl2 product-price">
                            <% out.print(dongFormat.format(productPrice)); %>
                        </span>
                    </div>
                    <c:if test="${product.productCost != '0' && product.productCost != product.productPrice}">
                        <div class="d-block">
                            <label class="font-size-15 m-r-8 d-inline-block">Giá gốc:</label>
                            <span class="mtext-106 cl2 product-cost font-size-15 m-r-12">
                                <% out.print(dongFormat.format(productCost)); %>
                            </span>
                        </div>
                    </c:if>
                    <span class="stext-102 cl3">
                        (Tiết kiệm:<span class="product-price m-l-8"><% out.print(dongFormat.format(productCost.subtract(productPrice))); %></span>)
                    </span>
                    <hr>

                    <span class="stext-102 cl3 d-block">
                        Loại sản phẩm:<span class="m-l-8">${product.category.categoryName}</span>
                    </span>
                    <span class="stext-102 cl3 d-block">
                        Thương hiệu:<span class="m-l-8">${product.brand.brandName}</span>
                    </span>
                    <span class="stext-102 cl3 d-block">
                        Bảo hành:<span class="m-l-8">24 tháng</span>
                    </span>
                    <hr>

                    <span class="stext-102 cl3 d-block">
                        <!--Lấy số điện thoại của user consultant-->
                        Gọi để được tư vấn mua hàng ngay:<span class="product-price m-l-8"><% out.print((new UserService()).getUser("CONSULTANT").getPhone()); %></span>
                    </span>
                    <span class="stext-102 cl3 d-block">
                        (Từ 7:30 - 20:30 hằng ngày)
                    </span>
                    <hr>
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

                                <button type="button" class=" btn-add-item flex-c-m stext-101 cl0 size-101 bg1 bor1 hov-btn1 p-lr-15 trans-04 js-addcart-detail">
                                    Thêm vào giỏ hàng
                                </button>
                            </form>
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

        <div class="bor10 m-t-50 p-t-43 p-b-40">
            <!-- Tab01 -->
            <div class="tab01">
                <!-- Nav tabs -->
                <ul class="nav nav-tabs" role="tablist">
                    <li class="nav-item p-b-10">
                        <a class="nav-link active" data-toggle="tab" href="#description" role="tab">Mô tả</a>
                    </li>

                    <li class="nav-item p-b-10">
                        <a class="nav-link" data-toggle="tab" href="#information" role="tab">Thông số</a>
                    </li>

                    <li class="nav-item p-b-10">
                        <a class="nav-link" data-toggle="tab" href="#reviews" role="tab">Đánh giá (<% out.print(new ReviewService().countReview(((Product) request.getAttribute("product")).getProductId())); %>)</a>
                    </li>
                </ul>

                <!-- Tab panes -->
                <div class="tab-content p-t-43">
                    <!-- - -->
                    <div class="tab-pane fade show active" id="description" role="tabpanel">
                        <div class="how-pos2 p-lr-15-md">
                            <p class="stext-102 cl6">
                                ${product.productDescription}
                            </p>
                        </div>
                    </div>

                    <!-- - -->
                    <div class="tab-pane fade" id="information" role="tabpanel">
                        <div class="row">
                            <div class="col-sm-10 col-md-8 col-lg-6 m-lr-auto">
                                <ul class="p-lr-28 p-lr-15-sm">
                                    <li class="flex-w flex-t p-b-7">
                                        <span class="stext-102 cl3 size-205">
                                            Khối lượng
                                        </span>

                                        <span class="stext-102 cl6 size-206">
                                            <%----%>
                                        </span>
                                    </li>

                                    <li class="flex-w flex-t p-b-7">
                                        <span class="stext-102 cl3 size-205">
                                            Kích thước
                                        </span>

                                        <span class="stext-102 cl6 size-206">
                                            <%----%>
                                        </span>
                                    </li>

                                    <li class="flex-w flex-t p-b-7">
                                        <span class="stext-102 cl3 size-205">
                                            Loại gỗ
                                        </span>

                                        <span class="stext-102 cl6 size-206">
                                            <%----%>
                                        </span>
                                    </li>

                                    <li class="flex-w flex-t p-b-7">
                                        <span class="stext-102 cl3 size-205">
                                            Màu sắc
                                        </span>

                                        <span class="stext-102 cl6 size-206">
                                            <%----%>
                                        </span>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <!-- - -->
                    <div class="tab-pane fade" id="reviews" role="tabpanel">
                        <div class="row">
                            <div class="col-sm-10 col-md-8 col-lg-6 m-lr-auto">
                                <div class="p-b-30 m-lr-15-sm">
                                    <!-- Review -->
                                    <div class="flex-w flex-t p-b-68">
                                        <div class="wrap-pic-s size-109 bor0 of-hidden m-r-18 m-t-6">
                                            <img src="${pageContext.request.contextPath}/assets/images/avatar-01.jpg" alt="AVATAR">
                                        </div>

                                        <div class="size-207">
                                            <div class="flex-w flex-sb-m p-b-17">
													<span class="mtext-107 cl2 p-r-20">
														Ariana Grande
													</span>

                                                <span class="fs-18 cl11">
														<i class="zmdi zmdi-star"></i>
														<i class="zmdi zmdi-star"></i>
														<i class="zmdi zmdi-star"></i>
														<i class="zmdi zmdi-star"></i>
														<i class="zmdi zmdi-star-half"></i>
													</span>
                                            </div>

                                            <p class="stext-102 cl6">
                                                Quod autem in homine praestantissimum atque optimum est, id deseruit. Apud ceteros autem philosophos
                                            </p>
                                        </div>
                                    </div>

                                    <!-- Add review -->
                                    <form class="w-full">
                                        <h5 class="mtext-108 cl2 p-b-7">
                                            Add a review
                                        </h5>

                                        <p class="stext-102 cl6">
                                            Your email address will not be published. Required fields are marked *
                                        </p>

                                        <div class="flex-w flex-m p-t-50 p-b-23">
												<span class="stext-102 cl3 m-r-16">
													Your Rating
												</span>

                                            <span class="wrap-rating fs-18 cl11 pointer">
													<i class="item-rating pointer zmdi zmdi-star-outline"></i>
													<i class="item-rating pointer zmdi zmdi-star-outline"></i>
													<i class="item-rating pointer zmdi zmdi-star-outline"></i>
													<i class="item-rating pointer zmdi zmdi-star-outline"></i>
													<i class="item-rating pointer zmdi zmdi-star-outline"></i>
                                                    <label>
                                                        <input class="dis-none" type="number" name="rating">
                                                    </label>
												</span>
                                        </div>

                                        <div class="row p-b-25">
                                            <div class="col-12 p-b-5">
                                                <label class="stext-102 cl3" for="review">Your review</label>
                                                <textarea class="size-110 bor8 stext-102 cl2 p-lr-20 p-tb-10" id="review" name="review"></textarea>
                                            </div>

                                            <div class="col-sm-6 p-b-5">
                                                <label class="stext-102 cl3" for="name">Name</label>
                                                <input class="size-111 bor8 stext-102 cl2 p-lr-20" id="name" type="text" name="name">
                                            </div>

                                            <div class="col-sm-6 p-b-5">
                                                <label class="stext-102 cl3" for="email">Email</label>
                                                <input class="size-111 bor8 stext-102 cl2 p-lr-20" id="email" type="text" name="email">
                                            </div>
                                        </div>

                                        <button class="flex-c-m stext-101 cl0 size-112 bg7 bor11 hov-btn3 p-lr-15 trans-04 m-b-10">
                                            Submit
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="bg6 flex-c-m flex-w size-302 m-t-73 p-tb-15">
        <span class="stext-107 cl6 p-lr-25">
            Tất cả thương hiệu có tên tuổi hàng đầu được Đông Phong kiểm duyệt và hợp tác mang đến cho người tiêu dùng chất lượng và giá thành tốt nhất.
        </span>
    </div>
</section>

<!-- Related Products -->
<section class="sec-relate-product bg0 p-t-45 p-b-105">
    <div class="container">
        <div class="p-b-45">
            <h3 class="ltext-106 cl5 txt-center">
                Sản Phẩm Liên Quan
            </h3>
        </div>

        <!-- Slide2 -->
        <div class="wrap-slick2">
            <div class="slick2">
                <jsp:useBean id="relatedProducts" scope="request" type="java.util.List"/>
                <c:forEach items="${relatedProducts}" var="relatedProduct">
                    <div class="item-slick2 p-l-15 p-r-15 p-t-15 p-b-15">
                        <div class="block2">
                            <div class="block2-pic hov-img0">
                                <%
                                    String reImage = imageService.getProReImage(((Product) pageContext.getAttribute("relatedProduct")).getProductId());
                                    request.setAttribute("reImage", reImage);
                                %>
                                <c:url value="/images/product-images?fname=${reImage}" var="productImg"/>
                                <img class="product-img" src="${productImg}" alt="Hình ảnh">

                                <a href="#" class="block2-btn flex-c-m stext-103 cl2 size-102 bg0 bor2 hov-btn1 p-lr-15 trans-04 js-show-modal">
                                    Xem Qua
                                </a>
                            </div>

                            <div class="block2-txt flex-w flex-t p-t-14">
                                <div class="block2-txt-child1 flex-col-l ">
                                    <a href="${pageContext.request.contextPath}/products/product-detail?id=${relatedProduct.productId}" class="product-name stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
                                        ${relatedProduct.productName}
                                    </a>
                                </div>
                                <form action="<c:url value="/wishlist/add"/>" method="get" class="block2-txt-child2 flex-r p-t-3">
                                    <!--Sign url-->
                                    <input type="hidden" class="input-add-item" name="forwardTo">
                                    <!--Sign product-->
                                    <input type="hidden" name="id" value="${product.productId}">

                                    <%
                                        WLItemService wlItemService = new WLItemService();
                                        Product relatedProduct = (Product) pageContext.getAttribute("relatedProduct");
                                        WishList wishList = (WishList) session.getAttribute("wishList");
                                        boolean existItem = wishList != null && wlItemService.checkExistItem(relatedProduct.getProductId(), wishList.getWishListId());
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
                                                    BigDecimal relatedProductPrice = new BigDecimal(relatedProduct.getProductPrice());
                                                    String showPrice = dongFormat.format(relatedProductPrice);
                                                    out.print("<td>" + showPrice + "</td>");
                                                %>
                                            </span>
                                        <%
                                            BigDecimal relatedProductCost = new BigDecimal(relatedProduct.getProductCost());
                                            String showCost = dongFormat.format(relatedProductCost);
                                        %>
                                        <c:if test="${relatedProduct.productCost != '0' && relatedProduct.productCost != relatedProduct.productPrice}">
                                                <span class="stext-105 cl3 product-cost">
                                                    <% out.print("<td>" + showCost + "</td>"); %>
                                                </span>
                                        </c:if>
                                    </div>
                                </div>
                                <%
                                    boolean b = relatedProductPrice.compareTo(relatedProductCost) < 0;
                                    request.setAttribute("b", b);

                                    BigDecimal percentage = new BigDecimal(0);
                                %>
                                <c:if test="${b}">
                                    <div class="block2-txt-child2 flex-r p-t-3">
                                        <span class="stext-105 cl3 product-sale-off">
                                            <%
                                                percentage = ((relatedProductCost.subtract(relatedProductPrice)).divide(relatedProductCost, 2, RoundingMode.HALF_UP)).multiply(new BigDecimal("100")).setScale(0, RoundingMode.UP);
                                                out.print("(-" + percentage + "%)");
                                            %>
                                        </span>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </div>
                    <!-- Modal -->
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
                                                        List<ProImage> relatedProductImages = imageService.getProImage(((Product) pageContext.getAttribute("relatedProduct")).getProductId());
                                                        request.setAttribute("relatedProductImages", relatedProductImages);
                                                    %>
                                                    <c:forEach items="${relatedProductImages}" var="image">
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
                                            <h4 class="mtext-105 cl2 js-name-detail p-b-14">${relatedProduct.productName}</h4>
                                            <span class="product-price mtext-106 cl2 m-r-16"><% out.print(showPrice); %></span>
                                            <span class="product-cost mtext-106 cl2 m-r-16"><% out.print(showCost); %></span>
                                            <span class="product-sale-off mtext-106 cl2"><% out.print("(-" + percentage + "%)"); %></span>
                                            <p class="stext-102 cl3 p-t-23">${relatedProduct.productDescription}</p>
                                            <!--  -->
                                            <div class="p-t-33">
                                                <div class="flex-w flex-r-m p-b-10">
                                                    <form action="<c:url value="/cart/add"/>" method="get" class="size-204 flex-w flex-m respon6-next">
                                                        <!--Sign url-->
                                                        <input type="hidden" class="input-add-item" name="forwardTo">
                                                        <!--Sign product-->
                                                        <input type="hidden" name="id" value="${relatedProduct.productId}">

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
                                                    <input type="hidden" name="id" value="${relatedProduct.productId}">

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
        </div>
    </div>
</section>

<!-- Footer -->
<jsp:include page="footer.jsp"/>

<label>
    <!--Kiểm tra đăng nhập-->
    <input id="checkAccount" type="hidden" value="<c:if test="${sessionScope.account == null}">null</c:if>"/>
</label>

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

</body>
</html>
