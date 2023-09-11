package ERP.Employee.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import ERP.Employee.model.dao.EmployeeDao;
import ERP.Employee.model.vo.Employee;
import Main.vo.NursingHospitalVo;
public class EmployeeService {
	
	private EmployeeDao eDao = new EmployeeDao();
	
	public EmployeeService() {}

	public Employee loginCheck(String userId, String userPwd,String hostId, String hostPwd) {
		Connection conn = getConnection(hostId, hostPwd);
		Employee emp = eDao.loginCheck(conn, userId, userPwd);
		close(conn);
		return emp;
	}

	public int getListCount(String hostId, String hostPwd) {
		Connection conn = getConnection(hostId, hostPwd);
		int result = eDao.getListCount(conn);
		close(conn);
		
		return result;
	}

	public ArrayList<Employee> selectList(int startRow, int endRow,String hostId, String hostPwd) {
		
		Connection conn = getConnection(hostId, hostPwd);
		ArrayList<Employee> memList = eDao.selectList(conn, startRow, endRow);
		
		close(conn);
		return memList;
	}
	//승민이가 손대쑵니다 죄송합니다 ㅠ
	public ArrayList<Employee> selectAll(NursingHospitalVo loginHospital) {
		String userid = loginHospital.getNH_USERID();
		String userpwd = loginHospital.getNH_USERPWD();
		Connection conn = getConnection(userid, userpwd);
		ArrayList<Employee> empList = eDao.selectAll(conn);
		
		close(conn);
		return empList;
	}
	
	public int updateEmployee(Employee emp) {
		Connection conn = getConnection(emp.getHostId(), emp.getHostPwd());
		int result = eDao.updateEmployee(conn, emp);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		
		return result;
	}

	public int deleteEmployee(String empNo,String hostId, String hostPwd) {
		Connection conn = getConnection(hostId, hostPwd);
		int result = eDao.deleteEmployee(conn, empNo);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		
		return result;
	}

	public Employee selectOne(String empNo,String hostId, String hostPwd) {
		Connection conn = getConnection(hostId, hostPwd);
		Employee emp = eDao.selectOne(conn, empNo);
		close(conn);
		return emp;
	}

	public ArrayList<Employee> selectName(String empName,String hostId, String hostPwd) {
		Connection conn = getConnection(hostId, hostPwd);
		ArrayList<Employee> emp = eDao.selectName(conn, empName);
		close(conn);
		return emp;
	}

	public ArrayList<Employee> selectEMPOne(String empName, String deptName,String hostId, String hostPwd) {
		Connection conn = getConnection(hostId, hostPwd);
		ArrayList<Employee> memList = eDao.selectEMPOne(conn, empName, deptName);
		
		close(conn);
		return memList;
	}

	public int empNewPwdUpdate(String newPwd, String empId,String hostId, String hostPwd) {
		Connection conn = getConnection(hostId, hostPwd);
		int result = eDao.empNewPwdUpdate(conn, newPwd, empId);
		
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int insertEmp(Employee emp) {
		Connection conn = getConnection(emp.getHostId(), emp.getHostPwd());
		int result = eDao.insertEmp(conn, emp);
		
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public Employee selectEmpId(String empId,String hostId, String hostPwd) {
		Connection conn = getConnection(hostId, hostPwd);
		Employee emp = eDao.selectEmpId(conn, empId);
		close(conn);
		return emp;
	}

	public int selectCheckId(String userId,String hostId, String hostPwd) {
		//사원정보등록페이지 에서 사원아이디 입력시 중복체크하는 서비스
		Connection conn = getConnection(hostId, hostPwd);
		int result = eDao.selectCheckId(conn, userId);
		
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public String selectEmpName(String userName,String hostId, String hostPwd) {
		Connection conn = getConnection(hostId, hostPwd);
		String empName = eDao.selectEmpName(conn, userName);
		close(conn);
		return empName;
	}

	public Employee selectIDOne(String empid, NursingHospitalVo loginHospital) {
		String userid = loginHospital.getNH_USERID();
		String userpwd = loginHospital.getNH_USERPWD();
		Connection conn = getConnection(userid, userpwd);
		Employee emp = eDao.selectEmpId(conn, empid);
		close(conn);
		return emp;
	}

	public ArrayList<Employee> selectEmployeeList(String hostId, String hostPwd) {
		Connection conn = getConnection(hostId, hostPwd);
		ArrayList<Employee> empList = eDao.selectAll(conn);
		
		close(conn);
		return empList;
	}

	public ArrayList<Employee> selectOrganEmpList(String hostId, String hostPwd, String teamName) {
		Connection conn = getConnection(hostId, hostPwd);
		ArrayList<Employee> empList = eDao.selectOrganEmpList(conn, teamName);
		
		close(conn);
		return empList;
	}

	public Employee inSelectEmpName(String empName, String hostId, String hostPwd) {
		Connection conn = getConnection(hostId, hostPwd);
		Employee emp = eDao.inSelectEmpName(conn, empName);
		close(conn);
		return emp;
	}

	public int teamEmpcount(String teamCode, String hostId, String hostPwd) {
		//조직도에서 팀이름 클릭시 팀에 부여된 사원 카운트 확인용 서비스
		Connection conn = getConnection(hostId, hostPwd);
		int result = eDao.teamEmpcount(conn, teamCode);
		
		close(conn);
		return result;
	}

	public Employee selectOrganEmpOne(String hostId, String hostPwd, String empName) {
		//조직도 사원 정보 클릭시 사원정보 1명 조회출력
		Connection conn = getConnection(hostId, hostPwd);
		Employee emp = eDao.selectOrganEmpOne(conn, empName);
		close(conn);
		return emp;
	}

	public ArrayList<Employee> selectAuthorityEmp(String hostId, String hostPwd, String auCode) {
		//권한부여관리에서 권한 체크박스 클릭시 해당권한 부여된 사원 조회용 서비스
		Connection conn = getConnection(hostId, hostPwd);
		ArrayList<Employee> empList = eDao.selectAuthorityEmp(conn, auCode);
		close(conn);
		return empList;
	}

	public String EmployeeCount(NursingHospitalVo loginHospital) {
		// 어드민 메인 사원 총명수 카운트 조회출력
		Connection conn = getConnection(loginHospital.getNH_USERID(), loginHospital.getNH_USERPWD());
		String result = eDao.EmployeeCount(conn);
		return result;
	}

	public int AuthorUpdateEmp(String hostId, String hostPwd, String empId, String authority) {
		//권한부여관리 사용자 선택창에서 사원 권한변경처리 업데이트 서비스
		Connection conn = getConnection(hostId, hostPwd);
		int result = eDao.AuthorUpdateEmp(conn, empId,authority);
		
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int DeleteAuEmployee(String hostId, String hostPwd, String empId, String au) {
		//사원들 권한 삭제시 권한 G1으로 바꾸는 서비스
		Connection conn = getConnection(hostId, hostPwd);
		int result = eDao.DeleteAuEmployee(conn, empId,au);
		
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int selectCheckEmail(String email, String hostId, String hostPwd) {
		//인사정보등록 사원이메일 에이작스 확인 서비스
		Connection conn = getConnection(hostId, hostPwd);
		int result = eDao.selectCheckEmail(conn, email);
		
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int selectCheckEmpName(String empName, String hostId, String hostPwd) {
		//인사정보등록 사원 이름 중복확인 컨트롤러
		Connection conn = getConnection(hostId, hostPwd);
		int result = eDao.selectCheckEmpName(conn, empName);
		
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	

	
	
	

}