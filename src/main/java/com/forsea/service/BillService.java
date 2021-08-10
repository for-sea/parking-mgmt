package com.forsea.service;

import com.forsea.pojo.entity.Bill;

import java.util.List;

public interface BillService {
    Bill calCost(Bill bill);

    Bill insertBill(Bill bill);

    Bill updateBill(Bill bill);

    List<Bill> queryBills();

    List<Bill> queryBillsByUserId(Long uid);

    Long deleteBill(Long bid);

    Bill queryBillByBid(Long bid);
}
