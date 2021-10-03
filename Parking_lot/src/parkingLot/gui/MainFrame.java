package parkingLot.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import car.Car;
import car.CompactGasolineCar;
import car.ElectronicCar;
import car.MidsizeGasolineCar;
import car.Van;
import parkingLot.gui.leftPanel.InputPanel;
import parkingLot.gui.leftPanel.MaxCarPanel;
import parkingLot.gui.rightPanel.RightPanel;
import parkingLot.parkingLotTemplate.CarData;
import parkingLot.parkingLotTemplate.DailyIncome;

public class MainFrame extends JFrame{
	
	// 주차장 데이터 설정
	// 최대 주차 가능 수
	public static class MaximumCarNumber{
		public final int MAXCAR;
		public MaximumCarNumber(int max) {this.MAXCAR = max;}
	}
	private MaximumCarNumber mc;
	
	// 현재 주차된 대수
	private int totCar = 0;
	
	private int totIncome = 0;
	
	// 필수 입력
	private LocalDateTime timeLine = null;
	private int incomeYear, incomeMonth, incomeDate;
	private int carNum, additionalInfo;
	private String carType, cmdType;
	// 필수 입력 확인
	private boolean cmdTimeInput = false, cmdInput = false;
	
	// 유지 데이터
	private ArrayList<CarData> carDataBook = new ArrayList<CarData>();
	private ArrayList<DailyIncome> accountingBook = new ArrayList<DailyIncome>();
	private Car parkingLot[];
	
	// GUI 설정
	private static final int DEFAULT_WIDTH = 800;
	private static final int DEFAULT_HEIGHT = 650;
	private ArrayList<JPanel> ShowAll = new ArrayList<JPanel> ();
	// Panel component
	private JPanel leftPanel;
	private JPanel maxCarPanel, inputPanel;
	private RightPanel rightPanel;
	
	public MainFrame() {
		
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setLayout(new GridLayout(1, 2));
		
		// Total Frame : leftPanel + rightPanel
		leftPanel = new JPanel();
		rightPanel = new RightPanel(this);
		add(leftPanel);
		add(rightPanel);
		
		// left Panel : maxCarPanel + inputPanel
		leftPanel.setLayout(new BorderLayout(0, 10));
		// maxCarPanel
		MaxCarPanel maxCarPanel = new MaxCarPanel(this, ShowAll);
		// inputPanel
		InputPanel inputPanel = new InputPanel(this);
		// maxCarPanel + inputPanel
		ShowAll.add(inputPanel); ShowAll.add(rightPanel);
		leftPanel.add(maxCarPanel, BorderLayout.NORTH);
		leftPanel.add(inputPanel, BorderLayout.CENTER);
				
	}
	
	// timeLineCheck
	public boolean timeLineCheck(int YEAR, int MONTH, int DATE, int HOUR, int MINUTE)
			throws DateTimeException {
		
 		LocalDateTime inputTime = LocalDateTime.of(YEAR, MONTH, DATE, HOUR, MINUTE);
		
		// 처음 checking 시에
		if(timeLine == null) {
			accountingBook.add(new DailyIncome(LocalDateTime.of(YEAR, MONTH, DATE, 0, 0), totIncome));
			timeLine = inputTime;
			return true;
		}
		
		if(inputTime.isAfter(timeLine)) {
			
			// Daily income 갱신 (일자가 변하였는지 확인)
			LocalDateTime tmpInputTime = LocalDateTime.of(YEAR, MONTH, DATE, 0, 0);
			LocalDateTime tmpTimeLine = LocalDateTime.of(timeLine.getYear(), timeLine.getMonthValue(), timeLine.getDayOfMonth(), 0, 0);
			
			// 날짜가 바뀌엇다면, 이전날짜를 accountingBook에 넣고, totIncome 0으로 바꾼다.
			if(tmpInputTime.isAfter(tmpTimeLine)) {
				totIncome = 0;
				for(LocalDateTime i=tmpTimeLine; i.isBefore(tmpInputTime) || i.isEqual(tmpInputTime); i =i.plusDays(1))
				accountingBook.add(new DailyIncome(i, totIncome));
			}			
			
			// timeLine 갱신.
			timeLine = inputTime;
			return true;
		}
		else {
			return false;
		}
	} // timeLineCheck method end
	
	
	// Command Check
	public boolean commandCheck() {
		// 시간 오류 X
		if(this.cmdInput && this.cmdTimeInput) {
			
			this.cmdInput = false;
			this.cmdTimeInput = false;
			return true;
		}
		
		else return false;
	}
	
	// Command Initiate
	public void command() {
		
		// enter 명령 처리
		if(this.cmdType.equals("enter")) {
						
			// 주차장이 가득 찬 경우
			if(totCar == mc.MAXCAR) {
				rightPanel.getOutputInterfacePanel().appendNewCommand("Parkinglot is full.");
			}
			else {
				for(int i=0 ;i<mc.MAXCAR; ++i) {
					if(parkingLot[i] == null) {
						if(carType.equals("e")) {
							parkingLot[i] = new ElectronicCar(carType, carNum, 
									timeLine.getYear(), timeLine.getMonthValue(), timeLine.getDayOfMonth(), timeLine.getHour(), timeLine.getMinute(),
									additionalInfo);
						}
						if(carType.equals("g")) {
							if(additionalInfo >= 1000) {
								parkingLot[i] = new CompactGasolineCar(carType, carNum, 
										timeLine.getYear(), timeLine.getMonthValue(), timeLine.getDayOfMonth(), timeLine.getHour(), timeLine.getMinute(),
										additionalInfo);
							}
							else {
								parkingLot[i] = new MidsizeGasolineCar(carType, carNum, 
										timeLine.getYear(), timeLine.getMonthValue(), timeLine.getDayOfMonth(), timeLine.getHour(), timeLine.getMinute(),
										additionalInfo);
							}
						}
						if(carType.equals("v")) {
							parkingLot[i] = new Van(carType, carNum, 
									timeLine.getYear(), timeLine.getMonthValue(), timeLine.getDayOfMonth(), timeLine.getHour(), timeLine.getMinute(),
									additionalInfo);
						}
						
						boolean same = false;
						for(int j=0; j<carDataBook.size(); ++j) {
							if(carDataBook.get(j).getCarNum() == carNum) {
								same = true;
								break;
							}
						}
						if(!same)							
							carDataBook.add(new CarData(carType, carNum, additionalInfo));
						
						break;
					}
				}
				++totCar;
				rightPanel.getParkingLotPanel().refreshCarData();
				rightPanel.getCarDataPanel().appendCarData();
				rightPanel.getOutputInterfacePanel().appendNewCommand("Car " + carNum + " entered");
			}
						
			return;
		}
					
		if(this.cmdType.equals("income")) {
			// 이미 year, month, date는 주어져 있음 : 해당 날짜의 income 출력만 하면 됨
			for(int i = 0; i<accountingBook.size(); ++i) {
				if( accountingBook.get(i).getLocalDateTime().equals(LocalDateTime.of(incomeYear, incomeMonth, incomeDate, 0, 0)) ) {
					rightPanel.getOutputInterfacePanel().appendNewCommand("총 수입 (" + incomeYear + "년 " + incomeMonth + "월 " + incomeDate + "일) : " + accountingBook.get(i).getIncome() + "원");
					break;
				}
			}
			return;
		}
					
		if(this.cmdType.equals("leave")) {
			// 이미 차량 정보는 주어져 있음. 삭제하는 작업 요구 + 삭제 결과 출력 요구
			// 빈 주차장
			if(totCar == 0) {
				rightPanel.getOutputInterfacePanel().appendNewCommand("Parkinglot is empty.");
				return;
			}
			// 차량 없음
			if(!findCar(carNum, parkingLot, mc.MAXCAR)) {
				rightPanel.getOutputInterfacePanel().appendNewCommand("Car number (" + carNum + ") doesn't exist\n");
				return;
			}
			
			LocalDateTime t = LocalDateTime.of(timeLine.getYear(), timeLine.getMonthValue(), timeLine.getDayOfMonth(), timeLine.getHour(), timeLine.getMinute());
			
			for(int i=0; i<mc.MAXCAR; ++i) {
				if((parkingLot[i] != null) && (carNum == parkingLot[i].getCarNum())) {
					totIncome += parkingLot[i].showLeave(t).fee;
					rightPanel.getOutputInterfacePanel().appendNewCommand(parkingLot[i].showLeave(t).info);
					parkingLot[i] = null;
					break;
				}
			}
			
			// 장부 갱신
			int lastIndex = accountingBook.size() - 1;
			DailyIncome tmp = accountingBook.get(lastIndex);
			tmp.setIncome(totIncome);
			accountingBook.set(lastIndex, tmp);
			
			// 주차장 갱신
			rightPanel.getParkingLotPanel().refreshCarData();
			--totCar;
			
			return;
		}
					
		if(this.cmdType.equals("show")) {
			// 이미 차량 정보는 주어져 있음. 결과 출력 요구
			// 빈 주차장
			if(totCar == 0) {
				rightPanel.getOutputInterfacePanel().appendNewCommand("Parkinglot is empty.");
				return;
			}
			// 차량 없음
			if(!findCar(carNum, parkingLot, mc.MAXCAR)) {
				rightPanel.getOutputInterfacePanel().appendNewCommand("Car number (" + carNum + ") doesn't exist\n");
				return;
			}
			
			LocalDateTime t = LocalDateTime.of(timeLine.getYear(), timeLine.getMonthValue(), timeLine.getDayOfMonth(), timeLine.getHour(), timeLine.getMinute());
			
			for(int i=0; i<mc.MAXCAR; ++i) {
				if((parkingLot[i] != null) && (carNum == parkingLot[i].getCarNum())) {
					rightPanel.getOutputInterfacePanel().appendNewCommand(parkingLot[i].showData(t));
					break;
				}
			}
		}
	}
	
	public boolean findCar(int carNum, Car[] parkingLot, int MAXCAR) {
		for(int i=0; i<MAXCAR; ++i) {
			if(parkingLot[i] == null) continue;
			if(parkingLot[i].getCarNum() == carNum)
				return true;
		}
		return false;
	}
	
	// getter setter bundle
	public void setCmdTimeInput(boolean cmdTimeInput) {
		this.cmdTimeInput = cmdTimeInput;
	}
	
	public boolean getCmdTimeInput() {
		return this.cmdTimeInput;
	}
		
	public void setCmdInput(boolean cmdInput) {
		this.cmdInput = cmdInput;
	}
	
	public void setCarType(String carType) {
		this.carType = carType;
	}
	
	public void setCarNum(int carNum) {
		this.carNum = carNum;
	}
	
	public void setIncomeYear(int incomeYear) {
		this.incomeYear = incomeYear;
	}
	
	public void setIncomeMonth(int incomeMonth) {
		this.incomeMonth = incomeMonth;
	}
	
	public void setIncomeDate(int incomeDate) {
		this.incomeDate = incomeDate;
	}
	
	public void setAdditionalInfo(int additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public MaximumCarNumber getMaxCar() {
		return mc;
	}
	
	public void setMaxCar(int num) {
		this.mc = new MaximumCarNumber(num);
	}
	
	public Car[] getParkingLot() {
		return parkingLot;
	}
	
	public void setParkingLot(int num) {
		this.parkingLot = new Car[num];
		}
	
	public ArrayList<CarData> getCarDataBook(){
		return this.carDataBook;
	}

	public ArrayList<DailyIncome> getAccountingBook(){
		return this.accountingBook;
	}

	public void setCmdType(String cmdType) {
		this.cmdType = cmdType;
	}
	
	public String getCmdType() {
		return this.cmdType;
	}
	
	public LocalDateTime getTimeLine() {
		return this.timeLine;
	}

	public RightPanel getRightPanel() {
		return this.rightPanel;
	}
}








