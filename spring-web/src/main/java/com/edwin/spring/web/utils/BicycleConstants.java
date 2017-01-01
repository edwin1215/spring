package com.edwin.spring.web.utils;

public class BicycleConstants {

	// key前辍+phone,存储注册认证码
	public static final String REG_CODE = "AC_";

	// key前辍+phone,存储登陆验证码
	public static final String LOGIN_CODE = "LC_";

	// key前辍+phone,存储找回密码验证码
	public static final String RESETPWD_CODE = "RSP_";

	// key前辍+phone,存储绑定手机号验证码
	public static final String BAND_CODE = "BAND_";

	// 变更绑定手机号验证码
	public static final String CHGPHONE_CODE = "CHGP_";

	// key前辍+phone,存储修改手机号验证码
	public static final String UPDATE_PHONE_CODE = "UP_";

	// 已注册用户的key,存储已注册用户set
	public static final String REGISTERUSER_SET_KEY = "REGP_KEY";

	// 获取新用户id的key,通过redis的incr方法获取新用户id
	public static final String USER_ID_KEY = "UID_KEY";

	// 获取新群组id的key,通过redis的incr方法获取新组id
	public static final String GROUP_ID_KEY = "GID_KEY";

	// key前辍+phone,根据phone获取uid
	public static final String PHONE_KEY = "PHUID_";

	// key前辍+uid,存储已注册用户明细
	public static final String REGISTERUSER_INFO_KEY = "REGU_";

	// key前缀+uid, 临时存储用户上传的通讯录用户set，求临时set和已注册用户set的交集
	public static final String TEMPUSER_SET_KEY = "TREGP_";

	// key前辍+msgid,存储动态消息缩略缓存hash(uid,msgid,content缩略,msgPics,createTime)
	public static final String MSG_INFO_KEY = "MSG_";

	// 缩略消息内容字数
	public static final int SIMPLE_MSG_CONTENTLEN = 32;

	// 新歌声后台不显示赠送收取礼物明细的用户
	public static final String CONSUME_FORBID_USERS = "CONSUME_FORBID_USERS";

	// key前辍+uid,我拍禁言，String
	public static final String WOPAI_FORBID_WORD_KEY = "FBU_";

	// key前辍+uid,麦信通讯录黑名单set
	public static final String MYCHAT_BLACKLIST_KEY = "CBU_";

	// key前辍+uid,在zset存储动态消息id
	public static final String MSG_ID_KEY = "MSGID_";

	// key前辍+uid,存储新鲜事消息id缓存列表
	public static final String FRMSG_ID_KEY = "FRMSGID_";

	// key前辍+phone,存储好友信息缓存列表
	public static final String ACTIVITY_KEY = "ACTY_";

	// key前辍+uid，在zset存储所有好友的uid
	public static final String FRIEND_KEY = "FRI_";

	// key前辍+uid，在map存储所有好友的备注名和是否新标联系人信息(friendid,json对象)
	public static final String FRIENDMAP_KEY = "FRIM_";

	public static final String FRIENDREMARK_KEY = "FRIMZ_";

	// session key
	public static final String SES_KEY = "SES_";

	// key前辍+uid,存储离线消息hash(mid:消息jsonStr)
	public static final String OFFLINE_KEY = "OFFLINE_";

	// key前辍+uid,存储离线消息list(消息jsonStr)
	public static final String OFFLINE_LIST_KEY = "OFL_";
	// 好友推荐离线,存储list
	public static final String OFFLINE_RECOMMEND_KEY = "OFL_{0}_{1}";

	// key前辍+uid,存储已推送消息数量 key:String
	public static final String PUSHED_KEY = "PUSHED_";

	// key前辍+uid,苹果用户已收取消息id的set
	public static final String RECEIVED_KEY = "GOT_";

	// 排序id
	public static final String SMILEFACE_ID = "smfid";

	// key前辍+uid 收藏id前缀
	public static final String FAVID_KEY = "FAVID_";

	// key前辍+uid 赞消息前缀
	public static final String PRAMSGID_KEY = "PRAMSGID_";

	// 用户收藏动态消息的数量
	public static final int FAVMSG_COUNT = 50;

	// 用户最近发布动态消息图片
	public static final String RECENT_PICS = "RPIC_";

	// 用户最近发布动态消息缩略图
	public static final String RECENT_MINIPICS = "RMIPIC_";

	// 用户最近发布动态消息图片列表
	public static final String RECENT_PIC_LIST = "RPICL_";

	// 用户最近发布图片缓存数
	public static final int RECENT_PICS_COUNT = 3;

	// uid+分隔符+msgid,用于新鲜事value存值分隔
	public static final String UID_MSGID_SPLIT = "_";

	// 用户初始id
	public static final String USER_ID_INIT = "100000";

	// 用户表字段msgTotal
	public static final String MSG_TOTAL_FIELD = "msgTotal";

	// 直播观众key
	public static final String LIVE_VIEWER_COUNT = "LIVE_VIEWER_COUNT";
	// 直播点赞key
	public static final String LIVE_PRAISE_COUNT = "LIVE_PRAISE_COUNT";
	// 是否显示直播开关
	public static final String LIVE_SWITCH = "LIVE_SWITCH";
	// 直播推送开关
	public static final String LIVE_PUSH_SWITCH = "LIVE_PUSH_SWITCH";

	// cdn名称
	public static final String CDN_NAME = "CDN_NAME";

	// 用户状态
	public static final int USER_STATUS_NOACTIVE = 0;
	public static final int USER_STATUS_ACTIVE = 1;
	public static final int USER_STATUS_DISABLED = 2;

	// 每页记录数
	public static final int PAGE_SIZE = 10;

	// 用户动态消息摘要在hash中缓存的数量
	public static final int SIMPLEMSG_LIST_SIZE = 10;

	// 用户自己的动态消息在list中缓存的msgid数量
	public static final int MSG_LIST_SIZE = 30;

	// 用户新鲜事在list中缓存的msgid数量
	public static final int FRIENDMSG_LIST_SIZE = 30;

	// 动态消息id分隔符 uid+分隔符+msgNum
	public static final String MSG_ID_SPLIT = "-";

	// tokenid前缀
	public static final String TOKENID_KEY = "TOK_";

	public static final String ADDFRIEND_KEY = "ADDF_{0}_{1}";

	// 短信类型
	public static final int SMS_TYPE_REG = 1;
	public static final int SMS_TYPE_LOGIN = 2;
	public static final int SMS_TYPE_UPDATEPHONE = 3;
	public static final int SMS_TYPE_RESETPWD = 4;
	public static final int SMS_TYPE_ONEKEYREG = 5;
	public static final int SMS_TYPE_CHGPHONE = 6;

	// 用户关系表type
	public static final int M_USER_RELATION_TYPE = 1;

	// 0:主帖 1：评论
	public static final int MSG_TYPE_PUBLISH = 0;
	public static final int MSG_TYPE_REPLY = 1;

	// 0:赞 1：取消赞 2:已赞 3：已取消赞
	public static final int MSG_PRAISE_INSERT = 0;
	public static final int MSG_PRAISE_CANCLE = 1;
	public static final int MSG_PRAISE_INSERTED = 2;
	public static final int MSG_PRAISE_CANCLED = 3;

	// 是否在黑名单列表 0:不在 1:在
	public static final int NOTIN_BLACKLIST = 0;
	public static final int IN_BLACKLIST = 1;

	// 更新个人头像或签名时，发布的动态消息内容
	public static final String UPDATE_HEADPIC_CONTENT = "更新了头像";
	public static final String UPDATE_SIGNATRUE_CONTENT = "更新了签名";

	// 图片持久化或非持久化路径
	public static final String PERSISTENCE_IMG_PATH = "img/";
	public static final String TEMP_PIC_PATH = "pic/";

	// 苹果osVersion 前缀
	public static final String APPLE_OSVERSION_PREX = "ios_";

	// 分页标识0：第一页，1：向下翻页（取最新数据），2：向上翻页（取历史数据））
	public static final int PAGEFLAG_FRISTPAGE = 0;
	public static final int PAGEFLAG_DOWNPAGE = 1;
	public static final int PAGEFLAG_UPPAGE = 2;

	// 表情包状态
	public static final int PACKAGE_STATUS_ONLINE = 1;
	public static final int PACKAGE_STATUS_OFFLINE = 2;

	// 0：不清除缓存 1:清除缓存
	public static final int NO_CLEAR_CACHE = 0;
	public static final int CLEAR_CACHE = 1;
	// 清除缓存要达到的删除记录数
	public static final int CLEAR_CACHE_SIZE = 5;

	/**
	 * SES_$phone内容中存在用户sessionid的key
	 */
	public static final String USER_SESSIONID = "sessionid";

	/**
	 * 客户端请求时request header中携带的sessionid名称
	 */
	public static final String REQUEST_HEADER_SESSION_NAME = "sessionid";

	/**
	 * 等待回执的消息 -------------------------------------- | KEY1 | KEY2 | VALUE |
	 * -------------------------------------- | RECM_$uid | message_id | content
	 * | --------------------------------------
	 */
	public static final String MESSAGE_RECEIPTS_WAITING_KEY = "MSGREC_";

	public static final String ANDROID_CLIENT_VERSON = "ANDROID_VERSION";
	// android上一次强制升级的key
	public static final String ANDROID_CLIENT_VERSON_PRE = "ANDROID_VERSION_PREV";
	public static final String IOS_CLIENT_VERSON = "IOS_VERSION";
	// IOS上一次强制升级的key
	public static final String IOS_CLIENT_VERSON_PRE = "IOS_VERSION_PREV";
	public static final String ANDROID_CLIENT_OS = "android";
	public static final String IOS_CLIENT_OS = "ios";
	public static final int CLIENT_UPGRADE_TYPE_NOTHING = -1;
	public static final int CLIENT_UPGRADE_TYPE_IGONRE = 0;
	public static final int CLIENT_UPGRADE_TYPE_TIP = 1;
	public static final int CLIENT_UPGRADE_TYPE_FORCE = 2;

	// 用户一天内重置密码次数 到期key失效
	public static final String USER_REST_PWD_COUNT = "URPC_";
	// 用户一天内找回密码次数 到期key失效
	public static final String USER_FORGET_PWD_COUNT = "UFPC_";
	// 注册时发短信次数
	public static final String USER_REG_DX_COUNT = "URDXC_";
	// 修改绑定手机号发送短信次数
	public static final String USER_CHGP_DX_COUNT = "CHGPC_";

	// 是否生成缩略图片
	public static final int GENERATE_MINIPIC = 1;
	public static final int NOGENERATE_MINIPIC = 0;

	/**
	 * 群成员redis key 前缀
	 */
	public static final String GROUP_MEMBER_KEY = "GMEM_";
	public static final String GROUP_KEY = "GROU_";

	// key前缀+uid，存储某用户关注的人的id（set）
	public static final String ATTENTION_ID_OF = "ATTENTION_ID_OF_";

	// 存储蓝v用户id（set）
	public static final String VIP_OF_BLUE = "VIP_OF_BLUE";

	// 存储黄v用户id（set）
	public static final String VIP_OF_YELLOW = "VIP_OF_YELLOW";

	// 存储绿v用户id（set）
	public static final String VIP_OF_GREEN = "VIP_OF_GREEN";

	// 新增v用户id（set）
	public static final String NEW_VIP_AUTO_FOCUS = "NEW_VIP_AUTO_FOCUS";

	// 新视频自动评论（set）
	public static final String NEW_VIDEO_AUTO_COMMENT = "NEW_VIDEO_AUTO_COMMENT";

	// 自动评论的videoId前缀
	public static final String AUTO_COMMENT_VID = "AUTO_COMMENT_VID_";

	// 存储所有v用户（蓝v，黄v，绿v）id（zset）
	public static final String VIP_OF_ALL = "VIP_OF_ALL";

	// 存储新注册用户id（set）
	public static final String NEW_REGISTER_USER = "NEW_REGISTER_USER";

	// 存储马甲用户id（set）
	public static final String MAJIA_UID = "MAJIA_UID";

	// key前缀+uid，存储马甲用户信息（map）
	public static final String MAJIA = "MAJIA_";

	// key前缀+uid，存储用户信息（map）
	public static final String USER_INFO = "USER_INFO_";

	public static final String USER_TASK_STATUS = "USER_TASK_STATUS_";

	public static final String DAILY_TASK_MATCH = "DAILY_TASK_MATCH_";

	// 存储积分总数 key前缀+uid
	public static final String SION_SUM_BYDAY = "SION_SUM_BYDAY_";

	// 新用户标识
	public static final String NEW_USER_FLAG = "NEW_USER_FLAG_";

	// 存储热度排行榜视频(set)，每个视频对象以json串的方式存储
	public static final String HOT_VIDEO = "HOT_VIDEO";

	// key前缀+活动id，存储某个活动热度排行榜视频(set)，每个视频对象以json串的方式存储
	public static final String HOT_ACTIVITY_VIDEO = "HOT_ACTIVITY_VIDEO_";

	// 存储所有视频热度权重（map）
	public static final String HOT_WEIGHT_OF_ALL = "HOT_WEIGHT_OF_ALL";

	// 存储活动视频热度权重（map）
	public static final String HOT_WEIGHT_OF_ACTIVITY = "HOT_WEIGHT_OF_ACTIVITY";

	// 点赞权重(map中的field)
	public static final String PRIASE_WEIGHT = "praise_weight";
	// 评论时间权重(map中的field)
	public static final String EVALUATION_WEIGHT = "evaluation_weight";
	// 发布时间权重(map中的field)
	public static final String PUBLISH_WEIGHT = "publish_weight";
	// 播放次数权重(map中的field)
	public static final String PLAY_WEIGHT = "play_weight";

	// 当日最热视频榜权重（map）
	public static final String DAY_HOT_VIDEOS_WEIGHT = "DAY_HOT_VIDEOS_WEIGHT";

	// 当日播放次数权重(map中的field)
	public static final String DAY_PLAYCOUNT_WEIGHT = "day_playcount_weight";
	// 当日点赞次数权重(map中的field)
	public static final String DAY_PRAISECOUNT_WEIGHT = "day_praisecount_weight";
	// 当日评论次数权重(map中的field)
	public static final String DAY_EVALUATIONCOUNT_WEIGHT = "day_evaluationcount_weight";

	// 当日最热视频榜权重（map）
	public static final String DAY_POPULARITY_WEIGHT = "DAY_POPULARITY_WEIGHT";

	// 当日播放次数权重(map中的field)
	public static final String DAY_VIDEOCOUNT_WEIGHT = "day_videocount_weight";
	// 当日点赞次数权重(map中的field)
	public static final String DAY_FANSCOUNT_WEIGHT = "day_fanscount_weight";

	// 新浪token
	public static final String SINA_TOKEN = "sina_token";

	// key 当日最热视频排行，序列化对象存入
	public static final String DAY_HOT_VIDEOS = "DAY_HOT_VIDEOS";

	// key 当日最热视频排行，序列化对象存入
	public static final String HOT_VIDEOS_UPDATE_TIME = "HOT_VIDEOS_UPDATE_TIME";

	// key 当日用户人气排行榜，序列化对象存入
	public static final String DAY_USER_POPULARITY = "DAY_USER_POPULARITY";

	// key 当日用户人气排行榜更新时间
	public static final String USER_POPULARITY_UPDATE_TIME = "USER_POPULARITY_UPDATE_TIME";

	// 我拍小助手id，由于注册默认关注
	public static final String OFFICICAL_USER_ID = "OFFICICAL_USER_ID";

	// 邀请信息标示
	public static final String INVITE_INFO_WEIXIN_MARK = "INVITE_INFO_WEIXIN_MARK";// 微信好友
	public static final String INVITE_INFO_QZONE_MARK = "INVITE_INFO_QZONE_MARK";// qq空间
	public static final String INVITE_INFO_ADDRESS_MARK = "INVITE_INFO_ADDRESS_MARK";// 手机通讯录
	public static final String INVITE_INFO_QQ_MARK = "INVITE_INFO_QQ_MARK";// qq好友
	public static final String INVITE_INFO_SINA_MARK = "INVITE_INFO_SINA_MARK";// 新浪
	public static final String INVITE_INFO_WEIXIN_TIMELINE_MARK = "INVITE_INFO_WEIXIN_TIMELINE_MARK";// 微信朋友圈

	// 所有的url的redis存储的key
	public static final String ALL_URL_CONFIG_REDIS_KEY = "ALL_URL_CONFIG_REDIS_KEY";

	public static final String QIAN_DAO_GET_STR = "签到";// 签到获取
	public static final String FEN_XIANG_GET_STR = "分享";// 分享获取
	public static final int QIAN_DAO_GET = 1;// 签到获取
	public static final int FEN_XIANG_GET = 2;// 分享获取
	public static final int FEN_XIANG_GET_SIGN = 100;
	// url设置类型
	public static final String URL_CONFIG_VIDEO_CDN = "cdnurl";
	public static final String URL_CONFIG_VOICE = "voiceurl";
	public static final String URL_CONFIG_IMAGE = "imageurl";
	public static final String URL_CONFIG_INTERFACE = "interfaceurl";
	public static final String URL_CONFIG_VIDEO_UPLOADURL = "videouploadurl";

	// 设备配置信息版本
	public static final String DEVICE_CONFIG_VERSION = "device_config_version";
	// 设备配置信息url
	public static final String DEVICE_CONFIG_URL = "device_config_url";

	// 用户默认头像
	public static final String DEFAULT_HEAD_PIC = "DEFAULT_HEAD_PIC";

	// 用户默认背景图
	public static final String DEFAULT_HOME_PIC = "DEFAULT_HOME_PIC";

	// redis存储直播房间id的key
	public static final String ROOM_ORDER = "ROOM_ORDER";

	// 存储直播房间实体（map）的key前缀
	public static final String ROOM_ = "ROOM_";

	// 存储直播房间用户(id)列表（zset）的key前缀
	public static final String ROOM_USERS = "ROOM_USERS_";

	// 存储直播房间马甲(id)列表（set）的key前缀
	public static final String ROOM_MAJIAS = "ROOM_MAJIAS_";

	// 24小时收视排行榜
	public static final String TWENTY_FOUR_HOUR_HOT_VIDEOS = "TWENTY_FOUR_HOUR_HOT_VIDEOS";

	// 24小时收视排行榜更新时间
	public static final String TWENTY_FOUR_HOUR_HOT_VIDEOS_UPDATE_TIME = "TWENTY_FOUR_HOUR_HOT_VIDEOS_UPDATE_TIME";

	// 月收视排行榜
	public static final String MONTH_HOT_VIDEOS = "MONTH_HOT_VIDEOS";

	// 月收视排行榜更新时间
	public static final String MONTH_HOT_VIDEOS_UPDATE_TIME = "MONTH_HOT_VIDEOS_UPDATE_TIME";

	// 年收视排行榜
	public static final String YEAR_HOT_VIDEOS = "YEAR_HOT_VIDEOS";

	// 年收视排行榜更新时间
	public static final String YEAR_HOT_VIDEOS_UPDATE_TIME = "YEAR_HOT_VIDEOS_UPDATE_TIME";

	// 周收视排行榜
	public static final String WEEK_HOT_VIDEOS = "WEEK_HOT_VIDEOS";

	// 周收视排行榜更新时间
	public static final String WEEK_HOT_VIDEOS_UPDATE_TIME = "WEEK_HOT_VIDEOS_UPDATE_TIME";

	public static final String WEEK_HOT_VIDEOS_TIME = "WEEK_HOT_VIDEOS_TIME";
	// 马甲id列表，有序
	public static final String KEY_ALL_MAJIA_UID = "ALL_MAJIA_UIDS_ASDPLSKDF_";

	// key 24小时用户人气排行榜，序列化对象存入
	public static final String TWENTY_FOUR_HOUR_USER_POPULARITY = "TWENTY_FOUR_HOUR_USER_POPULARITY";

	// key 24小时用户人气排行榜更新时间
	public static final String TWENTY_FOUR_HOUR_USER_POPULARITY_UPDATE_TIME = "TWENTY_FOUR_HOUR_USER_POPULARITY_UPDATE_TIME";

	// key 周用户人气排行榜，序列化对象存入
	public static final String WEEK_USER_POPULARITY = "WEEK_USER_POPULARITY";

	// key 周用户人气排行榜更新时间
	public static final String WEEK_USER_POPULARITY_UPDATE_TIME = "WEEK_USER_POPULARITY_UPDATE_TIME";

	// key 月用户人气排行榜，序列化对象存入
	public static final String MONTH_USER_POPULARITY = "MONTH_USER_POPULARITY";

	// key 月用户人气排行榜更新时间
	public static final String MONTH_USER_POPULARITY_UPDATE_TIME = "MONTH_USER_POPULARITY_UPDATE_TIME";

	// key 年用户人气排行榜，序列化对象存入
	public static final String YEAR_USER_POPULARITY = "YEAR_USER_POPULARITY";

	// key 年用户人气排行榜更新时间
	public static final String YEAR_USER_POPULARITY_UPDATE_TIME = "YEAR_USER_POPULARITY_UPDATE_TIME";

	// KEY 24小时人气榜所取时间参数
	public static final String TWENTY_FOUR_HOUR_HOT_VIDEOS_TIME_PARAMS = "TWENTY_FOUR_HOUR_HOT_VIDEOS_TIME_PARAMS";
	// key 敏感词库（set）
	public static final String SENSITIVE_WORDS = "SENSITIVE_WORDS";

	// 存放礼物id的set，score为礼物的权重
	public static final String GIFT_ID = "GIFT_ID";

	// 存放苹果支付对应的钻石id的set，score为钻石的权重
	public static final String DIAMOND_APPLE_ID = "DIAMOND_APPLE_ID";

	// 存放大额支付对应的钻石id的set，score为钻石的权重
	public static final String DIAMOND_BIGCHARGE_ID = "DIAMOND_BIGCHARGE_ID";

	// 存放应用宝支付对应的钻石id的set，score为钻石的权重
	public static final String DIAMOND_YINGYONGBAO_ID = "DIAMOND_YINGYONGBAO_ID";

	// 存放其他支付对应的钻石id的set，score为钻石的权重
	public static final String DIAMOND_OTHER_ID = "DIAMOND_OTHER_ID";

	// 存放礼物信息的key（key+礼物id）
	public static final String GIFT = "GIFT_";

	// 用户当日送出免费礼物数（key+uid+_giftId)
	public static final String DAY_FREE_GIFT = "FREE_GIFT_COUNT_";

	// 存放钻石信息的key（key+钻石id）
	public static final String DIAMOND = "DIAMOND_";

	// 支付渠道（android）
	public static final String PAY_CHANNEL_ANDROID = "PAY_CHANNEL_ANDROID";

	// 支付渠道（ios）
	public static final String PAY_CHANNEL_IOS = "PAY_CHANNEL_IOS";

	// 返回的热门标签的个数
	public static final String HOT_LABLE_COUNT = "HOT_LABLE_COUNT";

	// 主播认证短信验证码
	public static final String ANCHOR_IDENTIFY_CODE = "ANCHOR_IDENTIFY_CODE_";

	// 校园招募报名验证码
	public static final String SCHOOL_REGISTER_CODE = "SCHOOL_REGISTER_CODE_";

	// 足球宝贝验证码
	public static final String FOOTBALL_GIRL_CODE = "FOOTBALL_GIRL_CODE";

	// 直播公告
	public static final String LIVE_NOTICE = "LIVE_NOTICE";

	// 被自动点赞的用户id（sorted set key）
	public static final String AUTO_ATTENTION_UID = "AUTO_ATTENTION_UID";

	// 某人的马甲粉丝id
	public static final String MAJIA_FANS_ID_OF_ = "MAJIA_FANS_ID_OF_";

	//
	public static final String ATTENTION_MAJIA_ID_OF_ = "ATTENTION_MAJIA_ID_OF_";
	// zset key+anchorUid 直播贡献者列表，按贡献值排序
	public static final String LIVE_CONTRIBUTION_LIST = "LIVE_CONTRIBUTION_LIST_";

	// zset key=key+roomId_senderId value=giftId score=sum(number) 可连续赠送礼物记录
	public static final String LOOP_GIFT_RECORD = "LOOP_GIFT_RECORD_";

	// zset key+userId score
	public static final String RECOMMEND_USER_ID = "RECOMMEND_USER_ID";// 首页推荐用户ID

	// zset key+userId score
	public static final String SEARCH_RECOMMEND_USER_ID = "SEARCH_RECOMMEND_USER_ID";// 搜索页推荐用户ID

	// zset score
	public static final String VOICE_RECOMMEND_USER_ID = "VOICE_RECOMMEND_USER_ID"; // 新歌声专区推荐用户ID

	// set
	public static final String BLACK_LIST_USER_ID = "BLACK_LIST_USER_ID_";

	// 热门分类ID(对应搜索页热门视频)
	public static final String HOT_CATEGORY_ID = "HOT_CATEGORY_ID";

	// 每天最大同时直播主播人数
	public static final String MAX_ANCHOR_BY_DAY_ = "MAX_ANCHOR_BY_DAY_";

	// 专属礼物记录（豆）
	public static final String EXCLUSIVE_GIFT_RECORD_ = "EXCLUSIVE_GIFT_RECORD_";

	// 专属礼物记录（币）
	public static final String EXCLUSIVE_GIFT_RECORD_DIAMOND_ = "EXCLUSIVE_GIFT_RECORD_DIAMOND_";

	// 直播活动对象
	public static final String LIVE_ACTIVITY_ = "LIVE_ACTIVITY_";

	// 每场直播的礼物数
	public static final String GIFT_NUMBER_BY_ROOM_ = "GIFT_NUMBER_BY_ROOM_";

	// 每场直播收到的金豆数
	public static final String POINT_NUMBER_BY_ROOM_ = "POINT_NUMBER_BY_ROOM_";

	// 主播金豆分成比例
	public static final String DIVIDE_RATE = "DIVIDE_RATE";
	// 主播封号key=key+userId value=timestamp封号结束
	public static final String LOCK_LIVE_USER = "LOCK_LIVE_USER_";

	// 存放兑换信息id的set，score 权重
	public static final String EXCHANGE_ID = "EXCHANGE_ID";

	// 存放兑换信息的key（key+id）
	public static final String EXCHANGE = "EXCHANGE_";

	// 存放兑换信息的key（key+id）
	public static final String WITHDRAW_CASH = "WITHDRAW_CASH_";

	// 存放提现信息id的set，score 权重
	public static final String WITHDRAW_CASH_ID = "WITHDRAW_CASH_ID";

	// 分享次数
	public static final String SHARE_LIVE_TIME = "SHARE_LIVE_TIME_";

	// 种草莓次数
	public static final String PLANT_BERRY_TIME = "PLANT_BERRY_TIME";

	// 存储分享赠送金币（map）的key前缀
	public static final String SHARE_GIVE_GOLD = "SHARE_GIVE_GOLD";

	// 存储首次直播赠送金币（map）的key前缀
	public static final String FIRST_LIVE_GIVE_GOLD = "FIRST_LIVE_GIVE_GOLD";

	// sortedset key+roomId(message,timestamp)
	public static final String LIVE_MESSAGE_QUEUE = "LIVE_MESSAGE_QUEUE_";

	// sortedset key+roomId(message,timestamp)
	public static final String LIVE_PRAISE_MESSAGE_QUEUE = "LIVE_PRAISE_MESSAGE_QUEUE_";

	// sortedset key+roomId(message,timestamp)
	public static final String LIVE_GIFT_MESSAGE_QUEUE = "LIVE_GIFT_MESSAGE_QUEUE_";

	// 消息队列大小
	public static final String LIVE_MESSAGE_COUNT = "LIVE_MESSAGE_COUNT";

	// 点赞消息队列大小
	public static final String LIVE_PRAISE_MESSAGE_COUNT = "LIVE_PRAISE_MESSAGE_COUNT";

	// 礼物消息队列大小
	public static final String LIVE_GIFT_MESSAGE_COUNT = "LIVE_GIFT_MESSAGE_COUNT";

	// 首播送金币开关
	public static final String GIVE_DIAMOND_OF_LIVE = "GIVE_DIAMOND_OF_LIVE";

	// 分享送金币开关
	public static final String GIVE_DIAMOND_OF_SHARE = "GIVE_DIAMOND_OF_SHARE";

	// 存放违规(涉黄暴恐)信息key SET
	public static final String IRREGULARITY_IMAGE = "IRREGULARITY_IMAGE";

	// 存放违规(涉黄暴恐)信息key Map
	public static final String IRREGULARITY_IMAGE_STREAMID = "IRREGULARITY_IMAGE_STREAMID_";

	// xx小时金豆榜用户id
	public static final String TOP_POINT_USER_ID = "TOP_POINT_USER_ID";

	// set 房间内用户分布的服务器列表
	public static final String ROOM_SERVERURLS = "ROOM_SERVERURLS_";

	public static final String RECOMMEND_INTERVAL = "RECOMMEND_INTERVAL";

	// 审核未通过的房间id
	public static final String CHECK_FAIL_ROOM_ID = "CHECK_FAIL_ROOM_ID";

	// 探针开关
	public static final String PING_SWITCH = "PING_SWITCH";

	// 待审核的用户上传的图片（头像，背景图）
	public static final String TO_BE_VERIFIED_PIC = "TO_BE_VERIFIED_PIC";

	// 保存房间截图key ZSET
	public static final String ROOM_SCREENSHOT_ID = "ROOM_SCREENSHOT_ID_";

	// 保存审核组成员信息
	public static final String CHECK_GROUP = "CHECK_GROUP";

	// 当前审核组成员在线数量
	public static final String CHECK_GROUP_COUNT = "CHECK_GROUP_COUNT";

	// 保存当前时间点最新直播房间ID
	public static final String NEW_LIVE_ROOM = "NEW_LIVE_ROOM";

	// 保存最新直播房间ID
	public static final String NEWEST_LIVE_ROOM = "NEWEST_LIVE_ROOM";

	// 审核账户组ID key
	public static final String CHECK_GROUP_ID = "CHECK_GROUP_ID";

	// 推广公司类型 有米
	public static final String YOU_MI = "youmi";

	// 推广公司类型 有米
	public static final String XING_ZHE = "xingzhe";

	// 保存审核管理组成员信息
	public static final String CHECK_ADMIN_GROUP_ID = "CHECK_ADMIN_GROUP_ID";

	public static final String REQUEST_USER_ = "REQUEST_USER_";

	// 直播回放保存的后缀名（仅对ucloud有效）
	public static final String PLAY_BACK_SUFFIX = "PLAY_BACK_SUFFIX";

	// 360特殊渠道赠送金币开关
	public static final String REGISTER_360_SWITCH = "REGISTER_360_SWITCH";

	// 存储分享赠送金币按钮（map）的key前缀
	public static final String SHARE_GIVE_GOLD_BUTTON = "SHARE_GIVE_GOLD_BUTTON";

	// 直播认证开关
	public static final String IDENTIFY_SWITCH = "IDENTIFY_SWITCH";
	public static final String IDENTIFY_TYPE = "IDENTIFY_TYPE";

	// 直播认证开关
	public static final boolean VIDEO_BACK_FLG = false;
	// key 直播马甲设置(map) key+Id
	public static final String LIVE_SETTING = "LIVE_SETTING_";
	// key 马甲自动发言词库(zset) key+uid_typeId eg. AUTOCHAT_0_0 as default,content
	// (value,score) (word,id)
	public static final String AUTOCHAT_WORDS = "AUTOCHAT_";
	// key 在某个房间被某个马甲使用的词(k,v) key+roomId_majiaid_wordid(score),expire 5min
	public static final String USED_AUTOCHAT_WORDS = "USED_AUTOCHAT_";

	// 客户端验证开关
	public static final String CLIENT_VALIDATE_SWITCH = "CLIENT_VALIDATE_SWITCH";

	// 主播日榜
	public static final String ANCHOR_DAY_RANKING_ZSET = "ANCHOR_DAY_RANKING_ZSET";

	// 主播周榜
	public static final String ANCHOR_WEEK_RANKING_ZSET = "ANCHOR_WEEK_RANKING_ZSET";

	// 主播月榜
	public static final String ANCHOR_MONTH_RANKING_ZSET = "ANCHOR_MONTH_RANKING_ZSET";

	// 主播总榜
	public static final String ANCHOR_ALL_RANKING_ZSET = "ANCHOR_ALL_RANKING_ZSET";

	// 主播日榜
	public static final String RICH_DAY_RANKING_ZSET = "RICH_DAY_RANKING_ZSET";

	// 主播周榜
	public static final String RICH_WEEK_RANKING_ZSET = "RICH_WEEK_RANKING_ZSET";

	// 主播月榜
	public static final String RICH_MONTH_RANKING_ZSET = "RICH_MONTH_RANKING_ZSET";

	// 主播总榜
	public static final String RICH_ALL_RANKING_ZSET = "RICH_ALL_RANKING_ZSET";

	// B星名称设置
	public static final String B_STAR_NAME = "B_STAR_NAME";

	// 首次直播天数设置
	public static final String FIRST_LIVE_TIMES = "FIRST_LIVE_TIMES";

	// 敏感词库修改次数，每次判断敏感词，会用当前的次数和此次数进行对比，如果不一致，reload敏感词
	public static final String SENSITIVE_WORD_UPDATE_COUNT = "SENSITIVE_WORD_UPDATE_COUNT";

	// Speradmin 白名单<set>
	public static final String SUPERADMINS = "SUPERADMINS";

	// 一周首播用户key
	public static final String FIRST_LIVE_USER_INFO = "FIRST_LIVE_USER_INFO";

	// 更换绑定手机号验证
	public static final String CHANGE_BAND_CHECK_ = "CHANGE_BAND_CHECK_";
	// Set 房间用户列表置顶白名单
	public static final String ROOM_USERS_TOPLIST = "ROOM_USERS_TOPLIST";
	// 用户最后心跳时间
	public static final String USER_LAST_HARTBEAT = "USER_LAST_HARTBEAT";

	// 密码错误次数
	public static final String PWD_WRONG_TIMES_ = "PWD_WRONG_TIMES_";

	// 连麦主播推流地址(key)
	public static final String LINK_MIC_PUSH_MAIN = "LINK_MIC_PUSH_MAIN";

	// 连麦用户1的推流地址(key)
	public static final String LINK_MIC_PUSH_ONE = "LINK_MIC_PUSH_ONE";

	// 连麦用户2的推流地址(key)
	public static final String LINK_MIC_PUSH_SECOND = "LINK_MIC_PUSH_SECOND";

	// 连麦主播拉流地址(key)
	public static final String LINK_MIC_PULL_MAIN = "LINK_MIC_PULL_MAIN";

	// 连麦用户1的拉流地址(key)
	public static final String LINK_MIC_PULL_ONE = "LINK_MIC_PULL_ONE";

	// 连麦用户2的拉流地址(key)
	public static final String LINK_MIC_PULL_SECOND = "LINK_MIC_PULL_SECOND";

	// 连麦拉流密码(key)
	public static final String LINK_MIC_PULL_PWD = "LINK_MIC_PULL_PWD";

	// 连麦主播对应两个两个位置的用户id（map）
	public static final String LINK_MIC_PU = "LINK_MIC_PU_";

	// 屏蔽主播key=key+userId value=timestamp expire
	public static final String SHIELD_LIVE_USER = "SHIELD_LIVE_USER_";

	// 连麦白名单 zset
	public static final String CONNECT_MICROPHONE_WHITE_LIST = "CONNECT_MICROPHONE_WHITE_LIST";

	// 开启连麦金币数
	public static final String CONNECT_MICROPHONE_DIAMONDS = "CONNECT_MICROPHONE_DIAMONDS";

	// 聊天室消息队列0：房间ID%3==0
	public static final String ROOM_MSG_QUEUE0 = "ROOM_MSG_QUEUE0";

	// 聊天室消息队列0：房间ID%3==1
	public static final String ROOM_MSG_QUEUE1 = "ROOM_MSG_QUEUE1";

	// 聊天室消息队列0：房间ID%3==2
	public static final String ROOM_MSG_QUEUE2 = "ROOM_MSG_QUEUE2";

}
