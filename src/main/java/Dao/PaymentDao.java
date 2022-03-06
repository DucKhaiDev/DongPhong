package Dao;

import Entity.Payment;

import java.util.List;

public interface PaymentDao {
    void insert(Payment payment);
    void edit(Payment payment);
    void delete(String paymentId);
    Payment getPayment(String paymentId);
    List<Payment> getAll();
}
