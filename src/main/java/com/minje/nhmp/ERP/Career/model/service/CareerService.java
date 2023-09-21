package ERP.Career.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import ERP.Career.model.dao.CareerDao;
import ERP.Career.model.vo.Career;
import static common.JDBCTemplate.*;

public class CareerService {
	
private CareerDao cDao = new CareerDao();
	
	public CareerService() {}

	public int inserCar(Career c,String hostId, String hostPwd) {
		Connection conn = getConnection(hostId, hostPwd);
		int result = cDao.insertCar(conn, c);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		
		return result;
	}

	public ArrayList<Career> selectList(String empId,String hostId,String hostPwd) {
		Connection conn = getConnection(hostId, hostPwd);
		ArrayList<Career> carList = cDao.selectList(conn, empId);
		close(conn);
		return carList;
	}

	public int updateCar(Career c,String hostId,String hostPwd) {
		Connection conn = getConnection(hostId, hostPwd);
		int result = cDao.updateCar(conn, c);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		
		return result;
	}

	public String[] selectCarCode(String empId, int length,String hostId,String hostPwd) {
		Connection conn = getConnection(hostId, hostPwd);
		String[] carCode = cDao.selectCarCode(empId, length, conn);
		close(conn);
		return carCode;
	}
}
