package com.paySystem.ic.service.goods.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.GoodsAttribute;
import com.paySystem.ic.bean.goods.AttrEntity;
import com.paySystem.ic.dao.base.GoodsAttributeDao;
import com.paySystem.ic.dao.goods.AttrEntityDAO;
import com.paySystem.ic.dao.goods.GoodsAttrDAO;
import com.paySystem.ic.dao.goods.impl.GoodsAttrDAOImpl;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.service.goods.AttrEntityService;
import com.paySystem.ic.utils.EntityDtoConverter;
import com.paySystem.ic.web.dto.base.GoodsAttributeDTO;
import com.paySystem.ic.web.dto.goods.AttrEntityDTO;

/**
 * @ProjectName:omall
 * @ClassName:AttrEntityServiceImpl
 * @Description:属性值服务实现类
 * @date: 2014-10-10下午04:49:42
 * @author: 徐凯强
 * @version: V1.0
 */
@Service(AttrEntityService.ATTRENTITYSERVICE)
public class AttrEntityServiceImpl extends DaoSupport<GoodsAttribute> implements AttrEntityService {
	
	/**
	 * 注入goodsAttrDAO
	 */
	@Resource
	private GoodsAttributeDao goodsAttrDAO;

    /** 注入AttrEntityDAO */
    @Resource
    private AttrEntityDAO attrEntityDAO;
    /**注入GoodsAttrDAO*/
    @Resource(name="goodsAttrDAOImpl")
    private GoodsAttrDAO goodsAttrDaoimpl=new GoodsAttrDAOImpl();

    public GoodsAttributeDao getGoodsAttrDAO() {
		return goodsAttrDAO;
	}

	public void setGoodsAttrDAO(GoodsAttributeDao goodsAttrDAO) {
		this.goodsAttrDAO = goodsAttrDAO;
	}

	public AttrEntityDAO getAttrEntityDAO() {
		return attrEntityDAO;
	}

	public void setAttrEntityDAO(AttrEntityDAO attrEntityDAO) {
		this.attrEntityDAO = attrEntityDAO;
	}

	
	public GoodsAttrDAO getGoodsAttrDaoimpl() {
		return goodsAttrDaoimpl;
	}

	public void setGoodsAttrDaoimpl(GoodsAttrDAO goodsAttrDaoimpl) {
		this.goodsAttrDaoimpl = goodsAttrDaoimpl;
	}

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
            LinkedHashMap<String, String> orderBy) {
        /** 要返回的集合 */
        QueryResult<GoodsAttributeDTO> aQueryResultDTO = new QueryResult<GoodsAttributeDTO>();
        /** 接收返回的数据库值 */
        QueryResult<GoodsAttribute> aQueryResult = attrEntityDAO.findAll(firstindex, pageNum, goodsAttributeDTO,
                orderBy);
        /** 将实体转换为DTO */
        List<GoodsAttributeDTO> goodsAttributeDTOs = changeEntityTODTO(aQueryResult.getResultlist());
        aQueryResultDTO.setResultlist(goodsAttributeDTOs);
        aQueryResultDTO.setTotalrecord(aQueryResult.getTotalrecord());
        return aQueryResultDTO;
    }

    /**
     *@Title:changeEntityTODTO
     *@Description:将实体集合转换为DTO集合
     *@param attrEntities
     *@Return:List<GoodsAttributeDTO>
     *@author:徐凯强
     *@Date:2014-10-10下午04:50:59
     */
    public List<GoodsAttributeDTO> changeEntityTODTO(List<GoodsAttribute> goodsAttributes) {
        /** 实例化DTO集合 */
        List<GoodsAttributeDTO> goodsAttributeDTOs = new ArrayList<GoodsAttributeDTO>();
        /** 遍历转换 */
        for (GoodsAttribute goodsAttribute : goodsAttributes) {
            /** 实例化DTO对象 */
            GoodsAttributeDTO goodsAttributeDTO = new GoodsAttributeDTO();
            /** 转换DTO对象的属性 */
            goodsAttributeDTO.setAttrId(goodsAttribute.getAttrId());
            goodsAttributeDTO.setAttrName(goodsAttribute.getAttrName());
            goodsAttributeDTO.setIsEn(goodsAttribute.getIsEn());
            goodsAttributeDTO.setShowlable(goodsAttribute.getShowlable());
            /** 放入的DTO集合中 */
            goodsAttributeDTO.setStatus(goodsAttribute.getStatus());
            goodsAttributeDTOs.add(goodsAttributeDTO);
        }
        /** 返回DTO集合 */
        return goodsAttributeDTOs;
    }

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
            GoodsAttributeDTO goodsAttributeDTO, LinkedHashMap<String, String> orderBy) {
        return attrEntityDAO.showAttrEntities(firstindex, pageNum, attrEntityDTO, goodsAttributeDTO, orderBy);
    }

    /**
     *@Title:addSave
     *@Description:保存属性值记录
     *@param attrEntityDTO数据传输对象
     *@Return:void返回值
     *@author:徐凯强
     *@Date:2014-10-12下午04:15:49
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void addSave(AttrEntityDTO attrEntityDTO) {
        attrEntityDAO.addSave(attrEntityDTO);
    }

    /**
     *@Description:根据属性id获取属性名称
     *@param:@param attrId
     *@param:@return
     *@author: 张亚运
     *@throws:
     */
    @Override
    public String getAttrName(Integer attrId) {
        return attrEntityDAO.getAttrName(attrId);
    }

    /**
     *@Description:检验属性值名称是否存在
     *@param:@param attrEnName
     *@param:@param attrId
     *@param:@return
     *@author: 张亚运
     *@throws:
     */
    @Override
    public boolean checkAttrEnName(String attrEnName, Integer attrId) {
        return attrEntityDAO.checkAttrEnName(attrEnName, attrId);
    }
    
    /**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.goods.AttrEntityService#saveAttrAndAttrValue(com.paySystem.ic.web.dto.base.GoodsAttributeDTO, com.paySystem.ic.web.dto.goods.AttrEntityDTO)
	 *@MethodName: saveAttrAndAttrValue
     *@Description: 保存商品属性、商品属性值
     *@Params: goodsAttrDTO 商品属性
     *@Params: attrEnDTO 商品属性值
	 *@Author: 王少辉
	 *@Date: 2014-11-20 下午05:33:00
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveAttrAndAttrValue(GoodsAttributeDTO goodsAttrDTO,
			AttrEntityDTO attrEnDTO) throws Exception {
		goodsAttrDTO.setStatus(0);
		Integer attrId = goodsAttrDAO.saveAttr(goodsAttrDTO);
		
		// 商品属性为非枚举的，商品属性为枚举类型而枚举值已存在的，不保存属性值
		if (StringUtils.isBlank(attrEnDTO.getAttrEnName()) || attrEntityDAO.checkAttrEnName(attrEnDTO.getAttrEnName())) {
			return;
		}
		
		attrEnDTO.setAttrId(attrId);
		attrEnDTO.setStatus(0);
		attrEntityDAO.addSave(attrEnDTO);
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.goods.AttrEntityService#find(java.lang.Integer)
	 *@MethodName:find
	 *@Description:查找信息的方法
	 *@param attrId
	 *@return
	 *@Author:王楠
	 *@Date:2014-11-28上午11:26:55
	 */
	@Override
	public GoodsAttribute find(Integer attrId) {
		return attrEntityDAO.find(attrId);
	}

	 /**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.goods.AttrEntityService#findAttr(java.lang.Integer)
	 *@MethodName:findAttr
	 *@Description:查看属性枚举信息
	 *@param attrEnId
	 *@return
	 *@Author:王楠
	 *@Date:2014-12-2上午10:45:28
	 */
	@Transactional
	public AttrEntity findAttr(Integer attrEnId){
		 return goodsAttrDaoimpl.find(attrEnId.intValue());
		 
	 }
	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.goods.AttrEntityService#delete(java.lang.Integer)
	 *@MethodName:delete
	 *@Description:删除信息
	 *@param attrId
	 *@Author:王楠
	 *@Date:2014-11-28下午05:09:25
	 */
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void delete(Integer attrId) throws Exception{
		GoodsAttribute attribute=attrEntityDAO.find(attrId);
		attribute.setStatus(1);
		attrEntityDAO.update(attribute);
	}
	
	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.goods.AttrEntityService#drop(java.lang.Integer)
	 *@MethodName:drop
	 *@Description:停用枚举信息
	 *@param attrEnId
	 *@throws Exception
	 *@Author:王楠
	 *@Date:2014-12-2下午02:33:34
	 */
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void drop(Integer attrEnId)throws Exception{
		AttrEntity a=goodsAttrDaoimpl.find(attrEnId);
		a.setStatus(1);
		goodsAttrDaoimpl.update(a);
	}
}
