package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.libs.Json;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.node.ObjectNode;

import model.nmap.BoundingBox;
import model.nmap.Box;
import model.nmap.Paper;

public class Application extends Controller {
    
    public static Result index() {
        return ok(views.html.index.render("NMap Demo"));
    } 
    
    public static Result about() {
        return ok(views.html.about.render("NMap Demo"));
    }
    
    public static Result loadNMap(int caseNumber, int visualSpaceWidth, int visualSpaceHeight) {
    	ObjectNode result = Json.newObject();
		Paper nmap = new Paper("public/files/caso0" + caseNumber + ".csv",visualSpaceWidth,visualSpaceHeight);
		List<BoundingBox> nmapBoundingBoxes = nmap.execute();
//		List<Element> nmapElements = nmap.getElements();
		List<Box> nmapBoxes = new ArrayList<Box>();
        for (BoundingBox box : nmapBoundingBoxes) {
        	nmapBoxes.add(box.toBox());
        }
		result.put("boxes", Json.toJson(nmapBoxes));
//		result.put("points", Json.toJson(nmapElements));
		
		return ok(result);
    }
    
}
