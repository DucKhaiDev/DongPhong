<%@ page import="java.math.BigDecimal" %>
<%@ page import="Entity.CartItem" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="Entity.Order" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="Util.Constant" %>
<%--
  User: duckhaidev
  Date: 4/3/2022
  Time: 4:29 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h5 class="mb-3 text-start">Đơn hàng #${sessionScope.order_rv.orderId}</h5>
<div class="bor21 p-3">
    <div class="row d-flex mb-3">
        <div class="col-md-12"><span class="float-right">
            <%
                Order order = (Order) session.getAttribute("order_rv");
                if (order != null) {
                    Timestamp timestamp = order.getOrderDate();
                    Date date = new Date();
                    date.setTime(timestamp.getTime());
                    out.print(new SimpleDateFormat("HH:mm:ss dd/MM/yyyy").format(date));
                }
            %>
        </span></div>
    </div>
    <div class="bor20 p-3 mb-3">
        <div class="mb-3 text-center text-uppercase">Thông tin giao hàng</div>
        <div class="row d-flex justify-content-center">
            <div class="col-md-6 mb-3"><label for="fullName" class="labels">Họ và tên</label><input id="fullName"
                                                                                                    name="fullName"
                                                                                                    type="text"
                                                                                                    class="form-control"
                                                                                                    value="${sessionScope.order_rv.recipientName}"
                                                                                                    readonly></div>
            <div class="col-md-6 mb-3"><label for="phone" class="labels">Số điện thoại</label><input id="phone"
                                                                                                     name="phone"
                                                                                                     type="text"
                                                                                                     class="form-control"
                                                                                                     value="${sessionScope.order_rv.recipientPhone}"
                                                                                                     readonly></div>
            <div class="col-md-12 mb-3"><label for="recaddress" class="labels">Địa chỉ</label><input type="text"
                                                                                                     id="recaddress"
                                                                                                     class="form-control"
                                                                                                     value="${sessionScope.order_rv.recipientAddress}"
                                                                                                     readonly></div>
        </div>
    </div>
    <div class="bor20 p-3 mb-3">
        <div class="mb-3 text-center text-uppercase">Phương thức vận chuyển</div>
        <div class="row d-flex mb-2 align-items-center">
            <div class="col-md-2"><label for="ip1" class="labels text-nowrap">Đơn vị vận chuyển:&nbsp;</label></div>
            <div class="col-md-2"><input id="ip1" type="text" class="form-control w-fit-content text-center"
                                         value="Đông Phong" readonly></div>
        </div>
        <div class="row d-flex mb-3 align-items-center">
            <div class="col-md-2"><label for="ip2" class="labels text-nowrap">Thời gian giao hàng dự kiến:&nbsp;</label>
            </div>
            <div class="col-md-2"><input id="ip2" type="text" class="form-control w-fit-content text-center"
                                         value="${sessionScope.order_rv.recipientDate} <c:if test="${sessionScope.order_rv.recipientDate == null}">Chưa xác định</c:if>"
                                         readonly></div>
        </div>
    </div>
    <div class="bor20 p-3 mb-3">
        <div class="mb-3 text-center text-uppercase">Chi tiết đơn hàng</div>
        <%
            Locale vie = new Locale("vi", "VN");
            NumberFormat dongFormat = NumberFormat.getCurrencyInstance(vie);
        %>
        <div class="row">
            <div class="col-md-12 mb-5">
                <div class="table-responsive">
                    <table class="table table-striped table-bordered table-hover" id="dataTable">
                        <thead>
                        <tr>
                            <th>STT</th>
                            <th>Mã sản phẩm</th>
                            <th>Hình ảnh</th>
                            <th>Tên sản phẩm</th>
                            <th>Giá tiền</th>
                            <th>Số lượng</th>
                            <th>Tổng tiền</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:set var="count" value="0"/>
                        <c:forEach items="${sessionScope.cartItems_rv}" var="item">
                            <tr class="odd">
                                <td>${count = count + 1}</td>
                                <td>${item.product.productId}</td>
                                <%
                                    String reImage = Constant.Service.PRO_IMAGE_SERVICE.getProReImage(((CartItem) pageContext.getAttribute("item")).getProduct().getProductId());
                                    request.setAttribute("reImage", reImage);
                                %>
                                <c:url value="/images/product-images?fname=${reImage}" var="imageUrl"/>
                                <c:choose>
                                    <c:when test="${reImage == null}">
                                        <td><i>NULL</i></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td><img width="50" height="50" src="${imageUrl}" style="object-fit: cover;"
                                                 alt="Hình ảnh"></td>
                                    </c:otherwise>
                                </c:choose>
                                <td>${item.product.productName}</td>
                                <td>
                                    <%
                                        BigDecimal price = ((CartItem) pageContext.getAttribute("item")).getProduct().getProductPrice();
                                        out.print(dongFormat.format(price));
                                    %>
                                </td>
                                <td>${item.quantity}</td>
                                <td>
                                    <%
                                        BigDecimal value = ((CartItem) pageContext.getAttribute("item")).getValue();
                                        out.print(dongFormat.format(value));
                                    %>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <%
                BigDecimal subTotal = new BigDecimal(0);
                BigDecimal discount = new BigDecimal(0);
                BigDecimal tax = new BigDecimal(0);
                BigDecimal shipping = new BigDecimal(0);
                BigDecimal total = new BigDecimal(0);

                if (order != null) {
                    subTotal = order.getOrderSubTotal();
                    discount = order.getOrderDiscount();
                    tax = order.getOrderTax();
                    shipping = order.getOrderShipping();
                    total = order.getOrderTotal();
                }
            %>
            <div class="col-md-12 mb-2">
                <label class="labels d-flex text-nowrap align-items-center float-right">Tổng tiền:&nbsp;<input
                        type="text" class="form-control w-fit-content text-center"
                        value="<% out.print(dongFormat.format(subTotal)); %>" readonly></label>
            </div>
            <div class="col-md-12 mb-2">
                <label class="labels d-flex text-nowrap align-items-center float-right">Giảm giá:&nbsp;<input
                        type="text" class="form-control w-fit-content text-center"
                        value="<% out.print(dongFormat.format(discount)); %>" readonly></label>
            </div>
            <div class="col-md-12 mb-2">
                <label class="labels d-flex text-nowrap align-items-center float-right">Thuế (8%):&nbsp;<input
                        type="text" class="form-control w-fit-content text-center"
                        value="<% out.print(dongFormat.format(tax)); %>" readonly></label>
            </div>
            <div class="col-md-12 mb-2">
                <label class="labels d-flex text-nowrap align-items-center float-right">Phí vận chuyển:&nbsp;<input
                        type="text" class="form-control w-fit-content text-center"
                        value="<% out.print(dongFormat.format(shipping)); %>" readonly></label>
            </div>
            <div class="col-md-12 mb-2">
                <label class="labels d-flex text-nowrap align-items-center float-right"><strong>Thành tiền:</strong>&nbsp;<input
                        type="text" class="form-control w-fit-content text-center product-price"
                        value="<% out.print(dongFormat.format(total)); %>" readonly></label>
            </div>
        </div>
    </div>
</div>