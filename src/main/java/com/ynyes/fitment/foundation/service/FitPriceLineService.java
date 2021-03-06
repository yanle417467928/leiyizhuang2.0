package com.ynyes.fitment.foundation.service;

import org.springframework.data.domain.Page;

import com.ynyes.fitment.foundation.entity.FitPriceLine;

public interface FitPriceLineService {

	Page<FitPriceLine> findByHeaderId(Long headerId, Integer page, Integer size) throws Exception;

	FitPriceLine findByHeaderIdAndGoodsId(Long headerId, Long goodsId) throws Exception;

	FitPriceLine findByEbsId(Long ebsId);

	FitPriceLine save(FitPriceLine line);
}
