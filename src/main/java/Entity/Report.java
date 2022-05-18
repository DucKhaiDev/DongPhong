package Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Report implements Serializable {
    private static final long serialVersionUID = 1L;

    private int monthDate;
    private int yearDate;
    private int countId;
    private BigDecimal sumSubTotal;
    private BigDecimal sumDiscount;
    private BigDecimal sumTax;
    private BigDecimal sumShipping;
    private BigDecimal sumTotal;
}