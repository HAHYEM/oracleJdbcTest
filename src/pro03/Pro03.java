package pro03;

import java.sql.*;

public class Pro03 {

	public static void main(String[] args) {
		// 0. import java.sql.*;
		Connection conn = null;

		// 쿼리 관련된 클래스
		PreparedStatement pstmt = null;

		// SELECT 시에 필요
		ResultSet rs = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "hr", "hr");

			// 3. SQL문 준비 / 바인딩 / 실행

			String query = " SELECT E.employee_id, E.last_name , E.email, J.job_title, D.department_name, L.city "
			+ " FROM employees E, jobs J, departments D, locations L "
			+ " WHERE E.job_id = J.job_id "
			+ " AND E.department_id = D.department_id "
			+ " AND D.location_id = L.location_id "
			+ " AND j.job_id = 'PU_CLERK' "
			+ " AND l.city = 'Seattle' "
			+ " ORDER BY E.employee_id desc ";
			
			
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int employeeId = rs.getInt("employee_id");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				String jobTitle = rs.getString("job_title");
				String departmentName = rs.getString("department_name");
				String city = rs.getString("city");

				System.out.println( employeeId+ "\t"+ lastName + "\t"+ email + "\t"+ jobTitle + "\t"+ departmentName + "\t"+ departmentName + "\t"+ city);
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
	}


}