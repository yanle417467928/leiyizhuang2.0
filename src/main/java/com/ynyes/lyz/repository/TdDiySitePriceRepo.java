package com.ynyes.lyz.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ynyes.lyz.entity.TdDiySitePrice;

/**
 * @title 门店商品价格中间表类
 * @describe
 * @author Generation Road
 * @date 2017年5月8日
 */
public interface TdDiySitePriceRepo extends JpaRepository<TdDiySitePrice, Serializable> {

	/**
	 * @title 根据分公司ID sobId、门店编码storeCode和门店类型custTypeCode查询未过期的价目表
	 * @describe
	 * @author Generation Road
	 * @date 2017年5月8日
	 * @param sobId
	 * @param storeCode
	 * @param custTypeCode
	 * @param start
	 * @param end
	 * @return
	 */
	TdDiySitePrice findBySobIdAndStoreCodeAndCustTypeCodeAndStartDateActiveAndEndDateActiveOrSobIdAndStoreCodeAndCustTypeCodeAndStartDateActiveAndEndDateActiveIsNull(
			Long sobId, String storeCode, String custTypeCode, Date start, Date end, Long sobId2, String storeCode2,
			String custTypeCode2, Date start2);

	TdDiySitePrice findByAssignId(Long assignId);

	List<TdDiySitePrice> findByStoreCodeAndStartDateActiveBeforeAndEndDateActiveAfter(String storeCode, Date startDate,
			Date endDate);

}
