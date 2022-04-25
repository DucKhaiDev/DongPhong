package Dao.deploy;

import Connect.DBConnect;
import Dao.IWishlistDao;
import Entity.WishList;
import Util.Constant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WishlistDao implements IWishlistDao {
    @Override
    public void insert(WishList wishList) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("INSERT INTO [WISHLIST](WL_ID, USER_ID) VALUES(?, ?)");
            ps.setString(1, wishList.getWishListId());
            ps.setString(2, wishList.getUser().getUserId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(null, ps, conn);
        }
    }

    @Override
    public void edit(WishList wishList) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("UPDATE [WISHLIST] SET USER_ID = ? WHERE WL_ID = ?");
            ps.setString(1, wishList.getUser().getUserId());
            ps.setString(2, wishList.getWishListId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(null, ps, conn);
        }
    }

    @Override
    public void delete(String wishListId) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("DELETE FROM [WISHLIST] WHERE WL_ID = ?");
            ps.setString(1, wishListId);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(null, ps, conn);
        }
    }

    @Override
    public WishList getWishlist(String wishListId) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        WishList wishlist = new WishList();

        try {
            ps = conn.prepareStatement("SELECT * FROM [WISHLIST] WHERE WL_ID = ?");
            ps.setString(1, wishListId);

            rs = ps.executeQuery();
            if (rs.next()) {
                wishlist.setWishListId(wishListId);
                wishlist.setUser(Constant.Service.USER_SERVICE.getUser(rs.getString("USER_ID")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return wishlist;
    }

    @Override
    public WishList getWishListByUser(String userId) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        WishList wishList = new WishList();

        try {
            ps = conn.prepareStatement("SELECT * FROM [WISHLIST] WHERE USER_ID = ?");
            ps.setString(1, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                wishList.setWishListId(rs.getString("WL_ID"));
                wishList.setUser(Constant.Service.USER_SERVICE.getUser(userId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return wishList;
    }

    @Override
    public List<WishList> getAll() {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<WishList> wishLists = new ArrayList<>();

        try {
            ps = conn.prepareStatement("SELECT * FROM [WISHLIST]");
            rs = ps.executeQuery();
            while (rs.next()) {
                WishList wishList = new WishList();
                wishList.setWishListId(rs.getString("WL_ID"));
                wishList.setUser(Constant.Service.USER_SERVICE.getUser(rs.getString("USER_ID")));

                wishLists.add(wishList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return wishLists;
    }
}
