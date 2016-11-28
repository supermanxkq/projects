package com.paySystem.ic.service.report.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paySystem.ic.dao.message.ShortMesSendDao;
import com.paySystem.ic.dao.report.MessConsTotalDao;
import com.paySystem.ic.service.report.MessTotalService;
import com.paySystem.ic.web.dto.report.MessConsTotalDTO;
import com.paySystem.ic.web.dto.report.TermConsTotalDTO;

/**
 * @ClassName:MerConsTotalService
 * @Description:短信费用统计Service接口
 * @date: 2014-2-27上午11:36:50
 * @author: 张国法
 * @version: V1.0
 */

@Service(MessTotalService.MESSCONSTOTALSERVICE)
public class MessConsTotalServiceImpl implements MessTotalService {

    @Resource
    ShortMesSendDao smsDao;

    @Resource
    MessConsTotalDao messConsTotalDao;

    public List<MessConsTotalDTO> queryAll(int page, int pageNum, MessConsTotalDTO messConsTotalDTO,
            LinkedHashMap<String, String> orderBy) throws Exception {

        return messConsTotalDao.queryAll(page, pageNum, messConsTotalDTO, orderBy);
    }

	/**
	 *@Title:queryMerConsTotalReport
	 *@Description:导出报表Service
	 *@param:@return
	 *@return:ReturnDTO
	 *@author:张国法
	 */
	public List<MessConsTotalDTO> queryMessConsTotalReport(
			MessConsTotalDTO merConsTotal,
			LinkedHashMap<String, String> orderBy) {
		// TODO Auto-generated method stub
		return messConsTotalDao.queryMessConsTotalReport(merConsTotal, orderBy);
	}

}
