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

public class ShowPanel extends JPanel{

	private MainFrame parent;
	private InputPanel rootPanel;
	
	private JPanel carNumPanel;
	private JLabel carNumLabel;
	private JTextField carNumTextField;
	private JButton inputButton;
	
	public ShowPanel(MainFrame parent, InputPanel rootPanel) {
		
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
				ShowPanel.this.parent.setCmdType("show");
				ShowPanel.this.parent.setCmdInput(true);
				// show 처리
				if(ShowPanel.this.parent.commandCheck()) {
					int year = ShowPanel.this.parent.getTimeLine().getYear();
					int month = ShowPanel.this.parent.getTimeLine().getMonthValue();
					int date = ShowPanel.this.parent.getTimeLine().getDayOfMonth();
					int hour = ShowPanel.this.parent.getTimeLine().getHour();
					int minute = ShowPanel.this.parent.getTimeLine().getMinute();
					ShowPanel.this.rootPanel.getCommandStackPanel().appendCommandTime(year, month, date, hour, minute, ShowPanel.this.parent.getCmdType());
					ShowPanel.this.rootPanel.getTimePanel().setOutputTimeLabel("");
					ShowPanel.this.rootPanel.updateInterfacePanel();
					ShowPanel.this.parent.command();
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
				ShowPanel.this.parent.setCmdType("show");
				ShowPanel.this.parent.setCmdInput(true);
				// show 처리
				if(ShowPanel.this.parent.commandCheck()) {
					int year = ShowPanel.this.parent.getTimeLine().getYear();
					int month = ShowPanel.this.parent.getTimeLine().getMonthValue();
					int date = ShowPanel.this.parent.getTimeLine().getDayOfMonth();
					int hour = ShowPanel.this.parent.getTimeLine().getHour();
					int minute = ShowPanel.this.parent.getTimeLine().getMinute();
					ShowPanel.this.rootPanel.getCommandStackPanel().appendCommandTime(year, month, date, hour, minute, ShowPanel.this.parent.getCmdType());
					ShowPanel.this.rootPanel.getTimePanel().setOutputTimeLabel("");
					ShowPanel.this.rootPanel.updateInterfacePanel();
					ShowPanel.this.parent.command();
				} // 명령 입력 최신화
			}
		});
		
		carNumPanel.add(carNumLabel);
		carNumPanel.add(carNumTextField);
		carNumPanel.add(inputButton);
		add(carNumPanel, BorderLayout.CENTER);
		
	}
	
	
	public String toString() { return "show"; }
}
