package com.forsea.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.forsea.dao.BillDAO;
import com.forsea.pojo.entity.Bill;
import com.forsea.service.BillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BillServiceImpl implements BillService {
    @Autowired
    private BillDAO billDAO;
    @Override
    public Bill insertBill(Bill bill) {
        String today = DateUtil.today();
        DateTime date = DateUtil.date();
        String time = DateUtil.formatTime(date);
        String beginTime = today + " " + time;
        // 注入beginTime
        bill.setBeginTime(beginTime);
        billDAO.insertBill(bill);
        return billDAO.getBillById(bill.getBid());
    }

    public Bill calCost(Bill bill){
        Integer cost = new Integer(0);
        Integer stoped = new Integer(0);
        // 获取beginTime与endTime
        Bill billBeforeUpdate = billDAO.getBillById(bill.getBid());
        String today = DateUtil.today();
        DateTime date = DateUtil.date();
        String time = DateUtil.formatTime(date);
        String endTime = today + " " + time;
        String beginTime = billBeforeUpdate.getBeginTime();
        log.info("beginTime: ======> {}, endTime: ======> {}", beginTime, endTime);
        // 转换成字符串小时并计算
        DateTime time1 = DateUtil.parse(beginTime);
        DateTime time2 = DateUtil.parse(endTime);
        long betweenMinute = DateUtil.between(time1, time2, DateUnit.MINUTE);
        float hour = (float) betweenMinute / 60 - 1; // 首小时不计费
        float betweenHour = Math.round(hour * 100) / 100f; // 保留小数点后两位
        // 小数取整
        if (betweenHour % 1 == 0){
            // 小数部分为0
            stoped = (int)Math.floor(betweenHour);
        }else{
            // 小数部分不为0
            stoped = (int)Math.ceil(betweenHour);
        }
        cost = (int)(stoped / 24) * 10 + (int)(stoped % 24) * 2;
        log.info("stoped: ======> {}小时, cost: ======> {}元", stoped, cost);
        // 注入endTime、cost、stoped
        billBeforeUpdate.setEndTime(endTime);
        billBeforeUpdate.setCost(cost);
        billBeforeUpdate.setStoped(stoped);
        Bill billAfterUpdate = updateBill(billBeforeUpdate);
        // 返回完整的账单
        return billAfterUpdate;
    }

    @Override
    public Bill updateBill(Bill bill) {
        billDAO.updateBill(bill);
        return billDAO.getBillById(bill.getBid());
    }

    @Override
    public List<Bill> queryBills() {
        return billDAO.queryBills();
    }

    @Override
    public List<Bill> queryBillsByUserId(Long uid) {
        return billDAO.queryBillsByUserId(uid);
    }

    @Override
    public Long deleteBill(Long bid) {
        billDAO.deleteBill(bid);
        return bid;
    }

    @Override
    public Bill queryBillByBid(Long bid) {
        Bill bill = billDAO.getBillById(bid);
        return bill;
    }
}
