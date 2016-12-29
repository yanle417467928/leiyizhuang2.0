package com.ynyes.fitment.foundation.entity.client;

import java.math.BigDecimal;

import com.ynyes.fitment.core.entity.client.ClientEntity;
import com.ynyes.fitment.foundation.entity.FitCompanyGoods;
import com.ynyes.fitment.foundation.entity.FitPriceLine;
import com.ynyes.lyz.entity.TdDiySiteInventory;

public class ClientCompanyGoods extends ClientEntity {

	private String title;

	private String sku;

	private String imageUri;

	private BigDecimal price;
	
	private BigDecimal realPrice;

	private Long inventory;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getImageUri() {
		return imageUri;
	}

	public void setImageUri(String imageUri) {
		this.imageUri = imageUri;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getRealPrice() {
		return realPrice;
	}

	public void setRealPrice(BigDecimal realPrice) {
		this.realPrice = realPrice;
	}

	public Long getInventory() {
		return inventory;
	}

	public void setInventory(Long inventory) {
		this.inventory = inventory;
	}

	public ClientCompanyGoods init(FitCompanyGoods goods, TdDiySiteInventory inventory, FitPriceLine line) {
		this.setTitle(goods.getGoodsTitle());
		this.setSku(goods.getGoodsSku());
		this.setInventory(inventory.getInventory());
		this.setPrice(line.getPrice());
		this.setRealPrice(line.getRealPrice());
		return this;
	}
}