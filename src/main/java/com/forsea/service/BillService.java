package com.forsea.service;

import com.forsea.pojo.entity.Bill;

import java.util.List;

public interface BillService {
    Bill getBillByBid(Long bid);

    List<Bill> listBills();

    List<Bill> listBillsByUserId(Long uid);

    Bill saveBill(Bill bill);

    Long deleteBill(Long bid);

    Bill updateBill(Bill bill);

    Bill updateBillLeave(Bill bill);
}
