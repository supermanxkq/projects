package com.paySystem.ic.web.action.base;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.paySystem.ic.service.base.FreightSetService;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.base.FreightSetDTO;

/**
 * @ProjectName: omall_back
 * @ClassName: FreightSetAction
 * @Description: 运费设置
 * @date: 2014-11-14
 * @author: 廖晓远 
 * @version: V1.0
 */
@Controller("/base/freightset")
@Scope("prototype")
public class FreightSetAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	
	@Resource
	private FreightSetService freightSetService;
	
	private FreightSetDTO freightSetDTO = new FreightSetDTO();

	/**
	 * 列表页面
	 * 
	 * @version 2011-9-8 下午08:50:23
	 * @return
	 * @throws Exception 
	 * @throws Exception
	 */
	public String list() throws Exception {
			freightSetDTO = freightSetService.findFreightSet();
			return "list";	
	}

	/**
	*@Title:editUI
	*@Description:修改方法
	*@Params:@return
	*@Return:String
	*@author:廖晓远
	 * @throws Exception 
	*@Date:2014-9-15下午04:06:39
	*/
	public String editUI() throws Exception {
		freightSetService.updateOrSave(freightSetDTO);
		return "input";
	}

	public FreightSetDTO getFreightSetDTO() {
		return freightSetDTO;
	}

	public void setFreightSetDTO(FreightSetDTO freightSetDTO) {
		this.freightSetDTO = freightSetDTO;
	}

}
