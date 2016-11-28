package com.paySystem.ic.web.action.goods;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.GoodsAttribute;
import com.paySystem.ic.bean.goods.AttrEntity;
import com.paySystem.ic.service.goods.AttrEntityService;
import com.paySystem.ic.utils.ExportUtil;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.base.GoodsAttributeDTO;
import com.paySystem.ic.web.dto.goods.AttrEntityDTO;
import com.paySystem.ic.web.ui.OptionsInteger;

/**
 * @ProjectName:omall
 * @ClassName:AttrEntityAction
 * @Description:商品属性值管理控制类
 * @date: 2014-10-10下午04:10:08
 * @author: 徐凯强
 * @version: V1.0
 */
@Controller("/base/attrEntity")
@Scope("prototype")
public class AttrEntityAction extends BaseAction {
	private static final long serialVersionUID = 4566492141509678748L;

	/**
	 * 日志
	 */
	private static final Log log = LogFactory.getLog(AttrEntityAction.class);
	/** 注入Service */
	@Resource
	private AttrEntityService attrEntityService;
	/** 上传的文件 **/
	private File upload;
	/** 上传文件的类型 **/
	private String uploadContentType;
	/** 上传文件名 **/
	private String uploadFileName;
    
	/**
	 * @return File
	 */
	public File getUpload() {
		return upload;
	}

	/**
	 * @param upload
	 */
	public void setUpload(File upload) {
		this.upload = upload;
	}

	/**
	 * @return String
	 */
	public String getUploadContentType() {
		return uploadContentType;
	}

	/**
	 * @param uploadContentType
	 */
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	/**
	 * @return String
	 */
	public String getUploadFileName() {
		return uploadFileName;
	}

	/**
	 * @param uploadFileName
	 */
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	/** 实例化DTO对象 */
	private AttrEntityDTO attrEntityDTO = new AttrEntityDTO();

	public AttrEntityDTO getAttrEntityDTO() {
		return attrEntityDTO;
	}

	public void setAttrEntityDTO(AttrEntityDTO attrEntityDTO) {
		this.attrEntityDTO = attrEntityDTO;
	}

	/** 商品属性值数据传输对象 */
	private GoodsAttributeDTO goodsAttributeDTO = new GoodsAttributeDTO();

	public GoodsAttributeDTO getGoodsAttributeDTO() {
		return goodsAttributeDTO;
	}

	public void setGoodsAttributeDTO(GoodsAttributeDTO goodsAttributeDTO) {
		this.goodsAttributeDTO = goodsAttributeDTO;
	}
	
	/**
	 *@Title:list
	 *@Description:异步加载列表页面
	 *@Return:String
	 *@author:徐凯强
	 *@Date:2014-10-10下午04:11:19
	 */
	public String list() throws Exception {
		/** 获取所有的属性的集合 */
		QueryResult<GoodsAttributeDTO> aQueryResultDTO = attrEntityService
				.findAll(-1, -1, goodsAttributeDTO, null);
		/** 从集合中获取所有属性名称 */
		List<OptionsInteger> names = new ArrayList<OptionsInteger>();
		/** 使用for循环将获取的属性名称添加到集合中 */
		for (int i = 0; i < aQueryResultDTO.getResultlist().size(); i++) {
			/** 获取集合中对象的属性名称并添加到集合中去 */
			names.add(new OptionsInteger(i, aQueryResultDTO.getResultlist()
					.get(i).getAttrName()));
		}
		/** 通过request对象进行传递到页面中 */
		this.getRequest().setAttribute("goodsAttributeName", names);
		return "list";
	}

	/**
	 *@MethodName:jsonPageList
	 *@Description:获取所有商品属性记录，并返回到GoodsAttributesList.jsp页面中
	 *@throws Exception抛出异常
	 *@author:徐凯强
	 *@return String Date:2014-10-12下午03:50:38
	 */
	@SuppressWarnings("unchecked")
	public String jsonPageList() throws Exception {
		/** 存储排序信息的集合 */
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		/** 如果获取排序顺序为空，设置为降序排列 */
		if (StringUtils.isNotBlank(this.getOrderProperty())
				&& StringUtils.isNotBlank(this.getOrderDirection())) {
			orderby.put(this.getOrderProperty(), this.getOrderDirection());
		} else {
			orderby.put("attrId", "desc");
		}
		/** 获取queryResult中的值 */
		QueryResult<GoodsAttributeDTO> quResult = attrEntityService.findAll(
				(goodsAttributeDTO.getPage() - 1) * pageNum, pageNum,
				goodsAttributeDTO, orderby);
		/** 获取queryResult中的集合 */
		List<GoodsAttributeDTO> dtoResult = quResult.getResultlist();
		/** 结算信息字符串信息集合 */
		List<List<String>> lists = new ArrayList<List<String>>();
		/** QueryResult对象的getResultlist方法获取GoodsAttributeDTO集合 */
		for (int i = 0; i < dtoResult.size(); i++) {
			GoodsAttributeDTO goodsAttributeDTO = dtoResult.get(i);
			List<String> strings = new ArrayList<String>();
			/** 添加到字符串集合中去 */
			/** 属性编号 */
			strings.add(Utils.getString(goodsAttributeDTO.getAttrId()));
			/** 属性名称 */
			strings.add(Utils.getString(goodsAttributeDTO.getAttrName()));
			/** 显示属性名称 */
			strings.add(Utils.getString(goodsAttributeDTO.getShowlable()));
			/** 是否枚举 */
			strings.add(Utils.getOptionsIntegerName(OptionsValue.ISEN_STATUS,
					goodsAttributeDTO.getIsEn()));
			/** 操作字段 */
			String operation = "";
			if(Utils.checkPermission("sy-1706-06")){
			operation += "<a href=javascript:void(0) onclick=viewData('"
					+ goodsAttributeDTO.getAttrId() + "') title='查看'>"
					+ Globals.IMG_VIEW + "</a> &nbsp;";
			}
			if(1!=goodsAttributeDTO.getStatus()){
				if(goodsAttributeDTO.getIsEn()==0){
				operation += "<a href=base/attrEntity!showAttrEntities?goodsAttributeDTO.attrId="
					+ goodsAttributeDTO.getAttrId()
					+ " title='添加枚举值'>"
					+ Globals.IMG_AUDIT + "</a>&nbsp;";
				}
			}
			if(Utils.checkPermission("sy-1706-05")&& 1!=goodsAttributeDTO.getStatus()){
				operation += "<a href=javascript:void(0) onclick=deleteData('"
					+ goodsAttributeDTO.getAttrId() + "') title='停用'>"
					+ Globals.IMG_DELETE + "</a> &nbsp;";
				}
			strings.add(operation);
			lists.add(strings);
		}
		/** 实例化PageView对象,获取分页的参数、总页数、总记录数 */
		PageView pageView = new PageView(goodsAttributeDTO.getPage(), quResult
				.getTotalrecord());
		/** 实例化ListInfoDTO */
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);
	}

	/**
	 *@Title:lookData
	 *@Description:查看方法
	 *@Params:@return
	 *@Return:String
	 *@author:王楠
	 *@Date:2014-11-28上午11:06:59
	 */
	public String lookData() throws Exception {
		GoodsAttribute ga = attrEntityService.find(goodsAttributeDTO
				.getAttrId());
		return Utils.printInfo(ga);
	}
	

	/**
	 *@Title:delete
	 *@Description:删除
	 *@Params:
	 *@Return:void
	 *@author:王楠
	 *@Date:2014-11-28下午05:01:52
	 */
	public void delete() throws Exception {
		attrEntityService.delete(goodsAttributeDTO.getAttrId());
	}

	/**
	*@Title:drop
	*@Description:停用枚举
	*@Params:@throws Exception
	*@Return:void
	*@author:王楠
	*@Date:2014-12-2下午02:42:57
	*/
	public void drop()throws Exception{
		attrEntityService.drop(attrEntityDTO.getAttrEnId());
	}
	/**
	 *@Title:showAttrEntities
	 *@Description:跳转到属性值界面
	 *@Return:String
	 *@author:徐凯强
	 *@Date:2014-10-12下午08:06:19
	 */
	public String showAttrEntities() {
		/** 传递goodsAttributeDTO，用于传递编号 */
		goodsAttributeDTO.setAttrName(attrEntityService
				.getAttrName(goodsAttributeDTO.getAttrId()));
		this.getRequest().setAttribute("goodsAttributeDTO", goodsAttributeDTO);
		return "show";
	}

	/**
	 *@MethodName:jsonPageListForAttrEntities
	 *@Description:获取所有商品属性值记录，并返回到AttrEntityList.jsp页面中
	 *@throws Exception抛出异常
	 *@author:徐凯强
	 *@return String Date:2014-10-12下午03:50:38
	 */
	@SuppressWarnings("unchecked")
	public String jsonPageListForAttrEntities() throws Exception {
		/** 存储排序信息的集合 */
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		/** 如果获取排序顺序为空，设置为降序排列 */
		if (StringUtils.isNotBlank(this.getOrderProperty())
				&& StringUtils.isNotBlank(this.getOrderDirection())) {
			orderby.put(this.getOrderProperty(), this.getOrderDirection());
		} else {
			orderby.put("attrEnId", "desc");
		}
		/** 获取queryResult中的值 */
		QueryResult<AttrEntityDTO> quResult = attrEntityService
				.showAttrEntities((attrEntityDTO.getPage() - 1) * pageNum,
						pageNum, attrEntityDTO, goodsAttributeDTO, orderby);
		/** 获取queryResult中的集合 */
		List<AttrEntityDTO> dtoResult = quResult.getResultlist();
		/** 结算信息字符串信息集合 */
		List<List<String>> lists = new ArrayList<List<String>>();

		/** QueryResult对象的getResultlist方法获取AttrEntityDTO集合 */
		goodsAttributeDTO.setAttrName(attrEntityService
				.getAttrName(goodsAttributeDTO.getAttrId()));

		for (int i = 0; i < dtoResult.size(); i++) {
			AttrEntityDTO attrEnDto = dtoResult.get(i);

			List<String> strings = new ArrayList<String>();
			/** 添加到字符串集合中去 */
			/** 属性值编号 */
			strings.add(Utils.getString(attrEnDto.getAttrEnId()));
			/** 属性值名称 */
			strings.add(Utils.getString(attrEnDto.getAttrEnName()));
			/** 属性名称 */
			strings.add(Utils.getString(goodsAttributeDTO.getAttrName()));
			/** 操作字段 */
			String operation = "";
			if(Utils.checkPermission("sy-1706-01")){
			operation += "<a href=javascript:void(0) onclick=detailData('"
					+ attrEnDto.getAttrEnId() + "') title='查看'>"
					+ Globals.IMG_VIEW + "</a> &nbsp;";
			}
			if(Utils.checkPermission("sy-1706-03")&&attrEnDto.getStatus()!=1){
				operation += "<a href=javascript:void(0) onclick=stopData('"
					+ attrEnDto.getAttrEnId() + "') title='停用'>"
					+ Globals.IMG_DELETE + "</a> &nbsp;";
			}
			strings.add(operation);
			lists.add(strings);
		}
		/** 实例化PageView对象,获取分页的参数、总页数、总记录数 */
		PageView pageView = new PageView(attrEntityDTO.getPage(), quResult
				.getTotalrecord());
		/** 实例化ListInfoDTO */
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));

		return Utils.printInfo(listInfoDTO);
	}

	/**
	*@Title:viewData
	*@Description:属性枚举值的查看
	*@Params:@return
	*@Params:@throws Exception
	*@Return:String
	*@author:王楠
	*@Date:2014-12-1下午05:59:06
	*/
	public String viewData() throws Exception {
		AttrEntity a = attrEntityService.findAttr(attrEntityDTO
				.getAttrEnId());
		return Utils.printInfo(a);
	}
	/**
	 *@Title:addUI
	 *@Description:跳转到添加属性值页面
	 *@Return:String返回字符串结果到struts.xml
	 *@author:徐凯强
	 *@Date:2014-10-13上午12:05:40
	 */
	public String addUI() {
		this.setMethod("addSave");
		return "input";
	}

	/**
	 *@Title:addSave
	 *@Description:添加
	 *@Return:String返回字符串结果到struts.xml
	 *@author:徐凯强
	 *@Date:2014-10-13上午12:05:40
	 */
	public String addSave() {
		attrEntityService.addSave(attrEntityDTO);
		this.getRequest().setAttribute("result",
				this.getText("operation.success.notice"));
		this.getRequest().setAttribute("url", "base/attrEntity!list");
		return SUCCESS;
	}

	/**
	 *@Description:异步保存属性值名称
	 *@return:String
	 *@author: 张亚运
	 *@throws:
	 */
	public String saveAttrEntity() {
		ReturnDTO returnDto = new ReturnDTO();
		attrEntityDTO.setStatus(0);
		attrEntityService.addSave(attrEntityDTO);
		returnDto.setFlag(true);
		return Utils.printInfo(returnDto);
	}

	/**
	 *@Description:检验属性名称是否存在
	 *@return:String
	 *@author: 张亚运
	 *@throws:
	 */
	public String checkAttrEnName() {
		ReturnDTO data = new ReturnDTO();
		boolean flag = attrEntityService.checkAttrEnName(attrEntityDTO
				.getAttrEnName(), attrEntityDTO.getAttrId());
		data.setFlag(flag);
		return Utils.printInfo(data);
	}

	/**
	 *@Title: uploadExcel
	 *@Description: 上传Excel文件
	 *@Return: void
	 *@author: 王少辉
	 *@Date: 2014-11-21 下午04:42:21
	 */
	public File uploadExcel() {
		// 获得上传路径
		String realPath = ServletActionContext.getServletContext().getRealPath(
				"/upload/excel/");

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
	 *@Title: Export
	 *@Description: 导入商品属性值
	 *@Return: String 返回导入结果
	 *@author: 王少辉
	 *@Date: 2014-11-20 上午09:49:44
	 */
	public String export() {
		ReturnDTO dto = new ReturnDTO();
		String path = getServletContext().getRealPath("/");
		File file = new File(path+"/upload/excel/导入商品属性及属性值模板.xls");
		List<Object> lists = ExportUtil.exportExcel(file);

		if (CollectionUtils.isEmpty(lists)) {
			dto.setMsg("error");
			return Utils.printInfo(dto);
		}
		
		// 封装商品属性
		List<GoodsAttributeDTO> goodsAttrList = new ArrayList<GoodsAttributeDTO>();
		GoodsAttributeDTO goodsAttr = null;
		// 封装商品属性值
		List<AttrEntityDTO> attrEnList = new ArrayList<AttrEntityDTO>();
		AttrEntityDTO attrEn = null;
		for (int i = 0; i < lists.size(); i++) {
			Object obj = lists.get(i);

			if (null == obj) {
				log.error("Error! AttrEntityAction.Export() encounted an error! Data reading exception!");
				dto.setMsg("error");
				return Utils.printInfo(dto);
			}

			// 取每行数据，转换数组结构
			String[] str = obj.toString().split(",");

			// 遇到非有效数据行时停止操作
			if (str.length != 3 && str.length != 4) {
				break;
			}

			// 分开封装商品属性、商品属性值
			goodsAttr = new GoodsAttributeDTO();
			attrEn = new AttrEntityDTO();
			goodsAttr.setAttrName(Utils.getString(str[0]));
			if ("是".equals(Utils.getString(str[1]))) {
				goodsAttr.setIsEn(1);
				attrEn.setAttrEnName(str[3]);
			} else {
				goodsAttr.setIsEn(0);
			}
			goodsAttr.setShowlable(Utils.getString(str[2]));

			goodsAttrList.add(goodsAttr);
			attrEnList.add(attrEn);
		}

		GoodsAttributeDTO goodsAttrDTO = null;
		AttrEntityDTO attrEnDTO = null;
		for (int i = 0; i < goodsAttrList.size(); i++) {
			goodsAttrDTO = goodsAttrList.get(i);
			attrEnDTO = attrEnList.get(i);
			try {
				attrEntityService.saveAttrAndAttrValue(goodsAttrDTO, attrEnDTO);
				
				dto.setMsg("success");
				
				// 上传成功后删除文件
				if (file.exists()) {
					file.delete();
				}
			} catch (Exception e) {
				log
						.error((i + 2)
								+ " | Data save failed! Please see AttrEntityServiceImpl.saveAttrAndAttrValue()!");
				dto.setMsg("error");
			}
		}

		return Utils.printInfo(dto);
	}
}
