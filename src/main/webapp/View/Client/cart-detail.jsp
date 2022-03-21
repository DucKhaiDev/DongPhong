<%@ page import="Services.deploy.ProImageService" %>
<%@ page import="Entity.Product" %>
<%@ page import="Entity.CartItem" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="Tools.CalculateShipping" %>
<%--
  Author: is2vi
  Date: 1/11/2022
  Time: 5:44 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Giỏ hàng</title>
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

<!-- WishList -->
<jsp:include page="wishList.jsp"/>

<!-- breadcrumb -->
<div class="container">
    <div class="bread-crumb flex-w p-l-25 p-r-15 p-t-30 p-lr-0-lg">
        <a href="${pageContext.request.contextPath}" class="stext-109 cl8 hov-cl1 trans-04 font-size-15">
            Trang chủ
            <i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i>
        </a>

        <span class="stext-109 cl4 font-size-15">
            Giỏ hàng
        </span>
    </div>
</div>

<!-- Shoping Cart -->
<div class="bg0 p-t-75 p-b-85">
    <div class="container">
        <div class="row">
            <div class="col-lg-10 col-xl-7 m-lr-auto m-b-50">
                <div class="m-l-25 m-r--38 m-lr-0-xl">
                    <div class="wrap-table-shopping-cart">
                        <table class="table-shopping-cart">
                            <tr class="table_head">
                                <th class="column-1">Hình ảnh</th>
                                <th class="column-2">Tên sản phẩm</th>
                                <th class="column-3">Giá</th>
                                <th class="column-4">Số lượng</th>
                                <th class="column-5">Tổng</th>
                            </tr>

                            <%
                                Locale vie = new Locale("vi", "VN");
                                NumberFormat dongFormat = NumberFormat.getCurrencyInstance(vie);
                                BigDecimal total = new BigDecimal(0);
                            %>
                            <c:forEach items="${sessionScope.cartItems}" var="item" varStatus="loop">
                                <c:set var="index" value="${loop.index + 1}"/>
                                <tr class="table_row">
                                    <td class="column-1">
                                        <div class="how-itemcart1">
                                            <%
                                                ProImageService imageService = new ProImageService();
                                                Product product = ((CartItem) pageContext.getAttribute("item")).getProduct();
                                                request.setAttribute("cart_product", product);
                                            %>
                                            <div class="header-cart-item-img">
                                                <%
                                                    String reImage = imageService.getProReImage(product.getProductId());
                                                    request.setAttribute("cart_reImage", reImage);
                                                %>
                                                <c:url value="/images/product-images?fname=${cart_reImage}" var="imageUrl"/>
                                                <img class="cart_image" src="${imageUrl}" alt="IMG">
                                                <a href="${pageContext.request.contextPath}/cart/remove?id=${item.cartItemId}&forwardTo=${pageContext.request.contextPath}/cart" class="btn-remove-item">
                                                    <i class="fa fa-minus"></i>
                                                </a>
                                            </div>
                                        </div>
                                    </td>

                                    <td class="column-2">
                                        <a href="${pageContext.request.contextPath}/products/product-detail?id=${cart_product.productId}" class="header-cart-item-name m-b-18 hov-cl1 trans-04">
                                            ${cart_product.productName}
                                        </a>
                                    </td>

                                    <td class="column-3">
                                        <%
                                            BigDecimal price = new BigDecimal(product.getProductPrice());
                                            int productQuantity = ((CartItem) pageContext.getAttribute("item")).getQuantity();
                                            total = total.add(price.multiply(new BigDecimal(productQuantity)));
                                            out.print(dongFormat.format(price));
                                        %>
                                    </td>

                                    <td class="column-4">
                                        <div class="wrap-num-product flex-w m-l-auto m-r-0">
                                            <div class="btn-num-product-down cl8 hov-btn3 trans-04 flex-c-m">
                                                <i class="fs-16 zmdi zmdi-minus"></i>
                                            </div>
                                            <input class="mtext-104 cl3 txt-center num-product" type="number" name="num-product${index}" value="${item.quantity}">
                                            <div class="btn-num-product-up cl8 hov-btn3 trans-04 flex-c-m">
                                                <i class="fs-16 zmdi zmdi-plus"></i>
                                            </div>
                                        </div>
                                    </td>

                                    <td class="column-5">
                                        <% out.print(dongFormat.format(new BigDecimal(((CartItem) pageContext.getAttribute("item")).getValue()))); %>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>

                    <div class="flex-w flex-sb-m bor15 p-t-18 p-b-15 p-lr-40 p-lr-15-sm">
                        <div class="flex-w flex-m m-r-20 m-tb-5">
                            <label>
                                <input class="stext-104 cl2 plh4 size-117 bor13 p-lr-20 m-r-10 m-tb-5" type="text" name="coupon" placeholder="Mã giảm giá">
                            </label>

                            <div class="flex-c-m stext-101 cl2 size-118 bg8 bor13 hov-btn3 p-lr-15 trans-04 pointer m-tb-5">
                                Áp dụng
                            </div>
                        </div>

                        <div class="flex-c-m stext-101 cl2 size-119 bg8 bor13 hov-btn3 p-lr-15 trans-04 pointer m-tb-10">
                            Cập nhật giỏ hàng
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-sm-10 col-lg-7 col-xl-5 m-lr-auto m-b-50">
                <div class="bor10 p-lr-40 p-t-30 p-b-40 m-l-63 m-r-40 m-lr-0-xl p-lr-15-sm">
                    <h4 class="mtext-109 cl2 p-b-30">
                        Thông tin giỏ hàng
                    </h4>

                    <div class="flex-w flex-t bor12 p-b-13">
                        <div class="size-208">
                            <span class="stext-110 cl2">
                                Tổng giá trị:
                            </span>
                        </div>

                        <div class="size-209">
                            <span id="totalCart" class="mtext-110 cl2">
                                <% out.print(dongFormat.format(total)); %>
                            </span>
                        </div>
                    </div>

                    <div class="flex-w flex-t bor12 p-t-15 p-b-30">
                        <div class="size-208 w-full-ssm">
                            <span class="stext-110 cl2">
                                Giao hàng:
                            </span>
                        </div>

                        <div class="size-209 p-r-18 p-r-0-sm w-full-ssm">
                            <p class="stext-111 cl6 p-t-2">
                                Vui lòng chọn địa điểm nhận hàng.
                                <br>
                                (Miễn phí vận chuyển trong khu vực tỉnh Bắc Ninh)
                            </p>

                            <div class="p-t-15">
                                <span class="stext-112 cl8">
                                    Chi phí vận chuyển:
                                </span>

                                <select id="province" name="province" class="w-full bor8 bg0 m-b-12 m-t-9 p-1">
                                    <option value="0" selected hidden disabled>Tỉnh/Thành phố</option>
                                </select>
                                <select id="district" name="district" class="w-full bor8 bg0 m-b-12 m-t-9 p-1">
                                    <option value="0" selected hidden disabled>Quận/Huyện</option>
                                </select>
                                <select id="ward" name="ward" class="w-full bor8 bg0 m-b-12 m-t-9 p-1">
                                    <option value="0" selected hidden disabled>Phường/Xã</option>
                                </select>
                                <hr>
                                <div class="flex-w justify-content-center">
                                    <span id="shippingCost" class="mtext-110 cl2 m-b-12">
                                        <% out.print(dongFormat.format(0)); %>
                                    </span>
                                    <div id="btnUpdateTotal" class="flex-c-m stext-101 cl2 size-115 bg8 bor13 hov-btn3 p-lr-15 trans-04 pointer">
                                        Cập nhật
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>

                    <div class="flex-w flex-t p-t-27 p-b-33">
                        <div class="size-208">
                            <span class="mtext-101 cl2">
                                Total:
                            </span>
                        </div>

                        <div class="size-209 p-t-1">
                            <span class="mtext-110 cl2">
                                $79.65
                            </span>
                        </div>
                    </div>

                    <button class="flex-c-m stext-101 cl0 size-116 bg3 bor14 hov-btn3 p-lr-15 trans-04 pointer">
                        Tiến hành thanh toán
                    </button>
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
<script src="${pageContext.request.contextPath}/assets/vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
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
        }
    });
</script>

</body>
</html>