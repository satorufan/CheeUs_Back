package com.cheeus.user.magazine;

import com.cheeus.admin.adminMagazines.dto.AdminMagazineDto;
import com.cheeus.admin.adminMagazines.service.AdminMagazineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/")
public class UserMagazineController {

	@Autowired
	private AdminMagazineService magazineService;

	@GetMapping("/Magazine")
	public List<AdminMagazineDto> getBoard(){
		return magazineService.findAll();
	}

	@GetMapping("/Magazine/{id}")
	public Optional<AdminMagazineDto> getBoardById(@PathVariable("id") int id){
		return magazineService.findById(id);
	}

}