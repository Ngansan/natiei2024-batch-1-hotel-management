package com.spring.sample.service.imp;

import java.util.List;

import com.spring.sample.entity.RegisteredGuests;
import com.spring.sample.service.RegisteredGuestsService;

public class RegisteredGuestsServiceImp extends BaseServiceImpl implements RegisteredGuestsService{
	private RegisteredGuestsServiceImp() {
	}
	
	@Override
	public List<RegisteredGuests> getAllRegisteredGuests(){
		return registeredGuestsDAO.getAllRegisteredGuests();
	}

}
