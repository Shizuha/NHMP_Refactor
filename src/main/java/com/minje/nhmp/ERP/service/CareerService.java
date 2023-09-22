package com.minje.nhmp.ERP.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.minje.nhmp.ERP.dao.CareerDao;
import com.minje.nhmp.ERP.vo.CareerVo;
import static com.minje.nhmp.common.JDBCTemplate.*;

public class CareerService {
	
private CareerDao cDao = new CareerDao();
	
	public CareerService() {}

	public int inserCar(CareerVo c,String hostId, String hostPwd) {
		Connection conn = getConnection(hostId, hostPwd);
		int result = cDao.insertCar(conn, c);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		
		return result;
	}

	public ArrayList<CareerVo> selectList(String empId,String hostId,String hostPwd) {
		Connection conn = getConnection(hostId, hostPwd);
		ArrayList<CareerVo> carList = cDao.selectList(conn, empId);
		close(conn);
		return carList;
	}

	public int updateCar(CareerVo c,String hostId,String hostPwd) {
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
