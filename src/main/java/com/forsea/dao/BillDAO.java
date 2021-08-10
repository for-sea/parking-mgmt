package com.forsea.dao;

import com.forsea.pojo.entity.Bill;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BillDAO {
    @Select("select * from bill where bid=#{bid}")
    @Results(id = "Bill", value = {
            @Result(id = true, column = "bid", property = "bid"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "license", property = "license"),
            @Result(column = "begin_time", property = "beginTime"),
            @Result(column = "end_time", property = "endTime"),
            @Result(column = "stoped", property = "stoped"),
            @Result(column = "cost", property = "cost"),
            @Result(column = "created", property = "created")

    })
    Bill getBillById(Long bid);

    @Insert("insert into bill (user_id, license, begin_time) " +
            "values (#{userId}, #{license}, #{beginTime});")
    @ResultMap(value = "Bill")
    @Options(keyProperty = "bid", keyColumn = "bid", useGeneratedKeys = true)
    void insertBill(Bill bill);

    @Update("<script>update bill set" +
            "<if test='userId != null'>user_id=#{userId}, </if>" +
            "<if test='license != null'>license=#{license}, </if>" +
            "<if test='beginTime != null'>begin_time=#{beginTime}, </if>" +
            "<if test='endTime != null'>end_time=#{endTime}, </if>" +
            "<if test='stoped != null'>stoped=#{stoped}, </if>" +
            "<if test='cost != null'>cost=#{cost}, </if>" +
            "bid=#{bid} " +
            "where bid=#{bid}" +
            "</script>")
    void updateBill(Bill bill);

    @Select("select * from bill;")
    @ResultMap(value = "Bill")
    List<Bill> queryBills();

    @Select("select * from bill where user_id=#{uid}")
    @ResultMap(value = "Bill")
    List<Bill> queryBillsByUserId(Long uid);

    @Delete("delete from bill where bid=#{bid};")
    void deleteBill(Long bid);
}
