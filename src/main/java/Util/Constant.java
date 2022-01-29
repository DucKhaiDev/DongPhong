package Util;

import Controller.WelcomeController;

import java.io.File;

public class Constant {
    public static final String SESSION_USERNAME_OR_EMAIL = "username_or_email";
    public static final String COOKIE_REMEMBER = "username_or_email";

    public static class Path {
        public static final String LOGIN = "/View/Client/views/login.jsp";
        public static final String REGISTER = "/View/Client/views/register.jsp";
        public static final String HOME = "/View/Client/views/index.jsp";
        public static final String DASHBOARD = "";
        public static final String MYACCOUNT = "/View/Client/views/my-account.jsp";
        public static final String APPPATH = WelcomeController.APPPATH;
        public static final String UPLOADS = APPPATH + "Uploads";
        public static final String AVATARS = UPLOADS + File.separator + "Avatars";
        public static final String CHANGE_PASSWORD = "/View/Client/views/change-password.jsp";
    }
}
