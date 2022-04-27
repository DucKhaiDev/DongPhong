<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="Util.Constant" %>
<%--
  User: duckhaidev
  Date: 2/14/2022
  Time: 10:34 AM
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
    <link href="${pageContext.request.contextPath}/assets/js/morris/morris-0.4.3.min.css" rel="stylesheet"/>
    <!-- CUSTOM STYLES-->
    <link href="${pageContext.request.contextPath}/assets/css/custom.css" rel="stylesheet"/>
    <!-- GOOGLE FONTS-->
    <link href='https://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'/>
</head>
<body>
<div id="wrapper">
    <%--NAV TOP--%>
    <jsp:include page="navtop.jsp"/>

    <%--NAV SIDE--%>
    <jsp:include page="navside.jsp"/>

    <div id="page-wrapper">
        <div id="page-inner">
            <div class="row">
                <div class="col-md-12">
                    <h2>Trang Chủ</h2>
                    <h5>Chào mừng ${sessionScope.displayName}, rất vui được gặp lại bạn. </h5>
                </div>
            </div>
            <!-- /. ROW  -->
            <hr/>
            <a class="weatherwidget-io" href="https://forecast7.com/en/21d03105d83/hanoi/" data-label_1="HÀ NỘI" data-label_2="<%
                out.print(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
            %>" data-theme="blue-mountains" >HÀ NỘI VIETNAM</a>
            <script>
                !function(d,s,id){
                    let js, fjs = d.getElementsByTagName(s)[0];if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src='https://weatherwidget.io/js/widget.min.js';fjs.parentNode.insertBefore(js,fjs);}}(document,'script','weatherwidget-io-js');
            </script>
            <hr>
            <div class="row">
                <div class="col-md-3 col-sm-6 col-xs-6">
                    <div class="panel panel-back noti-box">
                <span class="icon-box bg-color-red set-icon">
                    <i class="fa fa-envelope"></i>
                </span>
                        <div class="text-box">
                            <p class="main-text fs-20">120 Tin nhắn</p>
                            <p class="text-muted">Mới</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 col-sm-6 col-xs-6">
                    <div class="panel panel-back noti-box">
                <span class="icon-box bg-color-green set-icon">
                    <i class="fa fa-user"></i>
                </span>
                        <div class="text-box">
                            <p class="main-text fs-20"><% out.print(Constant.Service.USER_SERVICE.countMember()); %> Người dùng</p>
                            <p class="text-muted">Mới</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 col-sm-6 col-xs-6">
                    <div class="panel panel-back noti-box">
                <span class="icon-box bg-color-blue set-icon">
                    <i class="fa fa-box-open"></i>
                </span>
                        <div class="text-box">
                            <p class="main-text fs-20"><% out.print(Constant.Service.PRODUCT_SERVICE.countOutOfStock()); %> Sản phẩm</p>
                            <p class="text-muted">Cháy hàng</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 col-sm-6 col-xs-6">
                    <div class="panel panel-back noti-box">
                <span class="icon-box bg-color-brown set-icon">
                    <i class="fa fa-file-spreadsheet"></i>
                </span>
                        <div class="text-box">
                            <p class="main-text fs-20"><% out.print(Constant.Service.ORDER_SERVICE.countPendingOrder()); %> Đơn hàng</p>
                            <p class="text-muted">Đang chờ xử lý</p>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /. ROW  -->
            <hr>
            <!-- /. ROW  -->
            <div class="row">
                <div class="col-md-9 col-sm-12 col-xs-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Biểu Đồ Doanh Thu
                        </div>
                        <div class="panel-body">
                            <canvas id="myChart"></canvas>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 col-sm-12 col-xs-12">
                    <span>Quy Đổi (VNĐ &#8644; USD)</span>
                    <!-- Currency Converter Script - EXCHANGERATEWIDGET.COM -->
                    <div style="width:100%;border:1px solid #2D6AB4; margin-top: 20px;"><div style="text-align:center;background-color:#2D6AB4;width:100%;font-size:13px;font-weight:bold;height:18px;padding-top:2px;"><a href="https://www.exchangeratewidget.com/" style="color:#FFFFFF;text-decoration:none;" rel="nofollow">Quy Đổi Tiền Tệ</a></div><script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/converter-usd-to-vnd.js"></script></div>
                    <!-- End of Currency Converter Script -->
                    <!-- Currency Converter Script - EXCHANGERATEWIDGET.COM -->
                    <div style="width:100%;border:1px solid #2D6AB4; margin-top: 20px;"><div style="text-align:center;background-color:#2D6AB4;width:100%;font-size:13px;font-weight:bold;height:18px;padding-top:2px;"><a href="https://www.exchangeratewidget.com/" style="color:#FFFFFF;text-decoration:none;" rel="nofollow">Quy Đổi Tiền Tệ</a></div><script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/converter-vnd-to-usd.js"></script></div>
                    <!-- End of Currency Converter Script -->
                </div>
            </div>
            <hr/>
            <div class="row">
                <div class="col-md-12 col-sm-12 col-xs-12">
                    <div class="panel panel-back noti-box">
                        <span class="text-uppercase" style="color: mediumblue; font-size: 15px; font-weight: bold;">Tin Tức Thị Trường Chứng Khoán</span>
                        <!-- TradingView Widget BEGIN -->
                        <div class="tradingview-widget-container" style="padding-top: 16px;">
                            <div id="tradingview_79c7d"></div>
                            <div class="tradingview-widget-copyright"><a href="https://vn.tradingview.com/symbols/NASDAQ-AAPL/" rel="noopener" target="_blank"><span class="blue-text">AAPL Biểu đồ</span> </a> bởi TradingView</div>
                            <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/trading-view.js"></script>
                            <script type="text/javascript">
                                new TradingView.widget(
                                    {
                                        "width": 980,
                                        "height": 610,
                                        "symbol": "NASDAQ:AAPL",
                                        "interval": "D",
                                        "timezone": "Asia/Ho_Chi_Minh",
                                        "theme": "dark",
                                        "style": "3",
                                        "locale": "vi_VN",
                                        "toolbar_bg": "#f1f3f6",
                                        "enable_publishing": false,
                                        "allow_symbol_change": true,
                                        "container_id": "tradingview_79c7d"
                                    }
                                );
                            </script>
                        </div>
                        <!-- TradingView Widget END -->
                    </div>
                </div>
            </div>
            <!-- /. ROW  -->
            <div class="row">
                <div class="col-md-3 col-sm-12 col-xs-12">
                    <div class="panel panel-primary text-center no-boder bg-color-green">
                        <div class="panel-body">
                            <i class="fa fa-comments-o fa-5x"></i>
                            <h4>${requestScope.countNewComment} Bình luận mới </h4>
                            <h4>Xem tất cả bình luận </h4>
                        </div>
                        <div class="panel-footer back-footer-green">
                            <i class="fa fa-rocket fa-5x"></i>
                            Đánh giá hoạt động tương tác dựa trên hoạt động bình luận gần đây của Website: <c:choose>
                            <c:when test="${requestScope.countNewComment < 10}">Thấp</c:when>
                            <c:when test="${10 <= requestScope.countNewComment && requestScope.countNewComment < 20}">Trung bình</c:when>
                            <c:otherwise>Cao</c:otherwise>
                        </c:choose>
                        </div>
                    </div>
                </div>
                <div class="col-md-9 col-sm-12 col-xs-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            TOP 10 Khách Hàng Thân Thiết
                        </div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover">
                                    <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Username</th>
                                        <th>Họ</th>
                                        <th>Tên</th>
                                        <th>Email</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:set var="number" value="0"/>
                                    <jsp:useBean id="topMembers" scope="request" type="java.util.List"/>
                                    <c:forEach items="${topMembers}" var="member">
                                        <tr>
                                            <td>${number = number + 1}</td>
                                            <td>${member.username}</td>
                                            <td>${member.lastName}</td>
                                            <td>${member.firstName}</td>
                                            <td>${member.email}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /. ROW  -->
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
<!-- MORRIS CHART SCRIPTS -->
<script src="${pageContext.request.contextPath}/assets/js/morris/raphael-2.1.0.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/morris/morris.js"></script>
<!-- CUSTOM SCRIPTS -->
<script src="${pageContext.request.contextPath}/assets/js/custom.js"></script>
<!--===============================================================================================-->
<script src="${pageContext.request.contextPath}/assets/js/Chart.js-v2.9.4/Chart.min.js"></script>
<script>
    $(function () {
        let xValues = [];
        let yValues = [];
        let fromYear = ${requestScope.fromYear};
        let toYear = ${requestScope.toYear};
        while (fromYear <= toYear) {
            xValues.push(fromYear);
            let exist = false;
            <jsp:useBean id="reports" scope="request" type="java.util.List"/>
            <c:forEach items="${reports}" var="item">
            if (fromYear === ${item.key}) {
                yValues.push(${item.value});
                exist = true;
            }
            </c:forEach>
            if (!exist) {
                yValues.push(0);
            }
            fromYear++;
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