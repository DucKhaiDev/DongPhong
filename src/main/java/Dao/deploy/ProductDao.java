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
            ps = conn.prepareStatement("INSERT INTO [PRODUCT](PRO_ID, PRO_NAME, PRO_RATE, PRO_DES, PRO_PRICE, PRO_COST, PRO_QUANT, CAT_ID, BRA_ID)" +
                                            "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, product.getPRO_ID());
            ps.setString(2, product.getPRO_NAME());
            ps.setDouble(3, product.getPRO_RATE());
            ps.setString(4, product.getPRO_DES());
            ps.setDouble(5, product.getPRO_PRICE());
            ps.setDouble(6, product.getPRO_COST());
            ps.setInt(7, product.getPRO_QUANT());
            ps.setString(8, product.getCAT().getCAT_ID());
            ps.setString(9, product.getBRA().getBRA_ID());
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
            ps.setDouble(4, product.getPRO_PRICE());
            ps.setDouble(5, product.getPRO_COST());
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
            product.setPRO_PRICE(rs.getDouble("PRO_PRICE"));
            product.setPRO_COST(rs.getDouble("PRO_COST"));
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
        List<Product> productList = new ArrayList<>();
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
                product.setPRO_PRICE(rs.getDouble("PRO_PRICE"));
                product.setPRO_COST(rs.getDouble("PRO_COST"));
                product.setPRO_QUANT(rs.getInt("PRO_QUANT"));
                product.setCAT(categoryService.getCategory(rs.getString("CAT_ID")));
                product.setBRA(brandService.getBrand(rs.getString("BRA_ID")));

                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeResultSet(rs);
            DBConnect.closePreparedStatement(ps);
            DBConnect.closeConnection(conn);
        }

        return productList;
    }

    @Override
    public List<Product> searchByName(String NAME) {
        List<Product> productList = new ArrayList<>();
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
                product.setPRO_PRICE(rs.getDouble("PRO_PRICE"));
                product.setPRO_COST(rs.getDouble("PRO_COST"));
                product.setPRO_QUANT(rs.getInt("PRO_QUANT"));
                product.setCAT(categoryService.getCategory(rs.getString("CAT_ID")));
                product.setBRA(brandService.getBrand(rs.getString("BRA_ID")));

                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeResultSet(rs);
            DBConnect.closePreparedStatement(ps);
            DBConnect.closeConnection(conn);
        }

        return productList;
    }

    @Override
    public List<Product> searchByCategory(String CAT) {
        List<String> CAT_ID_List = new ArrayList<>();
        List<Product> productList = new ArrayList<>();

        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT CAT_ID FROM [CATEGORY] WHERE CAT_NAME = ?");
            ps.setString(1, "%" + CAT + "%");

            rs = ps.executeQuery();
            while (rs.next()) {
                CAT_ID_List.add(rs.getString("CAT_ID"));
            }

            for (String CAT_ID : CAT_ID_List) {
                ps = conn.prepareStatement("SELECT * FROM [PRODUCT] WHERE CAT_ID = ?");
                ps.setString(1, CAT_ID);

                rs = ps.executeQuery();
                while (rs.next()) {
                    Product product = new Product();
                    product.setPRO_ID(rs.getString("PRO_ID"));
                    product.setPRO_NAME(rs.getString("PRO_NAME"));
                    product.setPRO_RATE(rs.getDouble("PRO_RATE"));
                    product.setPRO_DES(rs.getString("PRO_DES"));
                    product.setPRO_PRICE(rs.getDouble("PRO_PRICE"));
                    product.setPRO_COST(rs.getDouble("PRO_COST"));
                    product.setPRO_QUANT(rs.getInt("PRO_QUANT"));
                    product.setCAT(categoryService.getCategory(rs.getString("CAT_ID")));
                    product.setBRA(brandService.getBrand(rs.getString("BRA_ID")));

                    productList.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeResultSet(rs);
            DBConnect.closePreparedStatement(ps);
            DBConnect.closeConnection(conn);
        }

        return productList;
    }

    @Override
    public List<Product> searchByBrand(String BRA) {
        List<String> BRA_ID_List = new ArrayList<>();
        List<Product> productList = new ArrayList<>();

        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT BRA_ID FROM [BRAND] WHERE BRA_NAME = ?");
            ps.setString(1, "%" + BRA + "%");

            rs = ps.executeQuery();
            while (rs.next()) {
                BRA_ID_List.add(rs.getString("BRA_ID"));
            }

            for (String BRA_ID : BRA_ID_List) {
                ps = conn.prepareStatement("SELECT * FROM [PRODUCT] WHERE BRA_ID = ?");
                ps.setString(1, BRA_ID);

                rs = ps.executeQuery();
                while (rs.next()) {
                    Product product = new Product();
                    product.setPRO_ID(rs.getString("PRO_ID"));
                    product.setPRO_NAME(rs.getString("PRO_NAME"));
                    product.setPRO_RATE(rs.getDouble("PRO_RATE"));
                    product.setPRO_DES(rs.getString("PRO_DES"));
                    product.setPRO_PRICE(rs.getDouble("PRO_PRICE"));
                    product.setPRO_COST(rs.getDouble("PRO_COST"));
                    product.setPRO_QUANT(rs.getInt("PRO_QUANT"));
                    product.setCAT(categoryService.getCategory(rs.getString("CAT_ID")));
                    product.setBRA(brandService.getBrand(rs.getString("CAT_ID")));

                    productList.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeResultSet(rs);
            DBConnect.closePreparedStatement(ps);
            DBConnect.closeConnection(conn);
        }

        return productList;
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
}
