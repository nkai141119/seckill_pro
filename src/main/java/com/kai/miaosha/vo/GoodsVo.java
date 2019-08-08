package com.kai.miaosha.vo;

import java.util.Date;

import com.kai.miaosha.domain.Goods;
//将Goods表和MiaoshaGoods表合并，继承了Goods，但是添加了更多来自MiaoshaGoods的功能属性
public class GoodsVo extends Goods{
	private Integer stockCount;
	private Date startDate;
	private Date endDate;
	private Double miaoshaPrice;
	
	
	public Double getMiaoshaPrice() {
		return miaoshaPrice;
	}
	public void setMiaoshaPrice(Double miaoshaPrice) {
		this.miaoshaPrice = miaoshaPrice;
	}
	public Integer getStockCount() {
		return stockCount;
	}
	public void setStockCount(Integer stockCount) {
		this.stockCount = stockCount;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}
