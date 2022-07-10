package com.logevents.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.logevents.demo.entity.LogEvent;
import com.logevents.demo.service.ILogEventService;

@RestController("/")
public class LogEventController {
	
	private final String LOG_FILE_PATH = "classpath:logfile.txt";

	@Autowired
	private ILogEventService logEventService;
	
	public ResponseEntity<List<LogEvent>> getLogEvents() {
		List<LogEvent> logEvents = logEventService.readLogEvents(LOG_FILE_PATH);
		return ResponseEntity.ok(logEvents);
	} 
}
