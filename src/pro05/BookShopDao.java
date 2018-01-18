package pro05;

import java.sql.*;
import java.util.*;

public class BookShopDao {
	// BookShopDao �� �ۼ��մϴ�.

	public void insert(BookVo vo) {
		// 0. import java.sql.*; ctrl + shift + o
		Connection conn = null;
		PreparedStatement pstmt = null; // ������ ����

		try {
			// 1. JDBC ����̹� (Oracle) �ε�
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection ������
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// �߿� 3. SQL�� �غ� / ���ε� / ����
			String query = "INSERT INTO bookshop VALUES(seq_bookshop_id.nextval, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getPubs());
			pstmt.setString(3, vo.getPubDate());
			pstmt.setString(4, vo.getAuthorName());
			pstmt.setInt(5, vo.getStateCode());

			int count = pstmt.executeUpdate();

			// �߿� 4.���ó��
			System.out.println(count + "�� ����Ϸ�");
		} catch (ClassNotFoundException e) {
			System.out.println("error: ����̹� �ε� ���� - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. �ڿ�����
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
	}

	public BookVo selectBook(int BookId) {

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BookVo vo = new BookVo();

		try {
			// 1. JDBC ����̹� (Oracle) �ε�

			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection ������
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL�� �غ� / ���ε� / ����

			String query = " SELECT id, title, pubs, pub_date, author_name, state_code " 
							+ " FROM bookshop "
							+ " WHERE id = ? ";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, BookId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				String pubDate = rs.getString("pub_date");
				String authorName = rs.getString("author_name");
				int stateCode = rs.getInt("state_code");

				vo.setId(id);
				vo.setTitle(title);
				vo.setPubs(pubs);
				vo.setPubDate(pubDate);
				vo.setAuthorName(authorName);
				vo.setStateCode(stateCode);

			} else {
				vo = null;
			}

		} catch (ClassNotFoundException e) {
			System.out.println("error: ����̹� �ε� ���� - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. �ڿ�����
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

		}

		return vo;
	}

	public static List<BookVo> getListAll() {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<BookVo> bookshopList = new ArrayList<BookVo>();

		try {
			// 1. JDBC ����̹� (Oracle) �ε�
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection ������
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL�� �غ� / ���ε� / ����
			String query = "SELECT id, title, pubs, pub_date, author_name, state_code FROM bookshop";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery(); // �����°�

			// 4.���ó��
			while (rs.next()) {
				BookVo vo = new BookVo();

				int id = rs.getInt("id");
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				String pubDate = rs.getString("pub_date");
				String authorName = rs.getString("author_name");
				int stateCode = rs.getInt("state_code");

				vo.setId(id);
				vo.setTitle(title);
				vo.setPubs(pubs);
				vo.setPubDate(pubDate);
				vo.setAuthorName(authorName);
				vo.setStateCode(stateCode);

				bookshopList.add(vo);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("error: ����̹� �ε� ���� - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. �ڿ�����
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		return bookshopList;
	}

	public void rent(int id) {
		// 0. import java.sql.*;
		Connection conn = null;

		// ���� ���õ� Ŭ����
		PreparedStatement pstmt = null;

		try {
			// 1. JDBC ����̹� (Oracle) �ε�
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection ������
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL�� �غ� / ���ε� / ����

			String query = "UPDATE bookshop SET state_code = ? WHERE id = ?";

			pstmt = conn.prepareStatement(query);
			BookVo lsb = selectBook(id);

			int state = 0;
			
			if (lsb.getStateCode() == 1) {
				state = 0;
			} else if (lsb.getStateCode() == 0) {
				state = 1;
			}

			pstmt.setInt(1, state);
			pstmt.setInt(2, id);

			int count = pstmt.executeUpdate();

			System.out.println(lsb.getTitle() + "��(��) �뿩 �ƽ��ϴ�.");

		} catch (ClassNotFoundException e) {
			System.out.println("error: ����̹� �ε� ���� - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. �ڿ�����
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

		}

	}

}
