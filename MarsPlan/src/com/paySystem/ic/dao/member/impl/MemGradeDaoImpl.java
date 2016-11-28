package com.paySystem.ic.dao.member.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.member.MemGrade;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.member.MemGradeDao;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.member.MemGradeDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * @ClassName:MemGradeDaoImpl
 * @Description:会员等级实现类
 * @date: 2014-7-15下午03:01:19
 * @author: 张亚运
 * @version: V1.0
 */
@Repository(MemGradeDao.MEMGRADEDAO)
public class MemGradeDaoImpl extends DaoSupport<MemGrade> implements MemGradeDao {

    /**
     *@Description:查询方法
     *@param:@param page
     *@param:@param pageNum
     *@param:@param memGradeDto
     *@param:@param orderby
     *@param:@return
     *@param:@throws Exception
     *@author: 张亚运
     *@throws:
     */
    public QueryResult<MemGradeDTO> queryAll(int page, int pageNum, MemGradeDTO memGradeDto,
            LinkedHashMap<String, String> orderby) throws Exception {

        List<Object> params = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer();

        /**判断等级名称*/
        if (StringUtils.isNotBlank(memGradeDto.getGradeName())) {
            sql.append(" and o.gradeName like ?").append(params.size() + 1);
            params.add("%" + memGradeDto.getGradeName().trim() + "%");
        }
        /**判断使用状态*/
        if (memGradeDto.getStatus() != 2) {
            sql.append(" and o.status = ?").append(params.size() + 1);
            params.add(memGradeDto.getStatus());
        }

        QueryResult<MemGradeDTO> queryResult = new QueryResult<MemGradeDTO>();

        /**将查询出的实体转换为Dto*/
        QueryResult<MemGrade> result = new QueryResult<MemGrade>();
        result = getScrollData(page, pageNum, sql.toString(), params.toArray(), orderby);
        List<MemGradeDTO> list = new ArrayList<MemGradeDTO>();
        for (int i = 0; i < result.getResultlist().size(); i++) {
            MemGrade mg = new MemGrade();
            mg = result.getResultlist().get(i);
            MemGradeDTO mgDto = getDto(mg);
            list.add(mgDto);
        }

        queryResult.setTotalrecord(result.getTotalrecord());
        queryResult.setResultlist(list);
        return queryResult;
    }

    /**
     *@Description:将实体转为Dto
     *@return:MemGradeDTO
     *@author: 张亚运
     *@throws:
     */
    public MemGradeDTO getDto(MemGrade mg) {
        MemGradeDTO dto = new MemGradeDTO();
        if (mg != null) {
            dto.setGradeId(mg.getGradeId());
            dto.setGradeName(mg.getGradeName());
            dto.setLowerLimit(mg.getLowerLimit());
            dto.setUpperLimit(mg.getUpperLimit());
            dto.setStatus(mg.getStatus());
            dto.setOperator(mg.getOperator());
            dto.setCreateTime(mg.getCreateTime());
            dto.setUpdateTime(mg.getUpdateTime());
        }
        return dto;
    }

    /**
     *@Description:获取会员等级Id
     *@return:Integer
     *@author: 张亚运
     *@throws:
     */
    @SuppressWarnings("unchecked")
    public Integer getGradeId() {
        Integer gradeId = 0;
        String sql = "select m.gradeId from m_memgrade m order by m.gradeId desc";
        List<Object[]> objlist = new ArrayList<Object[]>();
        objlist = em.createNativeQuery(sql.toString()).getResultList();

        if (objlist.size() != 0) {
            Object obj = objlist.get(0);
            gradeId = Integer.parseInt(obj.toString());
            gradeId = gradeId + 1;
        }
        else {
            gradeId = 10000000;
        }
        return gradeId;
    }

    /**
     *@Description:添加保存会员等级
     *@param:@param memGradeDto
     *@param:@return
     *@author: 张亚运
     *@throws:
     */
    public synchronized MemGrade saveMemGrade(MemGradeDTO memGradeDto) {
        MemGrade memGrade = new MemGrade();
        if (memGradeDto != null) {
            memGrade.setGradeId(this.getGradeId());
            memGrade.setGradeName(memGradeDto.getGradeName());
            memGrade.setLowerLimit(memGradeDto.getLowerLimit());
            memGrade.setUpperLimit(memGradeDto.getUpperLimit());
            memGrade.setStatus(memGradeDto.getStatus());
            memGrade.setCreateTime(this.getSysTime());
            memGrade.setOperator(memGradeDto.getOperator());
            memGrade.setUpdateTime(memGradeDto.getUpdateTime());
            this.save(memGrade);
        }

        return memGrade;
    }

    /**
     *@Description:修改保存会员等级
     *@param:@param memGradeDto
     *@param:@return
     *@author: 张亚运
     *@throws:
     */
    public ReturnDTO updateMemGrade(MemGradeDTO memGradeDto) {
        ReturnDTO returnDTO = new ReturnDTO();
        /**获得要更新的数据*/
        MemGrade memGrade = this.find(memGradeDto.getGradeId());
        /**更新要修改的数据*/
        UserSession us = Utils.getUserSession();
        if (memGrade != null) {
            memGrade.setGradeId(memGradeDto.getGradeId());
            memGrade.setGradeName(memGradeDto.getGradeName());
            memGrade.setLowerLimit(memGradeDto.getLowerLimit());
            memGrade.setUpperLimit(memGradeDto.getUpperLimit());
            memGrade.setStatus(memGradeDto.getStatus());
            memGrade.setCreateTime(memGrade.getCreateTime());
            memGrade.setOperator(us.getRealName());
            memGrade.setUpdateTime(this.getSysTime());
            this.update(memGrade);
            returnDTO.setFlag(true);
        }
        return returnDTO;

    }

    /**
     *@Description:检验等级名称是否存在
     *@param:@param gradeName
     *@param:@param gradeId
     *@param:@return
     *@author: 张亚运
     *@throws:
     */
    public boolean validateNeme(String gradeName, Integer gradeId) {
        long count;
        if (gradeId == null) {
            count = (Long) em.createQuery("select count(m) from MemGrade m where m.gradeName = ?1").setParameter(1,
                    gradeName).getSingleResult();
        }
        else {
            count = (Long) em.createQuery("select count(m) from MemGrade m where m.gradeName = ?1 and m.gradeId<>?2")
                    .setParameter(1, gradeName).setParameter(2, gradeId).getSingleResult();
        }
        return count > 0;
    }

}
