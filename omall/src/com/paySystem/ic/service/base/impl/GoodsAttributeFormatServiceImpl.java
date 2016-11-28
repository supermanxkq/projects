package com.paySystem.ic.service.base.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.dao.base.GoodsAttributeDao;
import com.paySystem.ic.dao.base.GoodsFamilyAttrRelaDao;
import com.paySystem.ic.dao.base.GoodsFamilyGroupRelaDao;
import com.paySystem.ic.dao.base.GoodsFormatDao;
import com.paySystem.ic.dao.base.GoodsFormatGroupDao;
import com.paySystem.ic.dao.base.GoodsFormatGroupRelaDao;
import com.paySystem.ic.service.base.GoodsAttributeFormatService;
import com.paySystem.ic.web.dto.base.GoodsAttributeDTO;
import com.paySystem.ic.web.dto.base.GoodsFamilyAttrRelaDTO;
import com.paySystem.ic.web.dto.base.GoodsFamilyDTO;
import com.paySystem.ic.web.dto.base.GoodsFamilyGroupRelaDTO;
import com.paySystem.ic.web.dto.base.GoodsFormatDTO;
import com.paySystem.ic.web.dto.base.GoodsFormatGroupDTO;
import com.paySystem.ic.web.dto.base.GoodsFormatGroupRelaDTO;

/**  
 * @Title: GoodsAttributeFormatServiceImpl.java
 * @Package: com.paySystem.ic.service.base.impl
 * @Description: 商品属性Service实现类
 * @Author: yanwuyang 
 * @Date: 2014-8-19 下午11:09:53
 * @Version: V1.0  
 */

@Service(GoodsAttributeFormatService.GOODSATTRIBUTEFORMATERV)
public class GoodsAttributeFormatServiceImpl implements GoodsAttributeFormatService {

    /** 属性DAO*/
    @Resource
    private GoodsAttributeDao attributeDao;

    /** 规格分组DAO*/
    @Resource
    private GoodsFormatGroupDao formatGroupDao;

    /** 规格DAO*/
    @Resource
    private GoodsFormatDao formatDao;

    /** 规格与分组关联DAO*/
    @Resource
    private GoodsFormatGroupRelaDao formatGroupRelaDao;

    /** 分类与规格关联DAO*/
    @Resource
    private GoodsFamilyGroupRelaDao familyGroupRelaDao;

    /** 分类与属性关联DAO*/
    @Resource
    private GoodsFamilyAttrRelaDao familyAttrRelaDao;

    /**
     * 
     *@OverRiddenMethod：@see com.paySystem.ic.service.base.GoodsAttributeFormatService#saveAttribute(com.paySystem.ic.bean.base.GoodsAttribute)
     *@MethodName:saveAttribute
     *@Description:保存商品属性
     *@param attribute
     *@return
     *@Author:yanwuyang
     *@Date:2014-8-21下午09:38:47
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveAttribute(GoodsAttributeDTO attributeDTO) {
    	attributeDTO.setStatus(0);
        attributeDao.save(attributeDTO);
    }
    
    /**
     *@OverRiddenMethod：@see com.paySystem.ic.service.base.GoodsAttributeFormatService#editAttribute(com.paySystem.ic.web.dto.base.GoodsAttributeDTO)
     *@MethodName:editAttribute
     *@Description:修改商品属性
     *@param attributeDTO
     *@Author:王楠
     *@Date:2014-12-1上午11:43:52
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void editAttribute(GoodsAttributeDTO attributeDTO) {
    	attributeDao.edit(attributeDTO);
	}

    /**
     * 
     *@OverRiddenMethod：@see com.paySystem.ic.service.base.GoodsAttributeFormatService#getAllAttributes()
     *@MethodName:getAllAttributes
     *@Description:获取所有商品属性
     *@return
     *@throws Exception
     *@Author:yanwuyang
     *@Date:2014-8-21下午10:12:40
     */
    public QueryResult<GoodsAttributeDTO> getAllAttributes() throws Exception {
        return attributeDao.getAllAttributes();
    }

    /**
     * 
     *@OverRiddenMethod：@see com.paySystem.ic.service.base.GoodsAttributeFormatService#saveFormatGroup(com.paySystem.ic.web.dto.base.GoodsFormatGroupDTO)
     *@MethodName:saveFormatGroup
     *@Description:保存规格分组
     *@param formatGroupDTO
     *@Author:yanwuyang
     *@Date:2014-8-22下午3:33:40
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveFormatGroup(GoodsFormatGroupDTO formatGroupDTO) {
        formatGroupDao.save(formatGroupDTO);
    }

    /**
     * 
     *@OverRiddenMethod：@see com.paySystem.ic.service.base.GoodsAttributeFormatService#getAllFormatGruops()
     *@MethodName:getAllFormatGruops
     *@Description:获取所有的规格分组
     *@return
     *@throws Exception
     *@Author:yanwuyang
     *@Date:2014-8-22下午6:09:42
     */
    public QueryResult<GoodsFormatGroupDTO> getAllFormatGruops() throws Exception {
        return formatGroupDao.getAllFormatGruops();
    }

    /**
     * 
     *@OverRiddenMethod：@see com.paySystem.ic.service.base.GoodsAttributeFormatService#saveForamt(com.paySystem.ic.web.dto.base.GoodsFormatDTO, com.paySystem.ic.web.dto.base.GoodsFormatGroupRelaDTO)
     *@MethodName:saveForamt
     *@Description:保存规格以及分组关联
     *@param formatDTO
     *@param formatGroupRelaDTO
     *@Author:yanwuyang
     *@Date:2014-8-22下午6:09:58
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveForamt(GoodsFormatDTO formatDTO, GoodsFormatGroupRelaDTO formatGroupRelaDTO) {
    	Integer formatId = formatDao.save(formatDTO);
    	formatGroupRelaDTO.setFormatId(formatId);
    	formatGroupRelaDao.save(formatGroupRelaDTO);
    }
    
    /**
     * 
     *@OverRiddenMethod：@see com.paySystem.ic.service.base.GoodsAttributeFormatService#getFormatsByGroup(java.lang.Integer)
     *@MethodName:getFormatsByGroup
     *@Description:根据规格分组ID获取规格
     *@param groupId
     *@return
     *@throws Exception
     *@Author:yanwuyang
     *@Date:2014-8-22下午10:32:45
     */
    public List getFormatsByGroup(Integer groupId) throws Exception {
        return formatDao.getFormatsByGroup(groupId);
    }

    /**
     * 
     *@OverRiddenMethod：@see com.paySystem.ic.service.base.GoodsAttributeFormatService#saveAssociation(java.lang.Integer, java.util.List, java.util.List)
     *@MethodName:saveAssociation
     *@Description:保存属性、规格与分类的关联
     *@param familyId
     *@param familyGroupRelaDTOs
     *@param familyAttrRelaDTOs
     *@Author:yanwuyang
     *@Date:2014-8-24上午10:55:16
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveAssociation(Integer familyId, List<GoodsFamilyGroupRelaDTO> familyGroupRelaDTOs,
            List<GoodsFamilyAttrRelaDTO> familyAttrRelaDTOs) {
        familyGroupRelaDao.deleteByFamilyId(familyId);
        familyAttrRelaDao.deleteByFamilyId(familyId);
        for (int i = 0; i < familyGroupRelaDTOs.size(); i++) {
            familyGroupRelaDao.save(familyGroupRelaDTOs.get(i));
        }
        for (int i = 0; i < familyAttrRelaDTOs.size(); i++) {
            familyAttrRelaDao.save(familyAttrRelaDTOs.get(i));
        }

    }

    /**
     * 
     *@OverRiddenMethod：@see com.paySystem.ic.service.base.GoodsAttributeFormatService#getAttributeByFamilyId(java.lang.Integer)
     *@MethodName:getAttributeByFamilyId
     *@Description:根据类型获取属性
     *@param familyId
     *@return
     *@throws Exception
     *@Author:yanwuyang
     *@Date:2014-8-24下午06:16:50
     */
    public List<GoodsAttributeDTO> getAttributeByFamilyId(Integer familyId, String type) throws Exception {
        List<GoodsAttributeDTO> attributeDTOs = attributeDao.getAttributeByFamilyId(familyId);
        if (type.equals("list")) {
            return attributeDTOs;
        }
        List<GoodsAttributeDTO> pattributeDTOs = attributeDao.getParnetAttributeByFamilyId(familyId);
        if (pattributeDTOs == null) {
            return attributeDTOs;
        }
        if (attributeDTOs == null) {
            return pattributeDTOs;
        }
        ArrayList<GoodsAttributeDTO> list = new ArrayList<GoodsAttributeDTO>();
        list.addAll(pattributeDTOs);
        for (int i = 0; i < attributeDTOs.size(); i++) {
            GoodsAttributeDTO childDTO = attributeDTOs.get(i);
            int cId = childDTO.getAttrId();
            for (int j = 0; j < pattributeDTOs.size(); j++) {
                GoodsAttributeDTO parentChildDTO = pattributeDTOs.get(j);
                int pId = parentChildDTO.getAttrId();
                if (cId == pId) {
                    break;
                }
                if (j == pattributeDTOs.size() - 1) {
                    list.add(childDTO);
                }
            }
        }
        return list;
    }

    /**
     * 
     *@OverRiddenMethod：@see com.paySystem.ic.service.base.GoodsAttributeFormatService#getFormatByFamilyId(java.lang.Integer)
     *@MethodName:getFormatByFamilyId
     *@Description:根据类型获取规格
     *@param familyId
     *@return
     *@throws Exception
     *@Author:yanwuyang
     *@Date:2014-8-24下午06:17:17
     */
    public List<GoodsFormatDTO> getFormatByFamilyId(Integer familyId, String type) throws Exception {
        List<GoodsFormatDTO> formatDTOs = formatDao.getFormatByFamilyId(familyId);
        if (type.equals("list")) {
            return formatDTOs;
        }
        List<GoodsFormatDTO> pformatDTOs = formatDao.getParentFormatByFamilyId(familyId);
        if (pformatDTOs == null) {
            return formatDTOs;
        }
        if (formatDTOs == null) {
            return pformatDTOs;
        }
        List<GoodsFormatDTO> list = new ArrayList<GoodsFormatDTO>();
        list.addAll(pformatDTOs);
        for (int i = 0; i < formatDTOs.size(); i++) {
            GoodsFormatDTO childDTO = formatDTOs.get(i);
            int cId = childDTO.getFormatId();
            for (int j = 0; j < pformatDTOs.size(); j++) {
                GoodsFormatDTO parentChildDTO = pformatDTOs.get(j);
                int pId = parentChildDTO.getFormatId();
                if (cId == pId) {
                    break;
                }
                if (j == pformatDTOs.size() - 1) {
                    list.add(childDTO);
                }
            }
        }
        return list;
    }

    /**
     * 
     *@OverRiddenMethod：@see com.paySystem.ic.service.base.GoodsAttributeFormatService#getAllGoodsFormatGroupRela()
     *@MethodName:getAllGoodsFormatGroupRela
     *@Description:获取所有的规格分组关联
     *@return
     *@throws Exception
     *@Author:yanwuyang
     *@Date:2014-8-24下午06:17:46
     */
    public List<GoodsFormatGroupRelaDTO> getAllGoodsFormatGroupRela() throws Exception {
        return formatGroupRelaDao.getAllGoodsFormatGroupRela();
    }

    /**
     * 
     *@OverRiddenMethod：@see com.paySystem.ic.service.base.GoodsAttributeFormatService#getAssociatedFamily(int, int, com.paySystem.ic.web.dto.base.GoodsFamilyDTO, java.util.LinkedHashMap)
     *@MethodName:getAssociatedFamily
     *@Description:获取关联的类型
     *@param firstPage
     *@param pageNum
     *@param familyDTO
     *@param orderBy
     *@return
     *@throws Exception
     *@Author:yanwuyang
     *@Date:2014-8-24下午06:18:05
     */
    public QueryResult<List> getAssociatedFamily(int firstPage, int pageNum, GoodsFamilyDTO familyDTO,
            LinkedHashMap<String, String> orderBy) throws Exception {
        return familyAttrRelaDao.getAssociatedFamily(firstPage, pageNum, familyDTO, orderBy);
    }

    /**
     * 
     *@OverRiddenMethod：@see com.paySystem.ic.service.base.GoodsAttributeFormatService#deleteAssociatedFamily(java.lang.Integer)
     *@MethodName:deleteAssociatedFamily
     *@Description:删除
     *@param familyId
     *@throws Exception
     *@Author:yanwuyang
     *@Date:2014-8-24下午06:18:34
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteAssociatedFamily(Integer familyId) throws Exception {
        familyGroupRelaDao.deleteByFamilyId(familyId);
        familyAttrRelaDao.deleteByFamilyId(familyId);
    }

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
    public boolean checkFormatName(String name, Integer fgroupId) throws Exception {
        return formatDao.checkName(name, fgroupId);
    }

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
    public boolean checkAttributeName(String name) throws Exception {
        return attributeDao.checkName(name);
    }

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
    public boolean checkFormatGroupName(String name) throws Exception {
        return formatGroupDao.checkName(name);
    }

    /**
     * 
     *@OverRiddenMethod：@see com.paySystem.ic.service.base.GoodsAttributeFormatService#updateStautsByFamilyId(java.lang.Integer, java.lang.Integer)
     *@MethodName:updateStautsByFamilyId
     *@Description:修改状态
     *@param familyId
     *@param status
     *@Author:yanwuyang
     *@Date:2014-8-24下午11:12:07
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateStautsByFamilyId(Integer familyId, Integer status) {
        familyAttrRelaDao.updateStautsByFamilyId(familyId, status);
        familyGroupRelaDao.updateStautsByFamilyId(familyId, status);
    }

    /**
     * 
     *@OverRiddenMethod：@see com.paySystem.ic.service.base.GoodsAttributeFormatService#getFirstFamily()
     *@MethodName:getFirstFamily
     *@Description:获取第一个分类
     *@return
     *@throws Exception
     *@Author:yanwuyang
     *@Date:2014-9-23下午2:57:01
     */
    public GoodsFamilyDTO getFirstFamily() throws Exception {
        return familyAttrRelaDao.getFirstFamily();
    }

    /**
     *@Description:检验显示名称是否存在
     *@param:@param displayName
     *@param:@return
     *@param:@throws Exception
     *@author: 张亚运
     *@throws:
     */
    @Override
    public boolean checkDisplayName(String showLable) throws Exception {
        return attributeDao.checkDisPlayName(showLable);
    }

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.base.GoodsAttributeFormatService#saveForamtAndFGroup(com.paySystem.ic.web.dto.base.GoodsFormatDTO, com.paySystem.ic.web.dto.base.GoodsFormatGroupDTO)
	 *@MethodName: saveForamtAndFGroup
     *@Description: 保存商品规格和商品规格分组
     *@Params: format 商品规格
     *@Params: formatGroup 商品规格分组
     *@throws: Exception 抛出异常
	 *@Author: 王少辉
	 *@Date: 2014-11-28 下午04:08:37
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveForamtAndFGroup(GoodsFormatDTO format,
			GoodsFormatGroupDTO formatGroup) throws Exception {
		// 如果名称重复，返回重复记录的id，否则返回新增id
		Integer formatId = formatDao.save(format);
		
		String fGroupName = formatGroup.getFgroupName();
		if (null != formatGroup && StringUtils.isNotBlank(fGroupName)) {
			if (!formatGroupDao.checkName(fGroupName)) {
				// 如果名称重复，返回重复记录的id，否则返回新增id
				Integer fgroupId = formatGroupDao.saveObject(formatGroup);
				
				// 保存规格和规格分组后，在规格分组关系表里维护一条数据
				GoodsFormatGroupRelaDTO formatGroupRelaDTO = new GoodsFormatGroupRelaDTO();
				formatGroupRelaDTO.setFormatId(formatId);
				formatGroupRelaDTO.setFgroupId(fgroupId);
				formatGroupRelaDao.save(formatGroupRelaDTO);
			}
		}
		
	}

}
