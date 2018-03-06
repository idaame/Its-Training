package tdt4140.gr1802.app.core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
	
	private String filePath;
	
	
	public CSVReader(String path) {
		this.filePath = path;
	}
	
	
	public int converToMinutes(String dur) {
		String[] duration = dur.split(":");
		int hoursToMin = Integer.parseInt(duration[0]);
		int minToMin = Integer.parseInt(duration[1]);
		int secToMin = Integer.parseInt(duration[2]);
		
		return hoursToMin*60 + minToMin + secToMin/60;
	}
	
	
	public String readFile(int i) throws IOException {
		String csvSplitBy = ",";
		BufferedReader br = null;
		String type = "";

		try {
			br = new BufferedReader(new FileReader(filePath));
			String linje = br.readLine();
			linje = br.readLine();
			String[] liste = linje.split(csvSplitBy);
			type = liste[i]; // Saving type of activity in type	
		}
		
		catch(FileNotFoundException fnf) {
				fnf.printStackTrace();
		}
		
		catch(IOException io) {
			io.printStackTrace();
		}
		
		finally {
				if (br != null) {
					try {
						br.close();
					}
					catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		return type;
	}
	
	
	public int getDuration() throws IOException {
		String dist = readFile(4);
		return converToMinutes(dist);
	}
	
	
	public String getType() throws IOException {
		return readFile(1);
	}
	
	
	public String getDate() throws IOException {
		return readFile(2) + " " + readFile(3);
	}

	
	public double getDistance() throws IOException {
		return Double.parseDouble(readFile(5));

	}
	
	
	public List<String> getPulse() {

		String csvFile = this.filePath;
        
		BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        
        try {
        	
        		List<String> pulse = new ArrayList<String>(); 
        		int i = 0;

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
		
                // use comma as separator
                String[] linje = line.split(cvsSplitBy);

                if (i > 3 && i%10 == 0) {
                	pulse.add(linje[2]);
  
 	
                }
                i++;
            }
            //returns array
            return pulse;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            
        }

    return null;

}
		
	
	public static void main(String[] args) throws IOException {
		String path = "/Users/petter/Documents/oppdatertCSV.csv";
		CSVReader reader = new CSVReader(path);
		int duration = reader.getDuration();
		String type = reader.getType();
		String date = reader.getDate();
		double distance = reader.getDistance();
		
	
		System.out.println("Type: "+type);
		System.out.println("Duration: "+duration+" minutes");
		System.out.println("Distance: "+distance+" km");
		System.out.println("Date: "+date);
	}



}