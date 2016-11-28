/**  
 * @Title: GoodsFamilyRateRelDaoImpl.java
 * @Package: com.paySystem.ic.dao.base.impl
 * @Description: TODO
 * @Author: A18ccms A18ccms_gmail_com  
 * @Date: 2014-12-10 上午09:13:07
 * @Version: V1.0  
 */
package com.paySystem.ic.dao.base.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.base.GoodsFamilyRateRel;
import com.paySystem.ic.dao.base.GoodsFamilyRateRelDao;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.base.GoodsFamilyDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * @ProjectName:omallBackstage
 * @ClassName:GoodsFamilyRateRelDaoImpl
 * @Description:商品分类 & 手续费率Dao实现
 * @date: 2014-12-10
 * @author: 孟凡岭
 * @version: V1.0
 */
@Repository(GoodsFamilyRateRelDao.GOODSFAMILYRATERELDAO)
public class GoodsFamilyRateRelDaoImpl extends DaoSupport<GoodsFamilyRateRel>
		implements GoodsFamilyRateRelDao {

	/**
	 * 
	 *@MethodName:findByFamilyId
	 *@Description:通过分类编号查询
	 *@param familyId
	 *@return
	 *@Author:孟凡岭
	 *@Date:2014-12-10上午09:57:42
	 */
	public Map<Integer, GoodsFamilyRateRel> findByFamilyId(Integer familyId) {
		// TODO Auto-generated method stub
		Map<Integer, GoodsFamilyRateRel> map = new HashMap<Integer, GoodsFamilyRateRel>();
		String sql = "from GoodsFamilyRateRel g where g.familyId=?";
		List<GoodsFamilyRateRel> list = this.em.createQuery(sql).setParameter(
				1, familyId).getResultList();
		for (int i = 0; i < list.size(); i++) {
			map.put(list.get(i).getPayMentType(), list.get(i));
		}
		return map;
	}

	/**
	 *@MethodName:deleteByFamilyId
	 *@Description:通过分类编号删除数据
	 *@param familyId
	 *@Author:孟凡岭
	 *@Date:2014-12-10上午10:09:37
	 */
	public void deleteByFamilyId(Integer familyId) {
		// TODO Auto-generated method stub
		String sql = "delete from b_GoodsFamilyRateRel where familyId="
				+ familyId;
		this.em.createNativeQuery(sql).executeUpdate();
	}

	/**
	 *@MethodName:updateByFamilyId
	 *@Description:更新数据
	 *@param goodsfamilyDto
	 *@Author:孟凡岭
	 *@Date:2014-12-10上午10:12:55
	 */
	public void updateByFamilyId(GoodsFamilyDTO goodsfamilyDto,Map<Integer, GoodsFamilyRateRel> map) {
		// TODO Auto-generated method stub
		String userName=Utils.getUserSession().getUserName();
		//融芯卡
		GoodsFamilyRateRel g=map.get(goodsfamilyDto.getRswinPay());
		g.setChargeRate(goodsfamilyDto.getRswinPayValue());
		g.setOperatorId(userName);
		g.setUpdateTime(getSysTime());
		this.update(g);
		//网银支付
		g=map.get(goodsfamilyDto.getWyPay());
		g.setChargeRate(goodsfamilyDto.getWyPayValue());
		g.setOperatorId(userName);
		g.setUpdateTime(getSysTime());
		this.update(g);
		
		g=map.get(goodsfamilyDto.getWyPay());
		g.setChargeRate(goodsfamilyDto.getWyPayValue());
		g.setOperatorId(userName);
		g.setUpdateTime(getSysTime());
		this.update(g);
		g=map.get(goodsfamilyDto.getWyPay());
		g.setChargeRate(goodsfamilyDto.getWyPayValue());
		g.setOperatorId(userName);
		g.setUpdateTime(getSysTime());
		this.update(g);
		g=map.get(goodsfamilyDto.getWyPay());
		g.setChargeRate(goodsfamilyDto.getWyPayValue());
		g.setOperatorId(userName);
		g.setUpdateTime(getSysTime());
		this.update(g);
	}
}
