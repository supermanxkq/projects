package com.paySystem.ic.web.action.base;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.service.base.CodeService;
import com.paySystem.ic.service.base.MerResetPwdService;
import com.paySystem.ic.service.base.impl.CodeServiceImpl;
import com.paySystem.ic.service.base.impl.MerResetPwdServiceImpl;
import com.paySystem.ic.service.system.UserService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.RandomUtils;
import com.paySystem.ic.utils.ShortMessUtil;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.base.CodeDTO;
import com.paySystem.ic.web.dto.base.MerResetPwdDTO;

/**
 * @ProjectName:omallBackstage
 * @ClassName:MerResetAction
 * @Description:商户重置
 * @date: 2014-12-11
 * @author: 孟凡岭
 * @version: V1.0
 */
@Controller("/base/merReset")
@Scope("prototype")
public class MerResetAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private MerResetPwdService merResetPwdService = new MerResetPwdServiceImpl();
	private MerResetPwdDTO reset = new MerResetPwdDTO();
	@Resource
	private CodeService codeService = new CodeServiceImpl();
	/** 商户密码重置MD5（商户号+会员号+手机号） **/
	private String md5;
	/** 手机验证码 **/
	private String code;
	/** 验证码ID **/
	private Integer codeId;
	/**密码**/
	private String pwd;

	public String list() throws Exception {
		reset = merResetPwdService.findByMd5(md5);
		if (reset != null) {
			this.getRequest().setAttribute(
					"tel",
					reset.getTeleNo().substring(0, 3) + "*****"
							+ reset.getTeleNo().substring(7));
			return "list";
		}else{
			this.getRequest().setAttribute("result",this.getText("链接无效，请核对！"));
			this.getRequest().setAttribute("url",Utils.getBasePath()+"system/login!loginPage");
		}
		return ERROR;
	}

	public String reset() throws Exception {
		try {
			merResetPwdService.updatePwd(md5,pwd);
			this.getRequest().setAttribute("result",this.getText("操作成功"));
			this.getRequest().setAttribute("url",Utils.getBasePath()+"system/login!loginPage");
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			this.getRequest().setAttribute("result",this.getText("操作失败，请重试"));
			this.getRequest().setAttribute("url",Utils.getBasePath()+"system/login!loginPage");
			return ERROR;
		}
	}

	public String sendCode() throws Exception {
		String telephone = merResetPwdService.findByMd5(md5).getTeleNo();
		String code = RandomUtils.generateInt(6);
		CodeDTO dto = new CodeDTO();
		dto.setTelNum(telephone);
		dto.setCodeNum(code);

		/** 当前时间 **/
		String currentTime = DateTimeTool.dateFormat("yyyy-MM-dd HH:mm:ss",
				new Date());

		/** 创建时间 **/
		dto.setCreateTime(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm:ss",
				currentTime));
		dto.setSpaceTime("120");
		ReturnDTO returnDTO = codeService.saveCode(dto);
		CodeDTO codeDTO = (CodeDTO) returnDTO.getObj();
		if (returnDTO.getFlag()) {
			String content = "尊敬的客户您好，您的验证码为" + code + "请勿回复 ！【融芯思联】";
			String result = ShortMessUtil.sendCodeMessage(telephone, content);
			System.out.println(code);
			if (result.compareTo("0") > 0) {
				returnDTO.setFlag(true);
				codeDTO.setMessage("校验码已发送至你的手机,请注意查收！");
			} else {
				returnDTO.setFlag(false);
				codeDTO.setMessage("发送失败，请重新发送！");
			}
		}
		returnDTO.setObj(codeDTO);
		return Utils.printInfo(returnDTO);
	}

	/**
	 * 
	 *@Title:checkCode
	 *@Description:判断验证码是否正确
	 *@Params:@throws Exception
	 *@Return:void
	 *@author:孟凡岭
	 *@Date:2014-12-12上午10:23:48
	 */
	public void checkCode() throws Exception {
		ReturnDTO rdto = new ReturnDTO();
		String message = "";
		ReturnDTO returnDTO = merResetPwdService.checkCode(code, codeId);
		if (!returnDTO.getFlag()) {
			message = "验证码错误，请重新输入！";
		} else {
			rdto.setFlag(true);
		}
		rdto.setObj(message);
		Utils.printInfo(rdto);
	}

	public MerResetPwdService getMerResetPwdService() {
		return merResetPwdService;
	}

	public void setMerResetPwdService(MerResetPwdService merResetPwdService) {
		this.merResetPwdService = merResetPwdService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public MerResetPwdDTO getReset() {
		return reset;
	}

	public void setReset(MerResetPwdDTO reset) {
		this.reset = reset;
	}

	public CodeService getCodeService() {
		return codeService;
	}

	public void setCodeService(CodeService codeService) {
		this.codeService = codeService;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getCodeId() {
		return codeId;
	}

	public void setCodeId(Integer codeId) {
		this.codeId = codeId;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}
