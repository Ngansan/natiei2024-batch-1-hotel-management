package com.spring.sample.dao.imp;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.query.Query;

import com.spring.sample.dao.RoomDAO;
import com.spring.sample.entity.RentalReceipts;
import com.spring.sample.entity.Room;
import com.spring.sample.entity.RoomType;

public class RoomDAOImp extends GenericDAOImp<Room, Integer> implements RoomDAO {

	public RoomDAOImp() {
		super(Room.class);
	}

	@Override
	public List<Room> getAllRooms() {
		System.out.println("DA CHAY TOI DAY");
		String hql = "SELECT r FROM Room r LEFT JOIN FETCH r.roomType t";
		Query<Room> query = getSession().createQuery(hql, Room.class);
		return query.getResultList();
	}

	@Override
	public void deleteRoomById(Integer roomID) {
		// Tải thực thể Room bằng ID
		Room room = getHibernateTemplate().get(Room.class, roomID);

		// Kiểm tra xem thực thể có tồn tại không
		if (room == null) {
			throw new RuntimeException("Room not found for ID: " + roomID);
		}

		// Khóa thực thể (có thể dùng LockMode nếu cần)
		getSession().buildLockRequest(LockOptions.NONE).lock(room);

		// Xóa thực thể
		getHibernateTemplate().delete(room);
	}

	@Override
	public void updateRoom(Integer roomID, String nameRoom, String note, RoomType roomType) {
		// Tìm entity bằng ID
		Room room = getHibernateTemplate().get(Room.class, roomID);
		if (room == null) {
			throw new RuntimeException("Room not found");
		}
		// Cập nhật các thuộc tính
		room.setRoomName(nameRoom);
		room.setNote(note);
		room.setRoomType(roomType);
		// Lưu lại entity đã cập nhật
		getHibernateTemplate().update(room);
	}

	@Override
	public void save(Room newRoom) {
		getHibernateTemplate().saveOrUpdate(newRoom);
	}
	
	@Override
	public Room findById(Integer id) {
		String hql = "FROM Room r WHERE r.id = :id";
		Query<Room> query = getSession().createQuery(hql, Room.class);
		query.setParameter("id", id);
		return query.uniqueResult();
	}
	
    @Override
    public Room getRoom(String roomName) {

    	String hql = "FROM Room r  where r.name = :roomName";
        // Tạo đối tượng Query với câu truy vấn và thiết lập tham số
        Query<Room> query = getSession().createQuery(hql, Room.class);
        query.setParameter("roomName", roomName);
        return query.uniqueResult();

    }
	
	@Override
	public List<Room> getAvailableRooms() {
		String hql = "SELECT r FROM Room r JOIN FETCH r.roomType rt WHERE r.status = 0"; /*0: available; 1: rented*/
		Query<Room> query = getSession().createQuery(hql, Room.class);
		return query.getResultList();
	}
	
}
