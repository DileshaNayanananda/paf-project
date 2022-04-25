package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.User;

@Path("/User")
public class UserAPI {
	User UserObj = new User();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readUser() {
		return UserObj.readUser();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertUser(
	@FormParam("user_name") String user_name,			
	 @FormParam("email") String email,
	 @FormParam("address") String address,
	 @FormParam("phone_num") String phone_num,
	 @FormParam("password") String password)
	{
	 String output = UserObj.insertUser(user_name, email, address, phone_num, password);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUser(String userData)
	{
	//Convert the input string to a JSON object
	 JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject();
	//Read the values from the JSON object
	 String user_id = userObject.get("user_id").getAsString();
	 String user_name = userObject.get("user_name").getAsString();
	 String email = userObject.get("email").getAsString();
	 String address = userObject.get("address").getAsString();
	 String phone_num = userObject.get("phone_num").getAsString();
	 String password = userObject.get("password").getAsString();
	 String output = UserObj.updateUser(user_id, user_name, email, address, phone_num, password);
	return output;
	} 
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteUser(String userData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(userData, "", Parser.xmlParser());

	//Read the value from the element 
	 String user_id = doc.select("user_id").text();
	 String output = UserObj.deleteUser(user_id);
	return output;
	}
	
}
