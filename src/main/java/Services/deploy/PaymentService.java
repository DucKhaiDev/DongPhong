package Services.deploy;

import Dao.deploy.PaymentDao;
import Entity.Payment;

import java.util.List;

public class PaymentService implements Services.PaymentService {
    private final PaymentDao paymentDao = new PaymentDao();

    @Override
    public void insert(Payment payment) {
        paymentDao.insert(payment);
    }

    @Override
    public void edit(Payment payment) {
        paymentDao.edit(payment);
    }

    @Override
    public void delete(String paymentId) {
        paymentDao.delete(paymentId);
    }

    @Override
    public Payment getPayment(String paymentId) {
        return paymentDao.getPayment(paymentId);
    }

    @Override
    public List<Payment> getAll() {
        return paymentDao.getAll();
    }
}
