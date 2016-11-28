package com.paySystem.ic.service.base;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.web.dto.base.GoodsAttributeDTO;
import com.paySystem.ic.web.dto.base.GoodsFamilyAttrRelaDTO;
import com.paySystem.ic.web.dto.base.GoodsFamilyDTO;
import com.paySystem.ic.web.dto.base.GoodsFamilyGroupRelaDTO;
import com.paySystem.ic.web.dto.base.GoodsFormatDTO;
import com.paySystem.ic.web.dto.base.GoodsFormatGroupDTO;
import com.paySystem.ic.web.dto.base.GoodsFormatGroupRelaDTO;

/**  
 * @Title: GoodsAttributeFormatService.java
 * @Package: com.paySystem.ic.service.base
 * @Description: 商品属性Service
 * @Author: yanwuyang 
 * @Date: 2014-8-19 下午11:08:46
 * @Version: V1.0  
 */

public interface GoodsAttributeFormatService {

    public final static String GOODSATTRIBUTEFORMATERV = "goodsAttributeFormatService";

    /**
     * 
     *@Title:saveAttribute
     *@Description:保存商品属性
     *@Params:@param attribute
     *@Params:@return
     *@Return:boolean
     *@author:yanwuyang
     *@Date:2014-8-21下午09:38:20
     */
    public void saveAttribute(GoodsAttributeDTO attributeDTO);
    
    /**
    *@Title:editAttribute
    *@Description:修改商品属性
    *@Params:@param attributeDTO
    *@Return:void
    *@author:王楠
    *@Date:2014-12-1上午11:42:27
    */
    public void editAttribute(GoodsAttributeDTO attributeDTO);

    /**
     * 
     *@Title:getAllAttributes
     *@Description:获取所有商品属性
     *@Params:@return
     *@Params:@throws Exception
     *@Return:QueryResult<DeliveryModeDTO>
     *@author:yanwuyang
     *@Date:2014-8-21下午10:12:05
     */
    public QueryResult<GoodsAttributeDTO> getAllAttributes() throws Exception;

    /**
     * 
     *@Title:saveFormatGroup
     *@Description:保存规格分组
     *@Params:@param formatGroupDTO
     *@Return:void
     *@author:yanwuyang
     *@Date:2014-8-22下午3:32:59
     */
    public void saveFormatGroup(GoodsFormatGroupDTO formatGroupDTO);

    /**
     * 
     *@Title:getAllFormatGruops
     *@Description:查询所有的规格分组
     *@Params:@return
     *@Params:@throws Exception
     *@Return:QueryResult<GoodsFormatGroupDTO>
     *@author:yanwuyang
     *@Date:2014-8-22下午3:39:17
     */
    public QueryResult<GoodsFormatGroupDTO> getAllFormatGruops() throws Exception;

    /**
     * 
     *@Title:saveForamt
     *@Description:保存规格以及分组关联
     *@Params:@param formatDTO
     *@Params:@param formatGroupRelaDTO
     *@Return:void
     *@author:yanwuyang
     *@Date:2014-8-22下午6:08:49
     */
    public void saveForamt(GoodsFormatDTO formatDTO, GoodsFormatGroupRelaDTO formatGroupRelaDTO);

    /**
     * 
     *@Title:getFormatsByGroup
     *@Description:根据分组ID获取规格
     *@Params:@param groupId
     *@Params:@return
     *@Params:@throws Exception
     *@Return:QueryResult<GoodsFormatDTO>
     *@author:yanwuyang
     *@Date:2014-8-22下午10:31:26
     */
    public List getFormatsByGroup(Integer groupId) throws Exception;

    /**
     * 
     *@Title:saveAssociation
     *@Description:保存属性、规格与分类的关联
     *@Params:@param familyId
     *@Params:@param familyGroupRelaDTOs
     *@Params:@param familyAttrRelaDTOs
     *@Return:void
     *@author:yanwuyang
     *@Date:2014-8-24上午10:50:48
     */
    public void saveAssociation(Integer familyId, List<GoodsFamilyGroupRelaDTO> familyGroupRelaDTOs,
            List<GoodsFamilyAttrRelaDTO> familyAttrRelaDTOs);

    /**
     * 
     *@Title:getAttributeByFamilyId
     *@Description:根据分类ID获取属性
     *@Params:@param familyId
     *@Params:@return
     *@Params:@throws Exception
     *@Return:List<GoodsAttributeDTO>
     *@author:yanwuyang
     *@Date:2014-8-24下午12:57:12
     */
    public List<GoodsAttributeDTO> getAttributeByFamilyId(Integer familyId, String type) throws Exception;

    /**
     * 
     *@Title:getFormatByFamilyId
     *@Description:根据分类ID获取规格
     *@Params:@param familyId
     *@Params:@return
     *@Params:@throws Exception
     *@Return:QueryResult<GoodsFormat>
     *@author:yanwuyang
     *@Date:2014-8-24下午12:28:07
     */
    public List<GoodsFormatDTO> getFormatByFamilyId(Integer familyId, String type) throws Exception;

    /**
     * 
     *@Title:getAllGoodsFormatGroupRela
     *@Description:获取所有的规格与分组的关联
     *@Params:@return
     *@Params:@throws Exception
     *@Return:List<GoodsFormatGroupRelaDTO>
     *@author:yanwuyang
     *@Date:2014-8-24下午01:46:36
     */
    public List<GoodsFormatGroupRelaDTO> getAllGoodsFormatGroupRela() throws Exception;

    /**
     * 
     *@Title:getAssociatedFamilyId
     *@Description:获取已经关联的类型
     *@Params:@return
     *@Params:@throws Exception
     *@Return:List
     *@author:yanwuyang
     *@Date:2014-8-24下午04:35:21
     */
    public QueryResult<List> getAssociatedFamily(int firstPage, int pageNum, GoodsFamilyDTO familyDTO,
            LinkedHashMap<String, String> orderBy) throws Exception;

    /**
     * 
     *@Title:deleteAssociatedFamily
     *@Description:删除关联
     *@Params:@param familyId
     *@Params:@throws Exception
     *@Return:void
     *@author:yanwuyang
     *@Date:2014-8-24下午05:25:43
     */
    public void deleteAssociatedFamily(Integer familyId) throws Exception;

    /**
     * 
     *@Title:checkAttributeName
     *@Description:校验属性名称是否存在
     *@Params:@param name
     *@Params:@return
     *@Params:@throws Exception
     *@Return:boolean
     *@author:yanwuyang
     *@Date:2014-8-24下午09:58:20
     */
    public boolean checkAttributeName(String name) throws Exception;

    /**
     * 
     *@Title:checFormatName
     *@Description:校验规格名称
     *@Params:@param name
     *@Params:@return
     *@Params:@throws Exception
     *@Return:boolean
     *@author:yanwuyang
     *@Date:2014-8-24下午09:58:38
     */
    public boolean checkFormatName(String name, Integer fgroupId) throws Exception;

    /**
     * 
     *@Title:checkFormatGroupName
     *@Description:校验分组名称是否存在
     *@Params:@param name
     *@Params:@return
     *@Params:@throws Exception
     *@Return:boolean
     *@author:yanwuyang
     *@Date:2014-8-24下午09:58:53
     */
    public boolean checkFormatGroupName(String name) throws Exception;

    /**
     * 
     *@Title:updateStautsByFamilyId
     *@Description:修改状态
     *@Params:@param familyId
     *@Params:@param status
     *@Return:void
     *@author:yanwuyang
     *@Date:2014-8-24下午11:11:31
     */
    public void updateStautsByFamilyId(Integer familyId, Integer status);

    /**
     * 
     *@Title:getFirstFamily
     *@Description:获取第一个分类
     *@Params:@return
     *@Params:@throws Exception
     *@Return:GoodsFamily
     *@author:yanwuyang
     *@Date:2014-8-25下午5:30:57
     */
    public GoodsFamilyDTO getFirstFamily() throws Exception;

    /**
     *@Description:检验显示名称
     *@return:boolean
     *@author: 张亚运
     *@throws:
     */
    public boolean checkDisplayName(String showLable) throws Exception;
    
    /**
     *@Title: saveForamtAndFGroup
     *@Description: 保存商品规格和商品规格分组
     *@Params: format 商品规格
     *@Params: formatGroup 商品规格分组
     *@throws: Exception 抛出异常
     *@Return: void
     *@author: 王少辉
     *@Date: 2014-11-28 下午04:06:49
     */
    void saveForamtAndFGroup(GoodsFormatDTO format, GoodsFormatGroupDTO formatGroup) throws Exception;

}
