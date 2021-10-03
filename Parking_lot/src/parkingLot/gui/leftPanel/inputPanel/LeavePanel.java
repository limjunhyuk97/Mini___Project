package parkingLot.gui.leftPanel.inputPanel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import parkingLot.gui.MainFrame;
import parkingLot.gui.leftPanel.InputPanel;
import parkingLot.parkingLotTemplate.CarData;

public class LeavePanel extends JPanel{
	
	private MainFrame parent;
	private InputPanel rootPanel;
	
	private JPanel carNumPanel;
	private JLabel carNumLabel;
	private JTextField carNumTextField;
	private JButton inputButton;
	
	public LeavePanel(MainFrame parent, InputPanel rootPanel) {
		
		this.parent = parent;
		this.rootPanel = rootPanel;
		
		setLayout(new BorderLayout());
		
		// Panel
		carNumPanel = new JPanel();
		carNumPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		// Label
		carNumLabel = new JLabel("차량 번호 :");
		carNumLabel.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		carNumLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		// TextField
		carNumTextField = new JTextField(5);
		carNumTextField.addActionListener(event->{
			if(Integer.parseInt(carNumTextField.getText()) < 1000 || Integer.parseInt(carNumTextField.getText()) > 9999) {
				// 에러 메시지 출력
				parent.getRightPanel().getOutputInterfacePanel().appendNewCommand("Car number invalid");
			}
			else {
				this.parent.setCarNum(Integer.parseInt(carNumTextField.getText()));
				LeavePanel.this.parent.setCmdType("leave");
				LeavePanel.this.parent.setCmdInput(true);
				// leave 처리
				if(LeavePanel.this.parent.commandCheck()) {
					int year = LeavePanel.this.parent.getTimeLine().getYear();
					int month = LeavePanel.this.parent.getTimeLine().getMonthValue();
					int date = LeavePanel.this.parent.getTimeLine().getDayOfMonth();
					int hour = LeavePanel.this.parent.getTimeLine().getHour();
					int minute = LeavePanel.this.parent.getTimeLine().getMinute();
					LeavePanel.this.rootPanel.getCommandStackPanel().appendCommandTime(year, month, date, hour, minute, LeavePanel.this.parent.getCmdType());
					LeavePanel.this.rootPanel.getTimePanel().setOutputTimeLabel("");
					LeavePanel.this.rootPanel.updateInterfacePanel();
					LeavePanel.this.parent.command();
				} // 명령 입력 최신화
			}
		});
		
		// Button
		inputButton = new JButton("입력");
		inputButton.addActionListener(event->{
			if(Integer.parseInt(carNumTextField.getText()) < 1000 || Integer.parseInt(carNumTextField.getText()) > 9999) {
				// 에러 메시지 출력
				parent.getRightPanel().getOutputInterfacePanel().appendNewCommand("Car number invalid");
			}
			else {
				this.parent.setCarNum(Integer.parseInt(carNumTextField.getText()));
				LeavePanel.this.parent.setCmdType("leave");
				LeavePanel.this.parent.setCmdInput(true);
				// leave 처리
				if(LeavePanel.this.parent.commandCheck()) {
					int year = LeavePanel.this.parent.getTimeLine().getYear();
					int month = LeavePanel.this.parent.getTimeLine().getMonthValue();
					int date = LeavePanel.this.parent.getTimeLine().getDayOfMonth();
					int hour = LeavePanel.this.parent.getTimeLine().getHour();
					int minute = LeavePanel.this.parent.getTimeLine().getMinute();
					LeavePanel.this.rootPanel.getCommandStackPanel().appendCommandTime(year, month, date, hour, minute, LeavePanel.this.parent.getCmdType());
					LeavePanel.this.rootPanel.getTimePanel().setOutputTimeLabel("");
					LeavePanel.this.rootPanel.updateInterfacePanel();
					LeavePanel.this.parent.command();
				} // 명령 입력 최신화
			}
		});
		
		carNumPanel.add(carNumLabel);
		carNumPanel.add(carNumTextField);
		carNumPanel.add(inputButton);
		add(carNumPanel, BorderLayout.CENTER);
		
	}
}

