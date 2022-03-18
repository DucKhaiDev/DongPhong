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
                <a href="${pageContext.request.contextPath}" class="logo">
                    <img src="${pageContext.request.contextPath}/assets/images/icons/logo.png" alt="IMG-LOGO">
                </a>

                <!-- Menu desktop -->
                <div class="menu-desktop">
                    <ul class="main-menu">
                        <li>
                            <a href="${pageContext.request.contextPath}">Trang Chủ</a>
                        </li>

                        <li class="dropdown show">
                            <a class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" href="">Phòng Khách</a>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                                <c:forEach items="${applicationScope.lvrCategories}" var="lvrCategory">
                                    <li class="dropdown-item"><a href="${pageContext.request.contextPath}/products/category?id=${lvrCategory.categoryId}">${lvrCategory.categoryName}</a></li>
                                </c:forEach>
                            </ul>
                        </li>

                        <li class="dropdown show">
                            <a class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" href="">Phòng Bếp</a>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                                <c:forEach items="${applicationScope.kitCategories}" var="kitCategory">
                                    <li class="dropdown-item"><a href="${pageContext.request.contextPath}/products/category?id=${kitCategory.categoryId}">${kitCategory.categoryName}</a></li>
                                </c:forEach>
                            </ul>
                        </li>

                        <li class="dropdown show">
                            <a class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" href="">Phòng Ngủ</a>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                                <c:forEach items="${applicationScope.bedCategories}" var="bedCategory">
                                    <li class="dropdown-item"><a href="${pageContext.request.contextPath}/products/category?id=${bedCategory.categoryId}">${bedCategory.categoryName}</a></li>
                                </c:forEach>
                            </ul>
                        </li>

                        <li class="dropdown show">
                            <a class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" href="">Văn Phòng</a>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                                <c:forEach items="${applicationScope.offCategories}" var="offCategory">
                                    <li class="dropdown-item"><a href="${pageContext.request.contextPath}/products/category?id=${offCategory.categoryId}">${offCategory.categoryName}</a></li>
                                </c:forEach>
                            </ul>
                        </li>

                        <li class="dropdown show">
                            <a class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" href="">Phòng Thờ</a>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                                <c:forEach items="${applicationScope.altCategories}" var="altCategory">
                                    <li class="dropdown-item"><a href="${pageContext.request.contextPath}/products/category?id=${altCategory.categoryId}">${altCategory.categoryName}</a></li>
                                </c:forEach>
                            </ul>
                        </li>
                    </ul>
                </div>

                <!-- Icon header -->
                <div class="wrap-icon-header flex-w flex-r-m">
                    <div class="icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 js-show-modal-search">
                        <i class="zmdi zmdi-search"></i>
                    </div>

                    <div class="icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 icon-header-noti js-show-cart" data-notify="${sessionScope.cartItems.size() != null ? sessionScope.cartItems.size() : 0}">
                        <i class="zmdi zmdi-shopping-cart"></i>
                    </div>

                    <div class="dis-block icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 icon-header-noti js-show-wishList" data-notify="${sessionScope.wlItems.size() != null ? sessionScope.wlItems.size() : 0}">
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
            <a href="index.jsp"><img src="${pageContext.request.contextPath}/assets/images/icons/logo.png" alt="IMG-LOGO"></a>
        </div>

        <!-- Icon header -->
        <div class="wrap-icon-header flex-w flex-r-m m-r-15">
            <div class="icon-header-item cl2 hov-cl1 trans-04 p-r-11 js-show-modal-search">
                <i class="zmdi zmdi-search"></i>
            </div>

            <div class="icon-header-item cl2 hov-cl1 trans-04 p-r-11 p-l-10 icon-header-noti js-show-cart" data-notify="2">
                <i class="zmdi zmdi-shopping-cart"></i>
            </div>

            <a href="#" class="dis-block icon-header-item cl2 hov-cl1 trans-04 p-r-11 p-l-10 icon-header-noti" data-notify="0">
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
                <a href="product.jsp">Shop</a>
            </li>

            <li>
                <a href="cart-detail.jsp" class="label1 rs1" data-label1="hot">Features</a>
            </li>

            <li>
                <a href="blog.jsp">Blog</a>
            </li>

            <li>
                <a href="about.jsp">About</a>
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

            <form class="wrap-search-header flex-w p-l-15">
                <button class="flex-c-m trans-04">
                    <i class="zmdi zmdi-search"></i>
                </button>
                <label>
                    <input class="plh3" type="text" name="search" placeholder="Tìm kiếm...">
                </label>
            </form>
        </div>
    </div>
</header>