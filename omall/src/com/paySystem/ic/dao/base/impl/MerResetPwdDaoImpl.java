/**  
 * @Title: MerResetPwdDaoImpl.java
 * @Package: com.paySystem.ic.dao.base.impl
 * @Description: TODO
 * @Author: A18ccms A18ccms_gmail_com  
 * @Date: 2014-12-11 下午02:29:50
 * @Version: V1.0  
 */
package com.paySystem.ic.dao.base.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.base.MerResetPwd;
import com.paySystem.ic.dao.base.MerResetPwdDao;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.utils.EntityDtoConverter;
import com.paySystem.ic.utils.MD5;
import com.paySystem.ic.web.dto.base.MerResetPwdDTO;

/**
 * @ProjectName:omallBackstage
 * @ClassName:MerResetPwdDaoImpl
 * @Description:商户密码重置Dao接口实现
 * @date: 2014-12-11
 * @author: 孟凡岭
 * @version: V1.0
 */
@Repository(MerResetPwdDao.MERRESETPWDDAO)
public class MerResetPwdDaoImpl extends DaoSupport<MerResetPwd> implements
		MerResetPwdDao {

	/**
	 *@MethodName:findByMd5
	 *@Description:md5查询实体
	 *@param md5
	 *@return
	 *@Author:孟凡岭
	 *@Date:2014-12-11下午04:01:13
	 */
	@SuppressWarnings("unchecked")
	public MerResetPwdDTO findByMd5(String md5) throws Exception {
		// TODO Auto-generated method stub
		String sql = "from MerResetPwd where md5=? and isReset=0";
		List<MerResetPwd> list = this.em.createQuery(sql).setParameter(1, md5)
				.getResultList();
		if (list.size() > 0) {
			return (MerResetPwdDTO) EntityDtoConverter.bean2Dto(list.get(0),
					new MerResetPwdDTO());
		}
		return null;
	}

	/**
	 *@MethodName:updatePwd
	 *@Description:更新是否已经修改过密码状态
	 *@param md5
	 *@param pwd
	 *@return
	 *@Author:孟凡岭
	 *@Date:2014-12-12上午11:39:18
	 */
	public boolean updatePwd(String md5) throws Exception{
		// TODO Auto-generated method stub
			//更新是否已经修改过密码状态
			String sql="update b_MerResetPwd set isReset=1 where md5=?";
			Query querym=this.em.createNativeQuery(sql);
			querym.setParameter(1, md5);
			querym.executeUpdate();
			return true;
	}

}
