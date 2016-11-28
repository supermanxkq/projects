/**  
* @Title: MerResetPwdDao.java
* @Package: com.paySystem.ic.dao.base
* @Description: TODO
* @Author: A18ccms A18ccms_gmail_com  
* @Date: 2014-12-11 下午02:29:13
* @Version: V1.0  
*/
package com.paySystem.ic.dao.base;

import com.paySystem.ic.bean.base.MerResetPwd;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.base.MerResetPwdDTO;

/**
 * @ProjectName:omallBackstage
 * @ClassName:MerResetPwdDao
 * @Description:商户密码重置Dao接口
 * @date: 2014-12-11
 * @author: 孟凡岭
 * @version: V1.0
 */
public interface MerResetPwdDao extends DAO<MerResetPwd>{
	public static final String MERRESETPWDDAO="merResetPwdDao";

	/**
	*@Title:findByMd5
	*@Description:md5查询实体
	*@Params:@param md5
	*@Params:@return
	*@Return:MerResetPwdDTO
	*@author:孟凡岭
	*@Date:2014-12-11下午04:01:00
	*/
	MerResetPwdDTO findByMd5(String md5) throws Exception;

	/**
	*@Title:updatePwd
	*@Description:更新是否已经修改过密码状态
	*@Params:@param md5
	*@Params:@param pwd
	*@Params:@return
	*@Return:boolean
	*@author:孟凡岭
	*@Date:2014-12-12上午11:38:53
	*/
	boolean updatePwd(String md5) throws Exception;
}
