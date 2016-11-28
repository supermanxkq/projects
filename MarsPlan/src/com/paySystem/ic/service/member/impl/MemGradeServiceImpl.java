package com.paySystem.ic.service.member.impl;

import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.member.MemGrade;
import com.paySystem.ic.dao.member.MemGradeDao;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.service.member.MemGradeService;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.member.MemGradeDTO;

/**
 * @ClassName:MemGradeServiceImpl
 * @Description:会员等级Service实现类
 * @date: 2014-7-15下午03:07:09
 * @author: 张亚运
 * @version: V1.0
 */
@Service(MemGradeService.MEMGRADESERVICE)
public class MemGradeServiceImpl extends DaoSupport<MemGrade> implements MemGradeService {

    @Resource
    MemGradeDao memGradeDao;

    public QueryResult<MemGradeDTO> queryAll(int page, int pageNum, MemGradeDTO memGradeDto,
            LinkedHashMap<String, String> orderby) throws Exception {

        return memGradeDao.queryAll(page, pageNum, memGradeDto, orderby);
    }

    /**
     *@Description:添加保存会员等级
     *@param:@param memGradeDto
     *@param:@return
     *@author: 张亚运
     *@throws:
     */
    public MemGrade saveMemGrade(MemGradeDTO memGradeDto) {
        MemGrade memGrade = new MemGrade();
        memGrade = memGradeDao.saveMemGrade(memGradeDto);
        return memGrade;
    }

    /**
     *@Description:根据Id查询该条信息
     *@param:@param gradeId
     *@param:@return
     *@author: 张亚运
     *@throws:
     */
    public MemGradeDTO findGrade(Integer gradeId) {
        MemGradeDTO memGradeDto = new MemGradeDTO();
        MemGrade memGrade = memGradeDao.find(gradeId);
        memGradeDto = memGradeDao.getDto(memGrade);
        return memGradeDto;
    }

    /**
     *@Description:修改保存会员等级
     *@param:@param memGradeDto
     *@param:@return
     *@author: 张亚运
     *@throws:
     */
    public ReturnDTO updateMemGrade(MemGradeDTO memGradeDto) {
        ReturnDTO returnDto = new ReturnDTO();
        returnDto = memGradeDao.updateMemGrade(memGradeDto);
        return returnDto;
    }

    /**
     *@Description:删除会员等级（修改状态）
     *@param:@param gradeId
     *@author: 张亚运
     *@throws:
     */
    public ReturnDTO deleteMemGrade(Integer gradeId) {
        ReturnDTO returnDTO = new ReturnDTO();
        MemGrade memGrade = memGradeDao.find(gradeId);
        if (memGrade != null) {
            memGrade.setStatus(9);
            memGradeDao.update(memGrade);
            returnDTO.setFlag(true);
        }
        return returnDTO;
    }

    public boolean validateName(String gradeName, Integer gradeId) {

        return memGradeDao.validateNeme(gradeName, gradeId);
    }

}
