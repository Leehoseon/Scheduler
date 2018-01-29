package org.test.dto;

public class DetectDTO {
	private int ageHigh,ageLow;
	private String gender,smile;
	public int getAgeHigh() {
		return ageHigh;
	}
	public void setAgeHigh(int ageHigh) {
		this.ageHigh = ageHigh;
	}
	public int getAgeLow() {
		return ageLow;
	}
	public void setAgeLow(int ageLow) {
		this.ageLow = ageLow;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getSmile() {
		return smile;
	}
	public void setSmile(String smile) {
		this.smile = smile;
	}
	@Override
	public String toString() {
		return "DetectDTO [ageHigh=" + ageHigh + ", ageLow=" + ageLow + ", gender=" + gender + ", smile=" + smile + "]";
	}
	
}
