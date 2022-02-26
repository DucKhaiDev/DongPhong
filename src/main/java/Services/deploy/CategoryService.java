package Services.deploy;

import Dao.deploy.CategoryDao;
import Entity.Category;

import java.util.List;

public class CategoryService implements Services.CategoryService {
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
    public void delete(String CAT_ID) {
        categoryDao.delete(CAT_ID);
    }

    @Override
    public Category getCategory(String CAT_ID) {
        return categoryDao.getCategory(CAT_ID);
    }

    @Override
    public List<Category> getAll() {
        return categoryDao.getAll();
    }

    @Override
    public List<Category> getCategoryByRoom(String ROOM_ID) {
        return categoryDao.getCategoryByRoom(ROOM_ID);
    }

    @Override
    public List<Category> searchByName(String NAME) {
        return categoryDao.searchByName(NAME);
    }

    @Override
    public boolean checkExistID(String ID) {
        return categoryDao.checkExistID(ID);
    }

    @Override
    public boolean isUnusedCategory(String CAT_ID) {
        return categoryDao.isUnusedCategory(CAT_ID);
    }
}
