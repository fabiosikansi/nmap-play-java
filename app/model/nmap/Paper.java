package model.nmap;

//import View.Frame;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Paper {
	
	private String csvFile;
	private int visualSpaceWidth;
	private int visualSpaceHeight;
	private List<Element> data;
	
	public Paper(String csvFile,int visualSpaceWidth, int visualSpaceHeight) {
		this.csvFile = csvFile;
		this.visualSpaceHeight = visualSpaceHeight;
		this.visualSpaceWidth = visualSpaceWidth;
		this.data = loadCSV(this.csvFile);
	}

    //parse CSV file
    public static List<Element> loadCSV(String csvFile) {
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        List<Element> data = new ArrayList<Element>();
        Random random = new Random();

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                String[] cels = line.split(cvsSplitBy);
                Element d = null;
                
                if (cels.length == 4) {
                    d = new Element(
                        Float.parseFloat(cels[1]),
                        Float.parseFloat(cels[2]),
                        Integer.parseInt(cels[0]),
                        Float.parseFloat(cels[3]),
                        random.nextFloat() //random Weight for each element
                    );
                } else if (cels.length == 5) {
                    d = new Element(
                        Float.parseFloat(cels[1]),
                        Float.parseFloat(cels[2]),
                        Integer.parseInt(cels[0]),
                        Float.parseFloat(cels[3]),
                        Float.parseFloat(cels[4])
                    );
                } else {
                    System.err.println("Problems while parsing csv file");
                    System.exit(-1);
                }
                
                data.add(d);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Problems while parsing csv file");
            System.err.println(e.getMessage());
            System.exit(-1);
        } catch (IOException e) {
            System.err.println("Problems while parsing csv file");
            System.err.println(e.getMessage());
            System.exit(-1);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }
        }

        return data;
    }

    public List<BoundingBox> execute() {

//        String csvFile = "files/caso" + caseId + ".csv";
//        List<Element> data = loadCSV(this.csvFile);
//        int visualSpaceWidth  = 800;
//        int visualSpaceHeight = 600;
//
        NMap nmap = new NMap(this.visualSpaceWidth, this.visualSpaceHeight);
//        
//        //NMap Alternate Cut Aproach
        List<BoundingBox> ac = nmap.alternateCut(this.data);
            
//        //NMap Equal Weights Aproach
//        List<BoundingBox> ew = nmap.equalWeight(data);

        return ac;
    }
    
    public List<Element> getElements() {
    	return this.data;
    }
}
