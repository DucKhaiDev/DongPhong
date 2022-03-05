package Util;

import java.io.File;

public class Constant {
    public static final String COOKIE_USERNAME = "username";
    public static final String COOKIE_PASSWORD = "password";

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
        public static final String USERHOME = System.getProperty("user.home");
        public static final String IMAGES = USERHOME + File.separator + "Images";
        public static final String AVATARS = IMAGES + File.separator + "Avatars";
        public static final String PRODUCT_IMAGES = IMAGES + File.separator + "Product images";

        //Client constants
        public static final String REGISTER = "/View/Client/register.jsp";
        public static final String HOME = "/View/Client/index.jsp";
        public static final String MYACCOUNT = "/View/Client/my-account.jsp";
        public static final String CHANGE_PASSWORD = "/View/Client/change-password.jsp";
        public static final String PRODUCT_LIST_BY_CATEGORY = "/View/Client/product-list-by-category.jsp";
    }
}
