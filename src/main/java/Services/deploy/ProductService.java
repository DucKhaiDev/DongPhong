package Services.deploy;

import Dao.deploy.ProductDao;
import Entity.Product;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

public class ProductService implements Services.ProductService {
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
    public void delete(String PRO_ID) {
        productDao.delete(PRO_ID);
    }

    @Override
    public Product getProduct(String PRO_ID) {
        return productDao.getProduct(PRO_ID);
    }

    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }

    @Override
    public List<Product> searchByName(String NAME) {
        return productDao.searchByName(NAME);
    }

    @Override
    public List<Product> searchByNameInCategory(String CAT_ID, String NAME) {
        return productDao.searchByNameInCategory(CAT_ID, NAME);
    }

    @Override
    public List<Product> getProductByCategory(String CAT_ID) {
        return productDao.getProductByCategory(CAT_ID);
    }

    @Override
    public List<Product> getProductByBrand(String BRA_ID) {
        return productDao.getProductByBrand(BRA_ID);
    }

    @Override
    public boolean checkExistID(String PRO_ID) {
        return productDao.checkExistID(PRO_ID);
    }

    @Override
    public int countProduct(String CAT_ID, String BRA_ID) {
        return productDao.countProduct(CAT_ID, BRA_ID);
    }

    @Override
    public List<Product> filterProductByBrand(List<Product> products, String brands) {
        String[] brand = brands.split(",");
        Iterator<Product> iterator = products.iterator();

        while (iterator.hasNext()) {
            Product product = iterator.next();
            boolean exist = false;

            for (String b : brand) {
                if (product.getBRA().getBRA_ID().equals(b)) {
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
            BigDecimal proPrice = new BigDecimal(product.getPRO_PRICE());
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
        products.removeIf(product -> Double.compare(product.getPRO_RATE(), Double.parseDouble(stars)) < 0);
        return products;
    }
}
