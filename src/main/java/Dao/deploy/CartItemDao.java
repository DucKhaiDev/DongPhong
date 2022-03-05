package Dao.deploy;

import Connect.DBConnect;
import Entity.CartItem;
import Services.deploy.CartService;
import Services.deploy.ProductService;
import com.oracle.wls.shaded.org.apache.regexp.RE;

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
            ps = conn.prepareStatement("INSERT INTO [CARTITEM](CITEM_ID, QUANT, [VALUE], PRO_ID, CART_ID) VALUES(?, ?, ?, ?, ?)");
            ps.setString(1, item.getCITEM_ID());
            ps.setInt(2, item.getQUANT());
            ps.setString(3, item.getVALUE());
            ps.setString(4, item.getPRO().getPRO_ID());
            ps.setString(5, item.getCART().getCART_ID());

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
            ps.setInt(1, item.getQUANT());
            ps.setString(2, item.getVALUE());
            ps.setString(3, item.getPRO().getPRO_ID());
            ps.setString(4, item.getCART().getCART_ID());
            ps.setString(5, item.getCITEM_ID());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }
    }

    @Override
    public void delete(String CITEM_ID) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("DELETE FROM [CARTITEM] WHERE CITEM_ID = ?");
            ps.setString(1, CITEM_ID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }
    }

    @Override
    public CartItem getCartItem(String CITEM_ID) {
        conn = DBConnect.getConnection();
        CartItem item = new CartItem();

        try {
            ps = conn.prepareStatement("SELECT * FROM [CARTITEM] WHERE CITEM_ID = ?");
            ps.setString(1, CITEM_ID);
            rs = ps.executeQuery();
            item.setCITEM_ID(CITEM_ID);
            item.setQUANT(rs.getInt("QUANT"));
            item.setVALUE(rs.getString("VALUE"));
            item.setPRO(productService.getProduct(rs.getString("PRO_ID")));
            item.setCART(cartService.getCart(rs.getString("CART_ID")));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return item;
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
                item.setCITEM_ID(rs.getString("CITEM_ID"));
                item.setQUANT(rs.getInt("QUANT"));
                item.setVALUE(rs.getString("VALUE"));
                item.setPRO(productService.getProduct(rs.getString("PRO_ID")));
                item.setCART(cartService.getCart(rs.getString("CART_ID")));

                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return items;
    }
}
