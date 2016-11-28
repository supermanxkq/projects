package com.paySystem.ic.web.action.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.google.gson.Gson;
import com.paySystem.ic.bean.system.Area;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.service.system.AreaService;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.system.AreaDTO;

/**
 * 区域管理
 * @author
 * @version
 */
@Controller("/system/area")
@Scope("prototype")
public class AreaAction extends BaseAction {
	private static final long serialVersionUID = -3051940652987671139L;
	@Resource AreaService areaService;
	@Resource FunctionsService functionsService;
	
	private AreaDTO areaDTO = new AreaDTO();
	
	public AreaDTO getAreaDTO() {
		return areaDTO;
	}

	public void setAreaDTO(AreaDTO areaDTO) {
		this.areaDTO = areaDTO;
	}

	public String list(){
		this.getRequest().setAttribute("statusValues",OptionsValue.VISIBLE_STATUS );
		return "list";
	}
	
	public String getList(){
		List<AreaDTO> areaDTOs = new ArrayList<AreaDTO>();
		List<Area> list = areaService.findAll();
		for (Area area : list) {
			AreaDTO areaDTO = new AreaDTO();
			areaDTO.setId(area.getId());
			areaDTO.setDisplayName(area.getDisplayName());
			areaDTO.setAreaCode(area.getAreaCode());
			areaDTO.set_parentId(area.getParentId());
			if(Utils.checkPermission("sy-9201-02")){
				areaDTO.setOperate("<a href=javascript:addUI('"+area.getDisplayName()+"','"+area.getId()+"');>增加子地区</a> <a href=javascript:loadData('"+area.getId()+"');>修改</a> <a href=javascript:deleteData('system/area!delete','"+area.getId()+"');>删除</a>");
			}
			
			areaDTOs.add(areaDTO);
		}
		
		HttpServletResponse response = this.getResponse();
		Gson gson = new Gson();
		String result = gson.toJson(areaDTOs);
		response.setContentType("text/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache"); // 取消浏览器缓存
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print("{\"rows\":"+result+"}");
		out.flush();
		out.close();
		return null;
	}
	
	public String add(){
		
		try {
			Area area = new Area();
			area.setDisplayName(areaDTO.getDisplayName());
			area.setParentId(areaDTO.getParentId());
			area.setAreaCode(areaDTO.getAreaCode());
			area.setStatus(areaDTO.getStatus());
			area.setAreaLevel(areaService.getLevel(area.getParentId(), 1));
			area.setUpdateTime(new Date());
			areaService.save(area);
			functionsService.saveFunction("地区管理", 1, "新增地区："+area.getDisplayName());
			this.ajaxResult = "ajaxsuccess";
		} catch (Exception e) {
			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}
		return this.ajaxResult;
	}
	
	public String editUI(){
		
		Area area = areaService.find(areaDTO.getId());
		if(area!=null){
			if(area.getParentId().equals("0")){
				areaDTO.setParentName("顶级地区");
			}else{
				Area parent = areaService.find(area.getParentId());
				areaDTO.setParentName(parent.getDisplayName());
			}
			areaDTO.setDisplayName(area.getDisplayName());
			areaDTO.setFullName(area.getFullName());
			areaDTO.setParentId(area.getParentId());
			areaDTO.setAreaCode(area.getAreaCode());
			areaDTO.setStatus(area.getStatus());
			this.objResult = areaDTO;
			
			return "objResult";
		}
		return ERROR;
	}
	
	public String update(){
		try {
			Area area = areaService.find(areaDTO.getId());
			if(area!=null){
				area.setDisplayName(areaDTO.getDisplayName());
				area.setFullName(areaDTO.getFullName());
				area.setAreaCode(areaDTO.getAreaCode());
				area.setStatus(areaDTO.getStatus());
				area.setUpdateTime(new Date());
				areaService.update(area);
				functionsService.saveFunction("地区管理", 2, "修改地区："+area.getDisplayName());
				this.ajaxResult = "ajaxsuccess";
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}
		return this.ajaxResult;
	}
	
	public String delete(){
		
		try {
			List<Area> list = areaService.findByJpl("from Area o where o.parentId = "+this.getId());
			if(list!=null&&!list.isEmpty()){
				this.ajaxResult = "ajaxfailure";
				this.msgResult = "存在子地区，不能删除！";
			}else{
				areaService.delete(Integer.valueOf(this.getId()));
				functionsService.saveFunction("地区管理", 3, "删除地区："+areaService.find(Integer.valueOf(this.getId())).getDisplayName());
				this.ajaxResult = "ajaxsuccess";
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}
		return this.ajaxResult;
	}

}