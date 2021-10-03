package car;

import java.time.LocalDateTime;

import parkingLot.parkingLotTemplate.LeaveTicket;

// 자동차 . 승용차 > 가솔린
public abstract class GasolineCar extends Car{
	
	private int engineDisplacement;
	
	// 가솔린 생성자 GasolineCar constructor
	public GasolineCar(String carType, int carNum, int year, int month, int day, int hour, int minute, int engineDisplacement){
		super(carType, carNum, year, month, day, hour, minute);
		this.engineDisplacement = engineDisplacement;
	}
	
	// 가솔린 배기량 engineDisplacement
	public void setEngineDisplacement(int engineDisplacement) {
		this.engineDisplacement = engineDisplacement;
	}
	public int getEngineDisplacement() {
		return this.engineDisplacement;
	}
	
	// 가솔린 입차정보
	public abstract String showData(LocalDateTime t);
	
	// 가솔린 출차정보
	public abstract LeaveTicket showLeave(LocalDateTime t);
	
	// 가솔린 요금
	public abstract int Fee();
	
}
