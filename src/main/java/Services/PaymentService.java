package Services;

import Entity.Payment;

import java.util.List;

public interface PaymentService {
    void insert(Payment payment);
    void edit(Payment payment);
    void delete(String PAY_ID);
    Payment getPayment(String PAY_ID);
    List<Payment> getAll();
}
