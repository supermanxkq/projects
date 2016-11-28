package com.blog.web.action.gallery;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.blog.bean.QueryResult;
import com.blog.bean.photo.Photo;
import com.blog.service.gallery.GalleryService;
import com.blog.util.OrderUtils;
import com.blog.web.action.BaseAction;

/**
 * 登录Action类
 * 
 * @version 2013-9-1 上午11:12:59
 */

@Controller("/gallery/gallery")
@Scope("prototype")
public class GalleryAction extends BaseAction {
	private static final long serialVersionUID = 6527405819743413855L;
	private File file_upload;

	private String file_uploadFileName;
	@Resource
	private GalleryService galleryService;
	private Photo photo = new Photo();
	List<Photo> photos = new ArrayList<Photo>();

	public String list() {
		return "list";
	}

	@SuppressWarnings("unchecked")
	public String queryAll() {
		try {
			QueryResult<Photo> photosList = galleryService.getScrollData();
			photos=photosList.getResultlist();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "query";
	}

	@SuppressWarnings("deprecation")
	public String uploadImg() throws Exception {

		String extName = "";// 扩展名

		String newFileName = "";// 新文件名

		String nowTime = new SimpleDateFormat("yyyymmddHHmmss")
				.format(new Date());// 当前时间

		String savePath = ServletActionContext.getServletContext().getRealPath(
				"/");

		savePath = savePath + "UploadImgs/photo/";
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");

		// 获取扩展名
		if (file_uploadFileName.lastIndexOf(".") >= 0) {
			extName = file_uploadFileName.substring(file_uploadFileName
					.lastIndexOf("."));
		}
		newFileName = nowTime +OrderUtils.getOrderNo()+ extName;

		file_upload.renameTo(new File(savePath + newFileName));
		photo.setCreateDate(new Date());
		photo.setImgSrc("UploadImgs/photo/" + newFileName);
		galleryService.save(photo);
		response.getWriter().print(file_uploadFileName + "上传成功");

		return null; // 这里不需要页面转向，所以返回空就可以了
	}

	public File getFile_upload() {
		return file_upload;
	}

	public void setFile_upload(File fileUpload) {
		file_upload = fileUpload;
	}

	public String getFile_uploadFileName() {
		return file_uploadFileName;
	}

	public void setFile_uploadFileName(String fileUploadFileName) {
		file_uploadFileName = fileUploadFileName;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

}
