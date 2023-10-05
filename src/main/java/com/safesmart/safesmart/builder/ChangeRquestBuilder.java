package com.safesmart.safesmart.builder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.safesmart.safesmart.dto.ChangeValetDenominationsDto;
import com.safesmart.safesmart.model.ChangeValetDenominations;

@Component
public class ChangeRquestBuilder {

	public ChangeValetDenominations convertToModel(ChangeValetDenominationsDto changeValetDenominationsDto) {

		ChangeValetDenominations changeValetDenominations = new ChangeValetDenominations();

		changeValetDenominations.setNew_cents(changeValetDenominationsDto.getNew_cents());
		changeValetDenominations.setNew_den_1$(changeValetDenominationsDto.getNew_den_1$());
		changeValetDenominations.setNew_den_10$(changeValetDenominationsDto.getNew_den_10$());
		changeValetDenominations.setNew_den_100$(changeValetDenominationsDto.getNew_den_100$());
		changeValetDenominations.setNew_den_20$(changeValetDenominationsDto.getNew_den_20$());
		changeValetDenominations.setNew_den_5$(changeValetDenominationsDto.getNew_den_5$());
		changeValetDenominations.setNew_den_50$(changeValetDenominationsDto.getNew_den_50$());

		changeValetDenominations.setNew_dimes(changeValetDenominationsDto.getNew_dimes());
		changeValetDenominations.setNew_nickels(changeValetDenominationsDto.getNew_nickels());
		changeValetDenominations.setNew_quarters(changeValetDenominationsDto.getNew_quarters());
		
		changeValetDenominations.setOld_cents(changeValetDenominationsDto.getOld_cents());
		changeValetDenominations.setOld_den_1$(changeValetDenominationsDto.getOld_den_1$());
		changeValetDenominations.setOld_den_10$(changeValetDenominationsDto.getOld_den_10$());
		changeValetDenominations.setOld_den_100$(changeValetDenominationsDto.getOld_den_100$());
		changeValetDenominations.setOld_den_20$(changeValetDenominationsDto.getOld_den_20$());
		changeValetDenominations.setOld_den_5$(changeValetDenominationsDto.getOld_den_5$());
		changeValetDenominations.setOld_den_50$(changeValetDenominationsDto.getOld_den_50$());

		changeValetDenominations.setOld_dimes(changeValetDenominationsDto.getOld_dimes());
		changeValetDenominations.setOld_nickels(changeValetDenominationsDto.getOld_nickels());
		changeValetDenominations.setOld_quarters(changeValetDenominationsDto.getOld_quarters());
		changeValetDenominations.setType(changeValetDenominationsDto.getType());
		changeValetDenominations.setUpdatedOn(LocalDateTime.now());

		return changeValetDenominations;
	}

	public List<ChangeValetDenominationsDto> convertToDtosList(List<ChangeValetDenominations> list) {

		List<ChangeValetDenominationsDto> finalResponse = new ArrayList<ChangeValetDenominationsDto>();
		for (ChangeValetDenominations changeValetDenominations : list) {
			finalResponse.add(createDto(changeValetDenominations));
		}

		return finalResponse;
	}

	private ChangeValetDenominationsDto createDto(ChangeValetDenominations changeValetDenominations) {

		ChangeValetDenominationsDto changeValetDenominationsDto = new ChangeValetDenominationsDto();
		changeValetDenominationsDto.setId(changeValetDenominations.getId());
		changeValetDenominationsDto.setNew_cents(changeValetDenominations.getNew_cents());
		changeValetDenominationsDto.setNew_den_1$(changeValetDenominations.getNew_den_1$());
		changeValetDenominationsDto.setNew_den_10$(changeValetDenominations.getNew_den_10$());
		changeValetDenominationsDto.setNew_den_100$(changeValetDenominations.getNew_den_100$());
		changeValetDenominationsDto.setNew_den_20$(changeValetDenominations.getNew_den_20$());
		changeValetDenominationsDto.setNew_den_5$(changeValetDenominations.getNew_den_5$());

		changeValetDenominationsDto.setNew_den_50$(changeValetDenominations.getNew_den_50$());
		changeValetDenominationsDto.setNew_dimes(changeValetDenominations.getNew_dimes());
		changeValetDenominationsDto.setNew_nickels(changeValetDenominations.getNew_nickels());
		changeValetDenominationsDto.setNew_quarters(changeValetDenominations.getNew_quarters());
		
		changeValetDenominationsDto.setOld_cents(changeValetDenominations.getOld_cents());
		changeValetDenominationsDto.setOld_den_1$(changeValetDenominations.getOld_den_1$());
		changeValetDenominationsDto.setOld_den_10$(changeValetDenominations.getOld_den_10$());
		changeValetDenominationsDto.setOld_den_100$(changeValetDenominations.getOld_den_100$());

		changeValetDenominationsDto.setOld_den_20$(changeValetDenominations.getOld_den_20$());
		changeValetDenominationsDto.setOld_den_5$(changeValetDenominations.getOld_den_5$());
		changeValetDenominationsDto.setOld_den_50$(changeValetDenominations.getOld_den_50$());

		changeValetDenominationsDto.setOld_dimes(changeValetDenominations.getOld_dimes());
		changeValetDenominationsDto.setOld_nickels(changeValetDenominations.getOld_nickels());
		changeValetDenominationsDto.setOld_quarters(changeValetDenominations.getOld_quarters());

		changeValetDenominationsDto.setType(changeValetDenominations.getType());
		changeValetDenominationsDto.setUpdatedTime(changeValetDenominations.getUpdatedOn().toString());

		changeValetDenominationsDto.setUpdatedByUser(
				changeValetDenominations.getUpdatedBy() != null ? changeValetDenominations.getUpdatedBy().getId()
						: null);
		changeValetDenominationsDto.setValetDenominationsId(changeValetDenominations.getValetDenominations() != null
				? changeValetDenominations.getValetDenominations().getId()
				: null);


		return changeValetDenominationsDto;
	}

}
