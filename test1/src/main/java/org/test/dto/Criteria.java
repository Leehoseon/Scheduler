package org.test.dto;

public class Criteria {
	
	private int page;
	String searchType,searchText,text,writer,title,sortType,order;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		
		if(page <= 1) {
			this.page = 0;
		}
		else if(page >= 2){
		
			this.page = (page-1) * 10;
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}
	

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
	

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "Criteria [page=" + page + ", searchType=" + searchType + ", searchText=" + searchText + ", text=" + text
				+ ", writer=" + writer + "]";
	}

	

}
