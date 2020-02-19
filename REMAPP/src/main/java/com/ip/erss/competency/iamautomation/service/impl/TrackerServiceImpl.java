package com.ip.erss.competency.iamautomation.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ip.erss.competency.iamautomation.model.Tracker;
import com.ip.erss.competency.iamautomation.repository.TrackerRepository;
import com.ip.erss.competency.iamautomation.service.TrackerService;

@Service
@Transactional
public class TrackerServiceImpl implements TrackerService {
	
	@Autowired
	private TrackerRepository trackerRepository;
	
	@Override
	public List<Tracker> loadAllTrackers() {
		return trackerRepository.findAllByOrderByCreatedDateDesc();
	}

	@Override
	public void addTracker(Tracker tracker) {
		trackerRepository.saveAndFlush(tracker);
	}

	@Override
	public Tracker loadTrackerById(long trackerId) {
		return trackerRepository.findByTrackerId(trackerId);
	}

	@Override
	public void updateTracker(Tracker tracker) {
		trackerRepository.saveAndFlush(tracker);
	}

	@Override
	public void deleteTracker(long trackerId) {
		trackerRepository.delete(trackerId);
	}

	@Override
	public List<Tracker> loadTrackerByName(String accountName) {
		return trackerRepository.findByAccountName(accountName);
	}


}
