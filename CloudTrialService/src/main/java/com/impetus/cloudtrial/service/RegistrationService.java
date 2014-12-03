package com.impetus.cloudtrial.service;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.impetus.CloudTrialBusiness.model.UserEntity;
import com.impetus.CloudTrialBusiness.processors.UserRegistration;
import com.impetus.cloudtrial.constants.CommonConstants.Content;
import com.impetus.cloudtrial.constants.CommonConstants.PathConstants;
import com.impetus.cloudtrial.model.IncomingRequest;
import com.impetus.cloudtrial.model.OutgoingResponse;

@Path(PathConstants.USER_REGISTRATION)
public class RegistrationService {

	@POST
	@Path(PathConstants.SAVE_USER_DETAILS)
	@Produces(Content.JSON)
	public OutgoingResponse registerUser(final IncomingRequest request) {
		UserRegistration userRegistration = new UserRegistration();
		UserEntity user = new com.impetus.CloudTrialBusiness.model.UserEntity();
		user.setEmail(request.getParameters().getHeaderData().getEmail());
		user.setRegion(request.getParameters().getHeaderData().getRegion());
		user.setSource(request.getParameters().getHeaderData().getSource());
		user.setStartDate(request.getParameters().getHeaderData().getStartDate());
		user.setType(request.getParameters().getHeaderData().getType());
		String result = userRegistration.processUserRegistration(user);
		OutgoingResponse response=new OutgoingResponse();
		response.setMessage(result);
		return response;
	}
	
}
