package com.paySystem.ic.service.member;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.member.MemGrade;
import com.paySystem.ic.service.common.DAO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.member.MemGradeDTO;

/**
 * @ClassName:MemGradeService
 * @Description:会员等级Service
 * @date: 2014-7-15下午03:06:29
 * @author: 张亚运
 * @version: V1.0
 */
public interface MemGradeService extends DAO<MemGrade> {

    public static final String MEMGRADESERVICE = "memGradeService";

    QueryResult<MemGradeDTO> queryAll(int page, int pageNum, MemGradeDTO memGradeDto,
            LinkedHashMap<String, String> orderby) throws Exception;

    /**
     *@Description:添加保存会员等级
     *@return:MemGrade
     *@author: 张亚运
     *@throws:
     */
    public MemGrade saveMemGrade(MemGradeDTO memGradeDto);

    /**
     *@Description:根据Id查询该条信息
     *@return:MemGradeDTO
     *@author: 张亚运
     *@throws:
     */
    public MemGradeDTO findGrade(Integer gradeId);

    /**
     *@Description:修改保存会员等级
     *@return:ReturnDTO
     *@author: 张亚运
     *@throws:
     */
    public ReturnDTO updateMemGrade(MemGradeDTO memGradeDto);

    /**
     *@Description:删除会员等级（修改状态）
     *@return:void
     *@author: 张亚运
     *@throws:
     */
    public ReturnDTO deleteMemGrade(Integer gradeId);

    /**
     *@Description:检验等级名称是否存在
     *@return:boolean
     *@author: 张亚运
     *@throws:
     */
    public boolean validateName(String gradeName, Integer gradeId);

}
