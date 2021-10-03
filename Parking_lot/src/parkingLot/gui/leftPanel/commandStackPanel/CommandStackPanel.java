package parkingLot.gui.leftPanel.commandStackPanel;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import parkingLot.gui.MainFrame;

public class CommandStackPanel extends JPanel{
	private JTextArea commandTimeStack;
	private JScrollPane commandTimeStackscrollPane;
	
	public CommandStackPanel() {
				
		setLayout(new BorderLayout());
		
		commandTimeStack = new JTextArea();
		commandTimeStackscrollPane = new JScrollPane(commandTimeStack);
		commandTimeStack.setText("---- Command Stack Space ----\n");
		commandTimeStack.setEditable(false);
		
		add(commandTimeStackscrollPane, BorderLayout.CENTER);
	}
	
	public void appendCommandTime(int year, int month, int date, int hour, int minute, String command) {
		commandTimeStack.append(year + "-"+month+"-" + date+". " + hour + ":" + minute + ". " + command+"\n");
	}
	
}
