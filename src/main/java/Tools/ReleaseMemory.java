package Tools;

import Entity.ProImage;
import Entity.User;
import Services.deploy.ProImageService;
import Services.deploy.UserService;
import Util.Constant;

import java.io.File;
import java.util.List;

public class ReleaseMemory {
    private static UserService userService = new UserService();
    private static ProImageService imageService = new ProImageService();

    public static void deleteUnusedImg() {
        List<User> users = userService.getAll();
        List<ProImage> images = imageService.getAll();
        
        //Xóa avatar không còn sử dụng
        File avatars = new File(Constant.Path.AVATARS);
        if (!avatars.exists()) {
            avatars.mkdir();
        }

        for (File fileEntry : avatars.listFiles()) {
            boolean deleted = true;
            for (User user : users) {
                if (fileEntry.getName().equals(user.getAVATAR())) {
                    deleted = false;
                    break;
                }
            }

            if (deleted) {
                fileEntry.delete();
            }
        }
        
        //Xóa ảnh sản phẩm không còn sử dụng
        File product_images = new File(Constant.Path.PRODUCT_IMAGES);
        if (!product_images.exists()) {
            product_images.mkdir();
        }

        for (File fileEntry : product_images.listFiles()) {
            boolean deleted = true;
            for (ProImage image : images) {
                if (fileEntry.getName().equals(image.getIMG_NAME())) {
                    deleted = false;
                    break;
                }
            }

            if (deleted) {
                fileEntry.delete();
            }
        }
    }
}
