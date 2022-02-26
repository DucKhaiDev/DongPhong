package Dao.deploy;

import Connect.DBConnect;
import Entity.Product;
import Services.deploy.BrandService;
import Services.deploy.CategoryService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements Dao.ProductDao {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    CategoryService categoryService = new CategoryService();
    BrandService brandService = new BrandService();

    @Override
    public void insert(Product product) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("INSERT INTO [PRODUCT](PRO_ID, PRO_NAME, PRO_DES, PRO_PRICE, PRO_COST, PRO_QUANT, CAT_ID, BRA_ID)" +
                                            "VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, product.getPRO_ID());
            ps.setString(2, product.getPRO_NAME());
            ps.setString(3, product.getPRO_DES());
            ps.setString(4, product.getPRO_PRICE());
            ps.setString(5, product.getPRO_COST());
            ps.setInt(6, product.getPRO_QUANT());
            ps.setString(7, product.getCAT().getCAT_ID());
            ps.setString(8, product.getBRA().getBRA_ID());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closePreparedStatement(ps);
            DBConnect.closeConnection(conn);
        }
    }

    @Override
    public void edit(Product product) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("UPDATE [PRODUCT] SET PRO_NAME = ?, PRO_RATE = ?, PRO_DES = ?, PRO_PRICE = ?, PRO_COST = ?, PRO_QUANT = ?, CAT_ID = ?, BRA_ID = ? WHERE PRO_ID = ?");
            ps.setString(1, product.getPRO_NAME());
            ps.setDouble(2, product.getPRO_RATE());
            ps.setString(3, product.getPRO_DES());
            ps.setString(4, product.getPRO_PRICE());
            ps.setString(5, product.getPRO_COST());
            ps.setInt(6, product.getPRO_QUANT());
            ps.setString(7, product.getCAT().getCAT_ID());
            ps.setString(8, product.getBRA().getBRA_ID());
            ps.setString(9, product.getPRO_ID());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closePreparedStatement(ps);
            DBConnect.closeConnection(conn);
        }
    }

    @Override
    public void delete(String PRO_ID) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("DELETE FROM [PRODUCT] WHERE PRO_ID = ?");
            ps.setString(1, PRO_ID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closePreparedStatement(ps);
            DBConnect.closeConnection(conn);
        }
    }

    @Override
    public Product getProduct(String PRO_ID) {
        Product product = new Product();
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT * FROM [PRODUCT] WHERE PRO_ID = ?");
            ps.setString(1, PRO_ID);
            rs = ps.executeQuery();

            rs.next();
            product.setPRO_ID(rs.getString("PRO_ID"));
            product.setPRO_NAME(rs.getString("PRO_NAME"));
            product.setPRO_RATE(rs.getDouble("PRO_RATE"));
            product.setPRO_DES(rs.getString("PRO_DES"));
            product.setPRO_PRICE(rs.getString("PRO_PRICE"));
            product.setPRO_COST(rs.getString("PRO_COST"));
            product.setPRO_QUANT(rs.getInt("PRO_QUANT"));
            product.setCAT(categoryService.getCategory(rs.getString("CAT_ID")));
            product.setBRA(brandService.getBrand(rs.getString("BRA_ID")));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeResultSet(rs);
            DBConnect.closePreparedStatement(ps);
            DBConnect.closeConnection(conn);
        }

        return product;
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT * FROM [PRODUCT]");

            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setPRO_ID(rs.getString("PRO_ID"));
                product.setPRO_NAME(rs.getString("PRO_NAME"));
                product.setPRO_RATE(rs.getDouble("PRO_RATE"));
                product.setPRO_DES(rs.getString("PRO_DES"));
                product.setPRO_PRICE(rs.getString("PRO_PRICE"));
                product.setPRO_COST(rs.getString("PRO_COST"));
                product.setPRO_QUANT(rs.getInt("PRO_QUANT"));
                product.setCAT(categoryService.getCategory(rs.getString("CAT_ID")));
                product.setBRA(brandService.getBrand(rs.getString("BRA_ID")));

                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeResultSet(rs);
            DBConnect.closePreparedStatement(ps);
            DBConnect.closeConnection(conn);
        }

        return products;
    }

    @Override
    public List<Product> searchByName(String NAME) {
        List<Product> products = new ArrayList<>();
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT * FROM [PRODUCT] WHERE PRO_NAME LIKE ?");
            ps.setString(1, "%" + NAME + "%");

            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setPRO_ID(rs.getString("PRO_ID"));
                product.setPRO_NAME(rs.getString("PRO_NAME"));
                product.setPRO_RATE(rs.getDouble("PRO_RATE"));
                product.setPRO_DES(rs.getString("PRO_DES"));
                product.setPRO_PRICE(rs.getString("PRO_PRICE"));
                product.setPRO_COST(rs.getString("PRO_COST"));
                product.setPRO_QUANT(rs.getInt("PRO_QUANT"));
                product.setCAT(categoryService.getCategory(rs.getString("CAT_ID")));
                product.setBRA(brandService.getBrand(rs.getString("BRA_ID")));

                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeResultSet(rs);
            DBConnect.closePreparedStatement(ps);
            DBConnect.closeConnection(conn);
        }

        return products;
    }

    @Override
    public List<Product> getProductByCategory(String CAT_ID) {
        List<Product> products = new ArrayList<>();
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT * FROM [PRODUCT] WHERE CAT_ID = ?");
            ps.setString(1, CAT_ID);

            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setPRO_ID(rs.getString("PRO_ID"));
                product.setPRO_NAME(rs.getString("PRO_NAME"));
                product.setPRO_RATE(rs.getDouble("PRO_RATE"));
                product.setPRO_DES(rs.getString("PRO_DES"));
                product.setPRO_PRICE(rs.getString("PRO_PRICE"));
                product.setPRO_COST(rs.getString("PRO_COST"));
                product.setPRO_QUANT(rs.getInt("PRO_QUANT"));
                product.setCAT(categoryService.getCategory(rs.getString("CAT_ID")));
                product.setBRA(brandService.getBrand(rs.getString("BRA_ID")));

                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeResultSet(rs);
            DBConnect.closePreparedStatement(ps);
            DBConnect.closeConnection(conn);
        }

        return products;
    }

    @Override
    public List<Product> getProductByBrand(String BRA_ID) {
        List<Product> products = new ArrayList<>();
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT * FROM [PRODUCT] WHERE BRA_ID = ?");
            ps.setString(1, BRA_ID);

            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setPRO_ID(rs.getString("PRO_ID"));
                product.setPRO_NAME(rs.getString("PRO_NAME"));
                product.setPRO_RATE(rs.getDouble("PRO_RATE"));
                product.setPRO_DES(rs.getString("PRO_DES"));
                product.setPRO_PRICE(rs.getString("PRO_PRICE"));
                product.setPRO_COST(rs.getString("PRO_COST"));
                product.setPRO_QUANT(rs.getInt("PRO_QUANT"));
                product.setCAT(categoryService.getCategory(rs.getString("CAT_ID")));
                product.setBRA(brandService.getBrand(rs.getString("CAT_ID")));

                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeResultSet(rs);
            DBConnect.closePreparedStatement(ps);
            DBConnect.closeConnection(conn);
        }

        return products;
    }

    @Override
    public boolean checkExistID(String ID) {
        boolean exist = false;
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT * FROM [PRODUCT] WHERE PRO_ID = ?");
            ps.setString(1, ID);

            rs = ps.executeQuery();
            if (rs.next()) {
                exist = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeResultSet(rs);
            DBConnect.closePreparedStatement(ps);
            DBConnect.closeConnection(conn);
        }

        return exist;
    }

    @Override
    public int countProduct(String CAT_ID, String BRA_ID) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT COUNT(*) FROM [PRODUCT] WHERE CAT_ID = ? AND BRA_ID = ?");
            ps.setString(1, CAT_ID);
            ps.setString(2, BRA_ID);
            rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeResultSet(rs);
            DBConnect.closePreparedStatement(ps);
            DBConnect.closeConnection(conn);
        }

        return 0;
    }
}
