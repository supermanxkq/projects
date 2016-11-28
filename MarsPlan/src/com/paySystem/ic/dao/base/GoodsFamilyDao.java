package com.paySystem.ic.dao.base;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.GoodsFamily;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.base.GoodsFamilyDTO;
import com.paySystem.ic.web.ui.OptionsInteger;

/**
 * @ClassName:GoodsFamilyDao
 * @Description:商品分类接口
 * @date: 2014-6-26下午03:29:12
 * @author: 张亚运
 * @version: V1.0
 */
public interface GoodsFamilyDao extends DAO<GoodsFamily> {

    public static final String GOODSFSMILYDAO = "goodsFamilyDao";

    /**
     *@Description:查询商品分类信息
     *@return:QueryResult<GoodsFamily>
     *@author: 张亚运
     *@throws:
     */
    public QueryResult<GoodsFamilyDTO> queryAll(int page, int pageNum, GoodsFamilyDTO goodsFamilyDto,
            LinkedHashMap<String, String> orderBy) throws Exception;

    /**
     *@Description:保存商品分类信息
     *@return:GoodsFamily
     *@author: 张亚运
     *@throws:
     */
    public GoodsFamily saveGoodsFamily(GoodsFamilyDTO goodsFamilyDto);

    /**
     *@Description:修改保存商品分类信息
     *@return:void
     *@author: 张亚运
     *@throws:
     */
    public ReturnDTO updateGoodsFamily(GoodsFamilyDTO goodsFamilyDto);

    /**
     *@Description:检验商品分类名称是否存在
     *@return:boolean
     *@author: 张亚运
     * @param sign 
     *@throws:
     */
    public boolean validateName(String familyName, Integer familyId);

    /**
     *@Description:获取所属分类
     *@return:List<OptionsString>
     *@author: 张亚运
     *@throws:
     */
    public List<OptionsInteger> getFamilyOption();
    
    /**
     *@Description:获取纯洁的所属分类
     *@return:List<GoodsFamily>
     *@author: Jacky
     *@throws:
     */
    public List<GoodsFamily> getPureFamilyOption();

    /**
     *@Description:根据所选分类获取该节点等级
     *@return:Integer
     *@author: 张亚运
     *@throws:
     */
    public Integer getNodeLevel(Integer parentId);
    
    /**
     *@Description:批量获取分类列表
     *@return:List<GoodsFamily>
     *@author: Jacky
     *@throws:
     */
    public List<GoodsFamily> batchQueryGoodsFamily(Set<Integer> familyId);

}
