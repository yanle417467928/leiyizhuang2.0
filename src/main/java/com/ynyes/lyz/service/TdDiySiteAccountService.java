package com.ynyes.lyz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.lyz.entity.TdDiySiteAccount;
import com.ynyes.lyz.entity.TdOrder;
import com.ynyes.lyz.entity.TdReturnNote;
import com.ynyes.lyz.entity.user.TdUser;
import com.ynyes.lyz.repository.TdDiySiteAccountRepo;

/**
 * @title 门店账号相关事业务类
 * @describe 
 * @author Generation Road
 * @date 2017年5月11日
 */
@Service
@Transactional
public class TdDiySiteAccountService {
	
	@Autowired
	private TdDiySiteAccountRepo tdDiySiteAccountRepo;
	
	@Autowired
	private TdOrderService tdOrderService;
	
	@Autowired
	private TdUserService tdUserService;
	
	@Autowired
	private TdReturnNoteService tdReturnNoteService;
	
	/**
	 * @title 返还经销商下单的差价
	 * @describe 
	 * @author Generation Road
	 * @date 2017年5月11日
	 * @param tdOrder
	 */
	public void returnSpread(TdOrder tdOrder){
		
		if (null == tdOrder) {
			return;
		}
		
		//根据order计算需要返还的差价
		double spread = tdOrderService.calculateAllSpread(tdOrder);
		
		if (0.0 == spread) {
			return;
		}
		
		//根据门店ID查询门店账号ID
		TdDiySiteAccount tdDiySiteAccount =tdDiySiteAccountRepo.findByDiySiteId(tdOrder.getDiySiteId());
		
		if (null == tdDiySiteAccount) {
			return;
		}
		//根据门店账号ID查询user信息
		TdUser tdUser = tdUserService.findOne(tdDiySiteAccount.getUserId());
		
		tdUser.setUnCashBalance(tdUser.getUnCashBalance() + spread);
//		tdUser.setBalance(tdUser.getBalance() + spread);
		//保存
		tdUser = tdUserService.save(tdUser);
		
		// 日志
		
	}
	
	
	
	/**
	 * @title 返还经销商退单的差价
	 * @describe 
	 * @author Generation Road
	 * @date 2017年5月11日
	 * @param tdOrder
	 */
	public String returnSpread(TdReturnNote tdReturnNote){
		String msg = "";
		if (null == tdReturnNote) {
			msg = "未找到退单!";
			return msg;
		}
		
		//根据order计算需要返还的差价
		double spread = tdReturnNoteService.calculateAllSpread(tdReturnNote);
		
		if (0.0 == spread) {
			msg = "无需返还差价!";
			return msg;
		}
		
		//根据门店ID查询门店账号ID
		TdDiySiteAccount tdDiySiteAccount =tdDiySiteAccountRepo.findByDiySiteId(tdReturnNote.getDiySiteId());
		
		if (null == tdDiySiteAccount) {
			msg = "返还差价失败!";
			return msg;
		}
		//根据门店账号ID查询user信息
		TdUser tdUser = tdUserService.findOne(tdDiySiteAccount.getUserId());
		
		boolean temp = isReturnSpread(tdUser, spread);
		
		if (!temp) {
			msg = "余额不足！";
			return msg;
		}
		
		tdUser.setUnCashBalance(tdUser.getUnCashBalance() - spread);
//		tdUser.setBalance(tdUser.getBalance() - spread);
		//保存
		tdUser = tdUserService.save(tdUser);
		
		// 日志
		
		msg = "返还差价成功！";
		return msg;
	}
	
	
	/**
	 * @title 判断账户余额是否大于需要扣除的返还差价
	 * @describe 
	 * @author Generation Road
	 * @date 2017年5月11日
	 * @param tdUser
	 * @param spread
	 * @return
	 */
	public boolean isReturnSpread(TdUser tdUser, double spread){
		if (tdUser.getBalance() >= spread) {
			return true;
		}
		return false;
	}
	

}
