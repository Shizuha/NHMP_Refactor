package ERP.Employee.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ERP.Employee.model.vo.Employee;




public class EmployeeDao {
	
	public Employee loginCheck(Connection conn, String userId, String userPwd) {
		Employee emp= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select * from employee where userid =? and userpwd = ?";

		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				emp = new Employee();

				emp.setEmpId(rs.getString("emp_id"));
				emp.setHostId(rs.getString("host_id"));
				emp.setHostPwd(rs.getString("host_pwd"));
				emp.setEmpName(rs.getString("emp_name"));
				emp.setHireDate(rs.getDate("hire_date"));
				emp.setRsnDate(rs.getDate("rsn_date"));
				emp.setEmpNo(rs.getString("emp_no"));
				emp.setAddress(rs.getString("address"));
				emp.setItnalFor(rs.getString("itnal_for"));
				emp.setAdTel(rs.getString("ad_tel"));
				emp.setPhone(rs.getString("phone"));
				emp.setEmail(rs.getString("email"));
				emp.setSalary(rs.getInt("salary"));
				emp.setUserId(rs.getString("userid"));
				emp.setUserpwd(rs.getString("userpwd"));
				emp.setEmpEtc(rs.getString("emp_etc"));
				emp.setEmpImgOriginalFilename(rs.getString("emp_img_original_filename"));
				emp.setEmpRenameFilename(rs.getString("emp_img_rename_filename"));
				emp.setGender(rs.getString("gender"));
				emp.setEmpmentCode(rs.getString("empment_code"));
				emp.setDeptCode(rs.getString("dept_code"));
				emp.setTeamCode(rs.getString("team_code"));
				emp.setPosCode(rs.getString("pos_code"));
				emp.setDeductionCode(rs.getString("deduction_code"));
				emp.setAllowanceCode(rs.getString("allowance_code"));
				emp.setAuthorityCode(rs.getString("authority_code"));
				emp.setWardCode(rs.getString("ward_code"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return emp;
	}

	public Employee selectOne(Connection conn, String empNo) {
		Employee emp = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String query = "select * from employee where emp_no = ?";

		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, empNo);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				emp = new Employee();
				
				emp.setEmpId(rs.getString("emp_id"));
				emp.setHostId(rs.getString("host_id"));
				emp.setHostPwd(rs.getString("host_pwd"));
				emp.setEmpName(rs.getString("emp_name"));
				emp.setHireDate(rs.getDate("hire_date"));
				emp.setRsnDate(rs.getDate("rsn_date"));
				emp.setEmpNo(rs.getString("emp_no"));
				emp.setAddress(rs.getString("address"));
				emp.setItnalFor(rs.getString("itnal_for"));
				emp.setAdTel(rs.getString("ad_tel"));
				emp.setPhone(rs.getString("phone"));
				emp.setEmail(rs.getString("email"));
				emp.setSalary(rs.getInt("salary"));
				emp.setUserId(rs.getString("userid"));
				emp.setUserpwd(rs.getString("userpwd"));
				emp.setEmpEtc(rs.getString("emp_etc"));
				emp.setEmpImgOriginalFilename(rs.getString("emp_img_original_filename"));
				emp.setEmpRenameFilename(rs.getString("emp_img_rename_filename"));
				emp.setGender(rs.getString("gender"));
				emp.setEmpmentCode(rs.getString("empment_code"));
				emp.setDeptCode(rs.getString("dept_code"));
				emp.setTeamCode(rs.getString("team_code"));
				emp.setPosCode(rs.getString("pos_code"));
				emp.setDeductionCode(rs.getString("deduction_code"));
				emp.setAllowanceCode(rs.getString("allowance_code"));
				emp.setAuthorityCode(rs.getString("authority_code"));
				emp.setWardCode(rs.getString("ward_code"));
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return emp;
	}

	public ArrayList<Employee> selectAll(Connection conn) {
		ArrayList<Employee> memList = new ArrayList<Employee>();

		Employee emp = null;
		Statement pstmt = null;
		ResultSet rs = null;

		String query = "select * from employee ";

		try {

			pstmt = conn.createStatement();

			rs = pstmt.executeQuery(query);

			while (rs.next()) {

				emp = new Employee();

				emp.setEmpId(rs.getString("emp_id"));
				emp.setHostId(rs.getString("host_id"));
				emp.setHostPwd(rs.getString("host_pwd"));
				emp.setEmpName(rs.getString("emp_name"));
				emp.setHireDate(rs.getDate("hire_date"));
				emp.setRsnDate(rs.getDate("rsn_date"));
				emp.setEmpNo(rs.getString("emp_no"));
				emp.setAddress(rs.getString("address"));
				emp.setItnalFor(rs.getString("itnal_for"));
				emp.setAdTel(rs.getString("ad_tel"));
				emp.setPhone(rs.getString("phone"));
				emp.setEmail(rs.getString("email"));
				emp.setSalary(rs.getInt("salary"));
				emp.setUserId(rs.getString("userid"));
				emp.setUserpwd(rs.getString("userpwd"));
				emp.setEmpEtc(rs.getString("emp_etc"));
				emp.setEmpImgOriginalFilename(rs.getString("emp_img_original_filename"));
				emp.setEmpRenameFilename(rs.getString("emp_img_rename_filename"));
				emp.setGender(rs.getString("gender"));
				emp.setEmpmentCode(rs.getString("empment_code"));
				emp.setDeptCode(rs.getString("dept_code"));
				emp.setTeamCode(rs.getString("team_code"));
				emp.setPosCode(rs.getString("pos_code"));
				emp.setDeductionCode(rs.getString("deduction_code"));
				emp.setAllowanceCode(rs.getString("allowance_code"));
				emp.setAuthorityCode(rs.getString("authority_code"));
				emp.setWardCode(rs.getString("ward_code"));
				emp.setHoldOffice(rs.getString("hode_code"));

				memList.add(emp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return memList;
	}

	

	

	public int deleteEmployee(Connection conn, String empNo) {
		int result = 0;

		PreparedStatement pstmt = null;
		String query = "delete from employee where emp_no =?";

		try {

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, empNo);
			

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			close(pstmt);

		}

		return result;
	}

	public int getListCount(Connection conn) {
		int result = 0;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "select count(*) from employee";
		try {
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(query);
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		
		
		return result;
	}

	public ArrayList<Employee> selectList(Connection conn, int startRow, int endRow) {
		ArrayList<Employee> empList = new ArrayList<Employee>();
		Employee emp = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "SELECT * " + 
				"FROM (SELECT ROWNUM rnum, emp_id, hire_date, phone, email, emp_name, address, emp_no" +  
				" FROM (SELECT * FROM employee " + 
				" ORDER BY emp_id desc, hire_date asc nulls first, phone asc, email asc nulls first))" + 
				" WHERE rnum >= ? and rnum <= ? ";
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {

				emp = new Employee();
				emp.setEmpId(rs.getString("emp_id"));
				emp.setHireDate(rs.getDate("hire_date"));
				emp.setEmpName(rs.getString("emp_name"));
				emp.setPhone(rs.getString("phone"));
				emp.setEmail(rs.getString("email"));
				emp.setAddress(rs.getString("address"));
				emp.setEmpNo(rs.getString("emp_no"));
				empList.add(emp);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		
		return empList;
	}

	public ArrayList<Employee> selectEMPOne(Connection conn, String empName, String deptName) {
		ArrayList<Employee> empList = new ArrayList<Employee>();
		Employee emp = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "SELECT * " + 
				"FROM EMPLOYEE " + 
				"WHERE EMP_NAME LIKE ? " + 
				"AND DEPT_CODE like ? ";
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + empName + "%");
			pstmt.setString(2, "%" + deptName + "%");
			rs = pstmt.executeQuery();
			
			while (rs.next()) {

				emp = new Employee();
				
				emp.setEmpId(rs.getString("emp_id"));
				emp.setHostId(rs.getString("host_id"));
				emp.setHostPwd(rs.getString("host_pwd"));
				emp.setEmpName(rs.getString("emp_name"));
				emp.setHireDate(rs.getDate("hire_date"));
				emp.setRsnDate(rs.getDate("rsn_date"));
				emp.setEmpNo(rs.getString("emp_no"));
				emp.setAddress(rs.getString("address"));
				emp.setItnalFor(rs.getString("itnal_for"));
				emp.setAdTel(rs.getString("ad_tel"));
				emp.setPhone(rs.getString("phone"));
				emp.setEmail(rs.getString("email"));
				emp.setSalary(rs.getInt("salary"));
				emp.setUserId(rs.getString("userid"));
				emp.setUserpwd(rs.getString("userpwd"));
				emp.setEmpEtc(rs.getString("emp_etc"));
				emp.setEmpImgOriginalFilename(rs.getString("emp_img_original_filename"));
				emp.setEmpRenameFilename(rs.getString("emp_img_rename_filename"));
				emp.setGender(rs.getString("gender"));
				emp.setEmpmentCode(rs.getString("empment_code"));
				emp.setDeptCode(rs.getString("dept_code"));
				emp.setTeamCode(rs.getString("team_code"));
				emp.setPosCode(rs.getString("pos_code"));
				emp.setDeductionCode(rs.getString("deduction_code"));
				emp.setAllowanceCode(rs.getString("allowance_code"));
				emp.setAuthorityCode(rs.getString("authority_code"));
				emp.setWardCode(rs.getString("ward_code"));
				emp.setHoldOffice(rs.getString("hode_code"));
				
				empList.add(emp);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		
		return empList;
	}

	public ArrayList<Employee> selectName(Connection conn, String empName) {
		ArrayList<Employee> empList = new ArrayList<Employee>();
		Employee emp = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String query = "select * from employee where emp_name = ?";

		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, empName);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				emp = new Employee();

				emp.setEmpId(rs.getString("emp_id"));
				emp.setHostId(rs.getString("host_id"));
				emp.setHostPwd(rs.getString("host_pwd"));
				emp.setEmpName(rs.getString("emp_name"));
				emp.setHireDate(rs.getDate("hire_date"));
				emp.setRsnDate(rs.getDate("rsn_date"));
				emp.setEmpNo(rs.getString("emp_no"));
				emp.setAddress(rs.getString("address"));
				emp.setItnalFor(rs.getString("itnal_for"));
				emp.setAdTel(rs.getString("ad_tel"));
				emp.setPhone(rs.getString("phone"));
				emp.setEmail(rs.getString("email"));
				emp.setSalary(rs.getInt("salary"));
				emp.setUserId(rs.getString("userid"));
				emp.setUserpwd(rs.getString("userpwd"));
				emp.setEmpEtc(rs.getString("emp_etc"));
				emp.setEmpImgOriginalFilename(rs.getString("emp_img_original_filename"));
				emp.setEmpRenameFilename(rs.getString("emp_img_rename_filename"));
				emp.setGender(rs.getString("gender"));
				emp.setEmpmentCode(rs.getString("empment_code"));
				emp.setDeptCode(rs.getString("dept_code"));
				emp.setTeamCode(rs.getString("team_code"));
				emp.setPosCode(rs.getString("pos_code"));
				emp.setDeductionCode(rs.getString("deduction_code"));
				emp.setAllowanceCode(rs.getString("allowance_code"));
				emp.setAuthorityCode(rs.getString("authority_code"));
				emp.setWardCode(rs.getString("ward_code"));
				emp.setHoldOffice(rs.getString("hode_code"));
				
				empList.add(emp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return empList;
	}

	public int updateEmployee(Connection conn, Employee emp) {
		int result= 0;
		PreparedStatement pstmt = null;
		
		String query = "update employee set emp_name= ?, empment_code = ?, dept_code =?, "
				+ "pos_code = ?, ITNAL_FOR = ?, EMP_NO = ?, AD_TEL = ?, PHONE = ?, address =? ,"
				+ "EMAIL = ?, SALARY = ?, EMP_ETC = ?, EMP_IMG_ORIGINAL_FILENAME = ?, "
				+ "EMP_IMG_RENAME_FILENAME = ?, GENDER = ?, TEAM_CODE = ?, WARD_CODE = ?, HODE_CODE = ? "
				+ "where emp_id = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, emp.getEmpName());
			pstmt.setString(2, emp.getEmpmentCode());
			pstmt.setString(3, emp.getDeptCode());
			pstmt.setString(4, emp.getPosCode());
			pstmt.setString(5, emp.getItnalFor());
			pstmt.setString(6, emp.getEmpNo());
			pstmt.setString(7, emp.getAdTel());
			pstmt.setString(8, emp.getPhone());
			pstmt.setString(9, emp.getAddress());
			pstmt.setString(10, emp.getEmail());
			pstmt.setInt(11, emp.getSalary());
			pstmt.setString(12, emp.getEmpEtc());
			pstmt.setString(13, emp.getEmpImgOriginalFilename());
			pstmt.setString(14, emp.getEmpRenameFilename());
			pstmt.setString(15, emp.getGender());
			pstmt.setString(16, emp.getTeamCode());
			pstmt.setString(17, emp.getWardCode());
			pstmt.setString(18, emp.getHoldOffice());
			pstmt.setString(19, emp.getEmpId());
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return result;
	}

	public int insertEmp(Connection conn, Employee emp) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String query = "insert into employee values("
				+ "seq_emp_id.nextval,?,?,?,sysdate, null, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
				+ "?, ?, ?, ?, ?, ?, ?, ?, default, default, ?, ?, ?)";
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, emp.getHostId());
			pstmt.setString(2, emp.getHostPwd());
			pstmt.setString(3, emp.getEmpName());
			pstmt.setString(4, emp.getEmpNo());
			pstmt.setString(5, emp.getAddress());
			pstmt.setString(6, emp.getItnalFor());
			pstmt.setString(7, emp.getAdTel());
			pstmt.setString(8, emp.getPhone());
			pstmt.setString(9, emp.getEmail());
			pstmt.setInt(10, emp.getSalary());
			pstmt.setString(11, emp.getUserId());
			pstmt.setString(12, emp.getUserpwd());
			pstmt.setString(13, emp.getEmpEtc());
			pstmt.setString(14, emp.getEmpImgOriginalFilename());
			pstmt.setString(15, emp.getEmpRenameFilename());
			pstmt.setString(16, emp.getGender());
			pstmt.setString(17, emp.getEmpmentCode());
			pstmt.setString(18, emp.getDeptCode());
			pstmt.setString(19, emp.getTeamCode());
			pstmt.setString(20, emp.getPosCode());
			pstmt.setString(21, emp.getAuthorityCode());
			pstmt.setString(22, emp.getWardCode());
			pstmt.setString(23, emp.getHoldOffice());
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int empNewPwdUpdate(Connection conn, String newPwd, String empId) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		String query = "update employee set USERPWD = ? where emp_id = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, newPwd);
			pstmt.setString(2, empId);
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public Employee selectEmpId(Connection conn, String empId) {
		Employee emp = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String query = "select * from employee where emp_Id = ?";

		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, empId);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				emp = new Employee();

				emp.setEmpId(rs.getString("emp_id"));
				emp.setHostId(rs.getString("host_id"));
				emp.setHostPwd(rs.getString("host_pwd"));
				emp.setEmpName(rs.getString("emp_name"));
				emp.setHireDate(rs.getDate("hire_date"));
				emp.setRsnDate(rs.getDate("rsn_date"));
				emp.setEmpNo(rs.getString("emp_no"));
				emp.setAddress(rs.getString("address"));
				emp.setItnalFor(rs.getString("itnal_for"));
				emp.setAdTel(rs.getString("ad_tel"));
				emp.setPhone(rs.getString("phone"));
				emp.setEmail(rs.getString("email"));
				emp.setSalary(rs.getInt("salary"));
				emp.setUserId(rs.getString("userid"));
				emp.setUserpwd(rs.getString("userpwd"));
				emp.setEmpEtc(rs.getString("emp_etc"));
				emp.setEmpImgOriginalFilename(rs.getString("emp_img_original_filename"));
				emp.setEmpRenameFilename(rs.getString("emp_img_rename_filename"));
				emp.setGender(rs.getString("gender"));
				emp.setEmpmentCode(rs.getString("empment_code"));
				emp.setDeptCode(rs.getString("dept_code"));
				emp.setTeamCode(rs.getString("team_code"));
				emp.setPosCode(rs.getString("pos_code"));
				emp.setDeductionCode(rs.getString("deduction_code"));
				emp.setAllowanceCode(rs.getString("allowance_code"));
				emp.setAuthorityCode(rs.getString("authority_code"));
				emp.setWardCode(rs.getString("ward_code"));
				emp.setHoldOffice(rs.getString("hode_code"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return emp;
	}

	public int selectCheckId(Connection conn, String userId) {
		int rowCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select count(userid) from employee where userid = ?";
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				rowCount = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}finally {
			close(pstmt);
			close(rs);
		}
		return rowCount;
	}

	public String selectEmpName(Connection conn, String userName) {
		String empName= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select emp_name from employee where emp_name like ?";
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + userName + "%");
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				empName = rs.getString("emp_name");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}finally {
			close(pstmt);
			close(rs);
		}
		return empName;
	}

	public ArrayList<Employee> selectOrganEmpList(Connection conn, String teamName) {
		ArrayList<Employee> empList = new ArrayList<Employee>();
		Employee emp = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String query = "select * " + 
				" from employee " + 
				" where team_code = (select team_code " + 
				" from team " + 
				" where team_name like ?) ";

		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, teamName);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				emp = new Employee();

				emp.setEmpId(rs.getString("emp_id"));
				emp.setHostId(rs.getString("host_id"));
				emp.setHostPwd(rs.getString("host_pwd"));
				emp.setEmpName(rs.getString("emp_name"));
				emp.setHireDate(rs.getDate("hire_date"));
				emp.setRsnDate(rs.getDate("rsn_date"));
				emp.setEmpNo(rs.getString("emp_no"));
				emp.setAddress(rs.getString("address"));
				emp.setItnalFor(rs.getString("itnal_for"));
				emp.setAdTel(rs.getString("ad_tel"));
				emp.setPhone(rs.getString("phone"));
				emp.setEmail(rs.getString("email"));
				emp.setSalary(rs.getInt("salary"));
				emp.setUserId(rs.getString("userid"));
				emp.setUserpwd(rs.getString("userpwd"));
				emp.setEmpEtc(rs.getString("emp_etc"));
				emp.setEmpImgOriginalFilename(rs.getString("emp_img_original_filename"));
				emp.setEmpRenameFilename(rs.getString("emp_img_rename_filename"));
				emp.setGender(rs.getString("gender"));
				emp.setEmpmentCode(rs.getString("empment_code"));
				emp.setDeptCode(rs.getString("dept_code"));
				emp.setTeamCode(rs.getString("team_code"));
				emp.setPosCode(rs.getString("pos_code"));
				emp.setDeductionCode(rs.getString("deduction_code"));
				emp.setAllowanceCode(rs.getString("allowance_code"));
				emp.setAuthorityCode(rs.getString("authority_code"));
				emp.setWardCode(rs.getString("ward_code"));
				emp.setHoldOffice(rs.getString("hode_code"));
				
				empList.add(emp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return empList;
	}

	public Employee inSelectEmpName(Connection conn, String empName) {
		// TODO Auto-generated method stub
		return null;
	}

	public int teamEmpcount(Connection conn, String teamCode) {
		int count = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select count(*) " + 
						"from employee " + 
						"where team_code = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, teamCode);
			
			rs = pstmt.executeQuery();
			
				if(rs.next()) {
					count = rs.getInt(1);
				}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return count;
	}

	public Employee selectOrganEmpOne(Connection conn, String empName) {
		Employee emp = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String query = "select * from employee where emp_name like ?";

		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + empName + "%");

			rs = pstmt.executeQuery();

				if(rs.next()) {

				emp = new Employee();

				emp.setEmpId(rs.getString("emp_id"));
				emp.setHostId(rs.getString("host_id"));
				emp.setHostPwd(rs.getString("host_pwd"));
				emp.setEmpName(rs.getString("emp_name"));
				emp.setHireDate(rs.getDate("hire_date"));
				emp.setRsnDate(rs.getDate("rsn_date"));
				emp.setEmpNo(rs.getString("emp_no"));
				emp.setAddress(rs.getString("address"));
				emp.setItnalFor(rs.getString("itnal_for"));
				emp.setAdTel(rs.getString("ad_tel"));
				emp.setPhone(rs.getString("phone"));
				emp.setEmail(rs.getString("email"));
				emp.setSalary(rs.getInt("salary"));
				emp.setUserId(rs.getString("userid"));
				emp.setUserpwd(rs.getString("userpwd"));
				emp.setEmpEtc(rs.getString("emp_etc"));
				emp.setEmpImgOriginalFilename(rs.getString("emp_img_original_filename"));
				emp.setEmpRenameFilename(rs.getString("emp_img_rename_filename"));
				emp.setGender(rs.getString("gender"));
				emp.setEmpmentCode(rs.getString("empment_code"));
				emp.setDeptCode(rs.getString("dept_code"));
				emp.setTeamCode(rs.getString("team_code"));
				emp.setPosCode(rs.getString("pos_code"));
				emp.setDeductionCode(rs.getString("deduction_code"));
				emp.setAllowanceCode(rs.getString("allowance_code"));
				emp.setAuthorityCode(rs.getString("authority_code"));
				emp.setWardCode(rs.getString("ward_code"));
				emp.setHoldOffice(rs.getString("hode_code"));
				
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return emp;
	}

	public ArrayList<Employee> selectAuthorityEmp(Connection conn, String auCode) {
		//권한부여관리에서 권한목록 체크박스 클릭시 해당권한 부여된 사원 조회해 오는 Dao
		ArrayList<Employee> empList = new ArrayList<Employee>();
		Employee emp = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String query = "select * from employee where AUTHORITY_CODE = ?";

		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, auCode);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				emp = new Employee();

				emp.setEmpId(rs.getString("emp_id"));
				emp.setHostId(rs.getString("host_id"));
				emp.setHostPwd(rs.getString("host_pwd"));
				emp.setEmpName(rs.getString("emp_name"));
				emp.setHireDate(rs.getDate("hire_date"));
				emp.setRsnDate(rs.getDate("rsn_date"));
				emp.setEmpNo(rs.getString("emp_no"));
				emp.setAddress(rs.getString("address"));
				emp.setItnalFor(rs.getString("itnal_for"));
				emp.setAdTel(rs.getString("ad_tel"));
				emp.setPhone(rs.getString("phone"));
				emp.setEmail(rs.getString("email"));
				emp.setSalary(rs.getInt("salary"));
				emp.setUserId(rs.getString("userid"));
				emp.setUserpwd(rs.getString("userpwd"));
				emp.setEmpEtc(rs.getString("emp_etc"));
				emp.setEmpImgOriginalFilename(rs.getString("emp_img_original_filename"));
				emp.setEmpRenameFilename(rs.getString("emp_img_rename_filename"));
				emp.setGender(rs.getString("gender"));
				emp.setEmpmentCode(rs.getString("empment_code"));
				emp.setDeptCode(rs.getString("dept_code"));
				emp.setTeamCode(rs.getString("team_code"));
				emp.setPosCode(rs.getString("pos_code"));
				emp.setDeductionCode(rs.getString("deduction_code"));
				emp.setAllowanceCode(rs.getString("allowance_code"));
				emp.setAuthorityCode(rs.getString("authority_code"));
				emp.setWardCode(rs.getString("ward_code"));
				emp.setHoldOffice(rs.getString("hode_code"));
				
				empList.add(emp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return empList;
	}

	public String EmployeeCount(Connection conn) {
		// 사원 총명수 카운트 Dao
		String result = "0";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select COUNT(*) from employee";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getString(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return result;
	}

	public int AuthorUpdateEmp(Connection conn, String empId, String authority) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update employee set AUTHORITY_CODE = ? where EMP_ID = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, authority);
			pstmt.setString(2, empId);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public int DeleteAuEmployee(Connection conn, String empId, String au) {
		//권한삭제시 해당권한들 G1으로 변경하는 Dao
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update employee set AUTHORITY_CODE = ? where EMP_ID = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, au);
			pstmt.setString(2, empId);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public int selectCheckEmail(Connection conn, String email) {
		//인사정보등록 사원 이메일 확인 dao
		int rowCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select count(email) from employee where email = ?";
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, email);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				rowCount = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}finally {
			close(pstmt);
			close(rs);
		}
		return rowCount;
	}

	public int selectCheckEmpName(Connection conn, String empName) {
		//인사정보등록 사원 이름 확인 dao
		int rowCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select count(emp_name) from employee where emp_name = ?";
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, empName);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				rowCount = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}finally {
			close(pstmt);
			close(rs);
		}
		return rowCount;
	}

	

	

}

