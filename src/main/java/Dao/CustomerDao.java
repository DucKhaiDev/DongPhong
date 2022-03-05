package Dao;

import Entity.Customer;

import java.util.List;

public interface CustomerDao {
    void insert(Customer customer);
    void edit(Customer customer);
    void delete(String CUS_ID);
    Customer getCustomer(String CUS_ID);
    List<Customer> getAll();
    boolean checkExistID(String ID);
}
