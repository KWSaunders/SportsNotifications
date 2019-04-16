package com.sportsnotifyme.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.sportsnotifyme.api.util.ContentManager;
import com.sportsnotifyme.api.util.WebRequest;

@RestController
public class ScoreboardController {

	@RequestMapping("/nba/live.json")
	public String greeting(@RequestParam(value = "teams", defaultValue = "") String teams) {
		WebRequest request = new WebRequest("http://www.espn.com/nba/bottomline/scores");
		return ContentManager.transfer(request.getContent());
	}
}