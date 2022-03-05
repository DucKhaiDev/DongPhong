package Dao.deploy;

import Connect.DBConnect;
import Entity.Customer;
import Entity.Wishlist;
import Services.deploy.CustomerService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WishlistDao implements Dao.WishlistDao {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    CustomerService customerService = new CustomerService();

    @Override
    public void insert(Wishlist wishlist) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("INSERT INTO [WISHLIST](WL_ID, CUS_ID) VALUES(?, ?)");
            ps.setString(1, wishlist.getWL_ID());
            ps.setString(2, wishlist.getCUS().getCUS_ID());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }
    }

    @Override
    public void edit(Wishlist wishlist) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("UPDATE [WISHLIST] SET CUS_ID = ? WHERE WL_ID = ?");
            ps.setString(1, wishlist.getCUS().getCUS_ID());
            ps.setString(2, wishlist.getWL_ID());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }
    }

    @Override
    public void delete(String WL_ID) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("DELETE FROM [WISHLIST] WHERE WL_ID = ?");
            ps.setString(1, WL_ID);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }
    }

    @Override
    public Wishlist getWishlist(String WL_ID) {
        conn = DBConnect.getConnection();
        Wishlist wishlist = new Wishlist();

        try {
            ps = conn.prepareStatement("SELECT * FROM [WISHLIST] WHERE WL_ID = ?");
            ps.setString(1, WL_ID);

            rs = ps.executeQuery();
            rs.next();
            wishlist.setWL_ID(rs.getString("WL_ID").trim());
            wishlist.setCUS(customerService.getCustomer(rs.getString("CUS_ID").trim()));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return wishlist;
    }

    @Override
    public List<Wishlist> getAll() {
        conn = DBConnect.getConnection();
        List<Wishlist> wishlists = new ArrayList<>();

        try {
            ps = conn.prepareStatement("SELECT * FROM [WISHLIST]");
            rs = ps.executeQuery();
            while (rs.next()) {
                Wishlist wishlist = new Wishlist();
                wishlist.setWL_ID(rs.getString("WL_ID").trim());
                wishlist.setCUS(customerService.getCustomer(rs.getString("CUS_ID").trim()));

                wishlists.add(wishlist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return wishlists;
    }
}
