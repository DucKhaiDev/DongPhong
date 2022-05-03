package Controller.Admin;

import Controller.Client.LoginController;
import Entity.ProImage;
import Entity.Product;
import Util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "EditProduct", value = "/admin/product/edit")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class EditProduct extends HttpServlet {
    private Product product;
    private String productId;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!LoginController.checkLoginAdmin(request, response)) {
            return;
        }

        productId = request.getParameter("id");
        product = Constant.Service.PRODUCT_SERVICE.getProduct(productId);

        request.setAttribute("product", product);
        request.setAttribute("categories", Constant.Service.CATEGORY_SERVICE.getAll());
        request.setAttribute("brands", Constant.Service.BRAND_SERVICE.getAll());
        request.setAttribute("images", Constant.Service.PRO_IMAGE_SERVICE.getProImage(productId));

        request.getRequestDispatcher(Constant.Path.ADMIN_EDIT_PRODUCT).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Update product
        String productName = request.getParameter("productName");
        if (productName != null && !productName.trim().isEmpty()) {
            product.setProductName(productName);
        }

        product.setProductDescription(request.getParameter("productDescription"));
        product.setProductDimension(request.getParameter("productDimension"));
        product.setProductWeight(request.getParameter("productWeight"));
        product.setProductColor(request.getParameter("productColor"));

        String quantityParam = request.getParameter("productQuantity");
        int productQuantity = quantityParam.isEmpty() ? 0 : Integer.parseInt(quantityParam);
        product.setProductQuantity(productQuantity);

        String productPrice = request.getParameter("productPrice");
        if (productPrice != null && !productPrice.trim().isEmpty()) {
            product.setProductPrice(new BigDecimal(productPrice));
        }

        String productCost = request.getParameter("productCost");
        if (productCost != null && !productCost.trim().isEmpty()) {
            product.setProductCost(new BigDecimal(productCost));
        }

        product.setCategory(Constant.Service.CATEGORY_SERVICE.getCategory(request.getParameter("category")));
        product.setBrand(Constant.Service.BRAND_SERVICE.getBrand(request.getParameter("brand")));

        Constant.Service.PRODUCT_SERVICE.edit(product);

        //Update product images
        List<ProImage> images = Constant.Service.PRO_IMAGE_SERVICE.getProImage(productId);

        String savePath = Constant.Path.PRODUCT_IMAGES;

        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            if (!fileSaveDir.mkdir()) {
                System.out.println("Directory creation failed.");
            }
        }

        String fileName, newName;

        for (Part part : request.getParts()) {
            fileName = extractFileName(part);
            fileName = new File(fileName).getName();

            if (fileName.length() > 0) {
                part.write(savePath + File.separator + fileName);
                newName = UUID.randomUUID() + "." + FilenameUtils.getExtension(fileName);
                renameFile(fileName, newName);

                int index = Integer.parseInt(part.getName());
                if (index < images.size() + 1) {
                    ProImage image = images.get(index - 1);
                    image.setImageName(newName);
                    Constant.Service.PRO_IMAGE_SERVICE.edit(image);
                } else {
                    ProImage image = new ProImage(newName, product);
                    Constant.Service.PRO_IMAGE_SERVICE.insert(image);
                }
            }
        }

        response.sendRedirect(request.getContextPath() + "/admin/product/edit?id=" + productId);
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");

        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }

        return "";
    }

    private void renameFile(String oldFile, String newFile) {
        Path file = Paths.get(Constant.Path.PRODUCT_IMAGES + File.separator + oldFile);

        try {
            Files.move(file, file.resolveSibling(newFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}