<%--
  User: duckhaidev
  Date: 1/22/2022
  Time: 9:41 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<footer class="bg3 p-t-75 p-b-32">
    <div class="container">
        <div class="row">
            <div class="col-sm-6 col-lg-2 p-b-50">
                <h4 class="stext-301 cl0 p-b-30">
                    Sản phẩm
                </h4>

                <ul>
                    <c:forEach items="${applicationScope.rooms}" var="room">
                        <li class="p-b-10">
                            <a href="${pageContext.request.contextPath}/products/room?id=${room.roomId}"
                               class="stext-107 cl7 hov-cl1 trans-04">
                                    ${room.roomName}
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </div>

            <div class="col-sm-6 col-lg-2 p-b-50">
                <h4 class="stext-301 cl0 p-b-30">
                    Hỗ trợ
                </h4>

                <ul>
                    <li class="p-b-10">
                        <a href="<c:choose>
                            <c:when test="${sessionScope.account != null}">
                                ${pageContext.request.contextPath}/member/my-order
                            </c:when>
                            <c:otherwise>
                                #
                            </c:otherwise>
                        </c:choose>" class="stext-107 cl7 hov-cl1 trans-04">
                            Theo dõi đơn hàng
                        </a>
                    </li>

                    <li class="p-b-10">
                        <a href="#" class="stext-107 cl7 hov-cl1 trans-04">
                            Đổi trả
                        </a>
                    </li>

                    <li class="p-b-10">
                        <a href="#" class="stext-107 cl7 hov-cl1 trans-04">
                            Giao nhận
                        </a>
                    </li>

                    <li class="p-b-10">
                        <a href="#" class="stext-107 cl7 hov-cl1 trans-04">
                            FAQs
                        </a>
                    </li>
                </ul>
            </div>

            <div class="col-sm-6 col-lg-3 p-b-50">
                <h4 class="stext-301 cl0 p-b-30">
                    Địa chỉ liên hệ
                </h4>

                <p class="stext-107 cl7 size-201 p-t-0">
                    Nội thất cao cấp Đông Phong, thôn Phong Nẫm, xã Đông Phong, huyện Yên Phong, tỉnh Bắc Ninh.
                    <br>
                    SĐT: (+84) 98 252 3005
                </p>

                <div class="p-t-27">
                    <a href="https://www.facebook.com/DucKhaiDev/" class="fs-18 cl7 hov-cl1 trans-04 m-r-16">
                        <i class="fab fa-facebook"></i>
                    </a>

                    <a href="#" class="fs-18 cl7 hov-cl1 trans-04 m-r-16">
                        <i class="fab fa-twitter"></i>
                    </a>

                    <a href="#" class="fs-18 cl7 hov-cl1 trans-04 m-r-16">
                        <i class="fab fa-google-plus"></i>
                    </a>
                </div>
            </div>
            <div class="col-lg-1"></div>
            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d7303.987023982453!2d106.01402162920304!3d21.200006849751542!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0xa477e840bb7953f5!2zMjHCsDEyJzAwLjciTiAxMDbCsDAxJzE1LjQiRQ!5e1!3m2!1svi!2s!4v1650273694352!5m2!1svi!2s"
                    width="400" height="300" style="border:0;" allowfullscreen="" loading="lazy"
                    referrerpolicy="no-referrer-when-downgrade"></iframe>
        </div>

        <div class="p-t-40">
            <div class="flex-c-m flex-w p-b-18">
                <a href="#" class="m-all-1">
                    <img src="${pageContext.request.contextPath}/assets/images/icons/icon-pay-01.png" alt="ICON-PAY">
                </a>

                <a href="#" class="m-all-1">
                    <img src="${pageContext.request.contextPath}/assets/images/icons/icon-pay-02.png" alt="ICON-PAY">
                </a>

                <a href="#" class="m-all-1">
                    <img src="${pageContext.request.contextPath}/assets/images/icons/icon-pay-03.png" alt="ICON-PAY">
                </a>

                <a href="#" class="m-all-1">
                    <img src="${pageContext.request.contextPath}/assets/images/icons/icon-pay-04.png" alt="ICON-PAY">
                </a>

                <a href="#" class="m-all-1">
                    <img src="${pageContext.request.contextPath}/assets/images/icons/icon-pay-05.png" alt="ICON-PAY">
                </a>
            </div>

            <p class="stext-107 cl6 txt-center">
                <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                Bản quyền Nội dung &copy;<script>document.write(new Date().getFullYear().toString());</script>
                : Công ty TNHH MTV <i class="fa fa-heart-o" aria-hidden="true"></i><a
                    href="${pageContext.request.contextPath}/welcome" target="_blank">Đông Phong</a>.
                <br>
                Địa chỉ: Thôn Phong Nẫm, xã Đông Phong, huyện Yên Phong, tỉnh Bắc Ninh. SĐT: (+84) 98 252 3005
                <br>
                Bản quyền Website: <a href="https://www.facebook.com/DucKhaiDev/" target="_blank">DucKhaiDev</a>
                <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
            </p>
        </div>
    </div>
</footer>

<!-- Back to top -->
<div class="btn-back-to-top" id="myBtn">
		<span class="symbol-btn-back-to-top">
			<i class="zmdi zmdi-chevron-up"></i>
		</span>
</div>