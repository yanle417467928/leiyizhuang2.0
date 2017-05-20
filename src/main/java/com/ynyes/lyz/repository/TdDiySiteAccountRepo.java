package com.ynyes.lyz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ynyes.lyz.entity.TdDiySiteAccount;

/**
 * @title TdDiySiteAccount 实体数据库操作接口
 * @describe 
 * @author Generation Road
 * @date 2017年5月11日
 */
public interface TdDiySiteAccountRepo extends JpaRepository<TdDiySiteAccount, Long> {

	/**
	 * @title  根据门店ID查询门店账号ID
	 * @describe 
	 * @author Generation Road
	 * @date 2017年5月11日
	 * @param diySiteId
	 * @return
	 */
	TdDiySiteAccount findByDiySiteId(Long diySiteId);

}
