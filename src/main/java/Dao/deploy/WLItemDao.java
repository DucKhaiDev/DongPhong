package Dao.deploy;

import Connect.DBConnect;
import Dao.IWLItemDao;
import Entity.WLItem;
import Util.Constant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WLItemDao implements IWLItemDao {
    @Override
    public void insert(WLItem item) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("INSERT INTO [WLITEM](PRO_ID, WL_ID) VALUES(?, ?)");
            ps.setString(1, item.getProduct().getProductId());
            ps.setString(2, item.getWishList().getWishListId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(null, ps, conn);
        }
    }

    @Override
    public void edit(WLItem item) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("UPDATE [WLITEM] SET PRO_ID = ?, WL_ID = ? WHERE WLITEM_ID = ?");
            ps.setString(1, item.getProduct().getProductId());
            ps.setString(2, item.getWishList().getWishListId());
            ps.setInt(3, item.getWlItemId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(null, ps, conn);
        }
    }

    @Override
    public void delete(int wlItemId) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("DELETE FROM [WLITEM] WHERE WLITEM_ID = ?");
            ps.setInt(1, wlItemId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(null, ps, conn);
        }
    }

    @Override
    public void deleteAll(String wishListId) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("DELETE FROM [WLITEM] WHERE WL_ID = ?");
            ps.setString(1, wishListId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(null, ps, conn);
        }
    }

    @Override
    public WLItem getWLItem(int wlItemId) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        WLItem item = new WLItem();

        try {
            ps = conn.prepareStatement("SELECT * FROM [WLITEM] WHERE WLITEM_ID = ?");
            ps.setInt(1, wlItemId);
            rs = ps.executeQuery();
            if (rs.next()) {
                item.setWlItemId(wlItemId);
                item.setProduct(Constant.Service.PRODUCT_SERVICE.getProduct(rs.getString("PRO_ID").trim()));
                item.setWishList(Constant.Service.WISH_LIST_SERVICE.getWishlist(rs.getString("WL_ID").trim()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return item;
    }

    @Override
    public List<WLItem> getAll() {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<WLItem> items = new ArrayList<>();

        try {
            ps = conn.prepareStatement("SELECT * FROM [WLITEM]");
            rs = ps.executeQuery();
            while (rs.next()) {
                WLItem item = new WLItem();
                item.setWlItemId(rs.getInt("WLITEM_ID"));
                item.setProduct(Constant.Service.PRODUCT_SERVICE.getProduct(rs.getString("PRO_ID").trim()));
                item.setWishList(Constant.Service.WISH_LIST_SERVICE.getWishlist(rs.getString("WL_ID")));

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
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<WLItem> items = new ArrayList<>();

        try {
            ps = conn.prepareStatement("SELECT * FROM [WLITEM] WHERE WL_ID = ?");
            ps.setString(1, wishListId);
            rs = ps.executeQuery();
            while (rs.next()) {
                WLItem item = new WLItem();
                item.setWlItemId(rs.getInt("WLITEM_ID"));
                item.setProduct(Constant.Service.PRODUCT_SERVICE.getProduct(rs.getString("PRO_ID").trim()));
                item.setWishList(Constant.Service.WISH_LIST_SERVICE.getWishlist(wishListId));

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
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

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
