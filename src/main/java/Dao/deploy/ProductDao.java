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

    private final CategoryService categoryService = new CategoryService();
    private final BrandService brandService = new BrandService();

    @Override
    public void insert(Product product) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("INSERT INTO [PRODUCT](PRO_ID, PRO_NAME, PRO_DES, PRO_DIMEN, PRO_WEIGHT, PRO_MATE, PRO_COLOR, PRO_PRICE, PRO_COST, PRO_QUANT, CAT_ID, BRA_ID)" +
                                            "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, product.getProductId());
            ps.setString(2, product.getProductName());
            ps.setString(3, product.getProductDescription());
            ps.setString(4, product.getProductDimension());
            ps.setString(5, product.getProductWeight());
            ps.setString(6, product.getProductMaterial());
            ps.setString(7, product.getProductColor());
            ps.setBigDecimal(8, product.getProductPrice());
            ps.setBigDecimal(9, product.getProductCost());
            ps.setInt(10, product.getProductQuantity());
            ps.setString(11, product.getCategory().getCategoryId());
            ps.setString(12, product.getBrand().getBrandId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }
    }

    @Override
    public void edit(Product product) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("UPDATE [PRODUCT] SET PRO_NAME = ?, PRO_RATE = ?, PRO_DES = ?, PRO_DIMEN = ?, PRO_WEIGHT = ?, PRO_MATE = ?, PRO_COLOR = ?, PRO_PRICE = ?, PRO_COST = ?, PRO_QUANT = ?, CAT_ID = ?, BRA_ID = ? WHERE PRO_ID = ?");
            ps.setString(1, product.getProductName());
            ps.setDouble(2, product.getProductRate());
            ps.setString(3, product.getProductDescription());
            ps.setString(4, product.getProductDimension());
            ps.setString(5, product.getProductWeight());
            ps.setString(6, product.getProductMaterial());
            ps.setString(7, product.getProductColor());
            ps.setBigDecimal(8, product.getProductPrice());
            ps.setBigDecimal(9, product.getProductCost());
            ps.setInt(10, product.getProductQuantity());
            ps.setString(11, product.getCategory().getCategoryId());
            ps.setString(12, product.getBrand().getBrandId());
            ps.setString(13, product.getProductId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }
    }

    @Override
    public void delete(String productId) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("DELETE FROM [PRODUCT] WHERE PRO_ID = ?");
            ps.setString(1, productId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }
    }

    @Override
    public Product getProduct(String productId) {
        Product product = new Product();
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT * FROM [PRODUCT] WHERE PRO_ID = ?");
            ps.setString(1, productId);
            rs = ps.executeQuery();

            rs.next();
            product.setProductId(productId);
            product.setProductName(rs.getString("PRO_NAME"));
            product.setProductRate(rs.getDouble("PRO_RATE"));
            product.setProductDescription(rs.getString("PRO_DES"));
            product.setProductDimension(rs.getString("PRO_DIMEN"));
            product.setProductWeight(rs.getString("PRO_WEIGHT"));
            product.setProductMaterial(rs.getString("PRO_MATE"));
            product.setProductColor(rs.getString("PRO_COLOR"));
            product.setProductPrice(rs.getBigDecimal("PRO_PRICE"));
            product.setProductCost(rs.getBigDecimal("PRO_COST"));
            product.setProductQuantity(rs.getInt("PRO_QUANT"));
            product.setCategory(categoryService.getCategory(rs.getString("CAT_ID").trim()));
            product.setBrand(brandService.getBrand(rs.getString("BRA_ID").trim()));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return product;
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT * FROM [PRODUCT] ORDER BY PRO_ID ASC");

            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getString("PRO_ID").trim());
                product.setProductName(rs.getString("PRO_NAME"));
                product.setProductRate(rs.getDouble("PRO_RATE"));
                product.setProductDescription(rs.getString("PRO_DES"));
                product.setProductDimension(rs.getString("PRO_DIMEN"));
                product.setProductWeight(rs.getString("PRO_WEIGHT"));
                product.setProductMaterial(rs.getString("PRO_MATE"));
                product.setProductColor(rs.getString("PRO_COLOR"));
                product.setProductPrice(rs.getBigDecimal("PRO_PRICE"));
                product.setProductCost(rs.getBigDecimal("PRO_COST"));
                product.setProductQuantity(rs.getInt("PRO_QUANT"));
                product.setCategory(categoryService.getCategory(rs.getString("CAT_ID").trim()));
                product.setBrand(brandService.getBrand(rs.getString("BRA_ID").trim()));

                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return products;
    }

    @Override
    public List<Product> searchByName(String productName) {
        List<Product> products = new ArrayList<>();
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT * FROM [PRODUCT] WHERE PRO_NAME LIKE ? ORDER BY PRO_ID ASC");
            ps.setString(1, "%" + productName + "%");

            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getString("PRO_ID").trim());
                product.setProductName(rs.getString("PRO_NAME"));
                product.setProductRate(rs.getDouble("PRO_RATE"));
                product.setProductDescription(rs.getString("PRO_DES"));
                product.setProductDimension(rs.getString("PRO_DIMEN"));
                product.setProductWeight(rs.getString("PRO_WEIGHT"));
                product.setProductMaterial(rs.getString("PRO_MATE"));
                product.setProductColor(rs.getString("PRO_COLOR"));
                product.setProductPrice(rs.getBigDecimal("PRO_PRICE"));
                product.setProductCost(rs.getBigDecimal("PRO_COST"));
                product.setProductQuantity(rs.getInt("PRO_QUANT"));
                product.setCategory(categoryService.getCategory(rs.getString("CAT_ID").trim()));
                product.setBrand(brandService.getBrand(rs.getString("BRA_ID").trim()));

                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return products;
    }

    @Override
    public List<Product> searchByNameInCategory(String categoryId, String productName) {
        List<Product> products = new ArrayList<>();
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT * FROM [PRODUCT] WHERE CAT_ID = ? AND PRO_NAME LIKE ? ORDER BY PRO_ID ASC");
            ps.setString(1, categoryId);
            ps.setString(2, "%" + productName + "%");

            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getString("PRO_ID").trim());
                product.setProductName(rs.getString("PRO_NAME"));
                product.setProductRate(rs.getDouble("PRO_RATE"));
                product.setProductDescription(rs.getString("PRO_DES"));
                product.setProductDimension(rs.getString("PRO_DIMEN"));
                product.setProductWeight(rs.getString("PRO_WEIGHT"));
                product.setProductMaterial(rs.getString("PRO_MATE"));
                product.setProductColor(rs.getString("PRO_COLOR"));
                product.setProductPrice(rs.getBigDecimal("PRO_PRICE"));
                product.setProductCost(rs.getBigDecimal("PRO_COST"));
                product.setProductQuantity(rs.getInt("PRO_QUANT"));
                product.setCategory(categoryService.getCategory(categoryId));
                product.setBrand(brandService.getBrand(rs.getString("BRA_ID").trim()));

                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return products;
    }

    @Override
    public List<Product> getProductByCategory(String categoryId) {
        List<Product> products = new ArrayList<>();
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT * FROM [PRODUCT] WHERE CAT_ID = ? ORDER BY PRO_ID ASC");
            ps.setString(1, categoryId);

            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getString("PRO_ID").trim());
                product.setProductName(rs.getString("PRO_NAME"));
                product.setProductRate(rs.getDouble("PRO_RATE"));
                product.setProductDescription(rs.getString("PRO_DES"));
                product.setProductDimension(rs.getString("PRO_DIMEN"));
                product.setProductWeight(rs.getString("PRO_WEIGHT"));
                product.setProductMaterial(rs.getString("PRO_MATE"));
                product.setProductColor(rs.getString("PRO_COLOR"));
                product.setProductPrice(rs.getBigDecimal("PRO_PRICE"));
                product.setProductCost(rs.getBigDecimal("PRO_COST"));
                product.setProductQuantity(rs.getInt("PRO_QUANT"));
                product.setCategory(categoryService.getCategory(categoryId));
                product.setBrand(brandService.getBrand(rs.getString("BRA_ID").trim()));

                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return products;
    }

    @Override
    public List<Product> getProductByBrand(String brandId) {
        List<Product> products = new ArrayList<>();
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT * FROM [PRODUCT] WHERE BRA_ID = ? ORDER BY PRO_ID ASC");
            ps.setString(1, brandId);

            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getString("PRO_ID").trim());
                product.setProductName(rs.getString("PRO_NAME"));
                product.setProductRate(rs.getDouble("PRO_RATE"));
                product.setProductDescription(rs.getString("PRO_DES"));
                product.setProductDimension(rs.getString("PRO_DIMEN"));
                product.setProductWeight(rs.getString("PRO_WEIGHT"));
                product.setProductMaterial(rs.getString("PRO_MATE"));
                product.setProductColor(rs.getString("PRO_COLOR"));
                product.setProductPrice(rs.getBigDecimal("PRO_PRICE"));
                product.setProductCost(rs.getBigDecimal("PRO_COST"));
                product.setProductQuantity(rs.getInt("PRO_QUANT"));
                product.setCategory(categoryService.getCategory(rs.getString("CAT_ID").trim()));
                product.setBrand(brandService.getBrand(brandId));

                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return products;
    }

    @Override
    public boolean checkExistId(String id) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT * FROM [PRODUCT] WHERE PRO_ID = ?");
            ps.setString(1, id);
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
    public int countProduct(String categoryId, String brandId) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT COUNT(*) FROM [PRODUCT] WHERE CAT_ID = ? AND BRA_ID = ?");
            ps.setString(1, categoryId);
            ps.setString(2, brandId);
            rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return 0;
    }

    @Override
    public int countProduct(String roomId) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT COUNT(*) " +
                                                "FROM dbo.PRODUCT " +
                                                "JOIN dbo.CATEGORY ON CATEGORY.CAT_ID = PRODUCT.CAT_ID " +
                                                "JOIN dbo.ROOM ON ROOM.ROOM_ID = CATEGORY.ROOM_ID " +
                                                "WHERE ROOM.ROOM_ID = ?");
            ps.setString(1, roomId);
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
    public int countPrd_RoomBrand(String roomId, String brandId) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT COUNT(*) " +
                                                "FROM dbo.PRODUCT " +
                                                "JOIN dbo.CATEGORY ON CATEGORY.CAT_ID = PRODUCT.CAT_ID " +
                                                "JOIN dbo.ROOM ON ROOM.ROOM_ID = CATEGORY.ROOM_ID " +
                                                "JOIN dbo.BRAND ON BRAND.BRA_ID = PRODUCT.BRA_ID " +
                                                "WHERE dbo.ROOM.ROOM_ID = ? AND dbo.BRAND.BRA_ID = ?");
            ps.setString(1, roomId);
            ps.setString(2, brandId);
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
    public List<Product> getProductByRoom(String roomId) {
        conn = DBConnect.getConnection();
        List<Product> products = new ArrayList<>();

        try {
            ps = conn.prepareStatement("SELECT * " +
                                                "FROM dbo.PRODUCT JOIN dbo.CATEGORY ON CATEGORY.CAT_ID = PRODUCT.CAT_ID " +
                                                "JOIN dbo.ROOM ON ROOM.ROOM_ID = CATEGORY.ROOM_ID " +
                                                "WHERE ROOM.ROOM_ID = ?");
            ps.setString(1, roomId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getString("PRO_ID").trim());
                product.setProductName(rs.getString("PRO_NAME"));
                product.setProductRate(rs.getDouble("PRO_RATE"));
                product.setProductDescription(rs.getString("PRO_DES"));
                product.setProductDimension(rs.getString("PRO_DIMEN"));
                product.setProductWeight(rs.getString("PRO_WEIGHT"));
                product.setProductMaterial(rs.getString("PRO_MATE"));
                product.setProductColor(rs.getString("PRO_COLOR"));
                product.setProductPrice(rs.getBigDecimal("PRO_PRICE"));
                product.setProductCost(rs.getBigDecimal("PRO_COST"));
                product.setProductQuantity(rs.getInt("PRO_QUANT"));
                product.setCategory(categoryService.getCategory(rs.getString("CAT_ID").trim()));
                product.setBrand(brandService.getBrand(rs.getString("BRA_ID").trim()));

                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return products;
    }

    @Override
    public List<Product> searchByNameInRoom(String roomId, String name) {
        conn = DBConnect.getConnection();
        List<Product> products = new ArrayList<>();

        try {
            ps = conn.prepareStatement("SELECT * " +
                                                "FROM dbo.PRODUCT " +
                                                "JOIN dbo.CATEGORY ON CATEGORY.CAT_ID = PRODUCT.CAT_ID " +
                                                "JOIN dbo.ROOM ON ROOM.ROOM_ID = CATEGORY.ROOM_ID " +
                                                "WHERE ROOM.ROOM_ID = ? AND PRO_NAME LIKE ? ORDER BY PRO_ID ASC");
            ps.setString(1, roomId);
            ps.setString(2, name);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getString("PRO_ID").trim());
                product.setProductName(rs.getString("PRO_NAME"));
                product.setProductRate(rs.getDouble("PRO_RATE"));
                product.setProductDescription(rs.getString("PRO_DES"));
                product.setProductDimension(rs.getString("PRO_DIMEN"));
                product.setProductWeight(rs.getString("PRO_WEIGHT"));
                product.setProductMaterial(rs.getString("PRO_MATE"));
                product.setProductColor(rs.getString("PRO_COLOR"));
                product.setProductPrice(rs.getBigDecimal("PRO_PRICE"));
                product.setProductCost(rs.getBigDecimal("PRO_COST"));
                product.setProductQuantity(rs.getInt("PRO_QUANT"));
                product.setCategory(categoryService.getCategory(rs.getString("CAT_ID").trim()));
                product.setBrand(brandService.getBrand(rs.getString("BRA_ID").trim()));

                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return products;
    }
}
