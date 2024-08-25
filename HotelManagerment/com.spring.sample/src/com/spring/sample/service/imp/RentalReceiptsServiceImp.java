package com.spring.sample.service.imp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.sample.entity.RentalReceipts;
import com.spring.sample.service.RentalReceiptsService;

@Service
public class RentalReceiptsServiceImp extends BaseServiceImpl implements RentalReceiptsService {
	
	private RentalReceiptsServiceImp() {
	}

	@Override
	public List<RentalReceipts> getAllRoomBills() {
		return rentalReceiptsDAO.getAllRoomBills();
	}

	@Override
	public void save(RentalReceipts newReceipt) {
		rentalReceiptsDAO.save(newReceipt);
	}
	
//	@Override
//	public List<RentalReceipts> getCustomerInRoomBill() {
//		return rentalReceiptsDAO.getCustomerInRoomBill();
//	}
}
