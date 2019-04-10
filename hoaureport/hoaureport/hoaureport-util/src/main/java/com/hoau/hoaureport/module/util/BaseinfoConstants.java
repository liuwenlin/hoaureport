package com.hoau.hoaureport.module.util;

/**
 *
 * @author 莫涛
 * @date 2015年12月31日上午10:48:54
 */
public class BaseinfoConstants {
	/**
	 * 可用
	 */
	public static final String ACTIVE ="Y";
	/**
	 * 不可用
	 */
	public static final String INACTIVE ="N";
	/**
	 * 是
	 */
	public static final String YES ="Y";
	/**
	 * 否
	 */
	public static final String NO ="N";
	
	public static final String KEY_CURRENT_DEPTCODE = "BUTTERFLY_KEY_CURRENT_DEPTCODE";
	public static final String FRAMEWORK_KEY_EMPCODE = "FRAMEWORK_KEY_EMPCODE";
	public static final String KEY_CURRENT_DEPTNAME = "BUTTERFLY_KEY_CURRENT_DEPTNAME";
	
	/**
	 * 前台树根节点id
	 */
	public static final String TREE_ROOT_ID = "root";
	
	/**
	 * web权限资源树根节点code
	 */
	public static final String RESOURCE_TREE_WEB_ROOT_CODE = "1";
	
	
	////////////////////送货方式 //////////////////
	
	/**
	 * 送货上门：DOORSTEP
	 */
	public static String SHIPPERMETHOD_DOORSTEP="DOORSTEP";
	/**
	 * 客户自提：SELF_TAKE
	 */
	public static String SHIPPERMETHOD_SELF_TAKE="SELF_TAKE";
	/**
	 * 送货上楼：UPSTAIR
	 */
	public static String SHIPPERMETHOD_UPSTAIR="UPSTAIR";
	
	/////////////// 打包 ///////////////////////

	/**
	 * 纸箱
	 */
	public static String PACKAGE_CARTON="CARTON";//纸箱
	/**
	 * 纤维编织袋
	 */
	public static String PACKAGE_FIBREBAG="FIBREBAG";//	纤维编织袋
	/**
	 * 打包带
	 */
	public static String PACKAGE_PACKAGEBELT="PACKAGEBELT";//	打包带
	/**
	 * 缠绕膜
	 */
	public static String PACKAGE_STRETCHFILM="STRETCHFILM";//	缠绕膜
	/**
	 * 木框
	 */
	public static String PACKAGE_WOODEN="WOODEN";//	木框

	//////////////////////付款方式////////////////////////////////////
	
	/**
	 * CASH	现付
	 */
	public static final String PAYMENT_CASH = "CASH";
	/**
	 * ARRIVE_PAYMENT	到付
	 */
	public static final String PAYMENT_ARRIVE_PAYMENT = "ARRIVE_PAYMENT";
	/**
	 * Monthly_Statement	结算
	 */
	public static final String PAYMENT_MONTH_PAYMENT = "Monthly_Statement";
	
	/////////////////签收回单////////////////////
	/**
	 * NOBACK	无需返单
	 */
	public static final String SIGNBACK_NOBACK = "NOBACK";
	/**
	 * SIGNORIGINAL	到货签收单原件返回
	 */
	public static final String SIGNBACK_SIGNORIGINAL = "SIGNORIGINAL";
	/**
	 * SIGNCOPY	到货签收单传真返回
	 */
	public static final String SIGNBACK_SIGNCOPY = "SIGNCOPY";
	/**
	 * CUSTORIGINAL	客户出库单原件返回
	 */
	public static final String SIGNBACK_CUSTORIGINAL = "CUSTORIGINAL";
	/**
	 * CUSTCOPY	客户出库单传真返回
	 */
	public static final String SIGNBACK_CUSTCOPY = "CUSTCOPY";
	
	
	
}
