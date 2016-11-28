package com.paySystem.ic.web.action.base;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.base.GoodsFamily;
import com.paySystem.ic.service.base.GoodsFamilyService;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.utils.ExportUtil;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.UploadUtil;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.base.GoodsFamilyDTO;


/**
 * @ProjectName:omall
 * @ClassName:UploadGoodsFamilyAction
 * @Description:导入商品分类
 * @date: 2014-11-24
 * @author: 张军磊
 * @version: V1.0
 */
@Controller("/base/uploadGoodsFamily")
@Scope("prototype")
public class UploadGoodsFamilyAction extends BaseAction {

	private static final long serialVersionUID = -3880301662755892249L;

	@Resource
	GoodsFamilyService goodsfamilyService;

	@Resource
	FunctionsService functionsService;

	/** 上传的文件 **/
	private File upload;

	/** 上传文件的类型 **/
	private String uploadContentType;

	/** 上传文件名 **/
	private String uploadFileName;

	private Integer count=0;
	
	/**
	 *@Description:进入List页面
	 *@return:String
	 *@author: 张亚运
	 *@throws:
	 */
	public String list() {
		// this.getRequest().setAttribute("statusValues",
		// OptionsValue.STATE_STATUS);
		this.getRequest().setAttribute("statusValues",
				OptionsValue.STATE_STATUS);
		return "list";
	}

	
	
	/**
	*@Title:uploadExcel
	*@Description:上传图片
	*@Params:@return
	*@Return:File
	*@author:张军磊
	*@Date:2014-11-24下午12:15:02
	*/
	public File uploadExcel(){ 
		// 获得上传路径  
        String realPath = ServletActionContext.getServletContext().getRealPath("/upload/excel/");
		
        File saveFile = null;
		if (getUpload() != null) {
			
			saveFile = new File(new File(realPath), uploadFileName);
			if (!saveFile.getParentFile().exists()) {  
                saveFile.getParentFile().mkdirs();  
            }
			
			// 上传文件
			try {
				FileUtils.copyFile(upload, saveFile);
			} catch (IOException e1) {
				log.error("Upload excel file error!");
			}
		}
		
		return saveFile;
	}
	
	  /**
	    *@Title:savaExcelGood
	    *@Description:批量导入商品分类
	    *@Params:@return
	    *@Return:String
	    *@author:张军磊
	    *@Date:2014-11-20上午11:19:44
	    */
	        public String savaExcelGood() throws Exception{
	        	Map<String,Object> resultMap = new HashMap<String,Object>();
	        	this.getRequest().setAttribute("statusValues",
	    				OptionsValue.STATE_STATUS);
	        	List<Object> lists = ExportUtil.exportExcel(uploadExcel());
	        	//封装系统分类
	        	List<GoodsFamilyDTO> goodsFamilyDTOList = new ArrayList<GoodsFamilyDTO>();
	        	String result = "";
	        	if(null!=lists){
	        		for (int i = 0; i < lists.size(); i++) {
	        			GoodsFamilyDTO goodsFamilyDTO=new GoodsFamilyDTO();
		    			Object obj = lists.get(i);
		    			if (null == obj) {
		    				log.error("Error! AttrEntityAction.Export() encounted an error! Data reading exception!");
		    				result="导入失败";
		    				this.getRequest().setAttribute("result",result);
		    				resultMap.put("result", result);
		    				return Utils.printInfoWithoutType(resultMap);
		    			}
		    			// 取每行数据，转换数组结构
		    			String[] str = obj.toString().split(",");
		    			
		    			//判断商品分类名称
		    			if("".equals(Utils.getString(str[0]))){
		    				continue;	
		    			}else{
		    				goodsFamilyDTO.setFamilyName(Utils.getString(str[0]));
		    				
		    			}
		    			//判断商品上级分类名称
		    			if("".equals(Utils.getString(str[1]))){
		    				continue;	
		    			}else{
		    				goodsFamilyDTO.setPFamilyName(Utils.getString(str[1]));
		    				
		    			}
		    			//判断商品使用状态
		    			if ("启用".equals(Utils.getString(str[2]))) {
		    				goodsFamilyDTO.setStatus(1);
		    			} else if("禁用".equals(Utils.getString(str[2]))){
		    				goodsFamilyDTO.setStatus(2);
		    			}else{
		    				continue;
		    			}
		    			//判断是否展开
		    			if ("是".equals(Utils.getString(str[4]))) {
		    				goodsFamilyDTO.setDefaultShow(1);
		    			} else if("否".equals(Utils.getString(str[4]))){
		    				goodsFamilyDTO.setDefaultShow(2);
		    			}else{
		    				continue;
		    			}
		    			//图片路径
		    			goodsFamilyDTO.setPicPath(Utils.getString(str[3]));
		    			//关键字
		    			goodsFamilyDTO.setKeyWords(Utils.getString(str[5]));
		    			goodsFamilyDTO.setFamilyWay(1);
		    			goodsFamilyDTO.setCreateFloorSign(0);
		    			goodsFamilyDTOList.add(goodsFamilyDTO);
		    		}
	        		
	        		for(int j=0;j<goodsFamilyDTOList.size();j++){
	        		//判断该商品分类是否存在，如果存在则跳过不保存	
	        	    List<GoodsFamilyDTO> findGoodsFamilyDTO=goodsfamilyService.findGoodsFamilyByName(goodsFamilyDTOList.get(j).getFamilyName());
	        		if(findGoodsFamilyDTO.size()>0){
	        			continue;
	        		}else{
	        			if("顶级分类".equals(goodsFamilyDTOList.get(j).getPFamilyName())){
	        				
	        				 this.saveCGood(goodsFamilyDTOList.get(j));	
	        				
	        			}else{
	        				
	        				//判断该商品分类的上级分类是否存在
		        			List<GoodsFamilyDTO> goodsFamilyDTOListfis =goodsfamilyService.findGoodsFamilyByName(goodsFamilyDTOList.get(j).getPFamilyName());
		        			//如果存在，则直接进行保存
		        			if(goodsFamilyDTOListfis.size()>0){
		        				
		        			  this.saveCGood(goodsFamilyDTOList.get(j));	
		        				
		        			}
		        			//如果不存在，则查看Excel中，有没有该分类的上级分类
		        			else{
		        				saveUnCGood(goodsFamilyDTOList.get(j),goodsFamilyDTOList);
		        			}	

	        			}

	        		} 
  
	        		}      	
	        	}
	        	result = "导入成功,共"+lists.size()+"条，成功"+count+"条";
	        	this.getRequest().setAttribute("result",result);
	        	resultMap.put("result", result);
				return Utils.printInfoWithoutType(resultMap);
	    	}
	        /**
	        *@Title:saveUnCGood
	        *@Description: 保存Excel如果上级分类不存在的商品分类
	        *@Params:@param unGoodsFamilyDTO
	        *@Params:@param goodsFamilyDTOListTwo
	        *@Params:@throws Exception
	        *@Return:void
	        *@author:张军磊
	        *@Date:2014-11-24下午12:15:26
	        */
	        public void saveUnCGood(GoodsFamilyDTO unGoodsFamilyDTO,List<GoodsFamilyDTO> goodsFamilyDTOListTwo ) throws Exception {
	        	if("顶级分类".equals(unGoodsFamilyDTO.getPFamilyName())){
	        		unGoodsFamilyDTO.setNodeLevel(1);
	        		unGoodsFamilyDTO.setParentId(0);
	    		}else{
	    			//对Excel文件进行遍历,查看有没有该商品分类的上级分类
	    			for(int i=0;i<goodsFamilyDTOListTwo.size();i++){
	    				//如果Excel 文件中存在该商品分类的上级分类
	    				if(unGoodsFamilyDTO.getPFamilyName().equals(goodsFamilyDTOListTwo.get(i).getFamilyName())){
	    					//查看该上级分类是否存在
	    					List<GoodsFamilyDTO> findGoodsFamilyDTOThr=goodsfamilyService.findGoodsFamilyByName(goodsFamilyDTOListTwo.get(i).getPFamilyName());	
	    					if(findGoodsFamilyDTOThr.size()>0){
	    						//如果该上级分类存在,则直接保存
	    						this.saveCGood(goodsFamilyDTOListTwo.get(i));
	    						break;
	    					}
	    					//如果不存在则进行迭代
	    					else{
	    						
	    						this.saveUnCGood(goodsFamilyDTOListTwo.get(i), goodsFamilyDTOListTwo);
	    						
	    					}
	    					
	    				}
	    			}
	    			//如果迭代不存在则将该商品分类设置成顶级分类
	    			unGoodsFamilyDTO.setPFamilyName("顶级分类");
	    			//设置成一级分类
	    			unGoodsFamilyDTO.setNodeLevel(1);
	    			//将父ParentId设置成0
	    			unGoodsFamilyDTO.setParentId(0);
	    			//进行保存
	    			saveCGood(unGoodsFamilyDTO);
	    		}
                
	        }
	
	    	/**
	    	*@Title:saveCGood
	    	*@Description:保存顶级商品分类或者已经存在上级分类的商品分类
	    	*@Params:@param goodsFamilyDTO
	    	*@Params:@throws Exception
	    	*@Return:void
	    	*@author:张军磊
	    	*@Date:2014-11-24下午12:15:50
	    	*/
	    	public void saveCGood(GoodsFamilyDTO goodsFamilyDTO) throws Exception {
	    		  //如果为顶级分类
	    		if("顶级分类".equals(goodsFamilyDTO.getPFamilyName())){
	    			//将等级分类设为一级分类
	    			goodsFamilyDTO.setNodeLevel(1);
	    			//将登记分类的父ID 设置为0
	    			goodsFamilyDTO.setParentId(0);
	    		}else{
	    			//查询该分类的上级分类
	    			List<GoodsFamilyDTO> findGoodsFamilyDTO=goodsfamilyService.findGoodsFamilyByName(goodsFamilyDTO.getPFamilyName());
	    			//得到上级分类
                    GoodsFamilyDTO findGoodsFamily=findGoodsFamilyDTO.get(0);
                    goodsFamilyDTO.setParentId(findGoodsFamily.getFamilyId());
                    goodsFamilyDTO.setNodeLevel(findGoodsFamily.getNodeLevel()+1);
	    		}
	    		if("".equals(goodsFamilyDTO.getPicPath())){
	    			
	    			
	    		}else{
	    		File file1=new File(goodsFamilyDTO.getPicPath());
	    		     if(file1.exists()){
	    		    	
	    		    String pinName=goodsFamilyDTO.getPicPath().substring(goodsFamilyDTO.getPicPath().lastIndexOf("."));
	    		    	String imageUrl = UploadUtil.uploadImg(file1, pinName);
	    		    	 goodsFamilyDTO.setPicPath(imageUrl); 
	    		     }else{
	    		    	 
	    		    	 goodsFamilyDTO.setPicPath(""); 
	    		    	 
	    		     } 
	          	}      
	    		GoodsFamily goodsFamily = goodsfamilyService.saveGoodsFamily(goodsFamilyDTO);
		           functionsService.saveFunction("商品分类管理", 1, "添加商品分类："+ goodsFamily.getFamilyId());
		           count++;
		              //this.getRequest().setAttribute("result",this.getText("operation.success.notice"));
		              //this.getRequest().setAttribute("url", "base/goodsfamily!list");
	    	}

			public File getUpload() {
				return upload;
			}

			public void setUpload(File upload) {
				this.upload = upload;
			}

			public String getUploadContentType() {
				return uploadContentType;
			}

			public void setUploadContentType(String uploadContentType) {
				this.uploadContentType = uploadContentType;
			}

			public String getUploadFileName() {
				return uploadFileName;
			}

			public void setUploadFileName(String uploadFileName) {
				this.uploadFileName = uploadFileName;
			}
	        
	        
	

	
}
