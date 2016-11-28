/**  
* @Title: GoodsInfoDao.java
* @Package: com.paySystem.ic.dao.base
* @Description: TODO
* @Author: A18ccms A18ccms_gmail_com  
* @Date: 2014-11-24 上午09:44:04
* @Version: V1.0  
*/
package com.paySystem.ic.dao.base;

import com.paySystem.ic.bean.goods.GoodInfo;
import com.paySystem.ic.service.common.DAO;

/**
 * @ProjectName:omallBackstage
 * @ClassName:GoodsInfoDao
 * @Description:商品信息dao接口
 * @date: 2014-11-24
 * @author: 孟凡岭
 * @version: V1.0
 */
public interface GoodsInfoDao extends DAO<GoodInfo>{
	public static final String GOODSINFODAO="goodsInfoDao";
}
