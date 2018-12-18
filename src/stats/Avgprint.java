package stats;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Frames.Connect_to;

public class Avgprint extends JPanel{
	//UI를 만드는 함수
	private double[] atd;
	private double[] middle;
	private double[] Final;
	private double[] assignment;
	private String[] name;
	private double[] all;
	
	private double atdsum;
	private double middlesum;
	private double finalsum;
	private double assignmentsum;
	private double allsum;
	
	private double atdavg;
	private double middleavg;
	private double finalavg;
	private double assignmentavg;
	private double allavg;
	
	private double atd_rate;
	private double mid_rate;
	private double final_rate;
	private double assign_rate;
	
	private int count;
	private int row;
	
	private JComboBox score;
	private String[] sco;
	private JScrollPane scrollpane;
	private JPanel avgPanel;
	private JPanel Panel;
	private JPanel CenterPanel;
	
	public void createComboBox() {
		JPanel comboPanel = new JPanel(new FlowLayout());
		sco = new String[5];
		sco[0] = "출석 평균 보기";
		sco[1] = "과제 평균 보기";
		sco[2] = "중간고사 평균 보기";
		sco[3] = "기말고사 평균 보기";
		sco[4] = "전체 평균 보기";
		score = new JComboBox(sco);
		
		comboPanel.add(score);
		add("North",comboPanel);
		//add(score,0);
		score.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox str = (JComboBox)e.getSource();
				int index = str.getSelectedIndex();
				if(index ==0) { // 출석 평균 보기
					//for(int i=0;i<30;i++) {
					//System.out.print(name[i]+ " : ");
					//System.out.println(atd[i]+ "점");
					//}
					createAttendPanel();
				}
				else if(index ==1) { // 과제 평균 보기
					createAssignPanel();
				}
				else if(index ==2) { // 중간고사 평균 보기
					createMiddlePanel();
				}
				else if(index ==3) { // 기말고사 평균 보기
					createFinalPanel();
				}
				else if(index == 4) {
					createAllPanel();
				}
			}
		});
		
	}
	void createAttendPanel() {
		if(CenterPanel != null) {
			CenterPanel.removeAll();
			CenterPanel.setVisible(false);
		}
		if(avgPanel != null) {
			avgPanel.removeAll();
			avgPanel.setVisible(false);
		}
		CenterPanel = new JPanel();
		avgPanel = new JPanel();
		//attendPanel.setLayout(new FlowLayout());
		//attendPanel.setSize(50,800);
		CenterPanel.setLayout(new GridLayout(0,2));
		for(int i=0;i<row;i++) {
			CenterPanel.add(new JLabel(name[i]));
			CenterPanel.add(new JLabel(Double.toString(atd[i])));
		}
		JLabel atdavg1 = new JLabel("출석 평균 점수 : " + atdavg); // 라벨 생성
		atdavg1.setFont(new Font("Serif",Font.BOLD,20));
		avgPanel.add(atdavg1);
		add("Center",CenterPanel);
		add("South",avgPanel);
		if(scrollpane != null) {
			scrollpane.removeAll();
			scrollpane.setVisible(false);
		}
		scrollpane = new JScrollPane(CenterPanel);
		scrollpane.setPreferredSize(new Dimension(400,400));
		add(scrollpane);
		revalidate();
		scrollpane.setVisible(true);
	}
	
	void createAssignPanel() {
		if(CenterPanel != null) {
			CenterPanel.removeAll();
			CenterPanel.setVisible(false);
		}
		if(avgPanel != null) {
			avgPanel.removeAll();
			avgPanel.setVisible(false);
		}
		CenterPanel = new JPanel();
		avgPanel = new JPanel();
		//attendPanel.setLayout(new FlowLayout());
		//attendPanel.setSize(50,800);
		CenterPanel.setLayout(new GridLayout(0,2));
		for(int i=0;i<row;i++) {
			CenterPanel.add(new JLabel(name[i]));
			CenterPanel.add(new JLabel(Double.toString(assignment[i])));
		}
		JLabel assignmentavg1 = new JLabel("과제 평균 점수 : " + assignmentavg); // 라벨 생성
		assignmentavg1.setFont(new Font("Serif",Font.BOLD,20));
		avgPanel.add(assignmentavg1);
		add("Center",CenterPanel);
		add("South",avgPanel);
		if(scrollpane != null) {
			scrollpane.removeAll();
			scrollpane.setVisible(false);
		}
		scrollpane = new JScrollPane(CenterPanel);
		scrollpane.setPreferredSize(new Dimension(400,400));
		add(scrollpane);
		revalidate();
		scrollpane.setVisible(true);
	}
	
	void createMiddlePanel() {
		if(CenterPanel != null) {
			CenterPanel.removeAll();
			CenterPanel.setVisible(false);
		}
		if(avgPanel != null) {
			avgPanel.removeAll();
			avgPanel.setVisible(false);
		}
		CenterPanel = new JPanel();
		avgPanel = new JPanel();
		//attendPanel.setLayout(new FlowLayout());
		//attendPanel.setSize(50,800);
		CenterPanel.setLayout(new GridLayout(0,2));
		for(int i=0;i<row;i++) {
			CenterPanel.add(new JLabel(name[i]));
			CenterPanel.add(new JLabel(Double.toString(middle[i])));
		}
		JLabel middleavg1 = new JLabel("중간고사 평균 점수 : " + middleavg); // 라벨 생성
		middleavg1.setFont(new Font("Serif",Font.BOLD,20));
		avgPanel.add(middleavg1);
		add("Center",CenterPanel);
		add("South",avgPanel);
		if(scrollpane != null) {
			scrollpane.removeAll();
			scrollpane.setVisible(false);

		}
		scrollpane = new JScrollPane(CenterPanel);
		scrollpane.setPreferredSize(new Dimension(400,400));
		add(scrollpane);
		revalidate();
		scrollpane.setVisible(true);
	}
	
	void createFinalPanel() {
		if(CenterPanel != null) {
			CenterPanel.removeAll();
			CenterPanel.setVisible(false);
		}
		if(avgPanel != null) {
			avgPanel.removeAll();
			avgPanel.setVisible(false);
		}
		CenterPanel = new JPanel();
		avgPanel = new JPanel();
		//attendPanel.setLayout(new FlowLayout());
		//attendPanel.setSize(50,800);
		CenterPanel.setLayout(new GridLayout(0,2));
		for(int i=0;i<row;i++) {
			CenterPanel.add(new JLabel(name[i]));
			CenterPanel.add(new JLabel(Double.toString(Final[i])));
		}
		JLabel finalavg1 = new JLabel("기말고사 평균 점수 : " + finalavg); // 라벨 생성
		finalavg1.setFont(new Font("Serif",Font.BOLD,20));
		avgPanel.add(finalavg1);
		add("Center",CenterPanel);
		add("South",avgPanel);
		if(scrollpane != null) {
			scrollpane.removeAll();
			scrollpane.setVisible(false);
		}
		scrollpane = new JScrollPane(CenterPanel);
		scrollpane.setPreferredSize(new Dimension(400,400));
		add(scrollpane);
		revalidate();
		scrollpane.setVisible(true);
	}
	
	void createAllPanel() {
		if(CenterPanel != null) {
			CenterPanel.removeAll();
			CenterPanel.setVisible(false);
		}
		if(avgPanel != null) {
			avgPanel.removeAll();
			avgPanel.setVisible(false);
		}
		CenterPanel = new JPanel();
		avgPanel = new JPanel();
		//attendPanel.setLayout(new FlowLayout());
		//attendPanel.setSize(50,800);
		CenterPanel.setLayout(new GridLayout(0,2));
		for(int i=0;i<row;i++) {
			CenterPanel.add(new JLabel(name[i]));
			CenterPanel.add(new JLabel(Double.toString(all[i])));
		}
		JLabel allavg1 = new JLabel("전체 평균 점수 : " + allavg); // 라벨 생성
		allavg1.setFont(new Font("Serif",Font.BOLD,20));
		avgPanel.add(allavg1);
		add("Center",CenterPanel);
		add("South",avgPanel);
		if(scrollpane != null) {
			scrollpane.removeAll();
			scrollpane.setVisible(false);
		}
		scrollpane = new JScrollPane(CenterPanel);
		scrollpane.setPreferredSize(new Dimension(400,400));
		add(scrollpane);
		revalidate();
		scrollpane.setVisible(true);
	}
	
	public Avgprint() {
	//setLayout(new GridLayout(3,1));
	
		setLayout(new BorderLayout());
	//setLayout(new FlowLayout());
	setSize(100,800);
	
	createComboBox();
	Connection con = Connect_to.makeConnection(); // DB 연결
	Statement stmt = null;
	atdsum = 0;
	middlesum = 0;
	finalsum = 0;
	assignmentsum = 0;
	allsum = 0;
	count = 0;
	name = new String[40];
	atd = new double[40];
	middle = new double[40];
	Final = new double[40];
	assignment = new double[40];
	all = new double[40];
	
	try {
	stmt = con.createStatement();
	String sql;
	String sql2;
	// rate에서 비율을 가져와서 비율대로 점수를 산출함
	sql2 = "select * from rate";
	ResultSet rate_rs = stmt.executeQuery(sql2);
	rate_rs.next();
	atd_rate = (rate_rs.getInt("atd_rate")) / 100.0;
	mid_rate = (rate_rs.getInt( "mid_rate")) / 100.0;
	final_rate = (rate_rs.getInt("final_rate")) / 100.0;
	assign_rate = (rate_rs.getInt("assign_rate")) / 100.0;
	
	sql = "select * from student";
	ResultSet rs = stmt.executeQuery(sql);
	while(rs.next()) {
		name[count] = rs.getString("name");
		atd[count] = rs.getInt("atd");
		middle[count] = rs.getInt("middle");
		Final[count] = rs.getInt("final");
		assignment[count] = rs.getInt("assignment");
		all[count] = atd[count]*atd_rate + middle[count]*mid_rate + Final[count]*final_rate + assignment[count]*assign_rate;
		
		//System.out.println("\n** stuID : " + atd);
		atdsum += atd[count];
		middlesum += middle[count];
		finalsum += Final[count];
		assignmentsum += assignment[count];
		allsum += all[count];
		count ++;
	}
	
	row = count; // row를 카운트로 선언해줌!!
	atdavg = atdsum/count;
	middleavg = middlesum/count;
	finalavg = finalsum/count;
	assignmentavg = assignmentsum/count;
	allavg = allsum/count;

	atdavg = Math.round(atdavg*100)/100.0;
	middleavg = Math.round(middleavg*100)/100.0;
	finalavg = Math.round(finalavg*100)/100.0;
	assignmentavg = Math.round(assignmentavg*100)/100.0;
	allavg = Math.round(allavg*100)/100.0;
	
	createAttendPanel();	//출석 패널
	rs.close();
	stmt.close();
	con.close();				
	}catch(SQLException se1){
		se1.printStackTrace();
	}catch(Exception ex){
		ex.printStackTrace();
	}finally{
		try{
			if(stmt!=null)
				stmt.close();
		}catch(SQLException se2){
		}
		try{
			if(con!=null)
				con.close();
		}catch(SQLException se){
			se.printStackTrace();
		}
	}
  }
}