package com.safesmart.safesmart.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.safesmart.safesmart.dto.EODReport;
import com.safesmart.safesmart.dto.InsertBillRequest;
import com.safesmart.safesmart.dto.InsertBillResponse;
import com.safesmart.safesmart.model.Dollar;
import com.safesmart.safesmart.model.InsertBill;
import com.safesmart.safesmart.model.SequenceInfo;
import com.safesmart.safesmart.model.UserInfo;
import com.safesmart.safesmart.remoterepository.Remote_InsertBillRepository;
import com.safesmart.safesmart.repository.InsertBillRepository;
import com.safesmart.safesmart.repository.SequenceInfoRepository;
import com.safesmart.safesmart.repository.UserInfoRepository;

@Service
@Transactional
public class InsertBillService {

	@Autowired
	private InsertBillRepository insertBillRepository;
	@Autowired
	private Remote_InsertBillRepository remote_insertBillRepository;

	@Autowired
	private UserInfoRepository userInfoRepository;

	@Autowired
	private SequenceInfoRepository sequenceInfoRepository;

	public void add(InsertBillRequest insertBillRequest) {

		 UUID uuid = UUID.randomUUID();
		 Long longValue = uuidToLong(uuid);
		 
		InsertBill insertBill = new InsertBill();
		insertBill.setId(longValue);
		insertBill.setAmount(insertBillRequest.getAmount());
		insertBillRepository.save(insertBill);
		
		//server
		InsertBill insertBill1 = new InsertBill();
		insertBill1.setId(longValue);
		insertBill1.setAmount(insertBillRequest.getAmount());
		remote_insertBillRepository.save(insertBill1);

	}
	
	   public static long uuidToLong(UUID uuid) {
	        long mostSigBits = uuid.getMostSignificantBits();
	        long leastSigBits = uuid.getLeastSignificantBits();
	        long combinedValue = mostSigBits ^ leastSigBits;
	        return combinedValue % 1000000000000000L;//  last 15 digits
	    }

	public Collection<InsertBillResponse> getIncompleteInsertBills() {

		List<InsertBill> insertBills = insertBillRepository.findByTransactionNumberIsNull();

		Map<String, InsertBillResponse> map = new HashMap<String, InsertBillResponse>();
		for (InsertBill bill : insertBills) {
			if (map.get(bill.getAmount()) != null) {
				InsertBillResponse insertBill = map.get(bill.getAmount());
				int count = insertBill.getCount() + 1;
				insertBill.setCount(count);
				map.put(bill.getAmount(), insertBill);
			} else {
				InsertBillResponse billResponse = new InsertBillResponse();
				billResponse.setAmount(bill.getAmount());
				billResponse.setCount(1);
				map.put(bill.getAmount(), billResponse);

			}

		}

		Collection<InsertBillResponse> result = new ArrayList<InsertBillResponse>();

		for (Dollar dollar : Dollar.values()) {
			if (map.get(dollar.getDollar()) != null) {
				InsertBillResponse billResponse =map.get(dollar.getDollar());
				billResponse.calculateSum(dollar.getValue());
				result.add(billResponse);
			} else {
				InsertBillResponse billResponse = new InsertBillResponse();
				billResponse.setAmount(dollar.getDollar());
				billResponse.setCount(0);
				billResponse.setSum(0);
				result.add(billResponse);
			}

		}

		return result;
	}

	public InsertBillResponse processInsertBills(Long userId) {

		List<InsertBill> insertBills = insertBillRepository.findByTransactionNumberIsNull();
		LocalDate localDate = LocalDate.now();
		UserInfo user = userInfoRepository.findById(userId).get();
		SequenceInfo sequenceInfo = sequenceInfoRepository.findByName("TRANSACTIONNO");
		String transactionNumber = sequenceInfo.increment();
		sequenceInfoRepository.save(sequenceInfo);

		for (InsertBill insertBill : insertBills) {
			insertBill.setUser(user);
			insertBill.setCreatedOn(localDate);
			insertBill.setTransactionNumber(transactionNumber);
			insertBill.setDateTime(LocalDateTime.now());
		}

		insertBillRepository.saveAll(insertBills);
		return new InsertBillResponse(transactionNumber);
	}

	public List<EODReport> endOfDayReport() {

		List<InsertBill> insertBills = insertBillRepository.findByCreatedOn(LocalDate.now());

		Map<String, InsertBillResponse> map = new HashMap<String, InsertBillResponse>();
		for (InsertBill bill : insertBills) {
			if (map.get(bill.getAmount()) != null) {
				InsertBillResponse insertBill = map.get(bill.getAmount());
				int count = insertBill.getCount() + 1;
				insertBill.setCount(count);
				map.put(bill.getAmount(), insertBill);
			} else {
				InsertBillResponse billResponse = new InsertBillResponse();
				billResponse.setAmount(bill.getAmount());
				billResponse.setCount(1);
				map.put(bill.getAmount(), billResponse);

			}

		}

		List<InsertBillResponse> responses = (List<InsertBillResponse>) map.values();

		return null;
	}

	public List<InsertBill> findByTransactionNumber(String formatedValue) {
		// TODO Auto-generated method stub
		return null;
	}

}
