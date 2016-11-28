package com.paySystem.ic.service.software.impl;

import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.software.SoftWare;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.software.SoftWareDao;
import com.paySystem.ic.service.software.SoftWareService;
import com.paySystem.ic.utils.EntityDtoConverter;
import com.paySystem.ic.utils.UploadUtil;
import com.paySystem.ic.web.dto.software.SoftWareDTO;

/**
 * @ProjectName:MarsPlan
 * @ClassName:SoftWareServiceImpl
 * @Description:软件服务实现类
 * @author: 徐半仙儿
 * @version: V1.0
 * @date:Mar 9, 20168:58:29 PM
 */
@Service(SoftWareService.SOFTWARESERVICE)
public class SoftWareServiceImpl extends DaoSupport<SoftWare> implements SoftWareService {

	@Resource
	private SoftWareDao softWareDao;

	/**
	 * @MethodName:queryAll
	 * @Description:前台查询所有的软件集合
	 * @param firstIndex
	 * @param pageNum
	 * @param softWareDTO
	 * @param orderBy
	 * @return
	 * @throws Exception
	 * @author:徐凯强
	 * @date:Mar 9, 20168:58:55 PM
	 */
	public QueryResult<SoftWare> queryAll(int firstIndex, int pageNum, SoftWareDTO softWareDTO,
			LinkedHashMap<String, String> orderBy) throws Exception {
		return softWareDao.queryAll(firstIndex, pageNum, softWareDTO, orderBy);
	}

	/**
	 * @MethodName:addSave
	 * @Description:后台添加软件信息
	 * @param softWareDTO
	 * @author:徐凯强
	 * @date:Mar 9, 20168:59:19 PM
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void addSave(SoftWareDTO softWareDTO) {
		/** 上传文件 **/
		String fileName = null;
		try {
			if (softWareDTO != null) {
				if (softWareDTO.getImageFile() != null && StringUtils.isNotBlank(softWareDTO.getImageFileFileName())) {
					try {
						fileName = UploadUtil.uploadImg(softWareDTO.getImageFile(), softWareDTO.getImageFileFileName());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			SoftWare softWare = new SoftWare();
			softWare = (SoftWare) EntityDtoConverter.dto2Bean(softWareDTO, softWare);
			softWare.setThumbnailPath(fileName);
			this.save(softWare);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *@MethodName:updateData
	 *@Description:后台更新软件信息
	 *@param softWareDTO 
	 *@author:徐凯强
	 *@date:Mar 9, 20169:00:05 PM
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateData(SoftWareDTO softWareDTO) {
		SoftWare softWare = new SoftWare();
		String fileName = null;
		try {
			softWare = (SoftWare) EntityDtoConverter.dto2Bean(softWareDTO, softWare);
			if (softWareDTO != null) {
				if (softWareDTO.getImageFile() != null && StringUtils.isNotBlank(softWareDTO.getImageFileFileName())) {
					/** 上传文件 **/
					fileName = UploadUtil.uploadImg(softWareDTO.getImageFile(), softWareDTO.getImageFileFileName());
					softWare.setThumbnailPath(fileName);
				} else {
					softWare.setThumbnailPath(softWareDTO.getThumbnailPath());
				}
			}
			this.update(softWare);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}