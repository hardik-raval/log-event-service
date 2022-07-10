package com.eventdata.demo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.logevents.demo.entity.LogEvent;
import com.logevents.demo.repository.ILogEventRepository;
import com.logevents.demo.service.impl.LogEventServiceImpl;

@ExtendWith(MockitoExtension.class)
public class LogEventApplicationTests {

	@InjectMocks
	LogEventServiceImpl logEventService;

	@Mock
	ILogEventRepository logEventRepository;
	
	private final String LOG_FILE_PATH = "classpath:logfile.txt";
	
	@Test
	void readLogEventsTest() throws FileNotFoundException {
		List<LogEvent> events = logEventService.readLogEvents(LOG_FILE_PATH);
		assertTrue(events != null);
		assertTrue(events.size() > 0);
	}
}
