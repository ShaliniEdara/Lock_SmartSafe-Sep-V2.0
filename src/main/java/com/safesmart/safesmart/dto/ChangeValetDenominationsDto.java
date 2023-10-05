package com.safesmart.safesmart.dto;

import java.util.ArrayList;
import java.util.List;

import com.safesmart.safesmart.model.ChangeRequest;
import com.safesmart.safesmart.model.ValetDenominations;

public class ChangeValetDenominationsDto {
	
	private Long id;

	@Override
	public String toString() {
		return "ChangeValetDenominationsDto [id=" + id + ", old_cents=" + old_cents + ", new_cents=" + new_cents
				+ ", old_nickels=" + old_nickels + ", new_nickels=" + new_nickels + ", old_dimes=" + old_dimes
				+ ", new_dimes=" + new_dimes + ", old_quarters=" + old_quarters + ", new_quarters=" + new_quarters
				+ ", old_den_1$=" + old_den_1$ + ", new_den_1$=" + new_den_1$ + ", old_den_5$=" + old_den_5$
				+ ", new_den_5$=" + new_den_5$ + ", old_den_10$=" + old_den_10$ + ", new_den_10$=" + new_den_10$
				+ ", old_den_20$=" + old_den_20$ + ", new_den_20$=" + new_den_20$ + ", old_den_50$=" + old_den_50$
				+ ", new_den_50$=" + new_den_50$ + ", old_den_100$=" + old_den_100$ + ", new_den_100$=" + new_den_100$
				+ ", type=" + type + ", updatedByUser=" + updatedByUser + ", valetDenominationsId="
				+ valetDenominationsId + ", valetDenominationsDto=" + valetDenominationsDto + ", updatedTime="
				+ updatedTime + "]";
	}

	private Integer old_cents;

	
	public ChangeValetDenominationsDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	private Integer new_cents;

	private Integer old_nickels;

	private Integer new_nickels;

	private Integer old_dimes;

	private Integer new_dimes;

	private Integer old_quarters;

	private Integer new_quarters;

	private Integer old_den_1$;

	private Integer new_den_1$;

	private Integer old_den_5$;

	private Integer new_den_5$;

	private Integer old_den_10$;

	private Integer new_den_10$;

	private Integer old_den_20$;

	private Integer new_den_20$;

	private Integer old_den_50$;

	private Integer new_den_50$;

	private Integer old_den_100$;

	private Integer new_den_100$;

	private String type;

	private Long updatedByUser;

	private Long valetDenominationsId;
	
	private ValetDenominationsDto valetDenominationsDto;
	
	private Long changeRequestId;
	
	private ChangeRequestDto changeRequestDto;
	
	private String updatedTime;
	
	
	
	
		public List<ChangedCurrencyDto> difference(ChangeValetDenominationsDto c){
		
		List<ChangedCurrencyDto> list = new ArrayList<ChangedCurrencyDto>();
		int currencytotal =0;
		int depositedValue = 0;
		int changeNeeded = 0;
		int newcurrencytotal =0;
		
		int diff = this.old_cents-c.old_cents;
		if((diff)>0) {
			list.add(new ChangedCurrencyDto("Pennies","$"+old_cents,"$"+0,"$"+Integer.toString(diff),"$"+(old_cents-Integer.valueOf(diff))));
			changeNeeded+=diff;
			currencytotal+=old_cents;
			newcurrencytotal+=old_cents;
		}
		if((diff)<=0) {
			depositedValue+=diff*-1;
			list.add(new ChangedCurrencyDto("Pennies","$"+old_cents,"$"+Integer.toString(diff*-1),"$"+0,"$"+(old_cents+Integer.valueOf(diff*-1))));
			currencytotal+=old_cents;
			newcurrencytotal+=old_cents;
		}
		diff = this.old_nickels-c.old_nickels;
		if((diff)>0) {
			list.add(new ChangedCurrencyDto("Nickels","$"+old_nickels,"$"+0,"$"+Integer.toString(diff),"$"+(old_nickels-Integer.valueOf(diff))));
			currencytotal+=old_nickels;
			newcurrencytotal+=old_nickels;
			changeNeeded+=diff;
		}
		if((diff)<=0) {
			depositedValue+=(diff*-1);
			list.add(new ChangedCurrencyDto("Nickels","$"+old_nickels,"$"+Integer.toString(diff*-1),"$"+0,"$"+(old_nickels+Integer.valueOf(diff*-1))));
			currencytotal+=old_nickels;
			newcurrencytotal+=old_nickels;

		}
		diff= this.old_dimes-c.old_dimes;
		if((diff)>0) {
			changeNeeded+=diff;
			list.add(new ChangedCurrencyDto("Dimes","$"+old_dimes,"$"+0,"$"+Integer.toString(diff),"$"+(old_dimes-Integer.valueOf(diff))));
			currencytotal+=old_dimes;
			newcurrencytotal+=old_dimes;
		}
		if((diff)<=0) {
			depositedValue+=(diff*-1);
			list.add(new ChangedCurrencyDto("Dimes","$"+old_dimes,"$"+Integer.toString(diff*-1),"$"+0,"$"+(old_dimes+Integer.valueOf(diff*-1))));
			currencytotal+=old_dimes;
			newcurrencytotal+=old_dimes;

		}
		diff = this.old_quarters -c.old_quarters;
		if((diff)>0) {
			changeNeeded+=diff;
			list.add(new ChangedCurrencyDto("Quarters","$"+old_quarters,"$"+0,"$"+Integer.toString(diff),"$"+(old_quarters-Integer.valueOf(diff))));
			currencytotal+=old_quarters;
			newcurrencytotal+=old_quarters;
		}
		if((diff)<=0) {
			depositedValue+=(diff*-1);
			list.add(new ChangedCurrencyDto("Quarters","$"+old_quarters,"$"+Integer.toString(diff*-1),"$"+0,"$"+(old_quarters+Integer.valueOf(diff*-1))));
			currencytotal+=old_quarters;
			newcurrencytotal+=old_quarters;

		}
		diff = this.old_den_1$-c.old_den_1$;
		if((diff)>0) {
			changeNeeded+=diff;
			list.add(new ChangedCurrencyDto("$1","$"+old_den_1$,"$"+0,"$"+Integer.toString(diff),"$"+(old_den_1$-Integer.valueOf(diff))));
			currencytotal+=old_den_1$;
			newcurrencytotal+=old_den_1$;
		}
		if((diff)<=0) {
			depositedValue+=(diff*-1);
			list.add(new ChangedCurrencyDto("$1","$"+old_den_1$,"$"+Integer.toString(diff*-1),"$"+0,"$"+(old_den_1$+Integer.valueOf(diff*-1))));
			currencytotal+=old_den_1$;
			newcurrencytotal+=old_den_1$;

		}
		diff = this.old_den_5$-c.old_den_5$;
		if((diff)>0) {
			changeNeeded+=diff;
			list.add(new ChangedCurrencyDto("$5","$"+old_den_5$,"$"+0,"$"+Integer.toString(diff),"$"+(old_den_5$-Integer.valueOf(diff))));
			currencytotal+=old_den_5$;
			newcurrencytotal+=old_den_5$;
		}
		if((diff)<=0) {
			depositedValue+=(diff*-1);
			list.add(new ChangedCurrencyDto("$5","$"+old_den_5$,"$"+Integer.toString(diff*-1),"$"+0,"$"+(old_den_5$+Integer.valueOf(diff*-1))));
			currencytotal+=old_den_5$;
			newcurrencytotal+=old_den_5$;

		}
		diff = this.old_den_10$-c.old_den_10$;
		if((diff)>0) {
			changeNeeded+=diff;
			list.add(new ChangedCurrencyDto("$10","$"+old_den_10$,"$"+0,"$"+Integer.toString(diff),"$"+(old_den_10$-Integer.valueOf(diff))));
			currencytotal+=old_den_10$;
			newcurrencytotal+=old_den_10$;
		}
		if((diff)<=0) {
			depositedValue+=(diff*-1);
			list.add(new ChangedCurrencyDto("$10","$"+old_den_10$,"$"+Integer.toString(diff*-1),"$"+0,"$"+(old_den_10$+Integer.valueOf(diff*-1))));
			currencytotal+=old_den_10$;
			newcurrencytotal+=old_den_10$;

		}
		diff = this.old_den_20$-c.old_den_20$;
		if((diff)>0) {
			changeNeeded+=diff;
			list.add(new ChangedCurrencyDto("$20","$"+old_den_20$,"$"+0,"$"+Integer.toString(diff),"$"+(old_den_20$-Integer.valueOf(diff))));
			currencytotal+=old_den_20$;
			newcurrencytotal+=old_den_20$;
		}
		if((diff)<=0) {
			depositedValue+=(diff*-1);
			list.add(new ChangedCurrencyDto("$20","$"+old_den_20$,"$"+Integer.toString(diff*-1),"$"+0,"$"+(old_den_20$+Integer.valueOf(diff*-1))));
			currencytotal+=old_den_20$;
			newcurrencytotal+=old_den_20$;

		}
		diff = this.old_den_50$-c.old_den_50$;
		if((diff)>0) {
			changeNeeded+=diff;
			list.add(new ChangedCurrencyDto("$50","$"+old_den_50$,"$"+0,"$"+Integer.toString(diff),"$"+(old_den_50$-Integer.valueOf(diff))));
			currencytotal+=old_den_50$;
			newcurrencytotal+=old_den_50$;
		}
		if((diff)<=0) {
			depositedValue+=(diff*-1);
			list.add(new ChangedCurrencyDto("$50","$"+old_den_50$,"$"+Integer.toString(diff*-1),"$"+0,"$"+(old_den_50$+Integer.valueOf(diff*-1))));
			currencytotal+=old_den_50$;
			newcurrencytotal+=old_den_50$;

		}
		diff = this.old_den_100$-c.old_den_100$;
		if((diff)>0) {
			changeNeeded+=diff;
			list.add(new ChangedCurrencyDto("$100","$"+old_den_100$,"$"+0,"$"+Integer.toString(diff),"$"+(old_den_100$-Integer.valueOf(diff))));
			currencytotal+=old_den_100$;
			newcurrencytotal+=old_den_100$;
		}
		if((diff)<=0) {
			depositedValue+=(diff*-1);
			list.add(new ChangedCurrencyDto("$100","$"+old_den_100$,"$"+Integer.toString(diff*-1),"$"+0,"$"+(old_den_100$+Integer.valueOf(diff*-1))));
			currencytotal+=old_den_100$;
			newcurrencytotal+=old_den_100$;

		}
		
		list.add(new ChangedCurrencyDto("Total" ,"$"+Integer.toString(currencytotal),"$"+Integer.toString(depositedValue),"$"+Integer.toString(changeNeeded),"$"+Integer.toString(newcurrencytotal)));
		 
		return list;
	}

		public List<ChangedCurrencyDto> compareCurrentBal(ValetDenominations c){
			
			List<ChangedCurrencyDto> list = new ArrayList<ChangedCurrencyDto>();
			int changeNeeded = 0;
			int depositedValue = 0;
			
//			int diff = this.old_cents-c.getCents();
//			if((diff)>0) {
//				list.add(new ChangedCurrencyDto("Pennies","$"+Integer.toString(diff),"0"));
//				changeNeeded+=diff;
//			}
//			if((diff)<0) {
//				depositedValue+=(diff*-1);
//				list.add(new ChangedCurrencyDto("Pennies","0","$"+Integer.toString(diff*-1)));
//			}
//			diff = this.old_nickels-c.getNickels();
//			if((diff)>0) {
//				list.add(new ChangedCurrencyDto("Nickels","$"+Integer.toString(diff),"0"));
//				changeNeeded+=diff;
//			}
//			if((diff)<0) {
//				depositedValue+=(diff*-1);
//				list.add(new ChangedCurrencyDto("Nickels","0","$"+Integer.toString(diff*-1)));
//			}
//			diff= this.old_dimes-c.getDimes();
//			if((diff)>0) {
//				changeNeeded+=diff;
//				list.add(new ChangedCurrencyDto("Dimes","$"+Integer.toString(diff),"0"));
//			}
//			if((diff)<0) {
//				depositedValue+=(diff*-1);
//				list.add(new ChangedCurrencyDto("Dimes","0","$"+Integer.toString(diff*-1)));
//			}
//			diff = this.old_quarters -c.getQuarters();
//			if((diff)>0) {
//				changeNeeded+=diff;
//				list.add(new ChangedCurrencyDto("Quarters","$"+Integer.toString(diff),"0"));
//			}
//			if((diff)<0) {
//				depositedValue+=(diff*-1);
//				list.add(new ChangedCurrencyDto("Quarters","0","$"+Integer.toString(diff*-1)));
//			}
//			diff = this.old_den_1$-c.getDen_1$();
//			if((diff)>0) {
//				changeNeeded+=diff;
//				list.add(new ChangedCurrencyDto("$1","$"+Integer.toString(diff),"0"));
//			}
//			if((diff)<0) {
//				depositedValue+=(diff*-1);
//				list.add(new ChangedCurrencyDto("$1","0","$"+Integer.toString(diff*-1)));
//			}
//			diff = this.old_den_5$-c.getDen_5$();
//			if((diff)>0) {
//				changeNeeded+=diff;
//				list.add(new ChangedCurrencyDto("$5","$"+Integer.toString(diff),"0"));
//			}
//			if((diff)<0) { 
//				depositedValue+=(diff*-1);
//				list.add(new ChangedCurrencyDto("$5","0","$"+Integer.toString(diff*-1)));
//			}
//			diff = this.old_den_10$-c.getDen_10$();
//			if((diff)>0) {
//				changeNeeded+=diff;
//				list.add(new ChangedCurrencyDto("$10","$"+Integer.toString(diff),"0"));
//			}
//			if((diff)<0) {
//				depositedValue+=(diff*-1);
//				list.add(new ChangedCurrencyDto("$10","0","$"+Integer.toString(diff*-1)));
//			}
//			diff = this.old_den_20$-c.getDen_20$();
//			if((diff)>0) {
//				changeNeeded+=diff;
//				list.add(new ChangedCurrencyDto("$20","$"+Integer.toString(diff),"0"));
//			}
//			if((diff)<0) {
//				depositedValue+=(diff*-1);
//				list.add(new ChangedCurrencyDto("$20","0","$"+Integer.toString(diff*-1)));
//			}
//			diff = this.old_den_50$-c.getDen_50$();
//			if((diff)>0) {
//				changeNeeded+=diff;
//				list.add(new ChangedCurrencyDto("$50","$"+Integer.toString(diff),"0"));
//			}
//			if((diff)<0) {
//				changeNeeded+=(diff*-1);
//				list.add(new ChangedCurrencyDto("$50","$"+Integer.toString(diff*-1),"0"));
//			}
//			diff = this.old_den_100$-c.getDen_100$();
//			if((diff)>0) {
//				changeNeeded+=diff;
//				list.add(new ChangedCurrencyDto("$100","$"+Integer.toString(diff),"0"));
//			}
//			if((diff)<0) {
//				depositedValue+=(diff*-1);
//				list.add(new ChangedCurrencyDto("$100","0","$"+Integer.toString(diff*-1)));
//			}
//			
//			list.add(new ChangedCurrencyDto("ALL" ,"$"+Integer.toString(changeNeeded),"$"+Integer.toString(depositedValue)));
//			
			return list;
		}

	public String getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getOld_cents() {
		return old_cents;
	}

	public void setOld_cents(Integer old_cents) {
		this.old_cents = old_cents;
	}

	public Integer getNew_cents() {
		return new_cents;
	}

	public void setNew_cents(Integer new_cents) {
		this.new_cents = new_cents;
	}

	public Integer getOld_nickels() {
		return old_nickels;
	}

	public void setOld_nickels(Integer old_nickels) {
		this.old_nickels = old_nickels;
	}

	public Integer getNew_nickels() {
		return new_nickels;
	}

	public void setNew_nickels(Integer new_nickels) {
		this.new_nickels = new_nickels;
	}

	public Integer getOld_dimes() {
		return old_dimes;
	}

	public void setOld_dimes(Integer old_dimes) {
		this.old_dimes = old_dimes;
	}

	public Integer getNew_dimes() {
		return new_dimes;
	}

	public void setNew_dimes(Integer new_dimes) {
		this.new_dimes = new_dimes;
	}

	public Integer getOld_quarters() {
		return old_quarters;
	}

	public void setOld_quarters(Integer old_quarters) {
		this.old_quarters = old_quarters;
	}

	public Integer getNew_quarters() {
		return new_quarters;
	}

	public void setNew_quarters(Integer new_quarters) {
		this.new_quarters = new_quarters;
	}

	public Integer getOld_den_1$() {
		return old_den_1$;
	}

	public void setOld_den_1$(Integer old_den_1$) {
		this.old_den_1$ = old_den_1$;
	}

	public Integer getNew_den_1$() {
		return new_den_1$;
	}

	public void setNew_den_1$(Integer new_den_1$) {
		this.new_den_1$ = new_den_1$;
	}

	public Integer getOld_den_5$() {
		return old_den_5$;
	}

	public void setOld_den_5$(Integer old_den_5$) {
		this.old_den_5$ = old_den_5$;
	}

	public Integer getNew_den_5$() {
		return new_den_5$;
	}

	public void setNew_den_5$(Integer new_den_5$) {
		this.new_den_5$ = new_den_5$;
	}

	public Integer getOld_den_10$() {
		return old_den_10$;
	}

	public void setOld_den_10$(Integer old_den_10$) {
		this.old_den_10$ = old_den_10$;
	}

	public Integer getNew_den_10$() {
		return new_den_10$;
	}

	public void setNew_den_10$(Integer new_den_10$) {
		this.new_den_10$ = new_den_10$;
	}

	public Integer getOld_den_20$() {
		return old_den_20$;
	}

	public void setOld_den_20$(Integer old_den_20$) {
		this.old_den_20$ = old_den_20$;
	}

	public Integer getNew_den_20$() {
		return new_den_20$;
	}

	public void setNew_den_20$(Integer new_den_20$) {
		this.new_den_20$ = new_den_20$;
	}

	public Integer getOld_den_50$() {
		return old_den_50$;
	}

	public void setOld_den_50$(Integer old_den_50$) {
		this.old_den_50$ = old_den_50$;
	}

	public Integer getNew_den_50$() {
		return new_den_50$;
	}

	public void setNew_den_50$(Integer new_den_50$) {
		this.new_den_50$ = new_den_50$;
	}

	public Integer getOld_den_100$() {
		return old_den_100$;
	}

	public void setOld_den_100$(Integer old_den_100$) {
		this.old_den_100$ = old_den_100$;
	}

	public Integer getNew_den_100$() {
		return new_den_100$;
	}

	public void setNew_den_100$(Integer new_den_100$) {
		this.new_den_100$ = new_den_100$;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getUpdatedByUser() {
		return updatedByUser;
	}

	public void setUpdatedByUser(Long updatedByUser) {
		this.updatedByUser = updatedByUser;
	}

	public Long getValetDenominationsId() {
		return valetDenominationsId;
	}

	public void setValetDenominationsId(Long valetDenominationsId) {
		this.valetDenominationsId = valetDenominationsId;
	}

	public ValetDenominationsDto getValetDenominationsDto() {
		return valetDenominationsDto;
	}

	public void setValetDenominationsDto(ValetDenominationsDto valetDenominationsDto) {
		this.valetDenominationsDto = valetDenominationsDto;
	}

	public Long getChangeRequestId() {
		return changeRequestId;
	}

	public void setChangeRequestId(Long changeRequestId) {
		this.changeRequestId = changeRequestId;
	}

	public ChangeRequestDto getChangeRequestDto() {
		return changeRequestDto;
	}

	public void setChangeRequestDto(ChangeRequestDto changeRequestDto) {
		this.changeRequestDto = changeRequestDto;
	}

	
	
}
