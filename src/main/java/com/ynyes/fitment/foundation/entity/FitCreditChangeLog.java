package com.ynyes.fitment.foundation.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.ynyes.fitment.core.constant.CreditChangeType;
import com.ynyes.fitment.core.constant.CreditOperator;
import com.ynyes.fitment.core.entity.persistent.table.TableEntity;

@Entity
@Table(name = "FIT_CREDIT_CHANGE_LOG")
public class FitCreditChangeLog extends TableEntity {

	private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyyMMddHHmmss");

	// 变更前余额
	@Column(scale = 2, nullable = false, updatable = false)
	private Double beforeChange;

	// 变更后余额
	@Column(scale = 2, nullable = false, updatable = false)
	private Double afterChange;

	// 变更额度
	@Column(scale = 2, nullable = false, updatable = false)
	private Double money;

	// 变更时间
	@Column(nullable = false, updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date changeTime;
	
	// 涉及单号
	@Column(length = 30, updatable = false, nullable = false)
	private String referenceNumber;

	// 变更类型
	@Column(length = 10, nullable = false, updatable = false)
	@Enumerated(EnumType.STRING)
	private CreditChangeType type;

	// 操作人类型
	@Column(length = 10, nullable = false, updatable = false)
	@Enumerated(EnumType.STRING)
	private CreditOperator operatorType;

	// 操作人id
	@Column(nullable = false, updatable = false)
	private Long operatorId;

	// 备注
	@Column(length = 255, updatable = false, nullable = false)
	private String remark = "";

	// 相关装饰公司名
	@Column(nullable = false, updatable = false)
	private String companyTitle;

	// 相关装饰公司ID
	@Column(nullable = false, updatable = false)
	private Long companyId;

	public Double getBeforeChange() {
		return beforeChange;
	}

	public FitCreditChangeLog setBeforeChange(Double beforeChange) {
		this.beforeChange = beforeChange;
		return this;
	}

	public Double getAfterChange() {
		return afterChange;
	}

	public FitCreditChangeLog setAfterChange(Double afterChange) {
		this.afterChange = afterChange;
		return this;
	}

	public Double getMoney() {
		return money;
	}

	public FitCreditChangeLog setMoney(Double money) {
		this.money = money;
		return this;
	}

	public Date getChangeTime() {
		return changeTime;
	}

	public FitCreditChangeLog setChangeTime(Date changeTime) {
		this.changeTime = changeTime;
		return this;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public FitCreditChangeLog setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
		return this;
	}

	public CreditChangeType getType() {
		return type;
	}

	public FitCreditChangeLog setType(CreditChangeType type) {
		this.type = type;
		return this;
	}

	public CreditOperator getOperatorType() {
		return operatorType;
	}

	public FitCreditChangeLog setOperatorType(CreditOperator operatorType) {
		this.operatorType = operatorType;
		return this;
	}

	public Long getOperatorId() {
		return operatorId;
	}

	public FitCreditChangeLog setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
		return this;
	}

	public String getRemark() {
		return remark;
	}

	public FitCreditChangeLog setRemark(String remark) {
		this.remark = remark;
		return this;
	}

	public String getCompanyTitle() {
		return companyTitle;
	}

	public FitCreditChangeLog setCompanyTitle(String companyTitle) {
		this.companyTitle = companyTitle;
		return this;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public FitCreditChangeLog setCompanyId(Long companyId) {
		this.companyId = companyId;
		return this;
	}

	public String initManagerOperateNumber() {
		StringBuffer buffer = new StringBuffer("MO").append(FORMATTER.format(new Date()))
				.append((int) (Math.random() * 900) + 100);
		return buffer.toString();
	}
}
