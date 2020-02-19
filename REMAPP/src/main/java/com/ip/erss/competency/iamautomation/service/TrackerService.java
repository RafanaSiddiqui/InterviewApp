package com.ip.erss.competency.iamautomation.service;

import java.util.List;

import com.ip.erss.competency.iamautomation.model.Tracker;

public interface TrackerService {

	List<Tracker> loadAllTrackers();

	void addTracker(Tracker tracker);

	Tracker loadTrackerById(long trackerId);

	void updateTracker(Tracker tracker);

	void deleteTracker(long trackerId);

	List<Tracker> loadTrackerByName(String accountName);
	
}
