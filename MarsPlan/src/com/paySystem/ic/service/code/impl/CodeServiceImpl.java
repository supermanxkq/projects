package com.paySystem.ic.service.code.impl;

import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.code.Code;
import com.paySystem.ic.dao.code.CodeDAO;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.service.code.CodeService;
import com.paySystem.ic.utils.EntityDtoConverter;
import com.paySystem.ic.utils.UploadUtil;
import com.paySystem.ic.web.dto.base.StoreInfoDTO;
import com.paySystem.ic.web.dto.code.CodeDTO;

/**
 * @ProjectName:MarsPlan
 * @ClassName:CodeServiceImpl
 * @Description:代码服务类实现
 * @author: 徐半仙儿
 * @version: V1.0
 * @date:Mar 6, 20165:12:04 AM
 */
@Service(CodeService.CODESERVICE)
public class CodeServiceImpl extends DaoSupport<Code> implements CodeService {

	@Resource
	private CodeDAO codeDao;

	/**
	 * @MethodName:queryAll
	 * @Description:前台查询所有的代码集合
	 * @param firstIndex
	 * @param pageNum
	 * @param codeDTO
	 * @param orderBy
	 * @throws Exception
	 * @author:徐凯强
	 * @date:Mar 6, 20165:10:52 AM
	 */
	public QueryResult<Code> queryAll(int firstIndex, int pageNum, CodeDTO codeDTO,
			LinkedHashMap<String, String> orderBy) throws Exception {
		return codeDao.queryAll(firstIndex, pageNum, codeDTO, orderBy);
	}

	/**
	 * @MethodName:addSave
	 * @Description:后台添加代码信息
	 * @param codeDTO
	 * @author:徐凯强
	 * @date:Mar 6, 20165:11:41 AM
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void addSave(CodeDTO codeDTO) {
		/** 上传文件 **/
		String fileName = null;
		try {
			if (codeDTO != null) {
				if (codeDTO.getImageFile() != null && StringUtils.isNotBlank(codeDTO.getImageFileFileName())) {
					try {
						fileName = UploadUtil.uploadImg(codeDTO.getImageFile(), codeDTO.getImageFileFileName());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			Code code = new Code();
			code = (Code) EntityDtoConverter.dto2Bean(codeDTO, code);
			code.setThumbnailPath(fileName);
			this.save(code);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @MethodName:updateData
	 * @Description:后台更新代码信息
	 * @param codeDTO
	 * @author:徐凯强
	 * @return void
	 * @date:Mar 7, 20165:51:56 PM
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateData(CodeDTO codeDTO) {
		Code code = new Code();
		String fileName = null;
		try {
			code = (Code) EntityDtoConverter.dto2Bean(codeDTO, code);
			if (codeDTO != null) {
				if (codeDTO.getImageFile() != null && StringUtils.isNotBlank(codeDTO.getImageFileFileName())) {
					/** 上传文件 **/
					fileName = UploadUtil.uploadImg(codeDTO.getImageFile(), codeDTO.getImageFileFileName());
					code.setThumbnailPath(fileName);
				} else {
					code.setThumbnailPath(codeDTO.getThumbnailPath());
				}
			}
			this.update(code);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}