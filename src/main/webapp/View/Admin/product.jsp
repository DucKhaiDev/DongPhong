<%@ page import="Entity.Product" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="Util.Constant" %>
<%--
  User: duckhaidev
  Date: 2/17/2022
  Time: 9:37 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Dong Phong</title>
    <!-- BOOTSTRAP STYLES-->
    <link href="${pageContext.request.contextPath}/assets/css/bootstrap.css" rel="stylesheet"/>
    <!-- FONTAWESOME STYLES-->
    <link href="${pageContext.request.contextPath}/assets/fonts/fontawesome-pro-5.15.4-web/css/all.min.css"
          rel="stylesheet"/>
    <!-- MORRIS CHART STYLES-->

    <!-- CUSTOM STYLES-->
    <link href="${pageContext.request.contextPath}/assets/css/custom.css" rel="stylesheet"/>
    <!-- GOOGLE FONTS-->
    <link href='https://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'/>
    <!-- TABLE STYLES-->
    <link href="${pageContext.request.contextPath}/assets/js/dataTables/dataTables.bootstrap.css" rel="stylesheet"/>
</head>
<body>
<div id="wrapper">
    <jsp:include page="navtop.jsp"/>
    <!-- /. NAV TOP  -->

    <jsp:include page="navside.jsp"/>
    <!-- /. NAV SIDE  -->

    <div id="page-wrapper">
        <div id="page-inner">
            <div class="row">
                <div class="col-md-12">
                    <h2>Quản Lý Sản Phẩm</h2>
                    <div class="row">
                        <div class="col-md-12"><h5>Chào mừng ${sessionScope.displayName}, rất vui được gặp lại
                            bạn. </h5></div>
                    </div>

                </div>
            </div>
            <!-- /. ROW  -->
            <hr/>
            <div class="row">
                <div class="col-md-12 mb-3">
                    <a href="${pageContext.request.contextPath}/admin/product/add"
                       class="btn btn-primary ct-button float-right">
                        <i class="fa fa-plus"></i>&nbsp;Thêm sản phẩm
                    </a>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <!-- Advanced Tables -->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Danh mục sản phẩm
                        </div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="dataTable">
                                    <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th>ID</th>
                                        <th>Hình ảnh</th>
                                        <th>Tên sản phẩm</th>
                                        <th>Giá bán</th>
                                        <th>Loại sản phẩm</th>
                                        <th>Thương hiệu</th>
                                        <th>Tác vụ</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:set var="number" value="0"/>
                                    <jsp:useBean id="products" scope="request" type="java.util.List"/>
                                    <c:forEach items="${products}" var="product">
                                        <tr class="odd">
                                            <td>${number = number + 1}</td>
                                            <td>${product.productId}</td>
                                            <%
                                                String reImage = Constant.Service.PRO_IMAGE_SERVICE.getProReImage(((Product) pageContext.getAttribute("product")).getProductId());
                                                request.setAttribute("reImage", reImage);
                                            %>
                                            <c:url value="/images/product-images?fname=${reImage}" var="imageUrl"/>
                                            <c:choose>
                                                <c:when test="${reImage == null}">
                                                    <td><i>NULL</i></td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td><img width="50" height="50" src="${imageUrl}"
                                                             style="object-fit: cover;" alt="Hình ảnh"></td>
                                                </c:otherwise>
                                            </c:choose>
                                            <td>${product.productName}</td>
                                            <%
                                                BigDecimal price = ((Product) pageContext.getAttribute("product")).getProductPrice();
                                                Locale vie = new Locale("vi", "VN");
                                                NumberFormat dongFormat = NumberFormat.getCurrencyInstance(vie);
                                                String showPrice = dongFormat.format(price);
                                                out.print("<td>" + showPrice + "</td>");
                                            %>
                                            <td>${product.category.categoryName}</td>
                                            <td>${product.brand.brandName}</td>
                                            <td>
                                                <a href="<c:url value="/products/product-detail?id=${product.productId}"/>"
                                                   class="text-center">Chi tiết</a>&nbsp;|&nbsp;
                                                <a href="<c:url value="/admin/product/edit?id=${product.productId}"/>"
                                                   class="text-center">Sửa</a>&nbsp;|&nbsp;
                                                <a href="<c:url value="/admin/product/delete?id=${product.productId}"/>"
                                                   class="text-center">Xóa</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>

                        </div>
                    </div>
                    <!--End Advanced Tables -->
                </div>
            </div>
            <!-- /. ROW  -->
        </div>

    </div>
    <!-- /. PAGE INNER  -->
</div>
<!-- /. PAGE WRAPPER  -->
<!-- /. WRAPPER  -->
<!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
<!-- JQUERY SCRIPTS -->
<script src="${pageContext.request.contextPath}/assets/vendor/jquery/jquery-3.2.1.min.js"></script>
<!-- BOOTSTRAP SCRIPTS -->
<script src="${pageContext.request.contextPath}/assets/vendor/bootstrap/js/bootstrap.min.js"></script>
<!-- METISMENU SCRIPTS -->
<script src="${pageContext.request.contextPath}/assets/js/jquery.metisMenu.js"></script>
<!-- DATA TABLE SCRIPTS -->
<script src="${pageContext.request.contextPath}/assets/js/dataTables/jquery.dataTables.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/dataTables/dataTables.bootstrap.js"></script>
<script>
    $(document).ready(function () {
        $('#dataTable').dataTable({
            "pagingType": "full_numbers"
        });
    });
</script>
<!-- CUSTOM SCRIPTS -->
<script src="${pageContext.request.contextPath}/assets/js/custom.js"></script>

</body>
</html>
