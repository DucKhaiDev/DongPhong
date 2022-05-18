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
public class WishList implements Serializable {
    private static final long serialVersionUID = 1L;

    private String wishListId;
    private User user;
}