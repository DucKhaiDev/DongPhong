package Services;

import Entity.Customer;

import java.util.List;

public interface CustomerService {
    void insert(Customer customer);
    void edit(Customer customer);
    void delete(String CUS_ID);
    Customer getCustomer(String CUS_ID);
    List<Customer> getAll();
}
