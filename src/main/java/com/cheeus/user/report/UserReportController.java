package com.cheeus.user.report;

import com.cheeus.user.report.UserReportDto;
import com.cheeus.user.report.UserReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/report")
public class UserReportController {

	@Autowired
	private UserReportService adminReportService;

	@PostMapping("/insert")
	public void insertBoard(@RequestBody UserReportDto board){
		System.out.println("Received POST request: " + board);
		adminReportService.insert(board);
	}

}