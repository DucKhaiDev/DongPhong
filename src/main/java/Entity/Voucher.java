package Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Voucher implements Serializable {
    private static final long serialVersionUID = 1L;

    private String voucherId;
    private int minProduct;
    private BigDecimal minValue;
    private double discount;
    private BigDecimal discountMax;
    private int quantity;
    private Timestamp fromDate;
    private Timestamp toDate;
}