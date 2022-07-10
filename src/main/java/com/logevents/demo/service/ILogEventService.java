package com.logevents.demo.service;

import java.util.List;

import com.logevents.demo.entity.LogEvent;

public interface ILogEventService {

	public List<LogEvent> readLogEvents(String filePath);
}
