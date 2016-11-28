package com.paySystem.ic.dao.member;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.member.MemGrade;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.member.MemGradeDTO;

/**
 * @ClassName:MemGradeDao
 * @Description:会员等级接口
 * @date: 2014-7-15下午02:55:15
 * @author: 张亚运
 * @version: V1.0
 */
public interface MemGradeDao extends DAO<MemGrade> {

    public static final String MEMGRADEDAO = "memGradeDao";

    /**
     *@Description:查询方法
     *@return:QueryResult<MemGradeDTO>
     *@author: 张亚运
     *@throws:
     */
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
     *@Description:实体转换Dto
     *@return:MemGradeDTO
     *@author: 张亚运
     *@throws:
     */
    public MemGradeDTO getDto(MemGrade mg);

    /**
     *@Description:修改保存会员等级
     *@return:ReturnDTO
     *@author: 张亚运
     *@throws:
     */
    public ReturnDTO updateMemGrade(MemGradeDTO memGradeDto);

    /**
     *@Description:检验等级名称是否存在
     *@return:boolean
     *@author: 张亚运
     *@throws:
     */
    public boolean validateNeme(String gradeName, Integer gradeId);

}
