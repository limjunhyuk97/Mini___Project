package parkingLot.gui.leftPanel.inputPanel;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.time.DateTimeException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import parkingLot.gui.MainFrame;
import parkingLot.gui.leftPanel.InputPanel;

public class TimePanel extends JPanel{
	
	private MainFrame parent;
	private InputPanel rootPanel;
	private int year, month, date, hour, minute;
	
	private JLabel outputTimeLabel;
	
	// Layout
	private GridBagLayout gb;
	private GridBagConstraints gbc;

	public TimePanel(MainFrame parent, InputPanel rootPanel){
		
		this.parent = parent;
		this.rootPanel = rootPanel;

		gb = new GridBagLayout();
        setLayout(gb);
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
		
		JPanel inputTimePanel = new JPanel();
		inputTimePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
				
	    outputTimeLabel = new JLabel("");
		outputTimeLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		outputTimeLabel.setHorizontalAlignment(JLabel.CENTER);
				
		JTextField cmdTimeTextField[] = new JTextField[5];
		JTextField yearTextField = new JTextField(4); cmdTimeTextField[0] = yearTextField;
		JTextField monthTextField = new JTextField(2); cmdTimeTextField[1] = monthTextField;
		JTextField dateTextField = new JTextField(2); cmdTimeTextField[2] = dateTextField;
		JTextField hourTextField = new JTextField(2); cmdTimeTextField[3] = hourTextField;
		JTextField minuteTextField = new JTextField(2); cmdTimeTextField[4] = minuteTextField;
		minuteTextField.addActionListener(event->{
			try {
				TimePanel.this.year = Integer.parseInt(yearTextField.getText()); 
				TimePanel.this.month = Integer.parseInt(monthTextField.getText());
				TimePanel.this.date = Integer.parseInt(dateTextField.getText());
				TimePanel.this.hour = Integer.parseInt(hourTextField.getText());
				TimePanel.this.minute = Integer.parseInt(minuteTextField.getText());
				if(TimePanel.this.parent.timeLineCheck(year, month, date, hour, minute) == false)
					if(this.parent.getTimeLine() != null)
						TimePanel.this.outputTimeLabel.setText("<html>   Commading Time Invalid<br>[마지막 입력 : "
							+ TimePanel.this.parent.getTimeLine().getYear()
							+ "/" + TimePanel.this.parent.getTimeLine().getMonthValue()
							+ "/" + TimePanel.this.parent.getTimeLine().getDayOfMonth()
							+ " " + TimePanel.this.parent.getTimeLine().getHour()
							+ ":" + TimePanel.this.parent.getTimeLine().getMinute() + " ]<html>");
					else
						TimePanel.this.outputTimeLabel.setText("Commading Time Invalid");
				else {
					TimePanel.this.outputTimeLabel.setText(year + "/" + month + "/" + date + " " + hour + ":" + minute + "의 입력.");
					TimePanel.this.parent.setCmdTimeInput(true);
					if(TimePanel.this.parent.commandCheck()) {
						TimePanel.this.rootPanel.getCommandStackPanel().appendCommandTime(year, month, date, hour, minute, TimePanel.this.parent.getCmdType());
						TimePanel.this.rootPanel.getTimePanel().setOutputTimeLabel("");
						TimePanel.this.rootPanel.updateInterfacePanel();
						TimePanel.this.parent.command();
					}
				}
				for(int i=0; i<5; ++i) cmdTimeTextField[i].setText("");	
			}
			catch(DateTimeException e){
				if(this.parent.getTimeLine() != null)
					TimePanel.this.outputTimeLabel.setText("<html>        Invalid time value<br>[마지막 입력 : "
						+ TimePanel.this.parent.getTimeLine().getYear()
						+ "/" + TimePanel.this.parent.getTimeLine().getMonthValue()
						+ "/" + TimePanel.this.parent.getTimeLine().getDayOfMonth()
						+ " " + TimePanel.this.parent.getTimeLine().getHour()
						+ ":" + TimePanel.this.parent.getTimeLine().getMinute() + " ]<html>");
				else
					TimePanel.this.outputTimeLabel.setText("Invalid time value");
			}
			catch(NumberFormatException e) {
				if(this.parent.getTimeLine() != null)
					TimePanel.this.outputTimeLabel.setText("<html>         Input number<br>[마지막 입력 : "
						+ TimePanel.this.parent.getTimeLine().getYear()
						+ "/" + TimePanel.this.parent.getTimeLine().getMonthValue()
						+ "/" + TimePanel.this.parent.getTimeLine().getDayOfMonth()
						+ " " + TimePanel.this.parent.getTimeLine().getHour()
						+ ":" + TimePanel.this.parent.getTimeLine().getMinute() + " ]<html>");
				else
					TimePanel.this.outputTimeLabel.setText("Input number");
			}
		});
				
		inputTimePanel.add(new JLabel("명령시간 : ")); 
		inputTimePanel.add(yearTextField);
		inputTimePanel.add(monthTextField);
		inputTimePanel.add(dateTextField); 
		inputTimePanel.add(hourTextField);
		inputTimePanel.add(minuteTextField);
				
		JButton timeButton = new JButton("입력");
		timeButton.setFont(new Font("맑은 고딕", Font.BOLD, 10));
		timeButton.addActionListener(event->{
			try {
				TimePanel.this.year = Integer.parseInt(yearTextField.getText()); 
				TimePanel.this.month = Integer.parseInt(monthTextField.getText());
				TimePanel.this.date = Integer.parseInt(dateTextField.getText());
				TimePanel.this.hour = Integer.parseInt(hourTextField.getText());
				TimePanel.this.minute = Integer.parseInt(minuteTextField.getText());
				if(TimePanel.this.parent.timeLineCheck(year, month, date, hour, minute) == false)
					if(this.parent.getTimeLine() != null)
						TimePanel.this.outputTimeLabel.setText("<html>   Commading Time Invalid<br>[마지막 입력 : "
							+ TimePanel.this.parent.getTimeLine().getYear()
							+ "/" + TimePanel.this.parent.getTimeLine().getMonthValue()
							+ "/" + TimePanel.this.parent.getTimeLine().getDayOfMonth()
							+ " " + TimePanel.this.parent.getTimeLine().getHour()
							+ ":" + TimePanel.this.parent.getTimeLine().getMinute() + " ]<html>");
					else
						TimePanel.this.outputTimeLabel.setText("Commading Time Invalid");
				else {
					TimePanel.this.outputTimeLabel.setText(year + "/" + month + "/" + date + " " + hour + ":" + minute + "의 입력.");
					TimePanel.this.parent.setCmdTimeInput(true);
					if(TimePanel.this.parent.commandCheck()) {
						TimePanel.this.rootPanel.getCommandStackPanel().appendCommandTime(year, month, date, hour, minute, TimePanel.this.parent.getCmdType());
						TimePanel.this.rootPanel.getTimePanel().setOutputTimeLabel("");
						TimePanel.this.rootPanel.updateInterfacePanel();
						TimePanel.this.parent.command();
					}
				}
				for(int i=0; i<5; ++i) cmdTimeTextField[i].setText("");	
			}
			catch(DateTimeException e){
				if(this.parent.getTimeLine() != null)
					TimePanel.this.outputTimeLabel.setText("<html>        Invalid time value<br>[마지막 입력 : "
						+ TimePanel.this.parent.getTimeLine().getYear()
						+ "/" + TimePanel.this.parent.getTimeLine().getMonthValue()
						+ "/" + TimePanel.this.parent.getTimeLine().getDayOfMonth()
						+ " " + TimePanel.this.parent.getTimeLine().getHour()
						+ ":" + TimePanel.this.parent.getTimeLine().getMinute() + " ]<html>");
				else
					TimePanel.this.outputTimeLabel.setText("Invalid time value");
			}
			catch(NumberFormatException e) {
				if(this.parent.getTimeLine() != null)
					TimePanel.this.outputTimeLabel.setText("<html>         Input number<br>[마지막 입력 : "
						+ TimePanel.this.parent.getTimeLine().getYear()
						+ "/" + TimePanel.this.parent.getTimeLine().getMonthValue()
						+ "/" + TimePanel.this.parent.getTimeLine().getDayOfMonth()
						+ " " + TimePanel.this.parent.getTimeLine().getHour()
						+ ":" + TimePanel.this.parent.getTimeLine().getMinute() + " ]<html>");
				else
					TimePanel.this.outputTimeLabel.setText("Input number");
			}
		});
				
		inputTimePanel.add(timeButton);
		
		gbAdd(inputTimePanel, 0, 0, 1, 1, 1, 0.5);
		gbAdd(outputTimeLabel, 0, 1, 1, 1, 1, 0.5);
		
	}
	
	private void gbAdd(JComponent c, int x, int y, int w, int h, double gbcw, double gbch){
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        gbc.weightx = gbcw;
        gbc.weighty = gbch;
        add(c, gbc);
    }
	
	public void setOutputTimeLabel(String str){
		this.outputTimeLabel.setText("");
	}
	
}
