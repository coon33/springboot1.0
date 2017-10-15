package com.coonchen.framework.web.page;

public class PageBean {
	private int currIndex;
	private int pageCount;
	private int totalCount;
	private int rowNum;
	private int start;
	private int row;
	
	public PageBean(int currIndex,int rowNum) {
		this.currIndex = currIndex;
		this.rowNum = rowNum;
		start=(currIndex-1)*rowNum;
		row=rowNum;
	}
	
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCurrIndex() {
		return currIndex;
	}
	public void setCurrIndex(int currIndex) {
		this.currIndex = currIndex;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getRowNum() {
		return rowNum;
	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	public void setRowcountAndCompute(int totalCount) {
		setTotalCount(totalCount);
		if(rowNum==0 || totalCount==0) setPageCount(0);
		else {
			int pageCount = totalCount%rowNum==0?totalCount/rowNum:totalCount/rowNum+1;
			setPageCount(pageCount);
		}
	}
}
