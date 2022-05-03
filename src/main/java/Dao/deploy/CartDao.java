package Dao.deploy;

import Connect.DBConnect;
import Dao.ICartDao;
import Entity.Cart;
import Util.Constant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDao implements ICartDao {
    @Override
    public void insert(Cart cart) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("INSERT INTO [CART](CART_ID, USER_ID) VALUES(?, ?)");
            ps.setString(1, cart.getCartId());
            ps.setString(2, cart.getUser().getUserId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(null, ps, conn);
        }
    }

    @Override
    public void edit(Cart cart) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("UPDATE [CART] SET USER_ID = ? WHERE CART_ID = ?");
            ps.setString(1, cart.getUser().getUserId());
            ps.setString(2, cart.getCartId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(null, ps, conn);
        }
    }

    @Override
    public void delete(String cartId) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("DELETE FROM [CART] WHERE CART_ID = ?");
            ps.setString(1, cartId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(null, ps, conn);
        }
    }

    @Override
    public Cart getCart(String cartId) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Cart cart = new Cart();

        try {
            ps = conn.prepareStatement("SELECT * FROM [CART] WHERE CART_ID = ?");
            ps.setString(1, cartId);
            rs = ps.executeQuery();
            if (rs.next()) {
                cart.setCartId(rs.getString("CART_ID"));
                cart.setUser(Constant.Service.USER_SERVICE.getUser(rs.getString("USER_ID")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return cart;
    }

    @Override
    public List<Cart> getCartByUser(String userId) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Cart> carts = new ArrayList<>();

        try {
            ps = conn.prepareStatement("SELECT * FROM [CART] WHERE USER_ID = ?");
            ps.setString(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cart cart = new Cart();
                cart.setCartId(rs.getString("CART_ID"));
                cart.setUser(Constant.Service.USER_SERVICE.getUser(userId));

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
    public List<Cart> getAll() {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Cart> carts = new ArrayList<>();

        try {
            ps = conn.prepareStatement("SELECT * FROM [CART]");
            rs = ps.executeQuery();
            while (rs.next()) {
                Cart cart = new Cart();
                cart.setCartId(rs.getString("CART_ID"));
                cart.setUser(Constant.Service.USER_SERVICE.getUser(rs.getString("USER_ID")));

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
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Cart cart = new Cart();

        try {
            ps = conn.prepareStatement("SELECT TOP(1) * " +
                    "FROM dbo.CART " +
                    "WHERE USER_ID = ? ORDER BY CART_ID DESC");
            ps.setString(1, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                cart.setCartId(rs.getString("CART_ID"));
                cart.setUser(Constant.Service.USER_SERVICE.getUser(rs.getString("USER_ID")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return cart;
    }
}