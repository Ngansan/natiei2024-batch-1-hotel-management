package com.spring.sample.service.imp;

import java.util.List;

import com.spring.sample.entity.GuestTypes;
import com.spring.sample.service.GuestTypesService;

public class GuestTypesServiceImp extends BaseServiceImpl implements GuestTypesService{
	
	private GuestTypesServiceImp() {
	}
	
	@Override
	public List<GuestTypes> guestTypesList() {
		return guestTypesDAO.guestTypesList();
	}

}
