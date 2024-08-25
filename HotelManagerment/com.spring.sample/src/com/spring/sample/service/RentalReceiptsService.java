package com.spring.sample.service;

import java.util.List;

import com.spring.sample.entity.RentalReceipts;

public interface RentalReceiptsService {
	
	List<RentalReceipts> getAllRoomBills();
	
	void save(RentalReceipts newReceipt);
	
//	List<RentalReceipts> getCustomerInRoomBill();
}
