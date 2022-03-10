package Dao.deploy;

import Connect.DBConnect;
import Entity.CartItem;
import Services.deploy.CartService;
import Services.deploy.ProductService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartItemDao implements Dao.CartItemDao {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    private final ProductService productService = new ProductService();
    private final CartService cartService = new CartService();

    @Override
    public void insert(CartItem item) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("INSERT INTO [CARTITEM](QUANT, [VALUE], PRO_ID, CART_ID) VALUES(?, ?, ?, ?)");
            ps.setInt(1, item.getQuantity());
            ps.setString(2, item.getValue());
            ps.setString(3, item.getProduct().getProductId());
            ps.setString(4, item.getCart().getCartId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }
    }

    @Override
    public void edit(CartItem item) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("UPDATE [CARTITEM] SET QUANT = ?, [VALUE] = ?, PRO_ID = ?, CART_ID = ? WHERE CITEM_ID = ?");
            ps.setInt(1, item.getQuantity());
            ps.setString(2, item.getValue());
            ps.setString(3, item.getProduct().getProductId());
            ps.setString(4, item.getCart().getCartId());
            ps.setInt(5, item.getCartItemId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }
    }

    @Override
    public void delete(int cartItemId) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("DELETE FROM [CARTITEM] WHERE CITEM_ID = ?");
            ps.setInt(1, cartItemId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }
    }

    @Override
    public CartItem getCartItem(int cartItemId) {
        conn = DBConnect.getConnection();
        CartItem item = new CartItem();

        try {
            ps = conn.prepareStatement("SELECT * FROM [CARTITEM] WHERE CITEM_ID = ?");
            ps.setInt(1, cartItemId);
            rs = ps.executeQuery();
            rs.next();
            item.setCartItemId(cartItemId);
            item.setQuantity(rs.getInt("QUANT"));
            item.setValue(rs.getString("VALUE"));
            item.setProduct(productService.getProduct(rs.getString("PRO_ID").trim()));
            item.setCart(cartService.getCart(rs.getString("CART_ID")));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return item;
    }

    @Override
    public CartItem getCartItem(String productId, String cartId) {
        conn = DBConnect.getConnection();
        CartItem cartItem = new CartItem();

        try {
            ps = conn.prepareStatement("SELECT * FROM [CARTITEM] WHERE PRO_ID = ? AND CART_ID = ?");
            ps.setString(1, productId);
            ps.setString(2, cartId);
            rs = ps.executeQuery();
            rs.next();
            cartItem.setCartItemId(rs.getInt("CITEM_ID"));
            cartItem.setQuantity(rs.getInt("QUANT"));
            cartItem.setValue(rs.getString("VALUE"));
            cartItem.setProduct(productService.getProduct(rs.getString("PRO_ID")));
            cartItem.setCart(cartService.getCart(rs.getString("CART_ID")));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return cartItem;
    }

    @Override
    public List<CartItem> getAll() {
        conn = DBConnect.getConnection();
        List<CartItem> items = new ArrayList<>();

        try {
            ps = conn.prepareStatement("SELECT * FROM [CARTITEM]");
            rs = ps.executeQuery();
            while (rs.next()) {
                CartItem item = new CartItem();
                item.setCartItemId(rs.getInt("CITEM_ID"));
                item.setQuantity(rs.getInt("QUANT"));
                item.setValue(rs.getString("VALUE"));
                item.setProduct(productService.getProduct(rs.getString("PRO_ID").trim()));
                item.setCart(cartService.getCart(rs.getString("CART_ID")));

                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return items;
    }

    @Override
    public List<CartItem> getItemByCart(String cartId) {
        conn = DBConnect.getConnection();
        List<CartItem> items = new ArrayList<>();

        try {
            ps = conn.prepareStatement("SELECT * FROM [CARTITEM] WHERE CART_ID = ?");
            ps.setString(1, cartId);
            rs = ps.executeQuery();
            while (rs.next()) {
                CartItem item = new CartItem();
                item.setCartItemId(rs.getInt("CITEM_ID"));
                item.setQuantity(rs.getInt("QUANT"));
                item.setValue(rs.getString("VALUE"));
                item.setProduct(productService.getProduct(rs.getString("PRO_ID").trim()));
                item.setCart(cartService.getCart(rs.getString("CART_ID")));

                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return items;
    }

    @Override
    public boolean checkExistItem(String productId, String cartId) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT * FROM [CARTITEM] WHERE PRO_ID = ? AND CART_ID = ?");
            ps.setString(1, productId);
            ps.setString(2, cartId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return false;
    }
}