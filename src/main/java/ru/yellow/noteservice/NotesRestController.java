package ru.yellow.noteservice;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.yellow.entities.Note;
import ru.yellow.repos.NoteRepository;

@RestController
@RequestMapping("api")
public class NotesRestController {
	@Autowired
	NoteRepository noteRepo;

	@GetMapping
	private List<Note> getAllNotes(){
		return noteRepo.findAll();
	}
	
	@PostMapping
	public Note saveNote(@Valid @RequestBody Note note) {
	    return noteRepo.save(note);
	}
	
	@PostMapping("filter")
	public List<Note> filterNotes(@RequestBody FilterDTO filter){
		if(Objects.isNull(filter.filterText)||filter.filterText.isEmpty()) return noteRepo.findAll();
		return noteRepo.findByTitleLikeOrFindByTextLike(filter.filterText);
		
	}
	
	@DeleteMapping("{id}")
	public void deleteNote(@NotNull(message = "Note with selected id does't exist") @PathVariable("id")  Note note) {
		noteRepo.delete(note);
	}
	
}
