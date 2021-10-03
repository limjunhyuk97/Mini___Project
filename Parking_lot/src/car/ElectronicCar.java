package car;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

import parkingLot.parkingLotTemplate.LeaveTicket;

// 자동차 . 승용차 > 전기
public class ElectronicCar extends Car{

	private final int FULLBATTERY = 60;
	private final double INITBATTERY;
	private double battery;
	
	// 전기차 생성자 ElectronicCar constructor
	public ElectronicCar(String carType, int carNum, int year, int month, int day, int hour, int minute, double curBattery){
		super(carType, carNum, year, month, day, hour, minute);
		this.INITBATTERY = curBattery;
		this.battery = curBattery;
	}
	
	// 전기차 배터리 Battery 
	public void setBattery() {
		if(((int)this.getParkingTime() * 0.5) + INITBATTERY < FULLBATTERY) {
			this.battery += (this.getParkingTime() * 0.5);
		}
		else {
			this.battery = this.FULLBATTERY;
		}
	}
	public double getBattery() {
		return this.battery;
	}
	
	// 전기차 입차정보 showData
	public String showData(LocalDateTime t) {
		
		setCarCur(t);
		setParkingTime(this.getCarIn(), this.getCarCur());
		setBattery();
		
		DecimalFormat form = new DecimalFormat("#.#");
		return this.getCarType() + " " + this.getCarNum() + "번 \n입차시간:" + this.getCarIn().getYear() + "-" + this.getCarIn().getMonthValue() + "-" + this.getCarIn().getDayOfMonth() + " " + this.getCarIn().getHour() + ":" + this.getCarIn().getMinute() + " \n충전: " + form.format(this.getBattery()) + "KW";
	}
	
	// 전기차 출차 정보 showLeave
	public LeaveTicket showLeave(LocalDateTime t) {
		
		setCarCur(t);
		setParkingTime(this.getCarIn(), this.getCarCur());
		setBattery();
		
		DecimalFormat form = new DecimalFormat("#.#");
		
		return new LeaveTicket(Fee() + FeeElectric(), "총요금(" + this.getCarNum() + "): " + (this.Fee() + this.FeeElectric()) + "원\n"
				+ " - 주차요금 : " + this.Fee() + "원(주차시간: " + ((int)this.getParkingTime() / 60) + "시간 " + ((int)this.getParkingTime() % 60) + "분)\n"
						+ " - 충전요금 : " + this.FeeElectric() + "원(충전량: " + form.format(this.getBattery() - this.INITBATTERY) + "KW)");
	}
	
	// 전기차 요금 Fee
	public int Fee() {
		return 500 + (int)Math.ceil(((double)(this.getParkingTime() - 30)/10)) * 100;
	}
	
	public int FeeElectric() {
		return (int)((this.battery - this.INITBATTERY) * 400);
	}
	
}