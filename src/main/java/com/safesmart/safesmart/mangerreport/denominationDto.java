package com.safesmart.safesmart.mangerreport;

public class denominationDto {

	private String denominations;
	private String in_Values;
	private String out_Values;
	public String getDenominations() {
		return denominations;
	}
	public void setDenominations(String denominations) {
		this.denominations = denominations;
	}
	public String getIn_Values() {
		return in_Values;
	}
	public void setIn_Values(String in_Values) {
		this.in_Values = in_Values;
	}
	public String getOut_Values() {
		return out_Values;
	}
	public void setOut_Values(String out_Values) {
		this.out_Values = out_Values;
	}
	public denominationDto(String denominations, String in_Values, String out_Values) {
		super();
		this.denominations = denominations;
		this.in_Values = in_Values;
		this.out_Values = out_Values;
	}
	@Override
	public String toString() {
		return "denominationDto [denominations=" + denominations + ", in_Values=" + in_Values + ", out_Values="
				+ out_Values + "]";
	}

	

	
}
