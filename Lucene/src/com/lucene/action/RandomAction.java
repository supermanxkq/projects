package com.lucene.action;

import java.io.ByteArrayInputStream;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lucene.util.RandomNum;
import com.lucene.util.RandomNumUtil;
import com.lucene.util.VerifyCodeUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
@Controller("/system/randAction")
@Scope("prototype")
public class RandomAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3890733476819839189L;
	private ByteArrayInputStream inputStream;

	public String execute() throws Exception {
		
		//纯数字验证码
//		RandomNumUtil rdnu = RandomNumUtil.Instance();
//		this.setInputStream(rdnu.getImage());// 取得带有随机字符串的图片
//		ActionContext.getContext().getSession().put("random", rdnu.getString());// 取得随机字符串放入HttpSession
		
		//数字加英文验证码
//		String vcode=VerifyCodeUtils.generateVerifyCode(4);
//		int w = 85, h = 30;
//		VerifyCodeUtils.outputImage(w, h, vcode);
//		this.setInputStream(VerifyCodeUtils.getImage2());// 取得带有随机字符串的图片
//		ActionContext.getContext().getSession().put("random", vcode);// 取得随机字符串放入HttpSession
		
		//汉字验证码
		RandomNum rdnu = RandomNum.Instance();
		this.setInputStream(rdnu.getImage());
		ActionContext.getContext().getSession().put("random", rdnu.getCode());
		return SUCCESS;
	}

	public void setInputStream(ByteArrayInputStream inputStream) {
		this.inputStream = inputStream;
	}

	public ByteArrayInputStream getInputStream() {
		return inputStream;
	}
}