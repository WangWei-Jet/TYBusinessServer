package com.whty.example.webservice;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("")
public interface WebService_Rest_WS {

	@GET
	@Produces({ MediaType.APPLICATION_XML })
	@Path("/showUserInfo")
	public String showUserInfo();

	@GET
	@Produces({ MediaType.TEXT_PLAIN, "text/html;charset=UTF-8" })
	@Path("/showUserInfoById")
	public String showUserInfoById(@FormParam("userId") String userId);

	@GET
	@Produces({ MediaType.TEXT_HTML, "text/html;charset=UTF-8" })
	@Path("/uploadUnsupportPhone")
	public String uploadUnsupportPhone(@FormParam("model") String model, @FormParam("factory") String factory,
			@FormParam("OSVersion") String OSVersion, @FormParam("commType") Integer commType,
			@FormParam("deviceType") String deviceType);

}
