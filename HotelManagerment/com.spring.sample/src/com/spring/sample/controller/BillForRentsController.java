package com.spring.sample.controller;

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
		
        List<RentalReceipts> listAllRoomBills = rentalReceiptsService.getAllRoomBills();
        model.addAttribute("listAllRoomBills", listAllRoomBills);
		return "bill-for-rents/index";
	}
	
	// XÓA PHÒNG
	@PostMapping("/delete")
	public String deleteRentalRoom(@RequestParam("roomID") Integer roomID, Model model) {

		try {

			roomService.deleteRoomById(roomID);
			logger.info("Phòng với ID: {} đã được xóa thành công.", roomID);

		} catch (Exception e) {

			model.addAttribute("errorMessage", "Có lỗi xảy ra khi xóa phòng với ID: " + roomID + ". Vui lòng thử lại.");
		}

		return "redirect:/bill-for-rents";
	}
}
