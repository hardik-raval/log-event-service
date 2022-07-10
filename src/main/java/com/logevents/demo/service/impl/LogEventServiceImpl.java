package com.logevents.demo.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.logevents.demo.domain.LogEventVO;
import com.logevents.demo.entity.LogEvent;
import com.logevents.demo.repository.ILogEventRepository;
import com.logevents.demo.service.ILogEventService;

@PropertySource("classpath:application.properties")
@Service
public class LogEventServiceImpl implements ILogEventService {

	private static final Logger logger = LoggerFactory.getLogger(LogEventServiceImpl.class);

	@Autowired
	private ILogEventRepository logEventRepository;

	private static int ALERT_DURATION = 4;

	@Override
	public List<LogEvent> readLogEvents(String filePath) {
		List<LogEvent> response = null;
		File file = null;
		ObjectMapper objectMapper = new ObjectMapper();
		Scanner sc;
		List<LogEventVO> events = new ArrayList<>(5);
		try {
			file = ResourceUtils.getFile(filePath);
			sc = new Scanner(file);
			while (sc.hasNextLine()) {
				String value = sc.nextLine();
				LogEventVO event = objectMapper.readValue(value, LogEventVO.class);
				events.add(event);
			}
			response = processEvents(events);
		} catch (JsonProcessingException e) {
			logger.error("JsonProcessingException", e);
		} catch (FileNotFoundException e) {
			logger.error("FileNotFoundException", e);
		}
		return response;
	}

	private List<LogEvent> processEvents(List<LogEventVO> events) {
		List<LogEvent> logEvents = new ArrayList<LogEvent>();
		Set<String> ids = events.stream().map(x -> x.getId()).collect(Collectors.toSet());
		for (String id : ids) {
			logger.debug("Processing the event for id: {}", id);
			LogEventVO startEvent = null;
			LogEventVO finishEvent = null;
			
			for (LogEventVO event : events) {
				if (id.equalsIgnoreCase(event.getId())) {
					if ("STARTED".equalsIgnoreCase(event.getState())) {
						startEvent = event;
					} else if ("FINISHED".equalsIgnoreCase(event.getState())) {
						finishEvent = event;
					}
				}
				if (startEvent != null && finishEvent != null) {
					break;
				}
			}
			if (startEvent != null && finishEvent != null) {
				Long duration = Long.valueOf(finishEvent.getTimestamp()) - Long.valueOf(startEvent.getTimestamp());
				boolean alert = false;
				if (duration > ALERT_DURATION) {
					logger.debug("Event duration for \"{}\" is {}, it is more than the threashold {}", id, duration, ALERT_DURATION);
					alert = true;
				}
				
				//save the data in db
				saveLogEvents(startEvent, duration, alert, logEvents);
			}
		}
		return logEvents;
	}

	private void saveLogEvents(LogEventVO event, Long duration, boolean alert, List<LogEvent> logEvents) {
		LogEvent dbEvent = new LogEvent(event.getId(), String.valueOf(duration), event.getType(), event.getHost(), (duration > ALERT_DURATION) ? "true" : "false");
		LogEvent logEvent = logEventRepository.save(dbEvent);
		logger.info("Data saved successfully.. ");
		logEvents.add(logEvent);
	}
}
