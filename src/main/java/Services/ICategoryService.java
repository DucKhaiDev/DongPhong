package Services;

import Entity.Category;

import java.util.List;

public interface ICategoryService {
    void insert(Category category);
    void edit(Category category);
    void delete(String categoryId);
    Category getCategory(String categoryId);
    List<Category> getAll();
    List<Category> getCategoryByRoom(String roomId);
    List<Category> searchByName(String categoryName);
    boolean checkExistId(String id);
    boolean isUnusedCategory(String categoryId);
}
