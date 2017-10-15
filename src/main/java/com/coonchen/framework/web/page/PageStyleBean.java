package com.coonchen.framework.web.page;

public class PageStyleBean {
	private String startPage;
	private String firstPage;
	private String lastPage;
	private String otherPage;
	private String currentPage;
	private String previousPage;
	private String nextPage;
	private String endPage;
	private int pageMaxNum = 11;
	
	public String getStartPage() {
		return startPage;
	}
	public void setStartPage(String startPage) {
		this.startPage = startPage;
	}
	public String getEndPage() {
		return endPage;
	}
	public void setEndPage(String endPage) {
		this.endPage = endPage;
	}
	public String getFirstPage() {
		return firstPage;
	}
	public void setFirstPage(String firstPage) {
		this.firstPage = firstPage;
	}
	public String getLastPage() {
		return lastPage;
	}
	public void setLastPage(String lastPage) {
		this.lastPage = lastPage;
	}
	public String getOtherPage() {
		return otherPage;
	}
	public void setOtherPage(String otherPage) {
		this.otherPage = otherPage;
	}
	public String getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}
	public String getPreviousPage() {
		return previousPage;
	}
	public void setPreviousPage(String previousPage) {
		this.previousPage = previousPage;
	}
	public String getNextPage() {
		return nextPage;
	}
	public void setNextPage(String nextPage) {
		this.nextPage = nextPage;
	}
	public int getPageMaxNum() {
		return pageMaxNum;
	}
	public void setPageMaxNum(int pageMaxNum) {
		this.pageMaxNum = pageMaxNum;
	}
}
