//package com.mmzcg.game.qipai;
//
//
//import cn.hutool.http.HttpRequest;
//import com.mmzcg.util.DESUtil;
//import com.mmzcg.util.MD5Util;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.xml.sax.InputSource;
//
//import java.io.BufferedReader;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.StringReader;
//import java.lang.reflect.Field;
//import java.math.BigDecimal;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
//@Component
//@Slf4j
//public class AgPlatformApi implements ApiInterface{
//
//
//	private static String desKey = "abDRMhH8";
//	private static String md5Key = "HjFTFcRYLR6f";
//	private static String cagent = "FJ3_AGIN";
//	private static String url = "https://gi.mu622.com/doBusiness.do";
//
//	//ftp服务器地址
//    public static String hostname = "xf.gdcapi.com";
//
//    //ftp服务器端口号默认为21
//    public static Integer port = 21 ;
//
//    //ftp登录账号
//    public static String username = "FJ3.liangzi";
//
//    //ftp登录密码
//    public static String password = "oBffTLnUlY";
//
//    //当日期不等于这个值，清空todayProcessedFiles
//    private String todayTag;
//
//    //用于存储今天处理过的文件名
//    private HashSet<String> todayProcessedFiles = new HashSet<>();
//
//	@Override
//	public String login(String account, String password, String kindCode, String ip) {
//    	String method = "lg";
//    	String actype = "1";
//    	String oddtype = "A";
//    	String cur = "CNY";
//		String p = "cagent=" + cagent + "/\\\\/" +
//				"loginname=" + account + "/\\\\/" +
//				"method=" + method + "/\\\\/" +
//				"actype=" + actype + "/\\\\/" +
//				"password=" + password + "/\\\\/" +
//				"oddtype=" + oddtype + "/\\\\/" +
//				"cur=" + cur;
//    	String params = DESUtil.encrypts(p, desKey);
//    	String key = MD5Util.MD5Encode(params + md5Key, "UTF-8");
//    	String param = "params="+params+"&key="+key;
//    	String res = HttpRequest.post(url+param);
//    	log.info("第三方返回：" + res);
//    	InputSource in = new InputSource(new StringReader(res));
//    	in.setEncoding("UTF-8");
//    	SAXReader reader = new SAXReader();
//		Document document;
//		try {
//			document = reader.read(in);
//			Element root = document.getRootElement();
//			String f = root.attribute("info").getStringValue();
//			if(f.equals("0")) {
//				String url = "https://gci.mu622.com/forwardGame.do";
//		        String dm = "httP://fj3gc.cnamedns.net";
//		        String sid = cagent + System.currentTimeMillis();
//		        String lang = "1";
//		        String gameType = StringUtil.isEmpty(kindCode) ? "0" : kindCode;
//		        String mh5 = "y";
//				p = "cagent=" + cagent + "/\\\\/" +
//						"loginname=" + account + "/\\\\/" +
//						"password=" + password + "/\\\\/" +
//						"dm=" + dm + "/\\\\/" +
//						"sid=" + sid + "/\\\\/" +
//						"actype=" + actype + "/\\\\/" +
//						"lang=" + lang + "/\\\\/" +
//						"gameType=" + gameType + "/\\\\/" +
//						"oddtype=" + oddtype + "/\\\\/" +
//						"cur=" + cur + "/\\\\/" +
//						"mh5=" + mh5;
//			  	params = DESUtil.encrypts(p, desKey);
//				key = MD5Util.MD5Encode(params + md5Key, "UTF-8");
//				param = "params=" + params + "&key=" + key;
//				return url + "?" + param;
//			}
//		} catch (DocumentException e) {
//			e.printStackTrace();
//		}
//
//		return null;
//	}
//
//	@Override
//	public boolean upperScore(String orderNumber, String account, BigDecimal score,String password) {
//		String type = "IN";
//    	String method = "tc";
//    	String actype = "1";
//    	String cur = "CNY";
//    	String billno = orderNumber;
//		String p = "cagent=" + cagent + "/\\\\/" +
//				"loginname=" + account + "/\\\\/" +
//				"method=" + method + "/\\\\/" +
//				"billno=" + billno + "/\\\\/" +
//				"type=" + type + "/\\\\/" +
//				"credit=" + score + "/\\\\/" +
//				"actype=" + actype + "/\\\\/" +
//				"password=" + password + "/\\\\/" +
//				"cur=" + cur;
//		String params = DESUtil.encrypts(p, desKey);
//    	String key = MD5Util.MD5Encode(params + md5Key, "UTF-8");
//    	String param = "params="+params+"&key="+key;
//    	String res = HttpRequest.sendPost(url, param);
//    	log.info("第三方返回：" + res);
//    	InputSource in = new InputSource(new StringReader(res));
//    	in.setEncoding("UTF-8");
//    	SAXReader reader = new SAXReader();
//		Document document;
//		try {
//			document = reader.read(in);
//			Element root = document.getRootElement();
//	    	String f = root.attribute("info").getStringValue();
//	    	if(f.equals("0")) {
//	        	method = "tcc";
//	        	String flag = "1";
//				p = "cagent=" + cagent + "/\\\\/" +
//						"loginname=" + account + "/\\\\/" +
//						"method=" + method + "/\\\\/" +
//						"billno=" + billno + "/\\\\/" +
//						"type=" + type + "/\\\\/" +
//						"credit=" + score + "/\\\\/" +
//						"actype=" + actype + "/\\\\/" +
//						"flag=" + flag + "/\\\\/" +
//						"password=" + password + "/\\\\/" +
//						"cur=" + cur;
//	    		params = DESUtil.encrypts(p, desKey);
//	        	key = MD5Util.MD5Encode(params + md5Key, "UTF-8");
//	        	param = "params="+params+"&key="+key;
//	        	res = HttpRequest.sendPost(url, param);
//	        	log.info("第三方返回：" + res);
//	        	if(StringUtils.isBlank(res)) {
//	        		return orderStatus(orderNumber);
//	        	}
//	        	in = new InputSource(new StringReader(res));
//	        	in.setEncoding("UTF-8");
//	        	reader = new SAXReader();
//	        	document = reader.read(in);
//				root = document.getRootElement();
//		    	f = root.attribute("info").getStringValue();
//		    	if(f.equals("0")) {
//		    		return true;
//		    	}
//	    	}
//		} catch (DocumentException e) {
//			e.printStackTrace();
//		}
//
//		return false;
//	}
//
//	@Override
//	public boolean lowerScore(String orderNumber, String account, BigDecimal score,String password) {
//		String type = "OUT";
//    	String method = "tc";
//    	String actype = "1";
//    	String cur = "CNY";
//    	String billno = orderNumber;
//		String p = "cagent=" + cagent + "/\\\\/" +
//				"loginname=" + account + "/\\\\/" +
//				"method=" + method + "/\\\\/" +
//				"billno=" + billno + "/\\\\/" +
//				"type=" + type + "/\\\\/" +
//				"credit=" + score + "/\\\\/" +
//				"actype=" + actype + "/\\\\/" +
//				"password=" + password + "/\\\\/" +
//				"cur=" + cur;
//		String params = DESUtil.encrypts(p, desKey);
//    	String key = MD5Util.MD5Encode(params + md5Key, "UTF-8");
//		String param = "params=" + params + "&key=" + key;
//    	String res = HttpRequest.sendPost(url, param);
//    	log.info("第三方返回：" + res);
//    	InputSource in = new InputSource(new StringReader(res));
//    	in.setEncoding("UTF-8");
//    	SAXReader reader = new SAXReader();
//		Document document;
//		try {
//			document = reader.read(in);
//			Element root = document.getRootElement();
//	    	String f = root.attribute("info").getStringValue();
//	    	if(f.equals("0")) {
//	        	method = "tcc";
//	        	String flag = "1";
//				p = "cagent=" + cagent + "/\\\\/" +
//						"loginname=" + account + "/\\\\/" +
//						"method=" + method + "/\\\\/" +
//						"billno=" + billno + "/\\\\/" +
//						"type=" + type + "/\\\\/" +
//						"credit=" + score + "/\\\\/" +
//						"actype=" + actype + "/\\\\/" +
//						"flag=" + flag + "/\\\\/" +
//						"password=" + password + "/\\\\/" +
//						"cur=" + cur;
//	    		params = DESUtil.encrypts(p, desKey);
//	        	key = MD5Util.MD5Encode(params + md5Key, "UTF-8");
//	        	param = "params="+params+"&key="+key;
//	        	res = HttpRequest.sendPost(url, param);
//	        	log.info("第三方返回：" + res);
//	        	if(StringUtils.isBlank(res)) {
//	        		return orderStatus(orderNumber);
//	        	}
//	        	in = new InputSource(new StringReader(res));
//	        	in.setEncoding("UTF-8");
//	        	reader = new SAXReader();
//	        	document = reader.read(in);
//				root = document.getRootElement();
//		    	f = root.attribute("info").getStringValue();
//		    	if(f.equals("0")) {
//		    		return true;
//		    	}
//	    	}
//		} catch (DocumentException e) {
//			e.printStackTrace();
//		}
//		return false;
//	}
//
//	@Override
//	public UserStatusInfoVO getUserStatusInfo(String account,String password) {
//		String method = "gb";
//		String actype = "1";
//		String cur = "CNY";
//		String p = "cagent="+cagent + "/\\\\/"+
// 			   "loginname="+account +"/\\\\/"+
// 			   "method="+method +"/\\\\/"+
// 			   "actype="+actype+"/\\\\/"+
// 			   "password="+password+"/\\\\/"+
// 			   "cur="+cur;
//		String params = DESUtil.encrypts(p, desKey);
//    	String key = MD5Util.MD5Encode(params + md5Key, "UTF-8");
//    	String param = "params="+params+"&key="+key;
//    	String res = HttpRequest.sendPost(url, param);
//    	log.info("第三方返回：" + res);
//    	InputSource in = new InputSource(new StringReader(res));
//    	in.setEncoding("UTF-8");
//    	SAXReader reader = new SAXReader();
//		Document document;
//		try {
//			document = reader.read(in);
//			Element root = document.getRootElement();
//	    	String f = root.attribute("msg").getStringValue();
//	    	if(f.equals("")) {
//	    		UserStatusInfoVO u = new UserStatusInfoVO();
//	    		BigDecimal balance = new BigDecimal(root.attribute("info").getStringValue());
//	    		u.setScore(balance);
//	    		return u;
//	    	}
//		} catch (DocumentException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	@Override
//	public List<GameRecord> pullGameRecord() {
//		List<GameRecord> recordList = new ArrayList<>();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
//		sdf2.setTimeZone(TimeZone.getTimeZone("GMT-4"));
//
//		//今天的记录文件夹，美东时间
//		String today = sdf2.format(new Date());
//		if(todayTag == null || !todayTag.equals(today)) {
//			todayTag = today;
//			this.todayProcessedFiles.clear();
//		}
//
//		FTPClient fc = new FTPClient();
//		try {
//			fc.connect(hostname, port); // 连接ftp服务器
//			fc.login(username, password); // 登录ftp服务器
//			int replyCode = fc.getReplyCode(); // 是否成功登录服务器
//			if (!FTPReply.isPositiveCompletion(replyCode)) {
//				log.error("============FTP连接失败============");
//				return recordList;
//			}
//
//			log.error("=====登录到ftp服务器,code:{}=====", replyCode);
//          fc.enterLocalPassiveMode();
//			FTPFile[] typeFiles =  fc.listFiles();
//			log.error("typeFiles.length：{}",typeFiles.length);
//			for(FTPFile typeFile : typeFiles) {
//				if(typeFile.isDirectory()) {
//					log.error("============入进目录{}============", fc.printWorkingDirectory() + typeFile.getName());
//					fc.changeWorkingDirectory(fc.printWorkingDirectory() + typeFile.getName());
//                  fc.enterLocalPassiveMode();
//					FTPFile[] dateFiles =  fc.listFiles();
//					for(FTPFile dateFile : dateFiles) {
//						if(dateFile.isDirectory() && dateFile.getName().equals(today)) {
//							log.error("============入进目录{}============", fc.printWorkingDirectory() + "/" + dateFile.getName());
//							fc.changeWorkingDirectory(fc.printWorkingDirectory() + "/" + dateFile.getName());
//                          fc.enterLocalPassiveMode();
//							FTPFile[] files =  fc.listFiles();
//							int number = 0;
//							for(FTPFile file : files) {
//								number++;
//								//if(this.todayProcessedFiles.contains(file.getName())) {
//								//	log.error("============文件{}已处理过============", file.getName());
//									//continue;
//								//}
//								log.error("============解析文件{}============", file.getName());
//								InputStream in = fc.retrieveFileStream(file.getName());
//								if (in != null) {
//								  BufferedReader br = new BufferedReader(new InputStreamReader(in));
//								  String xmlRowData = null;
//								  while ((xmlRowData = br.readLine()) != null) {
//									  AgGameRecordVO agGameRecord = parseGameRecord(xmlRowData);
//									  //只要不是最后一个文件，都标记为以处理，因为最后一个文件还可能再写新的数据
//									  if(number < files.length) {
//										  this.todayProcessedFiles.add(file.getName());
//									  }
//									  if(agGameRecord == null) {
//										  continue;
//									  }
//									  AgPlatformRecord agPlatformRecord = new AgPlatformRecord();
//									  agPlatformRecord.setChannelUniqueId(agGameRecord.getBillNo());
//									  String playerName = agGameRecord.getPlayerName();
//									  ChannelUserInfoVO channelUserInfoVO = accountService.getAccountInfoByChannelAccount(playerName);
//										if(channelUserInfoVO == null) {
//											log.error("{}用户不存在", playerName);
//											continue;
//										}
//										agPlatformRecord.setAccount(channelUserInfoVO.getAccounts());
//										agPlatformRecord.setParentId(channelUserInfoVO.getParentId());
//										agPlatformRecord.setUserId(channelUserInfoVO.getUserId());
//										agPlatformRecord.setChannelAccount(playerName);
//										agPlatformRecord.setGameId(channelUserInfoVO.getGameId());
//										agPlatformRecord.setGameCode(agGameRecord.getGameCode());
//										agPlatformRecord.setGameName(AgGameNameEnum.getValueByCode(agGameRecord.getGameType()));
//										agPlatformRecord.setTableName(agGameRecord.getTableCode());
//										agPlatformRecord.setDataType(agGameRecord.getDataType());
//										agPlatformRecord.setPlatformType(agGameRecord.getPlatformType());
//										agPlatformRecord.setGameType(agGameRecord.getGameType());
//										agPlatformRecord.setScore(Double.parseDouble(agGameRecord.getNetAmount()));
//										agPlatformRecord.setBetAmount(Double.parseDouble(agGameRecord.getValidBetAmount()));
//										switch(agPlatformRecord.getPlatformType()) {
//										case "XIN":
//										case "YOPLAY":
//											agPlatformRecord.setKindType(2);
//											break;
//										default:
//											switch(agPlatformRecord.getDataType()) {
//											case "EBR":
//												agPlatformRecord.setKindType(2);
//												break;
//											case "HSR":
//											case "HPR":
//												agPlatformRecord.setKindType(4);
//												break;
//											default:
//												agPlatformRecord.setKindType(2);
//												break;
//											}
//										}
//										sdf.setTimeZone(TimeZone.getTimeZone("GMT-4"));
//										Date startTime = null;
//										String startTimeStr = agGameRecord.getBetTime();
//										try {
//											startTime = sdf.parse(startTimeStr);
//											sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
//											startTimeStr = sdf.format(startTime);
//											agPlatformRecord.setStartTime(startTimeStr);
//											agPlatformRecord.setStartTimestamp(sdf.parse(startTimeStr).getTime());
//										} catch(Exception e) {
//
//										}
//
//										sdf.setTimeZone(TimeZone.getTimeZone("GMT-4"));
//										Date endTime = null;
//										String endTimeStr = agGameRecord.getRecalcuTime();
//										try {
//											endTime = sdf.parse(endTimeStr);
//											sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
//											endTimeStr = sdf.format(endTime);
//											agPlatformRecord.setEndTime(endTimeStr);
//										} catch(Exception e) {
//
//										}
//										agPlatformRecord.setDetail(xmlRowData);
//										agPlatformRecord.setKindId(10002);
//										recordList.add(agPlatformRecord);
//								  }
//								  in.close();
//								}
//								fc.completePendingCommand();
//							}
//							fc.changeToParentDirectory();
//						}
//					}
//					fc.changeToParentDirectory();
//				}
//			}
//			fc.disconnect();
//
//		} catch(Exception ex) {
//			log.error("拉取注单异常,{}", ex);
//		}
//		return recordList;
//	}
//
//	@Override
//	public String createOrderNumber() {
//		return cagent + System.currentTimeMillis();
//	}
//
//	@Override
//	public String kindName(String kindCode) {
//			return "AG平台";
//	}
//
//	private AgGameRecordVO parseGameRecord(String xmlData) {
//		InputSource in = new InputSource(new StringReader(xmlData));
//    	in.setEncoding("UTF-8");
//    	SAXReader reader = new SAXReader();
//		Document document;
//		AgGameRecordVO record = null;
//		try {
//			document = reader.read(in);
//			Element root = document.getRootElement();
//			if(root.attribute("dataType").getStringValue().equals("TR")) {
//				log.error("============属于转账类型，忽略============");
//				return null;
//			}
//			record = new AgGameRecordVO();
//			Class<?> recordClass = record.getClass();
//			Field[] fs = recordClass.getDeclaredFields();
//			for(Field f : fs) {
//				Attribute attribute = root.attribute(f.getName());
//				if(attribute != null) {
//					f.setAccessible(true);
//					f.set(record, attribute.getStringValue());
//				}
//			}
//
//		} catch(Exception ex) {
//			log.error("解析失败{}", ex);
//			return null;
//		}
//		return record;
//	}
//
//	@Override
//	public List<Object> gameList() {
//		return null;
//	}
//
//	@Override
//	public boolean makePullGameRecord(String startTime, String endTime) throws Exception {
//	    log.error("===AG开始补单===");
//	    List<GameRecord> recordList = new ArrayList<>();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
//        sdf2.setTimeZone(TimeZone.getTimeZone("GMT-4"));
//
//        //今天的记录文件夹，美东时间
//        String today = sdf2.format(sdf.parse(startTime));
//        String today2 = sdf2.format(sdf.parse(endTime));
//        FTPClient fc = new FTPClient();
//        try {
//            fc.connect(hostname, port); // 连接ftp服务器
//            fc.login(username, password); // 登录ftp服务器
//            int replyCode = fc.getReplyCode(); // 是否成功登录服务器
//            if (!FTPReply.isPositiveCompletion(replyCode)) {
//                log.error("============FTP连接失败============");
//                return true;
//            }
//          log.error("=====登录到ftp服务器,code:{}=====", replyCode);
//          fc.enterLocalPassiveMode();
//            FTPFile[] typeFiles =  fc.listFiles();
//            log.error("typeFiles.length：{}",typeFiles.length);
//            for(FTPFile typeFile : typeFiles) {
//                if(typeFile.isDirectory()) {
//                    log.error("============入进目录{}============", fc.printWorkingDirectory() + typeFile.getName());
//                    fc.changeWorkingDirectory(fc.printWorkingDirectory() + typeFile.getName());
//                  fc.enterLocalPassiveMode();
//                    FTPFile[] dateFiles =  fc.listFiles();
//                    for(FTPFile dateFile : dateFiles) {
//                        if(dateFile.isDirectory() && (dateFile.getName().equals(today) || dateFile.getName().equals(today2))) {
//                            log.error("============入进目录{}============", fc.printWorkingDirectory() + "/" + dateFile.getName());
//                            fc.changeWorkingDirectory(fc.printWorkingDirectory() + "/" + dateFile.getName());
//                          fc.enterLocalPassiveMode();
//                            FTPFile[] files =  fc.listFiles();
//                            for(FTPFile file : files) {
//                                log.error("============解析文件{}============", file.getName());
//                                InputStream in = fc.retrieveFileStream(file.getName());
//                                if (in != null) {
//                                  BufferedReader br = new BufferedReader(new InputStreamReader(in));
//                                  String xmlRowData = null;
//                                  while ((xmlRowData = br.readLine()) != null) {
//                                      AgGameRecordVO agGameRecord = parseGameRecord(xmlRowData);
//                                      if(agGameRecord == null) {
//                                          continue;
//                                      }
//                                      AgPlatformRecord agPlatformRecord = new AgPlatformRecord();
//                                      agPlatformRecord.setChannelUniqueId(agGameRecord.getBillNo());
//                                      String playerName = agGameRecord.getPlayerName();
//                                      ChannelUserInfoVO channelUserInfoVO = accountService.getAccountInfoByChannelAccount(playerName);
//                                        if(channelUserInfoVO == null) {
//                                            log.error("{}用户不存在", playerName);
//                                            continue;
//                                        }
//                                        agPlatformRecord.setAccount(channelUserInfoVO.getAccounts());
//                                        agPlatformRecord.setParentId(channelUserInfoVO.getParentId());
//                                        agPlatformRecord.setUserId(channelUserInfoVO.getUserId());
//                                        agPlatformRecord.setChannelAccount(playerName);
//                                        agPlatformRecord.setGameId(channelUserInfoVO.getGameId());
//                                        agPlatformRecord.setGameCode(agGameRecord.getGameCode());
//                                        agPlatformRecord.setGameName(AgGameNameEnum.getValueByCode(agGameRecord.getGameType()));
//                                        agPlatformRecord.setTableName(agGameRecord.getTableCode());
//                                        agPlatformRecord.setDataType(agGameRecord.getDataType());
//                                        agPlatformRecord.setPlatformType(agGameRecord.getPlatformType());
//                                        agPlatformRecord.setGameType(agGameRecord.getGameType());
//                                        agPlatformRecord.setScore(Double.parseDouble(agGameRecord.getNetAmount()));
//                                        agPlatformRecord.setBetAmount(Double.parseDouble(agGameRecord.getValidBetAmount()));
//                                        switch(agPlatformRecord.getPlatformType()) {
//                                        case "XIN":
//                                        case "YOPLAY":
//                                            agPlatformRecord.setKindType(2);
//                                            break;
//                                        default:
//                                            switch(agPlatformRecord.getDataType()) {
//                                            case "EBR":
//                                                agPlatformRecord.setKindType(2);
//                                                break;
//                                            case "HSR":
//                                            case "HPR":
//                                                agPlatformRecord.setKindType(4);
//                                                break;
//                                            default:
//                                                agPlatformRecord.setKindType(2);
//                                                break;
//                                            }
//                                        }
//                                        sdf.setTimeZone(TimeZone.getTimeZone("GMT-4"));
//                                        Date startTime1 = null;
//                                        String startTimeStr = agGameRecord.getBetTime();
//                                        try {
//                                            startTime1 = sdf.parse(startTimeStr);
//                                            sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
//                                            startTimeStr = sdf.format(startTime1);
//                                            agPlatformRecord.setStartTime(startTimeStr);
//                                            agPlatformRecord.setStartTimestamp(sdf.parse(startTimeStr).getTime());
//                                        } catch(Exception e) {
//
//                                        }
//
//                                        sdf.setTimeZone(TimeZone.getTimeZone("GMT-4"));
//                                        Date endTime1 = null;
//                                        String endTimeStr = agGameRecord.getRecalcuTime();
//                                        try {
//                                            endTime1 = sdf.parse(endTimeStr);
//                                            sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
//                                            endTimeStr = sdf.format(endTime1);
//                                            agPlatformRecord.setEndTime(endTimeStr);
//                                        } catch(Exception e) {
//
//                                        }
//                                        agPlatformRecord.setDetail(xmlRowData);
//                                        agPlatformRecord.setKindId(10002);
//                                        recordList.add(agPlatformRecord);
//                                  }
//                                  in.close();
//                                }
//                                fc.completePendingCommand();
//                            }
//                            fc.changeToParentDirectory();
//                        }
//                    }
//                    fc.changeToParentDirectory();
//                }
//            }
//            fc.disconnect();
//
//        } catch(Exception ex) {
//            log.error("拉取注单异常,{}", ex);
//        }
//        System.err.println("recordList.size():"+recordList.size());
//        if(recordList.size() > 0) {
//            if(!UgChannelGameApplication.isProd) {
//                return false;
//            }
//            for(GameRecord item : recordList) {
//                String channelUniqueId = item.getChannelUniqueId();
//                if(item.getStartTime() == null) {
//                    log.error("记录：" + channelUniqueId + "StartTime字段不能为空");
//                    continue;
//                }
//                Integer count = treasureServiceClient.gameRecordCountByChannelUniqueId(channelUniqueId);
//				if(count > 0) {
//					log.error("记录：" + channelUniqueId + "已存在");
//				} else {
//					Integer dbRet = treasureServiceClient.saveGameRecord(item);
//					if(dbRet < 0) {
//						log.info("记录{}已存在", item.getChannelUniqueId());
//					}
//				}
//                String roomName = "";
//                if(!StringUtils.isBlank(item.getRoomName())){
//                    roomName = item.getRoomName();
//                }
//                String gameName = "";
//                if(!StringUtils.isBlank(item.getGameName())){
//                    gameName = item.getGameName();
//                }
//                String gameCode = "";
//                if(!StringUtils.isBlank(item.getGameCode())){
//                    gameCode = item.getGameCode();
//                }
//                BigDecimal bdRe = new BigDecimal("0");
//                if(0 != item.getRevenue()) {
//                    bdRe = new BigDecimal(String.valueOf(item.getRevenue())).setScale(2, BigDecimal.ROUND_DOWN);
//                }
//                JSONObject retJson = accountsServiceClient.channelGameWriteScore(item.getUserId(), item.getParentId(), 10002, new BigDecimal(String.valueOf(item.getBetAmount())).setScale(2, BigDecimal.ROUND_DOWN), item.getChannelUniqueId(), item.getStartTime(),item.getKindType(),new BigDecimal(String.valueOf(item.getScore())).setScale(2, BigDecimal.ROUND_DOWN),
//                        gameName,gameCode,roomName,item.getChannelAccount(),bdRe);
//                log.error("调用结算存过{}", retJson);
//                if(retJson != null) {
//                    Integer ret = retJson.getInteger("ret");
//                    if(ret != null && ret.equals(0)) {
//                        nativeWebUtil.activityBetAmountAdvanceByThird(item.getUserId(),item.getParentId(),new BigDecimal(String.valueOf(item.getBetAmount())).setScale(2, BigDecimal.ROUND_DOWN),item.getStartTime(),item.getKindType(),10002);
//                        //gameRecordOtherESUtil.saveEsGameRecordOther(item.getChannelUniqueId());
//                        cleanShipUtil.insertCleanShipBet(item.getUserId(), item.getParentId(),new BigDecimal(String.valueOf(item.getBetAmount())).setScale(2, BigDecimal.ROUND_DOWN), 10002, item.getKindType());
//                        log.error("addUserInChannelGame结果：{}", accountsServiceClient.addUserInChannelGame(item.getParentId(), item.getUserId(), 10002, item.getChannelAccount(), new BigDecimal(String.valueOf(item.getScore())).setScale(2, BigDecimal.ROUND_DOWN), 1));
//                    }
//                }
//            }
//        }
//        log.error("===AG结束始补单===");
//        return true;
//	}
//
//	@Override
//	public boolean orderStatus(String orderNumber) {
//		String method = "qos";
//		String actype = "1";
//		String billno = orderNumber;
//		String cur = "CNY";
//		String p = "cagent="+cagent + "/\\\\/"+
// 			   "method="+method +"/\\\\/"+
// 			   "actype="+actype+"/\\\\/"+
// 			   "billno="+billno+"/\\\\/"+
// 			   "cur="+cur;
//		String params = DESUtil.encrypts(p, desKey);
//    	String key = MD5Util.MD5Encode(params + md5Key, "UTF-8");
//    	String param = "params="+params+"&key="+key;
//    	String res = HttpRequest.sendPost(url, param);
//    	log.info("查询订单状态第三方返回：" + res);
//    	InputSource in = new InputSource(new StringReader(res));
//    	in.setEncoding("UTF-8");
//    	SAXReader reader = new SAXReader();
//		Document document;
//		try {
//			document = reader.read(in);
//			Element root = document.getRootElement();
//	    	String f = root.attribute("info").getStringValue();
//	    	if(f.equals("0")) {
//	    		return true;
//	    	}
//		} catch (DocumentException e) {
//			e.printStackTrace();
//		}
//		return false;
//	}
//}
