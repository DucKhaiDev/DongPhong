<%@ page import="java.math.BigDecimal" %>
<%@ page import="Entity.Product" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.text.NumberFormat" %>
<%--
  User: duckhaidev
  Date: 2/19/2022
  Time: 11:16 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="product" scope="request" type="Entity.Product"/>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Dong Phong</title>
    <!-- BOOTSTRAP STYLES-->
    <link href="${pageContext.request.contextPath}/assets/css/bootstrap.css" rel="stylesheet"/>
    <!-- FONTAWESOME STYLES-->
    <link href="${pageContext.request.contextPath}/assets/fonts/fontawesome-pro-5.15.4-web/css/all.min.css" rel="stylesheet" />
    <!-- CUSTOM STYLES-->
    <link href="${pageContext.request.contextPath}/assets/css/custom.css" rel="stylesheet" />
    <!-- GOOGLE FONTS-->
    <link href='https://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
</head>
<body>
<div id="wrapper">
    <jsp:include page="navtop.jsp"/>
    <!-- /. NAV TOP  -->

    <jsp:include page="navside.jsp"/>
    <!-- /. NAV SIDE  -->

    <div id="page-wrapper" >
        <div id="page-inner">
            <div class="row">
                <div class="col-md-12">
                    <h2>Quản Lý Sản Phẩm</h2>
                    <h5>Welcome Jhon Deo , Love to see you back. </h5>
                </div>
            </div>
            <!-- /. ROW  -->
            <hr />
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">Cập nhật sản phẩm</div>
                        <div class="panel-body">
                            <div class="row">
                                <form action="<c:url value="/admin/product/edit"/>" method="post" enctype="multipart/form-data">
                                    <div class="row ml-2 mr-2">
                                        <div class="col-md-6">
                                            <div class="row mt-2">
                                                <div class="col-md-6 mb-3"><label for="productId" class="labels">ID</label><input id="productId" type="text" class="form-control" name="productId" maxlength="10" placeholder="${product.productId}" readonly></div>
                                                <div class="col-md-12 mb-3"><label for="productName" class="labels">Tên sản phẩm</label><input id="productName" type="text" class="form-control" name="productName" maxlength="255" placeholder="${product.productName}"></div>
                                                <div class="col-md-12 mb-3">
                                                    <label for="productDescription" class="labels">Mô tả sản phẩm</label>
                                                    <br>
                                                    <textarea name="productDescription" id="productDescription" class="form-control" cols="50" rows="5" maxlength="2000">${product.productDescription}</textarea>
                                                </div>
                                                <div class="col-md-6 mb-3"><label for="productQuantity" class="labels">Số lượng tồn kho</label><input id="productQuantity" type="number" class="form-control" name="productQuantity" min="0" max="<% out.print(Integer.MAX_VALUE); %>" step="1" placeholder="${product.productQuantity}"></div>
                                            </div>
                                            <div class="row">
                                                <%
                                                    BigDecimal price = new BigDecimal(((Product) request.getAttribute("product")).getProductPrice());
                                                    BigDecimal cost = new BigDecimal(((Product) request.getAttribute("product")).getProductCost());
                                                    Locale vie = new Locale("vi", "VN");
                                                    NumberFormat dongFormat = NumberFormat.getCurrencyInstance(vie);
                                                    String showPrice = dongFormat.format(price);
                                                    request.setAttribute("showPrice", showPrice);
                                                    String showCost = dongFormat.format(cost);
                                                    request.setAttribute("showCost", showCost);
                                                %>
                                                <div class="col-md-6 mb-3">
                                                    <label for="productPrice" class="labels">Giá bán</label>
                                                    <input id="productPrice" type="number" class="form-control" name="productPrice" min="0" max="9999999999" step="0.01" <c:if test="${empty product.productPrice}">placeholder="0"</c:if> placeholder="${showPrice}">
                                                </div>
                                                <div class="col-md-6 mb-3">
                                                    <label for="productCost" class="labels">Giá gốc</label>
                                                    <input id="productCost" type="number" class="form-control" name="productCost" min="0" max="9999999999" step="0.01" <c:if test="${empty product.productCost}">placeholder="0"</c:if> placeholder="${showCost}">
                                                </div>
                                                <div class="col-md-6 mb-3">
                                                    <label for="category" class="labels">Loại sản phẩm</label>
                                                    <div class="checkbox pl-0">
                                                        <select id="category" name="category" class="w-50">
                                                            <jsp:useBean id="categories" scope="request" type="java.util.List"/>
                                                            <c:forEach items="${categories}" var="category">
                                                                <c:choose>
                                                                    <c:when test="${product.category.categoryId == category.categoryId}">
                                                                        <option value="${category.categoryId}" selected="selected">${category.categoryName}</option>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <option value="${category.categoryId}">${category.categoryName}</option>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="col-md-6 mb-3">
                                                    <label for="brand" class="labels">Thương hiệu</label>
                                                    <div class="checkbox pl-0">
                                                        <select id="brand" name="brand" class="w-50">
                                                            <jsp:useBean id="brands" scope="request" type="java.util.List"/>
                                                            <c:forEach items="${brands}" var="brand">
                                                                <c:choose>
                                                                    <c:when test="${product.brand.brandId == brand.brandId}">
                                                                        <option value="${brand.brandId}" selected="selected">${brand.brandName}</option>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <option value="${brand.brandId}">${brand.brandName}</option>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="row mt-2">
                                                <div class="row">
                                                    <div class="col-md-12"><label class="labels">Hình ảnh sản phẩm (Tối đa 6 ảnh)</label></div>
                                                </div>
                                                <div class="row mt-5">
                                                    <jsp:useBean id="images" scope="request" type="java.util.List"/>
                                                    <c:forEach items="${images}" var="image" varStatus="loop">
                                                        <div class="col-md-6 mb-6">
                                                            <c:url value="/images/product-images?fname=${image.imageName}" var="imageUrl"/>
                                                            <div class="mb-1" style="width: 150px; height: 150px">
                                                                <img class="image-<c:out value="${loop.index + 1}"/>" width="150px" height="150px" style="object-fit: cover;" src="${imageUrl}" alt="Hình ảnh sản phẩm">
                                                            </div>
                                                            <input type="file" class="text-center mb-3 file-upload-<c:out value="${loop.index + 1}"/>" name="<c:out value="${loop.index + 1}"/>">
                                                        </div>
                                                    </c:forEach>
                                                    <c:forEach begin="${images.size() + 1}" end="6" varStatus="loop">
                                                        <div class="col-md-6 mb-6">
                                                            <div class="bg-img-empty mb-1" style="width: 150px; height: 150px">
                                                                <img class="product-images image-<c:out value="${loop.index}"/>" width="150px" height="150px" style="object-fit: cover; display: none" src="" alt="">
                                                            </div>
                                                            <input type="file" class="text-center mb-3 file-upload-<c:out value="${loop.index}"/>" name="<c:out value="${loop.index}"/>">
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row mt-5 ml-2 mr-2">
                                        <div class="mt-5 text-center col-md-6"><button class="btn btn-primary ct-button float-right mr-5" type="submit"><i class="fa fa-check"></i>&nbsp;Đồng ý</button></div>
                                        <div class="mt-5 text-center col-md-6">
                                            <button class="btn btn-primary ct-button float-left ml-5" type="reset"><i class="fa fa-undo-alt"></i>&nbsp;Nhập lại</button>
                                            <div class="float-right pr-3">
                                                <a href="${pageContext.request.contextPath}/admin/product" class="btn btn-primary ct-button">
                                                    <i class="fa fa-angle-left"></i>&nbsp;Quay lại
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /. PAGE INNER  -->
    </div>
    <!-- /. PAGE WRAPPER  -->
</div>
<!-- /. WRAPPER  -->
<!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
<!-- JQUERY SCRIPTS -->
<script src="${pageContext.request.contextPath}/assets/vendor/jquery/jquery-3.2.1.min.js"></script>
<!-- BOOTSTRAP SCRIPTS -->
<script src="${pageContext.request.contextPath}/assets/vendor/bootstrap/js/bootstrap.min.js"></script>
<!-- METISMENU SCRIPTS -->
<script src="${pageContext.request.contextPath}/assets/js/jquery.metisMenu.js"></script>
<!-- CUSTOM SCRIPTS -->
<script src="${pageContext.request.contextPath}/assets/js/custom.js"></script>
<!--===============================================================================================-->
<script type="text/javascript">
    $(document).ready(function () {
        <c:forEach begin="1" end="6" varStatus="loop">
        const readURL_<c:out value="${loop.index}"/> = function (input) {
            const reader = new FileReader();
            reader.onload = function (e) {
                $(<% out.print("'.image-"); %>${loop.index}<% out.print("'"); %>).attr('src', e.target.result);
            }
            reader.readAsDataURL(input.files[0]);
        };

        $(<% out.print("'.file-upload-"); %>${loop.index}<% out.print("'"); %>).on('change', function () {
                readURL_<c:out value="${loop.index}"/>(this);
            });
        </c:forEach>
    });
</script>
<!-- CK EDITOR -->
<script src="${pageContext.request.contextPath}/assets/js/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
    CKEDITOR.replace('productDescription');
</script>
<!--===============================================================================================-->
<script>
    $(function () {
        $('.product-images').each(function () {
            if ($(this).attr('src') === '#') {
                $(this).hide();
            }

            $(this).on('load', function () {
                $(this).show();
            });
        });
    });
</script>

</body>
</html>
