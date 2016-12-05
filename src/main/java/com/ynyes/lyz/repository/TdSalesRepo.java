package com.ynyes.lyz.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.lyz.entity.TdSales;

/**
 *  销量报表
 * 
 * @author
 *
 */

public interface TdSalesRepo extends
		PagingAndSortingRepository<TdSales, Long>,
		JpaSpecificationExecutor<TdSales> {

	@Query(value=" SELECT "
			+" 	uuid() id, "
			+" 	diy.city city_name, "
			+" 	o.diy_site_name diy_site_name, "
			+" 	'全款订单' AS stype, "
			+" 	o.main_order_number main_order_number, "
			+" 	o.order_number order_number, "
			+" 	o.order_time order_time, "
			+" 	o.username username, "
			+" 	o.real_user_real_name real_name, "
			+" 	o.seller_username seller_username, "
			+" 	o.seller_real_name seller_real_name, "
			+" 	ogi.sku sku, "
			+" 	ogi.goods_title goods_title, "
			+" 	ogi.gift_flag is_gift, "
			+" 	CASE ogi.gift_flag "
			+" WHEN 'N' THEN "
			+" 	ogi.ls_price "
			+" WHEN 'Y' THEN "
			+" 	0 "
			+" END goods_price, "
			+"  ogi.quantity goods_quantity, "
			+"  IFNULL(coupon.cash_coupon_price, 0) cash_coupon_price, "
			+"  IFNULL( "
			+" 	coupon.cash_coupon_quantity, "
			+" 	0 "
			+" ) cash_coupon_quantity, "
			+"  IFNULL( "
			+" 	coupon.product_coupon_price, "
			+" 	0 "
			+" ) product_coupon_price, "
			+"  IFNULL( "
			+" 	coupon.product_coupon_quantity, "
			+" 	0 "
			+" ) product_coupon_quantity "
			+" FROM "
			+" 	td_order o "
			+" INNER JOIN td_order_inf oi ON o.order_number = oi.order_number "
			+" LEFT JOIN td_order_goods_inf ogi ON oi.header_id = ogi.order_header_id "
			+" LEFT JOIN ( "
			+" 	SELECT "
			+" 		coupon_info.sku sku, "
			+" 		coupon_info.cash_coupon_price cash_coupon_price, "
			+" 		sum( "
			+" 			coupon_info.cash_coupon_quantity "
			+" 		) cash_coupon_quantity, "
			+" 		coupon_info.product_coupon_price product_coupon_price, "
			+" 		sum( "
			+" 			coupon_info.product_coupon_quantity "
			+" 		) product_coupon_quantity, "
			+" 		coupon_info.order_header_id order_header_id "
			+" 	FROM "
			+" 		( "
			+" 			SELECT "
			+" 				sku, "
			+" 				CASE coupon_type_id "
			+" 			WHEN 2 THEN "
			+" 				price "
			+" 			ELSE "
			+" 				0 "
			+" 			END cash_coupon_price, "
			+" 			CASE coupon_type_id "
			+" 		WHEN 2 THEN "
			+" 			quantity "
			+" 		ELSE "
			+" 			0 "
			+" 		END cash_coupon_quantity, "
			+" 		CASE coupon_type_id "
			+" 	WHEN 1 THEN "
			+" 		price "
			+" 	WHEN 4 THEN "
			+" 		price "
			+" 	ELSE "
			+" 		0 "
			+" 	END product_coupon_price, "
			+" 	CASE coupon_type_id "
			+" WHEN 1 THEN "
			+" 	quantity "
			+" WHEN 4 THEN "
			+" 	quantity "
			+" ELSE "
			+" 	0 "
			+" END product_coupon_quantity, "
			+"  order_header_id "
			+" FROM "
			+" 	td_order_coupon_inf "
			+" 		) coupon_info "
			+" 	GROUP BY "
			+" 		coupon_info.order_header_id, "
			+" 		coupon_info.sku "
			+" ) coupon ON ( "
			+" 	ogi.sku = coupon.sku "
			+" 	AND ogi.order_header_id = coupon.order_header_id "
			+" 	AND ogi.gift_flag = 'N' "
			+" ) "
			+" LEFT JOIN td_diy_site diy ON o.diy_site_id = diy.id "
			+" WHERE "
			+" 	diy.city LIKE ?3 "
			+" AND o.pay_time >= ?1 "
			+" AND o.pay_time <= ?2 "
			+" AND o.diy_site_code LIKE ?4 "
			+" AND o.diy_site_id IN ?5 "
			+" AND o.status_id NOT IN (1, 2, 8) "
			+" AND ( "
			+" 	( "
			+" 		o.total_price = 0 "
			+" 		AND IFNULL(o.cash_balance_used,0)+IFNULL(o.un_cash_balance_used,0) > 0 "
			+" 	) "
			+" 	OR ( "
			+" 		o.total_price > 0 "
			+" 		AND o.total_price = o.other_pay "
			+" 	) "
			+" ) "
			+" UNION ALL "
			+" 	SELECT "
			+" 		uuid() id, "
			+" 		diy.city city_name, "
			+" 		o.diy_site_name diy_site_name, "
			+" 		'配送单出货' AS stype, "
			+" 		o.main_order_number main_order_number, "
			+" 		o.order_number order_number, "
			+" 		o.order_time order_time, "
			+" 		o.username username, "
			+" 		o.real_user_real_name real_name, "
			+" 		o.seller_username seller_username, "
			+" 		o.seller_real_name seller_real_name, "
			+" 		ogi.sku sku, "
			+" 		ogi.goods_title goods_title, "
			+" 		ogi.gift_flag is_gift, "
			+" 		CASE ogi.gift_flag "
			+" 	WHEN 'N' THEN "
			+" 		ogi.ls_price "
			+" 	WHEN 'Y' THEN "
			+" 		0 "
			+" 	END goods_price, "
			+" 	ogi.quantity goods_quantity, "
			+" 	IFNULL(coupon.cash_coupon_price, 0) cash_coupon_price, "
			+" 	IFNULL( "
			+" 		coupon.cash_coupon_quantity, "
			+" 		0 "
			+" 	) cash_coupon_quantity, "
			+" 	IFNULL( "
			+" 		coupon.product_coupon_price, "
			+" 		0 "
			+" 	) product_coupon_price, "
			+" 	IFNULL( "
			+" 		coupon.product_coupon_quantity, "
			+" 		0 "
			+" 	) product_coupon_quantity "
			+" FROM "
			+" 	td_order o "
			+" INNER JOIN td_order_inf oi ON o.order_number = oi.order_number "
			+" LEFT JOIN td_order_goods_inf ogi ON oi.header_id = ogi.order_header_id "
			+" LEFT JOIN ( "
			+" 	SELECT "
			+" 		coupon_info.sku sku, "
			+" 		coupon_info.cash_coupon_price cash_coupon_price, "
			+" 		sum( "
			+" 			coupon_info.cash_coupon_quantity "
			+" 		) cash_coupon_quantity, "
			+" 		coupon_info.product_coupon_price product_coupon_price, "
			+" 		sum( "
			+" 			coupon_info.product_coupon_quantity "
			+" 		) product_coupon_quantity, "
			+" 		coupon_info.order_header_id order_header_id "
			+" 	FROM "
			+" 		( "
			+" 			SELECT "
			+" 				sku, "
			+" 				CASE coupon_type_id "
			+" 			WHEN 2 THEN "
			+" 				price "
			+" 			ELSE "
			+" 				0 "
			+" 			END cash_coupon_price, "
			+" 			CASE coupon_type_id "
			+" 		WHEN 2 THEN "
			+" 			quantity "
			+" 		ELSE "
			+" 			0 "
			+" 		END cash_coupon_quantity, "
			+" 		CASE coupon_type_id "
			+" 	WHEN 1 THEN "
			+" 		price "
			+" 	WHEN 4 THEN "
			+" 		price "
			+" 	ELSE "
			+" 		0 "
			+" 	END product_coupon_price, "
			+" 	CASE coupon_type_id "
			+" WHEN 1 THEN "
			+" 	quantity "
			+" WHEN 4 THEN "
			+" 	quantity "
			+" ELSE "
			+" 	0 "
			+" END product_coupon_quantity, "
			+"  order_header_id "
			+" FROM "
			+" 	td_order_coupon_inf "
			+" 		) coupon_info "
			+" 	GROUP BY "
			+" 		coupon_info.order_header_id, "
			+" 		coupon_info.sku "
			+" ) coupon ON ( "
			+" 	ogi.sku = coupon.sku "
			+" 	AND ogi.order_header_id = coupon.order_header_id "
			+" 	AND ogi.gift_flag = 'N' "
			+" ) "
			+" LEFT JOIN td_diy_site diy ON  o.diy_site_id = diy.id "
			+" WHERE "
			+" 	diy.city LIKE ?3 "
			+" AND o.send_time >= ?1 "
			+" AND o.send_time <= ?2 "
			+" AND o.diy_site_code LIKE ?4 "
			+" AND o.diy_site_id IN ?5 "
			+" AND o.deliver_type_title = '送货上门' "
			+" AND o.status_id NOT IN (1, 2, 7, 8) "
			+" AND o.total_price > o.other_pay  "
			+" UNION ALL "
			+" 	SELECT "
			+" 		uuid() id, "
			+" 		diy.city city_name, "
			+" 		o.diy_site_name diy_site_name, "
			+" 		'自提单收款' AS stype, "
			+" 		o.main_order_number main_order_number, "
			+" 		o.order_number order_number, "
			+" 		o.order_time order_time, "
			+" 		o.username username, "
			+" 		o.real_user_real_name real_name, "
			+" 		o.seller_username seller_username, "
			+" 		o.seller_real_name seller_real_name, "
			+" 		ogi.sku sku, "
			+" 		ogi.goods_title goods_title, "
			+" 		ogi.gift_flag is_gift, "
			+" 		CASE ogi.gift_flag "
			+" 	WHEN 'N' THEN "
			+" 		ogi.ls_price "
			+" 	WHEN 'Y' THEN "
			+" 		0 "
			+" 	END goods_price, "
			+" 	ogi.quantity goods_quantity, "
			+" 	IFNULL(coupon.cash_coupon_price,0) cash_coupon_price, "
			+" 	IFNULL(coupon.cash_coupon_quantity,0) cash_coupon_quantity, "
			+" 	IFNULL(coupon.product_coupon_price,0) product_coupon_price, "
			+" 	IFNULL(coupon.product_coupon_quantity,0) product_coupon_quantity "
			+" FROM "
			+" 	td_order o "
			+" INNER JOIN td_order_inf oi ON o.order_number = oi.order_number "
			+" LEFT JOIN td_order_goods_inf ogi ON oi.header_id = ogi.order_header_id "
			+" LEFT JOIN ( "
			+" 	SELECT "
			+" 		coupon_info.sku sku, "
			+" 		coupon_info.cash_coupon_price cash_coupon_price, "
			+" 		sum( "
			+" 			coupon_info.cash_coupon_quantity "
			+" 		) cash_coupon_quantity, "
			+" 		coupon_info.product_coupon_price product_coupon_price, "
			+" 		sum( "
			+" 			coupon_info.product_coupon_quantity "
			+" 		) product_coupon_quantity, "
			+" 		coupon_info.order_header_id order_header_id "
			+" 	FROM "
			+" 		( "
			+" 			SELECT "
			+" 				sku, "
			+" 				CASE coupon_type_id "
			+" 			WHEN 2 THEN "
			+" 				price "
			+" 			ELSE "
			+" 				0 "
			+" 			END cash_coupon_price, "
			+" 			CASE coupon_type_id "
			+" 		WHEN 2 THEN "
			+" 			quantity "
			+" 		ELSE "
			+" 			0 "
			+" 		END cash_coupon_quantity, "
			+" 		CASE coupon_type_id "
			+" 	WHEN 1 THEN "
			+" 		price "
			+" 	WHEN 4 THEN "
			+" 		price "
			+" 	ELSE "
			+" 		0 "
			+" 	END product_coupon_price, "
			+" 	CASE coupon_type_id "
			+" WHEN 1 THEN "
			+" 	quantity "
			+" WHEN 4 THEN "
			+" 	quantity "
			+" ELSE "
			+" 	0 "
			+" END product_coupon_quantity, "
			+"  order_header_id "
			+" FROM "
			+" 	td_order_coupon_inf "
			+" 		) coupon_info "
			+" 	GROUP BY "
			+" 		coupon_info.order_header_id, "
			+" 		coupon_info.sku "
			+" ) coupon ON ( "
			+" 	ogi.sku = coupon.sku "
			+" 	AND ogi.order_header_id = coupon.order_header_id "
			+" 	AND ogi.gift_flag = 'N' "
			+" ) "
			+" LEFT JOIN td_diy_site diy ON o.diy_site_id = diy.id "
			+" LEFT JOIN td_own_money_record owd ON o.main_order_number = owd.order_number "
			+" WHERE "
			+" 	diy.city LIKE ?3 "
			+" AND o.deliver_type_title = '门店自提' "
			+" AND owd.pay_time >= ?1 "
			+" AND owd.pay_time <= ?2 "
			+" AND o.diy_site_code LIKE ?4 "
			+" AND o.diy_site_id IN ?5 "
			+" AND o.status_id NOT IN (1, 2, 7, 8) "
			+" AND ( "
			+" 	o.cash_pay + o.pos_pay + o.back_other_pay "
			+" ) > 0 "
			+" UNION ALL "
			+" 	SELECT "
			+" 		uuid() id, "
			+" 		diy.city city_name, "
			+" 		o.diy_site_name diy_site_name, "
			+" 		'取消订单' AS stype, "
			+" 		o.main_order_number main_order_number, "
			+" 		o.order_number order_number, "
			+" 		o.order_time order_time, "
			+" 		o.username username, "
			+" 		o.real_user_real_name real_name, "
			+" 		o.seller_username seller_username, "
			+" 		o.seller_real_name seller_real_name, "
			+" 		ogi.sku sku, "
			+" 		ogi.goods_title goods_title, "
			+" 		ogi.gift_flag is_gift, "
			+" 		CASE ogi.gift_flag "
			+" 	WHEN 'N' THEN "
			+" 		ogi.ls_price "
			+" 	WHEN 'Y' THEN "
			+" 		0 "
			+" 	END goods_price, "
			+" 	- ogi.quantity goods_quantity, "
			+" 	IFNULL(coupon.cash_coupon_price,0) cash_coupon_price, "
			+" 	IFNULL(coupon.cash_coupon_quantity,0) cash_coupon_quantity, "
			+" 	IFNULL(coupon.product_coupon_price,0) product_coupon_price, "
			+" 	IFNULL(coupon.product_coupon_quantity,0) product_coupon_quantity "
			+" FROM "
			+" 	td_order o "
			+" INNER JOIN td_order_inf oi ON o.order_number = oi.order_number "
			+" LEFT JOIN td_order_goods_inf ogi ON oi.header_id = ogi.order_header_id "
			+" LEFT JOIN ( "
			+" 	SELECT "
			+" 		coupon_info.sku sku, "
			+" 		coupon_info.cash_coupon_price cash_coupon_price, "
			+" 		sum( "
			+" 			coupon_info.cash_coupon_quantity "
			+" 		) cash_coupon_quantity, "
			+" 		coupon_info.product_coupon_price product_coupon_price, "
			+" 		sum( "
			+" 			coupon_info.product_coupon_quantity "
			+" 		) product_coupon_quantity, "
			+" 		coupon_info.order_header_id order_header_id "
			+" 	FROM "
			+" 		( "
			+" 			SELECT "
			+" 				sku, "
			+" 				CASE coupon_type_id "
			+" 			WHEN 2 THEN "
			+" 				price "
			+" 			ELSE "
			+" 				0 "
			+" 			END cash_coupon_price, "
			+" 			CASE coupon_type_id "
			+" 		WHEN 2 THEN "
			+" 			quantity "
			+" 		ELSE "
			+" 			0 "
			+" 		END cash_coupon_quantity, "
			+" 		CASE coupon_type_id "
			+" 	WHEN 1 THEN "
			+" 		price "
			+" 	WHEN 4 THEN "
			+" 		price "
			+" 	ELSE "
			+" 		0 "
			+" 	END product_coupon_price, "
			+" 	CASE coupon_type_id "
			+" WHEN 1 THEN "
			+" 	quantity "
			+" WHEN 4 THEN "
			+" 	quantity "
			+" ELSE "
			+" 	0 "
			+" END product_coupon_quantity, "
			+"  order_header_id "
			+" FROM "
			+" 	td_order_coupon_inf "
			+" 		) coupon_info "
			+" 	GROUP BY "
			+" 		coupon_info.order_header_id, "
			+" 		coupon_info.sku "
			+" ) coupon ON ( "
			+" 	ogi.sku = coupon.sku "
			+" 	AND ogi.order_header_id = coupon.order_header_id "
			+" 	AND ogi.gift_flag = 'N' "
			+" ) "
			+" LEFT JOIN td_diy_site diy ON o.diy_site_id = diy.id "
			+" WHERE "
			+" 	diy.city LIKE ?3 "
			+" AND o.status_id = 7 "
			+" AND o.cancel_time >= ?1 "
			+" AND o.cancel_time <= ?2 "
			+" AND o.diy_site_code LIKE ?4 "
			+" AND o.diy_site_id IN ?5 "
			+" AND o.pay_time IS NOT NULL "
			+" AND ( "
			+" 	( "
			+" 		o.total_price = 0 "
			+" 		AND o.all_actual_pay > 0 "
			+" 	) "
			+" 	OR ( "
			+" 		o.total_price > 0 "
			+" 		AND o.total_price = o.other_pay "
			+" 	) "
			+" ) "
			+" UNION ALL "
			+" 	SELECT "
			+" 		uuid() id, "
			+" 		diy.city city_name, "
			+" 		o.diy_site_name diy_site_name, "
			+" 		'电子券' AS stype, "
			+" 		o.main_order_number main_order_number, "
			+" 		o.order_number order_number, "
			+" 		o.order_time order_time, "
			+" 		o.username username, "
			+" 		o.real_user_real_name real_name, "
			+" 		o.seller_username seller_username, "
			+" 		o.seller_real_name seller_real_name, "
			+" 		og.sku sku, "
			+" 		og.goods_title goods_title, "
			+" 		CASE "
			+" 	WHEN og.presented_list_id IS NULL THEN "
			+" 		'N' "
			+" 	ELSE "
			+" 		'Y' "
			+" 	END is_gift, "
			+" 	CASE "
			+" WHEN og.presented_list_id IS NULL THEN "
			+" 	og.price "
			+" ELSE "
			+" 	0 "
			+" END AS goods_price, "
			+"  og.quantity goods_quantity, "
			+"  CASE "
			+" WHEN og.presented_list_id IS NULL "
			+" AND og.cash_number > 0 THEN "
			+" 	og.coupon_money / og.cash_number "
			+" ELSE "
			+" 	0 "
			+" END AS cash_coupon_price, "
			+"  CASE "
			+" WHEN og.presented_list_id IS NULL THEN "
			+" 	og.cash_number "
			+" ELSE "
			+" 	0 "
			+" END AS cash_coupon_quantity, "
			+"  0 product_coupon_price, "
			+"  0 product_coupon_quantity "
			+" FROM "
			+" 	td_order o "
			+" LEFT JOIN td_order_goods og ON o.id = og.td_order_id "
			+" OR o.id = og.presented_list_id "
			+" LEFT JOIN td_diy_site diy ON o.diy_site_id = diy.id "
			+" WHERE "
			+" 	o.is_coupon = 1 "
			+" AND diy.city LIKE ?3 "
			+" AND o.order_time >= ?1 "
			+" AND o.order_time <= ?2 "
			+" AND o.status_id >= 4 "
			+" AND o.diy_site_code LIKE ?4 "
			+" AND o.diy_site_id IN ?5 "
			+" UNION ALL "
			+" 	SELECT "
			+" 		uuid() id, "
			+" 		diy.city city_name, "
			+" 		o.diy_site_name diy_site_name, "
			+" 		'退券' AS stype, "
			+" 		rn.return_number main_order_number, "
			+" 		o.order_number order_number, "
			+" 		o.order_time order_time, "
			+" 		o.username username, "
			+" 		o.real_user_real_name real_name, "
			+" 		o.seller_username seller_username, "
			+" 		o.seller_real_name seller_real_name, "
			+" 		og.sku sku, "
			+" 		og.goods_title goods_title, "
			+" 		'N' AS is_gift, "
			+" 		og.return_unit_price goods_price, "
			+" 		- og.quantity goods_quantity, "
			+" 		0 cash_coupon_price, "
			+" 		0 cash_coupon_quantity, "
			+" 		0 product_coupon_price, "
			+" 		0 product_coupon_quantity "
			+" 	FROM "
			+" 		td_return_note rn "
			+" 	LEFT JOIN td_order_goods og ON rn.id = og.td_return_id "
			+" 	LEFT JOIN td_order o ON o.order_number = rn.order_number "
			+" 	LEFT JOIN td_diy_site diy ON o.diy_site_id = diy.id "
			+" 	WHERE "
			+" 		rn.is_coupon = 1 "
			+" 	AND diy.city LIKE ?3 "
			+" 	AND rn.order_time >=?1 "
			+" 	AND rn.order_time <=?2 "
			+" 	AND o.diy_site_code LIKE ?4 "
			+" 	AND o.diy_site_id IN ?5 "
			+" 	AND rn.status_id >= 4 "
			+" 	UNION ALL "
			+" 		SELECT "
			+" 			uuid() id, "
			+" 			diy.city city_name, "
			+" 			o.diy_site_name diy_site_name, "
			+" 			'退货' AS stype, "
			+" 			rn.return_number, "
			+" 			o.order_number order_number, "
			+" 			o.order_time order_time, "
			+" 			o.username username, "
			+" 			o.real_user_real_name real_name, "
			+" 			o.seller_username seller_username, "
			+" 			o.seller_real_name seller_real_name, "
			+" 			og.sku sku, "
			+" 			og.goods_title, "
			+" 			'N' AS is_gift, "
			+" 			og.return_unit_price goods_price, "
			+" 			- og.quantity goods_quantity, "
			+" 			0 cash_coupon_price, "
			+" 			0 cash_coupon_quantity, "
			+" 			IFNULL(coupon.product_coupon_price,0) product_coupon_price, "
			+" 			IFNULL(- coupon.product_coupon_quantity,0) product_coupon_quantity "
			+" 		FROM "
			+" 			td_return_note rn "
			+" 		LEFT JOIN td_order_goods og ON rn.id = og.td_return_id "
			+" 		LEFT JOIN td_return_order_inf ri ON rn.order_number = ri.order_number "
			+" 		LEFT JOIN ( "
			+" 			SELECT "
			+" 				coupon_info.sku sku, "
			+" 				coupon_info.cash_coupon_price cash_coupon_price, "
			+" 				sum( "
			+" 					coupon_info.cash_coupon_quantity "
			+" 				) cash_coupon_quantity, "
			+" 				coupon_info.product_coupon_price product_coupon_price, "
			+" 				sum( "
			+" 					coupon_info.product_coupon_quantity "
			+" 				) product_coupon_quantity, "
			+" 				coupon_info.rt_header_id rt_header_id "
			+" 			FROM "
			+" 				( "
			+" 					SELECT "
			+" 						sku, "
			+" 						CASE coupon_type_id "
			+" 					WHEN 2 THEN "
			+" 						price "
			+" 					ELSE "
			+" 						0 "
			+" 					END cash_coupon_price, "
			+" 					CASE coupon_type_id "
			+" 				WHEN 2 THEN "
			+" 					quantity "
			+" 				ELSE "
			+" 					0 "
			+" 				END cash_coupon_quantity, "
			+" 				CASE coupon_type_id "
			+" 			WHEN 1 THEN "
			+" 				price "
			+" 			WHEN 4 THEN "
			+" 				price "
			+" 			ELSE "
			+" 				0 "
			+" 			END product_coupon_price, "
			+" 			CASE coupon_type_id "
			+" 		WHEN 1 THEN "
			+" 			quantity "
			+" 		WHEN 4 THEN "
			+" 			quantity "
			+" 		ELSE "
			+" 			0 "
			+" 		END product_coupon_quantity, "
			+" 		rt_header_id "
			+" 	FROM "
			+" 		td_return_coupon_inf "
			+" 				) coupon_info "
			+" 			GROUP BY "
			+" 				coupon_info.rt_header_id, "
			+" 				coupon_info.sku "
			+" 		) coupon ON og.sku = coupon.sku "
			+" 		AND ri.rt_header_id = coupon.rt_header_id "
			+" 		LEFT JOIN td_order o ON rn.order_number = o.order_number "
			+" 		LEFT JOIN td_diy_site diy ON o.diy_site_id = diy.id "
			+" 		WHERE "
			+" 			diy.city LIKE ?3 "
			+" 		AND rn.receive_time >= ?1 "
			+" 		AND rn.receive_time <= ?2 "
			+" 		AND o.diy_site_code LIKE ?4 "
			+" 		AND o.diy_site_id IN ?5 "
			+" 		AND rn.status_id >= 4 "
			+" 		ORDER BY "
			+" 			order_time, "
			+" 			main_order_number, "
			+" 			order_number DESC; ",nativeQuery=true)
	List<TdSales> queryDownList(Date begin, Date end, String cityName,
			String diySiteCode, List<String> roleDiyIds);
	
	
	@Query(value=" SELECT "
			+" 	uuid() id, "
			+" 	diy.city city_name, "
			+" 	o.diy_site_name diy_site_name, "
			+" 	'全款订单' AS stype, "
			+" 	o.main_order_number main_order_number, "
			+" 	o.order_number order_number, "
			+" 	o.order_time order_time, "
			+" 	o.username username, "
			+" 	o.real_user_real_name real_name, "
			+" 	o.seller_username seller_username, "
			+" 	o.seller_real_name seller_real_name, "
			+" 	ogi.sku sku, "
			+" 	ogi.goods_title goods_title, "
			+" 	ogi.gift_flag is_gift, "
			+" 	CASE ogi.gift_flag "
			+" WHEN 'N' THEN "
			+" 	ogi.ls_price "
			+" WHEN 'Y' THEN "
			+" 	0 "
			+" END goods_price, "
			+"  ogi.quantity goods_quantity, "
			+"  IFNULL(coupon.cash_coupon_price, 0) cash_coupon_price, "
			+"  IFNULL( "
			+" 	coupon.cash_coupon_quantity, "
			+" 	0 "
			+" ) cash_coupon_quantity, "
			+"  IFNULL( "
			+" 	coupon.product_coupon_price, "
			+" 	0 "
			+" ) product_coupon_price, "
			+"  IFNULL( "
			+" 	coupon.product_coupon_quantity, "
			+" 	0 "
			+" ) product_coupon_quantity "
			+" FROM "
			+" 	td_order o "
			+" INNER JOIN td_order_inf oi ON o.order_number = oi.order_number "
			+" LEFT JOIN td_order_goods_inf ogi ON oi.header_id = ogi.order_header_id "
			+" LEFT JOIN ( "
			+" 	SELECT "
			+" 		coupon_info.sku sku, "
			+" 		coupon_info.cash_coupon_price cash_coupon_price, "
			+" 		sum( "
			+" 			coupon_info.cash_coupon_quantity "
			+" 		) cash_coupon_quantity, "
			+" 		coupon_info.product_coupon_price product_coupon_price, "
			+" 		sum( "
			+" 			coupon_info.product_coupon_quantity "
			+" 		) product_coupon_quantity, "
			+" 		coupon_info.order_header_id order_header_id "
			+" 	FROM "
			+" 		( "
			+" 			SELECT "
			+" 				sku, "
			+" 				CASE coupon_type_id "
			+" 			WHEN 2 THEN "
			+" 				price "
			+" 			ELSE "
			+" 				0 "
			+" 			END cash_coupon_price, "
			+" 			CASE coupon_type_id "
			+" 		WHEN 2 THEN "
			+" 			quantity "
			+" 		ELSE "
			+" 			0 "
			+" 		END cash_coupon_quantity, "
			+" 		CASE coupon_type_id "
			+" 	WHEN 1 THEN "
			+" 		price "
			+" 	WHEN 4 THEN "
			+" 		price "
			+" 	ELSE "
			+" 		0 "
			+" 	END product_coupon_price, "
			+" 	CASE coupon_type_id "
			+" WHEN 1 THEN "
			+" 	quantity "
			+" WHEN 4 THEN "
			+" 	quantity "
			+" ELSE "
			+" 	0 "
			+" END product_coupon_quantity, "
			+"  order_header_id "
			+" FROM "
			+" 	td_order_coupon_inf "
			+" 		) coupon_info "
			+" 	GROUP BY "
			+" 		coupon_info.order_header_id, "
			+" 		coupon_info.sku "
			+" ) coupon ON ( "
			+" 	ogi.sku = coupon.sku "
			+" 	AND ogi.order_header_id = coupon.order_header_id "
			+" 	AND ogi.gift_flag = 'N' "
			+" ) "
			+" LEFT JOIN td_diy_site diy ON o.diy_site_id = diy.id "
			+" WHERE "
			+" 	o.pay_time >= ?1 "
			+" AND o.pay_time <= ?2 "
			+" AND o.status_id NOT IN (1, 2, 8) "
			+" AND ( "
			+" 	( "
			+" 		o.total_price = 0 "
			+" 		AND o.all_actual_pay > 0 "
			+" 	) "
			+" 	OR ( "
			+" 		o.total_price > 0 "
			+" 		AND o.total_price = o.other_pay "
			+" 	) "
			+" ) "
			+" UNION ALL "
			+" 	SELECT "
			+" 		uuid() id, "
			+" 		diy.city city_name, "
			+" 		o.diy_site_name diy_site_name, "
			+" 		'配送单出货' AS stype, "
			+" 		o.main_order_number main_order_number, "
			+" 		o.order_number order_number, "
			+" 		o.order_time order_time, "
			+" 		o.username username, "
			+" 		o.real_user_real_name real_name, "
			+" 		o.seller_username seller_username, "
			+" 		o.seller_real_name seller_real_name, "
			+" 		ogi.sku sku, "
			+" 		ogi.goods_title goods_title, "
			+" 		ogi.gift_flag is_gift, "
			+" 		CASE ogi.gift_flag "
			+" 	WHEN 'N' THEN "
			+" 		ogi.ls_price "
			+" 	WHEN 'Y' THEN "
			+" 		0 "
			+" 	END goods_price, "
			+" 	ogi.quantity goods_quantity, "
			+" 	coupon.cash_coupon_price cash_coupon_price, "
			+" 	coupon.cash_coupon_quantity cash_coupon_quantity, "
			+" 	coupon.product_coupon_price product_coupon_price, "
			+" 	coupon.product_coupon_quantity product_coupon_quantity "
			+" FROM "
			+" 	td_order o "
			+" INNER JOIN td_order_inf oi ON o.order_number = oi.order_number "
			+" LEFT JOIN td_order_goods_inf ogi ON oi.header_id = ogi.order_header_id "
			+" LEFT JOIN ( "
			+" 	SELECT "
			+" 		coupon_info.sku sku, "
			+" 		coupon_info.cash_coupon_price cash_coupon_price, "
			+" 		sum( "
			+" 			coupon_info.cash_coupon_quantity "
			+" 		) cash_coupon_quantity, "
			+" 		coupon_info.product_coupon_price product_coupon_price, "
			+" 		sum( "
			+" 			coupon_info.product_coupon_quantity "
			+" 		) product_coupon_quantity, "
			+" 		coupon_info.order_header_id order_header_id "
			+" 	FROM "
			+" 		( "
			+" 			SELECT "
			+" 				sku, "
			+" 				CASE coupon_type_id "
			+" 			WHEN 2 THEN "
			+" 				price "
			+" 			ELSE "
			+" 				0 "
			+" 			END cash_coupon_price, "
			+" 			CASE coupon_type_id "
			+" 		WHEN 2 THEN "
			+" 			quantity "
			+" 		ELSE "
			+" 			0 "
			+" 		END cash_coupon_quantity, "
			+" 		CASE coupon_type_id "
			+" 	WHEN 1 THEN "
			+" 		price "
			+" 	WHEN 4 THEN "
			+" 		price "
			+" 	ELSE "
			+" 		0 "
			+" 	END product_coupon_price, "
			+" 	CASE coupon_type_id "
			+" WHEN 1 THEN "
			+" 	quantity "
			+" WHEN 4 THEN "
			+" 	quantity "
			+" ELSE "
			+" 	0 "
			+" END product_coupon_quantity, "
			+"  order_header_id "
			+" FROM "
			+" 	td_order_coupon_inf "
			+" 		) coupon_info "
			+" 	GROUP BY "
			+" 		coupon_info.order_header_id, "
			+" 		coupon_info.sku "
			+" ) coupon ON ( "
			+" 	ogi.sku = coupon.sku "
			+" 	AND ogi.order_header_id = coupon.order_header_id "
			+" 	AND ogi.gift_flag = 'N' "
			+" ) "
			+" LEFT JOIN td_diy_site diy ON o.diy_site_id = diy.id "
			+" WHERE "
			+" 	o.send_time >= ?1 "
			+" AND o.send_time <= ?2 "
			+" AND o.deliver_type_title = '送货上门' "
			+" AND o.status_id NOT IN (1, 2, 7, 8) "
			+" AND o.total_price > o.other_pay  "
			+" UNION ALL "
			+" 	SELECT "
			+" 		uuid() id, "
			+" 		diy.city city_name, "
			+" 		o.diy_site_name diy_site_name, "
			+" 		'自提单收款' AS stype, "
			+" 		o.main_order_number main_order_number, "
			+" 		o.order_number order_number, "
			+" 		o.order_time order_time, "
			+" 		o.username username, "
			+" 		o.real_user_real_name real_name, "
			+" 		o.seller_username seller_username, "
			+" 		o.seller_real_name seller_real_name, "
			+" 		ogi.sku sku, "
			+" 		ogi.goods_title goods_title, "
			+" 		ogi.gift_flag is_gift, "
			+" 		CASE ogi.gift_flag "
			+" 	WHEN 'N' THEN "
			+" 		ogi.ls_price "
			+" 	WHEN 'Y' THEN "
			+" 		0 "
			+" 	END goods_price, "
			+" 	ogi.quantity goods_quantity, "
			+" 	IFNULL(coupon.cash_coupon_price, 0) cash_coupon_price, "
			+" 	IFNULL( "
			+" 		coupon.cash_coupon_quantity, "
			+" 		0 "
			+" 	) cash_coupon_quantity, "
			+" 	IFNULL( "
			+" 		coupon.product_coupon_price, "
			+" 		0 "
			+" 	) product_coupon_price, "
			+" 	IFNULL( "
			+" 		coupon.product_coupon_quantity, "
			+" 		0 "
			+" 	) product_coupon_quantity "
			+" FROM "
			+" 	td_order o "
			+" INNER JOIN td_order_inf oi ON o.order_number = oi.order_number "
			+" LEFT JOIN td_order_goods_inf ogi ON oi.header_id = ogi.order_header_id "
			+" LEFT JOIN ( "
			+" 	SELECT "
			+" 		coupon_info.sku sku, "
			+" 		coupon_info.cash_coupon_price cash_coupon_price, "
			+" 		sum( "
			+" 			coupon_info.cash_coupon_quantity "
			+" 		) cash_coupon_quantity, "
			+" 		coupon_info.product_coupon_price product_coupon_price, "
			+" 		sum( "
			+" 			coupon_info.product_coupon_quantity "
			+" 		) product_coupon_quantity, "
			+" 		coupon_info.order_header_id order_header_id "
			+" 	FROM "
			+" 		( "
			+" 			SELECT "
			+" 				sku, "
			+" 				CASE coupon_type_id "
			+" 			WHEN 2 THEN "
			+" 				price "
			+" 			ELSE "
			+" 				0 "
			+" 			END cash_coupon_price, "
			+" 			CASE coupon_type_id "
			+" 		WHEN 2 THEN "
			+" 			quantity "
			+" 		ELSE "
			+" 			0 "
			+" 		END cash_coupon_quantity, "
			+" 		CASE coupon_type_id "
			+" 	WHEN 1 THEN "
			+" 		price "
			+" 	WHEN 4 THEN "
			+" 		price "
			+" 	ELSE "
			+" 		0 "
			+" 	END product_coupon_price, "
			+" 	CASE coupon_type_id "
			+" WHEN 1 THEN "
			+" 	quantity "
			+" WHEN 4 THEN "
			+" 	quantity "
			+" ELSE "
			+" 	0 "
			+" END product_coupon_quantity, "
			+"  order_header_id "
			+" FROM "
			+" 	td_order_coupon_inf "
			+" 		) coupon_info "
			+" 	GROUP BY "
			+" 		coupon_info.order_header_id, "
			+" 		coupon_info.sku "
			+" ) coupon ON ( "
			+" 	ogi.sku = coupon.sku "
			+" 	AND ogi.order_header_id = coupon.order_header_id "
			+" 	AND ogi.gift_flag = 'N' "
			+" ) "
			+" LEFT JOIN td_diy_site diy ON o.diy_site_id = diy.id "
			+" LEFT JOIN td_own_money_record owd ON o.main_order_number = owd.order_number "
			+" WHERE "
			+" 	o.deliver_type_title = '门店自提' "
			+" AND owd.pay_time >= ?1 "
			+" AND owd.pay_time <= ?2 "
			+" AND o.status_id NOT IN (1, 2, 7, 8) "
			+" AND ( "
			+" 	o.cash_pay + o.pos_pay + o.back_other_pay "
			+" ) > 0 "
			+" UNION ALL "
			+" 	SELECT "
			+" 		uuid() id, "
			+" 		diy.city city_name, "
			+" 		o.diy_site_name diy_site_name, "
			+" 		'取消订单' AS stype, "
			+" 		o.main_order_number main_order_number, "
			+" 		o.order_number order_number, "
			+" 		o.order_time order_time, "
			+" 		o.username username, "
			+" 		o.real_user_real_name real_name, "
			+" 		o.seller_username seller_username, "
			+" 		o.seller_real_name seller_real_name, "
			+" 		ogi.sku sku, "
			+" 		ogi.goods_title goods_title, "
			+" 		ogi.gift_flag is_gift, "
			+" 		CASE ogi.gift_flag "
			+" 	WHEN 'N' THEN "
			+" 		ogi.ls_price "
			+" 	WHEN 'Y' THEN "
			+" 		0 "
			+" 	END goods_price, "
			+" 	- ogi.quantity goods_quantity, "
			+" 	IFNULL(coupon.cash_coupon_price, 0) cash_coupon_price, "
			+" 	IFNULL( "
			+" 		coupon.cash_coupon_quantity, "
			+" 		0 "
			+" 	) cash_coupon_quantity, "
			+" 	IFNULL( "
			+" 		coupon.product_coupon_price, "
			+" 		0 "
			+" 	) product_coupon_price, "
			+" 	IFNULL( "
			+" 		coupon.product_coupon_quantity, "
			+" 		0 "
			+" 	) product_coupon_quantity "
			+" FROM "
			+" 	td_order o "
			+" INNER JOIN td_order_inf oi ON o.order_number = oi.order_number "
			+" LEFT JOIN td_order_goods_inf ogi ON oi.header_id = ogi.order_header_id "
			+" LEFT JOIN ( "
			+" 	SELECT "
			+" 		coupon_info.sku sku, "
			+" 		coupon_info.cash_coupon_price cash_coupon_price, "
			+" 		sum( "
			+" 			coupon_info.cash_coupon_quantity "
			+" 		) cash_coupon_quantity, "
			+" 		coupon_info.product_coupon_price product_coupon_price, "
			+" 		sum( "
			+" 			coupon_info.product_coupon_quantity "
			+" 		) product_coupon_quantity, "
			+" 		coupon_info.order_header_id order_header_id "
			+" 	FROM "
			+" 		( "
			+" 			SELECT "
			+" 				sku, "
			+" 				CASE coupon_type_id "
			+" 			WHEN 2 THEN "
			+" 				price "
			+" 			ELSE "
			+" 				0 "
			+" 			END cash_coupon_price, "
			+" 			CASE coupon_type_id "
			+" 		WHEN 2 THEN "
			+" 			quantity "
			+" 		ELSE "
			+" 			0 "
			+" 		END cash_coupon_quantity, "
			+" 		CASE coupon_type_id "
			+" 	WHEN 1 THEN "
			+" 		price "
			+" 	WHEN 4 THEN "
			+" 		price "
			+" 	ELSE "
			+" 		0 "
			+" 	END product_coupon_price, "
			+" 	CASE coupon_type_id "
			+" WHEN 1 THEN "
			+" 	quantity "
			+" WHEN 4 THEN "
			+" 	quantity "
			+" ELSE "
			+" 	0 "
			+" END product_coupon_quantity, "
			+"  order_header_id "
			+" FROM "
			+" 	td_order_coupon_inf "
			+" 		) coupon_info "
			+" 	GROUP BY "
			+" 		coupon_info.order_header_id, "
			+" 		coupon_info.sku "
			+" ) coupon ON ( "
			+" 	ogi.sku = coupon.sku "
			+" 	AND ogi.order_header_id = coupon.order_header_id "
			+" 	AND ogi.gift_flag = 'N' "
			+" ) "
			+" LEFT JOIN td_diy_site diy ON o.diy_site_id = diy.id "
			+" WHERE "
			+" 	o.status_id = 7 "
			+" AND o.cancel_time >= ?1 "
			+" AND o.cancel_time <= ?2 "
			+" AND o.pay_time IS NOT NULL "
			+" AND ( "
			+" 	( "
			+" 		o.total_price = 0 "
			+" 		AND o.all_actual_pay > 0 "
			+" 	) "
			+" 	OR ( "
			+" 		o.total_price > 0 "
			+" 		AND o.total_price = o.other_pay "
			+" 	) "
			+" ) "
			+" UNION ALL "
			+" 	SELECT "
			+" 		uuid() id, "
			+" 		diy.city city_name, "
			+" 		o.diy_site_name diy_site_name, "
			+" 		'电子券' AS stype, "
			+" 		o.main_order_number main_order_number, "
			+" 		o.order_number order_number, "
			+" 		o.order_time order_time, "
			+" 		o.username username, "
			+" 		o.real_user_real_name real_name, "
			+" 		o.seller_username seller_username, "
			+" 		o.seller_real_name seller_real_name, "
			+" 		og.sku sku, "
			+" 		og.goods_title goods_title, "
			+" 		CASE "
			+" 	WHEN og.presented_list_id IS NULL THEN "
			+" 		'N' "
			+" 	ELSE "
			+" 		'Y' "
			+" 	END is_gift, "
			+" 	CASE "
			+" WHEN og.presented_list_id IS NULL THEN "
			+" 	og.price "
			+" ELSE "
			+" 	0 "
			+" END AS goods_price, "
			+"  og.quantity goods_quantity, "
			+"  CASE "
			+" WHEN og.presented_list_id IS NULL "
			+" AND og.cash_number > 0 THEN "
			+" 	og.coupon_money / og.cash_number "
			+" ELSE "
			+" 	0 "
			+" END AS cash_coupon_price, "
			+"  CASE "
			+" WHEN og.presented_list_id IS NULL THEN "
			+" 	og.cash_number "
			+" ELSE "
			+" 	0 "
			+" END AS cash_coupon_quantity, "
			+"  0 product_coupon_price, "
			+"  0 product_coupon_quantity "
			+" FROM "
			+" 	td_order o "
			+" LEFT JOIN td_order_goods og ON o.id = og.td_order_id "
			+" OR o.id = og.presented_list_id "
			+" LEFT JOIN td_diy_site diy ON o.diy_site_id = diy.id "
			+" WHERE "
			+" 	o.is_coupon = 1 "
			+" AND o.order_time >= ?1 "
			+" AND o.order_time <= ?2 "
			+" AND o.status_id >= 4 "
			+" UNION ALL "
			+" 	SELECT "
			+" 		uuid() id, "
			+" 		diy.city city_name, "
			+" 		o.diy_site_name diy_site_name, "
			+" 		'退券' AS stype, "
			+" 		rn.return_number main_order_number, "
			+" 		o.order_number order_number, "
			+" 		o.order_time order_time, "
			+" 		o.username username, "
			+" 		o.real_user_real_name real_name, "
			+" 		o.seller_username seller_username, "
			+" 		o.seller_real_name seller_real_name, "
			+" 		og.sku sku, "
			+" 		og.goods_title goods_title, "
			+" 		'N' AS is_gift, "
			+" 		og.return_unit_price goods_price, "
			+" 		- og.quantity goods_quantity, "
			+" 		0 cash_coupon_price, "
			+" 		0 cash_coupon_quantity, "
			+" 		0 product_coupon_price, "
			+" 		0 product_coupon_quantity "
			+" 	FROM "
			+" 		td_return_note rn "
			+" 	LEFT JOIN td_order_goods og ON rn.id = og.td_return_id "
			+" 	LEFT JOIN td_order o ON o.order_number = rn.order_number "
			+" 	LEFT JOIN td_diy_site diy ON o.diy_site_id = diy.id "
			+" 	WHERE "
			+" 		rn.is_coupon = 1 "
			+" 	AND rn.order_time >= ?1 "
			+" 	AND rn.order_time <= ?2 "
			+" 	AND rn.status_id >= 4 "
			+" 	UNION ALL "
			+" 		SELECT "
			+" 			uuid() id, "
			+" 			diy.city city_name, "
			+" 			o.diy_site_name diy_site_name, "
			+" 			'退货' AS stype, "
			+" 			rn.return_number main_order_number, "
			+" 			o.order_number order_number, "
			+" 			o.order_time order_time, "
			+" 			o.username username, "
			+" 			o.real_user_real_name real_name, "
			+" 			o.seller_username seller_username, "
			+" 			o.seller_real_name seller_real_name, "
			+" 			og.sku sku, "
			+" 			og.goods_title, "
			+" 			'N' AS is_gift, "
			+" 			og.return_unit_price goods_price, "
			+" 			- og.quantity goods_quantity, "
			+" 			0 cash_coupon_price, "
			+" 			0 cash_coupon_quantity, "
			+" 			IFNULL( "
			+" 				coupon.product_coupon_price, "
			+" 				0 "
			+" 			) product_coupon_price, "
			+" 			IFNULL( "
			+" 				- coupon.product_coupon_quantity, "
			+" 				0 "
			+" 			) product_coupon_quantity "
			+" 		FROM "
			+" 			td_return_note rn "
			+" 		LEFT JOIN td_order_goods og ON rn.id = og.td_return_id "
			+" 		LEFT JOIN td_return_order_inf ri ON rn.order_number = ri.order_number  "
			+" 		LEFT JOIN ( "
			+" 			SELECT "
			+" 				coupon_info.sku sku, "
			+" 				coupon_info.cash_coupon_price cash_coupon_price, "
			+" 				sum( "
			+" 					coupon_info.cash_coupon_quantity "
			+" 				) cash_coupon_quantity, "
			+" 				coupon_info.product_coupon_price product_coupon_price, "
			+" 				sum( "
			+" 					coupon_info.product_coupon_quantity "
			+" 				) product_coupon_quantity, "
			+" 				coupon_info.rt_header_id rt_header_id "
			+" 			FROM "
			+" 				( "
			+" 					SELECT "
			+" 						sku, "
			+" 						CASE coupon_type_id "
			+" 					WHEN 2 THEN "
			+" 						price "
			+" 					ELSE "
			+" 						0 "
			+" 					END cash_coupon_price, "
			+" 					CASE coupon_type_id "
			+" 				WHEN 2 THEN "
			+" 					quantity "
			+" 				ELSE "
			+" 					0 "
			+" 				END cash_coupon_quantity, "
			+" 				CASE coupon_type_id "
			+" 			WHEN 1 THEN "
			+" 				price "
			+" 			WHEN 4 THEN "
			+" 				price "
			+" 			ELSE "
			+" 				0 "
			+" 			END product_coupon_price, "
			+" 			CASE coupon_type_id "
			+" 		WHEN 1 THEN "
			+" 			quantity "
			+" 		WHEN 4 THEN "
			+" 			quantity "
			+" 		ELSE "
			+" 			0 "
			+" 		END product_coupon_quantity, "
			+" 		rt_header_id "
			+" 	FROM "
			+" 		td_return_coupon_inf "
			+" 				) coupon_info "
			+" 			GROUP BY "
			+" 				coupon_info.rt_header_id, "
			+" 				coupon_info.sku "
			+" 		) coupon ON og.sku = coupon.sku "
			+" 		AND ri.rt_header_id = coupon.rt_header_id "
			+" 		LEFT JOIN td_order o ON rn.order_number = o.order_number "
			+" 		LEFT JOIN td_diy_site diy ON o.diy_site_id = diy.id "
			+" 		WHERE "
			+" 			rn.receive_time >= ?1 "
			+" 		AND rn.receive_time <= ?2 "
			+" 		AND rn.status_id >= 4 "
			+" 		ORDER BY "
			+" 			order_time, "
			+" 			main_order_number, "
			+" 			order_number DESC; ", nativeQuery=true)
	List<TdSales> getSalesList(Date beginDate,Date endDate);
	

}
