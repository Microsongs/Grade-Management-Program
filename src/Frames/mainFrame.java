package Frames;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import File.inputPaner;
import File.loadFfile;
import File.saveFfile;
import attend.AttendMain;
import attend.AttendWeek;
import score.inputScore;
import score.scoreCheck;
import score.setApplyRate;
import score.setRankRate;
import stats.Avgprint;
import stats.Stdprint;
import stats.graph;

public class mainFrame extends JFrame{
	
	private JMenuBar bar;		//메뉴바
	private JMenu fileMenu;		//파일 메뉴
	private JMenu attendMenu;	//출석 메뉴
	private JMenu gradeMenu;	//성적 메뉴
	private JMenu statsMenu;	//통계 메뉴
	private JToolBar toolbar;      //툴바 
	private JPanel mainPanel;
	
	//툴바 이외의 패널은 Java파일을 나누어서 구현
	//메뉴바는 패널에 붙일 수 없으므로 Frame에서 처리
	public mainFrame() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		createMenu();	//메뉴 생성	
		this.setSize(1000,800);	
		this.setVisible(true);
		this.setTitle("성적 관리 프로그램");
		mainPanel = new JPanel();	//다른 JPanel 객체를 넣으면서 체크
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		defaultMenu();
	}
	
	public void defaultMenu() {
		JPanel tempPanel = new JPanel();
		ImageIcon ic = new ImageIcon("main.png");
		JLabel imgLabel = new JLabel(ic);	//이미지라벨
		
		tempPanel.add(imgLabel);
		tempPanel.setSize(600,400);
		mainPanel.removeAll();	//전부 날리기
		mainPanel.add(tempPanel);
		mainPanel.updateUI();	//패널 업데이트
		mainPanel.setVisible(true);
	}
	
	public void createMenu() {
		bar = new JMenuBar();
		createToolbar();
		fileMenu = new JMenu("파일");
		createFileMenu();
		
		attendMenu = new JMenu("출석");
		createAttendMenu();
		
		gradeMenu = new JMenu("성적");
		createGradeMenu();
		
		statsMenu = new JMenu("통계");
		createStatsMenu();
		
		bar.add(fileMenu);
		bar.add(attendMenu);
		bar.add(gradeMenu);
		bar.add(statsMenu);
		setJMenuBar(bar);
	}
	
	//툴바 관련 메서드
	public void createToolbar() {
		// 툴바 - 열기 이미지
		ImageIcon iconOpen = new ImageIcon("open.png");
		Action actionOpen = new AbstractAction("Open", iconOpen) {
					
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				loadFfile loadCVS = new loadFfile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
						
			}
		};	
		// 툴바 - 저장 이미지
		ImageIcon iconSave = new ImageIcon("save.png");
		Action actionSave = new AbstractAction("Save", iconSave) {
					
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				saveFfile saveCVS = new saveFfile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				}			
			}
		};
				
				// 툴바 - 통계 이미지
		ImageIcon iconStats = new ImageIcon("stats.png");
		Action actionStats = new AbstractAction("Stats", iconStats) {		
			@Override
			public void actionPerformed(ActionEvent e) {
				graph gradegraph = new graph(); //그래프 생성!!
						
			}
		};
				
						
		toolbar = new JToolBar();
		JButton btnOpen = toolbar.add(actionOpen); // 열기 버튼 추가
		JButton btnSave = toolbar.add(actionSave); // 저장 버튼 추가
		JButton btnStats = toolbar.add(actionStats); // 통계 버튼 추가
		getContentPane().add(toolbar, BorderLayout.NORTH);
	}
	
	//////////////////////////////////////////////////////////////////////////
	//파일 메뉴 관련 메서드
	public void createFileMenu() {
		int maxMenu = 3;
		String[] fileTitle= {"학생정보 입력","저장하기","불러오기"};
		JMenuItem[] fileItem = new JMenuItem[maxMenu];
		for(int i=0;i<maxMenu;i++) {
			fileItem[i] = new JMenuItem(fileTitle[i]);
			fileItem[i].addActionListener(new fileActionListener());// 메뉴아이템에 Action 리스너 설정screenMenu.add(item);
			fileMenu.add(fileItem[i]);
		}
	}
	//출석 메뉴를 생성
	public void createAttendMenu() {
		int maxMenu = 2;
		String[] attendTitle= {"주별 확인","전체 확인"};
		JMenuItem[] attendItem = new JMenuItem[maxMenu];
		for(int i=0;i<maxMenu;i++) {
			attendItem[i] = new JMenuItem(attendTitle[i]);
			attendItem[i].addActionListener(new attendActionListener());// 메뉴아이템에 Action 리스너 설정screenMenu.add(item);
			attendMenu.add(attendItem[i]);
		}
	}
	//성적 메뉴를 생성
	void createGradeMenu() {
		int maxMenu = 4;
		String[] gradeTitle= {"성적 입력", "반영 비율","등급 비율","점수 확인"};
		JMenuItem[] gradeItem = new JMenuItem[maxMenu];
		for(int i=0;i<maxMenu;i++) {			
			gradeItem[i] = new JMenuItem(gradeTitle[i]);
			gradeItem[i].addActionListener(new gradeActionListener());// 메뉴아이템에 Action 리스너 설정screenMenu.add(item);
			gradeMenu.add(gradeItem[i]);
		}
	}
	
	//통계 메뉴 관련 메서드
	void createStatsMenu() {
		int maxMenu =3;
		String[] statsTitle= {"평균 출력", "표준 편차 출력","그래프 출력"};
		JMenuItem[] statsItem = new JMenuItem[maxMenu];
		for(int i=0;i<maxMenu;i++) {
			statsItem[i] = new JMenuItem(statsTitle[i]);
			statsItem[i].addActionListener(new statsActionListener());// 메뉴아이템에 Action 리스너 설정screenMenu.add(item);
			statsMenu.add(statsItem[i]);
		}
	}
////////////////////////////////////////////////////////////////////////////
	//innor class
	
	//출석에 작업추가할때 사용
		class fileActionListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				// 사용자가 Load 메뉴아이템을 선택하는 경우 처리할 작업 구현
				//...
				String cmd = e.getActionCommand();
				switch(cmd) {
				case "학생정보 입력" :
					//수정2
					//inputData input = new inputData();
					mainPanel.removeAll();  //전부 날리기
					mainPanel.add(new inputPaner());
					mainPanel.updateUI();
					mainPanel.setVisible(true);
					break;
				case "저장하기" :
					try {
						saveFfile saveCVS = new saveFfile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case "불러오기" :
					try {
						loadFfile loadCVS = new loadFfile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				}
			}
		}
	
	//출석에 작업추가할때 사용
	class attendActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// 사용자가 Load 메뉴아이템을 선택하는 경우 처리할 작업 구현
			//...
			String cmd = e.getActionCommand();
			switch(cmd) {
			case "주별 확인" : 
				mainPanel.removeAll();  //전부 날리기
				mainPanel.add(new AttendWeek());
				mainPanel.updateUI();
				mainPanel.setVisible(true);
				break;
			case "전체 확인" :
				mainPanel.removeAll();	//전부 날리기
				mainPanel.add(new AttendMain());
				mainPanel.updateUI();	//패널 업데이트
				mainPanel.setVisible(true);
				break;
			}
		}
	}

	// 성적 - action
	class gradeActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// 사용자가 Load 메뉴아이템을 선택하는 경우 처리할 작업 구현
			//...
			String cmd = e.getActionCommand();
			switch(cmd) {
			case "성적 입력" :
				mainPanel.removeAll();  //전부 날리기
				try {
					mainPanel.add(new inputScore());
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				mainPanel.updateUI();//패널 업데이트
				mainPanel.setVisible(true);
				break;
			case "반영 비율" :
				mainPanel.removeAll(); //전부 날리기
				try {
					mainPanel.add(new setApplyRate());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				mainPanel.updateUI();	//패널 업데이트
				mainPanel.setVisible(true);
				break;
			case "등급 비율" :
				mainPanel.removeAll(); //전부 날리기
				try {
					mainPanel.add(new setRankRate());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				mainPanel.updateUI();	//패널 업데이트
				mainPanel.setVisible(true);
				break;
			case "점수 확인" :
				mainPanel.removeAll(); //전부 날리기
				try {
					mainPanel.add(new scoreCheck());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				mainPanel.updateUI();	//패널 업데이트
				mainPanel.setVisible(true);
				break;
			}
		}
	}
		
		//통계 작업 추가
	class statsActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// 사용자가 Load 메뉴아이템을 선택하는 경우 처리할 작업 구현
			//...
			String cmd = e.getActionCommand();
			switch(cmd) {
			case "평균 출력" :
				mainPanel.removeAll();  //전부 날리기
				mainPanel.add(new Avgprint());
				mainPanel.updateUI();
				mainPanel.setVisible(true);
				break;
			case "표준 편차 출력" :
				mainPanel.removeAll();  //전부 날리기
				mainPanel.add(new Stdprint());
				mainPanel.updateUI();
				mainPanel.setVisible(true);
				break;
			case "그래프 출력" :
				graph gradegraph = new graph(); //그래프 생성!!
				break;
			}		
		}
	}
	
	//메인 프레임 생성
	public static void main(String[] args) {
		new mainFrame();
	}
}


