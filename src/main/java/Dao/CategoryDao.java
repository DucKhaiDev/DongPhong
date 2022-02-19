package Dao;

import Entity.Category;

import java.util.List;

public interface CategoryDao {
    void insert(Category category);
    void edit(Category category);
    void delete(String CAT_ID);
    Category getCategory(String CAT_ID);
    List<Category> getAll();
    List<Category> searchByName(String NAME);
    boolean checkExistID(String ID);
}
