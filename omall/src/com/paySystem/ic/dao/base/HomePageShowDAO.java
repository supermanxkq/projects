package com.paySystem.ic.dao.base;

import com.paySystem.ic.bean.base.Organs;
import com.paySystem.ic.bean.base.StoreInfo;
import com.paySystem.ic.bean.base.UndealServiceNum;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.base.HomePageShowDTO;

/**
 * @ProjectName:omall
 * @ClassName:HomePageShowDAO
 * @Description:首页内容展示的DAO接口
 * @date: 2014-11-21
 * @author: 王楠
 * @version: V1.0
 */
public interface HomePageShowDAO extends DAO<HomePageShowDTO>{
	
    public static final String HOMEPAGESHOWDAO="homePageShowDAO";
    
    /**
    *@Title:findById
    *@Description:根据机构编号查询机构信息
    *@Params:@param organId 机构编号
    *@Params:@return
    *@Return:Organs
    *@author:王楠
    *@Date:2014-11-21上午11:39:49
    */
    public Organs findByOrgId(String organId)throws Exception;
    
    /**
    *@Title:findByStoId
    *@Description:根据商户编号查询店铺信息
    *@Params:@param merId 店铺编号
    *@Params:@return
    *@Params:@throws Exception
    *@Return:StoreInfo
    *@author:王楠
    *@Date:2014-11-21上午11:41:26
    */
    public StoreInfo findByStoId(String merId)throws Exception;
    
}
