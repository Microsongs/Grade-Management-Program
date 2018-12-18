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
	// 0~90���� �������� ���ڸ� ī��Ʈ�� ����
	int aplus; // 90�� �̻�
	int azero; // 80�� �̻�
	int bplus; // 70�� �̻�
	int bzero; // 60�� �̻�
	int cplus; // 50�� �̻�
	int czero; // 40�� �̻�
	int dzero; // 30�� �̻�
	int fzero; // 20�� �̻�
	int gzero; // 10�� �̻�
	int hzero;// 0�� �̻�
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
		g.drawString("90�� �̻�", 100, 270);
		g.drawString("80�� �̻�", 200, 270);
		g.drawString("70�� �̻�", 300, 270);
		g.drawString("60�� �̻�", 400, 270);
		g.drawString("50�� �̻�", 500, 270);
		g.drawString("40�� �̻�", 600, 270);
		g.drawString("30�� �̻�", 700, 270);
		g.drawString("20�� �̻�", 800, 270);
		g.drawString("10�� �̻�", 900, 270);
		g.drawString("0�� �̻�", 1000, 270);
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

	// ����
	public void setScores(String str) {
		Connection con = Connect_to.makeConnection(); // DB ����
		try {

			// aplus�� ��� Ŀ�� �ҋ����� �ʱ�ȭ �������!!
			aplus = 0; // 90�� �̻�
			azero = 0; // 80�� �̻�
			bplus = 0; // 70�� �̻�
			bzero = 0; // 60�� �̻�
			cplus = 0; // 50�� �̻�
			czero = 0; // 40�� �̻�
			dzero = 0; // 30�� �̻�
			fzero = 0; // 20�� �̻�
			gzero = 0; // 10�� �̻�
			hzero = 0; // 0�� �̻�
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
		Connection con = Connect_to.makeConnection(); // DB ����
		try {
			// �׷��� �� ��� Ŀ�� �ҋ����� �ʱ�ȭ �������!!
			aplus = 0; // 90�� �̻�
			azero = 0; // 80�� �̻�
			bplus = 0; // 70�� �̻�
			bzero = 0; // 60�� �̻�
			cplus = 0; // 50�� �̻�
			czero = 0; // 40�� �̻�
			dzero = 0; // 30�� �̻�
			fzero = 0; // 20�� �̻�
			gzero = 0; // 10�� �̻�
			hzero = 0; // 0�� �̻�
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