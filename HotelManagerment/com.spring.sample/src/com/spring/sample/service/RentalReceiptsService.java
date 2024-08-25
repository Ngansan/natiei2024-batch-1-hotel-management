package com.spring.sample.service;

import java.time.LocalDateTime;
import java.util.List;

import com.spring.sample.entity.RentalReceipts;

public interface RentalReceiptsService {
	
	List<RentalReceipts> getAllRoomBills();
	
//	void createRentalReceipt(Integer id, String roomName, LocalDateTime checkInDateTime, LocalDateTime checkOutDateTime, String status);
	
	void save(RentalReceipts newReceipt);
	
//	List<RentalReceipts> getCustomerInRoomBill();
}
