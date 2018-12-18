package stats;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class graph  // 그래프 만들어주는 클래스
{	   
	  
 public graph() {
	  JFrame frame = new JFrame("성적 분포 그래프");
	  frame.setLocation(0,0);
	  frame.setPreferredSize(new Dimension(1500,500));
	  
	  Container contentpane = frame.getContentPane();
	  DrawingPanel drawingPanel = new DrawingPanel();
	  contentpane.add(drawingPanel, BorderLayout.CENTER);
	  
	  JPanel controlPanel = new JPanel();
	  JTextField text1 = new JTextField(3);
	  JTextField text2 = new JTextField(3);
	  JTextField text3 = new JTextField(3);
	  JTextField text4 = new JTextField(3);
	  JTextField text5 = new JTextField(3);
	  JTextField text6 = new JTextField(3);
	  JTextField text7 = new JTextField(3);
	  JTextField text8 = new JTextField(3);
	  JTextField text9 = new JTextField(3);
	  JTextField text10 = new JTextField(3);
	  
	  
	  JRadioButton Radio1 = new JRadioButton("출석 점수");
	  JRadioButton Radio2 = new JRadioButton("과제 점수");
	  JRadioButton Radio3 = new JRadioButton("중간 점수");
	  JRadioButton Radio4 = new JRadioButton("기말 점수");
	  JRadioButton Radio5 = new JRadioButton("전체 점수");	
	  
	  
	  
	  ButtonGroup group = new ButtonGroup(); // 라디오 버튼 그룹화
	  group.add(Radio1); group.add(Radio2); group.add(Radio3);
	  group.add(Radio4); group.add(Radio5);

	  
	  controlPanel.add(Radio1);
	  controlPanel.add(Radio2);
	  controlPanel.add(Radio3);
	  controlPanel.add(Radio4);
	  controlPanel.add(Radio5);
	  
	  
	  
	  contentpane.add(controlPanel,BorderLayout.SOUTH);
	  //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 이거 하면 그래프 끄면 성적 처리 프로그램 자체가 꺼짐!!!! 주의
	  
	  Radio1.addActionListener(new DrawActionListener(drawingPanel));
	  Radio2.addActionListener(new DrawActionListener(drawingPanel));
	  Radio3.addActionListener(new DrawActionListener(drawingPanel));
	  Radio4.addActionListener(new DrawActionListener(drawingPanel));
	  Radio5.addActionListener(new DrawActionListener(drawingPanel));
	  
	  
	  frame.pack();
	  frame.setVisible(true);
	  
	  
	}



}