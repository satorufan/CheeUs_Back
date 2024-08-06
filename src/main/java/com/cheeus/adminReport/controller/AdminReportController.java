package com.cheeus.adminReport.controller;

import com.cheeus.adminReport.dto.AdminReportDto;
import com.cheeus.adminReport.service.AdminReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/admin")
public class AdminReportController {

	@Autowired
	private AdminReportService adminReportService;

	@GetMapping("/AdminReport")
	public List<AdminReportDto> getAdminReport(){
		return adminReportService.findAll();
	}


	@GetMapping("/AdminReport/{id}")
	public Optional<AdminReportDto> getAdminReportById(@PathVariable("id") int id){
		return adminReportService.findById(id);
	}


	@PostMapping("/AdminReport")
	public void insertBoard(@RequestBody AdminReportDto board){
		adminReportService.insert(board);
	}

	@PutMapping("/AdminReport/{id}")
	public String updateBoard(@RequestBody AdminReportDto board) {
		Optional<AdminReportDto> existing = adminReportService.findById(board.getId());
		if (existing.isPresent()) {
			adminReportService.update(board);
			return "update 성공";
		} else {
			return "update 실패";
		}
	}

	@DeleteMapping("/AdminReport/{id}")
	public String delete(@PathVariable("id") int id) {
		Optional<AdminReportDto> existing = adminReportService.findById(id);
		if (existing.isPresent()) {
			adminReportService.delete(id);
			return "delete 성공";
		} else {
			return "delete 실패";
		}
	}
}