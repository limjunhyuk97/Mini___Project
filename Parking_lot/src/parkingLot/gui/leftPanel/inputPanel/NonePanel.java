package parkingLot.gui.leftPanel.inputPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NonePanel extends JPanel{

	private JPanel selectCommandPanel;
	private JLabel selectCommand;
	
	public NonePanel() {
		
		setLayout(new BorderLayout());
		selectCommand = new JLabel("Select Command");
		selectCommand.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		selectCommand.setAlignmentX(CENTER_ALIGNMENT);
		
		selectCommandPanel = new JPanel();
		selectCommandPanel.add(selectCommand);
		add(selectCommandPanel, BorderLayout.CENTER);
	}
}
