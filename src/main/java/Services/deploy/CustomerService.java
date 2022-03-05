package Services.deploy;

import Dao.deploy.CustomerDao;
import Entity.Customer;

import java.util.List;

public class CustomerService implements Services.CustomerService {
    CustomerDao customerDao = new CustomerDao();

    @Override
    public void insert(Customer customer) {
        customerDao.insert(customer);
    }

    @Override
    public void edit(Customer customer) {
        customerDao.edit(customer);
    }

    @Override
    public void delete(String CUS_ID) {
        customerDao.delete(CUS_ID);
    }

    @Override
    public Customer getCustomer(String CUS_ID) {
        return customerDao.getCustomer(CUS_ID);
    }

    @Override
    public List<Customer> getAll() {
        return customerDao.getAll();
    }
}
