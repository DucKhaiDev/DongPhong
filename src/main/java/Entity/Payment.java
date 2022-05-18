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
public class Payment implements Serializable {
    private static final long serialVersionUID = 1L;

    private String paymentId;
    private String paymentMethod;
    private boolean paymentStatus;
}