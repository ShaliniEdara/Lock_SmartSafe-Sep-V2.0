package com.safesmart.safesmart.controller;

import java.awt.print.Pageable;
import java.io.IOException;
//import java.lang.WeakPairMap.Pair.Weak;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.management.relation.RoleInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.safesmart.safesmart.dto.LocksResponse;
import com.safesmart.safesmart.dto.RoleConunt;
import com.safesmart.safesmart.dto.StoreInfoRequest;
import com.safesmart.safesmart.dto.StoreInfoResponse;
import com.safesmart.safesmart.dto.UserInfoRequest;
import com.safesmart.safesmart.model.BillValidator;
import com.safesmart.safesmart.model.Kiosk;
import com.safesmart.safesmart.model.Locks;
import com.safesmart.safesmart.model.Printer;
import com.safesmart.safesmart.model.Role;
import com.safesmart.safesmart.model.StoreInfo;
import com.safesmart.safesmart.model.UserInfo;
import com.safesmart.safesmart.repository.BillValidatorRepository;
import com.safesmart.safesmart.repository.KioskRepository;
import com.safesmart.safesmart.repository.LocksRepository;
import com.safesmart.safesmart.repository.PrinterRepository;
import com.safesmart.safesmart.repository.StoreInfoRepository;
import com.safesmart.safesmart.repository.UserInfoRepository;
import com.safesmart.safesmart.service.BillValidatorService;
import com.safesmart.safesmart.service.KioskService;
import com.safesmart.safesmart.service.LocksService;
import com.safesmart.safesmart.service.PrinterService;
import com.safesmart.safesmart.service.StoreInfoService;
import com.safesmart.safesmart.service.UserService;

@RestController
@RequestMapping(value = "/storeinfo")
@CrossOrigin
public class StoreInfoController {
	

	@Autowired
	private StoreInfoService storeInfoService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PrinterService printerService;
	
	@Autowired
	private KioskService kioskService;
	
	@Autowired
	private KioskRepository kioskRepository;
	
	@Autowired
	private BillValidatorRepository billValidatorRepository;
	
	@Autowired
	private PrinterRepository printerRepository;
	
	@Autowired
	private LocksRepository locksRepository;

	
	@Autowired
	private BillValidatorService billValidatorService;
	
	@Autowired
	private LocksService locksService;
	
	@Autowired
	private UserInfoRepository userInfoRepository;

	//private Object ;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public StoreInfoResponse findStoreInfo() {
		return storeInfoService.getStoreInfoService();
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void addStore(@RequestBody StoreInfoRequest storeInfoRequest) {

		storeInfoService.addStore(storeInfoRequest);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<StoreInfoResponse> findAll() {
		return storeInfoService.findAll();
	}

	@RequestMapping(value = "/{storeName}", method = RequestMethod.GET)
	public StoreInfoResponse findByStoreName(@PathVariable("storeName") String storeName) {
		return storeInfoService.findByStoreName(storeName);
	}

	@RequestMapping(value = "/{storeId}", method = RequestMethod.PUT)
	public void updateStoreInfo(@PathVariable("storeId") Long storeId, @RequestBody StoreInfoRequest infoRequest) {
		infoRequest.setId(storeId);
		storeInfoService.updateStoreInfo(infoRequest);

	}
	
	@RequestMapping(value = "/{Id}", method = RequestMethod.DELETE)
	public void deleteByStoreInfo(@PathVariable("Id") Long Id) {
		storeInfoService.deleteByStoreInfo(Id);
	}

	@RequestMapping(value = "/configure", method = RequestMethod.POST)
	public void configureStore(@RequestBody StoreInfoRequest storeInfoRequest) {

		storeInfoService.configureStore(storeInfoRequest);
	}
	
	@RequestMapping(value = "/assign/store/{storeId}/user/{userId}", method = RequestMethod.POST)
	public void assignStore(@PathVariable(value = "storeId") Long storeId,
			@PathVariable(value = "userId") Long userId) {

		storeInfoService.assignStore(storeId, userId);
	}
	
	@RequestMapping(value = "/assign/store/{storeId}/kiosk/{kId}/billValidator/{bId}/printer/{pId}/locks/{lId}", method = RequestMethod.POST)
	public void assignStoreKBPL(@PathVariable(value = "storeId") Long storeId,
			@PathVariable(value = "kId") Long kId ,
			@PathVariable(value = "bId") List<Long> bId ,
			@PathVariable(value = "pId") Long pId ,
	         @PathVariable(value = "lId") List<Long> lId) {

		storeInfoService.assignStoreKBPL(storeId, kId, bId, pId, lId);
	}

	@RequestMapping(value = "/all/unassigned", method = RequestMethod.GET)
	public List<StoreInfoResponse> findUnassignedStores() {
		return storeInfoService.findUnassignedStores();
	}
	
	@RequestMapping(value = "/all/unassignedusers", method = RequestMethod.GET)
	public List<StoreInfoResponse> findUnassignedStoresforUser() {
		return storeInfoService.findUnassignedStoresforUser();
	}
	
	@RequestMapping(value = "/all/unassignedKBPL", method = RequestMethod.GET)
	public List<StoreInfoResponse> findUnassignedStoresforKBPL() {
		return storeInfoService.findUnassignedStoresforKBPL();
	}
	
	@RequestMapping(value = "/all/assigned", method = RequestMethod.GET)
	public List<StoreInfoResponse> findAssignedStores() {
		return storeInfoService.findAssignedStores();
	}
	
	@RequestMapping(value = "/all/assignedstoresforreport", method = RequestMethod.GET)
	public List<StoreInfoResponse> findAssignedStoresforreport() {
		return storeInfoService.findAssignedStoresforreport();
	}
	
	//get all assign stores based on status field
	@RequestMapping(value = "/all/assignedStores", method = RequestMethod.GET)
	public List<StoreInfoResponse> findAssignedStoresKBPL() {
		return storeInfoService.findAssignedStoresKBPL();
	}
	
	//get all storeinformation with assign names of kiosks etc
		@RequestMapping(value="/kiosknames",method=RequestMethod.GET)
		public List<StoreInfoResponse> findNames() {
			List<StoreInfoResponse> storeInfoResponses=new ArrayList<>();
			
			List<StoreInfoResponse> storeinfo=storeInfoService.findAssignedStoresKBPL();
			String kioskname = "";
			String billvalidatorname="";
			String printername="";
			String lockname="";
			 for (StoreInfoResponse storeInfoResponse : storeinfo) {
				
				//System.out.println("-----kiosk ids are-------"+storeInfoResponse.getKioskIds());
				//get the kiosk information
			List<Long> kioskids	=storeInfoResponse.getKioskIds();
			for (Long id : kioskids) {
				//System.out.println(kioskids);
				Kiosk kiosk = new Kiosk();
			Optional<Kiosk> kioskinfo =	kioskRepository.findById(id);
			if(kioskinfo.isPresent()){
				kiosk=kioskinfo.get();
			}
			kioskname=kiosk.getKioskName();
			storeInfoResponse.setKioskName(kioskname);
			}
			
			//get billvalidator information
					List<Long> billvalidatorids = storeInfoResponse.getBillValidatorIds();
					for (Long id1 : billvalidatorids) {
						BillValidator billinfo = new BillValidator();
						Optional<BillValidator> bill = billValidatorRepository.findById(id1);
						if(bill.isPresent()) {
							billinfo = bill.get();
						}
						billvalidatorname=billinfo.getBillAcceptorName();
						storeInfoResponse.setBillvallidatorName(billvalidatorname);
					}
					
			//get printer information 
					List<Long> printerids = storeInfoResponse.getPrinterIds();
					for(Long id2 :printerids ) {
						Printer printer=new Printer();	
					Optional<Printer>	printerinfo =printerRepository.findById(id2);
					if(printerinfo.isPresent()) {
						printer=printerinfo.get();
					}
					printername=printer.getPrinterName();
					storeInfoResponse.setPrinterName(printername);
						
						}
			//get Lock information
					List<Long> lockids=storeInfoResponse.getLockIds();
					for (Long id3 : lockids) {
						Locks lock =new Locks();
					Optional<Locks> lockinfo=locksRepository.findById(id3);
					if(lockinfo.isPresent()) {
						lock=lockinfo.get();
						}
					lockname=lock.getDigitalLockName();
					storeInfoResponse.setLockName(lockname);
			 }
	                		
			
			
			storeInfoResponses.add(storeInfoResponse);
			
			 }
			
			 
	     return  storeInfoResponses;
		}

		//get all storeinformation with assign store names for user etc
				@RequestMapping(value="/usernames",method=RequestMethod.GET)
				public List<StoreInfoResponse> findUserNames() {
					List<StoreInfoResponse> storeInfoResponses=new ArrayList<>();
					
					List<StoreInfoResponse> storeinfo=storeInfoService.findAssignedStoresforreport();
					String username = "";
					 for (StoreInfoResponse storeInfoResponse : storeinfo) {
						
					List<Long> userIds	=storeInfoResponse.getUserIds();
					for (Long id : userIds) {
						UserInfo user = new UserInfo();
					Optional<UserInfo> userkinfo =	userInfoRepository.findById(id);
					if(userkinfo.isPresent()){
						user=userkinfo.get();
					}
					username=user.getUsername();
					storeInfoResponse.setUserrName(username);
					}
			                		
					
					
					storeInfoResponses.add(storeInfoResponse);
					
					 }
									 
			     return  storeInfoResponses;
		}

		
		
	//get all storesnames without assign the users
	@RequestMapping(value = "/all/assignedStoresunassignusers", method = RequestMethod.GET)
	public List<StoreInfoResponse> findAssignedStoresUnassignedUsers() {
		return storeInfoService.findAssignedStoresUnassignedUsers();
	}
	
	
	
	//Find Store to User
	@RequestMapping(value = "/all/assigneduser/{id}", method = RequestMethod.GET)
	public List<UserInfo> findByStoreInfoId(@PathVariable  Long id) {
		List<UserInfo> userList=userService.findByStoreInfo_Id(id);
		List<UserInfo> empUsersList=new ArrayList<>();
		
			
		
		return  userService.findByStoreInfo_Id(id);
	}
	
	//assigned stores
	@RequestMapping(value = "/assignedstores", method = RequestMethod.GET)
	public List<String> assignedStores() {
		List<StoreInfoResponse> storeInfoResponse=storeInfoService.findAll();
		System.out.println(storeInfoResponse);
		List<Long> storeidsList=storeInfoService.getAllStoreIds();
	    System.out.println(storeidsList);
	    Long assignedStoreIdList;
	    Long unassignedStoreIds;
	    List<Long> assiginStorelist=new ArrayList();
	    List<Long> unassignedStoreIdList=new ArrayList();
	     List<String> assisgnedStoreNames = new ArrayList();
	    for (Long id : storeidsList) {
	    	System.out.println("store id is "+id);
	          List<UserInfo> userInfos=userService.getAllUsers(id);
			
	    	//System.out.println(userInfos);
	    	//for (UserInfo userInfo : userInfos) {
				 if(userInfos.size()>0) {
					  System.out.println("we are in if black");
					  
					  assignedStoreIdList=id;
					  //System.out.println( assignedStoreIdList);
					  assiginStorelist.add(assignedStoreIdList);
					  
				  }
				 else {
		    		unassignedStoreIds= id;
		    		 //System.out.println(unassignedStoreIdList);
		    		 unassignedStoreIdList.add(unassignedStoreIds);
		    	 }
			   System.out.println("Unassignstore ids"+unassignedStoreIdList);
			  System.out.println("Assign store ids"+assiginStorelist);
	    	}
	   // }
		List<StoreInfo> resultStoresInfos = new ArrayList<>();
	    for (Long id : assiginStorelist) {
	    	System.out.println("store id is"+id);
	    
	    	StoreInfo assignedStoreInfo =storeInfoService.findByStoreId(id);
	    	resultStoresInfos.add(assignedStoreInfo);
	    	//System.out.println(assignedStoreInfo);	
	    }
	    System.out.println("----List of store informations--"+resultStoresInfos.size());
    	for (StoreInfo storeNames : resultStoresInfos) {
    		assisgnedStoreNames.add(storeNames.getStoreName());
    	}
	   return assisgnedStoreNames;
	
		
	}
	
	//All Store information for dashboard
		@RequestMapping(value = "/StoreInfoForDashBoard/{storeName}",method = RequestMethod.GET)
		public StoreInfoResponse storeTotalInfoForDashBoard(@PathVariable("storeName")String storeName ) throws IOException {

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
//			System.out.println(userService.getAllRolesCount(storeid));
//			RoleConunt roleConunt=userService.getAllRolesCount(storeid);
//			System.out.println(roleConunt);
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
}

