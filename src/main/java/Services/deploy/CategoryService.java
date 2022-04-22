package Services.deploy;

import Dao.deploy.CategoryDao;
import Entity.Category;
import Services.ICategoryService;

import java.util.List;

public class CategoryService implements ICategoryService {
    CategoryDao categoryDao = new CategoryDao();

    @Override
    public void insert(Category category) {
        categoryDao.insert(category);
    }

    @Override
    public void edit(Category category) {
        categoryDao.edit(category);
    }

    @Override
    public void delete(String categoryId) {
        categoryDao.delete(categoryId);
    }

    @Override
    public Category getCategory(String categoryId) {
        return categoryDao.getCategory(categoryId);
    }

    @Override
    public List<Category> getAll() {
        return categoryDao.getAll();
    }

    @Override
    public List<Category> getCategoryByRoom(String roomId) {
        return categoryDao.getCategoryByRoom(roomId);
    }

    @Override
    public List<Category> searchByName(String categoryName) {
        return categoryDao.searchByName(categoryName);
    }

    @Override
    public boolean checkExistId(String id) {
        return categoryDao.checkExistId(id);
    }

    @Override
    public boolean isUnusedCategory(String categoryId) {
        return categoryDao.isUnusedCategory(categoryId);
    }
}
