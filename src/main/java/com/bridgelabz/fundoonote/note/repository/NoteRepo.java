package com.bridgelabz.fundoonote.note.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonote.note.models.Note;

@Repository
public interface NoteRepo extends JpaRepository<Note, Integer> {

	Optional<Note> findByTitle(String title);

	Optional<Note> findById(Integer noteId);

	
}
