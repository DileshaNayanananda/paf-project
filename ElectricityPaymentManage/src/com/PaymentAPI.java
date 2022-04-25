package com;

import model.Payment;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Payment")
public class PaymentAPI {
	Payment PaymentObj = new Payment();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPayment() {
		return PaymentObj.readPayment();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPayment(
		@FormParam("date_time") String date_time,
	@FormParam("user_address") String user_address,
	@FormParam("amount") String amount,
	@FormParam("payment_method") String payment_method) {
	String output = PaymentObj.insertPayment(date_time, user_address, amount, payment_method);
	return output;
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	public String updatePayment(String paymentData) {
		
		JsonObject paymentObject = new JsonParser().parse(paymentData).getAsJsonObject();
		// Read the values from the JSON object
		String paymnt_id = paymentObject.get("paymnt_id").getAsString();
		String date_time = paymentObject.get("date_time").getAsString();
		String user_address = paymentObject.get("user_address").getAsString();
		String amount = paymentObject.get("amount").getAsString();
		String payment_method = paymentObject.get("payment_method").getAsString();	
		
		String output = PaymentObj.updatePayment(paymnt_id, date_time, user_address, amount, payment_method);
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePayment(String paymentData) {
		
		Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());

		// Read the value from the element
		String paymnt_id = doc.select("paymnt_id").text();
		String output = PaymentObj.deletePayment(paymnt_id);
		return output;
		
	}
}
