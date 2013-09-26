package edu.asu.momo.recording;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BreakTimeManager {
	
	@Autowired
	private List<BreakTime> breakTimes;

	public List<BreakTime> getBreakTimes() {
		return Collections.unmodifiableList(breakTimes);
	}
	
	public BreakTime getBreakTime(float time) {
		for (BreakTime bTime : breakTimes) {
			if (bTime.getTime() == time)
				return bTime;
		}
		return null;
	}
}
