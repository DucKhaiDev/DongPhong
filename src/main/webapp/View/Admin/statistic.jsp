<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="Entity.Product" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="Util.Constant" %>
<%--
  User: duckhaidev
  Date: 4/21/2022
  Time: 5:01 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Thống Kê - Đồ gỗ Cao cấp Đông Phong</title>
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/images/icons/icon-logo.png"/>
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
    <%---------------------------------------------------------------------------------------------%>
    <style>
        .table > tbody > tr > td > p {
            font-size: 14px;
            line-height: 20px;
            padding-top: 0;
            margin: 0;
        }
    </style>
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
                    <h2>Báo Cáo & Thống Kê</h2>
                    <div class="row">
                        <div class="col-md-12"><h5>Chào mừng ${sessionScope.displayName}, rất vui được gặp lại
                            bạn. </h5></div>
                    </div>
                </div>
            </div>
            <!-- /. ROW  -->
            <hr/>
            <div class="row">
                <div class="col-md-12">
                    <!-- Advanced Tables -->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Thống Kê
                        </div>
                        <div class="row panel-body ml-0 mr-0">
                            <div class="col-md-12 bor21 mb-3">
                                <h4 class="text-uppercase mb-3" style="color: orangered">Top sản phẩm bán chạy nhất</h4>
                                <form action="<c:url value="/admin/statistic"/>" method="get"
                                      class="row d-flex justify-content-center mt-3 mb-3">
                                    <div class="col-md-2 mb-3">
                                        <label for="fromDate" class="labels">Từ ngày</label>
                                        <input id="fromDate" type="date"
                                               value="<% out.print(new SimpleDateFormat("yyyy-MM-dd").format((Timestamp) request.getAttribute("fromDate"))); %>"
                                               class="form-control text-center" name="fromDate">
                                    </div>
                                    <div class="col-md-2 mb-3">
                                        <label for="toDate" class="labels">Đến ngày</label>
                                        <input id="toDate" type="date"
                                               value="<% out.print(new SimpleDateFormat("yyyy-MM-dd").format((Timestamp) request.getAttribute("toDate"))); %>"
                                               class="form-control text-center" name="toDate">
                                    </div>
                                    <div class="col-md-2 mb-3 d-flex align-items-end">
                                        <button type="submit" class="btn btn-primary ct-button w-100">
                                            Thống Kê
                                        </button>
                                    </div>
                                </form>

                                <div class="table-responsive">
                                    <table class="table table-striped table-bordered table-hover" id="table-1">
                                        <thead>
                                        <tr>
                                            <th>STT</th>
                                            <th>ID</th>
                                            <th>Hình ảnh</th>
                                            <th>Tên sản phẩm</th>
                                            <th>Giá bán</th>
                                            <th>Loại sản phẩm</th>
                                            <th>Thương hiệu</th>
                                            <th>Số lượng đã bán</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:set var="number" value="0"/>
                                        <jsp:useBean id="bestseller" scope="request" type="java.util.Map"/>
                                        <c:forEach items="${bestseller}" var="entry">
                                            <tr class="odd">
                                                <td>${number = number + 1}</td>
                                                <td>${entry.key.productId}</td>
                                                <c:set var="product" value="${entry.key}"/>
                                                <%
                                                    Product product = (Product) pageContext.getAttribute("product");
                                                    String reImage = Constant.Service.PRO_IMAGE_SERVICE.getProReImage(product.getProductId());
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
                                                <td>${entry.key.productName}</td>
                                                <%
                                                    BigDecimal price = product.getProductPrice();
                                                %>
                                                <td><% out.print(Constant.NF_DONG.format(price)); %></td>
                                                <td>${entry.key.category.categoryName}</td>
                                                <td>${entry.key.brand.brandName}</td>
                                                <td>${entry.value}</td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div class="col-md-12 bor21 mb-3">
                                <h4 class="text-uppercase mb-3" style="color: orangered">Top sản phẩm được yêu thích
                                    nhất</h4>
                                <div class="table-responsive">
                                    <table class="table table-striped table-bordered table-hover" id="table-2">
                                        <thead>
                                        <tr>
                                            <th>STT</th>
                                            <th>ID</th>
                                            <th>Hình ảnh</th>
                                            <th>Tên sản phẩm</th>
                                            <th>Giá bán</th>
                                            <th>Loại sản phẩm</th>
                                            <th>Thương hiệu</th>
                                            <th>Số lượt yêu thích</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:set var="number" value="0"/>
                                        <jsp:useBean id="favourite" scope="request" type="java.util.Map"/>
                                        <c:forEach items="${favourite}" var="entry">
                                            <tr class="odd">
                                                <td>${number = number + 1}</td>
                                                <td>${entry.key.productId}</td>
                                                <c:set var="product" value="${entry.key}"/>
                                                <%
                                                    Product product = (Product) pageContext.getAttribute("product");
                                                    String reImage = Constant.Service.PRO_IMAGE_SERVICE.getProReImage(product.getProductId());
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
                                                <td>${entry.key.productName}</td>
                                                <%
                                                    BigDecimal price = product.getProductPrice();
                                                %>
                                                <td><% out.print(Constant.NF_DONG.format(price)); %></td>
                                                <td>${entry.key.category.categoryName}</td>
                                                <td>${entry.key.brand.brandName}</td>
                                                <td>${entry.value}</td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div class="col-md-12 bor21">
                                <h4 class="text-uppercase mb-3" style="color: orangered">Mức độ đánh giá các sản
                                    phẩm</h4>
                                <div class="table-responsive">
                                    <table class="table table-striped table-bordered table-hover" id="table-3">
                                        <thead>
                                        <tr>
                                            <th>STT</th>
                                            <th>ID</th>
                                            <th>Hình ảnh</th>
                                            <th>Tên sản phẩm</th>
                                            <th>Giá bán</th>
                                            <th>Loại sản phẩm</th>
                                            <th>Thương hiệu</th>
                                            <th>Điểm đánh giá</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:set var="number" value="0"/>
                                        <jsp:useBean id="highestRated" scope="request" type="java.util.Map"/>
                                        <c:forEach items="${highestRated}" var="entry">
                                            <tr class="odd">
                                                <td>${number = number + 1}</td>
                                                <td>${entry.key.productId}</td>
                                                <c:set var="product" value="${entry.key}"/>
                                                <%
                                                    Product product = (Product) pageContext.getAttribute("product");
                                                    String reImage = Constant.Service.PRO_IMAGE_SERVICE.getProReImage(product.getProductId());
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
                                                <td>${entry.key.productName}</td>
                                                <%
                                                    BigDecimal price = product.getProductPrice();
                                                %>
                                                <td><% out.print(Constant.NF_DONG.format(price)); %></td>
                                                <td>${entry.key.category.categoryName}</td>
                                                <td>${entry.key.brand.brandName}</td>
                                                <td>${entry.value}/5.0</td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
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
        $('#table-1').dataTable({
            "pagingType": "full_numbers"
        });

        $('#table-2').dataTable({
            "pagingType": "full_numbers"
        });

        $('#table-3').dataTable({
            "pagingType": "full_numbers"
        });
    });
</script>
<!-- CUSTOM SCRIPTS -->
<script src="${pageContext.request.contextPath}/assets/js/custom.js"></script>

</body>
</html>
