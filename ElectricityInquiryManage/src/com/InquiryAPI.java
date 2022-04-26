package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.gson.*;

import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.Inquiry;

@Path("/Inquiry")
public class InquiryAPI {
	Inquiry InquiryObj = new Inquiry();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readInquiry() {
		return InquiryObj.readInquiry();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertInquiry(
	 @FormParam("inquiry_name") String inquiry_name,			
	 @FormParam("inquiry_acc") String inquiry_acc,
	 @FormParam("inquiry_reason") String inquiry_reason,
	 @FormParam("inquiry_date") String inquiry_date,
	 @FormParam("inquiry_pNo") String inquiry_pNo)
	{
	 String output = InquiryObj.insertInquiry(inquiry_name, inquiry_acc, inquiry_reason, inquiry_date, inquiry_pNo);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateInquiry(String inquiryData)
	{
	//Convert the input string to a JSON object
	 JsonObject inquiryObject = new JsonParser().parse(inquiryData).getAsJsonObject();
	//Read the values from the JSON object
	 String inquiryId = inquiryObject.get("inquiryId").getAsString();
	 String inquiry_name = inquiryObject.get("inquiry_name").getAsString();
	 String inquiry_acc = inquiryObject.get("inquiry_acc").getAsString();
	 String inquiry_reason = inquiryObject.get("inquiry_reason").getAsString();
	 String inquiry_date = inquiryObject.get("inquiry_date").getAsString();
	 String inquiry_pNo = inquiryObject.get("inquiry_pNo").getAsString();
	 String output = InquiryObj.updateInquiry(inquiryId, inquiry_name, inquiry_acc, inquiry_reason, inquiry_date, inquiry_pNo);
	return output;
	} 
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteInquiry(String inquiryData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(inquiryData, "", Parser.xmlParser());

	 
	//Read the value from the element <ID>
	 String inquiryId = doc.select("inquiryId").text();
	 String output = InquiryObj.deleteInquiry(inquiryId);
	return output;
	}
}
