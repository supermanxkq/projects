package com.paySystem.ic.dao.base.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.GoodsFamily;
import com.paySystem.ic.dao.base.GoodsFamilyDao;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.base.GoodsFamilyDTO;
import com.paySystem.ic.web.dto.system.UserSession;
import com.paySystem.ic.web.ui.OptionsInteger;

/**
 * @ClassName:GoodsFamilyDaoImpl
 * @Description:商品分类接口实现类
 * @date: 2014-6-26下午04:27:09
 * @author: 张亚运
 * @version: V1.0
 */
@Repository(GoodsFamilyDao.GOODSFSMILYDAO)
public class GoodsFamilyDaoImpl extends DaoSupport<GoodsFamily> implements GoodsFamilyDao {

    public QueryResult<GoodsFamilyDTO> queryAll(int page, int pageNum, GoodsFamilyDTO goodsFamilyDto,
            LinkedHashMap<String, String> orderBy) throws Exception {
        List<Object> params = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer();

        /**判断商品分类名称*/
        if (StringUtils.isNotBlank(goodsFamilyDto.getFamilyName())) {
            sql.append(" and o.familyName like ?").append(params.size() + 1);
            params.add("%" + goodsFamilyDto.getFamilyName().trim() + "%");
        }
        /**判断关键字*/
        if (StringUtils.isNotBlank(goodsFamilyDto.getKeyWords())) {
            sql.append(" and o.keyWords like ?").append(params.size() + 1);
            params.add("%" + goodsFamilyDto.getKeyWords().trim() + "%");
        }

        sql.append(" and o.status = ?").append(params.size() + 1);
        params.add(goodsFamilyDto.getStatus());

        sql.append(" order by o.nodeLevel,o.parentId asc");

        QueryResult<GoodsFamilyDTO> queryResult = new QueryResult<GoodsFamilyDTO>();

        /**将查询出的实体转换为Dto*/
        QueryResult<GoodsFamily> result = new QueryResult<GoodsFamily>();
        result = getScrollData(page, pageNum, sql.toString(), params.toArray(), orderBy);
        List<GoodsFamilyDTO> list = new ArrayList<GoodsFamilyDTO>();
        for (int i = 0; i < result.getResultlist().size(); i++) {
            GoodsFamily gf = new GoodsFamily();
            gf = result.getResultlist().get(i);
            GoodsFamilyDTO gfDto = getDto(gf);
            list.add(gfDto);
        }

        queryResult.setTotalrecord(result.getTotalrecord());
        queryResult.setResultlist(list);
        return queryResult;
    }

    /**
     *@Description:添加保存商品分类信息
     *@param:@param goodsFamilyDto
     *@param:@return
     *@author: 张亚运
     *@throws:
     */
    public GoodsFamily saveGoodsFamily(GoodsFamilyDTO goodsFamilyDto) {

        UserSession us = Utils.getUserSession();
        GoodsFamily goodsfamily = new GoodsFamily();
        if (goodsFamilyDto != null) {
            goodsfamily.setParentId(goodsFamilyDto.getParentId());
            goodsfamily.setFamilyName(goodsFamilyDto.getFamilyName());
            goodsfamily.setKeyWords(goodsFamilyDto.getKeyWords());
            goodsfamily.setNodeLevel(goodsFamilyDto.getNodeLevel());
            goodsfamily.setPicPath(goodsFamilyDto.getPicPath());
            goodsfamily.setDefaultShow(goodsFamilyDto.getDefaultShow());
            goodsfamily.setFamilyWay(goodsFamilyDto.getFamilyWay());
            goodsfamily.setStatus(goodsFamilyDto.getStatus());
            goodsfamily.setCreateTime(this.getSysTime());
            goodsfamily.setOperator(us.getUserName());
            this.save(goodsfamily);
        }
        return goodsfamily;
    }

    /**
     *@Description:修改保存商品分类信息
     *@param:@param goodsFamilyDto
     *@author: 张亚运
     *@throws:
     */
    public ReturnDTO updateGoodsFamily(GoodsFamilyDTO goodsFamilyDto) {

        ReturnDTO returnDTO = new ReturnDTO();
        /**获得要更新的数据*/
        GoodsFamily goodsfamily = this.find(goodsFamilyDto.getFamilyId());

        UserSession us = Utils.getUserSession();
        if (goodsfamily != null) {
            goodsfamily.setFamilyId(goodsFamilyDto.getFamilyId());
            goodsfamily.setParentId(goodsFamilyDto.getParentId());
            goodsfamily.setFamilyName(goodsFamilyDto.getFamilyName());
            goodsfamily.setKeyWords(goodsFamilyDto.getKeyWords());
            goodsfamily.setNodeLevel(goodsFamilyDto.getNodeLevel());
            goodsfamily.setPicPath(goodsFamilyDto.getPicPath());
            goodsfamily.setDefaultShow(goodsFamilyDto.getDefaultShow());
            goodsfamily.setFamilyWay(goodsFamilyDto.getFamilyWay());
            goodsfamily.setStatus(goodsFamilyDto.getStatus());
            goodsfamily.setOperator(us.getUserName());
            goodsfamily.setPreFlag(goodsFamilyDto.getPreFlag());
            this.update(goodsfamily);
            returnDTO.setFlag(true);
        }
        return returnDTO;

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
     *@Description:检验商品分类名称是否存在
     *@param:@param familyName
     *@param:@param familyId
     *@param:@return
     *@author: 张亚运
     *@throws:
     */
    public boolean validateName(String familyName, Integer familyId) {
        long count;
        if (familyId == null) {
            count = (Long) em.createQuery("select count(o) from GoodsFamily o where o.familyName = ?1").setParameter(1,
                    familyName).getSingleResult();
        }
        else {
            count = (Long) em.createQuery(
                    "select count(o) from GoodsFamily o where o.familyName = ?1 and o.familyId<>?2").setParameter(1,
                    familyName).setParameter(2, familyId).getSingleResult();
        }
        return count > 0;
    }

    /**
     *@Description:获取所属分类
     *@param:@return
     *@author: 张亚运
     *@throws:
     */
    @SuppressWarnings("unchecked")
    public List<OptionsInteger> getFamilyOption() {
        List<GoodsFamily> gfList = em.createQuery("from GoodsFamily o where o.status = 1 order by o.familyId asc")
                .getResultList();
        List<OptionsInteger> list = new ArrayList<OptionsInteger>();
        for (GoodsFamily gf : gfList) {
            for (int i = 1; i < gf.getNodeLevel(); i++) {
                String sb = "..";
                gf.setFamilyName(sb + gf.getFamilyName());
            }
            list.add(new OptionsInteger(gf.getFamilyId(), gf.getFamilyName()));
        }
        return list;
    }
    
    /**
     *@Description:获取纯洁的所属分类
     *@return:List<GoodsFamily>
     *@author: Jacky
     *@throws:
     */
    @SuppressWarnings("unchecked")
	public List<GoodsFamily> getPureFamilyOption() {
		return em.createQuery("from GoodsFamily o where o.status = 1 order by o.familyId asc")
        .getResultList();
	}

    /**
     *@Description:批量获取分类列表
     *@return:List<GoodsFamily>
     *@author: Jacky
     *@throws:
     */
	@SuppressWarnings("unchecked")
	public List<GoodsFamily> batchQueryGoodsFamily(Set<Integer> familyId) {
		String sql = getBatchSQL(familyId);
		return em.createQuery(sql)
        .getResultList();
	}

	/**
     *@Description: 拼接批量查询分类sql
     *@return: String  sql
     *@author: Jacky
     *@throws:
     */
	private String getBatchSQL(Set<Integer> familyId) {
		StringBuilder sb = new StringBuilder("from GoodsFamily o where o.status = 1 ");
		if(familyId.size() > 0) {
			sb.append(" and o.familyId in(");
			int i=0;
			for(Integer family : familyId) {
				i ++ ;
				sb.append(family);
				if(i < familyId.size()) {
					sb.append(",");
				}
			}
			sb.append(")");
		}
		return sb.toString();
	}
	
	public Integer getNodeLevel(Integer parentId) {
        Object nodeLevel = em.createNativeQuery("select o.nodeLevel from b_goodsfamily o where o.familyId = ?1")
                .setParameter(1, parentId).getSingleResult();
        Integer NodeLevel = Integer.valueOf(nodeLevel.toString());
        return NodeLevel;
    }
}
