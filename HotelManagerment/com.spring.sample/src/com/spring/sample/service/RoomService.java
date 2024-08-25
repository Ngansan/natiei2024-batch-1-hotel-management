package com.spring.sample.service;

import java.time.LocalDateTime;
import java.util.List;

import com.spring.sample.entity.Room;
import com.spring.sample.entity.RoomType;

public interface RoomService {
	List<Room> getAllRooms();

	void deleteRoomById(Integer roomID);

	void updateRoom(Integer roomID, String nameRoom, String note, RoomType roomType);

	void save(Room newRoom);
	
	Room findById(Integer Id);
	
	List<Room> getAvailableRooms();
	
	Room getRoom(String roomName);
}
