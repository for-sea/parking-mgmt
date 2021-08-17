package com.forsea.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.forsea.pojo.entity.Bill;
import com.forsea.pojo.Result;
import com.forsea.service.BillService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/bill")
public class BillController {
    @Autowired
    private BillService billService;

    @PostMapping("/enter")
    @ApiOperation("用户进入登记")
    public Result enter(@Validated @RequestBody @ApiParam("账单实体") Bill bill) {
        Bill insertedBill = billService.saveBill(bill);
        return Result.success().message("用户登记生成初始账单成功").data(insertedBill);
    }

    @PutMapping("/leave")
    @ApiOperation("用户离开登记")
    public Result leave(@RequestBody @ApiParam("账单实体") Bill bill) {
        Bill cost = billService.updateBillLeave(bill);
        return Result.success().message("用户离开更新账单成功").data(cost);
    }

    @GetMapping("/{bid}")
    @ApiOperation("通过账单id查询账单")
    public Result queryBillByBid(@PathVariable @ApiParam("账单id") Long bid) {
        Bill bill = billService.getBillByBid(bid);
        return Result.success().message("账单查询成功").data(bill);
    }

    @GetMapping("/list/{uid}")
    @ApiOperation("用户查询账单列表")
    public Result queryBillsByUserId(@PathVariable @ApiParam("用户id") Long uid) {
        List<Bill> bills = billService.listBillsByUserId(uid);
        return Result.success().message("用户查询账单列表成功").data(bills);
    }

    @GetMapping("/list")
    @ApiOperation("管理员查询所有账单列表")
    public Result queryBills() {
        List<Bill> bills = billService.listBills();
        return Result.success().message("管理员查询账单列表成功").data(bills);
    }

    @DeleteMapping("/list/{bid}")
    @ApiOperation("管理员删除订单")
    public Result deleteBill(@PathVariable @ApiParam("账单id") Long bid) {
        billService.deleteBill(bid);
        JSONObject obj = JSONUtil.createObj().putOpt("bid", bid);
        return Result.success().message("管理员删除账单成功").data(obj);
    }

    @PutMapping("/list")
    @ApiOperation("管理员修改账单信息")
    public Result modifyBill(@RequestBody @ApiParam("账单实体") Bill bill) {
        log.info("进入BillController ");
        log.info("账单实体： {}",bill.toString());
        Bill updatedBill = billService.updateBill(bill);
        return Result.success().message("管理员修改订单成功").data(updatedBill);
    }
}
