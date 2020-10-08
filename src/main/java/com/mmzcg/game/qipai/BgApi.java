//package com.mmzcg.game.qipai;
//
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.lzkj.game.UgChannelGameApplication;
//import com.lzkj.game.async.CleanShipUtil;
//import com.lzkj.game.async.GameRecordOtherESUtil;
//import com.lzkj.game.async.NativeWebUtil;
//import com.lzkj.game.client.AccountsServiceClient;
//import com.lzkj.game.client.TreasureServiceClient;
//import com.lzkj.game.config.ChannelParamsConfig;
//import com.lzkj.game.mongo.GameRecord;
//import com.lzkj.game.mongo.live.BgLiveRecord;
//import com.lzkj.game.redis.AccountService;
//import com.lzkj.game.util.HttpRequest;
//import com.lzkj.game.util.MD5Util;
//import com.lzkj.game.util.Sha1Util;
//import com.lzkj.game.util.StringUtil;
//import com.lzkj.game.vo.ChannelUserInfoVO;
//import com.lzkj.game.vo.UserStatusInfoVO;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.math.BigDecimal;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
//@Component
//@Slf4j
//public class BgApi implements ApiInterface {
//
//
//	private SimpleDateFormat beijingTimeSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	SimpleDateFormat usaTimeSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	private String lastEndTime;
//
//
//
//	/**
//	 * 先创建会员，再登录游戏
//	 */
//	@Override
//	public String login(String account, String password, String kindCode, String ip) {
//		String mode = "&mode=3";
//		String secretCode = Sha1Util.encodeBase64(channelParamsConfig.getBg_agentPassWord());
//		String id = UUID.randomUUID().toString();
//		String random = id;
//		String digest = MD5Util.MD5Encode(random + channelParamsConfig.getBg_sn() + account + secretCode, "UTF-8");
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("random", random);
//		params.put("sn", channelParamsConfig.getBg_sn());
//		params.put("loginId", account);
//		params.put("digest", digest);
//		String method = "open.user.get";
//
//		Map<String, Object> postData = new HashMap<String, Object>();
//		postData.put("id", id);
//		postData.put("method", method);
//		postData.put("params", params);
//		postData.put("jsonrpc", "2.0");
//
//		Object str = JSONObject.toJSON(postData);
//		String content = "json=" + str;
//		String res = HttpRequest.sendPost(channelParamsConfig.getBg_url() + method, content);
//		log.info("获取会员信息第三方返回：" + res);
//		JSONObject resJson = JSONObject.parseObject(res);
//		String result = resJson.getString("result");
//		if (result.equals("0")) {
//			id = UUID.randomUUID().toString();
//			random = id;
//			digest = MD5Util.MD5Encode(random + channelParamsConfig.getBg_sn() + secretCode, "UTF-8");
//			params = new HashMap<String, Object>();
//			params.put("random", random);
//			params.put("sn", channelParamsConfig.getBg_sn());
//			params.put("loginId", account);
//			params.put("agentLoginId", channelParamsConfig.getBg_agentLoginId());
//			params.put("digest", digest);
//			method = "open.user.create";
//			postData = new HashMap<String, Object>();
//			postData.put("id", id);
//			postData.put("method", method);
//			postData.put("params", params);
//			postData.put("jsonrpc", "2.0");
//
//			str = JSONObject.toJSON(postData);
//			content = "json=" + str;
//			res = HttpRequest.sendPost(channelParamsConfig.getBg_url() + method, content);
//			log.info("创建会员信息第三方返回：" + res);
//			resJson = JSONObject.parseObject(res);
//			result = resJson.getString("result");
//			if (result.equals("0")) {
//				return null;
//			}
//		}
//		switch (kindCode) {
//		case "1":
//			id = UUID.randomUUID().toString();
//			random = id;
//			digest = MD5Util.MD5Encode(random + channelParamsConfig.getBg_sn() + account + secretCode, "UTF-8");
//			params = new HashMap<String, Object>();
//			params.put("random", random);
//			params.put("sn", channelParamsConfig.getBg_sn());
//			params.put("loginId", account);
//			params.put("digest", digest);
//			params.put("isMobileUrl", 1);
//			method = "open.video.game.url";
//
//			postData = new HashMap<String, Object>();
//			postData.put("id", id);
//			postData.put("method", method);
//			postData.put("params", params);
//			postData.put("jsonrpc", "2.0");
//
//			str = JSONObject.toJSON(postData);
//			content = "json=" + str;
//			res = HttpRequest.sendPost(channelParamsConfig.getBg_url() + method, content);
//			log.info("视讯游戏第三方返回：" + res);
//			resJson = JSONObject.parseObject(res);
//			result = resJson.getString("result");
//			if (result.equals("0")) {
//				return null;
//			}
//			return result;
//		case "2":
//			String gameType = "105"; // 105为BG捕鱼
//			id = UUID.randomUUID().toString();
//			random = id;
//			digest = MD5Util.MD5Encode(random + channelParamsConfig.getBg_sn() + channelParamsConfig.getBg_secretKey(), "UTF-8");
//			params = new HashMap<String, Object>();
//			params.put("random", random);
//			params.put("sn", channelParamsConfig.getBg_sn());
//			params.put("loginId", account);
//			params.put("sign", digest);
//			params.put("gameType", gameType);
//			method = "open.game.bg.fishing.url";
//
//			postData = new HashMap<String, Object>();
//			postData.put("id", id);
//			postData.put("method", method);
//			postData.put("params", params);
//			postData.put("jsonrpc", "2.0");
//
//			str = JSONObject.toJSON(postData);
//			content = "json=" + str;
//			res = HttpRequest.sendPost(channelParamsConfig.getBg_url() + method, content);
//			log.info("BG捕鱼大师第三方返回：" + res);
//			resJson = JSONObject.parseObject(res);
//			result = resJson.getString("result");
//			if (result.equals("0")) {
//				return null;
//			}
//			return result + mode;
//		case "3":
//			gameType = "411"; // 411为西游捕鱼
//			id = UUID.randomUUID().toString();
//			random = id;
//			digest = MD5Util.MD5Encode(random + channelParamsConfig.getBg_sn() + channelParamsConfig.getBg_secretKey(), "UTF-8");
//			params = new HashMap<String, Object>();
//			params.put("random", random);
//			params.put("sn", channelParamsConfig.getBg_sn());
//			params.put("loginId", account);
//			params.put("sign", digest);
//			params.put("gameType", gameType);
//			method = "open.game.bg.fishing.url";
//
//			postData = new HashMap<String, Object>();
//			postData.put("id", id);
//			postData.put("method", method);
//			postData.put("params", params);
//			postData.put("jsonrpc", "2.0");
//
//			str = JSONObject.toJSON(postData);
//			content = "json=" + str;
//			res = HttpRequest.sendPost(channelParamsConfig.getBg_url() + method, content);
//			log.info("西游捕鱼第三方返回：" + res);
//			resJson = JSONObject.parseObject(res);
//			result = resJson.getString("result");
//			if (result.equals("0")) {
//				return null;
//			}
//			return result + mode;
//		default:
//			return "";
//		}
//	}
//
///**
// * 转账金额(负数表示从BG 转出，正数转入)
// */
//	@Override
//	public boolean upperScore(String orderNumber, String account, BigDecimal score, String password) {
//		String secretCode = Sha1Util.encodeBase64(channelParamsConfig.getBg_agentPassWord());
//		String id = UUID.randomUUID().toString();
//		String random = id;
//		String digest = MD5Util.MD5Encode(random+channelParamsConfig.getBg_sn()+account+score+secretCode, "UTF-8");
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("random", random);
//		params.put("sn", channelParamsConfig.getBg_sn());
//		params.put("loginId", account);
//		params.put("digest", digest);
//		params.put("amount", score);
//		String method = "open.balance.transfer";
//
//		Map<String, Object> postData = new HashMap<String, Object>();
//		postData.put("id", id);
//		postData.put("method", method);
//		postData.put("params", params);
//		postData.put("jsonrpc", "2.0");
//
//		Object str = JSONObject.toJSON(postData);
//		String content = "json=" + str;
//		String res = HttpRequest.sendPost(channelParamsConfig.getBg_url() + method, content);
//		log.info("额度转换第三方返回：" + res);
//		JSONObject resJson = JSONObject.parseObject(res);
//		String msg = resJson.getString("error");
//		if( null == msg || ("null").equals(msg)) {
//			return true;
//		}
//		return false;
//	}
///**
// * 转账金额(负数表示从BG 转出，正数转入)
// */
//	@Override
//	public boolean lowerScore(String orderNumber, String account, BigDecimal score, String password) {
//		return upperScore(orderNumber, account, score.multiply(new BigDecimal(-1)), password);
//	}
//
//	/**
//	 * 会员信息
//	 */
//	@Override
//	public UserStatusInfoVO getUserStatusInfo(String account, String password) {
//		String secretCode = Sha1Util.encodeBase64(channelParamsConfig.getBg_agentPassWord());
//		String id = UUID.randomUUID().toString();
//		String random = id;
//		String digest = MD5Util.MD5Encode(random+channelParamsConfig.getBg_sn()+account+secretCode, "UTF-8");
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("random", random);
//		params.put("sn", channelParamsConfig.getBg_sn());
//		params.put("loginId", account);
//		params.put("digest", digest);
//		String method = "open.balance.get";
//
//		Map<String, Object> postData = new HashMap<String, Object>();
//		postData.put("id", id);
//		postData.put("method", method);
//		postData.put("params", params);
//		postData.put("jsonrpc", "2.0");
//
//		Object str = JSONObject.toJSON(postData);
//		String content = "json=" + str;
//		String res = HttpRequest.sendPost(channelParamsConfig.getBg_url() + method, content);
//		log.info("查询余额第三方返回：" + res);
//		JSONObject resJson = JSONObject.parseObject(res);
//		String msg = resJson.getString("error");
//		if(null == msg || ("null").equals(msg)) {
//			UserStatusInfoVO u = new UserStatusInfoVO();
//			BigDecimal balance = resJson.getBigDecimal("result");
//			u.setScore(balance);
//			return u;
//		}
//		return null;
//	}
//
//	@Override
//	public String kindName(String kindCode) {
//		return "BG平台";
//	}
//
//	@Override
//	public String createOrderNumber() {
//		Random r = new Random();
//		String order_number = "10005" + ((char)(r.nextInt(123 - 97) + 97)) +
//				((char)(r.nextInt(123 - 97) + 97)) +
//				((char)(r.nextInt(123 - 97) + 97)) +
//				((char)(r.nextInt(123 - 97) + 97)) + System.currentTimeMillis();
//		return order_number;
//	}
//
//	@Override
//	public List<GameRecord> pullGameRecord() {
//		usaTimeSdf.setTimeZone(TimeZone.getTimeZone("GMT-4"));
//		beijingTimeSdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
//		List<GameRecord> gameRecordList = new ArrayList<GameRecord>();
//
//		Date now = new Date();
//		log.error("北京时间\tnow :" + beijingTimeSdf.format(now));
//		String startTime;
//		if (StringUtil.isEmpty(lastEndTime)) {
//			lastEndTime = usaTimeSdf.format(now.getTime() - 1000 * 60 * 2);
//			startTime = usaTimeSdf.format(now.getTime() - 1000 * 60 * 10 * 7 - 12000);
//		} else {
//			startTime = lastEndTime;
//			//lastEndTime = usaTimeSdf.format(usaTimeSdf.parse(lastEndTime).getTime() + 1000 * 60 * 10);
//			lastEndTime = usaTimeSdf.format(now.getTime() - 1000 * 60 * 2);
//		}
//		log.error("美东时间");
//		log.error("startTime:"+startTime);
//		log.error("lastEndTime:"+lastEndTime);
//		String id = UUID.randomUUID().toString();
//		String random = id;
//		String digest = MD5Util.MD5Encode(random+channelParamsConfig.getBg_sn()+channelParamsConfig.getBg_secretKey(), "UTF-8");
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("random", random);
//		params.put("sn", channelParamsConfig.getBg_sn());
//		params.put("sign", digest);
//		params.put("startTime", startTime);
//		params.put("endTime", lastEndTime);
//		params.put("pageSize", "500");
//		String method = "open.order.query";
//
//		Map<String, Object> postData = new HashMap<String, Object>();
//		postData.put("id", id);
//		postData.put("method", method);
//		postData.put("params", params);
//		postData.put("jsonrpc", "2.0");
//
//		Object str = JSONObject.toJSON(postData);
//		String content = "json=" + str;
//		String res = HttpRequest.sendPost(channelParamsConfig.getBg_url()+ method, content);
//		log.error("拉取视讯注单第三方返回：" + res);
//		if(!StringUtil.isEmpty(res)) {
//			JSONObject resJson = JSONObject.parseObject(res);
//			String result = resJson.getString("result");
//			if(!result.equals("0")) {
//				resJson = JSONObject.parseObject(resJson.getString("result"));
//				Integer code = resJson.getInteger("total");
//				if(code > 0) {
//					JSONArray data = resJson.getJSONArray("items");
//					int size = data.size();
//					log.error("共" + size + "条BG视讯注单数据");
//					if(size > 0) {
//						for(Object d : data) {
//							JSONObject item = JSONObject.parseObject(d.toString());
//							BgLiveRecord llr = new BgLiveRecord();
//							llr.setChannelUniqueId(item.getString("orderId"));
//							String userName = item.getString("loginId");
//							ChannelUserInfoVO channelUserInfoVO = accountService.getAccountInfoByChannelAccount(userName);
//							if(channelUserInfoVO == null) {
//								log.error("{}用户不存在", userName);
//								continue;
//							}
//							llr.setAccount(channelUserInfoVO.getAccounts());
//							llr.setParentId(channelUserInfoVO.getParentId());
//							llr.setUserId(channelUserInfoVO.getUserId());
//							llr.setChannelAccount(userName);
//							llr.setGameId(channelUserInfoVO.getGameId());
//							id = UUID.randomUUID().toString();
//							random = id;
//							String reqTime = beijingTimeSdf.format(now);
//							digest = MD5Util.MD5Encode(random + channelParamsConfig.getBg_sn() + llr.getChannelUniqueId() + reqTime + channelParamsConfig.getBg_secretKey(), "UTF-8");
//							params = new HashMap<String, Object>();
//							params.put("random", random);
//							params.put("sn", channelParamsConfig.getBg_sn());
//							params.put("sign", digest);
//							params.put("orderId", llr.getChannelUniqueId());
//							params.put("reqTime", reqTime);
//							method = "open.sn.video.order.detail";
//							String url = channelParamsConfig.getBg_pullUrl() + method;
//							postData = new HashMap<String, Object>();
//							postData.put("id", id);
//							postData.put("method", method);
//							postData.put("params", params);
//							postData.put("jsonrpc", "2.0");
//
//							str = JSONObject.toJSON(postData);
//							content = "json=" + str;
//							res = HttpRequest.sendPost(url, content);
//							log.error("拉取视讯详情注单第三方返回：" + res + "?" + content);
//							resJson = JSONObject.parseObject(res);
//							resJson = JSONObject.parseObject(resJson.getString("result"));
//							llr.setGameCode(resJson.getString("serialNo"));
//							llr.setGameName(resJson.getString("gameName"));
//							llr.setTableName(resJson.getString("tableId"));
//
//							llr.setRoomName(resJson.getString("gameName"));
//							llr.setScore(item.getBigDecimal("payment").setScale(2, BigDecimal.ROUND_DOWN).doubleValue());
//							llr.setBetAmount(item.getDoubleValue("validBet"));
//							llr.setKindType(1);
//							Date orderTime = null;
//							String orderTimeStr = item.getString("orderTime");
//							try {
//								orderTime = usaTimeSdf.parse(orderTimeStr);
//								orderTimeStr = beijingTimeSdf.format(orderTime);
//								llr.setStartTime(orderTimeStr);
//								llr.setStartTimestamp(beijingTimeSdf.parse(orderTimeStr).getTime());
//							} catch(Exception e) {
//							}
//							Date paymentTime = null;
//							String paymentTimeStr = resJson.getString("paymentTime");
//							try {
//								paymentTime = usaTimeSdf.parse(paymentTimeStr);
//								paymentTimeStr = beijingTimeSdf.format(paymentTime);
//								llr.setEndTime(paymentTimeStr);
//							} catch(Exception e) {
//							}
//							llr.setDetail(item.toJSONString());
//							llr.setDetail2(resJson.toJSONString());
//							llr.setKindId(10005);
//							gameRecordList.add(llr);
//						}
//					}
//				}
//			}
//		}
//
//		//拉取捕鱼注单
//		String gameType = "1";   //当指定gameType=1时，将同时返回BG 捕鱼大师和西游捕鱼的注单记录
//		id = UUID.randomUUID().toString();
//		random = id;
//		digest = MD5Util.MD5Encode(random+channelParamsConfig.getBg_sn()+channelParamsConfig.getBg_secretKey(), "UTF-8");
//		params = new HashMap<String, Object>();
//		params.put("random", random);
//		params.put("sn", channelParamsConfig.getBg_sn());
//		params.put("sign", digest);
//		params.put("gameType", gameType);
//		params.put("startTime", startTime);
//		params.put("endTime", lastEndTime);
//		params.put("pageSize", "500");
//		method = "open.order.bg.fishing.query";
//		postData = new HashMap<String, Object>();
//		postData.put("id", id);
//		postData.put("method", method);
//		postData.put("params", params);
//		postData.put("jsonrpc", "2.0");
//
//		str = JSONObject.toJSON(postData);
//		content = "json=" + str;
//		res = HttpRequest.sendPost(channelParamsConfig.getBg_url()+ method, content);
//		log.error("拉取捕鱼注单第三方返回：" + res);
//		if(!StringUtil.isEmpty(res)) {
//			JSONObject resJson = JSONObject.parseObject(res);
//			String code = resJson.getString("result");
//			if(!code.equals("0")) {
//				resJson = JSONObject.parseObject(resJson.getString("result"));
//				Integer count = resJson.getInteger("total");
//				if(count > 0) {
//					JSONArray data = resJson.getJSONArray("items");
//					int size = data.size();
//					log.error("共" + size + "条BG捕鱼注单数据");
//					if(size > 0) {
//						for(Object d : data) {
//							JSONObject item = JSONObject.parseObject(d.toString());
//							BgLiveRecord llr = new BgLiveRecord();
//							llr.setChannelUniqueId(item.getString("betId"));
//							String userName = item.getString("loginId");
//							ChannelUserInfoVO channelUserInfoVO = accountService.getAccountInfoByChannelAccount(userName);
//							if(channelUserInfoVO == null) {
//								log.error("{}用户不存在", userName);
//								continue;
//							}
//							llr.setAccount(channelUserInfoVO.getAccounts());
//							llr.setParentId(channelUserInfoVO.getParentId());
//							llr.setUserId(channelUserInfoVO.getUserId());
//							llr.setChannelAccount(userName);
//							llr.setGameId(channelUserInfoVO.getGameId());
//
//							llr.setGameCode(item.getString("issueId"));
//							String gameId = item.getString("gameType");
//							if(gameId.equals("105")) {
//								llr.setGameName("BG捕鱼大师");
//								llr.setRoomName("BG捕鱼大师");
//								llr.setTableName("BG捕鱼大师");
//							}
//							if(gameId.equals("411")) {
//								llr.setGameName("西游捕鱼");
//								llr.setRoomName("西游捕鱼");
//								llr.setTableName("西游捕鱼");
//							}
//
//							llr.setScore(item.getBigDecimal("payout").setScale(2, BigDecimal.ROUND_DOWN).doubleValue());
//							llr.setBetAmount(item.getDoubleValue("validAmount"));
//							llr.setKindType(4);
//							Date orderTime = null;
//							String orderTimeStr = item.getString("orderTime");
//							try {
//								orderTime = usaTimeSdf.parse(orderTimeStr);
//								orderTimeStr = beijingTimeSdf.format(orderTime);
//								llr.setStartTime(orderTimeStr);
//								llr.setStartTimestamp(beijingTimeSdf.parse(orderTimeStr).getTime());
//							} catch(Exception e) {
//
//							}
//							Date paymentTime = null;
//							String paymentTimeStr = resJson.getString("paymentTime");
//							try {
//								paymentTime = usaTimeSdf.parse(paymentTimeStr);
//								paymentTimeStr = beijingTimeSdf.format(paymentTime);
//								llr.setEndTime(paymentTimeStr);
//							} catch(Exception e) {
//
//							}
//							llr.setDetail(item.toJSONString());
//							llr.setKindId(10005);
//							gameRecordList.add(llr);
//						}
//					}
//				}
//			}
//		}
//		return gameRecordList;
//   }
//
//	@Override
//	public List<Object> gameList() {
//
//		return null;
//	}
//
//	@Override
//	public boolean makePullGameRecord(String startTime, String endTime) throws Exception {
//		log.error("===开始BG平台补单");
//		boolean make = false;
//		usaTimeSdf.setTimeZone(TimeZone.getTimeZone("GMT-4"));
//		beijingTimeSdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
//		List<GameRecord> gameRecordList = new ArrayList<GameRecord>();
//		Date now = new Date();
//		startTime = usaTimeSdf.format(beijingTimeSdf.parse(startTime));
//		endTime = usaTimeSdf.format(beijingTimeSdf.parse(endTime));
//		String id = UUID.randomUUID().toString();
//		String random = id;
//		String digest = MD5Util.MD5Encode(random+channelParamsConfig.getBg_sn()+channelParamsConfig.getBg_secretKey(), "UTF-8");
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("random", random);
//		params.put("sn", channelParamsConfig.getBg_sn());
//		params.put("sign", digest);
//		params.put("startTime", startTime);
//		params.put("endTime", lastEndTime);
//		params.put("pageSize", "500");
//		String method = "open.order.query";
//
//		Map<String, Object> postData = new HashMap<String, Object>();
//		postData.put("id", id);
//		postData.put("method", method);
//		postData.put("params", params);
//		postData.put("jsonrpc", "2.0");
//
//		Object str = JSONObject.toJSON(postData);
//		String content = "json=" + str;
//		String res = HttpRequest.sendPost(channelParamsConfig.getBg_url()+ method, content);
//		log.error("拉取视讯注单第三方返回：" + res);
//		if(!StringUtil.isEmpty(res)) {
//			JSONObject resJson = JSONObject.parseObject(res);
//			String result = resJson.getString("result");
//			if(!result.equals("0")) {
//				resJson = JSONObject.parseObject(resJson.getString("result"));
//				Integer code = resJson.getInteger("total");
//				if(code > 0) {
//					JSONArray data = resJson.getJSONArray("items");
//					int size = data.size();
//					log.error("共" + size + "条BG视讯注单数据");
//					if(size > 0) {
//						for(Object d : data) {
//							JSONObject item = JSONObject.parseObject(d.toString());
//							BgLiveRecord llr = new BgLiveRecord();
//							llr.setChannelUniqueId(item.getString("orderId"));
//							String userName = item.getString("loginId");
//							ChannelUserInfoVO channelUserInfoVO = accountService.getAccountInfoByChannelAccount(userName);
//							if(channelUserInfoVO == null) {
//								log.error("{}用户不存在", userName);
//								continue;
//							}
//							llr.setAccount(channelUserInfoVO.getAccounts());
//							llr.setParentId(channelUserInfoVO.getParentId());
//							llr.setUserId(channelUserInfoVO.getUserId());
//							llr.setChannelAccount(userName);
//							llr.setGameId(channelUserInfoVO.getGameId());
//							id = UUID.randomUUID().toString();
//							random = id;
//							String reqTime = beijingTimeSdf.format(now);
//							digest = MD5Util.MD5Encode(random + channelParamsConfig.getBg_sn() + llr.getChannelUniqueId() + reqTime + channelParamsConfig.getBg_secretKey(), "UTF-8");
//							params = new HashMap<String, Object>();
//							params.put("random", random);
//							params.put("sn", channelParamsConfig.getBg_sn());
//							params.put("sign", digest);
//							params.put("orderId", llr.getChannelUniqueId());
//							params.put("reqTime", reqTime);
//							method = "open.sn.video.order.detail";
//							String url = channelParamsConfig.getBg_pullUrl() + method;
//							postData = new HashMap<String, Object>();
//							postData.put("id", id);
//							postData.put("method", method);
//							postData.put("params", params);
//							postData.put("jsonrpc", "2.0");
//
//							str = JSONObject.toJSON(postData);
//							content = "json=" + str;
//							res = HttpRequest.sendPost(url, content);
//							log.error("拉取视讯详情注单第三方返回：" + res + "?" + content);
//							resJson = JSONObject.parseObject(res);
//							resJson = JSONObject.parseObject(resJson.getString("result"));
//							llr.setGameCode(resJson.getString("serialNo"));
//							llr.setGameName(resJson.getString("gameName"));
//							llr.setTableName(resJson.getString("tableId"));
//
//							llr.setRoomName(resJson.getString("gameName"));
//							llr.setScore(item.getBigDecimal("payment").setScale(2, BigDecimal.ROUND_DOWN).doubleValue());
//							llr.setBetAmount(item.getDoubleValue("validBet"));
//							llr.setKindType(1);
//							Date orderTime = null;
//							String orderTimeStr = item.getString("orderTime");
//							try {
//								orderTime = usaTimeSdf.parse(orderTimeStr);
//								orderTimeStr = beijingTimeSdf.format(orderTime);
//								llr.setStartTime(orderTimeStr);
//								llr.setStartTimestamp(beijingTimeSdf.parse(orderTimeStr).getTime());
//							} catch(Exception e) {
//							}
//							Date paymentTime = null;
//							String paymentTimeStr = resJson.getString("paymentTime");
//							try {
//								paymentTime = usaTimeSdf.parse(paymentTimeStr);
//								paymentTimeStr = beijingTimeSdf.format(paymentTime);
//								llr.setEndTime(paymentTimeStr);
//							} catch(Exception e) {
//							}
//							llr.setDetail(item.toJSONString());
//							llr.setDetail2(resJson.toJSONString());
//							llr.setKindId(10005);
//							gameRecordList.add(llr);
//						}
//					}
//				}
//			}
//		}
//
//		//拉取捕鱼注单
//		String gameType = "1";   //当指定gameType=1时，将同时返回BG 捕鱼大师和西游捕鱼的注单记录
//		id = UUID.randomUUID().toString();
//		random = id;
//		digest = MD5Util.MD5Encode(random+channelParamsConfig.getBg_sn()+channelParamsConfig.getBg_secretKey(), "UTF-8");
//		params = new HashMap<String, Object>();
//		params.put("random", random);
//		params.put("sn", channelParamsConfig.getBg_sn());
//		params.put("sign", digest);
//		params.put("gameType", gameType);
//		params.put("startTime", startTime);
//		params.put("endTime", lastEndTime);
//		params.put("pageSize", "500");
//		method = "open.order.bg.fishing.query";
//		postData = new HashMap<String, Object>();
//		postData.put("id", id);
//		postData.put("method", method);
//		postData.put("params", params);
//		postData.put("jsonrpc", "2.0");
//
//		str = JSONObject.toJSON(postData);
//		content = "json=" + str;
//		res = HttpRequest.sendPost(channelParamsConfig.getBg_url()+ method, content);
//		log.error("拉取捕鱼注单第三方返回：" + res);
//		if(!StringUtil.isEmpty(res)) {
//			JSONObject resJson = JSONObject.parseObject(res);
//			String code = resJson.getString("result");
//			if(!code.equals("0")) {
//				resJson = JSONObject.parseObject(resJson.getString("result"));
//				Integer count = resJson.getInteger("total");
//				if(count > 0) {
//					JSONArray data = resJson.getJSONArray("items");
//					int size = data.size();
//					log.error("共" + size + "条BG捕鱼注单数据");
//					if(size > 0) {
//						for(Object d : data) {
//							JSONObject item = JSONObject.parseObject(d.toString());
//							BgLiveRecord llr = new BgLiveRecord();
//							llr.setChannelUniqueId(item.getString("betId"));
//							String userName = item.getString("loginId");
//							ChannelUserInfoVO channelUserInfoVO = accountService.getAccountInfoByChannelAccount(userName);
//							if(channelUserInfoVO == null) {
//								log.error("{}用户不存在", userName);
//								continue;
//							}
//							llr.setAccount(channelUserInfoVO.getAccounts());
//							llr.setParentId(channelUserInfoVO.getParentId());
//							llr.setUserId(channelUserInfoVO.getUserId());
//							llr.setChannelAccount(userName);
//							llr.setGameId(channelUserInfoVO.getGameId());
//
//							llr.setGameCode(item.getString("issueId"));
//							String gameId = item.getString("gameType");
//							if(gameId.equals("105")) {
//								llr.setGameName("BG捕鱼大师");
//								llr.setRoomName("BG捕鱼大师");
//								llr.setTableName("BG捕鱼大师");
//							}
//							if(gameId.equals("411")) {
//								llr.setGameName("西游捕鱼");
//								llr.setRoomName("西游捕鱼");
//								llr.setTableName("西游捕鱼");
//							}
//
//							llr.setScore(item.getBigDecimal("payout").setScale(2, BigDecimal.ROUND_DOWN).doubleValue());
//							llr.setBetAmount(item.getDoubleValue("validAmount"));
//							llr.setKindType(4);
//							Date orderTime = null;
//							String orderTimeStr = item.getString("orderTime");
//							try {
//								orderTime = usaTimeSdf.parse(orderTimeStr);
//								orderTimeStr = beijingTimeSdf.format(orderTime);
//								llr.setStartTime(orderTimeStr);
//								llr.setStartTimestamp(beijingTimeSdf.parse(orderTimeStr).getTime());
//							} catch(Exception e) {
//
//							}
//							Date paymentTime = null;
//							String paymentTimeStr = resJson.getString("paymentTime");
//							try {
//								paymentTime = usaTimeSdf.parse(paymentTimeStr);
//								paymentTimeStr = beijingTimeSdf.format(paymentTime);
//								llr.setEndTime(paymentTimeStr);
//							} catch(Exception e) {
//
//							}
//							llr.setDetail(item.toJSONString());
//							llr.setKindId(10005);
//							gameRecordList.add(llr);
//						}
//					}
//				}
//			}
//		}
//		if(!UgChannelGameApplication.isProd) {
//			return false;
//		}
//		for(GameRecord item : gameRecordList) {
//			String channelUniqueId = item.getChannelUniqueId();
//			if(item.getStartTime() == null) {
//				log.error("记录：" + channelUniqueId + "StartTime字段不能为空");
//				continue;
//			}
//			Integer count = treasureServiceClient.gameRecordCountByChannelUniqueId(channelUniqueId);
//			if(count > 0) {
//				log.error("记录：" + channelUniqueId + "已存在");
//			} else {
//				Integer dbRet = treasureServiceClient.saveGameRecord(item);
//				if(dbRet < 0) {
//					log.info("记录{}已存在", item.getChannelUniqueId());
//				}
//			}
//			String roomName = "";
//			if(!StringUtils.isBlank(item.getRoomName())){
//				roomName = item.getRoomName();
//			}
//			String gameName = "";
//			if(!StringUtils.isBlank(item.getGameName())){
//				gameName = item.getGameName();
//			}
//			String gameCode = "";
//			if(!StringUtils.isBlank(item.getGameCode())){
//				gameCode = item.getGameCode();
//			}
//			BigDecimal bdRe = new BigDecimal("0");
//            if(0 != item.getRevenue()) {
//                bdRe = new BigDecimal(String.valueOf(item.getRevenue())).setScale(2, BigDecimal.ROUND_DOWN);
//            }
//            JSONObject retJson = accountsServiceClient.channelGameWriteScore(item.getUserId(), item.getParentId(), 10005, new BigDecimal(String.valueOf(item.getBetAmount())).setScale(2, BigDecimal.ROUND_DOWN), item.getChannelUniqueId(), item.getStartTime(),item.getKindType(),new BigDecimal(String.valueOf(item.getScore())).setScale(2, BigDecimal.ROUND_DOWN),
//                    gameName,gameCode,roomName,item.getChannelAccount(), bdRe);
//			log.error("调用结算存过{}", retJson);
//			if(null != retJson) {
//				Integer ret = retJson.getInteger("ret");
//				if(ret != null && ret.equals(0)) {
//				    nativeWebUtil.activityBetAmountAdvanceByThird(item.getUserId(),item.getParentId(),new BigDecimal(String.valueOf(item.getBetAmount())).setScale(2, BigDecimal.ROUND_DOWN),item.getStartTime(),item.getKindType(),10005);
//					//gameRecordOtherESUtil.saveEsGameRecordOther(item.getChannelUniqueId());
//					cleanShipUtil.insertCleanShipBet(item.getUserId(), item.getParentId(),new BigDecimal(String.valueOf(item.getBetAmount())).setScale(2, BigDecimal.ROUND_DOWN), 10005, item.getKindType());
//					log.error("addUserInChannelGame结果：{}", accountsServiceClient.addUserInChannelGame(item.getParentId(), item.getUserId(), 10005, item.getChannelAccount(), new BigDecimal(String.valueOf(item.getScore())).setScale(2, BigDecimal.ROUND_DOWN), 1));
//				}
//			}
//		}
//		log.error("===结束BG平台补单");
//		make = true;
//		return make;
//	}
//
//	@Override
//	public boolean orderStatus(String orderNumber) {
//		return false;
//	}
//}
