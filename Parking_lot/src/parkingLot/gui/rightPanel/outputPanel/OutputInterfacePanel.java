package parkingLot.gui.rightPanel.outputPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import parkingLot.gui.MainFrame;

public class OutputInterfacePanel extends JPanel{

	private MainFrame parent;
	private JTextArea outputInterfaceTextArea;
	private JScrollPane outputInterfaceScrollPane;
	
	public OutputInterfacePanel(MainFrame parent) {
		
		this.parent = parent;
		
		setLayout(new BorderLayout());
		
		outputInterfaceTextArea = new JTextArea();
		outputInterfaceScrollPane = new JScrollPane(outputInterfaceTextArea);
		outputInterfaceTextArea.setText("Command Data\n\n");
		outputInterfaceTextArea.setEditable(false);
		outputInterfaceTextArea.setBackground(Color.DARK_GRAY);
		outputInterfaceTextArea.setFont(new Font("±¼¸²Ã¼", Font.BOLD, 18));
		outputInterfaceTextArea.setForeground(Color.white);
		
		add(outputInterfaceScrollPane, BorderLayout.CENTER);
	}
	
	public void appendNewCommand(String str) {
		outputInterfaceTextArea.setText("Command Data\n\n");
		outputInterfaceTextArea.append(str);
		
	}
	
}
