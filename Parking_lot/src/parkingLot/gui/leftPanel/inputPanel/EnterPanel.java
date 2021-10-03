package parkingLot.gui.leftPanel.inputPanel;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import parkingLot.gui.MainFrame;
import parkingLot.gui.leftPanel.InputPanel;
import parkingLot.parkingLotTemplate.CarData;

public class EnterPanel extends JPanel{
	
	// Layout
	private GridBagLayout gb;
	private GridBagConstraints gbc;
	
	private MainFrame parent;
	private InputPanel rootPanel;
	
	private String carType;
	private int carNum, additionalInfo;
	
	private JPanel carTypePanel, carNumPanel, additionalInfoPanel;
	private JLabel carTypeLabel, carNumLabel, additionalInfoLabel;
	private ButtonGroup cartypeButtonGroup;
	private JTextField carNumTextField;
	private JTextField additionalInfoTextField;
	private JButton inputButton;
	
	private static final int DEFAULT_TYPE = 1;
	private static final String DEFAULT_NAME = "g";
	
	public EnterPanel(MainFrame parent, InputPanel rootPanel) {
		
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
		carTypeLabel = new JLabel("차종 입력 (g/e/v) :");
		carTypeLabel.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		carNumLabel = new JLabel("차량 번호 :");
		carNumLabel.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		additionalInfoLabel = new JLabel("부가 정보 :");
		additionalInfoLabel.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		
		// Panel
		carTypePanel = new JPanel(); carTypePanel.setLayout(fl);
		carNumPanel = new JPanel(); carNumPanel.setLayout(fl);
		additionalInfoPanel = new JPanel(); additionalInfoPanel.setLayout(fl);
		
		// carType
		carTypePanel.add(carTypeLabel);
		cartypeButtonGroup = new ButtonGroup();
		addRadioButton("g", 1);
		addRadioButton("e", 2);
		addRadioButton("v", 3);
		
		// carNum
		carNumPanel.add(carNumLabel);
		carNumTextField = new JTextField(5);
		carNumPanel.add(carNumTextField);
		
		// AdditionalInfo
		additionalInfoPanel.add(additionalInfoLabel);
		additionalInfoTextField = new JTextField(5);
		additionalInfoTextField.addActionListener(event->{
			EnterPanel.this.carNum = Integer.parseInt(carNumTextField.getText());
			EnterPanel.this.additionalInfo = Integer.parseInt(additionalInfoTextField.getText());
			
			// 올바른 정보가 들어왔다면
			if(carDataFilter(carType, carNum, additionalInfo)) {
				EnterPanel.this.parent.setCarType(carType);
				EnterPanel.this.parent.setCarNum(carNum);
				EnterPanel.this.parent.setAdditionalInfo(additionalInfo);
				EnterPanel.this.parent.setCmdType("enter");
				EnterPanel.this.parent.setCmdInput(true);
				if(EnterPanel.this.parent.commandCheck()) {
					// 명령 입력 최신화, time 보이는 것 없앰
					int year = EnterPanel.this.parent.getTimeLine().getYear();
					int month = EnterPanel.this.parent.getTimeLine().getMonthValue();
					int date = EnterPanel.this.parent.getTimeLine().getDayOfMonth();
					int hour = EnterPanel.this.parent.getTimeLine().getHour();
					int minute = EnterPanel.this.parent.getTimeLine().getMinute();
					EnterPanel.this.rootPanel.getCommandStackPanel().appendCommandTime(year, month, date, hour, minute, EnterPanel.this.parent.getCmdType());
					EnterPanel.this.rootPanel.getTimePanel().setOutputTimeLabel("");
					EnterPanel.this.rootPanel.updateInterfacePanel();
				}
			}
			
			// 아니면 오류 발생
			else {
				
			}
		});
		additionalInfoPanel.add(additionalInfoTextField);
		
		// InputButton
		inputButton = new JButton("입력");
		inputButton.addActionListener(event->{
			EnterPanel.this.carNum = Integer.parseInt(carNumTextField.getText());
			EnterPanel.this.additionalInfo = Integer.parseInt(additionalInfoTextField.getText());
			
			// 올바른 정보가 들어왔다면
			if(carDataFilter(carType, carNum, additionalInfo)) {
				EnterPanel.this.parent.setCarType(carType);
				EnterPanel.this.parent.setCarNum(carNum);
				EnterPanel.this.parent.setAdditionalInfo(additionalInfo);
				EnterPanel.this.parent.setCmdType("enter");
				EnterPanel.this.parent.setCmdInput(true);
				if(EnterPanel.this.parent.commandCheck()) {
					// 명령 입력 최신화, time 보이는 것 없앰
					int year = EnterPanel.this.parent.getTimeLine().getYear();
					int month = EnterPanel.this.parent.getTimeLine().getMonthValue();
					int date = EnterPanel.this.parent.getTimeLine().getDayOfMonth();
					int hour = EnterPanel.this.parent.getTimeLine().getHour();
					int minute = EnterPanel.this.parent.getTimeLine().getMinute();
					EnterPanel.this.rootPanel.getCommandStackPanel().appendCommandTime(year, month, date, hour, minute, EnterPanel.this.parent.getCmdType());
					EnterPanel.this.rootPanel.getTimePanel().setOutputTimeLabel("");
					EnterPanel.this.rootPanel.updateInterfacePanel();
					EnterPanel.this.parent.command();
				}
			}
			
			// 아니면 오류 발생
			else {
				
			}
		});
		
		// Layout
		gbAdd(carTypePanel, 0, 0, 1, 1, 0, 0);
		gbAdd(carNumPanel, 0, 1, 1, 1, 0, 0);
		gbAdd(additionalInfoPanel, 0, 2, 1, 1, 0, 0);
		gbAdd(inputButton, 0, 3, 1, 1, 0, 0);
		
	}
	
	public void addRadioButton(String name, int type) {
		boolean selected = (type == DEFAULT_TYPE);
		if(name.equals(DEFAULT_NAME)) carType = name;
		JRadioButton rb = new JRadioButton(name, selected);
		rb.addActionListener(event->{
			carType = name;
		});
		cartypeButtonGroup.add(rb);
		carTypePanel.add(rb);
	}
	
	// 오류에 대한 출력 수정 요구
	public boolean carDataFilter(String type, int num, int info) {
		
		String carType = type;
		int carNum = num;
		int selec = info;
		
		// 선택 사항 정보 입력 오류 [사용자 정의] 
		if(!(carType.equals("g") || carType.equals("e") || carType.equals("v")))
			return false;
		
		// 가능한 속성인가?
		if(carNum < 1000 || carNum > 9999) {
			parent.getRightPanel().getOutputInterfacePanel().appendNewCommand("Car number invalid");
			return false;
		}
		
		if(carType.equals("g")) {
			// 가능한 값인가?
			if(selec <= 0) {
				parent.getRightPanel().getOutputInterfacePanel().appendNewCommand("Engine Displacement invalid");
				return false;
			}
		}
		else if(carType.equals("e")) {
			// electric : 가능한 값인가?
			if(selec <= 0 || selec > 60) {
				parent.getRightPanel().getOutputInterfacePanel().appendNewCommand("Battery Level invalid");
				return false;
			}
		}
		else if(carType.equals("v")) {
			// 가능한 값인가?
			if(selec <= 0 || selec > 3) {
				parent.getRightPanel().getOutputInterfacePanel().appendNewCommand("Van size invalid");
				return false;
			}
		}
		
		// (번호 비교) 이미 입차한 차 이다.
		for(int i=0; i<parent.getMaxCar().MAXCAR; ++i) {
			if(parent.getParkingLot()[i] == null) continue;
			// 이미 입차한 차와 동일 차 번호를 지니는가?
			if(parent.getParkingLot()[i].getCarNum() == carNum) {
				parent.getRightPanel().getOutputInterfacePanel().appendNewCommand("Car number (" + carNum + ") already parked!\n");
				return false;
			}
		}
		
		// (번호 비교) 이전에 입차했던 차인데, 속성 괜찮은가?
		for(int i=0; i<parent.getCarDataBook().size(); ++i) {
			CarData tmp = parent.getCarDataBook().get(i);
			// 이전에 입차한 차와 동일 차 번호를 지니는데, 차 속성이 다른가?
			if(tmp.getCarNum() == carNum) {
				if(!tmp.getCarType().equals(carType)) {
					parent.getRightPanel().getOutputInterfacePanel().appendNewCommand("Wrong car type input");
					return false;
				}
				else {
					if(carType.equals("g")) {
						// gasoline : 속성이 다른가?
						if((tmp.getEngineDisplacement() != selec)) {
							parent.getRightPanel().getOutputInterfacePanel().appendNewCommand("Wrong engine displacement input");
							return false;
						}
					}
					else if(carType.equals("v")) {
						// van : 속성이 다른가?
						if((tmp.getVanSize().ordinal()+1) != selec) {
							parent.getRightPanel().getOutputInterfacePanel().appendNewCommand("Wrong van size input");
							return false;
						}
					}
				}
			}			
		}
							
		// 이외에 정상의 상황이다 (0)
		return true;
		
	} // carDataFilter method end
	
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
	
	public String toString() { return "enter"; }
}












