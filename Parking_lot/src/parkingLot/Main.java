package parkingLot;

import java.awt.EventQueue;

import javax.swing.JFrame;

import parkingLot.gui.MainFrame;

public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(()->{
			MainFrame frame = new MainFrame();
			frame.setTitle("ParkingLot Management System");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}
}
