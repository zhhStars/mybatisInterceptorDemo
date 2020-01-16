package com.example.demo.config;

import java.util.HashMap;

public class Page{

	private int currentPage;// 当前页
	private int pageSize;	// 每页几条
	private int PageCount;	// 总页数
	private int totalNumber;// 总条数
	private int start; // 起始位置
	private HashMap<String, String> param = new HashMap<>();

	public int getCurrentPage() {
		if (this.currentPage <= 0)
			this.currentPage = 1;
		if (this.currentPage > getPageCount())
			this.currentPage = getPageCount();
		return this.currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		if(this.pageSize <= 0){
			this.pageSize = 10;
		}
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageCount() {
		if (this.totalNumber % this.pageSize == 0) {
			this.PageCount = this.totalNumber / this.pageSize;
		} else {
			this.PageCount = this.totalNumber / this.pageSize + 1;
		}  return this.PageCount;
	}

	public void setPageCount(int pageCount) {
		PageCount = pageCount;
	}

	public int getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
	}

	public HashMap<String, String> getParam() {
		return param;
	}

	public void setParam(HashMap<String, String> param) {
		this.param = param;
	}

	public int getStart() {
		this.start = (getCurrentPage() - 1) * getPageSize();
		if (this.start < 0)
			this.start = 0;
		return this.start;
	}

	public void setStart(int start) {
		this.start = start;
	}
}
