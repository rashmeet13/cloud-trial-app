package com.impetus.CloudTrialBusiness.processors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.impetus.CloudTrialBusiness.helper.ConversionBroker;
import com.impetus.CloudTrialBusiness.model.UserEntity;
import com.impetus.CloudTrialDao.UserRegistrationDao;
import com.impetus.CloudTrialDaoImpl.UserRegistrationDaoImpl;
import com.impetus.model.UserBean;

public class UserRegistration {

	private static final Logger logger = LoggerFactory
			.getLogger(UserRegistration.class);

	public String processUserRegistration(UserEntity user) {
		ConversionBroker conversion = new ConversionBroker();
		UserRegistrationDao userDao = new UserRegistrationDaoImpl();
		UserBean userBean = conversion.convertToDBModel(user);
		int Status = userDao.registerUser(userBean);
		if (Status >= 1){
			logger.debug("Successful Registration" + Status);
			return "Success";
		}			
		else{
			logger.debug("Registration" + Status);
			return "Failure";
		}			
	}
}
