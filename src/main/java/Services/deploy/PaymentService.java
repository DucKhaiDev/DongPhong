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
    public void delete(String PAY_ID) {
        paymentDao.delete(PAY_ID);
    }

    @Override
    public Payment getPayment(String PAY_ID) {
        return paymentDao.getPayment(PAY_ID);
    }

    @Override
    public List<Payment> getAll() {
        return paymentDao.getAll();
    }
}
