package com.spring.sample.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.sample.entity.RentalReceipts;
import com.spring.sample.entity.Room;
import com.spring.sample.service.RentalReceiptsService;
import com.spring.sample.service.RoomService;
import com.spring.sample.service.RoomTypeService;

import com.spring.sample.entity.RoomType;


@Controller
@RequestMapping("/bill-for-rents")
public class BillForRentsController {
	
	private static final Logger logger = LoggerFactory.getLogger(BillForRentsController.class);
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private RentalReceiptsService rentalReceiptsService;
	
	@GetMapping
	public String index(Locale locale, Model model) {
		List<Room> listAvailableRooms = roomService.getAvailableRooms();
        model.addAttribute("listAvailableRooms", listAvailableRooms);
        
        List<Room> rooms = roomService.getAllRooms();
        model.addAttribute("rooms", rooms);
		
        List<RentalReceipts> listAllRoomBills = rentalReceiptsService.getAllRoomBills();
        model.addAttribute("listAllRoomBills", listAllRoomBills);
		return "bill-for-rents/index";
	}
	
	@PostMapping("/handle")
	public String handleBillForRents(@RequestParam("roomName") String roomName,
			@RequestParam("checkInDateTime") CharSequence checkInDateTime,
			@RequestParam("checkOutDateTime") CharSequence checkOutDateTime, @RequestParam("id") Integer id,
			@RequestParam("status") String status, Model model) {

//		// Lấy thông tin phòng từ ID
//		Room room = roomService.findById(id);
//		if (room == null) {
//			model.addAttribute("error", "Không tìm thấy phòng với ID: " + id);
//			return "error"; // Hiển thị trang lỗi nếu không tìm thấy phòng
//		}
		
		//Lấy thông tin phòng theo tên phòng
//		Room room = roomService
		// Gọi service để lưu thông tin phiếu thuê phòng
		rentalReceiptsService.createRentalReceipt(id, roomName, checkInDateTime, checkOutDateTime, status);

		RentalReceipts newReceipt = new RentalReceipts(roomName, checkInDateTime, checkOutDateTime);
		newReceipt.setCorespondingRoom(roomName);
		newReceipt.setCheckInDateTime(LocalDateTime.parse(checkInDateTime));
		newReceipt.setCheckOutDateTime(LocalDateTime.parse(checkOutDateTime));
		newReceipt.setPaymentStatus(0); // Mặc định là chưa thanh toán

		// Lưu phiếu thuê phòng vào cơ sở dữ liệu
		rentalReceiptsService.save(newReceipt);
		// Cập nhật lại danh sách phiếu thuê phòng
		
//      List<RentalReceipts> listAllRoomBills = rentalReceiptsService.getAllRoomBills();
//      model.addAttribute("listAllRoomBills", listAllRoomBills);

		return "redirect:/INSERT_ROOMBILL";
//        

	}
	

}
