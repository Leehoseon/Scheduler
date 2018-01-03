package org.test.dto;

public class Criteria {
	
	private int page;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if(page < 1) {
			this.page = 10;
		}
		else if(page >= 2){
		
			this.page = page * 10;
		}
	}

	@Override
	public String toString() {
		return "Criteria [page=" + page + "]";
	}
	

}
