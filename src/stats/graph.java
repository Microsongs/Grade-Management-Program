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

public class graph  // �׷��� ������ִ� Ŭ����
{	   
	  
 public graph() {
	  JFrame frame = new JFrame("���� ���� �׷���");
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
	  
	  
	  JRadioButton Radio1 = new JRadioButton("�⼮ ����");
	  JRadioButton Radio2 = new JRadioButton("���� ����");
	  JRadioButton Radio3 = new JRadioButton("�߰� ����");
	  JRadioButton Radio4 = new JRadioButton("�⸻ ����");
	  JRadioButton Radio5 = new JRadioButton("��ü ����");	
	  
	  
	  
	  ButtonGroup group = new ButtonGroup(); // ���� ��ư �׷�ȭ
	  group.add(Radio1); group.add(Radio2); group.add(Radio3);
	  group.add(Radio4); group.add(Radio5);

	  
	  controlPanel.add(Radio1);
	  controlPanel.add(Radio2);
	  controlPanel.add(Radio3);
	  controlPanel.add(Radio4);
	  controlPanel.add(Radio5);
	  
	  
	  
	  contentpane.add(controlPanel,BorderLayout.SOUTH);
	  //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �̰� �ϸ� �׷��� ���� ���� ó�� ���α׷� ��ü�� ����!!!! ����
	  
	  Radio1.addActionListener(new DrawActionListener(drawingPanel));
	  Radio2.addActionListener(new DrawActionListener(drawingPanel));
	  Radio3.addActionListener(new DrawActionListener(drawingPanel));
	  Radio4.addActionListener(new DrawActionListener(drawingPanel));
	  Radio5.addActionListener(new DrawActionListener(drawingPanel));
	  
	  
	  frame.pack();
	  frame.setVisible(true);
	  
	  
	}



}