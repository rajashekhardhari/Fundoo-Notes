package com.bridgelabz.fundoonote.label.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonote.label.models.Label;

@Repository
public interface LabelRepo extends JpaRepository<Label, Integer> {

	Optional<Label> findByLabelIdAndNoteId(int labelId, int userId);

}
