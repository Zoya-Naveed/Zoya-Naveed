package com.github.group37.roadmap.controllers;
import com.github.group37.roadmap.percistance.models.Feedback;
import com.github.group37.roadmap.service.FeedbackService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class FeedbackController {

	private FeedbackService feedbackService;
	
	@Autowired
	public FeedbackController(FeedbackService feedbackService) {
		this.feedbackService=feedbackService;
	}
	
	@PostMapping("/feedback")
	@Transactional
	public Feedback submitFeedback(@RequestBody Feedback feedback){
		return feedbackService.saveFeedback(feedback);
	}
	
	@GetMapping("/feedback")
	public List<Feedback> getAllFeedback() {
		return feedbackService.getAllFeedback();
	}

}
