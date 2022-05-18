package Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class ProImage implements Serializable {
    private final static long serialVersionUID = 1L;

    private int imageId;
    private String imageName;
    private Product product;

    public ProImage(String imageName, Product product) {
        this.imageName = imageName;
        this.product = product;
    }
}