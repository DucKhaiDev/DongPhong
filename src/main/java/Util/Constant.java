package Util;

import java.io.File;

public class Constant {
    public static final String COOKIE_REMEMBER = "username_or_email";

    public static class Path {
        public static final String LOGIN = "/View/Client/login.jsp";
        public static final String REGISTER = "/View/Client/register.jsp";
        public static final String HOME = "/View/Client/index.jsp";
        public static final String DASHBOARD = "";
        public static final String MYACCOUNT = "/View/Client/my-account.jsp";
        public static final String USERHOME = System.getProperty("user.home");
        public static final String UPLOADS = USERHOME + File.separator + "Uploads";
        public static final String AVATARS = UPLOADS + File.separator + "Avatars";
        public static final String CHANGE_PASSWORD = "/View/Client/change-password.jsp";
    }
}
