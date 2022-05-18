package Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Setter
public class Review implements Serializable {
    private static final long serialVersionUID = 1L;

    private int reviewId;
    private User user;
    private Product product;
    private double reviewRate;
    private String reviewContent;
    private Timestamp reviewDate;
    private boolean isRate;

    public Review(User user, Product product, double reviewRate, String reviewContent, Timestamp reviewDate, boolean isRate) {
        this.user = user;
        this.product = product;
        this.reviewRate = reviewRate;
        this.reviewContent = reviewContent;
        this.reviewDate = reviewDate;
        this.isRate = isRate;
    }
}