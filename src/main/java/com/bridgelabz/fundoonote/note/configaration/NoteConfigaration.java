package com.bridgelabz.fundoonote.note.configaration;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import com.bridgelabz.fundoonote.note.service.NoteService;

@Configuration
@EnableScheduling
public class NoteConfigaration {

	@Autowired
	NoteService noteService;

	// parameters of cron = seconds,minute, hour, day, month, weekday
	@Scheduled(cron = "0/3 * * * * ?")
	public void deletenote() {
		System.out.println("Deletion method is performing from trash : " + LocalDateTime.now());
		noteService.deleteNoteTrash();

	}

}
