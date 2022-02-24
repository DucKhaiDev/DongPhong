package Controller.Admin;

import Entity.Brand;
import Entity.Category;
import Entity.ProImage;
import Entity.Product;
import Services.deploy.BrandService;
import Services.deploy.CategoryService;
import Services.deploy.ProImageService;
import Services.deploy.ProductService;
import Tools.Pair;
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

@WebServlet(name = "EditProductController", value = "/admin/product/edit")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class EditProductController extends HttpServlet {
    private final ProductService productService = new ProductService();
    private final CategoryService categoryService = new CategoryService();
    private final BrandService brandService = new BrandService();
    private final ProImageService imageService = new ProImageService();

    private String PRO_ID;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PRO_ID = request.getParameter("id");
        Product product = productService.getProduct(PRO_ID);
        request.setAttribute("product", product);
        List<Category> categories = categoryService.getAll();
        request.setAttribute("categories", categories);
        List<Brand> brands = brandService.getAll();
        request.setAttribute("brands", brands);
        List<ProImage> images = imageService.getProImage(PRO_ID);
        request.setAttribute("images", images);
        request.getRequestDispatcher(Constant.Path.ADMIN_EDIT_PRODUCT).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Cập nhật sản phẩm
        Product product = productService.getProduct(PRO_ID);

        String PRO_NAME = request.getParameter("pro_name");
        if (PRO_NAME != null && !PRO_NAME.trim().isEmpty()) {
            product.setPRO_NAME(PRO_NAME);
        }

        product.setPRO_DES(request.getParameter("pro_des"));

        int PRO_QUANT = request.getParameter("pro_quant").isEmpty() ? 0 : Integer.parseInt(request.getParameter("pro_quant"));
        product.setPRO_QUANT(PRO_QUANT);

        String PRO_PRICE = request.getParameter("pro_price");
        if (PRO_PRICE != null && !PRO_PRICE.trim().isEmpty()) {
            product.setPRO_PRICE(PRO_PRICE);
        }

        String PRO_COST = request.getParameter("pro_cost");
        if (PRO_COST != null && !PRO_COST.trim().isEmpty()) {
            product.setPRO_COST(PRO_COST);
        }

        product.setCAT(categoryService.getCategory(request.getParameter("cat")));
        product.setBRA(brandService.getBrand(request.getParameter("bra")));

        productService.edit(product);

        //Cập nhật hình ảnh sản phẩm
        List<ProImage> images = imageService.getProImage(PRO_ID);

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

                int index = Integer.parseInt(part.getName());
                if (index < images.size() + 1) {
                    ProImage image = images.get(index - 1);
                    image.setIMG_NAME(newName);
                    imageService.edit(image);
                } else {
                    ProImage image = new ProImage(newName, product);
                    imageService.insert(image);
                }
            }
        }

        response.sendRedirect(request.getContextPath() + "/admin/product/edit?id=" + PRO_ID);
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
