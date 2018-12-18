package stats;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JRadioButton;

import Frames.Connect_to;
public class DrawActionListener implements ActionListener{
	
	DrawingPanel drawingPanel;
	DrawActionListener(DrawingPanel drawingPanel){
			this.drawingPanel=drawingPanel;
			
	}
 public void actionPerformed(ActionEvent e){
	 JRadioButton rb = (JRadioButton)e.getSource();
	 if(rb.getText().equals("출석 점수"))
		 drawingPanel.setScores("atd");
	 else if(rb.getText().equals("과제 점수"))
		 drawingPanel.setScores("assignment");
	 else if(rb.getText().equals("중간 점수"))
		 drawingPanel.setScores("middle");
	 else if(rb.getText().equals("기말 점수"))
		 drawingPanel.setScores("final");
	 else if(rb.getText().equals("전체 점수"))
		 drawingPanel.setallScores();
	 drawingPanel.repaint();
   }
}