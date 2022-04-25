<%@ page import="Entity.User" %><%--
  User: duckhaidev
  Date: 2/23/2022
  Time: 10:04 PM
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
    <!-- CUSTOM STYLES-->
    <link href="${pageContext.request.contextPath}/assets/css/custom.css" rel="stylesheet"/>
    <!-- GOOGLE FONTS-->
    <link href='https://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'/>
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

    <div id="page-wrapper">
        <div id="page-inner">
            <div class="row">
                <div class="col-md-12">
                    <h2>Quản Lý Sản Phẩm</h2>
                    <h5>Chào mừng ${sessionScope.displayName}, rất vui được gặp lại bạn. </h5>
                </div>
            </div>
            <!-- /. ROW  -->
            <hr/>
            <div class="row d-flex justify-content-center">
                <div class="col-md-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">Thêm mới loại sản phẩm</div>
                        <div class="panel-body">
                            <div class="row">
                                <form action="<c:url value="/admin/category/add"/>" method="post">
                                    <div class="row ml-1 mr-1">
                                        <div class="col-md-6 mb-3"><label for="categoryId"
                                                                          class="labels">ID</label><input
                                                id="categoryId" type="text" class="form-control" name="categoryId"
                                                maxlength="10" required="required"></div>
                                        <div class="col-md-6 mb-3"><label class="labels"></label>
                                            <p class="exist-id">${requestScope.existId}</p></div>
                                        <div class="col-md-12 mb-3"><label for="categoryName" class="labels">Loại sản
                                            phẩm</label><input id="categoryName" type="text" class="form-control"
                                                               name="categoryName" maxlength="255" required="required">
                                        </div>
                                        <div class="col-md-6 mb-3">
                                            <label for="room" class="labels">Danh mục</label>
                                            <div class="checkbox pl-0">
                                                <select id="room" name="room" class="w-50">
                                                    <jsp:useBean id="rooms" scope="request" type="java.util.List"/>
                                                    <c:forEach items="${rooms}" var="room">
                                                        <option value="${room.roomId}">${room.roomName}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <label for="categoryDescription" class="labels">Mô tả</label>
                                            <br>
                                            <textarea name="categoryDescription" id="categoryDescription"
                                                      class="form-control" cols="50" rows="5"
                                                      maxlength="2000"></textarea>
                                        </div>
                                    </div>
                                    <div class="row ml-1 mr-1">
                                        <div class="mt-5 text-center col-md-12 d-flex justify-content-end">
                                            <button class="btn btn-primary ct-button" type="submit"><i
                                                    class="fa fa-check"></i>&nbsp;Đồng ý
                                            </button>
                                            <button class="btn btn-primary ct-button ml-3" type="reset"><i
                                                    class="fa fa-undo-alt"></i>&nbsp;Nhập lại
                                            </button>
                                            <a href="${pageContext.request.contextPath}/admin/category"
                                               class="btn btn-primary ct-button ml-3"><i class="fa fa-angle-left"></i>&nbsp;Quay
                                                lại</a>
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
<!-- CK EDITOR -->
<script src="${pageContext.request.contextPath}/assets/js/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
    CKEDITOR.replace('categoryDescription');
</script>

</body>
</html>
