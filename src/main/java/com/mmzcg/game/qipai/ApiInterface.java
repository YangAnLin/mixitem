//package com.mmzcg.game.qipai;
//
//import com.lzkj.game.mongo.GameRecord;
//import com.lzkj.game.vo.UserStatusInfoVO;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//public interface ApiInterface {
//	/**
//	 * 登录
//	 * @param account
//	 * @return
//	 */
//	String login(String account, String password, String kindCode, String ip);
//
//	/**
//	 * 上分，存款，转入金额到第三方
//	 * @param orderNumber
//	 * @param account
//	 * @param score
//	 * @return
//	 */
//	boolean upperScore(String orderNumber, String account, BigDecimal score,String password);
//
//	/**
//	 * 下分，取款，转出金额到我方平台
//	 * @param orderNumber
//	 * @param account
//	 * @param score
//	 * @return
//	 */
//	boolean lowerScore(String orderNumber, String account, BigDecimal score,String password);
//
//	/**
//	 * 会员状态，信息
//	 * @param account
//	 * @return
//	 */
////	UserStatusInfoVO getUserStatusInfo(String account,String password);
//
//	/**
//	 * 拉取注单
//	 * @return
//	 */
////	List<GameRecord> pullGameRecord();
//
//
//	/**
//	 * 游戏名称
//	 * @return
//	 */
//	String kindName(String kindCode);
//
//	/**
//	 * 按各平台订单号规则生成订单号
//	 * @return
//	 */
//	String createOrderNumber();
//
//	List<Object> gameList();
//
//	boolean makePullGameRecord(String startTime, String endTime) throws Exception;
//
//	boolean orderStatus(String orderNumber);
//}