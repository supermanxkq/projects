/**  
* @Title: annount.java
* @Package: com.paySystem.ic.web.dto.marketing
* @Description: TODO
* @Author: A18ccms A18ccms_gmail_com  
* @Date: 2014-9-9 上午10:32:38
* @Version: V1.0  
*/
package com.paySystem.ic.web.dto.marketing;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ProjectName:omall20140905
 * @ClassName:AnnountDTO
 * @Description:全站公告管理DTO
 * @date: 2014-9-9
 * @author: 孙晓磊
 * @version: V1.0
 */
public class AnnountDTO extends BaseDTO{
	
	/**公告编号**/
	private Integer AnnounId;
	/**公告标题**/
	private String AnnounTitle;
	/**公告的创建时间**/
	private String CreateTime;
	/**公告的发布人**/
	private String Author;
	/**公告的截止时间**/
	private String StopTime;
	/**公告内容**/
	private String AnnounContent;
	/**状态1:未发布（默认）2:发布 9:删除**/
	private Integer status;
	/**是否置顶0：否(默认)1：是**/
	private Integer isTop;
	/**公告的发布时间**/
	private String reportTime;
	/**get set 	方法**/
	
	public String getAnnounContent() {
		return AnnounContent;
	}
	public String getReportTime() {
		return reportTime;
	}
	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}
	public void setAnnounContent(String announContent) {
		AnnounContent = announContent;
	}
	public Integer getAnnounId() {
		return AnnounId;
	}
	public void setAnnounId(Integer announId) {
		AnnounId = announId;
	}
	public String getAnnounTitle() {
		return AnnounTitle;
	}
	public void setAnnounTitle(String announTitle) {
		AnnounTitle = announTitle;
	}
	
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public String getStopTime() {
		return StopTime;
	}
	public void setStopTime(String stopTime) {
		StopTime = stopTime;
	}
	public String getAuthor() {
		return Author;
	}
	public void setAuthor(String author) {
		Author = author;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getIsTop() {
		return isTop;
	}
	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}
	
	
}
