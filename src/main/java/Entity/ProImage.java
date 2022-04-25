package Entity;

import java.io.Serializable;

public class ProImage implements Serializable {
    private final static long serialVersionUID = 1L;

    private int imageId;
    private String imageName;
    private Product product;

    public ProImage() {
    }

    public ProImage(String imageName, Product product) {
        this.imageName = imageName;
        this.product = product;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
