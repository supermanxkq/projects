package com.paySystem.ic.service.base;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.Organs;
import com.paySystem.ic.web.dto.base.OrgansDTO;
import com.paySystem.ic.web.ui.OptionsString;

/**
 * @ClassName:OrgansService
 * @Description:机构Service接口
 * @date: 2013-12-7下午06:29:12
 * @author: 谢洪飞
 * @version: V1.0
 */
public interface OrgansService{

	/**
	 *@Title:validate
	 *@Description:检验数据库中是否已经存在该机构编号
	 *@param:@param organId
	 *@param:@return
	 *@return:boolean
	 *@thorws:
	 */
	public boolean validate(String organId);
	
	/**
	 *@Title:validateOrgName
	 *@Description:检查数据库中是否存在该机构名称
	 *@Param:@param organName 待检验的机构名称
	 *@Param:@return
	 *@Return:boolean
	 *@Throws:
	 */
	public boolean validateOrgName(String organName,String organId);
	
	/**
	 *@Title:addSaveOrg
	 *@Description:保存机构信息--新增
	 *@param:@param organsDTO
	 *@param:@return
	 *@return:Organs
	 *@thorws:
	 */
	public Organs addSaveOrg(OrgansDTO organsDTO);
	
	
	/**
	 *@Title:editSaveOrg
	 *@Description:保存机构信息--修改
	 *@param:@param organs
	 *@return:String
	 *@thorws:
	 */
	public void editSaveOrg(Organs organs);
	
	/**
	 *@Title:getOrganId
	 *@Description:获取新的机构编号
	 *@param:@return
	 *@return:String
	 *@thorws:
	 */
	public String getOrganId();
	
	/**
	 *@Title:getOption
	 *@Description:获取选项信息
	 *@param:@return
	 *@return:List<OptionsString>
	 *@thorws:
	 */
	public List<OptionsString> getOption();
	 
	public List<OptionsString> getTopOption();
	
	/**
	 *@Title:getOptionByMerId
	 *@Description:根据商户ID获取选项信息
	 *@param:@param merId
	 *@param:@return
	 *@return:List<OptionsString>
	 *@thorws:
	 */
	public List<OptionsString> getOptionByMerId(String merId);
	
	/**
	 *@Title:saveOrgan
	 *@Description:保存机构信息
	 *@param:@param organs
	 *@param:@throws Exception
	 *@return:void
	 *@thorws:
	 */
	public void saveOrgan(Organs organs) throws Exception;
	
	/**
	 *@Title:getTopOrganId
	 *@Description:获取顶级机构ID
	 *@param:@param organId
	 *@param:@return
	 *@return:String
	 *@thorws:
	 */
	public String getTopOrganId(String organId);
	
	/**
	 *@Title:find
	 *@Description:根据机构编号查找机构
	 *@param:@param organsId
	 *@param:@return
	 *@return:Organs
	 *@thorws:
	 */
	public Organs find(String organsId);
	
	/**
	 *@Title:queryOrgByCond
	 *@Description:根据条件查询机构信息
	 *@param:@param firstPage 起始条数
	 *@param:@param pageNum   最大条数
	 *@param:@param organsDTO organsDTO类
	 *@param:@param orderBy   排序方式Map
	 *@param:@return
	 *@param:@throws Exception
	 *@return:QueryResult<Organs>
	 *@thorws:
	 */
	QueryResult<Organs> queryOrgByCond(int firstPage,int pageNum,
			                           OrgansDTO organsDTO,
			                           LinkedHashMap<String, String> orderBy)
	                                                        throws Exception;

	public Date getSysTime();
	
	public static final String ORGANSSERVICE ="organsService";
	

}
