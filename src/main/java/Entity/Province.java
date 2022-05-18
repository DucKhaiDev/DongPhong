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
public class Province implements Serializable {
    private final static long serialVersionUID = 1L;

    private int provinceId;
    private String provinceName;
    private double latitude;
    private double longitude;
}