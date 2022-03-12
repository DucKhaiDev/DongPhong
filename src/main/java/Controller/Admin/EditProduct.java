package Controller.Admin;

import Entity.Brand;
import Entity.Category;
import Entity.ProImage;
import Entity.Product;
import Services.deploy.BrandService;
import Services.deploy.CategoryService;
import Services.deploy.ProImageService;
import Services.deploy.ProductService;
import Util.Constant;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@WebServlet(name = "EditProduct", value = "/admin/product/edit")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class EditProduct extends HttpServlet {
    private final ProductService productService = new ProductService();
    private final CategoryService categoryService = new CategoryService();
    private final BrandService brandService = new BrandService();
    private final ProImageService imageService = new ProImageService();

    private String productId;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        productId = request.getParameter("id");
        Product product = productService.getProduct(productId);
        request.setAttribute("product", product);
        List<Category> categories = categoryService.getAll();
        request.setAttribute("categories", categories);
        List<Brand> brands = brandService.getAll();
        request.setAttribute("brands", brands);
        List<ProImage> images = imageService.getProImage(productId);
        request.setAttribute("images", images);
        request.getRequestDispatcher(Constant.Path.ADMIN_EDIT_PRODUCT).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Cập nhật sản phẩm
        Product product = productService.getProduct(productId);

        String productName = request.getParameter("productName");
        if (productName != null && !productName.trim().isEmpty()) {
            product.setProductName(productName);
        }

        product.setProductDescription(request.getParameter("productDescription"));
        product.setProductDimension(request.getParameter("productDimension"));
        product.setProductWeight(request.getParameter("productWeight"));
        product.setProductColor(request.getParameter("productColor"));

        int productQuantity = request.getParameter("productQuantity").isEmpty() ? 0 : Integer.parseInt(request.getParameter("productQuantity"));
        product.setProductQuantity(productQuantity);

        String productPrice = request.getParameter("productPrice");
        if (productPrice != null && !productPrice.trim().isEmpty()) {
            product.setProductPrice(productPrice);
        }

        String productCost = request.getParameter("productCost");
        if (productCost != null && !productCost.trim().isEmpty()) {
            product.setProductCost(productCost);
        }

        product.setCategory(categoryService.getCategory(request.getParameter("category")));
        product.setBrand(brandService.getBrand(request.getParameter("brand")));

        productService.edit(product);

        //Cập nhật hình ảnh sản phẩm
        List<ProImage> images = imageService.getProImage(productId);

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
                    imageService.edit(image);
                } else {
                    ProImage image = new ProImage(newName, product);
                    imageService.insert(image);
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
