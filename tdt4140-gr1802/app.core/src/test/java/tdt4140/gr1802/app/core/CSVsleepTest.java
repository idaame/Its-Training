package tdt4140.gr1802.app.core;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

import org.junit.Test;


public class CSVsleepTest {
	
	@Test
	public void getSleepDataTest() {
		// Load data from file
		URL url = this.getClass().getResource("sleepdata-csvtest.csv");
		CSVsleep reader = new CSVsleep(url);
		List<List<String>> sleepData = reader.getSleepData();
		
		// Make list with correct answer
		List<String> correct = new ArrayList<>();
		correct.add("2016-11-30");
		correct.add("63");
		correct.add("6:09");
		List<List<String>> correctList = new ArrayList<>();
		correctList.add(correct);
		
		// Check if getSleepData-method returns correct
		assertEquals(sleepData, correctList);
	}
}

