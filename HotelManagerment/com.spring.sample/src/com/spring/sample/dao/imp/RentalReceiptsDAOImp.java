package com.spring.sample.dao.imp;

import java.util.List;

import org.hibernate.query.Query;

import com.spring.sample.dao.RentalReceiptsDAO;
import com.spring.sample.entity.RentalReceipts;


public class RentalReceiptsDAOImp extends GenericDAOImp<RentalReceipts, Integer> implements RentalReceiptsDAO{
	
	public RentalReceiptsDAOImp() {
		super(RentalReceipts.class);
	}
	
	@Override
	public List<RentalReceipts> getAllRoomBills() {
	    String hql = "SELECT ID, ROOMS.RoomID, RoomName, CheckInDateTime, CheckOutDateTime, RoomPriceForOneDay, PaymentStatus "
	    		+ "FROM RENTAL_RECEIPTS "
	    		+ "JOIN ROOMS ON RENTAL_RECEIPTS.RoomID = ROOMS.RoomID ORDER BY PaymentStatus";
	    Query<RentalReceipts> query = getSession().createQuery(hql, RentalReceipts.class);
	    return query.getResultList();
	}
	
	
	

}

