package stats;

import java.awt.Color;
import java.awt.Graphics;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPanel;
import javax.swing.JRadioButton;

import Frames.Connect_to;

public class DrawingPanel extends JPanel {
	// 0~90까지 점수폭의 숫자를 카운트할 변수
	int aplus; // 90점 이상
	int azero; // 80점 이상
	int bplus; // 70점 이상
	int bzero; // 60점 이상
	int cplus; // 50점 이상
	int czero; // 40점 이상
	int dzero; // 30점 이상
	int fzero; // 20점 이상
	int gzero; // 10점 이상
	int hzero;// 0점 이상
	private double atd_rate;
	private double mid_rate;
	private double final_rate;
	private double assign_rate;

	public void paint(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		g.drawLine(50, 250, 1200, 250);
		for (int cnt = 1; cnt < 7; cnt++) {
			g.drawString(cnt * 5 + "", 25, 255 - 30 * cnt);
			g.drawLine(50, 250 - 30 * cnt, 1200, 250 - 30 * cnt);
		}
		g.drawLine(50, 20, 50, 250);
		g.drawString("90점 이상", 100, 270);
		g.drawString("80점 이상", 200, 270);
		g.drawString("70점 이상", 300, 270);
		g.drawString("60점 이상", 400, 270);
		g.drawString("50점 이상", 500, 270);
		g.drawString("40점 이상", 600, 270);
		g.drawString("30점 이상", 700, 270);
		g.drawString("20점 이상", 800, 270);
		g.drawString("10점 이상", 900, 270);
		g.drawString("0점 이상", 1000, 270);
		g.setColor(Color.RED);
		if (aplus > 0)
			g.fillRect(120, 250 - aplus * 6, 10, aplus * 6);
		if (azero > 0)
			g.fillRect(220, 250 - azero * 6, 10, azero * 6);
		if (bplus > 0)
			g.fillRect(320, 250 - bplus * 6, 10, bplus * 6);
		if (bzero > 0)
			g.fillRect(420, 250 - bzero * 6, 10, bzero * 6);
		if (cplus > 0)
			g.fillRect(520, 250 - cplus * 6, 10, cplus * 6);
		if (czero > 0)
			g.fillRect(620, 250 - czero * 6, 10, czero * 6);
		if (dzero > 0)
			g.fillRect(720, 250 - dzero * 6, 10, dzero * 6);
		if (fzero > 0)
			g.fillRect(820, 250 - fzero * 6, 10, fzero * 6);
		if (gzero > 0)
			g.fillRect(920, 250 - gzero * 6, 10, gzero * 6);
		if (hzero > 0)
			g.fillRect(1020, 250 - hzero * 6, 10, hzero * 6);
	}

	// 점수
	public void setScores(String str) {
		Connection con = Connect_to.makeConnection(); // DB 연결
		try {

			// aplus가 계속 커짐 할떄마다 초기화 해줘야함!!
			aplus = 0; // 90점 이상
			azero = 0; // 80점 이상
			bplus = 0; // 70점 이상
			bzero = 0; // 60점 이상
			cplus = 0; // 50점 이상
			czero = 0; // 40점 이상
			dzero = 0; // 30점 이상
			fzero = 0; // 20점 이상
			gzero = 0; // 10점 이상
			hzero = 0; // 0점 이상
			String sql = "select * from student";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int temp = rs.getInt(str);
				if (temp >= 90)
					aplus++;
				else if (temp >= 80)
					azero++;
				else if (temp >= 70)
					bplus++;
				else if (temp >= 60)
					bzero++;
				else if (temp >= 50)
					cplus++;
				else if (temp >= 40)
					czero++;
				else if (temp >= 30)
					dzero++;
				else if (temp >= 20)
					fzero++;
				else if (temp >= 10)
					gzero++;
				else if (temp >= 0)
					hzero++;
			}
		} catch (SQLException se1) {
			se1.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	public void setallScores() {
		Connection con = Connect_to.makeConnection(); // DB 연결
		try {
			// 그래프 가 계속 커짐 할떄마다 초기화 해줘야함!!
			aplus = 0; // 90점 이상
			azero = 0; // 80점 이상
			bplus = 0; // 70점 이상
			bzero = 0; // 60점 이상
			cplus = 0; // 50점 이상
			czero = 0; // 40점 이상
			dzero = 0; // 30점 이상
			fzero = 0; // 20점 이상
			gzero = 0; // 10점 이상
			hzero = 0; // 0점 이상
			String sql2 = "select * from rate";
			PreparedStatement pstmt1 = con.prepareStatement(sql2);
			ResultSet rate_rs = pstmt1.executeQuery(sql2);
			rate_rs.next();
			atd_rate = (rate_rs.getInt("atd_rate")) / 100.0;
			mid_rate = (rate_rs.getInt("mid_rate")) / 100.0;
			final_rate = (rate_rs.getInt("final_rate")) / 100.0;
			assign_rate = (rate_rs.getInt("assign_rate")) / 100.0;
			String sql = "select * from student";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				double temp = rs.getInt("atd")*atd_rate + rs.getInt("assignment")*assign_rate + rs.getInt("middle")*mid_rate + rs.getInt("final")*final_rate;
				if (temp >= 90)
					aplus++;
				else if (temp >= 80)
					azero++;
				else if (temp >= 70)
					bplus++;
				else if (temp >= 60)
					bzero++;
				else if (temp >= 50)
					cplus++;
				else if (temp >= 40)
					czero++;
				else if (temp >= 30)
					dzero++;
				else if (temp >= 20)
					fzero++;
				else if (temp >= 10)
					gzero++;
				else if (temp >= 0)
					hzero++;
			}
		} catch (SQLException se1) {
			se1.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
}