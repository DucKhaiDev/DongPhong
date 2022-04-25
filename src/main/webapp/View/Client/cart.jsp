<%@ page import="Entity.Product" %>
<%@ page import="Entity.CartItem" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.math.RoundingMode" %>
<%@ page import="Util.Constant" %>
<%--
  User: duckhaidev
  Date: 1/22/2022
  Time: 9:51 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="wrap-header-cart js-panel-cart">
    <div class="s-full js-hide-cart"></div>

    <div class="header-cart flex-col-l p-l-65 p-r-25">
        <div class="header-cart-title flex-w flex-sb-m p-b-8">
				<span class="mtext-103 cl2">
					Giỏ Hàng
				</span>

            <div class="fs-35 lh-10 cl2 p-lr-5 pointer hov-cl1 trans-04 js-hide-cart">
                <i class="zmdi zmdi-close"></i>
            </div>
        </div>

        <div class="header-cart-content flex-w js-pscroll">
            <%
                Locale vie = new Locale("vi", "VN");
                NumberFormat dongFormat = NumberFormat.getCurrencyInstance(vie);
                BigDecimal total = new BigDecimal(0);
            %>
            <ul class="header-cart-wrapitem w-full">
                <c:forEach items="${sessionScope.cartItems}" var="item">
                    <li class="header-cart-item flex-w flex-t m-b-12">
                        <%
                            Product product = ((CartItem) pageContext.getAttribute("item")).getProduct();
                            request.setAttribute("cart_product", product);
                        %>
                        <form action="<c:url value="/cart/remove"/>" method="get" class="header-cart-item-img">
                            <!--Id-->
                            <input type="hidden" name="id" value="${item.cartItemId}">
                            <!--Sign url-->
                            <input class="input-remove-item" type="hidden" name="forwardTo">
                            <%
                                String reImage = Constant.Service.PRO_IMAGE_SERVICE.getProReImage(product.getProductId());
                                request.setAttribute("cart_reImage", reImage);
                            %>
                            <c:url value="/images/product-images?fname=${cart_reImage}" var="imageUrl"/>
                            <img class="cart_image" src="${imageUrl}" alt="IMG">
                            <button class="btn-remove-item" type="submit">
                                <i class="fa fa-minus"></i>
                            </button>
                        </form>

                        <div class="header-cart-item-txt p-t-8">
                            <%
                                String productName = product.getProductName();
                                if (productName.length() > 24) {
                                    productName = productName.substring(0, 25).trim() + "...";
                                }
                            %>
                            <a href="${cart_product.productId}"
                               class="header-cart-item-name m-b-18 hov-cl1 trans-04 tooltip100"
                               data-tooltip="<% out.print(product.getProductName()); %>>">
                                <% out.print(productName); %>
                            </a>

                            <%
                                BigDecimal price = product.getProductPrice();
                                int productQuantity = ((CartItem) pageContext.getAttribute("item")).getQuantity();
                                total = total.add(price.multiply(new BigDecimal(productQuantity)));
                                BigDecimal cost = product.getProductCost();
                            %>

                            <div class="d-flex">
                                <span class="header-cart-item-info product-price">
								    <% out.print(dongFormat.format(price)); %>
							    </span>

                                <span class="header-cart-item-info product-quantity">
								    x <% out.print(productQuantity); %>
							    </span>
                            </div>


                            <div class="d-flex">
                                <c:if test="${cart_product.productCost != '0' && cart_product.productCost != cart_product.productPrice}">
                                    <span class="header-cart-item-info product-cost">
                                        <% out.print(dongFormat.format(cost)); %>
                                    </span>
                                </c:if>

                                <%
                                    boolean b = price.compareTo(cost) < 0;
                                    request.setAttribute("b", b);

                                    BigDecimal percentage;
                                %>
                                <c:if test="${b}">
                                    <span class="header-cart-item-info product-sale-off">
                                    <%
                                        percentage = ((cost.subtract(price)).divide(cost, 2, RoundingMode.HALF_UP)).multiply(new BigDecimal("100")).setScale(0, RoundingMode.UP);
                                        out.print("(-" + percentage + "%)");
                                    %>
                                </span>
                                </c:if>
                            </div>
                        </div>
                    </li>
                </c:forEach>
            </ul>

            <div class="w-full">
                <div class="header-cart-total w-full p-tb-40">
                    Tổng: <% out.print(dongFormat.format(total)); %>
                </div>

                <div class="header-cart-buttons flex-w w-full">
                    <a href="${pageContext.request.contextPath}/cart"
                       class="flex-c-m stext-101 cl0 size-107 bg3 bor2 hov-btn3 p-lr-15 trans-04 m-r-8 m-b-10">
                        Xem giỏ hàng
                    </a>

                    <a href="${pageContext.request.contextPath}/cart"
                       class="flex-c-m stext-101 cl0 size-107 bg3 bor2 hov-btn3 p-lr-15 trans-04 m-b-10">
                        Thanh toán
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
