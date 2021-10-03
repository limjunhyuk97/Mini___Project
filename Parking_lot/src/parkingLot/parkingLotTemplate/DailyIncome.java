package parkingLot.parkingLotTemplate;

import java.time.LocalDateTime;

public class DailyIncome{
	
	private LocalDateTime t;
	private int income;
	
	public DailyIncome(LocalDateTime t, int income) {
		this.t = t;
		this.income = income;
	}
	
	public LocalDateTime getLocalDateTime() {return t;}
	public int getIncome() {return income;}
	
	public void setLocalDateTime(LocalDateTime t) {this.t = t;}
	public void setIncome(int income) {this.income = income;}
}
