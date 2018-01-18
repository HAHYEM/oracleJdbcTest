package pro05;

import java.sql.*;
import java.util.*;

import pro04.MemberVo;

public class BookShopDao {

	public void insert(BookVo vo) {

		// 0. import java.sql.*;
		Connection conn = null;

		// 쿼리 관련된 클래스
		PreparedStatement pstmt = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL문 준비 / 바인딩 / 실행

			String query = "INSERT INTO bookshop VALUES (seq_bookshop_id.nextval,?,?,?,?,?)";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getPubs());
			pstmt.setString(3, vo.getPubDate());
			pstmt.setString(4, vo.getAuthorName());
			pstmt.setInt(5, vo.getStateCode());	//int로 바꿈.

			int count = pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
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
			// 1. JDBC 드라이버 (Oracle) 로딩

			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL문 준비 / 바인딩 / 실행

			String query = " SELECT id, title, pubs, pub_date, author_name, state_code " + " FROM bookshop "
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
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
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
	
	static public List<BookVo> getListAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BookVo> bookList = new ArrayList<BookVo>();

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = " SELECT id, title, pubs, pub_date, author_name, state_code " + " FROM bookshop ";
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();
			
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
				
				bookList.add(vo);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
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
	
		return bookList;
	}
	

	public void rent(int id) {

		// 0. import java.sql.*;
		Connection conn = null;

		// 쿼리 관련된 클래스
		PreparedStatement pstmt = null;


		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL문 준비 / 바인딩 / 실행

			String query = "UPDATE bookshop SET state_code = ? WHERE id = ?";

			pstmt = conn.prepareStatement(query);
			BookVo brrowBook = selectBook(id);
			
			int st = 0;
			if (brrowBook.getStateCode() == 1) {
				st = 0;
			} else if (brrowBook.getStateCode() == 0) {
				st = 1;
			}

			pstmt.setInt(1, st);
			pstmt.setInt(2, id);

			int count = pstmt.executeUpdate();
			
			System.out.println(brrowBook.getTitle() + "이(가) 대여 됐습니다.");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
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
