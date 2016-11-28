package com.paySystem.ic.service.base.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.MerScoMo;
import com.paySystem.ic.dao.base.MerScoMoDao;
import com.paySystem.ic.service.base.MerScoMoService;
import com.paySystem.ic.web.dto.base.MerScoMoDTO;

/**
 * @Title:MerScoMoServiceImpl
 * @Package: com.paySystem.ic.service.base.impl
 * @Description:商户评分模型接口实现类
 * @Author: 张军磊
 * @Date: 2014-08-06
 * @Version: V1.0
 */
@Service(MerScoMoService.MERSCOMOSERVICE)
public class MerScoMoServiceImpl implements MerScoMoService {

	@Resource
	private MerScoMoDao merScoMoDao;

	/**
	 *@Title:saveMerScoMo
	 *@Description: 保存商户评分模型业务
	 *@param:@param merScoMo 商户评分模型业务参数实体对象
	 *@Return:void
	 *@author: 张军磊
	 *@Thorws:
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveMerScoMo(MerScoMoDTO merScoMoDTO) {
		MerScoMo merScoMo = new MerScoMo();
		BeanUtils.copyProperties(merScoMoDTO, merScoMo);
		merScoMoDao.saveMerScoMo(merScoMo);

	}

	/**
	 *@Title:updateMerScoMo
	 *@Description: 更新商户评分模型业务参数信息
	 *@param:@param merScoMo 商户评分模型业务参数实体对象
	 *@Return:void
	 *@author: 张军磊
	 *@Thorws:
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateMerScoMo(MerScoMoDTO merScoMoDTO) {

		MerScoMo merScoMo = new MerScoMo();
		BeanUtils.copyProperties(merScoMoDTO, merScoMo);
		merScoMoDao.updateMerScoMo(merScoMo);

	}

	/**
	 *@Title:queryLogisticsByCond
	 *@Description:根据条件查询商户评分模型列表
	 *@param:@param firstPage 开始条数
	 *@param:@param pageNum 每页显示调试
	 *@param:@param merScoMoDTO MerScoMoDTO对象
	 *@param:@param orderBy 排序方式
	 *@param:@return
	 *@return:List<LogisticsDTO> 返回DTO集合
	 *@author: 张军磊
	 * @throws Exception
	 *@Thorws:
	 */

	public QueryResult<MerScoMoDTO> queryMerScoMoByCond(int firstPage,
			int pageNum, MerScoMoDTO merScoMoDTO,
			LinkedHashMap<String, String> orderBy) throws Exception {
		QueryResult<MerScoMoDTO> dtoResult = merScoMoDao.queryMerScoMoByCond(
				firstPage, pageNum, merScoMoDTO, orderBy);

		return dtoResult;
	}

	/**
	 *@Title:findById
	 *@Description:根据Id查询商户评分模型信息
	 *@param:@param merScoMoId 接口Id
	 *@param:@return
	 *@return:LogisticsDTO
	 *@author:张军磊
	 *@Thorws:
	 */
	public MerScoMoDTO findById(Integer merScoMoId) {

		MerScoMoDTO merScoMoDTO = merScoMoDao.findById(merScoMoId);

		return merScoMoDTO;
	}

	/**
	 *@Title:delete
	 *@Description: 根据Id删除商户评分模型信息
	 *@param:@return
	 *@return:String
	 *@author: 张军磊
	 *@Thorws:
	 */

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public MerScoMoDTO deleteByMerScoMoId(Integer merScoMoId) throws Exception {
		MerScoMoDTO merScoMoDTO = merScoMoDao.deleteByMerScoMoId(merScoMoId);
		return merScoMoDTO;
	}

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.service.base.MerScoMoService#findmerScoMoName
	 *                        (java.lang.String, java.lang.String,
	 *                        java.lang.Integer)
	 *@MethodName:findmerScoMoName
	 *@Description:判断商户名称是否一样
	 *@param moName
	 *@param method
	 *@param merScoMoId
	 *@return
	 *@Author:张军磊
	 *@Date:2014-9-30上午10:34:00
	 */
	public boolean findmerScoMoName(String moName, String method,
			Integer merScoMoId) {
		boolean flag = false;
		List<MerScoMo> list = merScoMoDao.findmerScoMoName(moName);
		if (method.equals("addSave")) {

			if (list.size() > 0 == true) {
				flag = true;
			}
		} else {

			if (list.size() > 0
					&& !list.get(0).getMerScoMoId().equals(merScoMoId)) {
				flag = true;
			}
		}
		return flag;
	}

	/**
	 *@Title:findMaxSco
	 *@Description:判断评分时否已有集合存在
	 *@Params:@param maxSco
	 *@Params:@param method
	 *@Params:@param merScoMoId
	 *@Params:@return
	 *@Return:boolean
	 *@author:张军磊
	 *@Date:2014-9-30上午10:39:53
	 */
	public boolean findMaxSco(Integer maxSco, String method, Integer merScoMoId) {
		boolean flag = false;
		List<MerScoMo> list = merScoMoDao.findMaxSco(maxSco);
		if (method.equals("addSave")) {

			if (list.size() > 0 == true) {
				flag = true;
			}
		} else {

			if (list.size() > 0 && !list.get(0).getMerScoMoId().equals(merScoMoId)) {
				flag = true;
			}
		}
		return flag;

	}

}
