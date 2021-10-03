package parkingLot.gui.leftPanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JPanel;

import parkingLot.gui.MainFrame;
import parkingLot.gui.leftPanel.commandStackPanel.CommandStackPanel;
import parkingLot.gui.leftPanel.inputPanel.CommandPanel;
import parkingLot.gui.leftPanel.inputPanel.EnterPanel;
import parkingLot.gui.leftPanel.inputPanel.IncomePanel;
import parkingLot.gui.leftPanel.inputPanel.LeavePanel;
import parkingLot.gui.leftPanel.inputPanel.NonePanel;
import parkingLot.gui.leftPanel.inputPanel.ShowPanel;
import parkingLot.gui.leftPanel.inputPanel.TimePanel;
import parkingLot.parkingLotTemplate.DailyIncome;

public class InputPanel extends JPanel{
	
	private String cmdType;
	
	// Layout
	private GridBagLayout gb;
    private GridBagConstraints gbc;
    
    private MainFrame parent;
	private JPanel timePanel, commandPanel, commandStackPanel, interfacePanel;
	
	public InputPanel(MainFrame parent) {
			
		setVisible(false);
		gb = new GridBagLayout();
        setLayout(gb);
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
		
		this.parent = parent;
	
		timePanel = new TimePanel(parent, this);
		gbAdd(timePanel, 0, 0, 12, 1, 1, 0.03);
		
		commandPanel = new CommandPanel(parent, this);
		gbAdd(commandPanel, 0, 1, 1, 1, 0.025, 0.2);
		
		interfacePanel = new NonePanel();
		gbAdd(interfacePanel, 1, 1, 11, 1, 0.975, 0.2);
		
		commandStackPanel = new CommandStackPanel();
		gbAdd(commandStackPanel, 0, 2, 12, 8, 1, 0.8);
		
	}
	
	public CommandStackPanel getCommandStackPanel() {
		return (CommandStackPanel)this.commandStackPanel;
	}

	public TimePanel getTimePanel() {return (TimePanel)this.timePanel;}
	
	public void setCmdType(String cmdType) {this.cmdType = cmdType;}
	
	public void updateInterfacePanel() {
		this.remove(interfacePanel);
		switch(cmdType){
		case "-none-":
			interfacePanel = new NonePanel();
			gbAdd(interfacePanel, 1, 1, 11, 1, 0.975, 0.2);
			break;
			
		case "enter":
			interfacePanel = new EnterPanel(parent, this);
			gbAdd(interfacePanel, 1, 1, 11, 1, 0.975, 0.2);
			break;
			
		case "income":
			interfacePanel = new IncomePanel(parent, this);
			gbAdd(interfacePanel, 1, 1, 11, 1, 0.975, 0.2);
			break;
			
		case "leave":
			interfacePanel = new LeavePanel(parent, this);
			gbAdd(interfacePanel, 1, 1, 11, 1, 0.975, 0.2);
			break;
			
		case "show":
			interfacePanel = new ShowPanel(parent, this);
			gbAdd(interfacePanel, 1, 1, 11, 1, 0.975, 0.2);
			break;
		}
		this.revalidate();
		this.repaint();
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












