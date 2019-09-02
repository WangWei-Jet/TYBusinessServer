package com.whty.example.webservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("webservices")
public class WebService_Rest_WS_Impl implements WebService_Rest_WS {

	private Logger logger = LoggerFactory.getLogger(WebService_Rest_WS_Impl.class);

	@Override
	public String showUserInfo() {
		return null;
	}

	@Override
	public String showUserInfoById(String userId) {
		logger.debug("showUserInfoById");
		return "查询成功";
	}

	@Override
	public String uploadUnsupportPhone(String model, String factory, String OSVersion, Integer commType,
			String deviceType) {
		return null;
	}

}
