package Dao;

import Entity.Payment;

import java.util.List;

public interface PaymentDao {
    void insert(Payment payment);
    void edit(Payment payment);
    void delete(String PAY_ID);
    Payment getPayment(String PAY_ID);
    List<Payment> getAll();
}
