package com.blog.service.gallery.impl;

import org.springframework.stereotype.Service;

import com.blog.bean.photo.Photo;
import com.blog.dao.common.DaoSupport;
import com.blog.service.gallery.GalleryService;

/**
 * @ProjectName:omall
 * @ClassName:MessServParamConfigServiceImpl
 * @Description:TODO
 * @date: 2014-7-22下午02:58:05
 * @author: 徐凯强
 * @version: V1.0
 */
@Service(GalleryService.GALLERYSERVICE)
public class GalleryServiceImpl extends DaoSupport<Photo> implements
		GalleryService {


}
