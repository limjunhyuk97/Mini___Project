package parkingLot.gui.leftPanel.inputPanel;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextField;
import java.time.LocalDateTime;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import parkingLot.gui.MainFrame;
import parkingLot.gui.leftPanel.InputPanel;

public class IncomePanel extends JPanel{
	
	private int year, month, date;
	private boolean timeCheck = false;
	
	// Layout
	private GridBagLayout gb;
	private GridBagConstraints gbc;
	
	private MainFrame parent;
	private InputPanel rootPanel;
	
	private JPanel yearPanel, monthPanel, datePanel;
	private JLabel yearLabel, monthLabel, dateLabel;
	private JTextField yearTextField, monthTextField, dateTextField;
	private JButton inputButton;
	
	public IncomePanel(MainFrame parent, InputPanel rootPanel) {
		
		this.parent = parent;
		this.rootPanel = rootPanel;
		
		// Layout
		FlowLayout fl = new FlowLayout(FlowLayout.LEFT);
		setLayout(fl);
		gb = new GridBagLayout();
		setLayout(gb);
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		
		// Label
		yearLabel = new JLabel("년 :");
		yearLabel.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		monthLabel = new JLabel("월 :");
		monthLabel.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		dateLabel = new JLabel("일 :");
		dateLabel.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		
		// Panel
		yearPanel = new JPanel(); yearPanel.setLayout(fl);
		monthPanel = new JPanel(); monthPanel.setLayout(fl);
		datePanel = new JPanel(); datePanel.setLayout(fl);
				
		// Year
		yearPanel.add(yearLabel);
		yearTextField = new JTextField(5);
		yearPanel.add(yearTextField);
		
		// Month
		monthPanel.add(monthLabel);
		monthTextField = new JTextField(5);
		monthPanel.add(monthTextField);
		
		// Date
		datePanel.add(dateLabel);
		dateTextField = new JTextField(5);
		dateTextField.addActionListener(event->{
			IncomePanel.this.year = Integer.parseInt(yearTextField.getText());
			IncomePanel.this.month = Integer.parseInt(monthTextField.getText());
			IncomePanel.this.date = Integer.parseInt(dateTextField.getText());
			if(IncomePanel.this.parent.getAccountingBook().size() == 0) {
				// 오류 처리 내용 OutputInterface에 출력 : 아직 accountingBook이 생성되지 않음
				parent.getRightPanel().getOutputInterfacePanel().appendNewCommand("Income Data doesn't exist\n");
				IncomePanel.this.rootPanel.updateInterfacePanel();
			}
			else {
				if(IncomePanel.this.parent.getCmdTimeInput()) {
					for(int i = 0; i<IncomePanel.this.parent.getAccountingBook().size(); ++i) {
						// 일치하는 날짜를 찾은 경우
						if( IncomePanel.this.parent.getAccountingBook().get(i).getLocalDateTime().equals(LocalDateTime.of(year, month, date, 0, 0)) ) {
							IncomePanel.this.timeCheck = true;
							IncomePanel.this.parent.setIncomeYear(IncomePanel.this.year);
							IncomePanel.this.parent.setIncomeMonth(IncomePanel.this.month);
							IncomePanel.this.parent.setIncomeDate(IncomePanel.this.date);
							IncomePanel.this.parent.setCmdType("income");
							IncomePanel.this.parent.setCmdInput(true);
							// income 정보에 대한 처리
							if(IncomePanel.this.parent.commandCheck()) {
								int year = IncomePanel.this.parent.getTimeLine().getYear();
								int month = IncomePanel.this.parent.getTimeLine().getMonthValue();
								int date = IncomePanel.this.parent.getTimeLine().getDayOfMonth();
								int hour = IncomePanel.this.parent.getTimeLine().getHour();
								int minute = IncomePanel.this.parent.getTimeLine().getMinute();
								IncomePanel.this.rootPanel.getCommandStackPanel().appendCommandTime(year, month, date, hour, minute, IncomePanel.this.parent.getCmdType());
								IncomePanel.this.rootPanel.getTimePanel().setOutputTimeLabel("");
								IncomePanel.this.rootPanel.updateInterfacePanel();
								IncomePanel.this.parent.command();
							}
							break;
						}
						
					}
					
					// 오류 처리 내용 OutputInterface에 출력 : 존재하지 않는 날짜에 대한 접근
					if(!IncomePanel.this.timeCheck) {
						parent.getRightPanel().getOutputInterfacePanel().appendNewCommand("There is no income information for that date\n");
						IncomePanel.this.rootPanel.updateInterfacePanel();
					}
					
				}
				else {
					parent.getRightPanel().getOutputInterfacePanel().appendNewCommand("Please Input 명령 시간 first\n");
					IncomePanel.this.rootPanel.updateInterfacePanel();
				}
			}
		});
		datePanel.add(dateTextField);
		
		// InputButton
		inputButton = new JButton("입력");
		inputButton.addActionListener(event->{
			IncomePanel.this.year = Integer.parseInt(yearTextField.getText());
			IncomePanel.this.month = Integer.parseInt(monthTextField.getText());
			IncomePanel.this.date = Integer.parseInt(dateTextField.getText());
			if(IncomePanel.this.parent.getAccountingBook().size() == 0) {
				// 오류 처리 내용 OutputInterface에 출력 : 아직 accountingBook이 생성되지 않음
				parent.getRightPanel().getOutputInterfacePanel().appendNewCommand("Income Data doesn't exist\n");
				IncomePanel.this.rootPanel.updateInterfacePanel();
			}
			else {
				if(IncomePanel.this.parent.getCmdTimeInput()) {
					for(int i = 0; i<IncomePanel.this.parent.getAccountingBook().size(); ++i) {
						// 일치하는 날짜를 찾은 경우
						if( IncomePanel.this.parent.getAccountingBook().get(i).getLocalDateTime().equals(LocalDateTime.of(year, month, date, 0, 0)) ) {
							IncomePanel.this.timeCheck = true;
							IncomePanel.this.parent.setIncomeYear(IncomePanel.this.year);
							IncomePanel.this.parent.setIncomeMonth(IncomePanel.this.month);
							IncomePanel.this.parent.setIncomeDate(IncomePanel.this.date);
							IncomePanel.this.parent.setCmdType("income");
							IncomePanel.this.parent.setCmdInput(true);
							// income 정보에 대한 처리
							if(IncomePanel.this.parent.commandCheck()) {
								int year = IncomePanel.this.parent.getTimeLine().getYear();
								int month = IncomePanel.this.parent.getTimeLine().getMonthValue();
								int date = IncomePanel.this.parent.getTimeLine().getDayOfMonth();
								int hour = IncomePanel.this.parent.getTimeLine().getHour();
								int minute = IncomePanel.this.parent.getTimeLine().getMinute();
								IncomePanel.this.rootPanel.getCommandStackPanel().appendCommandTime(year, month, date, hour, minute, IncomePanel.this.parent.getCmdType());
								IncomePanel.this.rootPanel.getTimePanel().setOutputTimeLabel("");
								IncomePanel.this.rootPanel.updateInterfacePanel();
								IncomePanel.this.parent.command();
							}
							break;
						}
						
					}
					
					// 오류 처리 내용 OutputInterface에 출력 : 존재하지 않는 날짜에 대한 접근
					if(!IncomePanel.this.timeCheck) {
						parent.getRightPanel().getOutputInterfacePanel().appendNewCommand("There is no income information for that date\n");
						IncomePanel.this.rootPanel.updateInterfacePanel();
					}
					
				}
				else {
					parent.getRightPanel().getOutputInterfacePanel().appendNewCommand("Please Input 명령 시간 first\n");
					IncomePanel.this.rootPanel.updateInterfacePanel();
				}
			}
		});
		
		// Layout
		gbAdd(yearPanel, 0, 0, 1, 1, 0, 0);
		gbAdd(monthPanel, 0, 1, 1, 1, 0, 0);
		gbAdd(datePanel, 0, 2, 1, 1, 0, 0);
		gbAdd(inputButton, 0, 3, 1, 1, 0, 0);
		
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
	
	public String toString() {return "income";}
}














