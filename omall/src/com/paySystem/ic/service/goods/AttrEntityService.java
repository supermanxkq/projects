package com.paySystem.ic.service.goods;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.GoodsAttribute;
import com.paySystem.ic.bean.goods.AttrEntity;
import com.paySystem.ic.web.dto.base.GoodsAttributeDTO;
import com.paySystem.ic.web.dto.goods.AttrEntityDTO;

/**
 * @ProjectName:omall
 * @ClassName:AttrEntityService
 * @Description:商品属性值服务类
 * @date: 2014-10-10下午04:38:15
 * @author: 徐凯强
 * @version: V1.0
 */
public interface AttrEntityService {

    /** 常量 */
    public static final String ATTRENTITYSERVICE = "AttrEntityService";

    /**
     *@Title:findAll
     *@Description:查询数据库中所有的属性名称，用于显示在下拉框中
     *@param firstindex分页的首个参数
     *@param pageNum每页有多少条数据
     *@param goodsAttributeDTO商品属性数据传输对象
     *@param orderBy排序参数
     *@Return:QueryResult<GoodsAttributeDTO>商品属性记录和总条数集合
     *@author:徐凯强
     *@Date:2014-10-12下午04:15:49
     */
    public QueryResult<GoodsAttributeDTO> findAll(int firstindex, int pageNum, GoodsAttributeDTO goodsAttributeDTO,
            LinkedHashMap<String, String> orderBy);

    /**
     *@Title:showAttrEntities
     *@Description:查询数据库中所有的属性值，用于显示在下拉框中
     *@param firstindex分页的首个参数
     *@param pageNum每页有多少条数据
     *@param attrEntityDTO商品属性值数据传输对象
     *@param goodsAttributeDTO商品属性数据传输对象
     *@param orderBy排序参数
     *@Return:QueryResult<AttrEntityDTO>商品属性值记录和总条数集合
     *@author:徐凯强
     *@Date:2014-10-12下午04:15:49
     */
    public QueryResult<AttrEntityDTO> showAttrEntities(int firstindex, int pageNum, AttrEntityDTO attrEntityDTO,
            GoodsAttributeDTO goodsAttributeDTO, LinkedHashMap<String, String> orderBy);

    /**
     *@Title:addSave
     *@Description:保存属性值记录
     *@param attrEntityDTO数据传输对象
     *@Return:void返回值
     *@author:徐凯强
     *@Date:2014-10-12下午04:15:49
     */
    public void addSave(AttrEntityDTO attrEntityDTO);
    
    /**
     *@Title: saveAttrAndAttrValue
     *@Description: 保存商品属性、商品属性值
     *@Params: goodsAttrDTO 商品属性
     *@Params: attrEnDTO 商品属性值
     *@Return: void
     *@author: 王少辉
     *@Date: 2014-11-20 下午05:31:57
     */
    void saveAttrAndAttrValue(GoodsAttributeDTO goodsAttrDTO, AttrEntityDTO attrEnDTO) throws Exception;

    /**
     *@Description:根据属性id获取属性名称
     *@return:String
     *@author: 张亚运
     *@throws:
     */
    public String getAttrName(Integer attrId);

    /**
     *@Description:检验属性值名称是否存在
     *@return:boolean
     *@author: 张亚运
     *@throws:
     */
    public boolean checkAttrEnName(String attrEnName, Integer attrId);
    
    /**
     *@Title:find
     *@Description:查找信息的方法
     *@Params:@param attrId
     *@Params:@return
     *@Return:GoodsAttribute
     *@author:王楠
     *@Date:2014-11-28上午11:24:47
     */
     public GoodsAttribute find(Integer attrId);
     
     /**
    *@Title:findAttr
    *@Description:查找属性值枚举类的方法
    *@Params:@param attrEnId 属性值枚举类的id
    *@Params:@return
    *@Return:AttrEntity
    *@author:王楠
    *@Date:2014-12-1下午05:25:16
    */
    public AttrEntity findAttr(Integer attrEnId);
     
     /**
    *@Title:delete
    *@Description:删除信息
    *@Params:@param attrId
    *@Return:void
    *@author:王楠
    *@Date:2014-11-28下午05:08:57
    */
    public void delete(Integer attrId) throws Exception;
    
    /**
    *@Title:drop
    *@Description:删除枚举信息
    *@Params:@param attrEnId
    *@Params:@throws Exception
    *@Return:void
    *@author:王楠
    *@Date:2014-12-2下午02:32:57
    */
    public void drop(Integer attrEnId)throws Exception;

}