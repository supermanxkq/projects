package com.ssi.stu.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.ssi.stu.Constants;

public class BasePageAction extends BaseAction implements ServletRequestAware {

	private static final long serialVersionUID = 1L;
	public int totalCount;
	public int currentPageNo = 1;
	public int pageSize = Constants.INIT_PAGESIZE;

	public int totalCount_Tab;
	public int currentPageNo_Tab = 1;
	public int pageSize_Tab = Constants.INIT_PAGESIZE;

	public BasePageAction() {
		super();
	}

	public void setServletRequest(HttpServletRequest request) {
		pageSize = Constants.INIT_PAGESIZE;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				try {
					if (cookie.getName().equals(Constants.COOKIE_PAGESIZE)) {
						if (cookie.getValue() != null) {
							pageSize = Integer.parseInt(cookie.getValue()
									.toString());
						}
						break;
					}
				} catch (Exception e) {
					pageSize = Constants.INIT_PAGESIZE;
				}
			}
		}

		pageSize_Tab = Constants.INIT_PAGESIZE;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				try {
					if (cookie.getName().equals(Constants.COOKIE_PAGESIZE)) {
						if (cookie.getValue() != null) {
							pageSize_Tab = Integer.parseInt(cookie.getValue()
									.toString());
						}
						break;
					}
				} catch (Exception e) {
					pageSize = Constants.INIT_PAGESIZE;
				}
			}
		}
	}

	public void pageNumberCheck() {
		int lastCount = totalCount - pageSize * (currentPageNo - 1);
		int totalPageCount = (totalCount - 1) / pageSize + 1;
		if (((totalCount > 0) && !(lastCount > 0))
				|| (currentPageNo > totalPageCount)) {
			currentPageNo = totalPageCount;
		}
	}

	public void pageNumberCheck_tab() {
		int lastCount = totalCount_Tab - pageSize_Tab * (currentPageNo_Tab - 1);
		int totalPageCount = (totalCount_Tab - 1) / pageSize_Tab + 1;
		if (((totalCount_Tab > 0) && !(lastCount > 0))
				|| (currentPageNo_Tab > totalPageCount)) {
			currentPageNo_Tab = totalPageCount;
		}
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount_Tab() {
		return totalCount_Tab;
	}

	public void setTotalCount_Tab(int totalCountTab) {
		totalCount_Tab = totalCountTab;
	}

	public int getCurrentPageNo_Tab() {
		return currentPageNo_Tab;
	}

	public void setCurrentPageNo_Tab(int currentPageNoTab) {
		currentPageNo_Tab = currentPageNoTab;
	}

	public int getPageSize_Tab() {
		return pageSize_Tab;
	}

	public void setPageSize_Tab(int pageSizeTab) {
		pageSize_Tab = pageSizeTab;
	}

}
