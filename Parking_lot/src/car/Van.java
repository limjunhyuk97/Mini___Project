package car;

import java.time.LocalDateTime;

import parkingLot.parkingLotTemplate.LeaveTicket;

public class Van extends Car{
	
	private VanSize carSize;
	
	// 밴 생성자 van constructor
	public Van(String carType, int carNum, int year, int month, int day, int hour, int minute, int carSize) {
		super(carType, carNum, year, month, day, hour, minute);
		this.carSize = VanSize.values()[carSize-1];
	}
	
	// 밴 크기
	public VanSize getVanSize() { return carSize; }
	public void setVanSize(int carSize) { this.carSize = VanSize.values()[carSize]; }
	
	// 밴 입차정보 showData
	public String showData(LocalDateTime t) {
		return this.getCarType() + " " + this.getCarNum() + "번 \n입차시간:" + this.getCarIn().getYear() + "-" + this.getCarIn().getMonthValue() + "-" + this.getCarIn().getDayOfMonth() + " " + this.getCarIn().getHour() + ":" + this.getCarIn().getMinute() + " \n밴 크기: " + this.getVanSize();
	}
	
	// 밴 출차정보 showLeave
	public LeaveTicket showLeave(LocalDateTime t) {
		
		setCarCur(t);
		setParkingTime(this.getCarIn(), this.getCarCur());

		return new LeaveTicket(this.Fee(), "주차요금(" + this.getCarNum() + "번) : " + this.Fee() + "원 \n(주차시간 " + ((int)this.getParkingTime() / 60) + "시간 " + ((int)this.getParkingTime() % 60) + "분)");
	}
	
	// 밴 요금 Fee
	public int Fee() {
		switch(this.carSize) {
		case A:
			return (int)Math.ceil(((double)(this.getParkingTime())/10)) * 500;
			
		case B:
			return (int)Math.ceil(((double)(this.getParkingTime())/10)) * 300;
		
		case C:
			return (int)Math.ceil(((double)(this.getParkingTime())/10)) * 200;
			
		default:
			return 0;
		}
	}
	
}