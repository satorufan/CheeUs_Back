package com.cheeus.admin.adminMagazines.controller;

import com.cheeus.admin.adminMagazines.dto.AdminMagazineDto;
import com.cheeus.admin.adminMagazines.service.AdminMagazineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/admin")
public class AdminMagazineController {

	@Autowired
	private AdminMagazineService magazineService;

	@GetMapping("/AdminMagazine")
	public List<AdminMagazineDto> getBoard(){
		return magazineService.findAll();
	}

	@GetMapping("/AdminMagazine/{id}")
	public Optional<AdminMagazineDto> getBoardById(@PathVariable("id") int id){
		return magazineService.findById(id);
	}

	@PostMapping("/AdminMagazine")
	public void insertBoard(@RequestBody AdminMagazineDto adminMagazineDto){
		magazineService.insert(adminMagazineDto);
	}

	@PutMapping("/AdminMagazine/{id}")
	public String updateBoard(@RequestBody AdminMagazineDto adminMagazineDto) {
		Optional<AdminMagazineDto> existing = magazineService.findById(adminMagazineDto.getId());
		if (existing.isPresent()) {
			magazineService.update(adminMagazineDto);
			return "update 성공";
		} else {
			return "update 실패";
		}
	}

	@DeleteMapping("/AdminMagazine/{id}")
	public String delete(@PathVariable("id") int id) {
		Optional<AdminMagazineDto> existing = magazineService.findById(id);
		if (existing.isPresent()) {
			magazineService.delete(id);
			return "delete 성공";
		} else {
			return "delete 실패";
		}
	}
}