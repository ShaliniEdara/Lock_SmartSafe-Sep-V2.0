package com.safesmart.safesmart.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.safesmart.safesmart.dto.EODReport;
import com.safesmart.safesmart.dto.StoreInfoResponse;
import com.safesmart.safesmart.model.BillValidator;
import com.safesmart.safesmart.model.ChangeRequest;
import com.safesmart.safesmart.model.InsertBill;
import com.safesmart.safesmart.model.Kiosk;
import com.safesmart.safesmart.model.Locks;
import com.safesmart.safesmart.model.Printer;
import com.safesmart.safesmart.model.Role;
import com.safesmart.safesmart.model.UserInfo;
import com.safesmart.safesmart.model.ValetDenominations;
import com.safesmart.safesmart.repository.ChangeRequestRepository;
import com.safesmart.safesmart.repository.ChangeRquestDenominationsRepository;
import com.safesmart.safesmart.repository.InsertBillRepository;
import com.safesmart.safesmart.repository.SequenceInfoRepository;
import com.safesmart.safesmart.repository.UserInfoRepository;
import com.safesmart.safesmart.repository.ValetDenominationsRepository;
import com.safesmart.safesmart.util.EmailTemplate;

@Service
@Transactional
public class DashBoardService {
	
	@Autowired
	private StoreInfoService storeInfoService;

	@Autowired
	private SequenceInfoRepository sequenceInfoRepository;

	@Autowired
	private InsertBillRepository insertBillRepository;

	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@Autowired
	ValetDenominationsRepository valetDenominationsRepository;

	@Autowired
	private EmailTemplate emailTemplate;
	
	@Autowired
	private ChangeRquestDenominationsRepository changeRequestDenominationsRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PrinterService printerService;
	
	@Autowired
	private LocksService locksService;
	
	@Autowired
	private KioskService kioskService;
	
	@Autowired
	private BillValidatorService billValidatorService;
	
	@Autowired
	private ChangeRequestRepository changeRequestRepo;

	public List<EODReport> getEodReports(String storeName, boolean toDay) {
		List<EODReport> eodReports=new ArrayList<>();
		//EODReport eod=new EODReport();
		   StoreInfoResponse storeInfoResponse = storeInfoService.getStoreInfoService(storeName);
			List<Long> userIds = storeInfoResponse.getUserIds();
			//Set<String> userNames=new HashSet();
			//List<Integer> userTotallAmount =new ArrayList();
			//int sum = 0;
			String stTime = storeInfoResponse.getStartTime();
			String endTimes = storeInfoResponse.getEndTime();
			LocalTime startTime = LocalTime.parse(stTime);
			LocalTime endTime = LocalTime.parse(endTimes);
			LocalTime now = LocalTime.now();
			LocalDateTime startDateTime = null;
			LocalDateTime endDateTime = null;
			LocalDate startDate = null;
			LocalDate endDate = null;
			// if check box Today is not checked then endDateTime will be assigned as certain conditions based on current time and Store default 
			// Open time and Close time 
			if(!toDay){
				
				/* now.compareTo(endTime) returns positive if now is greater than end time 
				 returns zero if equal 
				returns negative if now is less than end time */
				int diff = now.compareTo(endTime); 
				
				/*Logic for if report is generated after end time that current date report will be generated if 10:00 AM  is end time and report is generated at 10:01 AM then report will be from yesterday 10:00Am to todat 10 AM
				if report is generated at 09:59 AM then report will be from day before yesterday 10:00 AM to yesterday 10:00 AM */
				
				 endDate = (diff>0)|| (diff==0)?LocalDate.now():LocalDate.now().minusDays(1);
				
					endDateTime = endTime.atDate(endDate);
				}else {
					 	endDate = LocalDate.now();
						endDateTime = LocalTime.now().atDate(endDate);
				}
				//Checking = difference between start time and end time of a Store
						long hours = ChronoUnit.HOURS.between(startTime, endTime);
						long minutes
				        = ChronoUnit.MINUTES.between(startTime, endTime) % 60;
						long seconds
			            = ChronoUnit.SECONDS.between(startTime, endTime) % 60;
					// To determine the start date is before day or same day based on the store start time and end time
							if(hours<0 || minutes <0 || seconds <0 || (hours ==0 && minutes ==0 && seconds ==0)) {
							 startDate = endDate.minusDays(1);
							}else {
								startDate = endDate;
							}
							
							startDateTime = startTime.atDate(startDate);
				            System.out.println("----------The Assigin users id are--------------"+userIds);
				            System.out.println(" The "+storeName+"start Date is " + startDate + "and  End date is " + endDate);
				            System.out.println("The Assign users are between Start date and End date of "+storeName);
				 int grandTotal =0;
			     int grandCount =0;
				for(Long userId : userIds) {
					EODReport eod=new EODReport();
				      List<InsertBill> insertBills = insertBillRepository.findByUser_IdAndDateTimeBetween(userId, startDateTime, endDateTime);
				      if(!insertBills.isEmpty()) {
				    	  //System.out.println("-----------------we are in if block -------------------");
				    	  
				    	  UserInfo user = userInfoRepository.findById(userId).get();
				    	  //System.out.println("--------------Users informations are----------------"+user);
				    	  String username=user.getUsername();
				    	  System.out.println("--------the username is-----"+ username);
				    	  eod.setUserName(username);
				    	  Set<String> distinctDenominations =  new HashSet<String>();
				    	   for(InsertBill bill : insertBills) {
			    		   distinctDenominations.add(bill.getAmount());
			    		   //System.out.println("------we are in for loop ending stage---------");
				    		   }
				    	   System.out.println("the "+username+"Denaminations are"+distinctDenominations);
				    	  int totalCount =0;
				    	  int sum = 0;
				    	  //Adding Distinct denominations in to a set like $1,$2,...
				    	   for(String a : distinctDenominations) {
			    		      int count = 0;
				    		   int product = 0;
				    		   //checking number of notes of same denomination present in the current date bill
				    		   for(InsertBill bill : insertBills) {
				    		     //System.out.println("--------we are in  find  the count for loop-----------");
				    			  if(a.equals(bill.getAmount())) {
				    				  count++;
				    				  }
				    			  }
				    		   System.out.println(count);
				    		   product = a.equals("$1")?1*count:a.equals("$2")?2*count:a.equals("$5")?5*count:a.equals("$10")?10*count:a.equals("$20")?20*count:
				    			   a.equals("$50")?50*count:a.equals("$100")?100*count:1*count;  
				    		   totalCount+= count;
				    		   sum+=product;
				    		   System.out.println("The "+username +"TotalAmount is "+sum);
			    		       eod.setTotalValue(sum);
					    	   //eod.setTotalCount(totalCount);
			    		      }
				    	   grandTotal+=sum;
				    	   eod.setGrandTotal(grandTotal);
				    	   
				    	   System.out.println("Total inserted bills of "+storeName+"is "+grandTotal);;
				    	   eodReports.add(eod);
				      }//if black ending
				    
				      
				}//first for loop ending
				
		return eodReports;
	}

	public StoreInfoResponse getAllStoreInfo(String storeName) {
		StoreInfoResponse storeInfoResponse=storeInfoService.findByStoreName(storeName);
		Long storeid=storeInfoResponse.getId();
		List<Long> pinteridList=storeInfoResponse.getPrinterIds();
		List<Long> kioskidList=storeInfoResponse.getKioskIds();
		List<Long> billList=storeInfoResponse.getBillValidatorIds();
		List<Long> lockList=storeInfoResponse.getLockIds();
		List<Long> userid=storeInfoResponse.getUserIds();
		UserInfo userInfo=null;
		Printer printerinfo=null;
		Kiosk kioskinfo=null;
		BillValidator billValidatorinfo=null;
		Locks lockinfo=new Locks();
		int usercount=0;
		int printerscount=0;
	    int kioskcount=0;
	    int billcount=0;
	    int lockcount=0;
	    int adminscount=0;
	    int employeescount=0;
	    int managerscount=0;
	    int shiftmanagerscount=0;
	    int truckscount=0;
	    
		System.out.println(userid);
		//count for user(totalusers)
		for (Long userids : userid) {
	       userInfo =userService.getUserInfo(userids);
	       usercount++;
	       }
	 storeInfoResponse.setTotalUsers(usercount);
		
		//count for printer(TotalPrinters)
		for (Long printerid : pinteridList) {
			 printerinfo = printerService.getPrinterInfo(printerid);
	         printerscount++;
	}
	 storeInfoResponse.setTotalPrinters(printerscount);
	 
	 //TotalKiosks
	 for(Long Kioskid :kioskidList) {
		 kioskinfo=kioskService.getKioskInfo(Kioskid);
		 kioskcount++;
	 }
	 storeInfoResponse.setTotalKiosk(kioskcount);
	 
	 //Total billvalidators count
	 for(Long billid : billList) {
		 billValidatorinfo=billValidatorService.getBillValidatorInfo(billid);
				 billcount++;
	 }
		storeInfoResponse.setTotalBillVallidator(billcount);
		
	//Total Locks count	
		for(Long locksid:lockList) {
			lockinfo=locksService.getLocksInfo(locksid);
			lockcount++;
		}
		storeInfoResponse.setTotalLocks(lockcount);
		
	//get the roleName count
	for (Long userids : userid) {
		 userInfo =userService.getUserInfo(userids);
		
		Role roleinfo=userInfo.getRole();
		System.out.println("the role name is"+roleinfo.getName());
		System.out.println("the role id is"+roleinfo.getId());
//		System.out.println(userService.getAllRolesCount(storeid));
//		RoleConunt roleConunt=userService.getAllRolesCount(storeid);
//		System.out.println(roleConunt);
		if(roleinfo.getName().equalsIgnoreCase("ADMIN")) {
			adminscount++;
		}else if (roleinfo.getName().equalsIgnoreCase("EMPLOYEE")) {
			employeescount++;
			
		}else if (roleinfo.getName().equalsIgnoreCase("MANAGER")) {
			managerscount++;
		}else if (roleinfo.getName().equalsIgnoreCase("SHIFTMANAGER")) {
			shiftmanagerscount++;
		}else if (roleinfo.getName().equalsIgnoreCase("TRUCK")) {
			truckscount++;
		}else {
			System.out.println("error");
		}
		storeInfoResponse.setTotalAdmins(adminscount);
		storeInfoResponse.setTotalManagers(managerscount);
		storeInfoResponse.setTotalEmployess(employeescount);
		storeInfoResponse.setTotalShiftManagers(shiftmanagerscount);
		storeInfoResponse.setTotalTruckers(truckscount); 
		 }
		
	
		return  storeInfoResponse;


	
	}

	public int getTotalStateBankBalance(String storeName, boolean toDay) {
		StoreInfoResponse storeInfoResponse=storeInfoService.getStoreInfoService(storeName);
		   List<Long> userIds = storeInfoResponse.getUserIds();
		   System.out.println("----The userids are----"+userIds);
		   UserInfo user;
		   List<ValetDenominations> list = new ArrayList<>();
		   List<ValetDenominations> shiftManagerList=new ArrayList<>();
		   int mainSafeBalance = 0;
		   int shiftMangerBalnce = 0;
		   int standBankBalance;
		   for (Long userid : userIds) {
			   user = userInfoRepository.findById(userid).get();
			   //MainSafe balance
			   try {
			ValetDenominations valetDenominations=valetDenominationsRepository.findByCreatedByAndType( user, "MAINSAFE");
			list.add(valetDenominations);
			Long mainsafelist=valetDenominations.getCreatedBy().getId();
			 System.out.println("--------StandBank of MainSafe Information---------"+mainsafelist );
			 
			 for (ValetDenominations valetDenominations2 : list) {
				 
				 mainSafeBalance=valetDenominations2.getDen_1$()+valetDenominations2.getDen_10$()+valetDenominations2.getDen_5$()+valetDenominations2.getDen_20$()+
						valetDenominations2.getDen_50$()+valetDenominations2.getDen_100$()+valetDenominations2.getDimes()+
						valetDenominations2.getNickels()+valetDenominations2.getQuarters()+valetDenominations2.getCents();
					System.out.println("  MainSafe Total balance  ="+mainSafeBalance);
			}
			   }catch (Exception e) {
				System.out.println("their is no records"+e);
			}
			
			 
		   
			 //Shiftmanager balance
			   try {
			 ValetDenominations shiftManagervaletDenominations=valetDenominationsRepository.findByCreatedByAndType( user, "SHIFTMANAGER");
			 shiftManagerList.add(shiftManagervaletDenominations);
           Long shiftManagersIds=shiftManagervaletDenominations.getCreatedBy().getId();
           System.out.println("--------StandBank of ShiftManger Information---------"+shiftManagersIds );
          // if(shiftManagervaletDenominations!=null) {
          
           for (ValetDenominations valetDenominations2 : shiftManagerList) {
				 
         	  shiftMangerBalnce=valetDenominations2.getDen_1$()+valetDenominations2.getDen_10$()+valetDenominations2.getDen_5$()+valetDenominations2.getDen_20$()+
						valetDenominations2.getDen_50$()+valetDenominations2.getDen_100$()+valetDenominations2.getDimes()+
						valetDenominations2.getNickels()+valetDenominations2.getQuarters()+valetDenominations2.getCents();
					System.out.println("----ShiftManager Total Balance--- ="+shiftMangerBalnce);
			}
			   }catch (Exception e) {
				System.out.println("their is no record"+e);
			}
			 
		}
		   standBankBalance=mainSafeBalance+shiftMangerBalnce;
			return standBankBalance;
		}
	
	//MainSafe Balance Service method for Dashboard
	
		public int getTotalMainSafeBalance(String storeName, boolean toDay) {
			StoreInfoResponse storeInfoResponse=storeInfoService.getStoreInfoService(storeName);
			   List<Long> userIds = storeInfoResponse.getUserIds();
			   System.out.println("----The userids are----"+userIds);
			   UserInfo user;
			   List<ValetDenominations> list = new ArrayList<>();
			   List<ValetDenominations> shiftManagerList=new ArrayList<>();
			   int mainSafeBalance = 0;
			   int shiftMangerBalnce = 0;
			   int standBankBalance;
			   for (Long userid : userIds) {
				   user = userInfoRepository.findById(userid).get();
				   //MainSafe balance
				   try {
				ValetDenominations valetDenominations=valetDenominationsRepository.findByCreatedByAndType( user, "MAINSAFE");
				list.add(valetDenominations);
				Long mainsafelist=valetDenominations.getCreatedBy().getId();
				 System.out.println("--------StandBank of MainSafe Information---------"+mainsafelist );
				 
				 for (ValetDenominations valetDenominations2 : list) {
					 
					 mainSafeBalance=valetDenominations2.getDen_1$()+valetDenominations2.getDen_10$()+valetDenominations2.getDen_5$()+valetDenominations2.getDen_20$()+
							valetDenominations2.getDen_50$()+valetDenominations2.getDen_100$()+valetDenominations2.getDimes()+
							valetDenominations2.getNickels()+valetDenominations2.getQuarters()+valetDenominations2.getCents();
						System.out.println("  MainSafe Total balance  ="+mainSafeBalance);
				}
				   }catch (Exception e) {
					System.out.println("their is no records"+e);
				}
				   
			   }
			   return mainSafeBalance;
		}
		
		//ShiftManager Balance  Service method for dashboard
		public int getTotalShiftMangerBalance(String storeName, boolean toDay) {
			StoreInfoResponse storeInfoResponse=storeInfoService.getStoreInfoService(storeName);
			   List<Long> userIds = storeInfoResponse.getUserIds();
			   System.out.println("----The userids are----"+userIds);
			   UserInfo user;
			   List<ValetDenominations> list = new ArrayList<>();
			   List<ValetDenominations> shiftManagerList=new ArrayList<>();
			   int mainSafeBalance = 0;
			   int shiftMangerBalnce = 0;
			   int standBankBalance;
			   for (Long userid : userIds) {
				   user = userInfoRepository.findById(userid).get();
				 //Shiftmanager balance
				   try {
				 ValetDenominations shiftManagervaletDenominations=valetDenominationsRepository.findByCreatedByAndType( user, "SHIFTMANAGER");
				 shiftManagerList.add(shiftManagervaletDenominations);
	           Long shiftManagersIds=shiftManagervaletDenominations.getCreatedBy().getId();
	           System.out.println("--------StandBank of ShiftManger Information---------"+shiftManagersIds );
	          // if(shiftManagervaletDenominations!=null) {
	          
	           for (ValetDenominations valetDenominations2 : shiftManagerList) {
					 
	         	  shiftMangerBalnce=valetDenominations2.getDen_1$()+valetDenominations2.getDen_10$()+valetDenominations2.getDen_5$()+valetDenominations2.getDen_20$()+
							valetDenominations2.getDen_50$()+valetDenominations2.getDen_100$()+valetDenominations2.getDimes()+
							valetDenominations2.getNickels()+valetDenominations2.getQuarters()+valetDenominations2.getCents();
						System.out.println("----ShiftManager Total Balance--- ="+shiftMangerBalnce);
				}
				   }catch (Exception e) {
					System.out.println("their is no record"+e);
				}
				 
			}
			   return shiftMangerBalnce;
		}

	
	
//ChangeRequest Balance service methode for dashboard
	public int getChageRequestBalance(String storeName) {
		StoreInfoResponse storeInfoResponse=storeInfoService.getStoreInfoService(storeName);
		   List<Long> userIds = storeInfoResponse.getUserIds();
//		   Date date = new Date();
//		    String strDateFormat = "hh:mm:ss a";
//		    DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
//		    String formattedDate= dateFormat.format(date);
//		    System.out.println("Current time of the day using Date - 12 hour format: " + formattedDate);
		    int changeRequestBalance=0;
		    UserInfo user;
		    for (Long userid : userIds) {
		    	try {
         	user = userInfoRepository.findById(userid).get();
         	 ChangeRequest changeRequest=changeRequestRepo.findBycreatedByAndOrderStatus(user,"Ordered");
         	 changeRequestBalance=changeRequest.getCents()+changeRequest.getDen_1$()+changeRequest.getDen_10$()+changeRequest.getDen_5$()+
         			 changeRequest.getDen_20$()+changeRequest.getDen_50$()+changeRequest.getDen_100$()+changeRequest.getNickels()+
         			 changeRequest.getQuarters();
         	 //System.out.println("ChangeRequest details"+changeRequest);
			}catch (Exception e) {
				System.out.println("Their is no recors"+e);
			}
	
		    }
		  
		return changeRequestBalance;
	}
		
	
}


		
	

