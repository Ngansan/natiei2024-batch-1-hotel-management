package com.spring.sample.dao;

import java.util.List;
import com.spring.sample.entity.RegisteredGuests;

public interface RegisteredGuestsDAO {
	List<RegisteredGuests> getAllRegisteredGuests();
}
