package car;

import java.time.LocalDateTime;

import parkingLot.parkingLotTemplate.LeaveTicket;

// 자동차 . 승용차 > 가솔린 > 경차
public class MidsizeGasolineCar extends GasolineCar{

	// 경차 생성자
	public MidsizeGasolineCar(String carType, int carNum, int year, int month, int day, int hour, int minute, int engineDisplacement) {
		super(carType, carNum, year, month, day, hour, minute, engineDisplacement);
	}
	
	// 경차 입차정보 showData
	public String showData(LocalDateTime t) {
		return this.getCarType() + " " + this.getCarNum() + "번 \n입차시간:" + this.getCarIn().getYear() + "-" + this.getCarIn().getMonthValue() + "-" + this.getCarIn().getDayOfMonth() + " " + this.getCarIn().getHour() + ":" + this.getCarIn().getMinute() + " \n배기량: " + this.getEngineDisplacement();
	}
	
	// 경차 출차정보 showLeave
	public LeaveTicket showLeave(LocalDateTime t) {

		setCarCur(t);
		setParkingTime(this.getCarIn(), this.getCarCur());
		
		return new LeaveTicket(this.Fee(), "주차요금(" + this.getCarNum() + "번) : " + this.Fee() + "원 \n(주차시간 " + ((int)this.getParkingTime() / 60) + "시간 " + ((int)this.getParkingTime() % 60) + "분)");
	}
	
	// 경차 요금 Fee
	public int Fee() {
		return ( (500 + (int)Math.ceil(((double)(this.getParkingTime() - 30)/10)) * 100) / 2 );
	}
	
}
