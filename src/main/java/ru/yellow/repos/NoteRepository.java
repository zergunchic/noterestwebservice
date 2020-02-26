package ru.yellow.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ru.yellow.entities.Note;

public interface NoteRepository extends JpaRepository<Note, Integer> {
	@Query("SELECT note FROM Note note WHERE LOWER(note.title) LIKE CONCAT('%',LOWER(:text),'%') OR LOWER(note.text) LIKE CONCAT('%',LOWER(:text),'%')")
	public List<Note> findByTitleLikeOrFindByTextLike(@Param(value = "text") String textToFind);
}
