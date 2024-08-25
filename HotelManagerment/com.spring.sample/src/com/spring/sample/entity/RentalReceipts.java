package com.spring.sample.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "RENTAL_RECEIPTS") // PHIEU THUE PHONG
public class RentalReceipts extends BaseEntity implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // MA PHIEU THUE

    @Column(name = "CheckInDateTime")
    private LocalDateTime checkInDateTime; // NGAY THUE PHONG

    @Column(name = "CheckOutDateTime")
    private LocalDateTime checkOutDateTime; // NGAY TRA PHONG

    @Column(name = "RoomPriceForOneDay")
    private Float roomPriceForOneDay; // DON GIA MOT NGAY

    @Column(name = "PaymentStatus")
    private Integer paymentStatus; // TINH TRANG TRA TIEN

    @Column(name = "RentalDays")
    private Integer numOfRentalDays; // SO NGAY THUE

    @Column(name = "Amount")
    private Float amountOfPayment; // SO TIEN 1 PHONG

    @ManyToOne
    @JoinColumn(name = "InvoiceID")
    private Invoices corespondingInvoice; // KHOA NGOAI

    @ManyToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "RoomID")
    private Room corespondingRoom;

    @ManyToOne
    @JoinColumn(name = "RGuest_ID")
    private RegisteredGuests corespondingRegisteredGuest;

    public RentalReceipts(LocalDateTime checkInDateTime, LocalDateTime checkOutDateTime, Float roomPriceForOneDay,
            Integer paymentStatus, Integer numOfRentalDays, Float amountOfPayment, Invoices corespondingInvoice, Room corespondingRoom) {
        this.checkInDateTime = checkInDateTime;
        this.checkOutDateTime = checkOutDateTime;
        this.roomPriceForOneDay = roomPriceForOneDay;
        this.paymentStatus = paymentStatus;
        this.numOfRentalDays = numOfRentalDays;
        this.amountOfPayment = amountOfPayment;
        this.corespondingInvoice = corespondingInvoice;
        this.corespondingRoom = corespondingRoom;
    }

	public RentalReceipts(String roomName, LocalDateTime checkInDateTime2, LocalDateTime checkOutDateTime2,
			String paymentStatus2) {
		// TODO Auto-generated constructor stub
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getRoomPriceForOneDay() {
		return roomPriceForOneDay;
	}

	public void setRoomPriceForOneDay(Float roomPriceForOneDay) {
		this.roomPriceForOneDay = roomPriceForOneDay;
	}

	public Integer getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(Integer paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Integer getNumOfRentalDays() {
		return numOfRentalDays;
	}

	public void setNumOfRentalDays(Integer numOfRentalDays) {
		this.numOfRentalDays = numOfRentalDays;
	}

	public Float getAmountOfPayment() {
		return amountOfPayment;
	}

	public void setAmountOfPayment(Float amountOfPayment) {
		this.amountOfPayment = amountOfPayment;
	}

	public Invoices getCorespondingInvoice() {
		return corespondingInvoice;
	}

	public void setCorespondingInvoice(Invoices corespondingInvoice) {
		this.corespondingInvoice = corespondingInvoice;
	}

	public Room getCorespondingRoom() {
		return corespondingRoom;
	}

	public void setCorespondingRoom(Room corespondingRoom) {
		this.corespondingRoom = corespondingRoom;
	}

	public RegisteredGuests getCorespondingRegisteredGuest() {
		return corespondingRegisteredGuest;
	}

	public void setCorespondingRegisteredGuest(RegisteredGuests corespondingRegisteredGuest) {
		this.corespondingRegisteredGuest = corespondingRegisteredGuest;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public RentalReceipts() {
	}

	public void setCorespondingRoom(String roomName) {
	}

	public void setCheckInDateTime(LocalDateTime localDateTime) {
		// TODO Auto-generated method stub
		
	}

	public void setCheckOutDateTime(LocalDateTime localDateTime) {
	}

	public void setPaymentStatus(int i) {
	}

	public LocalDateTime getCheckInDateTime() {
		return checkInDateTime;
	}

	public LocalDateTime getCheckOutDateTime() {
		return checkOutDateTime;
	}
	
}
