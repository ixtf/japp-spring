package com.hengyi.japp.trans.applet.ys;

import java.awt.Graphics;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JApplet;

public class YSPoundApplet extends JApplet {
	private static final long serialVersionUID = 5028592652776079301L;
	private static final String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
	private static final String url = "jdbc:odbc:toledo";
	private static final String query = "SELECT * FROM TRADE WHERE TICKETNO1=?";
	private static final String carNoColumn = "carNo";
	private static final String lfimg1Column = "lfimg1";
	private static final String lfimg2Column = "lfimg1";
	private static final String meinsColumn = "meins";
	private Connection connection;
	private PreparedStatement preparedStatement;
	private String carNo;
	private BigDecimal lfimg1;
	private BigDecimal lfimg2;
	private String meins;

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.drawString("Hello World!", 25, 25);
	}

	@Override
	public void init() {
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url);
			preparedStatement = connection.prepareStatement(query);
		} catch (Exception e) {
			throw new RuntimeException("db config error !");
		}
	}

	@Override
	public void destroy() {
		if (preparedStatement != null)
			try {
				preparedStatement.close();
			} catch (SQLException e1) {
				throw new RuntimeException("preparedStatement close failure !");
			}
		if (connection != null)
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException("connection close failure !");
			}
	}

	@Override
	public void start() {
		repaint();
	}

	public void read(final String poundNo) throws Exception {
		ResultSet rs = null;
		try {
			preparedStatement.setString(1, poundNo);
			rs = preparedStatement.executeQuery();
			if (!rs.next())
				throw new Exception("PoundNo: " + poundNo + " not found !");
			carNo = rs.getString(carNoColumn);
			lfimg1 = rs.getBigDecimal(lfimg1Column);
			lfimg2 = rs.getBigDecimal(lfimg2Column);
			meins = rs.getString(meinsColumn);
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				throw new RuntimeException("rs close failure !");
			}
		}
	}

	public String getCarNo() {
		return carNo;
	}

	public BigDecimal getLfimg1() {
		return lfimg1;
	}

	public BigDecimal getLfimg2() {
		return lfimg2;
	}

	public String getMeins() {
		meins = "KG";
		return meins;
	}
}
