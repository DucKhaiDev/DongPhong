package Dao.deploy;

import Connect.DBConnect;
import Entity.WLItem;
import Services.deploy.ProductService;
import Services.deploy.WishlistService;
import com.oracle.wls.shaded.org.apache.regexp.RE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WLItemDao implements Dao.WLItemDao {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    private final ProductService productService = new ProductService();
    private final WishlistService wishlistService = new WishlistService();

    @Override
    public void insert(WLItem item) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("INSERT INTO [WLITEM](WLITEM_ID, PRO_ID, WL_ID) VALUES(?, ?, ?)");
            ps.setString(1, item.getWLITEM_ID());
            ps.setString(2, item.getPRO().getPRO_ID());
            ps.setString(3, item.getWL().getWL_ID());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }
    }

    @Override
    public void edit(WLItem item) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("UPDATE [WLITEM] SET PRO_ID = ?, WL_ID = ? WHERE WLITEM_ID = ?");
            ps.setString(1, item.getPRO().getPRO_ID());
            ps.setString(2, item.getWL().getWL_ID());
            ps.setString(3, item.getWLITEM_ID());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }
    }

    @Override
    public void delete(String WLITEM_ID) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("DELETE FROM [WLITEM] WHERE WLITEM_ID = ?");
            ps.setString(1, WLITEM_ID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }
    }

    @Override
    public WLItem getWLItem(String WLITEM_ID) {
        conn = DBConnect.getConnection();
        WLItem item = new WLItem();

        try {
            ps = conn.prepareStatement("SELECT * FROM [WLITEM] WHERE WLITEM_ID = ?");
            ps.setString(1, WLITEM_ID);
            rs = ps.executeQuery();
            rs.next();
            item.setWLITEM_ID(WLITEM_ID);
            item.setPRO(productService.getProduct(rs.getString("PRO_ID")));
            item.setWL(wishlistService.getWishlist(rs.getString("WL_ID")));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return item;
    }

    @Override
    public List<WLItem> getAll() {
        conn = DBConnect.getConnection();
        List<WLItem> items = new ArrayList<>();

        try {
            ps = conn.prepareStatement("SELECT * FROM [WLITEM]");
            rs = ps.executeQuery();
            while (rs.next()) {
                WLItem item = new WLItem();
                item.setWLITEM_ID(rs.getString("WLITEM_ID"));
                item.setPRO(productService.getProduct(rs.getString("PRO_ID")));
                item.setWL(wishlistService.getWishlist(rs.getString("WL_ID")));

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
