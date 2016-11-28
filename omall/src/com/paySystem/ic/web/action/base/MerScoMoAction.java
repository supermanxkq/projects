package com.paySystem.ic.web.action.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.service.base.MerScoMoService;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.UploadUtil;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.base.MerScoMoDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * @ClassName:MerchantsAction
 * @Description:商户评分模型Action类
 * @date: 2013-11-02上午10:52:32
 * @author: 张军磊
 * @version: V1.0
 */
@Controller("/base/merscomo")
@Scope("prototype")
public class MerScoMoAction extends BaseAction {
	public static final String MERSCOMOSERVICE = "merScoMoService";
	private static final long serialVersionUID = 8061217647380748369L;
	@Resource
	MerScoMoService merScoMoService;

	private MerScoMoDTO merScoMoDTO = new MerScoMoDTO();

	public MerScoMoDTO getMerScoMoDTO() {
		return merScoMoDTO;
	}

	public void setMerScoMoDTO(MerScoMoDTO merScoMoDTO) {
		this.merScoMoDTO = merScoMoDTO;
	}

	private File image; // 上传的文件
	private String imageFileName; // 文件名称
	private String imageContentType; // 文件类型

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

	/**
	 * 列表页面
	 * 
	 * @version 2011-9-8 下午08:50:23
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		UserSession us = Utils.getUserSession();
		if (us.getUserLevel() == 0) {
			return "list";
		}
		return "intercepthtml";

	}

	/**
	 *@Title:addUI
	 *@Description:跳转到增加页面
	 *@Params:@return
	 *@Return:String
	 *@author:张军磊
	 *@Date:2014-9-9下午04:38:06
	 */
	public String addUI() {
		this.setMethod("addSave");

		return INPUT;
	}

	/**
	 *@Title:saveMerScoMo
	 *@Description: 保存商户评分模型业务
	 *@param:@param merScoMo 商户评分模型业务参数实体对象
	 *@Return:void
	 *@author: 张军磊
	 * @throws FileNotFoundException
	 *@Thorws:
	 */
	public String addSave() throws Exception {

		FileOutputStream fos = null;
		FileInputStream fis = null;

		
		
		
		String urlString=  UploadUtil.uploadImg(this.getImage(), this.getImageFileName());
		merScoMoDTO.setShowPic(urlString);
		merScoMoDTO.setOperatorId(Utils.getUserSession().getUserName());
		// merScoMoDTO.setUpdateTime(new Date());
		merScoMoDTO.setCreateTime(new Date());
		merScoMoDTO.setStatus(1);
		// merScoMoService.updateMerScoMo(merScoMoDTO);
		merScoMoService.saveMerScoMo(merScoMoDTO);

		return "list";
	};

	/**
	 * 列表查询
	 * 
	 *@Title:jsonPageList
	 *@Description: 根据列表界面条件对数据进行分页查询
	 *@param:@return
	 *@param:@throws Exception
	 *@return:String
	 *@author: 张军磊
	 *@Thorws:
	 */
	@SuppressWarnings("unchecked")
	public String jsonPageList() throws Exception {

		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();

		// 封装排序方式参数，如界面传递排序方式则
		if (StringUtils.isNotBlank(this.getOrderProperty())
				&& StringUtils.isNotBlank(this.getOrderDirection())) {

			orderby.put(this.getOrderProperty(), this.getOrderDirection());

		} else {
			orderby.put("minSco", "asc");
		}

		// 调用物流公司Service，根据条件查询物流公司信息列表及分页信息
		QueryResult<MerScoMoDTO> dtoResult = merScoMoService
				.queryMerScoMoByCond((merScoMoDTO.getPage() - 1) * pageNum,
						pageNum, merScoMoDTO, orderby);

		// 获取物流公司DTO对象集合
		List<MerScoMoDTO> merScoMoDTOList = dtoResult.getResultlist();
		List<List<String>> lists = new ArrayList<List<String>>();

		// 遍历DTO对象集合并生成要输出到界面的信息
		for (int i = 0; i < merScoMoDTOList.size(); i++) {

			List<String> strings = new ArrayList<String>();

			MerScoMoDTO merScoMoDTO = merScoMoDTOList.get(i);

			strings.add(String.valueOf(i + 1));

			strings.add(merScoMoDTO.getMoName());
			strings.add(merScoMoDTO.getMinSco().toString());
			strings.add(merScoMoDTO.getMaxSco().toString());

			strings.add("<img height='30px' width='50px'  src=" + merScoMoDTO.getShowPic() + " />");

			String operation = "";

			// 判断状态是否为 "删除" 状态，如不是删除状态 则可进行修改及删除操作
			if (merScoMoDTO.getStatus().equals(1)) {
				// 检查是否有物流公司查看权限，如有则显示"查看"按钮
				if (Utils.checkPermission("sy-1202-01")) {
					operation += "<a href=base/merscomo!checkUI?merScoMoDTO.merScoMoId="
							+ merScoMoDTO.getMerScoMoId()
							+ " title='查看'>"
							+ Globals.IMG_VIEW + "</a>&nbsp;";
				}

				// 检查是否有物流公司修改权限，如有则显示"修改"按钮
				if (Utils.checkPermission("sy-1202-03")) {

					operation += "<a href=base/merscomo!editUI?merScoMoDTO.merScoMoId="
							+ merScoMoDTO.getMerScoMoId()
							+ " title='修改'>"
							+ Globals.IMG_EDIT + "</a>&nbsp;";

				}
				// 检查是否有物流公司删除权限，如有则显示"删除"按钮
				if (Utils.checkPermission("sy-1202-04")) {

					operation += "<a href=javascript:deleteData('base/merscomo!delete','"
							+ merScoMoDTO.getMerScoMoId()
							+ "') title='删除'>"
							+ Globals.IMG_DELETE + "</a>&nbsp;";
				}

			} else {
				// 如果本条记录状态为"删除"状态，则操作员只可以对本条记录进行查看操作
				if (Utils.checkPermission("sy-1202-01")) {
					operation += "<a href=base/merscomo!checkUI?merScoMoDTO.merScoMoId="
							+ merScoMoDTO.getMerScoMoId()
							+ " title='查看'>"
							+ Globals.IMG_VIEW + "</a>&nbsp;";
				}
			}

			strings.add(operation);
			lists.add(strings);

		}

		PageView pageView = new PageView(merScoMoDTO.getPage(), dtoResult
				.getTotalrecord());

		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		// 向前台输出查询数据结果集字符串
		return Utils.printInfo(listInfoDTO);
	}

	/**
	 * 
	 * 
	 * @Title: checkUI
	 * @Description: 跳转到商户评分信息的查看页面
	 * @Param: @return
	 * @Return: String
	 * @Author: 张军磊
	 * @Thorws:
	 */
	public String checkUI() {

		this.setMethod("checkUI");

		merScoMoDTO = merScoMoService.findById(merScoMoDTO.getMerScoMoId());

		return INPUT;
	}

	/**
	 * 修改界面跳转
	 * 
	 *@Title:editUI
	 *@Description:跳转到商户评分模型的修改界面
	 *@param:@return
	 *@return:String
	 *@author: 张军磊
	 *@Thorws:
	 */
	public String editUI() {

		this.setMethod("editSave");

		// 根据Id获取支付参数信息DTO对象
		merScoMoDTO = merScoMoService.findById(merScoMoDTO.getMerScoMoId());

		return INPUT;
	}

	/**
	 *@Title:updateMerScoMo
	 *@Description: 更新商户评分模型业务参数信息
	 *@param:@param merScoMo 商户评分模型业务参数实体对象
	 *@Return:void
	 *@author: 张军磊
	 *@Thorws:
	 */
	public String editSave() throws Exception {

		if (getImageFileName() != null) {

			String urlString=  UploadUtil.uploadImg(this.getImage(), this.getImageFileName());
			merScoMoDTO.setShowPic(urlString);
		}
		merScoMoDTO.setOperatorId(Utils.getUserSession().getUserName());
		merScoMoDTO.setUpdateTime(new Date());
		merScoMoDTO.setStatus(1);
		merScoMoService.updateMerScoMo(merScoMoDTO);

		return "list";
	};

	/**
	 *@Title:delete
	 *@Description:删除商户评分信息
	 *@Params:@return
	 *@Return:String
	 *@author:张军磊
	 *@Date:2014-9-30上午10:53:58
	 */
	public String delete() {

		try {

			MerScoMoDTO merScoMoDTO = merScoMoService
					.deleteByMerScoMoId(Integer.parseInt(this.id));

			this.ajaxResult = "ajaxsuccess";

		} catch (Exception e) {

			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}

		return this.ajaxResult;
	}

	/**
	 *@Title:checkmoName
	 *@Description:验证商户名称是否重复
	 *@Params:@return
	 *@Return:String
	 *@author:张军磊
	 *@Date:2014-9-30上午10:53:20
	 */
	public String checkmoName() {
		ReturnDTO dto = new ReturnDTO();
		/** 获取传递过来的Json数据 */
		Integer merScoMoId = merScoMoDTO.getMerScoMoId();
		String moName = merScoMoDTO.getMoName();
		String method = this.getMethod();
		/** 设置返回标志信息 */
		dto.setFlag(merScoMoService
				.findmerScoMoName(moName, method, merScoMoId));
		return Utils.printInfo(dto);
	}

	/**
	 *@Title:checkMaxNum
	 *@Description:验证分数是否已经有集合存在
	 *@Params:@return
	 *@Return:String
	 *@author:张军磊
	 *@Date:2014-9-30上午10:52:49
	 */
	public String checkMaxNum() {
		ReturnDTO dto = new ReturnDTO();
		/** 获取传递过来的Json数据 */
		Integer merScoMoId = merScoMoDTO.getMerScoMoId();
		Integer maxSco = merScoMoDTO.getMaxSco();
		Integer minSco = merScoMoDTO.getMinSco();
		String method = this.getMethod();
		if (merScoMoService.findMaxSco(minSco, method, merScoMoId) == false) {

			if (merScoMoService.findMaxSco(maxSco, method, merScoMoId) == false) {

				dto.setTotal(1);
			} else {

				dto.setTotal(3);

			}

		} else {

			dto.setTotal(2);

		}
		return Utils.printInfo(dto);
	}

}
