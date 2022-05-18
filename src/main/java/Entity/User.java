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
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private String address;
    private String phone;
    private String avatar;
    private boolean role;
    private boolean vc_chaomung;

    //Constructor for register
    public User(String userId, String username, String password, String email) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = true;
    }
}