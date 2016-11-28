package com.paySystem.ic.bean;

public class PageIndex {
	private long startindex;
	private long endindex;

	public PageIndex(long startindex, long endindex) {
		this.startindex = startindex;
		this.endindex = endindex;
	}

	public long getStartindex() {
		return startindex;
	}

	public void setStartindex(long startindex) {
		this.startindex = startindex;
	}

	public long getEndindex() {
		return endindex;
	}

	public void setEndindex(long endindex) {
		this.endindex = endindex;
	}
/*	public static PageIndex getPageIndex(long viewpagecount, int currentPage,long totalpage) {
		long startpage = currentPage - (viewpagecount % 2 == 0 ? viewpagecount / 2 - 1 : viewpagecount / 2);
		long endpage = currentPage + viewpagecount / 2;
		if (startpage < 1) {
			startpage = 1;
			if (totalpage >= viewpagecount)
				endpage = viewpagecount;
			else
				endpage = totalpage;
		}
		if (endpage > totalpage) {
			endpage = totalpage;
			if ((endpage - viewpagecount) > 0)
				startpage = endpage - viewpagecount + 1;
			else
				startpage = 1;
		}
		return new PageIndex(startpage, endpage);
	}*/
	
	public static PageIndex getPageIndex(long totalpage, int currentPage) {
		long startpage = currentPage - (totalpage % 2 == 0 ? totalpage / 2 - 1 : totalpage / 2);
		long endpage = currentPage + totalpage / 2;
		if (startpage < 1) {
			startpage = 1;
			endpage = totalpage;
		}
		if (endpage > totalpage) {
			endpage = totalpage;
			if ((endpage - totalpage) > 0)
				startpage = endpage - totalpage + 1;
			else
				startpage = 1;
		}
		return new PageIndex(startpage, endpage);
	}
}
