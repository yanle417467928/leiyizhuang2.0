package com.ynyes.lyz.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.lyz.entity.TdDiySitePrice;
import com.ynyes.lyz.repository.TdDiySitePriceRepo;


/**
 * @title 门店商品价格中间表服务类
 * @describe 
 * @author Generation Road
 * @date 2017年5月8日
 */
@Service
@Transactional
public class TdDiySitePriceService {
	
	@Autowired
	private TdDiySitePriceRepo repository;
	
	
	/**
	 * @title 根据分公司ID sobId、门店编码storeCode和门店类型custTypeCode查询未过期的
	 * @describe 
	 * @author Generation Road
	 * @date 2017年5月8日
	 * @param sobId
	 * @param storeCode
	 * @param custTypeCode  (1:JX  2:ZY)
	 * @param start
	 * @param end
	 * @return
	 */
	public TdDiySitePrice getDiySitePrice(Long sobId, String storeCode, String custTypeCode, Date start, Date end){
		return repository.findBySobIdAndStoreCodeAndCustTypeCodeAndStartDateActiveAndEndDateActiveOrSobIdAndStoreCodeAndCustTypeCodeAndStartDateActiveAndEndDateActiveIsNull(sobId, storeCode, custTypeCode, start, end, sobId, storeCode, custTypeCode, start);
	}

}
