package com.mmzcg.game.qipai;

import com.mmzcg.entity.Account;

import java.math.BigDecimal;

public interface ApiInterface {

	/**
	 * 登录
	 */
	String login(String account, String password, String kindCode, String ip);

	/**
	 * 上分，存款，转入金额到第三方
	 */
	boolean upperScore(String orderNumber, String account, BigDecimal score,String password);

	/**
	 * 下分，取款，转出金额到我方平台
	 */
	boolean lowerScore(String orderNumber, String account, BigDecimal score,String password);

	/**
	 * 会员状态，信息
	 */
	Account getUserStatusInfo(String account, String password);

	/**
	 * 游戏名称
	 */
	String kindName(String kindCode);

	/**
	 * 按各平台订单号规则生成订单号
	 */
	String createOrderNumber();
}