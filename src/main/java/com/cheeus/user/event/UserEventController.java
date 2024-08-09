package com.cheeus.user.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/")
public class UserEventController {

	@Autowired
	private UserEventService eventService;

	@GetMapping("/Event")
	public List<UserEventDto> getBoard(){
		return eventService.findAll();
	}

	@GetMapping("/Event/{id}")
	public Optional<UserEventDto> getBoardById(@PathVariable("id") int id){
		return eventService.findById(id);
	}

}