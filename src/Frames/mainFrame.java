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
	
	private JMenuBar bar;		//�޴���
	private JMenu fileMenu;		//���� �޴�
	private JMenu attendMenu;	//�⼮ �޴�
	private JMenu gradeMenu;	//���� �޴�
	private JMenu statsMenu;	//��� �޴�
	private JToolBar toolbar;      //���� 
	private JPanel mainPanel;
	
	//���� �̿��� �г��� Java������ ����� ����
	//�޴��ٴ� �гο� ���� �� �����Ƿ� Frame���� ó��
	public mainFrame() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		createMenu();	//�޴� ����	
		this.setSize(1000,800);	
		this.setVisible(true);
		this.setTitle("���� ���� ���α׷�");
		mainPanel = new JPanel();	//�ٸ� JPanel ��ü�� �����鼭 üũ
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		defaultMenu();
	}
	
	public void defaultMenu() {
		JPanel tempPanel = new JPanel();
		ImageIcon ic = new ImageIcon("main.png");
		JLabel imgLabel = new JLabel(ic);	//�̹�����
		
		tempPanel.add(imgLabel);
		tempPanel.setSize(600,400);
		mainPanel.removeAll();	//���� ������
		mainPanel.add(tempPanel);
		mainPanel.updateUI();	//�г� ������Ʈ
		mainPanel.setVisible(true);
	}
	
	public void createMenu() {
		bar = new JMenuBar();
		createToolbar();
		fileMenu = new JMenu("����");
		createFileMenu();
		
		attendMenu = new JMenu("�⼮");
		createAttendMenu();
		
		gradeMenu = new JMenu("����");
		createGradeMenu();
		
		statsMenu = new JMenu("���");
		createStatsMenu();
		
		bar.add(fileMenu);
		bar.add(attendMenu);
		bar.add(gradeMenu);
		bar.add(statsMenu);
		setJMenuBar(bar);
	}
	
	//���� ���� �޼���
	public void createToolbar() {
		// ���� - ���� �̹���
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
		// ���� - ���� �̹���
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
				
				// ���� - ��� �̹���
		ImageIcon iconStats = new ImageIcon("stats.png");
		Action actionStats = new AbstractAction("Stats", iconStats) {		
			@Override
			public void actionPerformed(ActionEvent e) {
				graph gradegraph = new graph(); //�׷��� ����!!
						
			}
		};
				
						
		toolbar = new JToolBar();
		JButton btnOpen = toolbar.add(actionOpen); // ���� ��ư �߰�
		JButton btnSave = toolbar.add(actionSave); // ���� ��ư �߰�
		JButton btnStats = toolbar.add(actionStats); // ��� ��ư �߰�
		getContentPane().add(toolbar, BorderLayout.NORTH);
	}
	
	//////////////////////////////////////////////////////////////////////////
	//���� �޴� ���� �޼���
	public void createFileMenu() {
		int maxMenu = 3;
		String[] fileTitle= {"�л����� �Է�","�����ϱ�","�ҷ�����"};
		JMenuItem[] fileItem = new JMenuItem[maxMenu];
		for(int i=0;i<maxMenu;i++) {
			fileItem[i] = new JMenuItem(fileTitle[i]);
			fileItem[i].addActionListener(new fileActionListener());// �޴������ۿ� Action ������ ����screenMenu.add(item);
			fileMenu.add(fileItem[i]);
		}
	}
	//�⼮ �޴��� ����
	public void createAttendMenu() {
		int maxMenu = 2;
		String[] attendTitle= {"�ֺ� Ȯ��","��ü Ȯ��"};
		JMenuItem[] attendItem = new JMenuItem[maxMenu];
		for(int i=0;i<maxMenu;i++) {
			attendItem[i] = new JMenuItem(attendTitle[i]);
			attendItem[i].addActionListener(new attendActionListener());// �޴������ۿ� Action ������ ����screenMenu.add(item);
			attendMenu.add(attendItem[i]);
		}
	}
	//���� �޴��� ����
	void createGradeMenu() {
		int maxMenu = 4;
		String[] gradeTitle= {"���� �Է�", "�ݿ� ����","��� ����","���� Ȯ��"};
		JMenuItem[] gradeItem = new JMenuItem[maxMenu];
		for(int i=0;i<maxMenu;i++) {			
			gradeItem[i] = new JMenuItem(gradeTitle[i]);
			gradeItem[i].addActionListener(new gradeActionListener());// �޴������ۿ� Action ������ ����screenMenu.add(item);
			gradeMenu.add(gradeItem[i]);
		}
	}
	
	//��� �޴� ���� �޼���
	void createStatsMenu() {
		int maxMenu =3;
		String[] statsTitle= {"��� ���", "ǥ�� ���� ���","�׷��� ���"};
		JMenuItem[] statsItem = new JMenuItem[maxMenu];
		for(int i=0;i<maxMenu;i++) {
			statsItem[i] = new JMenuItem(statsTitle[i]);
			statsItem[i].addActionListener(new statsActionListener());// �޴������ۿ� Action ������ ����screenMenu.add(item);
			statsMenu.add(statsItem[i]);
		}
	}
////////////////////////////////////////////////////////////////////////////
	//innor class
	
	//�⼮�� �۾��߰��Ҷ� ���
		class fileActionListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				// ����ڰ� Load �޴��������� �����ϴ� ��� ó���� �۾� ����
				//...
				String cmd = e.getActionCommand();
				switch(cmd) {
				case "�л����� �Է�" :
					//����2
					//inputData input = new inputData();
					mainPanel.removeAll();  //���� ������
					mainPanel.add(new inputPaner());
					mainPanel.updateUI();
					mainPanel.setVisible(true);
					break;
				case "�����ϱ�" :
					try {
						saveFfile saveCVS = new saveFfile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case "�ҷ�����" :
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
	
	//�⼮�� �۾��߰��Ҷ� ���
	class attendActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// ����ڰ� Load �޴��������� �����ϴ� ��� ó���� �۾� ����
			//...
			String cmd = e.getActionCommand();
			switch(cmd) {
			case "�ֺ� Ȯ��" : 
				mainPanel.removeAll();  //���� ������
				mainPanel.add(new AttendWeek());
				mainPanel.updateUI();
				mainPanel.setVisible(true);
				break;
			case "��ü Ȯ��" :
				mainPanel.removeAll();	//���� ������
				mainPanel.add(new AttendMain());
				mainPanel.updateUI();	//�г� ������Ʈ
				mainPanel.setVisible(true);
				break;
			}
		}
	}

	// ���� - action
	class gradeActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// ����ڰ� Load �޴��������� �����ϴ� ��� ó���� �۾� ����
			//...
			String cmd = e.getActionCommand();
			switch(cmd) {
			case "���� �Է�" :
				mainPanel.removeAll();  //���� ������
				try {
					mainPanel.add(new inputScore());
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				mainPanel.updateUI();//�г� ������Ʈ
				mainPanel.setVisible(true);
				break;
			case "�ݿ� ����" :
				mainPanel.removeAll(); //���� ������
				try {
					mainPanel.add(new setApplyRate());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				mainPanel.updateUI();	//�г� ������Ʈ
				mainPanel.setVisible(true);
				break;
			case "��� ����" :
				mainPanel.removeAll(); //���� ������
				try {
					mainPanel.add(new setRankRate());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				mainPanel.updateUI();	//�г� ������Ʈ
				mainPanel.setVisible(true);
				break;
			case "���� Ȯ��" :
				mainPanel.removeAll(); //���� ������
				try {
					mainPanel.add(new scoreCheck());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				mainPanel.updateUI();	//�г� ������Ʈ
				mainPanel.setVisible(true);
				break;
			}
		}
	}
		
		//��� �۾� �߰�
	class statsActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// ����ڰ� Load �޴��������� �����ϴ� ��� ó���� �۾� ����
			//...
			String cmd = e.getActionCommand();
			switch(cmd) {
			case "��� ���" :
				mainPanel.removeAll();  //���� ������
				mainPanel.add(new Avgprint());
				mainPanel.updateUI();
				mainPanel.setVisible(true);
				break;
			case "ǥ�� ���� ���" :
				mainPanel.removeAll();  //���� ������
				mainPanel.add(new Stdprint());
				mainPanel.updateUI();
				mainPanel.setVisible(true);
				break;
			case "�׷��� ���" :
				graph gradegraph = new graph(); //�׷��� ����!!
				break;
			}		
		}
	}
	
	//���� ������ ����
	public static void main(String[] args) {
		new mainFrame();
	}
}


