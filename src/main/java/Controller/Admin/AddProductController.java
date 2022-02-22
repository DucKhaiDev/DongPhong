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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "AddProductController", value = "/admin/product/add")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class AddProductController extends HttpServlet {
    private ProductService productService = new ProductService();
    private CategoryService categoryService = new CategoryService();
    private BrandService brandService = new BrandService();
    private ProImageService imageService = new ProImageService();

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
        //Thêm sản phẩm
        Product product = new Product();

        String PRO_ID = request.getParameter("pro_id");
        product.setPRO_ID(PRO_ID);

        String PRO_NAME = request.getParameter("pro_name");
        product.setPRO_NAME(PRO_NAME);

        String PRO_DES = request.getParameter("pro_des");
        product.setPRO_DES(PRO_DES);

        int PRO_QUANT = request.getParameter("pro_quant").isEmpty() ? 0 : Integer.parseInt(request.getParameter("pro_quant"));
        product.setPRO_QUANT(PRO_QUANT);

        String PRO_PRICE = request.getParameter("pro_price");
        product.setPRO_PRICE(PRO_PRICE);

        String PRO_COST = request.getParameter("pro_cost");
        product.setPRO_COST(PRO_COST);

        Category category = categoryService.getCategory(request.getParameter("cat"));
        product.setCAT(category);

        Brand brand = brandService.getBrand(request.getParameter("bra"));
        product.setBRA(brand);

        productService.insert(product);

        //Thêm hình ảnh sản phẩm
        String savePath = Constant.Path.PRODUCT_IMAGES;

        File fileSaveDir = new File(savePath);
        if (!fileSaveDir .exists()) {
            fileSaveDir.mkdir();
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
