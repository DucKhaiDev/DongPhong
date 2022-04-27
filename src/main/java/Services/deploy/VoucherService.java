package Services.deploy;

import Dao.deploy.VoucherDao;
import Entity.Voucher;
import Services.IVoucherService;

import java.util.List;

public class VoucherService implements IVoucherService {
    private final VoucherDao voucherDao = new VoucherDao();

    @Override
    public void insert(Voucher voucher) {
        voucherDao.insert(voucher);
    }

    @Override
    public void edit(Voucher voucher) {
        voucherDao.edit(voucher);
    }

    @Override
    public void delete(String voucherId) {
        voucherDao.delete(voucherId);
    }

    @Override
    public Voucher getVoucher(String voucherId) {
        return voucherDao.getVoucher(voucherId);
    }

    @Override
    public List<Voucher> getAll() {
        return voucherDao.getAll();
    }

    @Override
    public List<String> getAllVoucher() {
        return voucherDao.getAllVoucher();
    }

    @Override
    public boolean checkExistId(String voucherId) {
        return voucherDao.checkExistId(voucherId);
    }

    @Override
    public List<Voucher> getAvailableVoucher(String cartId) {
        return voucherDao.getAvailableVoucher(cartId);
    }
}