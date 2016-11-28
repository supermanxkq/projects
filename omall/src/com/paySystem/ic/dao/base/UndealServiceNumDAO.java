package com.paySystem.ic.dao.base;

import com.paySystem.ic.bean.base.UndealServiceNum;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.base.UndealServiceNumDTO;

/**
 * @ProjectName:omall
 * @ClassName:UndealServiceDAO
 * @Description:商城待处理的业务查看的DAO接口
 * @date: 2014-10-22
 * @author: 王楠
 * @version: V1.0
 */
public interface UndealServiceNumDAO extends DAO<UndealServiceNum>{
	
	public static final String UNDEALSERVICENUMDAO="undealServiceNumDAO";
	
    /**
    *@Title:findList
    *@Description:查询数据的方法
    *@Params:@return
    *@Params:@throws Exception
    *@Return:UndealServiceNumDTO
    *@author:王楠
    *@Date:2014-10-22下午04:51:24
    */
    public UndealServiceNumDTO findList(String merId)throws Exception;
    
    /**
    *@Title:maidtainData
    *@Description:用于维护待处理业务统计信息的方法
    *@Params:@param merId 商户编号
    *@Params:@param undealServiceNum 待处理业务统计的实体
    *@Params:@return
    *@Params:@throws Exception
    *@Return:UndealServiceNum
    *@author:王楠
    *@Date:2014-11-24下午05:48:49
    */
    public UndealServiceNum maintainData(String merId,UndealServiceNum undealServiceNum,Integer sign)throws Exception;
    

    /**
    *@Title:findTotalData
    *@Description:用于机构登陆时查找数据的方法
    *@Params:String organId
    *@Params:@throws Exception
    *@Return:UndealServiceNum 待处理的业务统计实体
    *@author:王楠
    *@Date:2014-11-25上午09:44:14
    */
    public UndealServiceNumDTO findTotalData(String organId)throws Exception;

	/**
	*@Title:findByOrgMerId
	*@Description:通过机构和商户号查询，初始化用
	*@Params:@param orgMerId
	*@Params:@return
	*@Return:UndealServiceNum
	*@author:孟凡岭
	*@Date:2014-12-13下午03:03:20
	*/
	public UndealServiceNum findByOrgMerId(String orgMerId);
}
