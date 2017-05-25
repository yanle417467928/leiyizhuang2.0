package com.ynyes.lyz.entity;

public class OrderGoodsTemp {

	private Long goodsId;
	private Double lsPrice = 0d;
	private Double jxPrice = 0d;
	private Long quantity = 0L;
	private Long freeCount = 0L;
	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	public Double getLsPrice() {
		return lsPrice;
	}
	public void setLsPrice(Double lsPrice) {
		this.lsPrice = lsPrice;
	}
	public Double getJxPrice() {
		return jxPrice;
	}
	public void setJxPrice(Double jxPrice) {
		this.jxPrice = jxPrice;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public Long getFreeCount() {
		return freeCount;
	}
	public void setFreeCount(Long freeCount) {
		this.freeCount = freeCount;
	}
}
