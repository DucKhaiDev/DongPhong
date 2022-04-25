<jsp:useBean id="cancel" scope="request" type="Entity.Report"/>
<jsp:useBean id="undone" scope="request" type="Entity.Report"/>
<jsp:useBean id="done" scope="request" type="Entity.Report"/>
<jsp:useBean id="reportDone" scope="request" type="java.util.List"/>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="Entity.Report" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.text.NumberFormat" %>
<%--
  User: duckhaidev
  Date: 4/20/2022
  Time: 6:50 PM
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
    <!--===============================================================================================-->
    <link href="${pageContext.request.contextPath}/assets/js/Chart.js-v2.9.4/Chart.min.css" rel="stylesheet"/>
    <!--===============================================================================================-->
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
                            Báo Cáo
                        </div>
                        <div class="panel-body row ml-0 mr-0">
                            <div class="col-md-12 mb-3">
                                <h4 class="text-uppercase mb-3" style="color: mediumaquamarine"><strong>Doanh số theo
                                    tháng</strong></h4>
                                <form action="<c:url value="/admin/report"/>" method="get"
                                      class="row d-flex justify-content-center mt-3 mb-3">
                                    <div class="col-md-2 mb-3">
                                        <label for="from" class="labels">Từ</label>
                                        <input id="from" type="month"
                                               value="<% out.print(new SimpleDateFormat("yyyy-MM").format((Timestamp) request.getAttribute("from"))); %>"
                                               class="form-control text-center" min="2000-01" name="from">
                                    </div>
                                    <div class="col-md-2 mb-3">
                                        <label for="to" class="labels">Đến</label>
                                        <input id="to" type="month"
                                               value="<% out.print(new SimpleDateFormat("yyyy-MM").format((Timestamp) request.getAttribute("to"))); %>"
                                               class="form-control text-center" min="2000-01" name="to">
                                    </div>
                                    <div class="col-md-2 mb-3 d-flex align-items-end">
                                        <button type="submit" class="btn btn-primary ct-button w-100">
                                            Báo Cáo
                                        </button>
                                    </div>
                                </form>
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-10 mb-3">
                                        <canvas id="myChart"></canvas>
                                        <h4 class="text-uppercase text-center" style="color: blue;">Biểu đồ tăng trưởng
                                            doanh thu
                                            từ <% out.print(new SimpleDateFormat("MM/yyyy").format((Timestamp) request.getAttribute("from"))); %>
                                            đến <% out.print(new SimpleDateFormat("MM/yyyy").format((Timestamp) request.getAttribute("to"))); %></h4>
                                    </div>
                                    <div class="col-md-12 bor21 mb-3 table-responsive">
                                        <h4 class="mt-3 mb-3 text-uppercase" style="color: mediumaquamarine">Báo cáo
                                            doanh thu
                                            từ <% out.print(new SimpleDateFormat("MM/yyyy").format((Timestamp) request.getAttribute("from"))); %>
                                            đến <% out.print(new SimpleDateFormat("MM/yyyy").format((Timestamp) request.getAttribute("to"))); %>
                                            :</h4>
                                        <table class="table table-striped table-bordered table-hover">
                                            <thead>
                                            <tr style="background-color: #32383e; color: #fff">
                                                <th>Tháng</th>
                                                <th>Số lượng đơn hàng</th>
                                                <th>Doanh thu</th>
                                                <th>Giảm giá</th>
                                                <th>Thuế</th>
                                                <th>Vận chuyển</th>
                                                <th>Doanh thu thực</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <%
                                                Locale vie = new Locale("vi", "VN");
                                                NumberFormat dongFormat = NumberFormat.getCurrencyInstance(vie);
                                            %>
                                            <c:forEach items="${reportDone}" var="item">
                                                <tr>
                                                    <% Report item = (Report) pageContext.getAttribute("item"); %>
                                                    <td>${item.monthDate}/${item.yearDate}</td>
                                                    <td>${item.countId}</td>
                                                    <td><% out.print(dongFormat.format(item.getSumSubTotal())); %></td>
                                                    <td><% out.print(dongFormat.format(item.getSumDiscount())); %></td>
                                                    <td><% out.print(dongFormat.format(item.getSumTax())); %></td>
                                                    <td><% out.print(dongFormat.format(item.getSumShipping())); %></td>
                                                    <td><% out.print(dongFormat.format(item.getSumTotal())); %></td>
                                                </tr>
                                            </c:forEach>
                                            <tr>
                                                <th>Tổng:</th>
                                                <th>${done.countId}</th>
                                                <th><% out.print(dongFormat.format(done.getSumSubTotal())); %></th>
                                                <th><% out.print(dongFormat.format(done.getSumDiscount())); %></th>
                                                <th><% out.print(dongFormat.format(done.getSumTax())); %></th>
                                                <th><% out.print(dongFormat.format(done.getSumShipping())); %></th>
                                                <th><% out.print(dongFormat.format(done.getSumTotal())); %></th>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="col-md-12 bor21 mb-3 table-responsive">
                                        <h4 class="mt-3 mb-3 text-uppercase" style="color: mediumaquamarine">Báo cáo đơn
                                            hàng đang vận chuyển
                                            từ <% out.print(new SimpleDateFormat("MM/yyyy").format((Timestamp) request.getAttribute("from"))); %>
                                            đến <% out.print(new SimpleDateFormat("MM/yyyy").format((Timestamp) request.getAttribute("to"))); %>
                                            :</h4>
                                        <table class="table table-striped table-bordered table-hover">
                                            <thead>
                                            <tr style="background-color: #32383e; color: #fff">
                                                <th>Tháng</th>
                                                <th>Số lượng đơn hàng</th>
                                                <th>Doanh thu</th>
                                                <th>Giảm giá</th>
                                                <th>Thuế</th>
                                                <th>Vận chuyển</th>
                                                <th>Doanh thu thực</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <jsp:useBean id="reportUndone" scope="request" type="java.util.List"/>
                                            <c:forEach items="${reportUndone}" var="item">
                                                <tr>
                                                    <% Report item = (Report) pageContext.getAttribute("item"); %>
                                                    <td>${item.monthDate}/${item.yearDate}</td>
                                                    <td>${item.countId}</td>
                                                    <td><% out.print(dongFormat.format(item.getSumSubTotal())); %></td>
                                                    <td><% out.print(dongFormat.format(item.getSumDiscount())); %></td>
                                                    <td><% out.print(dongFormat.format(item.getSumTax())); %></td>
                                                    <td><% out.print(dongFormat.format(item.getSumShipping())); %></td>
                                                    <td><% out.print(dongFormat.format(item.getSumTotal())); %></td>
                                                </tr>
                                            </c:forEach>
                                            <tr>
                                                <th>Tổng:</th>
                                                <th>${undone.countId}</th>
                                                <th><% out.print(dongFormat.format(undone.getSumSubTotal())); %></th>
                                                <th><% out.print(dongFormat.format(undone.getSumDiscount())); %></th>
                                                <th><% out.print(dongFormat.format(undone.getSumTax())); %></th>
                                                <th><% out.print(dongFormat.format(undone.getSumShipping())); %></th>
                                                <th><% out.print(dongFormat.format(undone.getSumTotal())); %></th>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="col-md-12 bor21 mb-3 table-responsive">
                                        <h4 class="mt-3 mb-3 text-uppercase" style="color: mediumaquamarine">Báo cáo đơn
                                            hàng bị hủy
                                            từ <% out.print(new SimpleDateFormat("MM/yyyy").format((Timestamp) request.getAttribute("from"))); %>
                                            đến <% out.print(new SimpleDateFormat("MM/yyyy").format((Timestamp) request.getAttribute("to"))); %>
                                            :</h4>
                                        <table class="table table-striped table-bordered table-hover">
                                            <thead>
                                            <tr style="background-color: #32383e; color: #fff">
                                                <th>Tháng</th>
                                                <th>Số lượng đơn hàng</th>
                                                <th>Giá trị đơn hàng</th>
                                                <th>Áp dụng giảm giá</th>
                                                <th>Thuế</th>
                                                <th>Giá trị vận chuyển</th>
                                                <th>Tổng giá trị</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <jsp:useBean id="reportCancel" scope="request" type="java.util.List"/>
                                            <c:forEach items="${reportCancel}" var="item">
                                                <tr>
                                                    <% Report item = (Report) pageContext.getAttribute("item"); %>
                                                    <td>${item.monthDate}/${item.yearDate}</td>
                                                    <td>${item.countId}</td>
                                                    <td><% out.print(dongFormat.format(item.getSumSubTotal())); %></td>
                                                    <td><% out.print(dongFormat.format(item.getSumDiscount())); %></td>
                                                    <td><% out.print(dongFormat.format(item.getSumTax())); %></td>
                                                    <td><% out.print(dongFormat.format(item.getSumShipping())); %></td>
                                                    <td><% out.print(dongFormat.format(item.getSumTotal())); %></td>
                                                </tr>
                                            </c:forEach>
                                            <tr>
                                                <th>Tổng:</th>
                                                <th>${cancel.countId}</th>
                                                <th><% out.print(dongFormat.format(cancel.getSumSubTotal())); %></th>
                                                <th><% out.print(dongFormat.format(cancel.getSumDiscount())); %></th>
                                                <th><% out.print(dongFormat.format(cancel.getSumTax())); %></th>
                                                <th><% out.print(dongFormat.format(cancel.getSumShipping())); %></th>
                                                <th><% out.print(dongFormat.format(cancel.getSumTotal())); %></th>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
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
<!-- CUSTOM SCRIPTS -->
<script src="${pageContext.request.contextPath}/assets/js/custom.js"></script>
<!--===============================================================================================-->
<script src="${pageContext.request.contextPath}/assets/js/Chart.js-v2.9.4/Chart.min.js"></script>
<script>
    $(function () {
        let xValues = [];
        let yValues = [];
        const from = new Date($('#from').prop('value'));
        const to = new Date($('#to').prop('value'));
        let minMonth = from.getMonth() + 1;
        let minYear = from.getFullYear();
        let maxMonth = to.getMonth() + 1;
        let maxYear = to.getFullYear();
        while (minMonth <= maxMonth || minYear < maxYear) {
            console.log(minMonth);
            console.log(minYear);
            xValues.push(minMonth + '/' + minYear);
            let exist = false;
            <c:forEach items="${reportDone}" var="item">
            if (minMonth === ${item.monthDate} && minYear === ${item.yearDate}) {
                yValues.push(${item.sumTotal});
                exist = true;
            }
            </c:forEach>
            if (!exist) {
                yValues.push(0);
            }
            minMonth++;
            if (minMonth === 13) {
                minMonth = 1;
                minYear++;
            }
        }

        new Chart("myChart", {
            type: "line",
            data: {
                labels: xValues,
                datasets: [{
                    fill: false,
                    lineTension: 0,
                    backgroundColor: "rgba(0,0,255,1.0)",
                    borderColor: "rgba(0,0,255,0.1)",
                    data: yValues
                }]
            },
            options: {
                legend: {display: false},
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero: true,
                            callback: function (value) {
                                if (parseInt(value) >= 1000) {
                                    return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".") + ' \u20AB';
                                } else {
                                    return value + ' \u20AB';
                                }
                            }
                        }
                    }]
                },
                tooltips: {
                    callbacks: {
                        label: function (tooltipItem) {
                            return Number(tooltipItem.yLabel).toFixed(0).replace(/./g, function (c, i, a) {
                                return i > 0 && c !== "." && (a.length - i) % 3 === 0 ? "." + c : c;
                            }) + ' \u20AB';
                        }
                    }
                }
            }
        });
    });
</script>

</body>
</html>
