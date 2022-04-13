<%@ page import="Services.deploy.ProImageService" %>
<%@ page import="Entity.WLItem" %>
<%@ page import="Entity.Product" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.math.RoundingMode" %>
<%--
  User: duckhaidev
  Date: 3/9/2022
  Time: 4:11 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="wrap-header-cart js-panel-wishList">
    <div class="s-full js-hide-wishList"></div>

    <div class="header-cart flex-col-l p-l-65 p-r-25">
        <div class="header-cart-title flex-w flex-sb-m p-b-8">
				<span class="mtext-103 cl2">
					Danh Sách Yêu Thích
				</span>

            <div class="fs-35 lh-10 cl2 p-lr-5 pointer hov-cl1 trans-04 js-hide-wishList">
                <i class="zmdi zmdi-close"></i>
            </div>
        </div>

        <div class="header-cart-content flex-w js-pscroll">
            <ul class="header-cart-wrapitem w-full">
                <c:forEach items="${sessionScope.wlItems}" var="item">
                    <li class="header-cart-item flex-w flex-t m-b-12">
                        <%
                            ProImageService imageService = new ProImageService();
                            Product product = ((WLItem) pageContext.getAttribute("item")).getProduct();
                            request.setAttribute("wl_product", product);
                        %>
                        <form action="<c:url value="/wishlist/remove"/>" method="get" class="header-cart-item-img">
                            <!--Id-->
                            <input type="hidden" name="id" value="${item.wlItemId}">
                            <!--Sign url-->
                            <input class="input-remove-item" type="hidden" name="forwardTo">
                            <%
                                String reImage = imageService.getProReImage(product.getProductId());
                                request.setAttribute("wl_reImage", reImage);
                            %>
                            <c:url value="/images/product-images?fname=${wl_reImage}" var="imageUrl"/>
                            <img class="wl_image" src="${imageUrl}" alt="IMG">
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
                            <a href="#" class="header-cart-item-name m-b-18 hov-cl1 trans-04 tooltip100" data-tooltip="<% out.print(product.getProductName()); %>>">
                                <% out.print(productName); %>
                            </a>

                            <%
                                Locale vie = new Locale("vi", "VN");
                                NumberFormat dongFormat = NumberFormat.getCurrencyInstance(vie);
                                BigDecimal price = product.getProductPrice();
                                BigDecimal cost = product.getProductCost();
                            %>

                            <span class="header-cart-item-info product-price">
								<% out.print(dongFormat.format(price)); %>
							</span>

                            <div class="d-flex">
                                <c:if test="${wl_product.productCost != '0' && wl_product.productCost != wl_product.productPrice}">
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
        </div>
    </div>
</div>
