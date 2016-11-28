package com.paySystem.ic.dao.base;

import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.GoodsAttribute;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.base.GoodsAttributeDTO;

/**  
 * @Title: GoodsAttributeFormatDao.java
 * @Package: com.paySystem.ic.dao.base
 * @Description: 商品属性DAO
 * @Author: yanwuyang 
 * @Date: 2014-8-19 下午11:12:41
 * @Version: V1.0  
 */

public interface GoodsAttributeDao extends DAO<GoodsAttribute> {

    public final static String GOODSATTRIBUTEDAO = "goodsAttributeDao";

    /**
     * 
     *@Title:save
     *@Description:保存属性
     *@Params:@param attribute
     *@Return:void
     *@author:yanwuyang
     *@Date:2014-8-20下午10:08:50
     */
    public void save(GoodsAttributeDTO attributeDTO);
    
    /**
    *@Title:edit
    *@Description:修改属性
    *@Params:@param attributeDTO
    *@Return:void
    *@author:王楠
    *@Date:2014-12-1下午02:55:04
    */
    public void edit(GoodsAttributeDTO attributeDTO);
    
    /**
     *@Title: saveAttr
     *@Description: 保存商品属性（带返回值）
     *@Params: attributeDTO 商品属性
     *@return: Integer 返回商品属性编号
     *@author: 王少辉
     *@Date: 2014-11-21 上午10:57:21
     */
    public Integer saveAttr(GoodsAttributeDTO attributeDTO);

    /**
     * 
     *@Title:delete
     *@Description:删除属性
     *@Params:@param id
     *@Return:void
     *@author:yanwuyang
     *@Date:2014-8-20下午10:10:02
     */
    public void delete(Integer id);

    /**
     * 
     *@Title:getAllAttributes
     *@Description:获取所有商品属性
     *@Params:@return
     *@Params:@throws Exception
     *@Return:QueryResult<GoodsAttributeDTO>
     *@author:yanwuyang
     *@Date:2014-8-21下午10:13:45
     */
    public QueryResult<GoodsAttributeDTO> getAllAttributes() throws Exception;

    /**
     * 
     *@Title:getAttributeByFamilyId
     *@Description:根据分类ID获取属性
     *@Params:@param familyId
     *@Params:@return
     *@Params:@throws Exception
     *@Return:List<GoodsAttributeDTO>
     *@author:yanwuyang
     *@Date:2014-8-24下午12:41:25
     */
    public List<GoodsAttributeDTO> getAttributeByFamilyId(Integer familyId) throws Exception;

    /**
     * 
     *@Title:checkName
     *@Description:校验名称是否存在
     *@Params:@param name
     *@Params:@return
     *@Params:@throws Exception
     *@Return:boolean
     *@author:yanwuyang
     *@Date:2014-8-24下午09:50:46
     */
    public boolean checkName(String name) throws Exception;

    /**
     * 
     *@Title:getParnetAttributeByFamilyId
     *@Description:查询父类的属性
     *@Params:@param familyId
     *@Params:@return
     *@Params:@throws Exception
     *@Return:List<GoodsAttributeDTO>
     *@author:yanwuyang
     *@Date:2014-8-25下午3:40:16
     */
    public List<GoodsAttributeDTO> getParnetAttributeByFamilyId(Integer familyId) throws Exception;

    /**
     *@Description:检验显示名字
     *@return:boolean
     *@author: 张亚运
     *@throws:
     */
    public boolean checkDisPlayName(String showLable);

}
