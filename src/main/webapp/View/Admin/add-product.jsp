<%--
  User: duckhaidev
  Date: 2/19/2022
  Time: 11:21 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/assets" var="url" />
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Dong Phong</title>
    <!-- BOOTSTRAP STYLES-->
    <link href="${url}/css/bootstrap.css" rel="stylesheet"/>
    <!-- FONTAWESOME STYLES-->
    <link href="${url}/fonts/fontawesome-pro-5.15.4-web/css/all.min.css" rel="stylesheet" />
    <!-- CUSTOM STYLES-->
    <link href="${url}/css/custom.css" rel="stylesheet" />
    <!-- GOOGLE FONTS-->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
    <%---------------------------------------------------------------------------------------------%>
    <style>
        p.exist-id {
            font-size: 14px;
            line-height: 20px;
            padding-top: 14px;
            color: #FF0000;
        }
    </style>
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
                        <div class="panel-heading">Thêm mới sản phẩm</div>
                        <div class="panel-body">
                            <div class="row">
                                <form action="<c:url value="/admin/product/add"/>" method="post" enctype="multipart/form-data">
                                    <div class="row ml-2 mr-2">
                                        <div class="col-md-6">
                                            <div class="row mt-2">
                                                <div class="col-md-6 mb-3"><label for="productId" class="labels">ID</label><input id="productId" type="text" class="form-control" name="productId" maxlength="10" required="required"></div>
                                                <div class="col-md-6 mb-3"><label class="labels"></label><p class="exist-id">${requestScope.existId}</p></div>
                                                <div class="col-md-12 mb-3"><label for="productName" class="labels">Tên sản phẩm</label><input id="productName" type="text" class="form-control" name="productName" maxlength="255" required="required"></div>
                                                <div class="col-md-12 mb-3">
                                                    <label for="productDescription" class="labels">Mô tả sản phẩm</label>
                                                    <br>
                                                    <textarea name="productDescription" id="productDescription" class="form-control" cols="50" rows="5" maxlength="2000"></textarea>
                                                </div>
                                                <div class="col-md-6 mb-3"><label for="productQuantity" class="labels">Số lượng tồn kho</label><input id="productQuantity" type="number" class="form-control" name="productQuantity" min="0" max="<% out.print(Integer.MAX_VALUE); %>" step="1" placeholder="0"></div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-6 mb-3"><label for="productPrice" class="labels">Giá bán (VNĐ)</label><input id="productPrice" type="number" class="form-control" name="productPrice" min="0" max="9999999999" step="0.01" placeholder="0"></div>
                                                <div class="col-md-6 mb-3"><label for="productCost" class="labels">Giá gốc (VNĐ)</label><input id="productCost" type="number" class="form-control" name="productCost" min="0" max="9999999999" step="0.01" placeholder="0"></div>
                                                <div class="col-md-6 mb-3">
                                                    <label for="category" class="labels">Loại sản phẩm</label>
                                                    <div class="checkbox pl-0">
                                                        <select id="category" name="category" class="w-50">
                                                            <c:forEach items="${categories}" var="category">
                                                                <option value="${category.categoryId}">${category.categoryName}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="col-md-6 mb-3">
                                                    <label for="brand" class="labels">Thương hiệu</label>
                                                    <div class="checkbox pl-0">
                                                        <select id="brand" name="brand" class="w-50">
                                                            <c:forEach items="${brands}" var="brand">
                                                                <option value="${brand.brandId}">${brand.brandName}</option>
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
                                                    <c:forEach begin="1" end="6" varStatus="loop">
                                                        <div class="col-md-6 mb-6">
                                                            <div class="mb-1" style="width: 150px; height: 150px">
                                                                <img class="bg-img-empty image-<c:out value="${loop.index}"/> mb-1" width="150px" height="150px" style="object-fit: cover;">
                                                            </div>
                                                            <input type="file" class="text-center mb-3 file-upload-<c:out value="${loop.index}"/>" name="image_<c:out value="${loop.index}"/>">
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
<script src="${url}/vendor/jquery/jquery-3.2.1.min.js"></script>
<!-- BOOTSTRAP SCRIPTS -->
<script src="${url}/vendor/bootstrap/js/bootstrap.min.js"></script>
<!-- METISMENU SCRIPTS -->
<script src="${url}/js/jquery.metisMenu.js"></script>
<!-- CUSTOM SCRIPTS -->
<script src="${url}/js/custom.js"></script>
<!--===============================================================================================-->
<script type="text/javascript">
    $(document).ready(function () {
        <c:forEach begin="1" end="6" varStatus="loop">
        var readURL_<c:out value="${loop.index}"/> = function (input) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $(<% out.print("\'.image-"); %>${loop.index}<% out.print("\'"); %>).attr('src', e.target.result);
            }
            reader.readAsDataURL(input.files[0]);
        }

        $(<% out.print("\'.file-upload-"); %>${loop.index}<% out.print("\'"); %>).on('change', function () {
            readURL_<c:out value="${loop.index}"/>(this);
        });
        </c:forEach>
    });
</script>
<!-- CK EDITOR -->
<script src="${url}/js/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
    CKEDITOR.replace('productDescription');
</script>

</body>
</html>
