package Util;

import Services.deploy.*;

import java.io.File;
import java.text.NumberFormat;
import java.util.Locale;

public class Constant {
    public static final String COOKIE_USERNAME = "username";
    public static final String COOKIE_PASSWORD = "password";
    public static final Locale LC_VIETNAM = new Locale("vi", "VN");
    public static final NumberFormat NF_DONG = NumberFormat.getCurrencyInstance(LC_VIETNAM);
    public static final String ERROR_PAGE = "/View/error.jsp";
    public static final String ERROR_PAGE_404 = "/View/error-404.jsp";

    public static class Service {
        public static final BrandService BRAND_SERVICE = new BrandService();
        public static final CartItemService CART_ITEM_SERVICE = new CartItemService();
        public static final CartService CART_SERVICE = new CartService();
        public static final CategoryService CATEGORY_SERVICE = new CategoryService();
        public static final OrderService ORDER_SERVICE = new OrderService();
        public static final PaymentService PAYMENT_SERVICE = new PaymentService();
        public static final ProductService PRODUCT_SERVICE = new ProductService();
        public static final ProImageService PRO_IMAGE_SERVICE = new ProImageService();
        public static final ProvinceService PROVINCE_SERVICE = new ProvinceService();
        public static final ReportService REPORT_SERVICE = new ReportService();
        public static final ReviewService REVIEW_SERVICE = new ReviewService();
        public static final RoomService ROOM_SERVICE = new RoomService();
        public static final UserService USER_SERVICE = new UserService();
        public static final VoucherService VOUCHER_SERVICE = new VoucherService();
        public static final WishListService WISH_LIST_SERVICE = new WishListService();
        public static final WLItemService WL_ITEM_SERVICE = new WLItemService();
        public static final TempLinkService TEMP_LINK_SERVICE = new TempLinkService();
        public static final MessageService MESSAGE_SERVICE = new MessageService();
    }

    public static class Path {
        //General constants
        public static final String LOGIN = "/View/Client/login.jsp";

        //Admin constants
        public static final String DASHBOARD = "/View/Admin/index.jsp";
        public static final String ADMIN_USER = "/View/Admin/user.jsp";
        public static final String ADMIN_EDIT_USER = "/View/Admin/edit-user.jsp";
        public static final String ADMIN_PRODUCT = "/View/Admin/product.jsp";
        public static final String ADMIN_ADD_PRODUCT = "/View/Admin/add-product.jsp";
        public static final String ADMIN_EDIT_PRODUCT = "/View/Admin/edit-product.jsp";
        public static final String ADMIN_CATEGORY = "/View/Admin/category.jsp";
        public static final String ADMIN_ADD_CATEGORY = "/View/Admin/add-category.jsp";
        public static final String ADMIN_EDIT_CATEGORY = "/View/Admin/edit-category.jsp";
        public static final String ADMIN_BRAND = "/View/Admin/brand.jsp";
        public static final String ADMIN_ADD_BRAND = "/View/Admin/add-brand.jsp";
        public static final String ADMIN_EDIT_BRAND = "/View/Admin/edit-brand.jsp";
        public static final String USER_HOME = System.getProperty("user.home");
        public static final String IMAGES = USER_HOME + File.separator + "Images";
        public static final String AVATARS = IMAGES + File.separator + "Avatars";
        public static final String PRODUCT_IMAGES = IMAGES + File.separator + "Product images";
        public static final String DP_RESOURCES = USER_HOME + File.separator + "resources";
        public static final String ADMIN_ORDER = "/View/Admin/order.jsp";
        public static final String ADMIN_ADD_ORDER = "/View/Admin/add-order.jsp";
        public static final String ADMIN_ORDER_DETAIL = "/View/Admin/order-detail.jsp";
        public static final String ADMIN_EDIT_ORDER = "/View/Admin/edit-order.jsp";
        public static final String ADMIN_VOUCHER = "/View/Admin/voucher.jsp";
        public static final String ADMIN_ADD_VOUCHER = "/View/Admin/add-voucher.jsp";
        public static final String ADMIN_EDIT_VOUCHER = "/View/Admin/edit-voucher.jsp";
        public static final String ADMIN_REVIEW = "/View/Admin/review.jsp";
        public static final String ADMIN_REVIEW_DETAIL = "/View/Admin/review-detail.jsp";
        public static final String ADMIN_REPORT = "/View/Admin/report.jsp";
        public static final String ADMIN_STATISTIC = "/View/Admin/statistic.jsp";
        public static final String ADMIN_MESSAGE = "/View/Admin/message.jsp";

        //Client constants
        public static final String REGISTER = "/View/Client/register.jsp";
        public static final String HOME = "/View/Client/index.jsp";
        public static final String MY_ACCOUNT = "/View/Client/my-account.jsp";
        public static final String CHANGE_PASSWORD = "/View/Client/change-password.jsp";
        public static final String PRODUCT_LIST_BY_CATEGORY = "/View/Client/product-list-by-category.jsp";
        public static final String PRODUCT_DETAIL = "/View/Client/product-detail.jsp";
        public static final String CART = "/View/Client/cart-detail.jsp";
        public static final String CHECKOUT = "/View/Client/checkout.jsp";
        public static final String PRODUCT_LIST_BY_ROOM = "/View/Client/product-list-by-room.jsp";
        public static final String PRODUCT_SEARCH = "/View/Client/search-product.jsp";
        public static final String FORGET_PASSWORD = "/View/Client/forget-password.jsp";
        public static final String FGP_CHANGE_PASSWORD = "/View/Client/fgp-change-password.jsp";
        public static final String MY_ORDER = "/View/Client/my-order.jsp";
        public static final String MY_ORDER_DETAIL = "/View/Client/my-order-detail.jsp";
        public static final String CONTACT = "/View/Client/contact.jsp";
        public static final String ABOUT = "/View/Client/about.jsp";
    }
}