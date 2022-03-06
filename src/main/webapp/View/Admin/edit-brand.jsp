<%--
  User: duckhaidev
  Date: 2/24/2022
  Time: 12:54 AM
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
            <div class="row d-flex justify-content-center">
                <div class="col-md-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">Cập nhật thương hiệu</div>
                        <div class="panel-body">
                            <div class="row">
                                <form action="<c:url value="/admin/brand/edit"/>" method="post">
                                    <div class="row ml-1 mr-1">
                                        <div class="col-md-6 mb-3"><label class="labels">ID</label><input type="text" class="form-control" placeholder="${brand.brandId}" readonly></div>
                                        <div class="col-md-12 mb-3"><label class="labels">Thương hiệu</label><input type="text" class="form-control" name="brandName" maxlength="255" placeholder="${brand.brandName}"></div>
                                        <div class="col-md-12">
                                            <label class="labels">Mô tả</label>
                                            <br>
                                            <textarea name="brandDescription" id="description" class="form-control" cols="50" rows="5" maxlength="2000">${brand.brandDescription}</textarea>
                                        </div>
                                    </div>
                                    <div class="row ml-1 mr-1">
                                        <div class="mt-5 text-center col-md-12 d-flex justify-content-end">
                                            <button class="btn btn-primary ct-button" type="submit"><i class="fa fa-check"></i>&nbsp;Đồng ý</button>
                                            <button class="btn btn-primary ct-button ml-3" type="reset"><i class="fa fa-undo-alt"></i>&nbsp;Nhập lại</button>
                                            <a href="${pageContext.request.contextPath}/admin/brand" class="btn btn-primary ct-button ml-3"><i class="fa fa-angle-left"></i>&nbsp;Quay lại</a>
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
<!-- CK EDITOR -->
<script src="${url}/js/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
    CKEDITOR.replace('description');
</script>

</body>
</html>
