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
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "AddProduct", value = "/admin/product/add")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class AddProduct extends HttpServlet {
    private final ProductService productService = new ProductService();
    private final CategoryService categoryService = new CategoryService();
    private final BrandService brandService = new BrandService();
    private final ProImageService imageService = new ProImageService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories = categoryService.getAll();
        request.setAttribute("categories", categories);
        List<Brand> brands = brandService.getAll();
        request.setAttribute("brands", brands);
        request.getRequestDispatcher(Constant.Path.ADMIN_ADD_PRODUCT).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Add product
        String productId = request.getParameter("productId");
        if (productService.checkExistId(productId)) {
            String existId = "Mã sản phẩm đã tồn tại!";
            request.setAttribute("existId", existId);
            request.getRequestDispatcher(Constant.Path.ADMIN_ADD_PRODUCT).forward(request, response);
            return;
        }

        String productName = request.getParameter("productName");
        String productDescription = request.getParameter("productDescription");
        String productDimension = request.getParameter("productDimension");
        String productWeight = request.getParameter("productWeight");
        String productMaterial = request.getParameter("productMaterial");
        String productColor = request.getParameter("productColor");
        int productQuantity = request.getParameter("productQuantity").isEmpty() ? 0 : Integer.parseInt(request.getParameter("productQuantity"));
        String productPrice = request.getParameter("productPrice");
        String productCost = request.getParameter("productCost");
        Category category = categoryService.getCategory(request.getParameter("category"));
        Brand brand = brandService.getBrand(request.getParameter("brand"));
        Product product = new Product(productId, productName, productDescription, productDimension, productWeight, productMaterial, productColor, new BigDecimal(productPrice), new BigDecimal(productCost), productQuantity, category, brand);

        productService.insert(product);

        //Add product images
        String savePath = Constant.Path.PRODUCT_IMAGES;

        File fileSaveDir = new File(savePath);
        if (!fileSaveDir .exists()) {
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
                ProImage image = new ProImage(newName, product);
                imageService.insert(image);
            }
        }

        response.sendRedirect(request.getContextPath() + "/admin/product");
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
