package parkingLot.gui.leftPanel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import car.Car;
import parkingLot.gui.MainFrame;
import parkingLot.gui.MainFrame.MaximumCarNumber;

public class MaxCarPanel extends JPanel{
	
	MainFrame parent;
	
	public MaxCarPanel(MainFrame parent, ArrayList<JPanel> ShowAll){
		
		JTextField totcarTextField = new JTextField(10);
		this.parent = parent;
		
		JLabel totcarLabel = new JLabel("ÀÔÂ÷°¡´ÉÇÑ Â÷·® ´ë¼ö ÁöÁ¤");
		totcarLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		
		JButton startButton = new JButton("ÀÔ·Â");
		startButton.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 10));
		
		totcarTextField.addActionListener(event->{
			MaxCarPanel.this.parent.setMaxCar( Integer.parseInt(totcarTextField.getText()) ); 
			MaxCarPanel.this.parent.setParkingLot( Integer.parseInt(totcarTextField.getText()) );
			if(Integer.parseInt(totcarTextField.getText()) != 0) {
				for(int i=0; i<ShowAll.size(); ++i) ShowAll.get(i).setVisible(true);
				startButton.setEnabled(false);
				totcarTextField.setEnabled(false);
			}
		});
		startButton.addActionListener(event->{ 
			MaxCarPanel.this.parent.setMaxCar( Integer.parseInt(totcarTextField.getText()) ); 
			MaxCarPanel.this.parent.setParkingLot( Integer.parseInt(totcarTextField.getText()) );
			if(Integer.parseInt(totcarTextField.getText()) != 0) {
				for(int i=0; i<ShowAll.size(); ++i) ShowAll.get(i).setVisible(true);
				startButton.setEnabled(false);
				totcarTextField.setEnabled(false);
			}
		});
		
		JPanel totcarInputPanel = new JPanel();	totcarInputPanel.add(totcarTextField); totcarInputPanel.add(startButton);
		setPreferredSize(new Dimension(200, 60));
		setLayout(new GridLayout(2,1));
		add(totcarLabel); totcarLabel.setHorizontalAlignment(JLabel.CENTER);
		add(totcarInputPanel);
	}
	
}