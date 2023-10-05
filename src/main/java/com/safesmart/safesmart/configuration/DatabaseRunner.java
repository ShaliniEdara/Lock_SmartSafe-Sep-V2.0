package com.safesmart.safesmart.configuration;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.safesmart.safesmart.model.Role;
import com.safesmart.safesmart.model.SequenceInfo;
import com.safesmart.safesmart.model.StoreInfo;
import com.safesmart.safesmart.model.UserInfo;
import com.safesmart.safesmart.remoterepository.Remote_RoleRepository;
import com.safesmart.safesmart.remoterepository.Remote_SequenceInfoRepository;
import com.safesmart.safesmart.remoterepository.Remote_StoreInfoRepository;
import com.safesmart.safesmart.remoterepository.Remote_UserInfoRepository;
import com.safesmart.safesmart.repository.RoleRepository;
import com.safesmart.safesmart.repository.SequenceInfoRepository;
import com.safesmart.safesmart.repository.StoreInfoRepository;
import com.safesmart.safesmart.repository.UserInfoRepository;
import com.safesmart.safesmart.util.Base64BasicEncryption;

@Component
public class DatabaseRunner implements CommandLineRunner {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private SequenceInfoRepository sequenceInfoRepository;

	@Autowired
	private StoreInfoRepository storeInfoRepository;

	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@Autowired
	private Base64BasicEncryption passwordEncrypt;
	
	
	
	@Autowired
	private Remote_RoleRepository remote_roleRepository;

	@Autowired
	private Remote_SequenceInfoRepository remote_sequenceInfoRepository;

	@Autowired
	private Remote_StoreInfoRepository remote_storeInfoRepository;

	@Autowired
	private Remote_UserInfoRepository remote_userInfoRepository;
	

	@Override
	public void run(String... args) throws Exception {

		List<Role> roles = (List<Role>) roleRepository.findAll();
		if (roles == null || roles.isEmpty()) {

			List<String> all = new ArrayList<String>();
			all.add("All");

			Role adminRole = new Role();
			adminRole.setId(1L);
			adminRole.setName("ADMIN");
			adminRole.setDescription("Administrator");
			adminRole.setFeatures(all);
			adminRole.setWebModule(all);

			List<String> insertBills = new ArrayList<String>();
			insertBills.add("InsertBills");
			Role employeRole = new Role();
			employeRole.setId(2L);
			employeRole.setName("EMPLOYEE");
			employeRole.setDescription("Employeee");
			employeRole.setFeatures(insertBills);

			Role manager = new Role();
			manager.setId(3L);
			manager.setName("MANAGER");
			manager.setDescription("manager");
			manager.setFeatures(all);
			manager.setWebModule(all);

			Role shiftmanager = new Role();
			shiftmanager.setId(4L);
			shiftmanager.setName("SHIFTMANAGER");
			shiftmanager.setDescription("Shift Manager");

			List<String> shiftFeatures = new ArrayList<String>();
			shiftFeatures.add("InsertBills");
			shiftFeatures.add("Admin");
			shiftFeatures.add("Doors");
			shiftFeatures.add("ChangeRequestDoors");
			shiftFeatures.add("StandBank");
			shiftmanager.setFeatures(shiftFeatures);

			Role truck = new Role();
			truck.setId(5L);
			truck.setName("TRUCK");
			truck.setDescription("Truck");
			List<String> truckFeatures = new ArrayList<String>();
			truckFeatures.add("OTPScreen");
			truckFeatures.add("Valut");
			truck.setFeatures(truckFeatures);
			
			Role owner = new Role();
			owner.setId(6L);
			owner.setName("OWNER");
			owner.setDescription("owner");
			owner.setFeatures(all);
			owner.setWebModule(all);
			
			Role store_admin = new Role();
			store_admin.setId(7L);
			store_admin.setName("STORE_ADMIN");
			store_admin.setDescription("store_admin");
			store_admin.setFeatures(all);
			store_admin.setWebModule(all);
			
			Role super_admin = new Role();
			super_admin.setId(8L);
			super_admin.setName("SUPER_ADMIN");
			super_admin.setDescription("super_admin");
			super_admin.setFeatures(all);
			super_admin.setWebModule(all);
			
			Role director_of_operation = new Role();
			director_of_operation.setId(9L);
			director_of_operation.setName("DIRECTOR_OF_OPERATION");
			director_of_operation.setDescription("director_of_operation");
			director_of_operation.setFeatures(all);
			director_of_operation.setWebModule(all);

			roles = new ArrayList<Role>();
			roles.add(adminRole);
			roles.add(employeRole);
			roles.add(manager);
			roles.add(shiftmanager);
			roles.add(truck);
			roles.add(owner);
			roles.add(store_admin);
			roles.add(super_admin);
			roles.add(director_of_operation);

			roleRepository.saveAll(roles);

			SequenceInfo sequenceInfo = new SequenceInfo();
			sequenceInfo.setId(10L);
			sequenceInfo.setName("TRANSACTIONNO");
			sequenceInfo.setValue(1);
			sequenceInfoRepository.save(sequenceInfo);

			StoreInfo storeInfo = new StoreInfo();
			storeInfo.setId(11L);
			storeInfo.setSerialNumber("UT0");
			storeInfo.setCorpStoreNo("ABC");
			storeInfo.setStoreName("Default_Store");
			storeInfo.setMinimumBalance(1000.0);
			storeInfo.setStartTime(LocalTime.now());
			storeInfo.setEndTime(LocalTime.NOON);
			storeInfo.setStatus(true);

			storeInfoRepository.save(storeInfo);

			UserInfo userInfo = new UserInfo();
			userInfo.setId(12L);
			userInfo.setUsername("Admin");
			userInfo.setPassword(passwordEncrypt.encodePassword("1234"));
			userInfo.setRole(roleRepository.findByName("ADMIN"));
			userInfo.setStoreInfo(storeInfoRepository.findByStoreName("Default_Store"));
			userInfoRepository.save(userInfo);

			

		}
		
//		server
		
		List<Role> roles1 = (List<Role>) remote_roleRepository.findAll();
		if (roles1 == null || roles1.isEmpty()) {

			List<String> all = new ArrayList<String>();
			all.add("All");

			Role adminRole = new Role();
			adminRole.setId(1L);
			adminRole.setName("ADMIN");
			adminRole.setDescription("Administrator");
			adminRole.setFeatures(all);
			adminRole.setWebModule(all);

			List<String> insertBills = new ArrayList<String>();
			insertBills.add("InsertBills");
			Role employeRole = new Role();
			employeRole.setId(2L);
			employeRole.setName("EMPLOYEE");
			employeRole.setDescription("Employeee");
			employeRole.setFeatures(insertBills);

			Role manager = new Role();
			manager.setId(3L);
			manager.setName("MANAGER");
			manager.setDescription("manager");
			manager.setFeatures(all);
			manager.setWebModule(all);

			Role shiftmanager = new Role();
			shiftmanager.setId(4L);
			shiftmanager.setName("SHIFTMANAGER");
			shiftmanager.setDescription("Shift Manager");

			List<String> shiftFeatures = new ArrayList<String>();
			shiftFeatures.add("InsertBills");
			shiftFeatures.add("Admin");
			shiftFeatures.add("Doors");
			shiftFeatures.add("ChangeRequestDoors");
			shiftFeatures.add("StandBank");
			shiftmanager.setFeatures(shiftFeatures);

			Role truck = new Role();
			truck.setId(5L);
			truck.setName("TRUCK");
			truck.setDescription("Truck");
			List<String> truckFeatures = new ArrayList<String>();
			truckFeatures.add("OTPScreen");
			truckFeatures.add("Valut");
			truck.setFeatures(truckFeatures);
			
			Role owner = new Role();
			owner.setId(6L);
			owner.setName("OWNER");
			owner.setDescription("owner");
			owner.setFeatures(all);
			owner.setWebModule(all);
			
			Role store_admin = new Role();
			store_admin.setId(7L);
			store_admin.setName("STORE_ADMIN");
			store_admin.setDescription("store_admin");
			store_admin.setFeatures(all);
			store_admin.setWebModule(all);
			
			Role super_admin = new Role();
			super_admin.setId(8L);
			super_admin.setName("SUPER_ADMIN");
			super_admin.setDescription("super_admin");
			super_admin.setFeatures(all);
			super_admin.setWebModule(all);
			
			Role director_of_operation = new Role();
			director_of_operation.setId(9L);
			director_of_operation.setName("DIRECTOR_OF_OPERATION");
			director_of_operation.setDescription("director_of_operation");
			director_of_operation.setFeatures(all);
			director_of_operation.setWebModule(all);

			roles1 = new ArrayList<Role>();
			roles1.add(adminRole);
			roles1.add(employeRole);
			roles1.add(manager);
			roles1.add(shiftmanager);
			roles1.add(truck);
			roles1.add(store_admin);
			roles1.add(super_admin);
			roles1.add(director_of_operation);

			remote_roleRepository.saveAll(roles1);

			SequenceInfo sequenceInfo = new SequenceInfo();
			sequenceInfo.setId(10L);
			sequenceInfo.setName("TRANSACTIONNO");
			sequenceInfo.setValue(1);
			remote_sequenceInfoRepository.save(sequenceInfo);

			StoreInfo storeInfo = new StoreInfo();
			storeInfo.setId(11L);
			storeInfo.setSerialNumber("UT0");
			storeInfo.setCorpStoreNo("ABC");
			storeInfo.setStoreName("XYZ");
			storeInfo.setStartTime(LocalTime.now());
			storeInfo.setEndTime(LocalTime.NOON);
			storeInfo.setStatus(true);

			remote_storeInfoRepository.save(storeInfo);

			UserInfo userInfo = new UserInfo();
			userInfo.setId(12L);
			userInfo.setUsername("Admin");
			userInfo.setPassword(passwordEncrypt.encodePassword("1234"));
			userInfo.setRole(remote_roleRepository.findByName("ADMIN"));
			userInfo.setStoreInfo(remote_storeInfoRepository.findByStoreName("XYZ"));
			remote_userInfoRepository.save(userInfo);

			

		}
		
		
	}


}
