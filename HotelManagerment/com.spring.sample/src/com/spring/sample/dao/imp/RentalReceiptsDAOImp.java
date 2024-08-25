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
	    String hql = "FROM RentalReceipts rr JOIN FETCH rr.corespondingRoom ";
	    Query<RentalReceipts> query = getSession().createQuery(hql, RentalReceipts.class);
	    return query.getResultList();
	}
	
	@Override
	public void save(RentalReceipts newReceipt) {
		getSession().save(newReceipt);
	}
	
//	@Override
//	public List<RentalReceipts> getCustomerInRoomBill() {
//		String hql = "SELECT rg.id, rg.firstName, rg.lastName, rg.address, rr.id, rr.checkInDateTime, rr.checkOutDateTime, gt.typeName " +
//	             "FROM RegisteredGuests rg " +
//	             "JOIN rg.rentalReceipts rr " +       // Join với phiếu thuê phòng từ quan hệ @OneToMany
//	             "JOIN rg.typeOfGuest gt " +          // Join với loại khách từ quan hệ @ManyToOne
//	             "WHERE rr.checkOutDateTime IS NULL " + // Điều kiện để chỉ lấy những phiếu thuê chưa trả phòng
//	             "ORDER BY rg.id ASC, rr.checkInDateTime DESC"; // Sắp xếp theo ID khách và ngày thuê phòng
//		Query<RentalReceipts> query = getSession().createQuery(hql, RentalReceipts.class);
//
//		return query.getResultList();
//	}
}

	

