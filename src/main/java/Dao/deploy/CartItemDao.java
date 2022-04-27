package Dao.deploy;

import Connect.DBConnect;
import Dao.ICartItemDao;
import Entity.CartItem;
import Util.Constant;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartItemDao implements ICartItemDao {
    @Override
    public void insert(CartItem item) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("INSERT INTO [CARTITEM](QUANT, [VALUE], PRO_ID, CART_ID) VALUES(?, ?, ?, ?)");
            ps.setInt(1, item.getQuantity());
            ps.setBigDecimal(2, item.getValue());
            ps.setString(3, item.getProduct().getProductId());
            ps.setString(4, item.getCart().getCartId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(null, ps, conn);
        }
    }

    @Override
    public void edit(CartItem item) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("UPDATE [CARTITEM] SET QUANT = ?, [VALUE] = ?, PRO_ID = ?, CART_ID = ? WHERE CITEM_ID = ?");
            ps.setInt(1, item.getQuantity());
            ps.setBigDecimal(2, item.getValue());
            ps.setString(3, item.getProduct().getProductId());
            ps.setString(4, item.getCart().getCartId());
            ps.setInt(5, item.getCartItemId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(null, ps, conn);
        }
    }

    @Override
    public void delete(int cartItemId) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("DELETE FROM [CARTITEM] WHERE CITEM_ID = ?");
            ps.setInt(1, cartItemId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(null, ps, conn);
        }
    }

    @Override
    public void deleteAll(String cartId) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("DELETE FROM [CARTITEM] WHERE CART_ID = ?");
            ps.setString(1, cartId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(null, ps, conn);
        }
    }

    @Override
    public CartItem getCartItem(int cartItemId) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        CartItem item = new CartItem();

        try {
            ps = conn.prepareStatement("SELECT * FROM [CARTITEM] WHERE CITEM_ID = ?");
            ps.setInt(1, cartItemId);
            rs = ps.executeQuery();
            if (rs.next()) {
                item.setCartItemId(cartItemId);
                item.setQuantity(rs.getInt("QUANT"));
                item.setValue(rs.getBigDecimal("VALUE"));
                item.setProduct(Constant.Service.PRODUCT_SERVICE.getProduct(rs.getString("PRO_ID").trim()));
                item.setCart(Constant.Service.CART_SERVICE.getCart(rs.getString("CART_ID")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return item;
    }

    @Override
    public CartItem getCartItem(String productId, String cartId) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        CartItem cartItem = new CartItem();

        try {
            ps = conn.prepareStatement("SELECT * FROM [CARTITEM] WHERE PRO_ID = ? AND CART_ID = ?");
            ps.setString(1, productId);
            ps.setString(2, cartId);
            rs = ps.executeQuery();
            if (rs.next()) {
                cartItem.setCartItemId(rs.getInt("CITEM_ID"));
                cartItem.setQuantity(rs.getInt("QUANT"));
                cartItem.setValue(rs.getBigDecimal("VALUE"));
                cartItem.setProduct(Constant.Service.PRODUCT_SERVICE.getProduct(rs.getString("PRO_ID")));
                cartItem.setCart(Constant.Service.CART_SERVICE.getCart(rs.getString("CART_ID")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return cartItem;
    }

    @Override
    public List<CartItem> getAll() {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<CartItem> items = new ArrayList<>();

        try {
            ps = conn.prepareStatement("SELECT * FROM [CARTITEM]");
            rs = ps.executeQuery();
            while (rs.next()) {
                CartItem item = new CartItem();
                item.setCartItemId(rs.getInt("CITEM_ID"));
                item.setQuantity(rs.getInt("QUANT"));
                item.setValue(rs.getBigDecimal("VALUE"));
                item.setProduct(Constant.Service.PRODUCT_SERVICE.getProduct(rs.getString("PRO_ID").trim()));
                item.setCart(Constant.Service.CART_SERVICE.getCart(rs.getString("CART_ID")));

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
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<CartItem> items = new ArrayList<>();

        try {
            ps = conn.prepareStatement("SELECT * FROM [CARTITEM] WHERE CART_ID = ?");
            ps.setString(1, cartId);
            rs = ps.executeQuery();
            while (rs.next()) {
                CartItem item = new CartItem();
                item.setCartItemId(rs.getInt("CITEM_ID"));
                item.setQuantity(rs.getInt("QUANT"));
                item.setValue(rs.getBigDecimal("VALUE"));
                item.setProduct(Constant.Service.PRODUCT_SERVICE.getProduct(rs.getString("PRO_ID").trim()));
                item.setCart(Constant.Service.CART_SERVICE.getCart(rs.getString("CART_ID")));

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
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

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

    @Override
    public int countSumQuantity(String cartId) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT COUNT(*) " +
                    "FROM dbo.CARTITEM " +
                    "WHERE CART_ID = ? " +
                    "GROUP BY CART_ID");
            ps.setString(1, cartId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return 0;
    }

    @Override
    public BigDecimal getSubTotal(String cartId) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT SUM(VALUE) " +
                    "FROM dbo.CARTITEM " +
                    "WHERE CART_ID = ? " +
                    "GROUP BY CART_ID");
            ps.setString(1, cartId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getBigDecimal(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return BigDecimal.valueOf(0);
    }
}