package car;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import parkingLot.parkingLotTemplate.LeaveTicket;

// 자동차
public abstract class Car {
	
	private String carType;
	private int carNum;
	private LocalDateTime in = null;
	private LocalDateTime cur = null;
	private long parkingTime = 0;
	
	// Car Constructor
	public Car(String carType, int carNum, int year, int month, int day, int hour, int minute) {
		
		if(carType.equals("g")) this.carType = "승용차";
		else if(carType.equals("e")) this.carType = "전기차";
		else if(carType.equals("v")) this.carType = "밴";
		else {}
		
		this.carNum = carNum;
		in = LocalDateTime.of(year, month, day, hour, minute);
	}
	
	// 차량종류 carType
	public String getCarType() {return this.carType;}
	public void setCarType(String carType) {this.carType = carType;}
	
	// 차량번호 carNum
	public int getCarNum(){return this.carNum;}
	public void setCarNum(int carNum) {this.carNum = carNum;}
	
	// 입차시간 carIn
	public LocalDateTime getCarIn() {
		if(in == null)
			return null;
		return in;
	}
	public void setCarIn(LocalDateTime t) {
		this.in = t;
	}
	
	// 명령시간+출차시간 carCur
	public LocalDateTime getCarCur() {
		if(cur == null)
			return null;
		else
			return cur;
	}
	public void setCarCur(LocalDateTime t) {
		this.cur = t;
	}
	
	// 주차시간 parkingTime(분)
	public long getParkingTime() { return this.parkingTime; }
	public void setParkingTime(LocalDateTime t1, LocalDateTime t2) {
		this.parkingTime = ( ChronoUnit.MINUTES.between(t1, t2) );
	}
	
	// 입차정보
	public abstract String showData(LocalDateTime t);
	
	// 출차
	public abstract LeaveTicket showLeave(LocalDateTime t);

	// 요금
	public abstract int Fee();
		
}