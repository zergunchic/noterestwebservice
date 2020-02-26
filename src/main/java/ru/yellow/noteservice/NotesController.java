package ru.yellow.noteservice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class NotesController {
	@GetMapping()
	public String getPage() {
		return "forward:/webinterface.html";
	}
}
