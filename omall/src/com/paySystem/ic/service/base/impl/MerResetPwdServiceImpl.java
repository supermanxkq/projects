/**  
 * @Title: MerResetPwdServiceImpl.java
 * @Package: com.paySystem.ic.service.base.impl
 * @Description: TODO
 * @Author: A18ccms A18ccms_gmail_com  
 * @Date: 2014-12-11 下午02:28:13
 * @Version: V1.0  
 */
package com.paySystem.ic.service.base.impl;

import javax.annotation.Resource;

import org.apache.taglibs.standard.lang.jstl.Coercions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.system.User;
import com.paySystem.ic.dao.base.MerResetPwdDao;
import com.paySystem.ic.dao.base.MerchantsDao;
import com.paySystem.ic.dao.base.impl.MerResetPwdDaoImpl;
import com.paySystem.ic.dao.base.impl.MerchantsDaoImpl;
import com.paySystem.ic.dao.system.UserDao;
import com.paySystem.ic.dao.system.impl.UserDaoImpl;
import com.paySystem.ic.service.base.CodeService;
import com.paySystem.ic.service.base.MerResetPwdService;
import com.paySystem.ic.service.system.UserService;
import com.paySystem.ic.service.system.impl.UserServiceBean;
import com.paySystem.ic.utils.MD5;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.base.CodeDTO;
import com.paySystem.ic.web.dto.base.MerResetPwdDTO;

/**
 * @ProjectName:omallBackstage
 * @ClassName:MerResetPwdServiceImpl
 * @Description:商户密码重置Service接口实现
 * @date: 2014-12-11
 * @author: 孟凡岭
 * @version: V1.0
 */
@Service(MerResetPwdServiceImpl.MERRESETPWDSERVICE)
public class MerResetPwdServiceImpl implements MerResetPwdService {
	@Resource
	private MerResetPwdDao merResetPwdDao = new MerResetPwdDaoImpl();
	@Resource
	private CodeService codeService = new CodeServiceImpl();
	@Resource
	private UserDao userDao = new UserDaoImpl();
	@Resource
	private MerchantsDao merchantsDao = new MerchantsDaoImpl();

	public MerchantsDao getMerchantsDao() {
		return merchantsDao;
	}

	public void setMerchantsDao(MerchantsDao merchantsDao) {
		this.merchantsDao = merchantsDao;
	}

	public CodeService getCodeService() {
		return codeService;
	}

	public void setCodeService(CodeService codeService) {
		this.codeService = codeService;
	}

	public MerResetPwdDao getMerResetPwdDao() {
		return merResetPwdDao;
	}

	public void setMerResetPwdDao(MerResetPwdDao merResetPwdDao) {
		this.merResetPwdDao = merResetPwdDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 *@MethodName:findByMd5
	 *@Description:md5查询实体
	 *@param md5
	 *@return
	 *@Author:孟凡岭
	 *@Date:2014-12-11下午04:00:29
	 */
	@Override
	public MerResetPwdDTO findByMd5(String md5) throws Exception {
		// TODO Auto-generated method stub
		return merResetPwdDao.findByMd5(md5);
	}

	/**
	 *@MethodName:checkCode
	 *@Description:判断验证码是否正确
	 *@param code
	 *@param codeId
	 *@return
	 * @throws Exception
	 *@Author:孟凡岭
	 *@Date:2014-12-12上午10:24:29
	 */
	public ReturnDTO checkCode(String code, Integer codeId) throws Exception {
		// TODO Auto-generated method stub
		ReturnDTO returnDTO = new ReturnDTO();
		CodeDTO codeDTO = codeService.findByCodeId(codeId);
		if (code.equals(codeDTO.getCodeNum())) {
			returnDTO.setFlag(true);
		}
		return returnDTO;
	}

	/**
	 *@MethodName:updatePwd
	 *@Description:更新用户密码
	 *@param md5
	 *@param pwd
	 *@return
	 *@Author:孟凡岭
	 *@Date:2014-12-12上午10:46:22
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean updatePwd(String md5, String pwd) throws Exception {
		// TODO Auto-generated method stub
		MerResetPwdDTO m = merResetPwdDao.findByMd5(md5);
		// 更新修改状态
		merResetPwdDao.updatePwd(md5);
		// 更新User数据
		User user = userDao.find(m.getTeleNo());
		user.setPassWord(MD5.MD5Encode(pwd));
		/**
		 * 注意：如果不设置status和等级登录会报错
		 * 不设置机构登录后顶部导航不会显示
		 * **/
		user.setStatus(1);
		user.setUserLevel(2);
		user.setOrganId(merchantsDao.find(user.getMerId()).getOrgans().getOrganId());
		userDao.update(user);
		return true;

	}

}
