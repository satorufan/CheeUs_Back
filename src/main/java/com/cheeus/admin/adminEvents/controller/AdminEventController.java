package com.cheeus.admin.adminEvents.controller;

import com.cheeus.admin.adminEvents.dto.AdminEventDto;
import com.cheeus.admin.adminEvents.service.AdminEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/admin")
public class AdminEventController {

	@Autowired
	private AdminEventService eventService;

	@GetMapping("/AdminEvent")
	public List<AdminEventDto> getBoard(){
		return eventService.findAll();
	}

	@GetMapping("/AdminEvent/{id}")
	public Optional<AdminEventDto> getBoardById(@PathVariable("id") int id){
		return eventService.findById(id);
	}

	@PostMapping("/AdminEvent")
	public void insertBoard(@RequestBody AdminEventDto adminEventDto){
		eventService.insert(adminEventDto);
	}

	@PutMapping("/AdminEvent/{id}")
	public String updateBoard(@RequestBody AdminEventDto adminEventDto) {
		Optional<AdminEventDto> existing = eventService.findById(adminEventDto.getId());
		if (existing.isPresent()) {
			eventService.update(adminEventDto);
			return "update 성공";
		} else {
			return "update 실패";
		}
	}

	@DeleteMapping("/AdminEvent/{id}")
	public String delete(@PathVariable("id") int id) {
		Optional<AdminEventDto> existing = eventService.findById(id);
		if (existing.isPresent()) {
			eventService.delete(id);
			return "delete 성공";
		} else {
			return "delete 실패";
		}
	}
}