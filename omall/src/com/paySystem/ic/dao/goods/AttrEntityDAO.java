package com.paySystem.ic.dao.goods;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.GoodsAttribute;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.base.GoodsAttributeDTO;
import com.paySystem.ic.web.dto.goods.AttrEntityDTO;

/**
 * @ProjectName:omall
 * @ClassName:AttrEntityDAO
 * @Description:商品属性值DAO
 * @date: 2014-10-10下午04:29:14
 * @author: 徐凯强
 * @version: V1.0
 */
public interface AttrEntityDAO extends DAO<GoodsAttribute> {

    /** 常量 */
    public static final String ATTRENTITYDAO = "AttrEntityDAO";

    /**
     *@Title:findAll
     *@Description:查询数据库中所有的属性名称，用于显示在下拉框中
     *@param firstindex分页的首个参数
     *@param pageNum每页有多少条数据
     *@param goodsAttributeDTO商品属性数据传输对象
     *@param orderBy排序参数
     *@Return:QueryResult<GoodsAttribute>商品属性记录和总条数集合
     *@author:徐凯强
     *@Date:2014-10-12下午04:15:49
     */
    public QueryResult<GoodsAttribute> findAll(int firstindex, int pageNum, GoodsAttributeDTO goodsAttributeDTO,
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
     *@Description:根据属性Id获取属性名称
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
     *@Title: checkAttrEnName
     *@Description: 检验属性值名称是否存在
     *@Params: attrEnName 属性值名称
     *@Return: boolean
     *@author: 王少辉
     *@Date: 2014-12-4 下午06:24:19
     */
    boolean checkAttrEnName(String attrEnName);
    
}
