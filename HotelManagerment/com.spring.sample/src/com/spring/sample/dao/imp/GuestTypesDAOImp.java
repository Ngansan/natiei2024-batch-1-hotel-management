package com.spring.sample.dao.imp;

import org.hibernate.query.Query;

import com.spring.sample.dao.GuestTypesDAO;
import com.spring.sample.entity.GuestTypes;
import java.util.List;

public class GuestTypesDAOImp extends GenericDAOImp<GuestTypes, Integer> implements GuestTypesDAO {
	
	public GuestTypesDAOImp() {
		super(GuestTypes.class);
	}

	@Override
	public List<GuestTypes> guestTypesList() {
		// Sử dụng HQL để truy vấn danh sách loại khách
		String hql = "FROM GuestTypes";
		Query<GuestTypes> query = getSession().createQuery(hql, GuestTypes.class);
		return query.getResultList();
	}
}
