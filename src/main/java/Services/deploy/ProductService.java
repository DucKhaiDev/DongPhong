package Services.deploy;

import Dao.deploy.ProductDao;
import Entity.Product;
import Services.IProductService;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

public class ProductService implements IProductService {
    private final ProductDao productDao = new ProductDao();

    @Override
    public void insert(Product product) {
        productDao.insert(product);
    }

    @Override
    public void edit(Product product) {
        productDao.edit(product);
    }

    @Override
    public void delete(String productId) {
        productDao.delete(productId);
    }

    @Override
    public Product getProduct(String productId) {
        return productDao.getProduct(productId);
    }

    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }

    @Override
    public List<Product> searchByName(String productName) {
        return productDao.searchByName(productName);
    }

    @Override
    public List<Product> searchByNameInCategory(String categoryId, String productName) {
        return productDao.searchByNameInCategory(categoryId, productName);
    }

    @Override
    public List<Product> getProductByCategory(String categoryId) {
        return productDao.getProductByCategory(categoryId);
    }

    @Override
    public List<Product> getProductByBrand(String brandId) {
        return productDao.getProductByBrand(brandId);
    }

    @Override
    public boolean checkExistId(String productId) {
        return productDao.checkExistId(productId);
    }

    @Override
    public int countProduct(String categoryId, String brandId) {
        return productDao.countProduct(categoryId, brandId);
    }

    @Override
    public void sortByPriceAsc(List<Product> products) {
        products.sort((o1, o2) -> {
            BigDecimal o1price = o1.getProductPrice();
            BigDecimal o2price = o2.getProductPrice();
            return o1price.compareTo(o2price);
        });
    }

    @Override
    public void sortByPriceDesc(List<Product> products) {
        products.sort(Collections.reverseOrder((o1, o2) -> {
            BigDecimal o1price = o1.getProductPrice();
            BigDecimal o2price = o2.getProductPrice();
            return o1price.compareTo(o2price);
        }));
    }

    @Override
    public void sortByRateAsc(List<Product> products) {
        products.sort((o1, o2) -> {
            double o1Rate = o1.getProductRate();
            double o2Rate = o2.getProductRate();
            return Double.compare(o1Rate, o2Rate);
        });
    }

    @Override
    public void sortByRateDesc(List<Product> products) {
        products.sort(Collections.reverseOrder((o1, o2) -> {
            double o1Rate = o1.getProductRate();
            double o2Rate = o2.getProductRate();
            return Double.compare(o1Rate, o2Rate);
        }));
    }

    @Override
    public void sortBySaleAsc(List<Product> products) {
        List<Product> sortedProducts = productDao.getSortSaleAsc();

        //remove if not exist in products
        ListIterator<Product> litr = sortedProducts.listIterator();
        while (litr.hasNext()) {
            Product outer = litr.next();
            boolean exist = false;

            for (Product inner : products) {
                if (inner.getProductId().equals(outer.getProductId())) {
                    exist = true;
                    break;
                }
            }

            if (!exist) {
                litr.remove();
            }
        }

        products.clear();
        products.addAll(sortedProducts);
    }

    @Override
    public void sortBySaleDesc(List<Product> products) {
        List<Product> sortedProducts = productDao.getSortSaleDesc();

        //remove if not exist in products
        ListIterator<Product> litr = sortedProducts.listIterator();
        while (litr.hasNext()) {
            Product outer = litr.next();
            boolean exist = false;

            for (Product inner : products) {
                if (inner.getProductId().equals(outer.getProductId())) {
                    exist = true;
                    break;
                }
            }

            if (!exist) {
                litr.remove();
            }
        }

        products.clear();
        products.addAll(sortedProducts);
    }

    @Override
    public List<Product> filterProductByBrand(List<Product> products, String brands) {
        String[] brand = brands.split(",");
        Iterator<Product> iterator = products.iterator();

        while (iterator.hasNext()) {
            Product product = iterator.next();
            boolean exist = false;

            for (String b : brand) {
                if (product.getBrand().getBrandId().equals(b)) {
                    exist = true;
                    break;
                }
            }

            if (!exist) {
                iterator.remove();
            }
        }

        return products;
    }

    @Override
    public List<Product> filterProductByPrice(List<Product> products, String price) {
        String[] priceArr = price.split(",");
        Iterator<Product> iterator = products.iterator();

        while (iterator.hasNext()) {
            Product product = iterator.next();
            BigDecimal proPrice = product.getProductPrice();
            BigDecimal
                    p10tr = new BigDecimal(10000000),
                    p50tr = new BigDecimal(50000000),
                    p100tr = new BigDecimal(100000000),
                    p200tr = new BigDecimal(200000000),
                    p500tr = new BigDecimal(500000000);

            boolean deleted = true;

            for (String p : priceArr) {
                //Nhỏ hơn 10tr
                if (p.equals("lt10")) {
                    if (proPrice.compareTo(p10tr) < 0) {
                        deleted = false;
                    }
                }

                //10tr - 50tr
                if (p.equals("10t50")) {
                    if ((!(proPrice.compareTo(p10tr) < 0)) && (!(proPrice.compareTo(p50tr) > 0))) {
                        deleted = false;
                    }
                }

                //50tr - 100tr
                if (p.equals("50t100")) {
                    if ((!(proPrice.compareTo(p50tr) < 0)) && (!(proPrice.compareTo(p100tr) > 0))) {
                        deleted = false;
                    }
                }

                //100tr - 200tr
                if (p.equals("100t200")) {
                    if ((!(proPrice.compareTo(p100tr) < 0)) && (!(proPrice.compareTo(p200tr) > 0))) {
                        deleted = false;
                    }
                }

                if (p.equals("200t500")) {
                    if ((!(proPrice.compareTo(p200tr) < 0)) && (!(proPrice.compareTo(p500tr) > 0))) {
                        deleted = false;
                    }
                }

                if (p.equals("mt500")) {
                    if (proPrice.compareTo(p500tr) > 0) {
                        deleted = false;
                    }
                }
            }

            if (deleted) {
                iterator.remove();
            }
        }

        return products;
    }

    @Override
    public List<Product> filterProductByStars(List<Product> products, String stars) {
        products.removeIf(product -> Double.compare(product.getProductRate(), Double.parseDouble(stars)) < 0);
        return products;
    }

    @Override
    public int countProduct(String roomId) {
        return productDao.countProduct(roomId);
    }

    @Override
    public int countPrd_RoomBrand(String roomId, String brandId) {
        return productDao.countPrd_RoomBrand(roomId, brandId);
    }

    @Override
    public List<Product> getProductByRoom(String roomId) {
        return productDao.getProductByRoom(roomId);
    }

    @Override
    public List<Product> searchByNameInRoom(String roomId, String name) {
        return productDao.searchByNameInRoom(roomId, name);
    }

    @Override
    public int countPrd_KeywordBrand(String keyword, String brandId) {
        return productDao.countPrd_KeywordBrand(keyword, brandId);
    }

    @Override
    public Map<Product, Integer> bestseller(Timestamp fromDate, Timestamp toDate) {
        return productDao.bestseller(fromDate, toDate);
    }

    @Override
    public Map<Product, Integer> favourite() {
        return productDao.favourite();
    }

    @Override
    public Map<Product, Double> highestRated() {
        return productDao.highestRated();
    }

    @Override
    public int countSale(String productId) {
        return productDao.countSale(productId);
    }

    @Override
    public int countOutOfStock() {
        return productDao.countOutOfStock();
    }
}
