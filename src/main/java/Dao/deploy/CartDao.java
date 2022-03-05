package Dao.deploy;

import Connect.DBConnect;
import Entity.Cart;
import Services.deploy.CustomerService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDao implements Dao.CartDao {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    CustomerService customerService = new CustomerService();

    @Override
    public void insert(Cart cart) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("INSERT INTO [CART](CART_ID, CUS_ID) VALUES(?, ?)");
            ps.setString(1, cart.getCART_ID());
            ps.setString(2, cart.getCUS().getCUS_ID());

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
            ps = conn.prepareStatement("UPDATE [CART] SET CUS_ID = ? WHERE CART_ID = ?");
            ps.setString(1, cart.getCUS().getCUS_ID());
            ps.setString(2, cart.getCART_ID());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }
    }

    @Override
    public void delete(String CART_ID) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("DELETE FROM [CART] WHERE CART_ID = ?");
            ps.setString(1, CART_ID);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }
    }

    @Override
    public Cart getCart(String CART_ID) {
        conn = DBConnect.getConnection();
        Cart cart = new Cart();

        try {
            ps = conn.prepareStatement("SELECT * FROM [CART] WHERE CART_ID = ?");
            ps.setString(1, CART_ID);

            rs = ps.executeQuery();
            rs.next();
            cart.setCART_ID(rs.getString("WL_ID"));
            cart.setCUS(customerService.getCustomer(rs.getString("CUS_ID")));
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
                cart.setCART_ID(rs.getString("CART_ID"));
                cart.setCUS(customerService.getCustomer(rs.getString("CUS_ID")));

                carts.add(cart);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return carts;
    }
}
