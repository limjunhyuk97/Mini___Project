package parkingLot.parkingLotTemplate;

import car.VanSize;

public class CarData {
	private String carType;
	private int carNum;
	private int engineDisplacement = 0;
	private VanSize vanSize = null;
	
	public CarData(String carType, int carNum, int selec) {
		this.carType = carType;
		this.carNum = carNum;
		
		if(carType.equals("g")) this.engineDisplacement = selec;
		else if(carType.equals("v")) this.vanSize = VanSize.values()[selec-1];
	}
	
	public void setCarType(String carType) { this.carType = carType; }
	public String getCarType() { return this.carType; }
		
	public void setCarNum(int carNum) { this.carNum = carNum; }
	public int getCarNum() { return this.carNum; }
	
	public int getEngineDisplacement() { return this.engineDisplacement; }
	public void setEngineDisplacement(int engineDisplacement) { this.engineDisplacement = engineDisplacement; }
	
	public VanSize getVanSize() { return this.vanSize; }
	public void setVanSize(int vanSize) { this.vanSize = VanSize.values()[vanSize-1]; }
	
}
