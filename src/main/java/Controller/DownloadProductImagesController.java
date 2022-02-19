package Controller;

import Util.Constant;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet(name = "DownloadProductImagesController", value = "/images/product-images")
public class DownloadProductImagesController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getParameter("fname");
        File file = new File(Constant.Path.PRODUCT_IMAGES + File.separator + fileName);
        response.setContentType("image/jpeg");
        if (file.exists()) {
            IOUtils.copy(new FileInputStream(file), response.getOutputStream());
        }
    }
}
