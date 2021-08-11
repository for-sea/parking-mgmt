package com.forsea.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.forsea.pojo.entity.Bill;
import com.forsea.pojo.Result;
import com.forsea.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillController {
    @Autowired
    private BillService billService;

    /**
     * 用户进入登记
     *
     * @param bill
     * @return
     */
    @PostMapping("/enter")
    public Result enter(@Validated @RequestBody Bill bill) {
        Bill insertedBill = billService.saveBill(bill);
        return Result.success().message("用户登记生成初始账单成功").data(insertedBill);
    }

    /**
     * 用户离开登记
     *
     * @param bill
     * @return
     */
    @PutMapping("/leave")
    public Result leave(@RequestBody Bill bill) {
        Bill cost = billService.updateBillLeave(bill);
        return Result.success().message("用户离开更新账单成功").data(cost);
    }

    /**
     * 通过账单id查询账单
     *
     * @param bid
     * @return
     */
    @GetMapping("/{bid}")
    public Result queryBillByBid(@PathVariable Long bid) {
        Bill bill = billService.getBillByBid(bid);
        return Result.success().message("账单查询成功").data(bill);
    }

    /**
     * 用户查询账单列表
     *
     * @param uid
     * @return
     */
    @GetMapping("/list/{uid}")
    public Result queryBillsByUserId(@PathVariable Long uid) {
        List<Bill> bills = billService.listBillsByUserId(uid);
        return Result.success().message("用户查询账单列表成功").data(bills);
    }

    /**
     * 管理员查询所有账单列表
     *
     * @return
     */
    @GetMapping("/list")
    public Result queryBills() {
        List<Bill> bills = billService.listBills();
        return Result.success().message("管理员查询账单列表成功").data(bills);
    }

    /**
     * 管理员删除订单
     *
     * @param bid
     * @return
     */
    @DeleteMapping("/list/{bid}")
    public Result deleteBill(@PathVariable Long bid) {
        billService.deleteBill(bid);
        JSONObject obj = JSONUtil.createObj().putOpt("bid", bid);
        return Result.success().message("管理员删除账单成功").data(obj);
    }

    /**
     * 管理员修改账单信息
     *
     * @param bill
     * @return
     */
    @PutMapping("/list")
    public Result modifyBill(@RequestBody Bill bill) {
        Bill updatedBill = billService.updateBill(bill);
        return Result.success().message("管理员修改订单成功").data(updatedBill);
    }
}
