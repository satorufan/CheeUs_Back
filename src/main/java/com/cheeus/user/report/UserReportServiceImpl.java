package com.cheeus.user.report;

import com.cheeus.user.report.UserReportDto;
import com.cheeus.user.report.UserReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserReportServiceImpl implements UserReportService {

	@Autowired
	private UserReportMapper userReportMapper;

	@Override
	public void insert(UserReportDto board) {
		userReportMapper.insert(board);
	}


}
