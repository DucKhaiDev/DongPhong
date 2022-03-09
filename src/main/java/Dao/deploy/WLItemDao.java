package Dao.deploy;

import Connect.DBConnect;
import Entity.WLItem;
import Services.deploy.ProductService;
import Services.deploy.WishListService;

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
    private final WishListService wishlistService = new WishListService();

    @Override
    public void insert(WLItem item) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("INSERT INTO [WLITEM](PRO_ID, WL_ID) VALUES(?, ?)");
            ps.setString(1, item.getProduct().getProductId());
            ps.setString(2, item.getWishList().getWishListId());

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
            ps.setString(1, item.getProduct().getProductId());
            ps.setString(2, item.getWishList().getWishListId());
            ps.setInt(3, item.getWlItemId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }
    }

    @Override
    public void delete(int wlItemId) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("DELETE FROM [WLITEM] WHERE WLITEM_ID = ?");
            ps.setInt(1, wlItemId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }
    }

    @Override
    public WLItem getWLItem(int wlItemId) {
        conn = DBConnect.getConnection();
        WLItem item = new WLItem();

        try {
            ps = conn.prepareStatement("SELECT * FROM [WLITEM] WHERE WLITEM_ID = ?");
            ps.setInt(1, wlItemId);
            rs = ps.executeQuery();
            rs.next();
            item.setWlItemId(wlItemId);
            item.setProduct(productService.getProduct(rs.getString("PRO_ID").trim()));
            item.setWishList(wishlistService.getWishlist(rs.getString("WL_ID").trim()));
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
                item.setWlItemId(rs.getInt("WLITEM_ID"));
                item.setProduct(productService.getProduct(rs.getString("PRO_ID").trim()));
                item.setWishList(wishlistService.getWishlist(rs.getString("WL_ID")));

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
    public List<WLItem> getItemByWishList(String wishListId) {
        conn = DBConnect.getConnection();
        List<WLItem> items = new ArrayList<>();

        try {
            ps = conn.prepareStatement("SELECT * FROM [WLITEM] WHERE WL_ID = ?");
            ps.setString(1, wishListId);
            rs = ps.executeQuery();
            while (rs.next()) {
                WLItem item = new WLItem();
                item.setWlItemId(rs.getInt("WLITEM_ID"));
                item.setProduct(productService.getProduct(rs.getString("PRO_ID").trim()));
                item.setWishList(wishlistService.getWishlist(wishListId));

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
    public boolean checkExistItem(String productId, String wishListId) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT * FROM [WLITEM] WHERE PRO_ID = ? AND WL_ID = ?");
            ps.setString(1, productId);
            ps.setString(2, wishListId);
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
