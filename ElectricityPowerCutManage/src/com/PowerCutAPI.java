package com;

import model.PowerCut;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/PowerCut")
public class PowerCutAPI {
	PowerCut PowerCutObj = new PowerCut();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPowerCut() {
		return PowerCutObj.readPowerCut();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPowerCut(
			@FormParam("date") String date,
			@FormParam("time") String time,
			@FormParam("area") String area,
			@FormParam("duration") String duration) {
		String output = PowerCutObj.insertPowerCut(date, time, area, duration);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	public String updatePowerCut(String powercutData) {
		// Convert the input string to a JSON object
		JsonObject powercutObject = new JsonParser().parse(powercutData).getAsJsonObject();

		// Read the values from the JSON object
		String CutID = powercutObject.get("id").getAsString();
		String date = powercutObject.get("date").getAsString();
		String time = powercutObject.get("time").getAsString();
		String area = powercutObject.get("area").getAsString();
		String duration = powercutObject.get("duration").getAsString();	
		String output = PowerCutObj.updatePowerCut(CutID, date, time, area, duration);
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePowerCut(String powercutData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(powercutData, "", Parser.xmlParser());

		// Read the value from the element
		String id = doc.select("id").text();
		String output = PowerCutObj.deletePowerCut(id);
		return output;
	}
}
