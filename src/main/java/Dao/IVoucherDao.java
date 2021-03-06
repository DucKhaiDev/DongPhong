package Dao;

import Entity.Voucher;

import java.util.List;

public interface IVoucherDao {
    void insert(Voucher voucher);

    void edit(Voucher voucher);

    void delete(String voucherId);

    Voucher getVoucher(String voucherId);

    List<Voucher> getAll();

    List<String> getAllVoucher();

    boolean checkExistId(String voucherId);

    List<Voucher> getAvailableVoucher(String cartId);
}