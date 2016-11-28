/**  
* @Title: MerResetPwdService.java
* @Package: com.paySystem.ic.service.base
* @Description: TODO
* @Author: A18ccms A18ccms_gmail_com  
* @Date: 2014-12-11 下午02:27:36
* @Version: V1.0  
*/
package com.paySystem.ic.service.base;

import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.base.MerResetPwdDTO;

/**
 * @ProjectName:omallBackstage
 * @ClassName:MerResetPwdService
 * @Description:商户密码重置Service接口
 * @date: 2014-12-11
 * @author: 孟凡岭
 * @version: V1.0
 */
public interface MerResetPwdService {
	public static final String MERRESETPWDSERVICE="merResetPwdService";

	/**
	*@Title:findByMd5
	*@Description:md5查询实体
	*@Params:@param md5
	*@Params:@return
	*@Return:MerResetPwdDTO
	*@author:孟凡岭
	*@Date:2014-12-11下午04:00:11
	*/
	MerResetPwdDTO findByMd5(String md5) throws Exception;

	/**
	*@Title:checkCode
	*@Description:判断验证码是否正确
	*@Params:@param code
	*@Params:@param codeId
	*@Params:@return
	*@Return:ReturnDTO
	*@author:孟凡岭
	*@Date:2014-12-12上午10:24:20
	*/
	ReturnDTO checkCode(String code, Integer codeId) throws Exception ;

	/**
	*@Title:updatePwd
	*@Description:更新用户密码
	*@Params:@param md5
	*@Params:@param pwd
	*@Params:@return
	*@Return:boolean
	*@author:孟凡岭
	*@Date:2014-12-12上午10:46:02
	*/
	boolean updatePwd(String md5, String pwd) throws Exception;
}
