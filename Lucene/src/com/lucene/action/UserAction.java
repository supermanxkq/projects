package com.lucene.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.lucene.search.IndexSearcher;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lucene.bean.Search;
import com.lucene.dto.UserDTO;
import com.lucene.service.LuceneService;
import com.lucene.service.UserService;
import com.lucene.util.ExceptionUtil;
import com.lucene.util.ReturnTool;
import com.lucene.util.Utils;

/**
 * @ProjectName:blog
 * @ClassName:UserAction
 * @Description:用户的管理
 * @date: 2015-6-14下午08:45:52
 * @author: 徐半仙儿
 * @version: V1.0
 * @date:2015-6-14下午08:45:52
 */
@Controller("/system/user")
@Scope("prototype")
public class UserAction extends BaseAction {

	public Logger logger = Logger.getLogger(UserAction.class);
	/***/
	private static final long serialVersionUID = -1289007052029825283L;
	@Resource
	private UserService userService;
	private UserDTO userDTO = new UserDTO();
	/** 表单中的rand **/
	private String rand;
	private String keyWord;
	@Resource
	private LuceneService luceneService;

	/**
	 * @MethodName:addUI
	 * @Description:跳转到注册页面
	 * @author:徐半仙儿
	 * @return String
	 * @date:2015-6-14下午08:42:17
	 */
	public String addUI() {
		this.setMethod("addSave");
		return "input";
	}

	/**
	 * @MethodName:addSave
	 * @Description:注册新用户
	 * @author:徐半仙儿
	 * @return String
	 * @date:2015-6-14下午09:16:05
	 */
	public String addSave() {
		/** 从session中取出RandomAction.java 中生成的验证码random */
		String arandom = (String) ServletActionContext.getRequest().getSession().getAttribute("random");
		/** 判断验证码是否输入正确,验证码正确，进行登录操作 **/
		if (arandom.equals(this.getRand())) {
			userService.addUser(userDTO);
			return "addSaveSuccess";
		}
		return null;
	}

	/**
	 * @MethodName:validateCode
	 * @Description:验证验证码是否正确
	 * @author:徐半仙儿
	 * @return String
	 * @date:2015-6-14下午11:17:26
	 */
	public String validateCode() {
		String code = (String) ServletActionContext.getRequest().getSession().getAttribute("random");
		if (this.rand.equals(code)) {
			return Utils.printInfo(1);
		} else {
			return Utils.printInfo(0);
		}
	}

	public String luceneSearch() {
		String key="title,content";
		String fields="t_article";
		int page = 1;
		int row = 10;
		ReturnTool returnTool = new ReturnTool();

		int pages = 0;
		int counts = 0;
		List<Search> searchResult = null;
		String[] f = key.split(",");
		try {
			if (null != keyWord && !"".equals(keyWord)) {
				if (null != fields && fields.split(",").length > 1) {
					IndexSearcher indexSearcher = luceneService.getSearchers(fields);
					searchResult = luceneService.queryFromIndex(indexSearcher, keyWord, f, page, row);

					counts = luceneService.count(indexSearcher, keyWord, f);
				} else {
					IndexSearcher indexSearcher = luceneService.getSearcher(fields);
					// String[] f = key.split(",");
					searchResult = luceneService.queryFromIndex(indexSearcher, keyWord, f, page, row);
					counts = luceneService.count(indexSearcher, keyWord, f);
				}
			}
			pages = (int) Math.ceil((double) counts / row);
			returnTool.setPages(pages);
			returnTool.setSuccess(true);
			returnTool.setSearches(searchResult);
			returnTool.setCounts(counts);
		} catch (Exception e) {
			e.printStackTrace();
			String errorMsg = ExceptionUtil.getExceptionMessage(e);
			logger.info(errorMsg);
			returnTool.setSuccess(false);
			returnTool.setMsg(errorMsg);
		}
		return Utils.printInfo(returnTool);
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public String getRand() {
		return rand;
	}

	public void setRand(String rand) {
		this.rand = rand;
	}


	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
}
