package Dao.deploy;

import Connect.DBConnect;
import Dao.ICartDao;
import Entity.Cart;
import Services.deploy.UserService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDao implements ICartDao {
    private final UserService userService = new UserService();
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    @Override
    public void insert(Cart cart) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("INSERT INTO [CART](CART_ID, USER_ID) VALUES(?, ?)");
            ps.setString(1, cart.getCartId());
            ps.setString(2, cart.getUser().getUserId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }
    }

    @Override
    public void edit(Cart cart) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("UPDATE [CART] SET USER_ID = ? WHERE CART_ID = ?");
            ps.setString(1, cart.getUser().getUserId());
            ps.setString(2, cart.getCartId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }
    }

    @Override
    public void delete(String cartId) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("DELETE FROM [CART] WHERE CART_ID = ?");
            ps.setString(1, cartId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }
    }

    @Override
    public Cart getCart(String cartId) {
        conn = DBConnect.getConnection();
        Cart cart = new Cart();

        try {
            ps = conn.prepareStatement("SELECT * FROM [CART] WHERE CART_ID = ?");
            ps.setString(1, cartId);
            rs = ps.executeQuery();
            rs.next();
            cart.setCartId(rs.getString("CART_ID"));
            cart.setUser(userService.getUser(rs.getString("USER_ID")));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return cart;
    }

    @Override
    public Cart getCartByUser(String userId) {
        conn = DBConnect.getConnection();
        Cart cart = new Cart();

        try {
            ps = conn.prepareStatement("SELECT * FROM [CART] WHERE USER_ID = ?");
            ps.setString(1, userId);
            rs = ps.executeQuery();
            rs.next();
            cart.setCartId(rs.getString("CART_ID"));
            cart.setUser(userService.getUser(userId));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return cart;
    }

    @Override
    public List<Cart> getAll() {
        conn = DBConnect.getConnection();
        List<Cart> carts = new ArrayList<>();

        try {
            ps = conn.prepareStatement("SELECT * FROM [CART]");
            rs = ps.executeQuery();
            while (rs.next()) {
                Cart cart = new Cart();
                cart.setCartId(rs.getString("CART_ID"));
                cart.setUser(userService.getUser(rs.getString("USER_ID")));

                carts.add(cart);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return carts;
    }

    @Override
    public Cart getLastCart(String userId) {
        conn = DBConnect.getConnection();
        Cart cart = new Cart();

        try {
            ps = conn.prepareStatement("SELECT TOP(1) * " +
                                                "FROM dbo.CART " +
                                                "WHERE USER_ID = ? ORDER BY CART_ID DESC");
            ps.setString(1, userId);
            rs = ps.executeQuery();
            rs.next();
            cart.setCartId(rs.getString("CART_ID"));
            cart.setUser(userService.getUser(rs.getString("USER_ID")));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return cart;
    }
}
