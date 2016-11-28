package com.paySystem.ic.service.base.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.GoodsFamily;
import com.paySystem.ic.dao.base.GoodsFamilyDao;
import com.paySystem.ic.service.base.GoodsFamilyService;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.base.GoodsFamilyDTO;
import com.paySystem.ic.web.dto.goods.GoodsTypeDTO;
import com.paySystem.ic.web.ui.OptionsInteger;

/**
 * @ClassName:GoodsFamilyServiceImpl
 * @Description:商品分类Service实现
 * @date: 2014-6-26下午03:36:32
 * @author: 张亚运
 * @version: V1.0
 */
@Service(GoodsFamilyService.GOODSFAMILYSERVICE)
public class GoodsFamilyServiceImpl implements GoodsFamilyService {

    @Resource
    GoodsFamilyDao goodsFamilyDao;

    /**
     *@Description:查询商品分类信息
     *@param:@param page
     *@param:@param pageNum
     *@param:@param goodsFamilyDto
     *@param:@param orderBy
     *@param:@return
     *@param:@throws Exception
     *@author: 张亚运
     *@throws:
     */
    public QueryResult<GoodsFamilyDTO> queryAll(int page, int pageNum, GoodsFamilyDTO goodsFamilyDto,
            LinkedHashMap<String, String> orderBy) throws Exception {
        return goodsFamilyDao.queryAll(page, pageNum, goodsFamilyDto, orderBy);
    }

    /**
     *@Description:添加保存商品分类信息
     *@param:@param goodsFamilyDto
     *@param:@return
     *@author: 张亚运
     *@throws:
     */
    public GoodsFamily saveGoodsFamily(GoodsFamilyDTO goodsFamilyDto) {
        GoodsFamily goodsfamily = new GoodsFamily();
        goodsfamily = goodsFamilyDao.saveGoodsFamily(goodsFamilyDto);
        return goodsfamily;
    }

    /**
     *@Description:删除商品分类信息（更新状态）
     *@return:void
     *@author: 张亚运
     *@throws:
     */
    public void deleteGoodsFamily(Integer familyId) {
        GoodsFamily goodsfamily = goodsFamilyDao.find(familyId);
        goodsfamily.setStatus(9);
        goodsFamilyDao.update(goodsfamily);
    }

    /**
     *@Description:根据编号查询该条记录
     *@param:@param familyId
     *@param:@return
     *@author: 张亚运
     *@throws:
     */
    public GoodsFamilyDTO find(Integer familyId) {

        GoodsFamily gf = new GoodsFamily();
        gf = goodsFamilyDao.find(familyId);
        GoodsFamilyDTO dto = getDto(gf);
        return dto;
    }

    /**
     *@Description:将实体转换Dto
     *@return:GoodsFamilyDTO
     *@author: 张亚运
     *@throws:
     */
    public GoodsFamilyDTO getDto(GoodsFamily goodsFamily) {
        GoodsFamilyDTO dto = new GoodsFamilyDTO();
        dto.setFamilyId(goodsFamily.getFamilyId());
        dto.setFamilyName(goodsFamily.getFamilyName());
        dto.setFamilyWay(goodsFamily.getFamilyWay());
        dto.setCreateTime(goodsFamily.getCreateTime());
        dto.setDefaultShow(goodsFamily.getDefaultShow());
        dto.setPicPath(goodsFamily.getPicPath());
        dto.setKeyWords(goodsFamily.getKeyWords());
        dto.setNodeLevel(goodsFamily.getNodeLevel());
        dto.setOperator(goodsFamily.getOperator());
        dto.setParentId(goodsFamily.getParentId());
        dto.setStatus(goodsFamily.getStatus());
        dto.setPreFlag(goodsFamily.getPreFlag());
        return dto;
    }

    /**
     *@Description:修改商品分类信息
     *@param:@param goodsfamilyDto
     *@param:@return
     *@author: 张亚运
     *@throws:
     */
    public ReturnDTO updateGoodsFamily(GoodsFamilyDTO goodsfamilyDto) {
        ReturnDTO returnDto = new ReturnDTO();
        returnDto = goodsFamilyDao.updateGoodsFamily(goodsfamilyDto);
        return returnDto;
    }

    /**
     *@Description:检验商品分类名称是否存在
     *@param:@param familyName
     *@param:@param familyId
     *@param:@return
     *@author: 张亚运
     *@throws:
     */
    public boolean validateName(String familyName, Integer familyId) {
        return goodsFamilyDao.validateName(familyName, familyId);
    }

    /**
     *@Description:获取页面上的所属分类
     *@param:@return
     *@author: 张亚运
     *@throws:
     */
    public List<OptionsInteger> getFamilyOption() {
        List<OptionsInteger> list = goodsFamilyDao.getFamilyOption();
        return list;
    }
    
    /**
     *@Description:获取商品分类包含父子关系的列表
     *@return:List<OptionsString>
     *@author: Jacky
     *@throws:
     */
    public List<GoodsTypeDTO> getFamilyCategoryList() {
    	List<GoodsFamily> goodsFamilyList = goodsFamilyDao.getPureFamilyOption();
    	List<GoodsTypeDTO> familyList = new ArrayList<GoodsTypeDTO>(goodsFamilyList.size());
    	if(CollectionUtils.isNotEmpty(goodsFamilyList)) {
    		Map<Integer,List<GoodsTypeDTO>> childFamilyMap = new HashMap<Integer,List<GoodsTypeDTO>>();
	    	for(GoodsFamily family : goodsFamilyList) {
	    		GoodsTypeDTO goodsTypeDTO = new GoodsTypeDTO();
	    		goodsTypeDTO.setId(family.getFamilyId());
	    		goodsTypeDTO.setText(family.getFamilyName());
	    		if(null == family.getDefaultShow() || family.getDefaultShow() == 1) {
	    			goodsTypeDTO.setState("");
	    		} else {
	    			goodsTypeDTO.setState("closed");
	    		}
 	    		/** 如果是根级分类，先加入list **/
	    		if(family.getParentId() == 0) {
	    			familyList.add(goodsTypeDTO);
	    		} else {
	    		/** 非根级分类先入map**/	
	    			List<GoodsTypeDTO> goodDTOList = childFamilyMap.get(family.getParentId());
	    			if(goodDTOList == null) {
	    				goodDTOList = new ArrayList<GoodsTypeDTO>();
	    				childFamilyMap.put(family.getParentId(), goodDTOList);
	    			}
	    			goodDTOList.add(goodsTypeDTO);
	    		}
	    	}
	    	/** 找孩子 **/
	    	for(GoodsTypeDTO goodsTypeDTO : familyList) {
	    		findAndSetChild(goodsTypeDTO,childFamilyMap);
	    	}
    	}
		return familyList;
	}
    
    /**
     *@Description: 批量获取分类对应的类型名称
     *@param typeIds 批量类型id
     *@return:List<OptionsString>
     *@author: Jacky
     *@throws:
     */
    public Map<String, String> batchQueryFamilyCategoryMap(Set<Integer> typeIds) {
    	List<GoodsFamily> familyList = goodsFamilyDao.batchQueryGoodsFamily(typeIds);
    	Map<String,String> categoryMap = new HashMap<String,String>();
    	if(CollectionUtils.isNotEmpty(familyList)) {
    		for(GoodsFamily goodsFamily : familyList) {
    			categoryMap.put(String.valueOf(goodsFamily.getFamilyId()), goodsFamily.getFamilyName());
    		}
    	}
		return categoryMap;
	}

	/**
     *@Description:递归方式获取商品分类包含父子关系的列表
     *@return:List<GoodsTypeDTO>
     *@author: Jacky
     *@throws:
     */
    private List<GoodsTypeDTO> findAndSetChild(GoodsTypeDTO goodsTypeDTO,Map<Integer,List<GoodsTypeDTO>> childFamilyMap) {
    	List<GoodsTypeDTO> childList = childFamilyMap.get(goodsTypeDTO.getId());
		if(!CollectionUtils.isEmpty(childList)) {
			if(goodsTypeDTO.getChildren() == null) {
				goodsTypeDTO.setChildren(new ArrayList<GoodsTypeDTO>());
			}
			goodsTypeDTO.getChildren().addAll(childList);
			/** 查找孩子 **/
			for(GoodsTypeDTO child : goodsTypeDTO.getChildren()) {
				child.setChildren(findAndSetChild(child,childFamilyMap));
			}
			return goodsTypeDTO.getChildren();
		} else {
			/** 没有孩子的人的状态不设置 **/
			goodsTypeDTO.setState("");
		}
		return new ArrayList<GoodsTypeDTO>();
    }
    
	public Integer getNodeLevel(Integer parentId) {
        return goodsFamilyDao.getNodeLevel(parentId);
    }

}
