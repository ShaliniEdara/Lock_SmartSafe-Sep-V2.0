package com.safesmart.safesmart.service;

import java.awt.print.Pageable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.util.Streamable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.safesmart.safesmart.common.CommonException;
import com.safesmart.safesmart.common.CommonExceptionMessage;
import com.safesmart.safesmart.dto.KioskResponse;
import com.safesmart.safesmart.dto.RoleConunt;
import com.safesmart.safesmart.dto.RoleDto;
import com.safesmart.safesmart.dto.RolesDto;
import com.safesmart.safesmart.dto.StoreInfoResponse;
import com.safesmart.safesmart.dto.UserInfoRequest;
import com.safesmart.safesmart.dto.UserInfoResponse;
import com.safesmart.safesmart.model.Kiosk;
import com.safesmart.safesmart.model.Role;
import com.safesmart.safesmart.model.SampleTable;
import com.safesmart.safesmart.model.StoreInfo;
import com.safesmart.safesmart.model.UserInfo;
import com.safesmart.safesmart.remoterepository.Remote_RoleRepository;
import com.safesmart.safesmart.remoterepository.Remote_UserInfoRepository;
import com.safesmart.safesmart.repository.KioskRepository;
import com.safesmart.safesmart.repository.RoleRepository;
import com.safesmart.safesmart.repository.StoreInfoRepository;
import com.safesmart.safesmart.repository.UserInfoRepository;
import com.safesmart.safesmart.util.Base64BasicEncryption;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserInfoRepository userInfoRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private Base64BasicEncryption passwordEncrypt;
	
	@Autowired
	private KioskRepository kioskRepository;
	
	@Autowired
	private  StoreInfoRepository storeInfoRepository;
	
	@Autowired
	private Remote_UserInfoRepository remote_userInfoRepository;

	@Autowired
	private Remote_RoleRepository remote_roleRepository;

	public void add(UserInfoRequest userInfoRequest) {

		Role role = roleRepository.findByName(userInfoRequest.getRole());

		UserInfo userInfo = userInfoRepository.findByUsername(userInfoRequest.getUsername());
		if (userInfo != null) {
			throw CommonException.CreateException(CommonExceptionMessage.ALREADY_EXISTS, "Username");
		}
		UserInfo infoPassword = userInfoRepository.findByPassword(passwordEncrypt.encodePassword(userInfoRequest.getPassword()));
		if (infoPassword != null) {
			throw CommonException.CreateException(CommonExceptionMessage.ALREADY_EXISTS, "Password");
		}
		//user mobile
//		UserInfo usermobile = userInfoRepository.findByMobile(userInfoRequest.getMobile());
//		if (usermobile != null) {
//			throw CommonException.CreateException(CommonExceptionMessage.ALREADY_EXISTS, "mobile");
//		}
// 
//		//user email
//		UserInfo infoMail = userInfoRepository.findByEmail(userInfoRequest.getEmail());
//		if (infoMail != null) {
//			throw CommonException.CreateException(CommonExceptionMessage.ALREADY_EXISTS, "email");
//		}
		
		 UUID uuid = UUID.randomUUID();
		 Long longValue = uuidToLong(uuid);
		
		userInfo = new UserInfo();
		userInfo.setId(longValue);
		userInfo.setRole(role);
		userInfo.setUsername(userInfoRequest.getUsername());
		userInfo.setPassword(passwordEncrypt.encodePassword(userInfoRequest.getPassword()));
		userInfo.setCreate_time(LocalDate.now());
		userInfo.setActive(true);
		userInfo.setFirstName(userInfoRequest.getFirstName());
		userInfo.setLastName(userInfoRequest.getLastName());
		userInfo.setEmail(userInfoRequest.getEmail());
		userInfo.setMobile(userInfoRequest.getMobile());
		userInfo.setPassLength(userInfoRequest.getPassLength());
		if(userInfoRequest.getLoggedUserId()!= null) {
		Optional<UserInfo> optionalAdminUser = userInfoRepository.findById(userInfoRequest.getLoggedUserId());
		if (optionalAdminUser.isPresent()) {
			UserInfo dbUser = optionalAdminUser.get();
			if (dbUser != null) {
				if (dbUser.getStoreInfo() != null) {
					userInfo.setStoreInfo(dbUser.getStoreInfo());
				}
			}
		}
		}
		
		userInfoRepository.save(userInfo);
		
		
	//server	
		
		Role role1 = remote_roleRepository.findByName(userInfoRequest.getRole());

		UserInfo userInfo1 = remote_userInfoRepository.findByUsername(userInfoRequest.getUsername());
		if (userInfo1 != null) {
			throw CommonException.CreateException(CommonExceptionMessage.ALREADY_EXISTS, "Username");
		}
		UserInfo infoPassword1 = remote_userInfoRepository.findByPassword(passwordEncrypt.encodePassword(userInfoRequest.getPassword()));
		if (infoPassword1 != null) {
			throw CommonException.CreateException(CommonExceptionMessage.ALREADY_EXISTS, "Password");
		}

		
		userInfo1 = new UserInfo();
		userInfo1.setId(longValue);
		userInfo1.setRole(role1);
		userInfo1.setUsername(userInfoRequest.getUsername());
		userInfo1.setPassword(passwordEncrypt.encodePassword(userInfoRequest.getPassword()));
		userInfo1.setCreate_time(LocalDate.now());
		userInfo1.setActive(true);
		userInfo1.setFirstName(userInfoRequest.getFirstName());
		userInfo1.setLastName(userInfoRequest.getLastName());
		userInfo1.setEmail(userInfoRequest.getEmail());
		userInfo1.setMobile(userInfoRequest.getMobile());
		userInfo1.setPassLength(userInfoRequest.getPassLength());
		if(userInfoRequest.getLoggedUserId()!= null) {
		Optional<UserInfo> optionalAdminUser1 = remote_userInfoRepository.findById(userInfoRequest.getLoggedUserId());
		if (optionalAdminUser1.isPresent()) {
			UserInfo dbUser1 = optionalAdminUser1.get();
			if (dbUser1 != null) {
				if (dbUser1.getStoreInfo() != null) {
					userInfo1.setStoreInfo(dbUser1.getStoreInfo());
				}
			}
		}
		}
		
		remote_userInfoRepository.save(userInfo1);
		
	}
	
	   public static long uuidToLong(UUID uuid) {
	        long mostSigBits = uuid.getMostSignificantBits();
	        long leastSigBits = uuid.getLeastSignificantBits();
	        long combinedValue = mostSigBits ^ leastSigBits;
	        return combinedValue % 1000000000000000L;//  last 15 digits
	    }
	
	
	//sync
//    @Transactional
//    public void syncData() {
//        List<UserInfo> locals = (List<UserInfo>) remote_userInfoRepository.findAll();
//
//        for (UserInfo local : locals) {
//                 
//        	UserInfo userInfo = userInfoRepository.findByUsername(local.getUsername());
//    		System.out.println("1");
//        
//    		System.out.println("2");
//    		 userInfo = userInfoRepository.findByPassword(local.getPassword());
//    	
//        	
//            if (userInfo != null) {
//        		System.out.println("3");
//
//            } else {
//        		System.out.println("4");
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
//       		
//            	userInfoRepository.save(userInfo);
//            }
//        }
//    }
//
//    @Scheduled(fixedRate =120000)
//    public void performDataSync() {
//        this.syncData();
//    }	
	
	

	public List<UserInfoResponse> findAllUser() {
		// TODO Auto-generated method stub
		List<UserInfo> users = (List<UserInfo>) userInfoRepository.findAll();

		List<UserInfoResponse> infoResponses = new ArrayList<UserInfoResponse>();

		for (UserInfo userInfo : users) {
			if (userInfo.getStoreInfo() !=null) {
			infoResponses.add(new UserInfoResponse(userInfo.getId(), userInfo.getUsername(), userInfo.getPassword(),
					userInfo.getRole().getName(), userInfo.isActive(),userInfo.getFirstName(),userInfo.getLastName(),userInfo.getEmail(),userInfo.getMobile(),
					userInfo.getStoreInfo().getStoreName()));
		}
		if (userInfo.getStoreInfo() ==null) {
		infoResponses.add(new UserInfoResponse(userInfo.getId(), userInfo.getUsername(), userInfo.getPassword(),
				userInfo.getRole().getName(), userInfo.isActive(),userInfo.getFirstName(),userInfo.getLastName(),userInfo.getEmail(),userInfo.getMobile()
				));
		}
	}
			return infoResponses;
	}

	public void deleteByUser(Long userId) {
		userInfoRepository.deleteById(userId);
	}
	

	public void deleteByUserD(Long days) {
		List<UserInfo> users = (List<UserInfo>) userInfoRepository.findAll();

		for (UserInfo userInfo : users) {
			
			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
			String inputString1 = myFormat.format(userInfo.getLastLoginDate());
					
			
			Date today = Calendar.getInstance().getTime();   
			String inputString2 = myFormat.format(today);

			try {
			    Date date1 = myFormat.parse(inputString1);
			    Date date2 = myFormat.parse(inputString2);
			    long diff = date2.getTime() - date1.getTime();
			    long d=TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
			    if(d>=days) {			    	
			    	userInfoRepository.deleteById(userInfo.getId());
			    }
			} catch (ParseException e) {
			    e.printStackTrace();
			}
			}
		
	}

	public UserInfoResponse doLogin(UserInfoRequest infoRequest) {

		UserInfo userInfo = userInfoRepository.findByUsernameAndPassword(infoRequest.getUsername(),passwordEncrypt.encodePassword(infoRequest.getPassword()));
		if (userInfo == null) {
			throw CommonException.CreateException(CommonExceptionMessage.INCORRECT_UserNameAndPassword);
		}
		if (!userInfo.checkWebModule(infoRequest.getFeature())) {
			throw CommonException.CreateException(CommonExceptionMessage.PERMISSION_NOTEXISTS);
		}
		return new UserInfoResponse(userInfo.getId(), userInfo.getUsername(), userInfo.getPassword(),
				userInfo.getRole().getName(), userInfo.isActive(),userInfo.getFirstName(),userInfo.getLastName(),userInfo.getEmail(),userInfo.getMobile(),
				userInfo.getStoreInfo().getStoreName());
	}
	
	public UserInfoResponse doLoginkiosk(UserInfoRequest infoRequest) {

		UserInfo userInfo = userInfoRepository.findByPassword(passwordEncrypt.encodePassword(infoRequest.getPassword()));		
		if (userInfo == null) {
			throw CommonException.CreateException(CommonExceptionMessage.INCORRECT_PIN);
		}
		if (userInfo.getStoreInfo() == null) {
			throw CommonException.CreateException(CommonExceptionMessage.NOT_ASSIGN);
		}
		if (!userInfo.checkfeature(infoRequest.getFeature())) {
			throw CommonException.CreateException(CommonExceptionMessage.PERMISSION_NOTEXISTS);
		}
		userInfo.setLastLoginDate(Calendar.getInstance().getTime());
		return new UserInfoResponse(userInfo.getId(), userInfo.getUsername(), userInfo.getPassword(),
				userInfo.getRole().getName(), userInfo.isActive(),userInfo.getFirstName(),userInfo.getLastName(),userInfo.getEmail(),userInfo.getMobile(),
				userInfo.getStoreInfo().getStoreName());
		
	
	}
	
	public UserInfoResponse updateUserForm(Long id) {
		UserInfo userInfo = userInfoRepository.findById(id).get();
		//System.out.println(userInfo);
		//System.out.println(userInfo.getEmail()+ " Here " + userInfo.getFirstName());
		
		UserInfoResponse info = new UserInfoResponse(userInfo.getId(), userInfo.getUsername(), userInfo.getPassword(),
				userInfo.getRole().getName(), userInfo.isActive(),userInfo.getFirstName(),userInfo.getLastName(),userInfo.getEmail(),userInfo.getMobile(),
				userInfo.getStoreInfo().getStoreName());
		//System.out.println(info.getFirstName()+ " " + info.getLastName());
		return info;
		
	}
	public void updateUser(UserInfoRequest userInfoRequest) {

		Role role = roleRepository.findByName(userInfoRequest.getRole());

		UserInfo userInfo = userInfoRepository.findByUsername(userInfoRequest.getUsername());
		if (userInfo != null && !userInfo.getId().equals(userInfoRequest.getId())) {
			throw CommonException.CreateException(CommonExceptionMessage.ALREADY_EXISTS, "Username");
		}
		UserInfo infoPassword = userInfoRepository.findByPassword(userInfoRequest.getPassword());
		if (infoPassword != null && !infoPassword.getId().equals(userInfoRequest.getId())) {
			throw CommonException.CreateException(CommonExceptionMessage.ALREADY_EXISTS, "Password");
		}

		UserInfo info = userInfoRepository.findById(userInfoRequest.getId()).get();

		info.setPassword(userInfoRequest.getPassword());
		info.setUsername(userInfoRequest.getUsername());
		info.setRole(role);
		info.setActive(userInfoRequest.isActive());
		info.setFirstName(userInfoRequest.getFirstName());
		info.setLastName(userInfoRequest.getLastName());
		info.setEmail(userInfoRequest.getEmail());
		info.setMobile(userInfoRequest.getMobile());
		userInfoRepository.save(info);
		
		

	}

	public List<UserInfoResponse> getUserPaginations(int page, int size) {
		Page<UserInfo> userPage = userInfoRepository.findAll(PageRequest.of(page, size));
		List<UserInfo> users = userPage.getContent();
		List<UserInfoResponse> infoResponses = new ArrayList<UserInfoResponse>();
		for (UserInfo userInfo : users) {
			infoResponses.add(new UserInfoResponse(userInfo.getId(), userInfo.getUsername(), userInfo.getPassword(),
					userInfo.getRole().getName(), userInfo.isActive()));
		}
		return infoResponses;
	}

//	public List<UserInfoResponse> findUsersByRole(String role) {
//		List<UserInfo> users = userInfoRepository.findByRole_Name(role);
//		List<UserInfoResponse> infoResponses = new ArrayList<UserInfoResponse>();
//		for (UserInfo userInfo : users) {
//			infoResponses.add(new UserInfoResponse(userInfo.getId(), userInfo.getUsername(), userInfo.getPassword(),
//					userInfo.getRole().getName(), userInfo.isActive()));
//		}
//		return infoResponses;
//	}
	
	public List<UserInfoResponse> findUsersByRole(String role) {
		List<UserInfo> users = userInfoRepository.findByRole_Name(role);
		List<UserInfoResponse> infoResponses = new ArrayList<UserInfoResponse>();
		for (UserInfo userInfo : users) {
			if (userInfo.getStoreInfo() !=null) {
			infoResponses.add(new UserInfoResponse(userInfo.getId(), userInfo.getUsername(), userInfo.getPassword(),
					userInfo.getRole().getName(), userInfo.isActive()));
		}
		}
		return infoResponses;
	}
	
//	List<Long> userIds = StoreInfoResponse.getUserIds();
//	UserInfo user;
//	for(Long userId : userIds) {
//		user = userInfoRepository.findById(userId).get();

	public List<UserInfoResponse> findUserbyRoles(RolesDto rolesDto) {
		List<UserInfo> users = userInfoRepository.findByRole_NameIn(rolesDto.getRoles());
		List<UserInfoResponse> infoResponses = new ArrayList<UserInfoResponse>();
		for (UserInfo userInfo : users) {
			infoResponses.add(new UserInfoResponse(userInfo.getId(), userInfo.getUsername(), userInfo.getPassword(),
					userInfo.getRole().getName(), userInfo.isActive()));
		}
		return infoResponses;
	}
	
	public List<UserInfoResponse> findUserbyRolesss(RolesDto rolesDto,String storeInfo) {
		List<UserInfo> users = userInfoRepository.findByStoreInfo_StoreNameAndRole_NameIn(storeInfo,rolesDto.getRoles());
		List<UserInfoResponse> infoResponses = new ArrayList<UserInfoResponse>();
		for (UserInfo userInfo : users) {
			infoResponses.add(new UserInfoResponse(userInfo.getId(), userInfo.getUsername(), userInfo.getPassword(),
					userInfo.getRole().getName(), userInfo.isActive()));
		}
		return infoResponses;
	}

	public List<RoleDto> findAllRoles() {
		// TODO Auto-generated method stub
		List<RoleDto> response = new ArrayList<RoleDto>();
		List<Role> roles = (List<Role>) roleRepository.findAll();
		if (roles != null) {
			for (Role role : roles) {
				RoleDto roleDto = new RoleDto();
				roleDto.setDescription(role.getDescription());
				roleDto.setName(role.getName());
				roleDto.setId(role.getId());
				response.add(roleDto);
			}
		}
		return response;
	}

	public void promoteUser(Long userId, RoleDto roleDto) {
		UserInfo userInfo = userInfoRepository.findById(userId).get();

		Role role = roleRepository.findById(roleDto.getId()).get();
		userInfo.setRole(role);
		userInfoRepository.save(userInfo);

	}

	public List<UserInfoResponse> findUnassignedUserbyRoles(RolesDto rolesDto) {
		List<UserInfo> users = userInfoRepository.findByRole_NameIn(rolesDto.getRoles());
		List<UserInfoResponse> infoResponses = new ArrayList<UserInfoResponse>();
		for (UserInfo userInfo : users) {
			if (userInfo != null && userInfo.getStoreInfo() == null) {
				infoResponses.add(new UserInfoResponse(userInfo.getId(), userInfo.getUsername(), userInfo.getPassword(),
						userInfo.getRole().getName(), userInfo.isActive()));
			}
		}
		return infoResponses;
	}

	public List<UserInfoResponse> findunassignedUsersByRole(String role) {
		List<UserInfo> users = userInfoRepository.findByRole_Name(role);
		List<UserInfoResponse> infoResponses = new ArrayList<UserInfoResponse>();
		for (UserInfo userInfo : users) {
			if (userInfo != null && userInfo.getStoreInfo() == null) {
				infoResponses.add(new UserInfoResponse(userInfo.getId(), userInfo.getUsername(), userInfo.getPassword(),
						userInfo.getRole().getName(), userInfo.isActive()));
			}
		}
		return infoResponses;
	}
	
	// Store to user
	public List<UserInfo> findByStoreInfo_Id(Long id){
		System.out.println("findbystoreinfo_id in user table");
		return userInfoRepository.findByStoreInfo_Id(id);
	}
	
	public List<UserInfoResponse> findUsersByStore(String storeInfo) {
	List<UserInfo> users = userInfoRepository.findByStoreInfo_StoreName(storeInfo);
	List<UserInfoResponse> infoResponses = new ArrayList<UserInfoResponse>();
	for (UserInfo userInfo : users) {
		if (userInfo.getStoreInfo() !=null) {
		infoResponses.add(new UserInfoResponse(userInfo.getId(), userInfo.getUsername(), userInfo.getPassword(),
				userInfo.getStoreInfo().getStoreName(),userInfo.getStoreInfo().getAddress(),userInfo.getRole().getName(),userInfo.isActive()));
	}
	}
	return infoResponses;
 }
	
	public List<UserInfoResponse> findUsersByStore(String storeInfo,String role) {
		List<UserInfo> users = userInfoRepository.findByStoreInfo_StoreNameAndRole_Name(storeInfo,role);
		List<UserInfoResponse> infoResponses = new ArrayList<UserInfoResponse>();
		for (UserInfo userInfo : users) {
			if (userInfo.getStoreInfo() !=null) {
			infoResponses.add(new UserInfoResponse(userInfo.getId(), userInfo.getUsername(), userInfo.getPassword(),
					userInfo.getStoreInfo().getStoreName(),userInfo.getStoreInfo().getAddress(),userInfo.getRole().getName(), userInfo.isActive()));
		}
		}
		return infoResponses;
	}

	public List<UserInfo> getAllUsers(Long id) {
		System.out.println("----  we are in getAllUsers() methode in UserService");
		StoreInfo storeInfo=new StoreInfo();
		     storeInfo.setId(id);
		     System.out.println("the store is "+id);
		    		List<UserInfo> resultInfos =  userInfoRepository.findByStoreInfo(storeInfo);
		    		System.out.println("the size of users fethed is: "+resultInfos.size());
		    		
		    		return resultInfos;
		
		//return userInfoRepository.getAllUsers(id);
			}

	public UserInfo getUserInfo(Long userids) {
		         System.out.println("we are in user service");
               //Optional<UserInfo> userinfo= userInfoRepository.findById(userids).get();
               UserInfo userinfo=userInfoRepository.findById(userids).get();
              // System.out.println(userinfo);
//               UserInfo userInfo2=new UserInfo();
//               
//               if(userinfo.isPresent())
//               {
//            	   userInfo2=userinfo.get();
//            	   
//               }
		      
		       
		return userinfo;
	}

//	public RoleConunt getAllRolesCount(Long storeid) {
//		
//		return userInfoRepository.getAllRolesCount(storeid);
//	}
	

	public void changePassword(@PathVariable("oldPassword") String oldPassword, @PathVariable("newPassword") String newPassword) {
     
		UserInfo userInfo = userInfoRepository.findByPassword(passwordEncrypt.encodePassword(oldPassword));		
		if (userInfo == null) {
			throw CommonException.CreateException(CommonExceptionMessage.INCORRECT_PIN);
		}
		
		userInfo.setPassword(passwordEncrypt.encodePassword(newPassword));

		userInfoRepository.save(userInfo);
		
	}

	public List<KioskResponse> usertokiosk(Long userId) {
		
	UserInfo userInfo = userInfoRepository.findById(userId).get();

	List<Kiosk> kioskInfo = kioskRepository.findByStoreinfok_Id(userInfo.getStoreInfo().getId());
	
	List<KioskResponse> infoResponses = new ArrayList<KioskResponse>();

	for (Kiosk kiosk : kioskInfo) {

		infoResponses.add(new KioskResponse(kiosk.getId(),kiosk.getKioskId(),kiosk.getKioskName(),kiosk.getBrandName(),
				kiosk.getModelName(),kiosk.getCpu(),kiosk.getHdd(),kiosk.getRamMemory(),kiosk.getScreenSize(),
				kiosk.getIpAddress(),kiosk.getMacAddress(),kiosk.isActive()));
		
	}	
	return 	infoResponses;
	}

		
}
