package com.forsea.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.forsea.pojo.entity.Bill;
import com.forsea.pojo.Result;
import com.forsea.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillController {
    @Autowired
    private BillService billService;

    /**
     * 用户进入登记
     * @param bill
     * @return
     */
    @RequestMapping(value = "/in", method = RequestMethod.POST)
    public Result in(@RequestBody Bill bill){
        Bill insertedBill = billService.insertBill(bill);
        return Result.success().message("成功生成初始账单").data(insertedBill);
    }

    /**
     * 用户离开登记
     * @param bill
     * @return
     */
    @RequestMapping(value = "/out", method = RequestMethod.PUT)
    public Result out(@RequestBody Bill bill){
        Bill cost = billService.calCost(bill);
        return Result.success().message("成功生成账单").data(cost);
    }

    @RequestMapping(value = "/{bid}", method = RequestMethod.GET)
    public Result getBillByBid(@PathVariable Long bid){
        Bill bill = billService.queryBillByBid(bid);
        return Result.success().message("账单查询成功").data(bill);
    }

    /**
     * 查询用户账单列表
     * @param uid
     * @return
     */
    @RequestMapping(value = "/list/{uid}", method = RequestMethod.GET)
    public Result queryBillsByUserId(@PathVariable Long uid){
        List<Bill> bills = billService.queryBillsByUserId(uid);
        return Result.success().message("成功查询用户账单列表").data(bills);
    }

    /**
     * 查询所有账单列表
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result queryBills(){
        List<Bill> bills = billService.queryBills();
        return Result.success().message("成功获取账单列表").data(bills);
    }

    /**
     * 管理员修改订单信息
     * @param bill
     * @return
     */
    @RequestMapping(value = "/alter", method = RequestMethod.PUT)
    public Result updateBill(@RequestBody Bill bill){
        Bill updatedBill = billService.updateBill(bill);
        return Result.success().message("修改订单成功").data(updatedBill);
    }

    /**
     * 管理员删除订单
     * @param bid
     * @return
     */
    @RequestMapping(value = "/list/{bid}", method = RequestMethod.DELETE)
    public Result deleteBill(@PathVariable Long bid){
        billService.deleteBill(bid);
        JSONObject obj = JSONUtil.createObj().putOpt("bid", bid);
        return Result.success().message("删除账单成功").data(obj);
    }
}
