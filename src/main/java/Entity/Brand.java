package Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Brand implements Serializable {
    private static final long serialVersionUID = 1L;

    private String brandId;
    private String brandName;
    private String brandDescription;
}