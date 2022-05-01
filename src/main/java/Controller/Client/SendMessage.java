package Controller.Client;

import Entity.Message;
import Util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

@WebServlet(name = "SendMessage", value = "/send-message")
public class SendMessage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String msg_email = request.getParameter("email");
        String msg_content = request.getParameter("msg");
        Timestamp msg_date = new Timestamp(new Date().getTime());
        Constant.Service.MESSAGE_SERVICE.insert(new Message(msg_content, msg_email, msg_date));
        request.getSession().setAttribute("sendSuccess", "Cảm ơn bạn đã gửi ý kiến đóng góp để chúng tôi phát triển tốt hơn!");
        response.sendRedirect(request.getContextPath() + "/contact");
    }
}
