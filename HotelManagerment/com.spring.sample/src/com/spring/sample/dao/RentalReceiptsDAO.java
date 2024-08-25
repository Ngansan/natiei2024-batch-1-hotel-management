package com.spring.sample.dao;

import java.util.List;

import com.spring.sample.entity.RentalReceipts;

public interface RentalReceiptsDAO {
	List<RentalReceipts> getAllRoomBills();

	void save(RentalReceipts newReceipt);
	
//	public List<RentalReceipts> getCustomerInRoomBill();
}