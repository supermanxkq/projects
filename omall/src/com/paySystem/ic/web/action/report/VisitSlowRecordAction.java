package com.paySystem.ic.web.action.report;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.service.report.VisitSlowRecordService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.report.VisitSlowRecordDTO;

/**
 * @ProjectName:omall20140708DS
 * @ClassName:VisitorSlowAction
 * @Description:访客流量统计Action
 * @date: 2014-7-17
 * @author: 赵巧鹤
 * @version: V1.0
 */
@Controller("/report/visitorSlowRecord")
@Scope("prototype")
public class VisitSlowRecordAction extends BaseAction{
	
	private static final long serialVersionUID = -3829719907267602516L;
	
	@Resource
	VisitSlowRecordService visitSlowRecordService;
	@Resource
	FunctionsService functionsService;
	
	private VisitSlowRecordDTO visitSlowRecordDTO ;
	

    public VisitSlowRecordDTO getVisitSlowRecordDTO() {
		return visitSlowRecordDTO;
	}


	public void setVisitSlowRecordDTO(VisitSlowRecordDTO visitSlowRecordDTO) {
		this.visitSlowRecordDTO = visitSlowRecordDTO;
	}


	/**
    *@Title:list
    *@Description:显示列表页面
    *@Params:@return
    *@Return:String
    *@author:赵巧鹤
    *@Date:2014-7-17上午10:30:07
    */
    public String list(){
	
	     return  "list";
     }
    @SuppressWarnings("unchecked")
	public String jsonPageList(){
    	LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		// 判断排序参数是否有值
		if (StringUtils.isNotBlank(this.getOrderProperty())
				&& StringUtils.isNotBlank(this.getOrderDirection())) {
			orderBy.put(this.getOrderProperty(), this.getOrderDirection());
		} else {// 如果页面没有要求排序方式，则设置按照会员编号排序
			orderBy.put("memId", "desc");
		}
		
		QueryResult<VisitSlowRecordDTO> queryRecord =visitSlowRecordService.queryAll((visitSlowRecordDTO.getPage()-1) * pageNum, pageNum, visitSlowRecordDTO, orderBy);
		List<VisitSlowRecordDTO> listDTO = queryRecord.getResultlist();
		List<List<String>> lists = new ArrayList<List<String>>();
		for (int i = 0; i < listDTO.size(); i++) {
			VisitSlowRecordDTO visitSlowRecordDTO = listDTO.get(i);
			List<String> list = new ArrayList<String>();
			list.add(String.valueOf(i + 1));
			/** 调用下面写的私有方法，封装的String方法 */
			list = retTradesView(list, visitSlowRecordDTO);
			lists.add(list);
		}
		PageView pageView = new PageView(visitSlowRecordDTO.getPage(),
				queryRecord.getTotalrecord());
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);
    }
    
    
    /**
    *@Title:retTradesView
    *@Description:TODO
    *@Params:@param perPntList
    *@Params:@param perPntGetRecordDTO
    *@Params:@return
    *@Return:List<String>
    *@author:赵巧鹤
    *@Date:2014-7-17下午05:10:30
    */
    private List<String> retTradesView(List<String> visitList,
    		VisitSlowRecordDTO visitSlowRecordDTO) {
    	
    	visitList.add(visitSlowRecordDTO.getArea());
    	visitList.add(visitSlowRecordDTO.getVisitIp());
    	visitList.add(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm:ss",
    			visitSlowRecordDTO.getAccessTime()));
    	visitList.add(visitSlowRecordDTO.getVisitSource());
    	visitList.add(visitSlowRecordDTO.getEntrancePage());
    	visitList.add(Utils.getString(visitSlowRecordDTO.getTotalMember()));
    	visitList.add(Utils.getString(visitSlowRecordDTO.getTotalIp()));
    	
    	return visitList;
    }
    
}
