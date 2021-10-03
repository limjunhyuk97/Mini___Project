package parkingLot.gui.rightPanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JPanel;

import parkingLot.gui.MainFrame;
import parkingLot.gui.rightPanel.outputPanel.CarDataPanel;
import parkingLot.gui.rightPanel.outputPanel.OutputInterfacePanel;
import parkingLot.gui.rightPanel.outputPanel.ParkingLotPanel;

public class RightPanel extends JPanel{
	
	// Layout
	private GridBagLayout gb;
	private GridBagConstraints gbc;
	
	private MainFrame parent;
	
	private CarDataPanel carDataPanel;
	private OutputInterfacePanel outputInterfacePanel;
	private ParkingLotPanel parkinglotPanel;
	
	public RightPanel(MainFrame parent) {
		
		this.parent = parent;
			
		// Layout
		setVisible(false);
		gb = new GridBagLayout();
        setLayout(gb);
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
		
        this.parkinglotPanel = new ParkingLotPanel(parent);
        gbAdd(parkinglotPanel, 0, 0, 1, 11, 1, 0.30);
        
		this.carDataPanel = new CarDataPanel(parent);
		gbAdd(carDataPanel, 0, 11, 1, 7, 1, 0.30);
		
		this.outputInterfacePanel = new OutputInterfacePanel(parent);
		gbAdd(outputInterfacePanel, 0, 18, 1, 2, 1, 0.40);
		
	}
	
	public CarDataPanel getCarDataPanel() {
		return this.carDataPanel;
	}
	
	public OutputInterfacePanel getOutputInterfacePanel() {
		return this.outputInterfacePanel;
	}
	
	public ParkingLotPanel getParkingLotPanel() {
		return this.parkinglotPanel;
	}
	
	private void gbAdd(JComponent c, int x, int y, int w, int h, double gbcw, double gbch){
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        gbc.weightx = gbcw;
        gbc.weighty = gbch;
        gbc.insets = new Insets(2, 2, 2, 2);
        add(c, gbc);
    }
	
}

