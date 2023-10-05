package com.safesmart.safesmart.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.safesmart.safesmart.builder.RoleBuilder;
import com.safesmart.safesmart.builder.StoreInfoBuilder;
import com.safesmart.safesmart.model.BillValidator;
import com.safesmart.safesmart.model.ChangeRequest;
import com.safesmart.safesmart.model.ChangeValetDenominations;
import com.safesmart.safesmart.model.Crop;
import com.safesmart.safesmart.model.InsertBill;
import com.safesmart.safesmart.model.Kiosk;
import com.safesmart.safesmart.model.Locks;
import com.safesmart.safesmart.model.LocksInfo;
import com.safesmart.safesmart.model.Printer;
import com.safesmart.safesmart.model.Role;
import com.safesmart.safesmart.model.SampleTable;
import com.safesmart.safesmart.model.StoreInfo;
import com.safesmart.safesmart.model.TruckChangeRequest;
import com.safesmart.safesmart.model.UserInfo;
import com.safesmart.safesmart.model.ValetDenominations;
import com.safesmart.safesmart.remoterepository.Remote_BillValidatorRepository;
import com.safesmart.safesmart.remoterepository.Remote_ChangeRequestRepository;
import com.safesmart.safesmart.remoterepository.Remote_ChangeRquestDenominationsRepository;
import com.safesmart.safesmart.remoterepository.Remote_CropRepository;
import com.safesmart.safesmart.remoterepository.Remote_InsertBillRepository;
import com.safesmart.safesmart.remoterepository.Remote_KioskRepository;
import com.safesmart.safesmart.remoterepository.Remote_LockInfoRepository;
import com.safesmart.safesmart.remoterepository.Remote_LocksRepository;
import com.safesmart.safesmart.remoterepository.Remote_PrinterRepository;
import com.safesmart.safesmart.remoterepository.Remote_RoleRepository;
import com.safesmart.safesmart.remoterepository.Remote_SampleTableRepository;
import com.safesmart.safesmart.remoterepository.Remote_StoreInfoRepository;
import com.safesmart.safesmart.remoterepository.Remote_TruckChangeRequestRepository;
import com.safesmart.safesmart.remoterepository.Remote_UserInfoRepository;
import com.safesmart.safesmart.remoterepository.Remote_ValetDenominationsRepository;
import com.safesmart.safesmart.repository.BillValidatorRepository;
import com.safesmart.safesmart.repository.ChangeRequestRepository;
import com.safesmart.safesmart.repository.ChangeRquestDenominationsRepository;
import com.safesmart.safesmart.repository.CropRepository;
import com.safesmart.safesmart.repository.InsertBillRepository;
import com.safesmart.safesmart.repository.KioskRepository;
import com.safesmart.safesmart.repository.LockInfoRepository;
import com.safesmart.safesmart.repository.LocksRepository;
import com.safesmart.safesmart.repository.PrinterRepository;
import com.safesmart.safesmart.repository.RoleRepository;
import com.safesmart.safesmart.repository.SampleRepository;
import com.safesmart.safesmart.repository.StoreInfoRepository;
import com.safesmart.safesmart.repository.TruckChangeRequestRepository;
import com.safesmart.safesmart.repository.UserInfoRepository;
import com.safesmart.safesmart.repository.ValetDenominationsRepository;

@Service
public class DataSyncService {

	@Autowired
	private SampleRepository sampleRepository;
	
	@Autowired
	private Remote_SampleTableRepository remote_sampleTableRepository;
	
	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@Autowired
	private Remote_UserInfoRepository remote_userInfoRepository;

	@Autowired
	private  StoreInfoRepository storeInfoRepository;
	

	@Autowired
	private StoreInfoBuilder storeInfoBuilder;
	
	@Autowired
	private  Remote_StoreInfoRepository remote_storeInfoRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private Remote_RoleRepository remote_RoleRepository;

	@Autowired
	private RoleBuilder roleBuilder;
	
	@Autowired
	private BillValidatorRepository billValidatorRepository;
	
	@Autowired
	private Remote_BillValidatorRepository remote_billValidatorRepository;
	
	@Autowired
	private InsertBillRepository insertBillRepository;
	@Autowired
	private Remote_InsertBillRepository remote_insertBillRepository;
	
	@Autowired
	private KioskRepository kioskRepository;
	@Autowired
	private Remote_KioskRepository remote_kioskRepository;
	
	@Autowired
	private LocksRepository locksRepository;
	@Autowired
	private Remote_LocksRepository remote_locksRepository;
	
	@Autowired
	private LockInfoRepository lockInfoRepository;
	@Autowired
	private Remote_LockInfoRepository remote_lockInfoRepository;
	
	@Autowired
	private PrinterRepository printerRepository;
	@Autowired
	private Remote_PrinterRepository remote_printerRepository;
	
	@Autowired
	private ValetDenominationsRepository valetDenominationsRepository;
	@Autowired
	private Remote_ValetDenominationsRepository remote_valetDenominationsRepository;
	
	@Autowired
	private ChangeRequestRepository changeRequestRepository;
	
	@Autowired
	private Remote_ChangeRequestRepository remote_changeRequestRepository;
	
	@Autowired
	private TruckChangeRequestRepository truckChangeRequestRepository;
	@Autowired
	private Remote_TruckChangeRequestRepository remote_truckChangeRequestRepository;
	
	@Autowired
	private ChangeRquestDenominationsRepository changeRquestDenominationsRepository;
	@Autowired
	private Remote_ChangeRquestDenominationsRepository remote_changeRquestDenominationsRepository;
	
	@Autowired
	private CropRepository cropRepository;
	@Autowired
	private Remote_CropRepository remote_cropRepository;
	

	

	@Scheduled(fixedRate = 120000)
	 public void performDataSync() {
	   //   syncData();
	      syncDataUser();
	      syncDataStore();
	      syncDataRole();
	      syncDataBillValidator();
	      syncDataInsertBill();
	      syncDataKiosk();
	      syncDataLocks();
	      syncDataLocksInfo();
	      syncDataPrinter();
	      syncDataValetDenominations();
	      syncDataChangeRequest();
	      syncDataTruckChangeRequest();
	      syncDataChangeRequestDenominations();
	      syncDataCrop();
	      
	}
	
    @Transactional
    public void syncData() {
        List<SampleTable> locals = (List<SampleTable>) sampleRepository.findAll();

        for (SampleTable local : locals) {
        
        	SampleTable serverExisting = remote_sampleTableRepository.findByName(local.getName());
            
            if (serverExisting != null) {
            	
            } else {
            	remote_sampleTableRepository.save(local);
            }
        }
    }
    
    // user
    @Transactional
    public void syncDataUser() {
        List<UserInfo> locals = (List<UserInfo>) userInfoRepository.findAll();
        List<UserInfo> remotes = (List<UserInfo>) remote_userInfoRepository.findAll();

        for (UserInfo local : locals) {
        	
            for (UserInfo remote : remotes) {
             if(remote.getId().equals(local.getId())) {
            	remote.setRole(local.getRole());
            	remote.setUsername(local.getUsername());
            	remote.setPassword((local.getPassword()));
            	remote.setCreate_time(local.getCreate_time());
            	remote.setActive(local.isActive());
            	remote.setFirstName(local.getFirstName());
            	remote.setLastName(local.getLastName());
            	remote.setEmail(local.getEmail());
            	remote.setMobile(local.getMobile());
            	remote.setPassLength(local.getPassLength());
            	remote.setLastLoginDate(local.getLastLoginDate());
            	remote.setStoreInfo(local.getStoreInfo());
       		
        		remote_userInfoRepository.save(remote);
              }
            }
                 
        	UserInfo userInfo = remote_userInfoRepository.findByUsername(local.getUsername());
    		System.out.println("1");
        
    		System.out.println("2");
    		userInfo = remote_userInfoRepository.findByPassword(local.getPassword());
    	       	
            if (userInfo != null) {
        		System.out.println("3");

            } else {
        		System.out.println("4");
//        		userInfo = new UserInfo();
//        		userInfo.setRole(local.getRole());
//        		userInfo.setUsername(local.getUsername());
//        		userInfo.setPassword((local.getPassword()));
//        		userInfo.setCreate_time(local.getCreate_time());
//        		userInfo.setActive(true);
//        		userInfo.setFirstName(local.getFirstName());
//        		userInfo.setLastName(local.getLastName());
//        		userInfo.setEmail(local.getEmail());
//        		userInfo.setMobile(local.getMobile());
//        		userInfo.setPassLength(local.getPassLength());
//        		userInfo.setLastLoginDate(local.getLastLoginDate());
//        		userInfo.setStoreInfo(local.getStoreInfo());
       		
        		remote_userInfoRepository.save(local);
            }

        }
    }
    
    
    //store
    @Transactional
    public void syncDataStore() {
        List<StoreInfo> locals = (List<StoreInfo>) storeInfoRepository.findAll();
        List<StoreInfo> remotes = (List<StoreInfo>) remote_storeInfoRepository.findAll();

        for (StoreInfo local : locals) {
        	
            for (StoreInfo remote : remotes) {
                if(remote.getId().equals(local.getId())) {
                	remote.setAccountNumber(local.getAccountNumber());
                	remote.setAddress(local.getAddress());
                	remote.setBankName(local.getBankName());
                	remote.setConfigured(local.isConfigured());
                	remote.setStatus(local.isStatus());
                	remote.setCorpStoreNo(local.getCorpStoreNo());
                	remote.setMinimumBalance(local.getMinimumBalance());
                	remote.setSerialNumber(local.getSerialNumber());
                	remote.setStoreName(local.getStoreName());
    
                	remote.setStartTime(local.getStartTime());
                	remote.setEndTime(local.getEndTime());
           		
            		remote_storeInfoRepository.save(remote);
                 }
               }
        	
                 
        	StoreInfo storeInfo = remote_storeInfoRepository.findByStoreName(local.getStoreName());
    	
    		//CorpNo
        	storeInfo = remote_storeInfoRepository.findByCorpStoreNo(local.getCorpStoreNo());
    	
    		//SerialNo
        	storeInfo = remote_storeInfoRepository.findBySerialNumber(local.getSerialNumber());
    	
    		//AccNo
        	storeInfo= remote_storeInfoRepository.findByAccountNumber(local.getAccountNumber());

    	
        	
            if (storeInfo != null) {
        		System.out.println("3");

            } else {
        		System.out.println("4");
//        		storeInfo = new StoreInfo();
//        		storeInfo.setAccountNumber(local.getAccountNumber());
//        		storeInfo.setAddress(local.getAddress());
//        		storeInfo.setBankName(local.getBankName());
//        		storeInfo.setConfigured(local.isConfigured());
//        		storeInfo.setStatus(local.isStatus());
//        		storeInfo.setCorpStoreNo(local.getCorpStoreNo());
//        		storeInfo.setMinimumBalance(local.getMinimumBalance());
//        		storeInfo.setSerialNumber(local.getSerialNumber());
//        		storeInfo.setStoreName(local.getStoreName());
//
//        		storeInfo.setStartTime(local.getStartTime());
//        		storeInfo.setEndTime(local.getEndTime());
       		
        		remote_storeInfoRepository.save(local);
            }
        }
    }
    
    
    //role
    @Transactional
    public void syncDataRole() {
        List<Role> locals = (List<Role>) roleRepository.findAll();
        List<Role> remotes = (List<Role>) remote_RoleRepository.findAll();


        for (Role local : locals) {
        	
            for (Role remote : remotes) {
                if(remote.getId().equals(local.getId())) {
                	remote.setName(local.getName());
                	remote.setDescription(local.getDescription());
                	remote.setFeatures(local.getFeatures());
                	remote.setWebModule(local.getWebModule());
           		
            		remote_RoleRepository.save(remote);
                 }
               }
                 
    		Role role = remote_RoleRepository.findByName(local.getName());
    		role = remote_RoleRepository.findByDescription(local.getDescription());
    	
            if (role != null) {
        		System.out.println("3");

            } else {
        		System.out.println("4");
//        		role = new Role();
//        		role.setId(local.getId());
//        		role.setName(local.getName());
//        		role.setDescription(local.getDescription());
//        		role.setFeatures(local.getFeatures());
//        		role.setWebModule(local.getWebModule());
       		
        		remote_RoleRepository.save(local);
            }
        }
    }
    
    //billvalidator
    @Transactional
    public void syncDataBillValidator() {
        List<BillValidator> locals = (List<BillValidator>) billValidatorRepository.findAll();
        List<BillValidator> remotes = (List<BillValidator>) remote_billValidatorRepository.findAll();

        
        for (BillValidator local : locals) {
        	
            for (BillValidator remote : remotes) {
                if(remote.getId().equals(local.getId())) {
                	remote.setBillAcceptorNo(local.getBillAcceptorNo());
                	remote.setBillAcceptorName(local.getBillAcceptorName());
                	remote.setBrandName(local.getBrandName());
                	remote.setModelName(local.getModelName());
                	remote.setMachineType(local.getMachineType());
                	remote.setStorageCapacity(local.getStorageCapacity());           	
                	remote.setActive(local.isActive());
                	remote.setStoreinfob(local.getStoreinfob());
           		
                	remote_billValidatorRepository.save(remote);
                 }
               }
                 	
    		BillValidator billValidator = remote_billValidatorRepository.findByBillAcceptorName(local.getBillAcceptorName());   		
    		//validation for billvalidatorNo
    		billValidator = remote_billValidatorRepository.findByBillAcceptorNo(local.getBillAcceptorNo());

            if (billValidator != null) {
        		System.out.println("3");

            } else {
        		System.out.println("4");
//        		billValidator = new BillValidator();
//        		billValidator.setBillAcceptorNo(local.getBillAcceptorNo());
//        		billValidator.setBillAcceptorName(local.getBillAcceptorName());
//        		billValidator.setBrandName(local.getBrandName());
//        		billValidator.setModelName(local.getModelName());
//        		billValidator.setMachineType(local.getMachineType());
//        		billValidator.setStorageCapacity(local.getStorageCapacity());
//        	
//        		billValidator.setActive(local.isActive());
//        		billValidator.setStoreinfob(local.getStoreinfob());
       		
        		remote_billValidatorRepository.save(local);
            }
        }
    }
    
    //insertBill
    @Transactional
    public void syncDataInsertBill() {
        List<InsertBill> locals = (List<InsertBill>) insertBillRepository.findAll();
        List<InsertBill> remotes = (List<InsertBill>) remote_insertBillRepository.findAll();

        for (InsertBill local : locals) {
        	
        	for (InsertBill remote : remotes) {
                if(remote.getId().equals(local.getId())) {
                	remote.setAmount(local.getAmount());
                	remote.setCreatedOn(local.getCreatedOn());
                	remote.setDateTime(local.getDateTime());
                	remote.setTransactionNumber(local.getTransactionNumber());
                	remote.setUser(local.getUser());
           		
                	remote_insertBillRepository.save(remote);
                 }
               }
                 	
        		System.out.println("4");
//        		InsertBill insertBill = new InsertBill();
//        		insertBill.setAmount(local.getAmount());
//        		insertBill.setCreatedOn(local.getCreatedOn());
//        		insertBill.setDateTime(local.getDateTime());
//        		insertBill.setTransactionNumber(local.getTransactionNumber());
//        		insertBill.setUser(local.getUser());
        		remote_insertBillRepository.save(local);

        }
    }
    
  //kiosk
    @Transactional
    public void syncDataKiosk() {
        List<Kiosk> locals = (List<Kiosk>) kioskRepository.findAll();
        List<Kiosk> remotes = (List<Kiosk>) remote_kioskRepository.findAll();


        for (Kiosk local : locals) {
        	
        	for (Kiosk remote : remotes) {
                if(remote.getId().equals(local.getId())) {
                	remote.setKioskId(local.getKioskId());
                	remote.setKioskName(local.getKioskName());
                	remote.setBrandName(local.getBrandName());
                	remote.setModelName(local.getModelName());
                	remote.setCpu(local.getCpu());
                	remote.setHdd(local.getHdd());
                	remote.setRamMemory(local.getRamMemory());
                	remote.setScreenSize(local.getScreenSize());
                	remote.setIpAddress(local.getIpAddress());
                	remote.setMacAddress(local.getMacAddress());
                	remote.setActive(local.isActive());
                	remote.setStoreinfok(local.getStoreinfok());
           		
            		remote_kioskRepository.save(remote);
                 }
               }
   		
    		Kiosk kiosk = remote_kioskRepository.findByKioskName(local.getKioskName());

    		//validation for kioskId
    		kiosk = remote_kioskRepository.findByKioskId(local.getKioskId());
    		
            if (kiosk != null) {
        		System.out.println("3");

            } else {
//        		System.out.println("4");
//          		kiosk = new Kiosk();
//        		kiosk.setKioskId(local.getKioskId());
//        		kiosk.setKioskName(local.getKioskName());
//        		kiosk.setBrandName(local.getBrandName());
//        		kiosk.setModelName(local.getModelName());
//        		kiosk.setCpu(local.getCpu());
//        		kiosk.setHdd(local.getHdd());
//        		kiosk.setRamMemory(local.getRamMemory());
//        		kiosk.setScreenSize(local.getScreenSize());
//        		kiosk.setIpAddress(local.getIpAddress());
//        		kiosk.setMacAddress(local.getMacAddress());
//        		kiosk.setActive(local.isActive());
//        		kiosk.setStoreinfok(local.getStoreinfok());

        		remote_kioskRepository.save(local);
            }
        }
    }
    
    //locks
    @Transactional
    public void syncDataLocks() {
        List<Locks> locals = (List<Locks>) locksRepository.findAll();
        List<Locks> remotes = (List<Locks>) remote_locksRepository.findAll();


        for (Locks local : locals) {
        	
        	for (Locks remote : remotes) {
                if(remote.getId().equals(local.getId())) {
                	remote.setDigitalLockNo(local.getDigitalLockNo());
                	remote.setDigitalLockName(local.getDigitalLockName());
                	remote.setBrandName(local.getBrandName());
                	remote.setModelName(local.getModelName());
                	remote.setMachineType(local.getMachineType());
                	remote.setConnectors(local.getConnectors());
            	
                	remote.setActive(local.isActive());
                	remote.setStoreInfo(local.getStoreInfo());
           		
            		remote_locksRepository.save(remote);
                 }
               }
 
    		Locks locks = remote_locksRepository.findByDigitalLockName(local.getDigitalLockName());
    
    		//validation for lockNo
    		locks = remote_locksRepository.findByDigitalLockNo(local.getDigitalLockNo());
    		
            if (locks != null) {
        		System.out.println("3");

            } else {
//        		System.out.println("4");
//        		locks = new Locks();
//        		locks.setDigitalLockNo(local.getDigitalLockNo());
//        		locks.setDigitalLockName(local.getDigitalLockName());
//        		locks.setBrandName(local.getBrandName());
//        		locks.setModelName(local.getModelName());
//        		locks.setMachineType(local.getMachineType());
//        		locks.setConnectors(local.getConnectors());
//        	
//        		locks.setActive(local.isActive());
//        		locks.setStoreInfo(local.getStoreInfo());

        		remote_locksRepository.save(local);
        		
            }
        }
    }
    
    //lockInfo
    @Transactional
    public void syncDataLocksInfo() {
        List<LocksInfo> locals = (List<LocksInfo>) lockInfoRepository.findAll();
        List<LocksInfo> remotes = (List<LocksInfo>) remote_lockInfoRepository.findAll();


        for (LocksInfo local : locals) {
        	
          	for (LocksInfo remote : remotes) {
                if(remote.getId().equals(local.getId())) {
                	remote.setUsers(local.getUsers());
                	remote.setLockType(local.getLockType());
                	remote.setLockTime(local.getLockTime());
                	remote.setLockStatus(local.getLockStatus());
                	remote.setUsers(local.getUsers());
           		
            		remote_lockInfoRepository.save(remote);
                 }
               }
 
//        		System.out.println("4");
//        		LocksInfo lockinfo =new LocksInfo();
//        		
//        		lockinfo.setUsers(local.getUsers());
//        		lockinfo.setLockType(local.getLockType());
//        		lockinfo.setLockTime(local.getLockTime());
//        		lockinfo.setLockStatus(local.getLockStatus());
//        		lockinfo.setUsers(local.getUsers());
        		remote_lockInfoRepository.save(local);
        		
            }
        
    }
    
    //printer
    @Transactional
    public void syncDataPrinter() {
        List<Printer> locals = (List<Printer>) printerRepository.findAll();
        List<Printer> remotes = (List<Printer>) remote_printerRepository.findAll();


        for (Printer local : locals) {
        	
        	for (Printer remote : remotes) {
                if(remote.getId().equals(local.getId())) {
                	remote.setPrinterNo(local.getPrinterNo());
                	remote.setPrinterName(local.getPrinterName());
                	remote.setBrandName(local.getBrandName());
                	remote.setModelName(local.getModelName());
                	remote.setMachineType(local.getMachineType());
            		remote.setPrintCapacity(local.getPrintCapacity());
            	
            		remote.setActive(local.isActive());
            		remote.setStoreinfop(local.getStoreinfop());
           		
            		remote_printerRepository.save(remote);
                 }
               }
 		
    		Printer printer = remote_printerRepository.findByPrinterName(local.getPrinterName());

    		// validation for printerId
    		printer = remote_printerRepository.findByPrinterNo(local.getPrinterNo());

            if (printer != null) {
        		System.out.println("3");

            } else {
//        		System.out.println("4");
//          		printer = new Printer();
//        		printer.setPrinterNo(local.getPrinterNo());
//        		printer.setPrinterName(local.getPrinterName());
//        		printer.setBrandName(local.getBrandName());
//        		printer.setModelName(local.getModelName());
//        		printer.setMachineType(local.getMachineType());
//        		printer.setPrintCapacity(local.getPrintCapacity());
//        	
//        		printer.setActive(local.isActive());
//        		printer.setStoreinfop(local.getStoreinfop());

        		remote_printerRepository.save(local);
        		
            }
        }
    }
    
    //valet Denominations
	@Transactional
    public void syncDataValetDenominations() {
        List<ValetDenominations> locals = (List<ValetDenominations>) valetDenominationsRepository.findAll();
        List<ValetDenominations> remotes = (List<ValetDenominations>) remote_valetDenominationsRepository.findAll();

        for (ValetDenominations local : locals) {
 		
        	for (ValetDenominations remote : remotes) {
                if(remote.getId().equals(local.getId())) {
                	remote.setCents(local.getCents());
                	remote.setNickels(local.getNickels());
                	remote.setDimes(local.getDimes());
                	remote.setQuarters(local.getQuarters());
                	remote.setDen_1$(local.getDen_1$());
                	remote.setDen_5$(local.getDen_5$());
        			remote.setDen_10$(local.getDen_10$());
        			remote.setDen_20$(local.getDen_20$());
        			remote.setDen_50$(local.getDen_50$());
        			remote.setDen_100$(local.getDen_100$());
        			remote.setType(local.getType());
        			remote.setCreatedBy(local.getCreatedBy());
        			remote.setCreated(local.getCreated());
        			remote.setModifiedBy(local.getModifiedBy());
        			remote.setModified(local.getModified());
           		
                	remote_valetDenominationsRepository.save(remote);
                 }
               }
    		
    		ValetDenominations valetDenominations1 = remote_valetDenominationsRepository.findByType(local.getType());
    		
    		if (valetDenominations1 != null) {
    		   			
    		} else {
//    			ValetDenominations valetDenominations =new ValetDenominations();
//    			valetDenominations.setCents(local.getCents());
//    			valetDenominations.setNickels(local.getNickels());
//    			valetDenominations.setDimes(local.getDimes());
//    			valetDenominations.setQuarters(local.getQuarters());
//    			valetDenominations.setDen_1$(local.getDen_1$());
//    			valetDenominations.setDen_5$(local.getDen_5$());
//    			valetDenominations.setDen_10$(local.getDen_10$());
//    			valetDenominations.setDen_20$(local.getDen_20$());
//    			valetDenominations.setDen_50$(local.getDen_50$());
//    			valetDenominations.setDen_100$(local.getDen_100$());
//     			valetDenominations.setType(local.getType());
//    			valetDenominations.setCreatedBy(local.getCreatedBy());
//    			valetDenominations.setCreated(local.getCreated());
//     			valetDenominations.setModifiedBy(local.getModifiedBy());
//    			valetDenominations.setModified(local.getModified());
    			
    			remote_valetDenominationsRepository.save(local);
    		
    		
    		}
        }
    }
    
    //change request
    @Transactional
    public void syncDataChangeRequest() {
        List<ChangeRequest> locals = (List<ChangeRequest>) changeRequestRepository.findAll();
        List<ChangeRequest> remotes = (List<ChangeRequest>) remote_changeRequestRepository.findAll();

        for (ChangeRequest local : locals) {
        	
        	for (ChangeRequest remote : remotes) {
                if(remote.getId().equals(local.getId())) {
                	remote.setCents(local.getCents());
                	remote.setNickels(local.getNickels());
                	remote.setDimes(local.getDimes());
                	remote.setQuarters(local.getQuarters());
                	remote.setDen_1$(local.getDen_1$());
                	remote.setDen_5$(local.getDen_5$());
                	remote.setDen_10$(local.getDen_10$());
                	remote.setDen_20$(local.getDen_20$());
                	remote.setDen_50$(local.getDen_50$());
                	remote.setDen_100$(local.getDen_100$());
                	remote.setOrderStatus(local.getOrderStatus());
                	remote.setType(local.getType());
                	remote.setCreatedBy(local.getCreatedBy());
                	remote.setCreated(local.getCreated());
                	remote.setModifiedBy(local.getModifiedBy());
                	remote.setModified(local.getModified());
           		
            		remote_changeRequestRepository.save(remote);
                 }
               }
    		
//    		ChangeRequest changeRequest = new ChangeRequest();
//    		BeanUtils.copyProperties(local, changeRequest);    	
//    			
//    		changeRequest.setCents(local.getCents());
//    		changeRequest.setNickels(local.getNickels());
//    		changeRequest.setDimes(local.getDimes());
//    		changeRequest.setQuarters(local.getQuarters());
//    		changeRequest.setDen_1$(local.getDen_1$());
//    		changeRequest.setDen_5$(local.getDen_5$());
//    		changeRequest.setDen_10$(local.getDen_10$());
//    		changeRequest.setDen_20$(local.getDen_20$());
//    		changeRequest.setDen_50$(local.getDen_50$());
//    		changeRequest.setDen_100$(local.getDen_100$());
//    		changeRequest.setOrderStatus(local.getOrderStatus());
//    		changeRequest.setType(local.getType());
//    		changeRequest.setCreatedBy(local.getCreatedBy());
//    		changeRequest.setCreated(local.getCreated());
//    		changeRequest.setModifiedBy(local.getModifiedBy());
//    		changeRequest.setModified(local.getModified());
    			
    		remote_changeRequestRepository.save(local);
    			
        }
    }
    
    
    //truck change request
    @Transactional
    public void syncDataTruckChangeRequest() {
        List<TruckChangeRequest> locals = (List<TruckChangeRequest>) truckChangeRequestRepository.findAll();
        List<TruckChangeRequest> remotes = (List<TruckChangeRequest>) remote_truckChangeRequestRepository.findAll();

        for (TruckChangeRequest local : locals) {
        	
            for (TruckChangeRequest remote : remotes) {
                if(remote.getId().equals(local.getId())) {
                	remote.setCents(local.getCents());
                	remote.setNickels(local.getNickels());
                	remote.setDimes(local.getDimes());
                	remote.setQuarters(local.getQuarters());
                	remote.setDen_1$(local.getDen_1$());
                	remote.setDen_5$(local.getDen_5$());
                	remote.setDen_10$(local.getDen_10$());
                	remote.setDen_20$(local.getDen_20$());
                	remote.setDen_50$(local.getDen_50$());
                	remote.setDen_100$(local.getDen_100$());
                	remote.setOrderStatus(local.getOrderStatus());
            		remote.setType(local.getType());
            		remote.setCreatedBy(local.getCreatedBy());
            		remote.setCreated(local.getCreated());
            		remote.setModifiedBy(local.getModifiedBy());
            		remote.setModified(local.getModified());
          		
            		remote_truckChangeRequestRepository.save(remote);
                 }
               }
        	
//    		TruckChangeRequest truckChangeRequest = new TruckChangeRequest();
//    		BeanUtils.copyProperties(local, truckChangeRequest);
//   			
//    		truckChangeRequest.setCents(local.getCents());
//    		truckChangeRequest.setNickels(local.getNickels());
//    		truckChangeRequest.setDimes(local.getDimes());
//    		truckChangeRequest.setQuarters(local.getQuarters());
//    		truckChangeRequest.setDen_1$(local.getDen_1$());
//    		truckChangeRequest.setDen_5$(local.getDen_5$());
//    		truckChangeRequest.setDen_10$(local.getDen_10$());
//    		truckChangeRequest.setDen_20$(local.getDen_20$());
//    		truckChangeRequest.setDen_50$(local.getDen_50$());
//    		truckChangeRequest.setDen_100$(local.getDen_100$());
//    		truckChangeRequest.setOrderStatus(local.getOrderStatus());
//    		truckChangeRequest.setType(local.getType());
//    		truckChangeRequest.setCreatedBy(local.getCreatedBy());
//    		truckChangeRequest.setCreated(local.getCreated());
//    		truckChangeRequest.setModifiedBy(local.getModifiedBy());
//    		truckChangeRequest.setModified(local.getModified());
    			
    		remote_truckChangeRequestRepository.save(local);
    			
        }
    }
    
    
    //change request denominations
    @Transactional
    public void syncDataChangeRequestDenominations() {
        List<ChangeValetDenominations> locals = (List<ChangeValetDenominations>) changeRquestDenominationsRepository.findAll();
        List<ChangeValetDenominations> remotes = (List<ChangeValetDenominations>) remote_changeRquestDenominationsRepository.findAll();

        
        for (ChangeValetDenominations local : locals) {
        	
            for (ChangeValetDenominations remote : remotes) {
                if(remote.getId().equals(local.getId())) {
                	remote.setNew_cents(local.getNew_cents());
                	remote.setNew_dimes(local.getNew_dimes());
                	remote.setNew_nickels(local.getNew_nickels());
                	remote.setNew_quarters(local.getNew_quarters());
                	remote.setNew_den_1$(local.getNew_den_1$());
                	remote.setNew_den_5$(local.getNew_den_5$());
                	remote.setNew_den_10$(local.getNew_den_10$());
                	remote.setNew_den_20$(local.getNew_den_20$());
                	remote.setNew_den_50$(local.getNew_den_50$());
                	remote.setNew_den_100$(local.getNew_den_100$());
        		
                	remote.setOld_cents(local.getOld_cents());
                	remote.setOld_dimes(local.getOld_dimes());
                	remote.setOld_nickels(local.getOld_nickels());
                	remote.setOld_quarters(local.getOld_quarters());
                	remote.setOld_den_1$(local.getOld_den_1$());
                	remote.setOld_den_5$(local.getOld_den_5$());
                	remote.setOld_den_10$(local.getOld_den_10$());
                	remote.setOld_den_20$(local.getOld_den_20$());
                	remote.setOld_den_50$(local.getOld_den_50$());
                	remote.setOld_den_100$(local.getOld_den_100$());
        
                	remote.setType(local.getType());
                	remote.setUpdatedOn(local.getUpdatedOn());
                	remote.setUpdatedBy(local.getUpdatedBy());
                	remote.setValetDenominations(local.getValetDenominations());
            		remote.setChangeRequest(local.getChangeRequest());
            		remote.setCreated(local.getCreated());
          		
            		remote_changeRquestDenominationsRepository.save(remote);
                 }
               }
        	
//        	ChangeValetDenominations ChangeRequestDenominations = new ChangeValetDenominations();
//    		BeanUtils.copyProperties(local, ChangeRequestDenominations);
//    			
//    		ChangeRequestDenominations.setNew_cents(local.getNew_cents());
//    		ChangeRequestDenominations.setNew_dimes(local.getNew_dimes());
//    		ChangeRequestDenominations.setNew_nickels(local.getNew_nickels());
//    		ChangeRequestDenominations.setNew_quarters(local.getNew_quarters());
//    		ChangeRequestDenominations.setNew_den_1$(local.getNew_den_1$());
//    		ChangeRequestDenominations.setNew_den_5$(local.getNew_den_5$());
//    		ChangeRequestDenominations.setNew_den_10$(local.getNew_den_10$());
//    		ChangeRequestDenominations.setNew_den_20$(local.getNew_den_20$());
//    		ChangeRequestDenominations.setNew_den_50$(local.getNew_den_50$());
//    		ChangeRequestDenominations.setNew_den_100$(local.getNew_den_100$());
//		
//    		ChangeRequestDenominations.setOld_cents(local.getOld_cents());
//    		ChangeRequestDenominations.setOld_dimes(local.getOld_dimes());
//    		ChangeRequestDenominations.setOld_nickels(local.getOld_nickels());
//    		ChangeRequestDenominations.setOld_quarters(local.getOld_quarters());
//    		ChangeRequestDenominations.setOld_den_1$(local.getOld_den_1$());
//    		ChangeRequestDenominations.setOld_den_5$(local.getOld_den_5$());
//    		ChangeRequestDenominations.setOld_den_10$(local.getOld_den_10$());
//    		ChangeRequestDenominations.setOld_den_20$(local.getOld_den_20$());
//    		ChangeRequestDenominations.setOld_den_50$(local.getOld_den_50$());
//    		ChangeRequestDenominations.setOld_den_100$(local.getOld_den_100$());
//
//    		ChangeRequestDenominations.setType(local.getType());
//    		ChangeRequestDenominations.setUpdatedOn(local.getUpdatedOn());
//    		ChangeRequestDenominations.setUpdatedBy(local.getUpdatedBy());
//    		ChangeRequestDenominations.setValetDenominations(local.getValetDenominations());
//    		ChangeRequestDenominations.setChangeRequest(local.getChangeRequest());
//    		ChangeRequestDenominations.setCreated(local.getCreated());
    			
    		remote_changeRquestDenominationsRepository.save(local);
    			
        }
    }
    
    
    @Transactional
    public void syncDataCrop() {
        List<Crop> locals = (List<Crop>) cropRepository.findAll();
        List<Crop> remotes = (List<Crop>) remote_cropRepository.findAll();


        for (Crop local : locals) {
        	
        	for (Crop remote : remotes) {
                if(remote.getId().equals(local.getId())) {
                	remote.setCropName(local.getCropName());
                	remote.setDescription(local.getDescription());
                	remote.setStatus(local.getStatus());
                	
                	
           		
            		remote_cropRepository.save(remote);
                 }
               }
 		
    		Crop crop = remote_cropRepository.findByCropName(local.getCropName());

    		// validation for printerId
//    		printer = remote_printerRepository.findByPrinterNo(local.getPrinterNo());

            if (crop != null) {
        		System.out.println("3");

            } else {
//        		System.out.println("4");
//          		printer = new Printer();
//        		printer.setPrinterNo(local.getPrinterNo());
//        		printer.setPrinterName(local.getPrinterName());
//        		printer.setBrandName(local.getBrandName());
//        		printer.setModelName(local.getModelName());
//        		printer.setMachineType(local.getMachineType());
//        		printer.setPrintCapacity(local.getPrintCapacity());
//        	
//        		printer.setActive(local.isActive());
//        		printer.setStoreinfop(local.getStoreinfop());

        		remote_cropRepository.save(local);
        		
            }
        }
    }
    

 
}
