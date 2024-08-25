package com.spring.sample.dao;

import java.time.LocalDateTime;
import java.util.List;

import com.spring.sample.entity.RentalReceipts;
import com.spring.sample.entity.Room;

public interface RentalReceiptsDAO {
	List<RentalReceipts> getAllRoomBills();

//	void createRentalReceipt(Integer id, String roomName, LocalDateTime checkInDateTime, LocalDateTime checkOutDateTime, String status);

	void save(RentalReceipts newReceipt);
	
//	public List<RentalReceipts> getCustomerInRoomBill();
}