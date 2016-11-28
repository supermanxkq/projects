package com.paySystem.ic.utils;

import java.util.ArrayList;
import java.util.List;

import com.paySystem.ic.web.ui.OptionsInteger;
import com.paySystem.ic.web.ui.OptionsString;

public class OptionsValue {
	public static List<OptionsInteger> Advertma_mediaType = new ArrayList<OptionsInteger>();
	static {
		Advertma_mediaType.add(new OptionsInteger(0, "图片"));
		Advertma_mediaType.add(new OptionsInteger(1, "文字"));
		Advertma_mediaType.add(new OptionsInteger(9, "删除"));
	}
	public static List<OptionsInteger> Advertma_addmediaType = new ArrayList<OptionsInteger>();
	static {
		Advertma_addmediaType.add(new OptionsInteger(0, "图片"));
		Advertma_addmediaType.add(new OptionsInteger(1, "文字"));
	}
	public static List<OptionsInteger> Advertma_location = new ArrayList<OptionsInteger>();
	static {
		Advertma_location.add(new OptionsInteger(0, "站外广告"));
		Advertma_location.add(new OptionsInteger(1, "站内广告"));
	}
	/** 是否状态 */
	public static List<OptionsInteger> VISIBLE_STATUS = new ArrayList<OptionsInteger>();
	static {
		VISIBLE_STATUS.add(new OptionsInteger(0, "否"));
		VISIBLE_STATUS.add(new OptionsInteger(1, "是"));
	}
	public static List<OptionsInteger> Announ_STATUS = new ArrayList<OptionsInteger>();
	static {
		Announ_STATUS.add(new OptionsInteger(0, "未发布"));
		Announ_STATUS.add(new OptionsInteger(1, "发布"));
		Announ_STATUS.add(new OptionsInteger(9, "删除"));
	}
	public static List<OptionsInteger> AddAnnoun_STATUS = new ArrayList<OptionsInteger>();
	static {
		AddAnnoun_STATUS.add(new OptionsInteger(0, "未发布"));
		AddAnnoun_STATUS.add(new OptionsInteger(1, "发布"));
	}
	public static List<OptionsInteger> Announ_UpdateSTATUS = new ArrayList<OptionsInteger>();
	static {
		Announ_UpdateSTATUS.add(new OptionsInteger(0, "未发布"));
		Announ_UpdateSTATUS.add(new OptionsInteger(1, "发布"));
	}
	public static List<OptionsInteger> Announ_IsTop = new ArrayList<OptionsInteger>();
	static {
		Announ_IsTop.add(new OptionsInteger(0, "否"));
		Announ_IsTop.add(new OptionsInteger(1, "是"));
	}

	/** 是否状态 */
	public static List<OptionsInteger> VISIBLE_STATUS_CONV = new ArrayList<OptionsInteger>();
	static {
		VISIBLE_STATUS_CONV.add(new OptionsInteger(1, "是"));
		VISIBLE_STATUS_CONV.add(new OptionsInteger(0, "否"));
	}


	/**
	 * 账户类型种类 public static List<OptionsInteger> ACCTYPEID_KINDS = new
	 * ArrayList<OptionsInteger>(); static { ACCTYPEID_KINDS.add(new
	 * OptionsInteger(0, "油币账户")); ACCTYPEID_KINDS.add(new OptionsInteger(1,
	 * "一般账户")); ACCTYPEID_KINDS.add(new OptionsInteger(2, "积分账户"));
	 * ACCTYPEID_KINDS.add(new OptionsInteger(3, "保证金账户")); }
	 */

	/** 性别 */
	public static List<OptionsInteger> SEX_STATUS = new ArrayList<OptionsInteger>();
	static {
		SEX_STATUS.add(new OptionsInteger(1, "男"));
		SEX_STATUS.add(new OptionsInteger(2, "女"));
	}

	/** 是否绑定会员 */
	public static List<OptionsInteger> MEM_SIGN = new ArrayList<OptionsInteger>();
	static {
		MEM_SIGN.add(new OptionsInteger(0, "未绑定"));
		MEM_SIGN.add(new OptionsInteger(1, "绑定"));
	}

	/** 是否启用密码 */
	public static List<OptionsInteger> PWD_SIGN = new ArrayList<OptionsInteger>();
	static {
		PWD_SIGN.add(new OptionsInteger(0, "不启用"));
		PWD_SIGN.add(new OptionsInteger(1, "启用"));
	}

	/** 启用状态 */
	public static List<OptionsInteger> STATE_STATUS = new ArrayList<OptionsInteger>();
	static {
		STATE_STATUS.add(new OptionsInteger(1, "启用"));
		STATE_STATUS.add(new OptionsInteger(0, "禁用"));
		STATE_STATUS.add(new OptionsInteger(9, "删除"));
	}
	public static List<OptionsInteger> STATE_STATUSD = new ArrayList<OptionsInteger>();
	static {
		STATE_STATUSD.add(new OptionsInteger(1, "启用"));
		STATE_STATUSD.add(new OptionsInteger(0, "禁用"));
	}

	/** 启用状态 -作为查询条件 */
	public static List<OptionsInteger> COND_STATE = new ArrayList<OptionsInteger>();
	static {
		COND_STATE.add(new OptionsInteger(2, "全部"));
		COND_STATE.add(new OptionsInteger(1, "启用"));
		COND_STATE.add(new OptionsInteger(0, "禁用"));
		COND_STATE.add(new OptionsInteger(9, "删除"));
	}

	/** 启用状态 */
	public static List<OptionsInteger> ACC_KIND_STATUS = new ArrayList<OptionsInteger>();
	static {
		ACC_KIND_STATUS.add(new OptionsInteger(0, "启用"));
		ACC_KIND_STATUS.add(new OptionsInteger(1, "禁用"));
		ACC_KIND_STATUS.add(new OptionsInteger(2, "删除"));
		ACC_KIND_STATUS.add(new OptionsInteger(3, "冻结"));
	}

	/** 商户状态 */
	public static List<OptionsInteger> MERCHANT_STATUS = new ArrayList<OptionsInteger>();
	static {
		MERCHANT_STATUS.add(new OptionsInteger(0, "新开户"));
		MERCHANT_STATUS.add(new OptionsInteger(1, "正常"));
		MERCHANT_STATUS.add(new OptionsInteger(2, "锁定"));
		MERCHANT_STATUS.add(new OptionsInteger(3, "撤销"));
		MERCHANT_STATUS.add(new OptionsInteger(9, "删除"));
	}

	/** 结算方式 */
	public static List<OptionsInteger> SETTLEMENT_STATUS = new ArrayList<OptionsInteger>();
	static {
		SETTLEMENT_STATUS.add(new OptionsInteger(0, "按消费金额"));
		SETTLEMENT_STATUS.add(new OptionsInteger(1, "按交易笔数"));
		SETTLEMENT_STATUS.add(new OptionsInteger(2, "无需结算"));
	}

	/** 计算结算金额方式 */
	public static List<OptionsInteger> SETTLE_COUNT_WAY = new ArrayList<OptionsInteger>();
	static {
		SETTLE_COUNT_WAY.add(new OptionsInteger(0, "按实际金额"));
		SETTLE_COUNT_WAY.add(new OptionsInteger(1, "按原金额"));
	}

	/** 证件类型 */
	public static List<OptionsInteger> PERSONID_TYPE = new ArrayList<OptionsInteger>();
	static {
		PERSONID_TYPE.add(new OptionsInteger(1, "身份证"));
		PERSONID_TYPE.add(new OptionsInteger(2, "军官证"));
		PERSONID_TYPE.add(new OptionsInteger(3, "护照"));
	}

	/** 换卡原因 */
	public static List<OptionsInteger> CHANGE_REASON = new ArrayList<OptionsInteger>();
	static {
		CHANGE_REASON.add(new OptionsInteger(1, "损坏"));
		CHANGE_REASON.add(new OptionsInteger(2, "挂失"));
		CHANGE_REASON.add(new OptionsInteger(3, "其他"));
	}

	/** 登陆状态 */
	public static List<OptionsInteger> LOGIN_FLAG = new ArrayList<OptionsInteger>();
	static {
		LOGIN_FLAG.add(new OptionsInteger(1, "登陆"));
		LOGIN_FLAG.add(new OptionsInteger(0, "未登陆"));
	}

	/** 用户级别 */
	public static List<OptionsInteger> USER_LEVEL = new ArrayList<OptionsInteger>();
	static {
		USER_LEVEL.add(new OptionsInteger(0, "总部"));
		USER_LEVEL.add(new OptionsInteger(1, "发卡机构"));
		USER_LEVEL.add(new OptionsInteger(2, "商户"));
		/* 用户等级新增：炼油厂用户 */
		USER_LEVEL.add(new OptionsInteger(3, "炼油厂"));
	}

	/** 用户级别 */
	public static List<OptionsInteger> USER_LEVEL1 = new ArrayList<OptionsInteger>();
	static {
		USER_LEVEL1.add(new OptionsInteger(1, "发卡机构"));
		USER_LEVEL1.add(new OptionsInteger(2, "商户"));
	}

	/** 账户类型归属 */
	public static List<OptionsInteger> ACC_DEPTTYPE = new ArrayList<OptionsInteger>();
	static {
		ACC_DEPTTYPE.add(new OptionsInteger(0, "通用"));
		ACC_DEPTTYPE.add(new OptionsInteger(1, "发卡机构"));
		ACC_DEPTTYPE.add(new OptionsInteger(2, "商户"));
	}

	/** 用户级别 */
	public static List<OptionsInteger> USER_LEVEL3 = new ArrayList<OptionsInteger>();
	static {
		USER_LEVEL3.add(new OptionsInteger(1, "发卡机构"));
	}

	/** 用户级别 */
	public static List<OptionsInteger> USER_LEVEL2 = new ArrayList<OptionsInteger>();
	static {
		USER_LEVEL2.add(new OptionsInteger(2, "商户"));
	}

	/** 用户级别 */
	public static List<OptionsInteger> USER_LEVEL4 = new ArrayList<OptionsInteger>();
	static {
		USER_LEVEL4.add(new OptionsInteger(3, "炼油厂"));
	}

	/** 账户级别 */
	public static List<OptionsInteger> ACCOUNT_FLAG = new ArrayList<OptionsInteger>();
	static {
		ACCOUNT_FLAG.add(new OptionsInteger(0, "通用"));
		ACCOUNT_FLAG.add(new OptionsInteger(1, "发卡机构"));
		ACCOUNT_FLAG.add(new OptionsInteger(2, "商户"));
	}

	/** 卡标志 */
	public static List<OptionsInteger> CARD_SW = new ArrayList<OptionsInteger>();
	static {
		CARD_SW.add(new OptionsInteger(0, "CPU卡连机"));
		CARD_SW.add(new OptionsInteger(1, "磁条卡"));
		CARD_SW.add(new OptionsInteger(2, "CPU卡脱机"));
	}

	/** 卡类型 */
	public static List<OptionsInteger> CARD_FLAG = new ArrayList<OptionsInteger>();
	static {
		CARD_FLAG.add(new OptionsInteger(0, "专卡"));
		CARD_FLAG.add(new OptionsInteger(1, "通卡"));
	}

	/** 卡状态 */
	public static List<OptionsInteger> CARDNO_STATUS = new ArrayList<OptionsInteger>();
	static {
		CARDNO_STATUS.add(new OptionsInteger(0, "新卡"));
		CARDNO_STATUS.add(new OptionsInteger(1, "已导出"));
		CARDNO_STATUS.add(new OptionsInteger(2, "等待确认"));
		CARDNO_STATUS.add(new OptionsInteger(3, "已入库"));
		CARDNO_STATUS.add(new OptionsInteger(9, "已删除"));
	}

	/** 卡显示位数 */
	public static List<OptionsInteger> CARD_LENGH_VIEW = new ArrayList<OptionsInteger>();
	static {
		CARD_LENGH_VIEW.add(new OptionsInteger(16, "16位"));
		CARD_LENGH_VIEW.add(new OptionsInteger(8, "8位"));
	}

	/** 商户充值限额类型 */
	public static List<OptionsInteger> MERCHANTPURCHASE_FLAG = new ArrayList<OptionsInteger>();
	static {
		MERCHANTPURCHASE_FLAG.add(new OptionsInteger(0, "固定"));
		MERCHANTPURCHASE_FLAG.add(new OptionsInteger(1, "灵活"));
	}

	/**
	 * 0总部入库 1发卡机构入库 2商户入库 3商户互相调拨 4发卡机构售卡 5商户售卡 6发卡机构售卡退回 7商户售卡退回 8会员退卡 9商户退卡
	 * 10发卡机构换卡 11商户换卡
	 */
	public static List<OptionsInteger> INVENTORYCHANGE_FLAG = new ArrayList<OptionsInteger>();
	static {
		INVENTORYCHANGE_FLAG.add(new OptionsInteger(0, "总部入库"));
		INVENTORYCHANGE_FLAG.add(new OptionsInteger(1, "发卡机构领卡"));
		INVENTORYCHANGE_FLAG.add(new OptionsInteger(2, "商户领卡"));
		INVENTORYCHANGE_FLAG.add(new OptionsInteger(3, "商户互相调拨"));
		INVENTORYCHANGE_FLAG.add(new OptionsInteger(4, "发卡机构售卡"));
		INVENTORYCHANGE_FLAG.add(new OptionsInteger(5, "商户售卡"));
		INVENTORYCHANGE_FLAG.add(new OptionsInteger(6, "发卡机构售卡退回"));
		INVENTORYCHANGE_FLAG.add(new OptionsInteger(7, "商户售卡退回"));
		INVENTORYCHANGE_FLAG.add(new OptionsInteger(8, "会员退卡(发卡机构)"));
		INVENTORYCHANGE_FLAG.add(new OptionsInteger(9, "会员退卡(商户)"));
		INVENTORYCHANGE_FLAG.add(new OptionsInteger(10, "商户退卡"));
	}

	/** 库存变动状态 */
	public static List<OptionsInteger> CARDENABLED_STATUS = new ArrayList<OptionsInteger>();
	static {
		CARDENABLED_STATUS.add(new OptionsInteger(0, "未激活"));
		CARDENABLED_STATUS.add(new OptionsInteger(1, "激活"));
	}

	/** 库存变动状态 */
	public static List<OptionsInteger> INVENTORYCHANGE_STATUS = new ArrayList<OptionsInteger>();
	static {
		INVENTORYCHANGE_STATUS.add(new OptionsInteger(0, "未入库"));
		INVENTORYCHANGE_STATUS.add(new OptionsInteger(1, "已入库"));
		INVENTORYCHANGE_STATUS.add(new OptionsInteger(2, "已售出"));
	}

	/** 商户结算Flag */
	public static List<OptionsInteger> MERSETTTOTAL_FLAG = new ArrayList<OptionsInteger>();
	static {
		MERSETTTOTAL_FLAG.add(new OptionsInteger(0, "未结算"));
		MERSETTTOTAL_FLAG.add(new OptionsInteger(1, "已结算"));

	}

	/** 卡状态：0入库、1出库、2发卡机构入库、3发卡机构出库、4商户入库、5商户出库、6待激活、7正常、8挂失、9已补卡、10已换卡、11已退卡。 */
	public static List<OptionsInteger> CARD_STATUS = new ArrayList<OptionsInteger>();
	static {
		CARD_STATUS.add(new OptionsInteger(0, "总部入库"));
		CARD_STATUS.add(new OptionsInteger(1, "总部出库"));
		CARD_STATUS.add(new OptionsInteger(2, "发卡机构入库"));
		CARD_STATUS.add(new OptionsInteger(3, "发卡机构出库"));
		CARD_STATUS.add(new OptionsInteger(4, "商户入库"));
		CARD_STATUS.add(new OptionsInteger(5, "商户出库"));
		CARD_STATUS.add(new OptionsInteger(6, "待激活"));
		CARD_STATUS.add(new OptionsInteger(7, "正常"));
		CARD_STATUS.add(new OptionsInteger(8, "挂失"));
		CARD_STATUS.add(new OptionsInteger(9, "已换卡"));
		CARD_STATUS.add(new OptionsInteger(10, "已退卡(发卡机构)"));
		CARD_STATUS.add(new OptionsInteger(11, "已退卡(商户)"));
	}

	/** 支付方式(0现金1银行卡2支票) */
	public static List<OptionsInteger> PAY_TYPE = new ArrayList<OptionsInteger>();
	static {
		PAY_TYPE.add(new OptionsInteger(0, "现金"));
		PAY_TYPE.add(new OptionsInteger(1, "转账"));
		PAY_TYPE.add(new OptionsInteger(2, "支票"));
	}

	/** 订单类别 0:小订单1:大订单 */
	public static List<OptionsInteger> ORDER_FLAG = new ArrayList<OptionsInteger>();
	static {
		ORDER_FLAG.add(new OptionsInteger(0, "小订单"));
		ORDER_FLAG.add(new OptionsInteger(1, "大订单"));
	}

	/** 发卡机构商户 */
	public static List<OptionsInteger> ORGANMER_FLAG = new ArrayList<OptionsInteger>();
	static {
		ORGANMER_FLAG.add(new OptionsInteger(1, "发卡机构"));
		ORGANMER_FLAG.add(new OptionsInteger(2, "商户"));
	}

	/** 0：待审核；1：待修改；2：等待确认；(针对支票到账)3：订单成功；4：订单失败； */
	public static List<OptionsInteger> ORDER_STATUS = new ArrayList<OptionsInteger>();
	static {
		ORDER_STATUS.add(new OptionsInteger(0, "待审核"));
		ORDER_STATUS.add(new OptionsInteger(1, "待修改"));
		ORDER_STATUS.add(new OptionsInteger(2, "等待确认"));
		ORDER_STATUS.add(new OptionsInteger(3, "订单成功"));
		ORDER_STATUS.add(new OptionsInteger(4, "订单失败"));
	}

	/** 审核状态 */
	public static List<OptionsInteger> AUDIT_STATUS = new ArrayList<OptionsInteger>();
	static {
		AUDIT_STATUS.add(new OptionsInteger(0, "未审核"));
		AUDIT_STATUS.add(new OptionsInteger(1, "审核通过"));
		AUDIT_STATUS.add(new OptionsInteger(2, "审核不通过"));
	}

	/*** 到账状态 **/
	public static List<OptionsInteger> CHEQUEDISPOSE_STATUS = new ArrayList<OptionsInteger>();
	static {
		CHEQUEDISPOSE_STATUS.add(new OptionsInteger(0, "未到账"));
		CHEQUEDISPOSE_STATUS.add(new OptionsInteger(1, "已到账"));
	}

	/** 订单类型 */
	public static List<OptionsInteger> TOACCOUNT_TYPE = new ArrayList<OptionsInteger>();
	static {
		TOACCOUNT_TYPE.add(new OptionsInteger(1, "售卡订单"));
		TOACCOUNT_TYPE.add(new OptionsInteger(2, "充值订单"));
	}

	public static List<OptionsInteger> NEWTRADESTYPE = new ArrayList<OptionsInteger>();
	static {
		NEWTRADESTYPE.add(new OptionsInteger(1, "退货"));
		NEWTRADESTYPE.add(new OptionsInteger(2, "充值"));
		NEWTRADESTYPE.add(new OptionsInteger(3, "充值冲正"));
		NEWTRADESTYPE.add(new OptionsInteger(4, "充值撤销"));
		NEWTRADESTYPE.add(new OptionsInteger(5, "充值撤销冲正"));
		NEWTRADESTYPE.add(new OptionsInteger(13, "修改密码"));
		NEWTRADESTYPE.add(new OptionsInteger(14, "查询卡状态"));
		NEWTRADESTYPE.add(new OptionsInteger(15, "消费"));
		NEWTRADESTYPE.add(new OptionsInteger(16, "消费冲正"));
		NEWTRADESTYPE.add(new OptionsInteger(17, "消费撤销"));
		NEWTRADESTYPE.add(new OptionsInteger(18, "消费撤销冲正"));
		NEWTRADESTYPE.add(new OptionsInteger(20, "积分消费"));
		NEWTRADESTYPE.add(new OptionsInteger(21, "积分消费冲正"));
		NEWTRADESTYPE.add(new OptionsInteger(22, "积分消费撤销"));
		NEWTRADESTYPE.add(new OptionsInteger(23, "积分消费撤销冲正"));
	}

	/**
	 * 交易类型 INQ—查询 CRD--消费 CRR—消费冲正 VVD--消费撤销 VVR—消费撤销冲正 CCD--充值 CCR—充值冲正
	 * VCD--充值撤销 VCR—充值撤销冲正 PRD--积分消费 PRR—积分消费冲正 VRD--积分消费撤销 VRR—积分消费撤销冲正 PRF—退货
	 */
	public static List<OptionsString> TRADE_TYPE = new ArrayList<OptionsString>();
	static {
		TRADE_TYPE.add(new OptionsString("CRD", "消费"));
		TRADE_TYPE.add(new OptionsString("CCD", "充值"));
		TRADE_TYPE.add(new OptionsString("PRF", "退货"));
		TRADE_TYPE.add(new OptionsString("CRR", "消费冲正"));
		TRADE_TYPE.add(new OptionsString("VVD", "消费撤销"));
		TRADE_TYPE.add(new OptionsString("VVR", "消费撤销冲正"));
		TRADE_TYPE.add(new OptionsString("CCR", "充值冲正"));
		TRADE_TYPE.add(new OptionsString("VCD", "充值撤销"));
		TRADE_TYPE.add(new OptionsString("VCR", "充值撤销冲正"));
		TRADE_TYPE.add(new OptionsString("PRD", "积分消费"));
		TRADE_TYPE.add(new OptionsString("PRR", "积分消费冲正"));
		TRADE_TYPE.add(new OptionsString("VRD", "积分消费撤销"));
		TRADE_TYPE.add(new OptionsString("VRR", "积分消费撤销冲正"));
		TRADE_TYPE.add(new OptionsString("INQ", "查询"));
		TRADE_TYPE.add(new OptionsString("PCD", "预授权"));
		TRADE_TYPE.add(new OptionsString("PCR", "预授权冲正"));
		TRADE_TYPE.add(new OptionsString("VPD", "预授权撤销"));
		TRADE_TYPE.add(new OptionsString("VPR", "预授权撤销冲正"));
		TRADE_TYPE.add(new OptionsString("PCC", "预授权完成"));
		TRADE_TYPE.add(new OptionsString("CPR", "预授权完成冲正"));
		TRADE_TYPE.add(new OptionsString("VPC", "预授权完成撤销"));
		TRADE_TYPE.add(new OptionsString("CVR", "预授权完成撤销冲正"));

	}

	/**
	 * 交易类型 INQ—查询 CRD--消费 CRR—消费冲正 VVD--消费撤销 VVR—消费撤销冲正 CCD--充值 CCR—充值冲正
	 * VCD--充值撤销 VCR—充值撤销冲正 PRD--积分消费 PRR—积分消费冲正 VRD--积分消费撤销 VRR—积分消费撤销冲正 PRF—退货
	 */
	public static List<OptionsString> TRADE_TYPE2 = new ArrayList<OptionsString>();
	static {
		TRADE_TYPE2.add(new OptionsString("SK", "售卡"));
		TRADE_TYPE2.add(new OptionsString("CRD", "消费"));
		TRADE_TYPE2.add(new OptionsString("CCD", "POS充值"));
		TRADE_TYPE2.add(new OptionsString("CCD2", "后台充值"));
		TRADE_TYPE2.add(new OptionsString("PRF", "退货"));
		TRADE_TYPE2.add(new OptionsString("CRR", "消费冲正"));
		TRADE_TYPE2.add(new OptionsString("VVD", "消费撤销"));
		TRADE_TYPE2.add(new OptionsString("VVR", "消费撤销冲正"));
		TRADE_TYPE2.add(new OptionsString("CCR", "充值冲正"));
		TRADE_TYPE2.add(new OptionsString("VCD", "充值撤销"));
		TRADE_TYPE2.add(new OptionsString("VCR", "充值撤销冲正"));
		TRADE_TYPE2.add(new OptionsString("PRD", "积分消费"));
		TRADE_TYPE2.add(new OptionsString("PRR", "积分消费冲正"));
		TRADE_TYPE2.add(new OptionsString("VRD", "积分消费撤销"));
		TRADE_TYPE2.add(new OptionsString("VRR", "积分消费撤销冲正"));
		TRADE_TYPE2.add(new OptionsString("INQ", "查询"));
		TRADE_TYPE2.add(new OptionsString("PCD", "预授权"));
		TRADE_TYPE2.add(new OptionsString("PCR", "预授权冲正"));
		TRADE_TYPE2.add(new OptionsString("VPD", "预授权撤销"));
		TRADE_TYPE2.add(new OptionsString("VPR", "预授权撤销冲正"));
		TRADE_TYPE2.add(new OptionsString("PCC", "预授权完成"));
		TRADE_TYPE2.add(new OptionsString("CPR", "预授权完成冲正"));
		TRADE_TYPE2.add(new OptionsString("VPC", "预授权完成撤销"));
		TRADE_TYPE2.add(new OptionsString("CVR", "预授权完成撤销冲正"));
	}

	/** 交易状态P：处理中C：完成R：冲正r:冲正中 */
	public static List<OptionsString> TRADE_STATUS = new ArrayList<OptionsString>();
	static {
		TRADE_STATUS.add(new OptionsString("P", "处理中"));
		TRADE_STATUS.add(new OptionsString("C", "完成"));
		TRADE_STATUS.add(new OptionsString("R", "冲正"));
		TRADE_STATUS.add(new OptionsString("r", "冲正中"));
	}

	/** 状态 0 正常1 已冲正2 已退货3 已撤销 */
	public static List<OptionsInteger> TRADE_FLAG = new ArrayList<OptionsInteger>();
	static {
		TRADE_FLAG.add(new OptionsInteger(0, "正常"));
		TRADE_FLAG.add(new OptionsInteger(1, "已冲正"));
		TRADE_FLAG.add(new OptionsInteger(2, "已退货"));
		TRADE_FLAG.add(new OptionsInteger(3, "已撤销"));
	}

	/** 结算类型1本机构卡2其他机构卡3本机构卡对外 */
	public static List<OptionsInteger> SETTLE_TYPE = new ArrayList<OptionsInteger>();
	static {
		SETTLE_TYPE.add(new OptionsInteger(1, "本机构卡"));
		SETTLE_TYPE.add(new OptionsInteger(2, "其他机构卡"));
		SETTLE_TYPE.add(new OptionsInteger(3, "本机构卡对外"));
	}

	/** 是否允许退货N不允许Y允许 */
	public static List<OptionsString> ALLOW_RETURN = new ArrayList<OptionsString>();
	static {
		ALLOW_RETURN.add(new OptionsString("N", "不允许"));
		ALLOW_RETURN.add(new OptionsString("Y", "允许"));
	}

	/** 充值状态 */
	public static List<OptionsInteger> RECHARGE_STATUS = new ArrayList<OptionsInteger>();
	static {
		RECHARGE_STATUS.add(new OptionsInteger(0, "未充值"));
		RECHARGE_STATUS.add(new OptionsInteger(1, "已充值"));
	}

	/** 操作类型 */
	public static List<OptionsInteger> OPERATE_TYPE = new ArrayList<OptionsInteger>();
	static {
		OPERATE_TYPE.add(new OptionsInteger(0, "登陆"));
		OPERATE_TYPE.add(new OptionsInteger(1, "添加"));
		OPERATE_TYPE.add(new OptionsInteger(2, "修改"));
		OPERATE_TYPE.add(new OptionsInteger(3, "删除"));
		OPERATE_TYPE.add(new OptionsInteger(4, "审核"));
	}

	/** 商户自建会员状态 */
	public static List<OptionsInteger> MerVip_STATUS = new ArrayList<OptionsInteger>();
	static {
		MerVip_STATUS.add(new OptionsInteger(0, "正常"));
		MerVip_STATUS.add(new OptionsInteger(9, "已解除会员"));
	}

	/** 账户标志 */
	public static List<OptionsInteger> ACCKIND_SIGN = new ArrayList<OptionsInteger>();
	static {
		ACCKIND_SIGN.add(new OptionsInteger(0, "按金额"));
		ACCKIND_SIGN.add(new OptionsInteger(1, "按次数"));
	}

	/** 卡级别升降及方式标志升级方式 */
	public static List<OptionsInteger> LEVELUPDOWN_WAY = new ArrayList<OptionsInteger>();
	static {
		LEVELUPDOWN_WAY.add(new OptionsInteger(1, "充值累计"));
		LEVELUPDOWN_WAY.add(new OptionsInteger(2, "卡片消费累计"));
		LEVELUPDOWN_WAY.add(new OptionsInteger(3, "卡片单次充值"));
		LEVELUPDOWN_WAY.add(new OptionsInteger(4, "卡片余额"));
	}

	/** 账户赠送类型 */
	public static List<OptionsInteger> GIFT_TYPE = new ArrayList<OptionsInteger>();
	static {
		GIFT_TYPE.add(new OptionsInteger(0, "消费"));
		GIFT_TYPE.add(new OptionsInteger(1, "充值"));
	}

	/** 账户赠送类型 */
	public static List<OptionsInteger> ACTIVITY_TIME = new ArrayList<OptionsInteger>();
	static {
		ACTIVITY_TIME.add(new OptionsInteger(1, "固定时段"));
		ACTIVITY_TIME.add(new OptionsInteger(2, "按每月"));
		ACTIVITY_TIME.add(new OptionsInteger(3, "生日当天"));
	}

	/** 退卡类型 */
	public static List<OptionsInteger> DESTROY_TYPE = new ArrayList<OptionsInteger>();
	static {
		DESTROY_TYPE.add(new OptionsInteger(1, "空卡回收"));
		DESTROY_TYPE.add(new OptionsInteger(2, "客户退回"));
	}

	/** 终端折扣率优先级 */
	public static List<OptionsInteger> REBATE_FLAG = new ArrayList<OptionsInteger>();
	static {
		REBATE_FLAG.add(new OptionsInteger(0, "使用卡级别账户规则"));
		REBATE_FLAG.add(new OptionsInteger(1, "使用自定义终端折扣率"));
	}

	/** 短信状态 */
	public static List<OptionsInteger> MSG_FLAG = new ArrayList<OptionsInteger>();
	static {
		MSG_FLAG.add(new OptionsInteger(0, "未发送"));
		MSG_FLAG.add(new OptionsInteger(1, "提交成功"));
		MSG_FLAG.add(new OptionsInteger(2, "发送成功"));
		MSG_FLAG.add(new OptionsInteger(3, "发送失败"));
	}

	/** 短信发送模式 */
	public static List<OptionsString> SEND_TYPE = new ArrayList<OptionsString>();
	static {
		SEND_TYPE.add(new OptionsString("PRO", "行业应用"));
		SEND_TYPE.add(new OptionsString("AD", "广告"));
	}

	/** 短信供应商 */
	public static List<OptionsString> MSG_SUPPLIER = new ArrayList<OptionsString>();
	static {
		MSG_SUPPLIER.add(new OptionsString("MAI", "移动通道"));
		MSG_SUPPLIER.add(new OptionsString("UMS", "联通通道"));
	}

	/** 系统类型 */
	public static List<OptionsInteger> SYS_TYPE = new ArrayList<OptionsInteger>();
	static {
		SYS_TYPE.add(new OptionsInteger(0, "预付卡发行系统"));
		SYS_TYPE.add(new OptionsInteger(1, "会员营销系统"));
	}

	/** 团购 状态 0:未支付1:支付成功2:已使用 8已过期 9申请退款 */
	public static List<OptionsInteger> GROUPBUY_STATUS = new ArrayList<OptionsInteger>();
	static {
		GROUPBUY_STATUS.add(new OptionsInteger(0, "未支付"));
		GROUPBUY_STATUS.add(new OptionsInteger(1, "支付成功"));
		GROUPBUY_STATUS.add(new OptionsInteger(2, "已使用"));
		GROUPBUY_STATUS.add(new OptionsInteger(8, "已过期"));
		GROUPBUY_STATUS.add(new OptionsInteger(9, "申请退款"));
	}

	/** 会员类型 0个人 1企业 */
	public static List<OptionsInteger> VIP_TYPE = new ArrayList<OptionsInteger>();
	static {
		VIP_TYPE.add(new OptionsInteger(0, "个人"));
		VIP_TYPE.add(new OptionsInteger(1, "企业"));
	}

	/** 卡标志类型 0普通卡1礼品卡2公务卡3员工卡 */
	public static List<OptionsInteger> CARD_FLAGS = new ArrayList<OptionsInteger>();
	static {
		CARD_FLAGS.add(new OptionsInteger(1, "礼品卡"));
		CARD_FLAGS.add(new OptionsInteger(2, "公务卡"));
	}

	/**
	 * 交易账户类型 电子现金IC卡消费0x01， M1卡钱包消费0x02 电子钱包IC卡消费是0x06，冲正0x05 , 圈存/退货0x07，现金
	 * 0x08 计次0x09 电是0x11,水是0x12,社保 0x13， 移动0x14,联通0x15， 电信0x16，煤气0x17 其它待定
	 */
	public static List<OptionsString> TRADES_ACCTYPE = new ArrayList<OptionsString>();
	static {
		TRADES_ACCTYPE.add(new OptionsString("01", "电子现金IC卡消费"));
		TRADES_ACCTYPE.add(new OptionsString("02", "M1卡钱包消费"));
		TRADES_ACCTYPE.add(new OptionsString("05", "电子钱包IC卡冲正"));
		TRADES_ACCTYPE.add(new OptionsString("06", "电子钱包IC卡消费"));
		TRADES_ACCTYPE.add(new OptionsString("07", "圈存/退货"));
		TRADES_ACCTYPE.add(new OptionsString("08", "现金"));
		TRADES_ACCTYPE.add(new OptionsString("09", "计次"));
		TRADES_ACCTYPE.add(new OptionsString("11", "电"));
		TRADES_ACCTYPE.add(new OptionsString("12", "水"));
		TRADES_ACCTYPE.add(new OptionsString("13", "社保"));
		TRADES_ACCTYPE.add(new OptionsString("14", "移动"));
		TRADES_ACCTYPE.add(new OptionsString("15", "联通"));
		TRADES_ACCTYPE.add(new OptionsString("16", "电信"));
		TRADES_ACCTYPE.add(new OptionsString("17", "煤气"));
	}

	/** 企业发卡状态 */
	public static List<OptionsInteger> CPY_CARD_STATUS = new ArrayList<OptionsInteger>();
	static {
		CPY_CARD_STATUS.add(new OptionsInteger(0, "未领用"));
		CPY_CARD_STATUS.add(new OptionsInteger(1, "已领用"));
	}

	/** 企业订单类型 */
	public static List<OptionsInteger> CPY_ORDER_TYPE = new ArrayList<OptionsInteger>();
	static {
		CPY_ORDER_TYPE.add(new OptionsInteger(0, "手动充值"));
		CPY_ORDER_TYPE.add(new OptionsInteger(1, "自动充值"));
	}

	/** 企业库存变动类型 */
	public static List<OptionsInteger> CPY_INVEN_FLAG = new ArrayList<OptionsInteger>();
	static {
		CPY_INVEN_FLAG.add(new OptionsInteger(0, "企业入库"));
		CPY_INVEN_FLAG.add(new OptionsInteger(1, "员工领卡"));
		CPY_INVEN_FLAG.add(new OptionsInteger(2, "礼品公务卡领取"));
	}

	/** 地区类型 */
	public static List<OptionsInteger> AREA_VALUE = new ArrayList<OptionsInteger>();
	static {
		AREA_VALUE.add(new OptionsInteger(0, "北京"));
		AREA_VALUE.add(new OptionsInteger(1, "上海"));
		AREA_VALUE.add(new OptionsInteger(2, "天津"));
		AREA_VALUE.add(new OptionsInteger(3, "重庆"));
		AREA_VALUE.add(new OptionsInteger(4, "河南省"));
		AREA_VALUE.add(new OptionsInteger(5, "河北省"));
		AREA_VALUE.add(new OptionsInteger(6, "新疆维吾尔"));
		AREA_VALUE.add(new OptionsInteger(7, "西藏"));
		AREA_VALUE.add(new OptionsInteger(8, "广西省"));
		AREA_VALUE.add(new OptionsInteger(9, "江苏省"));
		AREA_VALUE.add(new OptionsInteger(10, "山东省"));
		AREA_VALUE.add(new OptionsInteger(11, "浙江省"));
		AREA_VALUE.add(new OptionsInteger(12, "云南省"));
		AREA_VALUE.add(new OptionsInteger(13, "辽宁省"));
		AREA_VALUE.add(new OptionsInteger(14, "黑龙江省"));
		AREA_VALUE.add(new OptionsInteger(15, "湖南省"));
		AREA_VALUE.add(new OptionsInteger(16, "安徽省"));
		AREA_VALUE.add(new OptionsInteger(17, "江西省"));
		AREA_VALUE.add(new OptionsInteger(18, "湖北省"));
		AREA_VALUE.add(new OptionsInteger(19, "甘肃省"));
		AREA_VALUE.add(new OptionsInteger(20, "山西省"));
		AREA_VALUE.add(new OptionsInteger(21, " 内蒙古"));
		AREA_VALUE.add(new OptionsInteger(22, "陕西省"));
		AREA_VALUE.add(new OptionsInteger(23, "吉林省"));
		AREA_VALUE.add(new OptionsInteger(24, "福建省"));
		AREA_VALUE.add(new OptionsInteger(25, "贵州省"));
		AREA_VALUE.add(new OptionsInteger(26, "广东省"));
		AREA_VALUE.add(new OptionsInteger(27, "青海省"));
		AREA_VALUE.add(new OptionsInteger(28, "四川省"));
		AREA_VALUE.add(new OptionsInteger(29, "天津"));
		AREA_VALUE.add(new OptionsInteger(30, "宁夏回族"));
		AREA_VALUE.add(new OptionsInteger(31, "海南省"));

	}

	/** 邮件发送类型0:个人 1:群组 2:节日 3:生日 **/
	public static List<OptionsInteger> MAIL_TYPE = new ArrayList<OptionsInteger>();
	static {
		MAIL_TYPE.add(new OptionsInteger(0, "个人"));
		MAIL_TYPE.add(new OptionsInteger(1, "群组"));
		MAIL_TYPE.add(new OptionsInteger(2, "节日发送"));
		MAIL_TYPE.add(new OptionsInteger(3, "生日发送"));
	}

	public static List<OptionsInteger> HOLIDAY_STATUS = new ArrayList<OptionsInteger>();
	/** 节日是否已过，0：未过 1：已过 **/
	static {
		HOLIDAY_STATUS.add(new OptionsInteger(0, "未过"));
		HOLIDAY_STATUS.add(new OptionsInteger(1, "已过"));
	}

	public static List<OptionsInteger> MAILBIRTHDAY_STATUS = new ArrayList<OptionsInteger>();
	static {
		MAILBIRTHDAY_STATUS.add(new OptionsInteger(0, "启用"));
		MAILBIRTHDAY_STATUS.add(new OptionsInteger(1, "关闭"));
	}

	/** 短信套餐类型 */
	public static List<OptionsInteger> PACKAGE_TYPE = new ArrayList<OptionsInteger>();
	static {
		PACKAGE_TYPE.add(new OptionsInteger(0, "按条收费"));
		PACKAGE_TYPE.add(new OptionsInteger(1, "按月收费"));
	}

	/** 短信套餐类型 */
	public static List<OptionsInteger> PEOPLE_TYPE = new ArrayList<OptionsInteger>();
	static {
		PEOPLE_TYPE.add(new OptionsInteger(0, "加油站所属会员"));
		PEOPLE_TYPE.add(new OptionsInteger(1, "平台所属会员"));
		PEOPLE_TYPE.add(new OptionsInteger(2, "加油站操作员"));
	}

	/** 商户消费所按条件: 0 :按季度 1：按月份 2：按天数 */
	public static List<OptionsInteger> MERCONSUME_STATUS = new ArrayList<OptionsInteger>();
	static {
		MERCONSUME_STATUS.add(new OptionsInteger(0, "按季度"));
		MERCONSUME_STATUS.add(new OptionsInteger(1, "按月份"));
		MERCONSUME_STATUS.add(new OptionsInteger(2, "按天数"));
	}

	/** 长短款处理 1:上调 2:下调 **/
	public static List<OptionsInteger> LONG_SHORT = new ArrayList<OptionsInteger>();
	static {
		LONG_SHORT.add(new OptionsInteger(0, "帐户上调"));
		LONG_SHORT.add(new OptionsInteger(1, "帐户下调"));
	}

	/** 帐户操作类型 0:调整 1:消费 2:消费 3:充值 4：换卡 5：退卡 **/
	public static List<OptionsInteger> ACC_OPERTYPE = new ArrayList<OptionsInteger>();
	static {
		ACC_OPERTYPE.add(new OptionsInteger(0, "调整"));
		ACC_OPERTYPE.add(new OptionsInteger(1, "消费"));
		ACC_OPERTYPE.add(new OptionsInteger(2, "售卡"));
		ACC_OPERTYPE.add(new OptionsInteger(3, "充值"));
		ACC_OPERTYPE.add(new OptionsInteger(4, "换卡"));
		ACC_OPERTYPE.add(new OptionsInteger(5, "退卡"));

	}

	/** 异常类型 */
	public static List<OptionsInteger> ABNORMAL_TYPE = new ArrayList<OptionsInteger>();
	static {
		ABNORMAL_TYPE.add(new OptionsInteger(1, "金额异常"));
		ABNORMAL_TYPE.add(new OptionsInteger(2, "卡号异常"));
	}

	/** 帐户资金操作流向类型 1:上调 2:下调 3:出帐 4:入帐 **/
	public static List<OptionsInteger> ACC_FLOW = new ArrayList<OptionsInteger>();
	static {
		ACC_FLOW.add(new OptionsInteger(0, "上调"));
		ACC_FLOW.add(new OptionsInteger(2, "下调"));
		ACC_FLOW.add(new OptionsInteger(3, "出账"));
		ACC_FLOW.add(new OptionsInteger(4, "入账"));
	}

	/*** 加油站与平台合作状态 ***/
	public static List<OptionsInteger> COOP_STATUE = new ArrayList<OptionsInteger>();
	static {
		COOP_STATUE.add(new OptionsInteger(1, "合作中"));
		COOP_STATUE.add(new OptionsInteger(2, "合作中断"));
	}

	/** 交保单位级别标识 ***/
	public static List<OptionsInteger> BAILCOMP_SIGN = new ArrayList<OptionsInteger>();
	static {
		BAILCOMP_SIGN.add(new OptionsInteger(1, "加油站"));
		BAILCOMP_SIGN.add(new OptionsInteger(2, "平台"));
	}

	/** 车型 **/
	public static List<OptionsInteger> CAR_TYPE = new ArrayList<OptionsInteger>();
	static {
		CAR_TYPE.add(new OptionsInteger(0, "大型车"));
		CAR_TYPE.add(new OptionsInteger(1, "中型车"));
		CAR_TYPE.add(new OptionsInteger(2, "小型车"));
		CAR_TYPE.add(new OptionsInteger(3, "轿车"));
		CAR_TYPE.add(new OptionsInteger(4, "公交车"));
	}

	/** 交保单位级别标识 ***/
	public static List<OptionsInteger> COOP_WAY = new ArrayList<OptionsInteger>();
	static {
		COOP_WAY.add(new OptionsInteger(1, "自建"));
		COOP_WAY.add(new OptionsInteger(2, "加盟"));
		COOP_WAY.add(new OptionsInteger(3, "合作"));

	}

	/** 油品标识 **/
	public static List<OptionsString> OIL_TYPE = new ArrayList<OptionsString>();
	static {
		OIL_TYPE.add(new OptionsString("90#汽油", "90#汽油"));
		OIL_TYPE.add(new OptionsString("92#汽油", "92#汽油"));
		OIL_TYPE.add(new OptionsString("93#汽油", "93#汽油"));
		OIL_TYPE.add(new OptionsString("95#汽油", "95#汽油"));
		OIL_TYPE.add(new OptionsString("97#汽油", "97#汽油"));
		OIL_TYPE.add(new OptionsString("0#柴油", "0#柴油"));
	}

	/**
	 * 购油申请订单状态 0：待审核；1：审核通过；2：审核未通过；3：已撤销；
	 */
	public static List<OptionsInteger> OilORDER_STATUS = new ArrayList<OptionsInteger>();
	static {
		OilORDER_STATUS.add(new OptionsInteger(0, "待审核"));
		OilORDER_STATUS.add(new OptionsInteger(1, "审核通过"));
		OilORDER_STATUS.add(new OptionsInteger(2, "审核未通过"));
		OilORDER_STATUS.add(new OptionsInteger(3, "已撤销"));
	}

	public static List<OptionsInteger> CARD_TYPE = new ArrayList<OptionsInteger>();
	static {
		CARD_TYPE.add(new OptionsInteger(1, "个人会员卡"));
		CARD_TYPE.add(new OptionsInteger(2, "司机卡"));
		CARD_TYPE.add(new OptionsInteger(3, "加油站卡"));

	}

	public static List<OptionsInteger> RECOM_TYPE = new ArrayList<OptionsInteger>();
	static {
		RECOM_TYPE.add(new OptionsInteger(1, "个人会员"));
		RECOM_TYPE.add(new OptionsInteger(2, "加油站会员"));

	}

	/*** 提现标识 **/
	public static List<OptionsInteger> GETCASH_SIGN = new ArrayList<OptionsInteger>();
	static {
		GETCASH_SIGN.add(new OptionsInteger(1, "司机"));
		GETCASH_SIGN.add(new OptionsInteger(2, "加油站"));
	}

	/*** 提現類型 ***/
	public static List<OptionsInteger> GETCASH_TYPE = new ArrayList<OptionsInteger>();
	static {
		GETCASH_TYPE.add(new OptionsInteger(1, "现金"));
		GETCASH_TYPE.add(new OptionsInteger(2, "转账"));
	}

	/*** 提現状态 ***/
	public static List<OptionsInteger> GETCASH_STATUS = new ArrayList<OptionsInteger>();
	static {
		GETCASH_STATUS.add(new OptionsInteger(1, "提现成功"));
		GETCASH_STATUS.add(new OptionsInteger(2, "提现处理中"));
		GETCASH_STATUS.add(new OptionsInteger(3, "提现失败"));
	}

	/*** 商品分类形式 ***/
	public static List<OptionsInteger> GoodsFamily_STATUS = new ArrayList<OptionsInteger>();
	static {
		GoodsFamily_STATUS.add(new OptionsInteger(0, "手动分类"));
		GoodsFamily_STATUS.add(new OptionsInteger(1, "自动分类"));
	}

	public static List<OptionsInteger> PayOrgans = new ArrayList<OptionsInteger>();
	static {
		PayOrgans.add(new OptionsInteger(0, "支付宝"));
		PayOrgans.add(new OptionsInteger(1, "财付通"));
		PayOrgans.add(new OptionsInteger(2, "快钱支付"));
		PayOrgans.add(new OptionsInteger(3, "银联在线支付"));
	}

	/*** 短信常用模版 ***/
	public static List<OptionsInteger> SMS_STEMNAME = new ArrayList<OptionsInteger>();
	static {
		SMS_STEMNAME.add(new OptionsInteger(10000000, "验证码模版"));
		SMS_STEMNAME.add(new OptionsInteger(10000001, "修改/找回密码"));
		SMS_STEMNAME.add(new OptionsInteger(10000002, "订单确认模版"));
		SMS_STEMNAME.add(new OptionsInteger(10000003, "订单取消模版"));
		SMS_STEMNAME.add(new OptionsInteger(10000004, "订单无效模版"));
		SMS_STEMNAME.add(new OptionsInteger(10000005, "发货通知模版"));
		SMS_STEMNAME.add(new OptionsInteger(10000006, "关注管理通知"));
	}
	/*** 短信服务商 ***/
	public static List<OptionsInteger> SMS_SERVERNAME = new ArrayList<OptionsInteger>();
	static {
		SMS_SERVERNAME.add(new OptionsInteger(00000001, "服务商1"));
		SMS_SERVERNAME.add(new OptionsInteger(00000002, "服务商2"));
		SMS_SERVERNAME.add(new OptionsInteger(00000003, "服务商3"));
		SMS_SERVERNAME.add(new OptionsInteger(00000004, "服务商4"));
		SMS_SERVERNAME.add(new OptionsInteger(00000005, "服务商5"));
	}
	/** 短信服务商状态xkq */
	public static List<OptionsInteger> SMS_SERVERSTATUS = new ArrayList<OptionsInteger>();
	static {
		SMS_SERVERSTATUS.add(new OptionsInteger(1, "启用"));
		SMS_SERVERSTATUS.add(new OptionsInteger(0, "停用"));
	}
	/** 发布状态 */
	public static List<OptionsInteger> PUBLISHSTATUS = new ArrayList<OptionsInteger>();
	static {
		PUBLISHSTATUS.add(new OptionsInteger(1, "启用"));
		PUBLISHSTATUS.add(new OptionsInteger(0, "停用"));
		PUBLISHSTATUS.add(new OptionsInteger(9, "删除"));
	}

	/** 服务类型 */
	public static List<OptionsInteger> SERVICETYPE = new ArrayList<OptionsInteger>();
	static {
		SERVICETYPE.add(new OptionsInteger(1, "自助服务"));
		SERVICETYPE.add(new OptionsInteger(0, "常见问题"));
	}
	public static List<OptionsInteger> ISSHOWLIST = new ArrayList<OptionsInteger>();
	static {
		ISSHOWLIST.add(new OptionsInteger(0, "是"));
		ISSHOWLIST.add(new OptionsInteger(1, "否"));
	}

	/******/

	public static List<OptionsInteger> EMAIL_TYPE = new ArrayList<OptionsInteger>();
	static {
		EMAIL_TYPE.add(new OptionsInteger(1, "发送密码模板"));
		EMAIL_TYPE.add(new OptionsInteger(2, "订单确认模板"));
		EMAIL_TYPE.add(new OptionsInteger(3, "发货通知模板"));
		EMAIL_TYPE.add(new OptionsInteger(4, "订单取消模板"));
		EMAIL_TYPE.add(new OptionsInteger(5, "订单无效模板"));
		EMAIL_TYPE.add(new OptionsInteger(6, "发送优惠券模板"));
		EMAIL_TYPE.add(new OptionsInteger(7, "商品团购模板"));
		EMAIL_TYPE.add(new OptionsInteger(8, "邮件验证模板"));
		EMAIL_TYPE.add(new OptionsInteger(9, "虚拟卡片模板"));
		EMAIL_TYPE.add(new OptionsInteger(10, "关注管理"));
		EMAIL_TYPE.add(new OptionsInteger(11, "新订单提醒模板"));
		EMAIL_TYPE.add(new OptionsInteger(12, "缺货回复模板"));
		EMAIL_TYPE.add(new OptionsInteger(13, "留言回复模板"));
		EMAIL_TYPE.add(new OptionsInteger(14, "用户评论回复模板"));
	}

	/** 启用状态 禁用 */
	public static List<OptionsInteger> STATUS_ENABLE_DISABLE = new ArrayList<OptionsInteger>();
	static {
		STATUS_ENABLE_DISABLE.add(new OptionsInteger(1, "启用"));
		STATUS_ENABLE_DISABLE.add(new OptionsInteger(0, "禁用"));
	}

	/** 启用状态 -无删除 */
	public static List<OptionsInteger> STATE_STATUSIMP = new ArrayList<OptionsInteger>();
	static {
		STATE_STATUSIMP.add(new OptionsInteger(1, "启用"));
		STATE_STATUSIMP.add(new OptionsInteger(0, "禁用"));

	}

	/** 活动管理 活动状态 */
	public static List<OptionsInteger> PROMOTION_STATUS = new ArrayList<OptionsInteger>();
	static {
		PROMOTION_STATUS.add(new OptionsInteger(1, "未招募"));
		PROMOTION_STATUS.add(new OptionsInteger(2, "招募中"));
		PROMOTION_STATUS.add(new OptionsInteger(3, "审核阶段"));
		PROMOTION_STATUS.add(new OptionsInteger(4, "活动中"));
		PROMOTION_STATUS.add(new OptionsInteger(5, "已结束"));
		PROMOTION_STATUS.add(new OptionsInteger(6, "已终止"));
		PROMOTION_STATUS.add(new OptionsInteger(9, "删除"));

	}

	/** 活动管理 活动状态 */
	public static List<OptionsInteger> PROMOTION_ADDSTATUS = new ArrayList<OptionsInteger>();
	static {
		PROMOTION_ADDSTATUS.add(new OptionsInteger(1, "未招募"));
		PROMOTION_ADDSTATUS.add(new OptionsInteger(2, "招募中"));

	}

	/** 活动管理 活动性质 */
	public static List<OptionsInteger> PROMOTION_PROTYPE = new ArrayList<OptionsInteger>();
	static {
		PROMOTION_PROTYPE.add(new OptionsInteger(1, "折扣促销"));
		PROMOTION_PROTYPE.add(new OptionsInteger(2, "抽奖促销"));
		PROMOTION_PROTYPE.add(new OptionsInteger(3, "会员制促销"));
		PROMOTION_PROTYPE.add(new OptionsInteger(4, "赠品促销"));

	}

	/** 活动管理 活动类型 */
	public static List<OptionsInteger> PROMOTION_HOSTSIGN = new ArrayList<OptionsInteger>();
	static {
		PROMOTION_HOSTSIGN.add(new OptionsInteger(0, "平台活动"));
		PROMOTION_HOSTSIGN.add(new OptionsInteger(1, "商户活动"));
	}

	/** 活动管理 活动名目 */
	public static List<OptionsInteger> PROMOTION_PROITEM = new ArrayList<OptionsInteger>();
	static {
		PROMOTION_PROITEM.add(new OptionsInteger(1, "季节性活动"));
		PROMOTION_PROITEM.add(new OptionsInteger(2, "开业促销活动"));
		PROMOTION_PROITEM.add(new OptionsInteger(3, "节庆促销活动"));
		PROMOTION_PROITEM.add(new OptionsInteger(4, "新品上市促销"));
		PROMOTION_PROITEM.add(new OptionsInteger(5, "庆典促销活动"));
		PROMOTION_PROITEM.add(new OptionsInteger(9, "其它促销活动"));

	}

	/** 活动管理 商户活动活动状态 */
	public static List<OptionsInteger> PROMOTION_MERSTATUS = new ArrayList<OptionsInteger>();
	static {
		PROMOTION_MERSTATUS.add(new OptionsInteger(3, "未开始"));
		PROMOTION_MERSTATUS.add(new OptionsInteger(4, "活动中"));
		PROMOTION_MERSTATUS.add(new OptionsInteger(5, "已结束"));
		PROMOTION_MERSTATUS.add(new OptionsInteger(6, "已终止"));
		PROMOTION_MERSTATUS.add(new OptionsInteger(9, "删除"));

	}

	/** 活动管理 商户活动活动状态 */
	public static List<OptionsInteger> PROMOTION_ADDMERSTATUS = new ArrayList<OptionsInteger>();
	static {
		PROMOTION_ADDMERSTATUS.add(new OptionsInteger(3, "未开始"));
		PROMOTION_ADDMERSTATUS.add(new OptionsInteger(4, "活动中"));

	}

	/** 活动管理 商户活动活动状态 */
	public static List<OptionsInteger> PROMOTION_MERAPPLYSTATUS = new ArrayList<OptionsInteger>();
	static {
		PROMOTION_MERAPPLYSTATUS.add(new OptionsInteger(1, "未审核"));
		PROMOTION_MERAPPLYSTATUS.add(new OptionsInteger(2, "审核通过"));
		PROMOTION_MERAPPLYSTATUS.add(new OptionsInteger(3, "审核不通过"));
		PROMOTION_MERAPPLYSTATUS.add(new OptionsInteger(9, "清退"));

	}

	/** 是否显示 **/
	public static List<OptionsInteger> isShowList = new ArrayList<OptionsInteger>() {
		private static final long serialVersionUID = -1972419493700376007L;

		{
			this.add(new OptionsInteger(0, "是"));
			this.add(new OptionsInteger(1, "否"));
		}
	};
	/** 违规类型 **/
	public static List<OptionsInteger> ILLEGAL_TYPE = new ArrayList<OptionsInteger>() {
		private static final long serialVersionUID = 8471606549344985508L;

		{
			this.add(new OptionsInteger(1, "一般违规"));
			this.add(new OptionsInteger(2, "严重违规"));
		}
	};

	/** 触犯案例1 一般违规 **/
	public static List<OptionsInteger> ILLEGAL_CASE1 = new ArrayList<OptionsInteger>() {
		private static final long serialVersionUID = 3847885243837182259L;

		{
			this.add(new OptionsInteger(1, "滥发信息"));
			this.add(new OptionsInteger(2, "虚假交易"));
			this.add(new OptionsInteger(3, "描述不符"));
			this.add(new OptionsInteger(4, "违背承诺"));
			this.add(new OptionsInteger(5, "竞拍不买"));
			this.add(new OptionsInteger(6, "恶意骚扰"));
			this.add(new OptionsInteger(7, "不当骚扰"));
			this.add(new OptionsInteger(8, "不当使用他人评论"));
			this.add(new OptionsInteger(9, "恶意评价"));
		}
	};

	/** 触犯案例2 严重违规 **/
	public static List<OptionsInteger> ILLEGAL_CASE2 = new ArrayList<OptionsInteger>() {
		private static final long serialVersionUID = -3602971422482494703L;

		{
			this.add(new OptionsInteger(1, "盗用他人账户"));
			this.add(new OptionsInteger(2, "泄露他人信息"));
			this.add(new OptionsInteger(3, "骗取他人财物"));
			this.add(new OptionsInteger(4, "扰乱市场秩序"));
			this.add(new OptionsInteger(5, "出售假冒伪劣产品"));
			this.add(new OptionsInteger(6, "不正当谋利"));
		}
	};

	/** 上下架状态 **/
	public static List<OptionsInteger> UPOFFSALE = new ArrayList<OptionsInteger>() {
		private static final long serialVersionUID = 8471606549344985508L;
		{
			this.add(new OptionsInteger(0, "上架"));
			this.add(new OptionsInteger(1, "下架"));
		}
	};

	/** 处理状态 **/
	public static List<OptionsInteger> PUNISHMETHOD = new ArrayList<OptionsInteger>() {
		private static final long serialVersionUID = 1719075383123093207L;
		{
			this.add(new OptionsInteger(1, "删除商品"));
			this.add(new OptionsInteger(2, "删除商店"));
			this.add(new OptionsInteger(3, "下架所有商品"));
			this.add(new OptionsInteger(4, "禁止上传商品"));
			this.add(new OptionsInteger(5, "停店整顿"));
		}
	};

	/**
	 * 处理提醒、处罚警告、强制下架、删除
	 */
	public static List<OptionsInteger> GOODSTA = new ArrayList<OptionsInteger>() {
		private static final long serialVersionUID = 3847885243837182259L;

		{
			this.add(new OptionsInteger(0, "正常"));
			this.add(new OptionsInteger(1, "禁用"));
			this.add(new OptionsInteger(9, "删除"));
		}
	};

	/**
	 * 违规状态： 1 已处理 2 未处理
	 */
	public static List<OptionsInteger> GOODILLSTA = new ArrayList<OptionsInteger>() {
		private static final long serialVersionUID = 3847885243837182258L;

		{
			this.add(new OptionsInteger(1, "已处理"));
			this.add(new OptionsInteger(2, "未处理"));
		}
	};
	/**
	 * 支付接配置口类型 0:标准双接口 1:担保交易接口 2:即时到帐交易接口
	 */
	public static List<OptionsInteger> PSTYPE = new ArrayList<OptionsInteger>() {
		private static final long serialVersionUID = -8035205005439404915L;

		{
			this.add(new OptionsInteger(0, "标准双接口"));
			this.add(new OptionsInteger(1, "担保交易接口"));
			this.add(new OptionsInteger(2, "即时到帐交易接口"));
		}
	};
	/**
	 * 支持交易货币 0:人民币 1：港币 2：澳币 3：美金 4：英镑 5：日元
	 * */
	public static List<OptionsInteger> CURRTYPE = new ArrayList<OptionsInteger>() {
		private static final long serialVersionUID = -8035205005439404915L;

		{
			this.add(new OptionsInteger(0, "人民币"));
			this.add(new OptionsInteger(1, "港币"));
			this.add(new OptionsInteger(2, "澳币"));
			this.add(new OptionsInteger(3, "美金"));
			this.add(new OptionsInteger(4, "英镑"));
			this.add(new OptionsInteger(5, "日元"));
		}
	};
	/**
	 * 结算方式 0：手动结算 1：自动结算
	 * */
	public static List<OptionsInteger> SETTWAY = new ArrayList<OptionsInteger>() {
		private static final long serialVersionUID = -8035205005439404915L;

		{
			this.add(new OptionsInteger(0, "手动结算"));
			this.add(new OptionsInteger(1, "自动结算"));
		}
	};
	/**
	 * 经营类型 0：个人全职 1：个人兼职 2：公司开店
	 * */
	public static List<OptionsInteger> BUSINTYPE = new ArrayList<OptionsInteger>() {
		private static final long serialVersionUID = -8035205005439404915L;

		{
			this.add(new OptionsInteger(0, "个人全职"));
			this.add(new OptionsInteger(1, "个人兼职"));
			this.add(new OptionsInteger(2, "公司开店"));
		}
	};
	/**
	 * 主要货源 0：线下批发市场 1：实体店拿货 2：阿里巴巴批发 3：分销/代销 4：自己生产 5：代工生产 6：自由公司渠道 7：货源还未确定
	 * */
	public static List<OptionsInteger> MAINPRODUCT = new ArrayList<OptionsInteger>() {
		private static final long serialVersionUID = -8035205005439404915L;

		{
			this.add(new OptionsInteger(0, "线下批发市场"));
			this.add(new OptionsInteger(1, "实体店拿货"));
			this.add(new OptionsInteger(2, "阿里巴巴批发"));
			this.add(new OptionsInteger(3, "分销/代销"));
			this.add(new OptionsInteger(4, "自己生产"));
			this.add(new OptionsInteger(5, "代工生产"));
			this.add(new OptionsInteger(6, "自由公司渠道"));
			this.add(new OptionsInteger(7, "货源还未确定"));
		}
	};

	/**
	 * 优先级0无1低2中3高 
	 */
	public static List<OptionsInteger> LEVELVALUE = new ArrayList<OptionsInteger>() {
		private static final long serialVersionUID = -8035205005439404915L;
		{
			this.add(new OptionsInteger(0, "无"));
			this.add(new OptionsInteger(1, "低"));
			this.add(new OptionsInteger(2, "中"));
			this.add(new OptionsInteger(3, "高"));
		}
	};
	/**
	 * 任务状态 0已完成1未完成9已延期
	 */
	public static List<OptionsInteger> STATUSVALUE = new ArrayList<OptionsInteger>() {
		private static final long serialVersionUID = -8035205005439404915L;
		{
			this.add(new OptionsInteger(0, "已完成"));
			this.add(new OptionsInteger(1, "未完成"));
			this.add(new OptionsInteger(9, "已延期"));
		}
	};
	/**
	 * 提醒方式 1邮件2短信
	 */
	public static List<OptionsInteger> REMINDWAY = new ArrayList<OptionsInteger>() {
		private static final long serialVersionUID = -8035205005439404915L;
		{
			this.add(new OptionsInteger(1, "邮件"));
			this.add(new OptionsInteger(2, "短信"));
		}
	};
	/**
	 * 商品状态 1销售中2下架
	 */
	public static List<OptionsInteger> GOODSSTATUS = new ArrayList<OptionsInteger>() {
		private static final long serialVersionUID = -8035205005439404915L;
		{
			this.add(new OptionsInteger(1, "销售中"));
			this.add(new OptionsInteger(2, "下架"));
			this.add(new OptionsInteger(9, "删除"));
		}
	};
	/**
	 * 商品类别状态
	 */
	public static List<OptionsInteger> GOODSTYPESTATUS = new ArrayList<OptionsInteger>() {
		private static final long serialVersionUID = -8035205005439404915L;
		{
			this.add(new OptionsInteger(1, "正常"));
			this.add(new OptionsInteger(9, "删除"));
		}
	};
	/**
	 * 订单状态
	 */
	public static List<OptionsInteger> ORDERSTATUS = new ArrayList<OptionsInteger>() {
		private static final long serialVersionUID = -8035205005439404915L;
		{
			this.add(new OptionsInteger(1, "交易成功"));
			this.add(new OptionsInteger(2, "交易失败"));
			this.add(new OptionsInteger(3, "欠款"));
			this.add(new OptionsInteger(9, "删除"));
		}
	};
}