/**  
* @Title: GoodsInfoDaoImpl.java
* @Package: com.paySystem.ic.dao.base.impl
* @Description: TODO
* @Author: A18ccms A18ccms_gmail_com  
* @Date: 2014-11-24 上午09:44:52
* @Version: V1.0  
*/
package com.paySystem.ic.dao.base.impl;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.goods.GoodInfo;
import com.paySystem.ic.dao.base.GoodsInfoDao;
import com.paySystem.ic.service.common.DaoSupport;

/**
 * @ProjectName:omallBackstage
 * @ClassName:GoodsInfoDaoImpl
 * @Description:商品信息dao接口实现类
 * @date: 2014-11-24
 * @author: 孟凡岭
 * @version: V1.0
 */
@Repository(GoodsInfoDao.GOODSINFODAO)
public class GoodsInfoDaoImpl extends DaoSupport<GoodInfo> implements GoodsInfoDao{

}
