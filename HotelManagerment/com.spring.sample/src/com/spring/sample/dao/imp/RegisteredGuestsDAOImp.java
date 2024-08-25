package com.spring.sample.dao.imp;

import java.util.List;

import org.hibernate.query.Query;

import com.spring.sample.dao.RegisteredGuestsDAO;
import com.spring.sample.entity.RegisteredGuests;

public class RegisteredGuestsDAOImp extends GenericDAOImp<RegisteredGuests, Integer> implements RegisteredGuestsDAO {
	
	public RegisteredGuestsDAOImp() {
		super(RegisteredGuests.class);
	}
	
    @Override
    public List<RegisteredGuests> getAllRegisteredGuests() {
        String hql = "FROM RegisteredGuests";
        Query<RegisteredGuests> query = getSession().createQuery(hql, RegisteredGuests.class);
        return query.getResultList();
    }
}
