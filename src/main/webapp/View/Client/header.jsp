<%--
  User: duckhaidev
  Date: 1/22/2022
  Time: 9:20 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <!-- Header desktop -->
    <div class="container-menu-desktop">
        <!-- Topbar -->
        <jsp:include page="topbar.jsp"/>

        <div class="wrap-menu-desktop">
            <nav class="limiter-menu-desktop container">

                <!-- Logo desktop -->
                <a href="${pageContext.request.contextPath}/welcome" class="logo">
                    <img src="${pageContext.request.contextPath}/assets/images/icons/logo.png" alt="IMG-LOGO">
                </a>

                <!-- Menu desktop -->
                <div class="menu-desktop">
                    <ul class="main-menu">
                        <li>
                            <a href="${pageContext.request.contextPath}/welcome">Trang Chủ</a>
                        </li>

                        <li class="nav-item dropdown btn-group">
                            <a class="nav-link dropdown-toggle cl93 cursor-pointer" id="navbarDropdownPK" role="button"
                               data-toggle="dropdown" aria-haspopup="true"
                               aria-expanded="false">Phòng Khách</a>
                            <div class="dropdown-menu m-t-25" aria-labelledby="navbarDropdownPK">
                                <c:forEach items="${applicationScope.lvrCategories}" var="lvrCategory">
                                    <a class="dropdown-item cl92"
                                       href="${pageContext.request.contextPath}/products/category?id=${lvrCategory.categoryId}">
                                        <span class="m-r-4"></span>
                                            ${lvrCategory.categoryName}
                                    </a>
                                </c:forEach>
                            </div>
                        </li>

                        <li class="nav-item dropdown btn-group">
                            <a class="nav-link dropdown-toggle cl93 cursor-pointer" id="navbarDropdownPB" role="button"
                               data-toggle="dropdown" aria-haspopup="true"
                               aria-expanded="false">Phòng Bếp</a>
                            <div class="dropdown-menu m-t-25" aria-labelledby="navbarDropdownPB">
                                <c:forEach items="${applicationScope.kitCategories}" var="kitCategory">
                                    <a class="dropdown-item cl92"
                                       href="${pageContext.request.contextPath}/products/category?id=${kitCategory.categoryId}">
                                        <span class="m-r-4"></span>
                                            ${kitCategory.categoryName}
                                    </a>
                                </c:forEach>
                            </div>
                        </li>

                        <li class="nav-item dropdown btn-group">
                            <a class="nav-link dropdown-toggle cl93 cursor-pointer" id="navbarDropdownPN" role="button"
                               data-toggle="dropdown" aria-haspopup="true"
                               aria-expanded="false">Phòng Ngủ</a>
                            <div class="dropdown-menu m-t-25" aria-labelledby="navbarDropdownPN">
                                <c:forEach items="${applicationScope.bedCategories}" var="bedCategory">
                                    <a class="dropdown-item cl92"
                                       href="${pageContext.request.contextPath}/products/category?id=${bedCategory.categoryId}">
                                        <span class="m-r-4"></span>
                                            ${bedCategory.categoryName}
                                    </a>
                                </c:forEach>
                            </div>
                        </li>

                        <li class="nav-item dropdown btn-group">
                            <a class="nav-link dropdown-toggle cl93 cursor-pointer" id="navbarDropdownVP" role="button"
                               data-toggle="dropdown" aria-haspopup="true"
                               aria-expanded="false">Văn Phòng</a>
                            <div class="dropdown-menu m-t-25" aria-labelledby="navbarDropdownVP">
                                <c:forEach items="${applicationScope.offCategories}" var="offCategory">
                                    <a class="dropdown-item cl92"
                                       href="${pageContext.request.contextPath}/products/category?id=${offCategory.categoryId}">
                                        <span class="m-r-4"></span>
                                            ${offCategory.categoryName}
                                    </a>
                                </c:forEach>
                            </div>
                        </li>

                        <li class="nav-item dropdown btn-group">
                            <a class="nav-link dropdown-toggle cl93 cursor-pointer" id="navbarDropdownPT" role="button"
                               data-toggle="dropdown" aria-haspopup="true"
                               aria-expanded="false">Phòng Thờ</a>
                            <div class="dropdown-menu m-t-25" aria-labelledby="navbarDropdownPT">
                                <c:forEach items="${applicationScope.altCategories}" var="altCategory">
                                    <a class="dropdown-item cl92"
                                       href="${pageContext.request.contextPath}/products/category?id=${altCategory.categoryId}">
                                        <span class="m-r-4"></span>
                                            ${altCategory.categoryName}
                                    </a>
                                </c:forEach>
                            </div>
                        </li>
                    </ul>
                </div>

                <!-- Icon header -->
                <div class="wrap-icon-header flex-w flex-r-m">
                    <div class="icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 js-show-modal-search">
                        <i class="zmdi zmdi-search"></i>
                    </div>

                    <div class="icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 icon-header-noti js-show-cart"
                         data-notify="${sessionScope.cartItems.size() != null ? sessionScope.cartItems.size() : 0}">
                        <i class="zmdi zmdi-shopping-cart"></i>
                    </div>

                    <div class="dis-block icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 icon-header-noti js-show-wishList"
                         data-notify="${sessionScope.wlItems.size() != null ? sessionScope.wlItems.size() : 0}">
                        <i class="zmdi zmdi-favorite-outline"></i>
                    </div>
                </div>
            </nav>
        </div>
    </div>

    <!-- Header Mobile -->
    <div class="wrap-header-mobile">
        <!-- Logo moblie -->
        <div class="logo-mobile">
            <a href="index.jsp"><img src="${pageContext.request.contextPath}/assets/images/icons/logo.png"
                                     alt="IMG-LOGO"></a>
        </div>

        <!-- Icon header -->
        <div class="wrap-icon-header flex-w flex-r-m m-r-15">
            <div class="icon-header-item cl2 hov-cl1 trans-04 p-r-11 js-show-modal-search">
                <i class="zmdi zmdi-search"></i>
            </div>

            <div class="icon-header-item cl2 hov-cl1 trans-04 p-r-11 p-l-10 icon-header-noti js-show-cart"
                 data-notify="2">
                <i class="zmdi zmdi-shopping-cart"></i>
            </div>

            <a href="#" class="dis-block icon-header-item cl2 hov-cl1 trans-04 p-r-11 p-l-10 icon-header-noti"
               data-notify="0">
                <i class="zmdi zmdi-favorite-outline"></i>
            </a>
        </div>

        <!-- Button show menu -->
        <div class="btn-show-menu-mobile hamburger hamburger--squeeze">
				<span class="hamburger-box">
					<span class="hamburger-inner"></span>
				</span>
        </div>
    </div>


    <!-- Menu Mobile -->
    <div class="menu-mobile">
        <ul class="topbar-mobile">
            <li>
                <div class="left-top-bar">
                    Free shipping for standard order over $100
                </div>
            </li>

            <li>
                <div class="right-top-bar flex-w h-full">
                    <a href="#" class="flex-c-m p-lr-10 trans-04">
                        Help & FAQs
                    </a>

                    <a href="#" class="flex-c-m p-lr-10 trans-04">
                        My Account
                    </a>

                    <a href="#" class="flex-c-m p-lr-10 trans-04">
                        EN
                    </a>

                    <a href="#" class="flex-c-m p-lr-10 trans-04">
                        USD
                    </a>
                </div>
            </li>
        </ul>

        <ul class="main-menu-m">
            <li>
                <a href="index.jsp">Home</a>
                <ul class="sub-menu-m">
                    <li><a href="index.jsp">Homepage 1</a></li>
                </ul>
                <span class="arrow-main-menu-m">
						<i class="fa fa-angle-right" aria-hidden="true"></i>
					</span>
            </li>

            <li>
                <a href="../Unused/product.jsp">Shop</a>
            </li>

            <li>
                <a href="cart-detail.jsp" class="label1 rs1" data-label1="hot">Features</a>
            </li>

            <li>
                <a href="../Unused/blog.jsp">Blog</a>
            </li>

            <li>
                <a href="../Unused/about.jsp">About</a>
            </li>

            <li>
                <a href="contact.jsp">Contact</a>
            </li>
        </ul>
    </div>

    <!-- Modal Search -->
    <div class="modal-search-header flex-c-m trans-04 js-hide-modal-search">
        <div class="container-search-header">
            <button class="flex-c-m btn-hide-modal-search trans-04 js-hide-modal-search">
                <img src="${pageContext.request.contextPath}/assets/images/icons/icon-close2.png" alt="CLOSE">
            </button>

            <form id="search-header" action="<c:url value="/search"/>" class="wrap-search-header flex-w p-l-15">
                <button type="submit" class="flex-c-m trans-04">
                    <i class="zmdi zmdi-search"></i>
                </button>
                <label>
                    <input class="plh3" type="text" name="keyword-search-header" placeholder="Tìm kiếm...">
                </label>
            </form>
        </div>
    </div>
</header>

<!--===============================================================================================-->
<script>
    $(function () {
        $('input[name="keyword-search-header"]').keypress(function (e) {
            const keycode = (e.keyCode ? e.keyCode : e.which);
            if (keycode === '13') {
                $('#search-header').submit();
            }
        });
    });
</script>