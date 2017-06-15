package com.ynyes.lyz.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

//要货单

@Entity
public class TdRequisition {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// 门店名称
	@Column
	private String diySiteTitle;

	// 门店编码
	@Column
	private Long diySiteId;

	// 客户姓名(用户名)
	@Column
	private String customerName;

	// 客户编码(用户id)
	@Column
	private Long customerId;

	// 原单号（主订单号）
	@Column
	private String orderNumber;

	// 总金额
	@Column
	private Double totalPrice;

	// 代收金额
	@Column
	private Double leftPrice;

	// 送货时间
	@Column
	private String deliveryTime;

	// 收货人(姓名)
	@Column
	private String receiveName;

	// 收货人地址
	@Column
	private String receiveAddress;

	// 拆分的收货地址分为：城市 + 区 + 街道 + 详细地址
	// 省
	@Column
	private String province;

	// 城市
	@Column
	private String city;

	// 区
	@Column
	private String disctrict;

	// 街道
	@Column
	private String subdistrict;

	// 详细地址
	@Column
	private String detailAddress;

	// 收货人电话
	@Column
	private String receivePhone;

	// 订单商品
	@OneToMany
	@JoinColumn(name = "TdRequisitionId")
	private List<TdRequisitionGoods> requisiteGoodsList;

	// 下单时间
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date orderTime;

	// 要货单类型 1： 要货单 2：要货单退单 3： 要货单订单
	@Column
	private Long typeId;

	// 订单备注
	@Column
	private String remarkInfo;

	// 门店电话
	@Column
	private String diySiteTel;

	// 门店编码
	@Column
	private String diyCode;

	// 销售顾问名字
	@Column
	private String sellerRealName;

	// 销顾电话
	@Column(length = 20)
	private String sellerTel;

	// 商品总数
	@Column
	private Integer goodsQuantity;

	// 上楼费总额
	@Column
	private Double upstairsAll = 0d;

	// 剩余上楼费
	@Column
	private Double upstairsLeft = 0d;

	// 运费总额
	@Column(scale = 2, nullable = false)
	private Double deliveryFee = 0d;

	// 调色费
	@Column(scale = 2, nullable = false)
	private Double colorFee = 0d;

	// 表示所有的折扣金额，当前包括：满减促销折扣，现金券折扣，产品券折扣和会员差价折扣
	@Column(scale = 2, nullable = false)
	private Double discount = 0d;

	// 表示用户已经使用的预存款金额
	@Column(scale = 2, nullable = false)
	private Double balanceUsed = 0d;

	// 表示第三方支付金额
	@Column(scale = 2, nullable = false)
	private Double otherPayed = 0d;
	
	// 表示是否是主家收货
	@Column(length = 5, nullable = false)
	private String memberReceiver = "FALSE";

	@Column(scale = 2, nullable = false)
	private Double totalGoodsPrice = 0d;

	public String getSellerRealName() {
		return sellerRealName;
	}

	public void setSellerRealName(String sellerRealName) {
		this.sellerRealName = sellerRealName;
	}

	public String getDiyCode() {
		return diyCode;
	}

	public void setDiyCode(String diyCode) {
		this.diyCode = diyCode;
	}

	public Double getLeftPrice() {
		return leftPrice;
	}

	public void setLeftPrice(Double leftPrice) {
		this.leftPrice = leftPrice;
	}

	public String getDiySiteTel() {
		return diySiteTel;
	}

	public void setDiySiteTel(String diySiteTel) {
		this.diySiteTel = diySiteTel;
	}

	public String getRemarkInfo() {
		return remarkInfo;
	}

	public void setRemarkInfo(String remarkInfo) {
		this.remarkInfo = remarkInfo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDiySiteTitle() {
		return diySiteTitle;
	}

	public void setDiySiteTitle(String diySiteTitle) {
		this.diySiteTitle = diySiteTitle;
	}

	public Long getDiySiteId() {
		return diySiteId;
	}

	public void setDiySiteId(Long diySiteId) {
		this.diySiteId = diySiteId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getReceiveName() {
		return receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}

	public String getReceiveAddress() {
		return receiveAddress;
	}

	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}

	public String getReceivePhone() {
		return receivePhone;
	}

	public void setReceivePhone(String receivePhone) {
		this.receivePhone = receivePhone;
	}

	public List<TdRequisitionGoods> getRequisiteGoodsList() {
		return requisiteGoodsList;
	}

	public void setRequisiteGoodsList(List<TdRequisitionGoods> requisiteGoodsList) {
		this.requisiteGoodsList = requisiteGoodsList;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDisctrict() {
		return disctrict;
	}

	public void setDisctrict(String disctrict) {
		this.disctrict = disctrict;
	}

	public String getSubdistrict() {
		return subdistrict;
	}

	public void setSubdistrict(String subdistrict) {
		this.subdistrict = subdistrict;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getSellerTel() {
		return sellerTel;
	}

	public void setSellerTel(String sellerTel) {
		this.sellerTel = sellerTel;
	}

	public Integer getGoodsQuantity() {
		return goodsQuantity;
	}

	public void setGoodsQuantity(Integer goodsQuantity) {
		this.goodsQuantity = goodsQuantity;
	}

	public Double getUpstairsAll() {
		return upstairsAll;
	}

	public void setUpstairsAll(Double upstairsAll) {
		this.upstairsAll = upstairsAll;
	}

	public Double getUpstairsLeft() {
		return upstairsLeft;
	}

	public void setUpstairsLeft(Double upstairsLeft) {
		this.upstairsLeft = upstairsLeft;
	}

	public Double getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(Double deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	public Double getColorFee() {
		return colorFee;
	}

	public void setColorFee(Double colorFee) {
		this.colorFee = colorFee;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getBalanceUsed() {
		return balanceUsed;
	}

	public void setBalanceUsed(Double balanceUsed) {
		this.balanceUsed = balanceUsed;
	}

	public String getMemberReceiver() {
		return memberReceiver;
	}

	public void setMemberReceiver(String memberReceiver) {
		this.memberReceiver = memberReceiver;
	}

	public Double getTotalGoodsPrice() {
		return totalGoodsPrice;
	}

	public void setTotalGoodsPrice(Double totalGoodsPrice) {
		this.totalGoodsPrice = totalGoodsPrice;
	}

	public Double getOtherPayed() {
		return otherPayed;
	}

	public void setOtherPayed(Double otherPayed) {
		this.otherPayed = otherPayed;
	}

	@Override
	public String toString() {
		return "TdRequisition [id=" + id + ", diySiteTitle=" + diySiteTitle + ", diySiteId=" + diySiteId
				+ ", customerName=" + customerName + ", customerId=" + customerId + ", orderNumber=" + orderNumber
				+ ", totalPrice=" + totalPrice + ", leftPrice=" + leftPrice + ", deliveryTime=" + deliveryTime
				+ ", receiveName=" + receiveName + ", receiveAddress=" + receiveAddress + ", province=" + province
				+ ", city=" + city + ", disctrict=" + disctrict + ", subdistrict=" + subdistrict + ", detailAddress="
				+ detailAddress + ", receivePhone=" + receivePhone + ", requisiteGoodsList=" + requisiteGoodsList
				+ ", orderTime=" + orderTime + ", typeId=" + typeId + ", remarkInfo=" + remarkInfo + ", diySiteTel="
				+ diySiteTel + ", diyCode=" + diyCode + ", sellerRealName=" + sellerRealName + ", sellerTel="
				+ sellerTel + ", goodsQuantity=" + goodsQuantity + ", upstairsAll=" + upstairsAll + ", upstairsLeft="
				+ upstairsLeft + ", deliveryFee=" + deliveryFee + ", colorFee=" + colorFee + ", discount=" + discount
				+ ", balanceUsed=" + balanceUsed + ", otherPayed=" + otherPayed + ", memberReceiver=" + memberReceiver
				+ ", totalGoodsPrice=" + totalGoodsPrice + "]";
	}

	public String toXml() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("<ERP><TABLE>");
		
		builder.append("<ID>").append(this.id).append("</ID>");
		builder.append("<CANCEL_TIME></CANCEL_TIME>");
		builder.append("<CHECK_TIME></CHECK_TIME>");
		builder.append("<DIY_SITE_ADDRESS></DIY_SITE_ADDRESS>");
		builder.append("<DIY_SITE_ID>").append(this.diySiteId).append("</DIY_SITE_ID>");
		builder.append("<DIY_SITE_TEL>").append(this.diySiteTel).append("</DIY_SITE_TEL>");
		builder.append("<MANAGER_REMARK_INFO></MANAGER_REMARK_INFO>");
		builder.append("<REMARK_INFO>").append(this.remarkInfo).append("</REMARK_INFO>");
		builder.append("<REQUISITION_NUMBER></REQUISITION_NUMBER>");
		builder.append("<STATUS_ID></STATUS_ID>");
		builder.append("<TYPE_ID></TYPE_ID>");
		builder.append("<CUSTOMER_NAME>").append(this.customerName).append("</CUSTOMER_NAME>");
		builder.append("<CUSTOMER_ID>").append(this.customerId).append("</CUSTOMER_ID>");
		builder.append("<DELIVERY_TIME>").append(this.deliveryTime).append("</DELIVERY_TIME>");
		builder.append("<ORDER_NUMBER>").append(this.orderNumber).append("</ORDER_NUMBER>");
		builder.append("<RECEIVE_ADDRESS>").append(this.receiveAddress).append("</RECEIVE_ADDRESS>");
		builder.append("<RECEIVE_NAME>").append(this.receiveName).append("</RECEIVE_NAME>");
		builder.append("<RECEIVE_PHONE>").append(this.receivePhone).append("</RECEIVE_PHONE>");
		builder.append("<CITY>").append(this.city).append("</CITY>");
		builder.append("<DETAIL_ADDRESS>").append(this.detailAddress).append("</DETAIL_ADDRESS>");
		builder.append("<DISCTRICT>").append(this.disctrict).append("</DISCTRICT>");
		builder.append("<PROVINCE>").append(this.province).append("</PROVINCE>");
		builder.append("<SUBDISCTRICT>").append(this.subdistrict).append("</SUBDISCTRICT>");
		builder.append("<ORDER_TIME>").append(this.orderTime).append("</ORDER_TIME>");
		builder.append("<SUB_ORDER_NUMBER></SUB_ORDER_NUMBER>");
		builder.append("<SELLER_TEL>").append(this.sellerTel).append("</SELLER_TEL>");
		builder.append("<GOODS_QUANTITY>").append(this.goodsQuantity).append("</GOODS_QUANTITY>");
		builder.append("<UPSTAIRS_ALL>").append(this.upstairsAll).append("</UPSTAIRS_ALL>");
		builder.append("<UPSTAIRS_LEFT>").append(this.upstairsLeft).append("</UPSTAIRS_LEFT>");
		builder.append("<SELLER_NAME>").append(this.sellerRealName).append("</SELLER_NAME>");
		builder.append("<DELIVERY_FEE>").append(this.deliveryFee).append("</DELIVERY_FEE>");
		builder.append("<COLOR_FEE>").append(this.colorFee).append("</COLOR_FEE>");
		builder.append("<DISCOUNT>").append(this.discount).append("</DISCOUNT>");
		builder.append("<BALANCE_USED>").append(this.balanceUsed).append("</BALANCE_USED>");
		builder.append("<OTHER_PAYED>").append(this.otherPayed).append("</OTHER_PAYED>");
		builder.append("<MEMBER_RECEIVER>").append(this.memberReceiver).append("</MEMBER_RECEIVER>");
		builder.append("<UNPAYED>").append(this.leftPrice).append("</UNPAYED>");
		builder.append("<TOTAL_GOODS_PRICE>").append(this.totalGoodsPrice).append("</TOTAL_GOODS_PRICE>");
		
		builder.append("</TABLE></ERP>");
		
		return builder.toString();
	}

}
