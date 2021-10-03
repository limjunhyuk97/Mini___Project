package parkingLot.gui.rightPanel.outputPanel;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import car.ElectronicCar;
import car.GasolineCar;
import car.Van;
import parkingLot.gui.MainFrame;

public class ParkingLotPanel extends JPanel {
	
	private MainFrame parent;
	private JTextArea parkinglotTextArea;
	private JScrollPane parkinglotScrollPane;
	
	public ParkingLotPanel(MainFrame parent) {
		
		this.parent = parent;
		
		setLayout(new BorderLayout());
		
		parkinglotTextArea = new JTextArea();
		parkinglotScrollPane = new JScrollPane(parkinglotTextArea);
		parkinglotTextArea.setText("---- Now Parking ----\n");
		parkinglotTextArea.setEditable(false);
		
		add(parkinglotScrollPane, BorderLayout.CENTER);
	}
	
	public void refreshCarData() {
		
		parkinglotTextArea.setText("---- Now Parking ----\n");

		for(int i=0; i<parent.getMaxCar().MAXCAR; ++i) {
			if(parent.getParkingLot()[i] != null) {
				String carType = parent.getParkingLot()[i].getCarType();
				int carNum = parent.getParkingLot()[i].getCarNum();
				if(carType.equals("Àü±âÂ÷")) {
					ElectronicCar c = (ElectronicCar)parent.getParkingLot()[i];
					c.setCarCur(parent.getTimeLine());
					c.setBattery();
					parkinglotTextArea.append("Car type : electronic car(e)"
							+ ", Car number : " + carNum
							+ ", Entrance Battery status : " + c.getBattery() + "KW\n");
				}
				if(carType.equals("½Â¿ëÂ÷")) {
					GasolineCar c = (GasolineCar)parent.getParkingLot()[i];
					parkinglotTextArea.append("Car type : Gasoline car(g)"
							+ ", Car number : " + carNum
							+ ", Engine Displacement : " + c.getEngineDisplacement() + "\n");
				}
				if(carType.equals("¹ê")) {
					Van c = (Van)parent.getParkingLot()[i];
					parkinglotTextArea.append("Car type : Van(v)"
							+ ", Car number : " + carNum
							+ ", Car size : " + c.getVanSize() + "\n");
				}
			}
		}
	}
}
