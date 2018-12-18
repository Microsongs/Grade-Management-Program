package score;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Arrays;
//import java.util.Collections;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Frames.Connect_to;

public class inputScore extends JPanel {
	private Statement stmt;
	private JComboBox<String> myStudents;
	private String[] students;
	private JPanel sPanel, scorePanel;
	private JTextField mid, fin, atd, assign;
	private JLabel lmid, lfin, latd, lassign;
	private String currentStudent;	
	private String midscore,finscore,atdscore,assignscore;
	private int updatemid, updatefin, updateatd, updateassign, row;
	private JButton updateScore;
	private scoreCheck mScore;
	private Object[][] setTotal;
	//JTable table = new JTable();
	//JScrollPane scrollpane = new JScrollPane(); 
	
	public inputScore() throws SQLException {
		//scorePrint();
		Connection con = Connect_to.makeConnection();
		stmt = con.createStatement();
		this.setPreferredSize(new Dimension(800,600));
		
		setLayout(new GridLayout(0,1));
		createPanel();
		currentStudent = students[0];
		inputData();		
	}
	
	// DB에연결하고 panel을 만듬
	public void createPanel() throws SQLException {
				
		String sql = "select * from student";
		ResultSet rs = stmt.executeQuery(sql);
		rs.last();
		int row = rs.getRow();
		students = new String[row];
		rs.beforeFirst();
		int i = 0;
		while(rs.next()) {
			students[i] = rs.getString("name");
			i++;
		}
		//for(int i=0; i<40;i++) {
			//rs.next();
			//students[i] = rs.getString("name");
		//}
		myStudents = new JComboBox<String>(students);
		myStudents.setPreferredSize(new Dimension(100,20));
		myStudents.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox<String> cb = (JComboBox<String>)e.getSource();				
				int index = cb.getSelectedIndex();
				currentStudent = students[index];
				try {
					inputData();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});	
		
		sPanel = new JPanel();
		sPanel.setLayout(new FlowLayout());
		sPanel.add(myStudents);
		
		add(sPanel);
		
		
		JPanel container = new JPanel();
		updateScore = new JButton("수정");
		lmid = new JLabel("중간점수 :");
		lfin = new JLabel("기말점수 :");
		latd = new JLabel("출석점수:");
		lassign = new JLabel("과제점수 :");
		container.setSize(200,200);
		container.setLayout(new FlowLayout());
		mid = new JTextField();
		mid.setPreferredSize(new Dimension(100,20));
		container.add(lmid);
		container.add(mid);
		fin = new JTextField();
		fin.setPreferredSize(new Dimension(100,20));
		container.add(lfin);
		container.add(fin);
		assign = new JTextField();
		assign.setPreferredSize(new Dimension(100,20));
		container.add(lassign);
		container.add(assign);
		//atd = new JTextField();
		//atd.setPreferredSize(new Dimension(100,20));
		//container.add(latd);
		//container.add(atd);
		container.add(updateScore);
		sPanel.add(container, BorderLayout.NORTH);
		//JPanel buttonPane = new JPanel();
		//buttonPane.setLayout(new FlowLayout());
		//JButton total = new JButton("총점 계산");
		//JButton grade = new JButton("등급 계산");
		//buttonPane.add(total);
		//buttonPane.add(grade);
		//sPanel.add(buttonPane,BorderLayout.CENTER);
		scorePanel = new JPanel();
		mScore = new scoreCheck();
		//mScore.setSize(800, 400);
		scorePanel.add(mScore);
		sPanel.add(scorePanel,BorderLayout.SOUTH);
		
		updateScore.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				updatemid = Integer.parseInt(mid.getText());
				updatefin = Integer.parseInt(fin.getText());
				//updateatd = Integer.parseInt(atd.getText());
				updateassign = Integer.parseInt(assign.getText());
				if(updatemid>100||updatemid<0||updatefin>100||updatefin<0
						||updateassign>100||updateassign<0) {
					JOptionPane.showMessageDialog(null, "0 과 100사이의 정수만 입력해 주세요.");					
				}
				else {
					String update = "update student set middle = "+updatemid+", final = "+updatefin+", assignment = "+updateassign+" where name = '"+currentStudent+"'";
					try {
						stmt.executeUpdate(update);	
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}				
					JOptionPane.showMessageDialog(null, "수정되었습니다.");
					totalCalculate();
					setGradeValue();
					scorePanel.remove(mScore);
					try {
						mScore = new scoreCheck();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					scorePanel.add(mScore);
					scorePanel.revalidate();
					scorePanel.repaint();
					sPanel.add(scorePanel);
				}
			}			
		});
		
		/*total.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				totalCalculate();
			}
		});	
		
		grade.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setGradeValue();
			}
		});*/			
		
		//rs.close();		
	}
	
	// 반영비율을 반영하여 중간, 기말, 과제, 출석 점수의 총점을 구함
	public void totalCalculate() {
		double mTotal;
		double mid, fin, atd, ass;
		
		ResultSet rs1, rs2;
		try {
			String total1 = "select * from rate";
			String total2 = "select * from student";
			String total3;
			rs1 = stmt.executeQuery(total1);
			
			rs1.next();
			mid = (double)(rs1.getInt("mid_rate"))/100;
			fin = (double)(rs1.getInt("final_rate"))/100;
			atd = (double)(rs1.getInt("atd_rate"))/100;
			ass = (double)(rs1.getInt("assign_rate"))/100;
			
			rs2 = stmt.executeQuery(total2);
			rs2.last();
			row = rs2.getRow();
			rs2.beforeFirst();
			setTotal = new Object[row][2];
			
			for(int i = 0; rs2.next(); i++) {
				setTotal[i][0] = rs2.getString("stuid");
				setTotal[i][1] = (mid*(double)rs2.getInt("middle")+fin*(double)rs2.getInt("final")
						+atd*(double)rs2.getInt("atd")+ass*(double)rs2.getInt("assignment"));
			}
			//total3 = "update student set total = " +(mid*(double)rs2.getInt("middle")+fin*(double)rs2.getInt("final")
					//+atd*(double)rs2.getInt("atd")+ass*(double)rs2.getInt("assignment"))+"where stuid = '"+stuid+"'";
			for(int i = 0; i < row; i++) {
				total3 = "update student set total = "+setTotal[i][1]+" where stuid = "+"'"+setTotal[i][0]+"'";
				stmt.executeUpdate(total3);
			}
			//mScore = new scoreCheck();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//JOptionPane.showMessageDialog(null, "총점이 계산되었습니다.");
		/*scorePanel.remove(mScore);
		try {
			mScore = new scoreCheck();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		scorePanel.add(mScore);
		scorePanel.revalidate();
		scorePanel.repaint();
		sPanel.add(scorePanel);*/
	}
	
	// 현재 총점을 기준으로 학점 등급을 학생에게 부여
	public void setGradeValue() {
		if(setTotal==null) {
			//JOptionPane.showMessageDialog(null, "총점을 먼저 계산하세요.");
		}
		else {
			String sql1 = "select * from myrank";
			String[] grade = {"A+","A0","B+","B0","C+","C0","D","F"};
			int sum = 0;
			int[] gradeCount = new int[8];
			double[] gradeRate = new double[8];
			try {
				ResultSet rs = stmt.executeQuery(sql1);
				rs.next();
				for(int i = 0; i < 8; i++) {
					gradeRate[i] = ((double)rs.getInt(i+1)/100)*row;
					gradeCount[i] = (int)Math.round(gradeRate[i]);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for(int i = 0; i < 8; i++) {
				sum += gradeCount[i];
			}
			if(sum>row) {
				gradeCount[7] -= (sum-row);
			}
			
			Arrays.sort(setTotal,new Comparator<Object[]>() {
				@Override
				public int compare(Object[] e1, Object[] e2) {
					if(((Comparable)e1[1]).compareTo(e2[1]) < 0) {
						return 1;
					}
					else {
						return -1;
					}			
			}});
			int update = 0;
			int count = 0;
			String sqlUpdate;
			while(update < 8) {
				for(int j = 0; j < gradeCount[update]; j++) {
					sqlUpdate = "update student set grade = '"+ grade[update] + "' where stuid = '"+setTotal[count][0]+"'";
					try {
						stmt.executeUpdate(sqlUpdate);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					count++;
				}
				update++;
			}
			//JOptionPane.showMessageDialog(null, "등급이 계산되었습니다.");
			/*scorePanel.remove(mScore);
			try {
				mScore = new scoreCheck();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			scorePanel.add(mScore);
			scorePanel.revalidate();
			scorePanel.repaint();
			sPanel.add(scorePanel);*/
			setTotal = null;			
		}
		
	}
	
	// 현재 선택된 학생의 성적을 수정
	public void inputData() throws SQLException {
		
		String sql = "select * from student where name = " + "'" +currentStudent +"'";
		ResultSet rs = stmt.executeQuery(sql);
		int upd;
		rs.next();
		midscore = Integer.toString(rs.getInt("middle"));
		finscore = Integer.toString(rs.getInt("final"));
		//atdscore = Integer.toString(rs.getInt("atd"));
		assignscore = Integer.toString(rs.getInt("assignment"));
		mid.setText(midscore);
		fin.setText(finscore);
		//atd.setText(atdscore);
		assign.setText(assignscore);		
	}	
}
