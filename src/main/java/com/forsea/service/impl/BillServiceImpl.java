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

    /**
     * 根据账单id查询账单
     *
     * @param bid
     * @return Bill对象
     */
    @Override
    public Bill getBillByBid(Long bid) {
        Bill bill = billDAO.selectBillByBid(bid);
        return bill;
    }

    /**
     * 查询所有账单列表
     *
     * @return 所有Bill列表
     */
    @Override
    public List<Bill> listBills() {
        return billDAO.selectBills();
    }

    /**
     * 根据用户id查询账单列表
     *
     * @param uid
     * @return 用户的Bill列表
     */
    @Override
    public List<Bill> listBillsByUserId(Long uid) {
        return billDAO.selectBillsByUserId(uid);
    }

    /**
     * 保存账单
     *
     * @param bill
     * @return Bill对象
     */
    @Override
    public Bill saveBill(Bill bill) {
        String beginTime = getCurrentTime();
        // 注入beginTime
        bill.setBeginTime(beginTime);

        billDAO.insertBill(bill);
        return billDAO.selectBillByBid(bill.getBid());
    }

    /**
     * 删除账单
     *
     * @param bid
     * @return 被删除账单的id
     */
    @Override
    public Long deleteBill(Long bid) {
        billDAO.deleteBill(bid);
        return bid;
    }

    /**
     * 离开时更新账单
     *
     * @param bill
     * @return 更新后的Bill对象
     */
    public Bill updateBillLeave(Bill bill) {
        Bill updatedBill = calCost(bill);
        return updatedBill;
    }

    /**
     * 更新账单
     *
     * @param bill
     * @return 更新后的Bill对象
     */
    @Override
    public Bill updateBill(Bill bill) {
        billDAO.updateBill(bill);
        return billDAO.selectBillByBid(bill.getBid());
    }

    /**
     * 获取当前时间
     *
     * @return 当前时间的字符串
     */
    public String getCurrentTime() {
        // 获取日期
        String today = DateUtil.today();
        // 获取时间并格式化
        DateTime date = DateUtil.date();
        String time = DateUtil.formatTime(date);
        // 拼接日期与时间
        String currentTime = today + " " + time;

        return currentTime;
    }

    /**
     * 更新账单的endTime、cost、stop
     *
     * @param bill
     * @return 更新后的Bill对象
     */
    public Bill calCost(Bill bill) {
        Integer cost;
        Integer stop;

        // 获取beginTime与endTime
        String endTime = getCurrentTime();
        Bill billBeforeUpdate = billDAO.selectBillByBid(bill.getBid());
        String beginTime = billBeforeUpdate.getBeginTime();
        log.info("beginTime: ======> {}, endTime: ======> {}", beginTime, endTime);

        // 字符串转换为DateTime类型，并计算停车时间
        DateTime time1 = DateUtil.parse(beginTime);
        DateTime time2 = DateUtil.parse(endTime);
        long betweenMinute = DateUtil.between(time1, time2, DateUnit.MINUTE);
        float tmp = (float) betweenMinute / 60 - 1; // 首小时不计费
        float betweenHour = Math.round(tmp * 100) / 100f; // 保留小数点后两位

        // 小数取整，计算cost：
        // 计费规则：首小时不计费，首小时过后，每过1小时收费2元，不满1小时按一小时计算，24小时内最多收费10元。
        if (betweenHour % 1 == 0) {
            // 小数部分为0
            stop = (int) Math.floor(betweenHour);
        } else {
            // 小数部分不为0
            stop = (int) Math.ceil(betweenHour);
        }
        int day = stop / 24;
        int hours = stop - day * 24;
        if (hours > 5 && hours < 24) {
            cost = day * 10 + 10;
        }else {
            cost = day * 10 + hours * 2;
        }
        log.info("停车时间{}天{}小时", day, hours);
        log.info("stop: ======> {}小时, cost: ======> {}元", stop, cost);

        // 注入endTime、cost、stop
        billBeforeUpdate.setEndTime(endTime);
        billBeforeUpdate.setCost(cost);
        billBeforeUpdate.setStop(stop);
        Bill billAfterUpdate = updateBill(billBeforeUpdate);

        // 返回完整的账单
        return billAfterUpdate;
    }
}
