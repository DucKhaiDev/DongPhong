package Tools;

import Entity.ProImage;
import Entity.User;
import Services.deploy.ProImageService;
import Services.deploy.UserService;
import Util.Constant;

import java.io.File;
import java.util.List;
import java.util.Objects;

public class ReleaseMemory {
    private static final UserService userService = new UserService();
    private static final ProImageService imageService = new ProImageService();

    public static void deleteUnusedImg() {
        List<User> users = userService.getAll();
        List<ProImage> images = imageService.getAll();
        
        //Xóa avatar không còn sử dụng
        File avatars = new File(Constant.Path.AVATARS);
        if (!avatars.exists()) {
            if (!avatars.mkdir()) {
                System.out.println("Directory creation failed.");
            }
        }

        for (File fileEntry : Objects.requireNonNull(avatars.listFiles())) {
            boolean deleted = true;
            for (User user : users) {
                if (fileEntry.getName().equals(user.getAvatar())) {
                    deleted = false;
                    break;
                }
            }

            if (deleted) {
                if (!fileEntry.delete()) {
                    System.out.println("Delete file failed.");
                }
            }
        }
        
        //Xóa ảnh sản phẩm không còn sử dụng
        File product_images = new File(Constant.Path.PRODUCT_IMAGES);
        if (!product_images.exists()) {
            if (!product_images.mkdir()) {
                System.out.println("Directory creation failed.");
            }
        }

        for (File fileEntry : Objects.requireNonNull(product_images.listFiles())) {
            boolean deleted = true;
            for (ProImage image : images) {
                if (fileEntry.getName().equals(image.getImageName())) {
                    deleted = false;
                    break;
                }
            }

            if (deleted) {
                if (!fileEntry.delete()) {
                    System.out.println("Delete file failed.");
                }
            }
        }
    }
}
