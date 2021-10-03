package parkingLot.gui.leftPanel.inputPanel;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import parkingLot.gui.MainFrame;
import parkingLot.gui.leftPanel.InputPanel;

public class CommandPanel extends JPanel{
	
	private MainFrame parent;
	private InputPanel rootPanel;
	
	private String command;
	private JPanel commandTypeLabelPanel, commandTypeComboBoxPanel;
	private JLabel commandType;
	private JComboBox<String> commandTypeList;
	
	// Layout
	private GridBagLayout gb;
	private GridBagConstraints gbc;

	public CommandPanel(MainFrame parent, InputPanel rootPanel){
		
		this.rootPanel = rootPanel;
		this.parent = parent;
			
		gb = new GridBagLayout();
        setLayout(gb);
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
		
		commandTypeLabelPanel = new JPanel();
		commandTypeComboBoxPanel = new JPanel();
		
		commandType = new JLabel("¸í·ÉÁ¾·ù");
		commandType.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		
		commandTypeList = new JComboBox<>();
		commandTypeList.addItem("-none-");
		commandTypeList.addItem("enter");
		commandTypeList.addItem("leave");
		commandTypeList.addItem("show");
		commandTypeList.addItem("income");
		commandTypeList.addActionListener((event)->{
			CommandPanel.this.rootPanel.setCmdType(commandTypeList.getItemAt(commandTypeList.getSelectedIndex()));
			ShowInterface showInterface = new ShowInterface(rootPanel);
			showInterface.updatePanel();
		});
		
		gbAdd(commandType, 0, 0, 1, 1, 1 , 0.01);
		gbAdd(commandTypeList, 0, 1, 1, 1, 1, 0.001);
		
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

class ShowInterface{
	
	private InputPanel rootPanel;
	
	public ShowInterface(InputPanel rootPanel) {
		this.rootPanel = rootPanel;
	}
	
	public void updatePanel() {
		rootPanel.updateInterfacePanel();
	}
}
