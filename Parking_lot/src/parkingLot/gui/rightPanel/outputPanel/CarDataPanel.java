package parkingLot.gui.rightPanel.outputPanel;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import car.ElectronicCar;
import car.GasolineCar;
import car.Van;
import parkingLot.gui.MainFrame;

public class CarDataPanel extends JPanel{

	private MainFrame parent;
	private JTextArea carInfoTextArea;
	private JScrollPane carInfoScrollPane;
	
	public CarDataPanel(MainFrame parent) {
		
		this.parent = parent;
		
		setLayout(new BorderLayout());
		
		carInfoTextArea = new JTextArea();
		carInfoScrollPane = new JScrollPane(carInfoTextArea);
		carInfoTextArea.setText("---- Car Info Stack ----\n");
		carInfoTextArea.setEditable(false);
		
		add(carInfoScrollPane, BorderLayout.CENTER);
	}
	
	public void appendCarData() {
		carInfoTextArea.setText("---- Car Info Stack ----\n");
		
		for(int i=0; i<parent.getCarDataBook().size(); ++i) {
			String carType = parent.getCarDataBook().get(i).getCarType();
			int carNum = parent.getCarDataBook().get(i).getCarNum();
			if(carType.equals("e")) {
				carInfoTextArea.append("Car type : electronic car(e)"
						+ ", Car number : " + carNum + "\n");
			}
			if(carType.equals("g")) {
				carInfoTextArea.append("Car type : Gasoline car(g)"
						+ ", Car number : " + carNum
						+ ", Engine Displacement : " + parent.getCarDataBook().get(i).getEngineDisplacement() + "\n");
			}
			if(carType.equals("v")) {
				carInfoTextArea.append("Car type : Van(v)"
						+ ", Car number : " + carNum
						+ ", Car size : " + parent.getCarDataBook().get(i).getVanSize() + "\n");
			}
			
		}
	}
	
}