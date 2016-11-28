package com.paySystem.ic.dao.base;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.Organs;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.base.OrgansDTO;
import com.paySystem.ic.web.ui.OptionsString;

/**
 * @ClassName:OrgansDao
 * @Description:机构管理Dao接口
 * @date: 2013-12-7上午11:41:21
 * @author: 谢洪飞
 * @version: V1.0
 */
public interface OrgansDao extends DAO<Organs>{
	

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
	 *@Description:检查数据库中是否已经存在该机构名称
	 *@Param:@param orgName
	 *@Param:@return
	 *@Return:boolean
	 *@Throws:谢
	 */
	public boolean validateOrgName(String orgName,String organId);
	
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
	 *@Title:editSaverOrg
	 *@Description:保存机构信息--修改
	 *@param:@param organs
	 *@return:void
	 *@thorws:
	 */
	public void editSaverOrg(Organs organs);
	
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
	 *@Title:queryOrgByCond
	 *@Description:根据条件查询机构
	 *@param:@param firstIndex
	 *@param:@param pageNum
	 *@param:@param organsDTO
	 *@param:@param orderBy
	 *@param:@return
	 *@return:QueryResult<Organs>
	 *@thorws:
	 */
	public QueryResult<Organs> queryOrgByCond(int firstIndex,int pageNum,OrgansDTO organsDTO,LinkedHashMap<String, String> orderBy) throws Exception;
	
	public static final String ORGANSDAO = "organsDao";
	
	/**
	 * 
	 *@Title:queryByName
	 *@Description:根据名字查询一个集合
	 *@param:@param name
	 *@param:@return
	 *@return:List<Organs>
	 *@author:井建国
	 *@thorws:
	 */
	public List<Organs> queryByName(String name);
	
	public Organs findById(String orgId);
}
