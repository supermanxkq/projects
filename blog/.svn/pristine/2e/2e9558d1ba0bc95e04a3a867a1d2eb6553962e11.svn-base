package com.blog.service.goods.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.blog.bean.QueryResult;
import com.blog.bean.goods.Goods;
import com.blog.dao.common.DaoSupport;
import com.blog.dto.goods.GoodsDTO;
import com.blog.service.goods.GoodsService;
import com.blog.util.EntityDtoConverter;
import com.blog.util.UploadUtil;

@Service(GoodsService.GOODSSERVICE)
public class GoodsServiceImpl extends DaoSupport<Goods> implements GoodsService {

	@Override
	public QueryResult<Goods> queryAll() {
		try {
			return this.getScrollData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void addSave(GoodsDTO goodsDTO) {
		/** 上传文件 **/
		String fileName = null;
		try {
			if (goodsDTO != null) {
				if (goodsDTO.getImageFile() != null
						&& StringUtils.isNotBlank(goodsDTO
								.getImageFileFileName())) {
					try {
						fileName = UploadUtil.uploadImg(
								goodsDTO.getImageFile(), goodsDTO
										.getImageFileFileName());
					} catch (Exception e) {
						e.printStackTrace();
					}
					/** 设置文件名 **/
					goodsDTO.setImageFileFileName(fileName);
				}
			}
			Goods goods = new Goods();
			goods = (Goods) EntityDtoConverter.dto2Bean(goodsDTO, goods);
			goods.setImgsrc(fileName);
			this.save(goods);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
