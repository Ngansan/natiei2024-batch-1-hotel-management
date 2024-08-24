package com.spring.sample.service;

import java.util.List;

import com.spring.sample.entity.RentalReceipts;
import com.spring.sample.entity.RoomType;

public interface RentalReceiptsService {
	
	List<RentalReceipts> getAllRoomBills();
	
	void deleteRentalRoomById(Integer id);

	void updateRentalRoom(Integer id, String nameRoom, String note, RoomType roomType);
}
