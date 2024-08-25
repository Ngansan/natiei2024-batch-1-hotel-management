package com.spring.sample.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.sample.entity.GuestTypes;
import com.spring.sample.entity.RegisteredGuests;
import com.spring.sample.entity.RentalReceipts;
import com.spring.sample.entity.Room;
import com.spring.sample.entity.RoomType;
import com.spring.sample.service.GuestTypesService;
import com.spring.sample.service.RegisteredGuestsService;
import com.spring.sample.service.RentalReceiptsService;
import com.spring.sample.service.RoomService;


@Controller
@RequestMapping("/bill-for-rents")
public class BillForRentsController {
	
	private static final Logger logger = LoggerFactory.getLogger(BillForRentsController.class);
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private RentalReceiptsService rentalReceiptsService;
	
	@Autowired
	private GuestTypesService guestTypesService;
	
	@Autowired
	private RegisteredGuestsService registeredGuestsService;
	
	@GetMapping
	public String index(Locale locale, Model model) {
		List<Room> listAvailableRooms = roomService.getAvailableRooms();
        model.addAttribute("listAvailableRooms", listAvailableRooms);
        
        List<Room> rooms = roomService.getAllRooms();
        model.addAttribute("rooms", rooms);
		
        List<RentalReceipts> listAllRoomBills = rentalReceiptsService.getAllRoomBills();
        model.addAttribute("listAllRoomBills", listAllRoomBills);
//        
//        List<GuestTypes> listTypeCustomer = guestTypesService.guestTypesList();
//        model.addAttribute("listTypeCustomer", listTypeCustomer);
//        
//        List<RegisteredGuests> listCustomer = registeredGuestsService.getAllRegisteredGuests();
//        model.addAttribute("listCustomer", listCustomer);        
		return "bill-for-rents/index";
	}
	
    // Xử lý khi thêm phiếu thuê phòng mới @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @PostMapping("/add")
    public String handleRoomBillSubmit(
    		@RequestParam(value = "roomName", required = false, defaultValue = "DefaultRoomName") String roomName,
    		@RequestParam(value = "checkInDateTime", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime checkInDateTime,
			@RequestParam(value = "checkOutDateTime", required = false ) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime checkOutDateTime,
			@RequestParam(value = "paymentStatus", required = false, defaultValue = "0") Integer paymentStatus, Model model) {
    			
    	System.out.println("Da khoi tao thanh cong \n \n\n\n\n");
    	System.out.println("nameRoom: " + roomName);
		System.out.println("checkInDateTime: " + checkInDateTime);
		System.out.println("checkOutDateTime: " + checkOutDateTime);
		System.out.println("paymentStatus: " + paymentStatus);

    	    try {
    	    	LocalDateTime currentDateTime = LocalDateTime.now();
    	    	Room room = roomService.getRoom(roomName);
    	    	
        		if (checkInDateTime == null) {
                    model.addAttribute("error", "Ngày thuê phòng không được để trống.");
                    return "redirect:/bill-for-rents";
                }

                if (checkOutDateTime == null) {
                    model.addAttribute("error", "Ngày trả phòng không được để trống.");
                    return "redirect:/bill-for-rents";
                }
                
                // Kiểm tra nếu ngày kết thúc sớm hơn hoặc trùng với ngày thuê
                if (checkOutDateTime.isBefore(checkInDateTime) || checkOutDateTime.isEqual(checkInDateTime)) {
                    model.addAttribute("error", "Ngày trả phòng phải sau ngày thuê phòng.");
                    return "redirect:/bill-for-rents";
                }
                
                // Kiểm tra nếu ngày thuê trễ hơn ngày hiện tại
                
                if (checkInDateTime.isBefore(currentDateTime)) {
                    model.addAttribute("error", "Ngày thuê phòng không được trễ hơn ngày hiện tại.");
                    return "redirect:/bill-for-rents";
                }
                
                //Tạo và lưu phiếu phòng thuê mới
    	        RentalReceipts newRoom = new RentalReceipts();
    	        newRoom.setCorespondingRoom(room);
    	        newRoom.setCheckInDateTime(checkInDateTime);
    	        newRoom.setCheckOutDateTime(checkOutDateTime);
    	        newRoom.setPaymentStatus(0);  
    	        
				rentalReceiptsService.save(newRoom);
    	        logger.info("Dữ liệu đã được lưu: " + newRoom);
    	        
    	        //Update lại status là "Đã thuê"
//    	        room.setStatus(1);
//    	        roomService.updateStatus(room.getRoomName(), 1);
    	        

    	        
    	    } catch (Exception e) {
    	    	logger.error("Có lỗi xảy ra khi thêm phòng mới.", e);
    	        model.addAttribute("error", "Có lỗi xảy ra khi thêm phiếu thuê phòng.");
    	    }
    	    return "redirect:/bill-for-rents";
    }	
}
