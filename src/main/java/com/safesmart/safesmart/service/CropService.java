package com.safesmart.safesmart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.safesmart.safesmart.common.CommonException;
import com.safesmart.safesmart.common.CommonExceptionMessage;
import com.safesmart.safesmart.dto.CropRequest;
import com.safesmart.safesmart.dto.CropResponse;
import com.safesmart.safesmart.dto.PrinterRequest;
import com.safesmart.safesmart.dto.PrinterResponse;
import com.safesmart.safesmart.model.Crop;
import com.safesmart.safesmart.model.Printer;
import com.safesmart.safesmart.remoterepository.Remote_CropRepository;
import com.safesmart.safesmart.remoterepository.Remote_PrinterRepository;
import com.safesmart.safesmart.repository.CropRepository;
import com.safesmart.safesmart.repository.PrinterRepository;

@Service
@Transactional
public class CropService {
	@Autowired
	private CropRepository cropRepository;
	@Autowired
	private Remote_CropRepository remote_CropRepository;
	
	public void add(CropRequest cropRequest) {

		Crop crop = cropRepository.findByCropName(cropRequest.getCropName());
		if (crop != null) {
			throw CommonException.CreateException(CommonExceptionMessage.ALREADY_EXISTS, "CropName");
		}

		 UUID uuid = UUID.randomUUID();
		 Long longValue = uuidToLong(uuid);
		
	    crop = new Crop();
	    crop.setId(longValue);
	    crop.setCropName(cropRequest.getCropName());
	    crop.setDescription(cropRequest.getDescription());
	    crop.setStatus(cropRequest.getStatus());
	    
	    cropRepository.save(crop);
		
		//server
	    Crop crop1 = remote_CropRepository.findByCropName(cropRequest.getCropName());
		if (crop1 != null) {
			throw CommonException.CreateException(CommonExceptionMessage.ALREADY_EXISTS, "CropName");
		}
        crop1 = new Crop();
	    crop1.setId(longValue);
	    crop1.setCropName(cropRequest.getCropName());
	    crop1.setDescription(cropRequest.getDescription());
	    crop1.setStatus(cropRequest.getStatus());
	    
	    remote_CropRepository.save(crop1);
	}
	
	   public static long uuidToLong(UUID uuid) {
	        long mostSigBits = uuid.getMostSignificantBits();
	        long leastSigBits = uuid.getLeastSignificantBits();
	        long combinedValue = mostSigBits ^ leastSigBits;
	        return combinedValue % 1000000000000000L;//  last 15 digits
	    }
	   
	   
	   public List<CropResponse> findAllUser() {
			// TODO Auto-generated method stub
			List<Crop> crops = (List<Crop>) cropRepository.findAll();

			List<CropResponse> cropResponses = new ArrayList<CropResponse>();
			for (Crop crop :crops) {
				cropResponses.add(new CropResponse(crop.getId(),crop.getCropName(),crop.getDescription(),crop.getStatus()));
			}
			return cropResponses;
		}
	   
	   public void deleteByCrop(Long Id) {
			cropRepository.deleteById(Id);
		}
	   
	   
	   public void updateCrop(CropRequest cropRequest) {


			Crop crop = cropRepository.findById(cropRequest.getId()).get();
            crop.setCropName(cropRequest.getCropName());
			crop.setDescription(cropRequest.getDescription());
			crop.setStatus(cropRequest.getStatus());
			cropRepository.save(crop);

		}
	

	

}
