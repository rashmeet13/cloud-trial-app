package com.impetus.CloudTrialBusiness.helper;


import com.impetus.CloudTrialBusiness.model.UserEntity;
import com.impetus.model.UserBean;



public class ConversionBroker {
	
	public UserBean convertToDBModel(UserEntity userEntity){
		UserBean userBean = new UserBean();
		userBean.setEmail(userEntity.getEmail());
		userBean.setRegion(userEntity.getRegion());
		userBean.setSource(userEntity.getSource());
		userBean.setStartDate(userEntity.getStartDate());
		userBean.setType(userEntity.getType());
		userBean.setTerm(userEntity.getTerm());
		return userBean;
	}
}
