package com.whty.example.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class BaseController {

	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

	public void writeJSONResult(Object result, HttpServletResponse response) {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("appliaction/json");
			Gson gson = new Gson();
			// response.getWriter().write(gson.toJson(result));
			String resMsg = gson.toJson(result);
			logger.info("response:" + resMsg);
			response.getWriter().write(resMsg);
		} catch (IOException e) {
			logger.error("response回写失败", e);
		}
	}

	/**
	 * 回写json类型数据响应
	 * 
	 * @param result
	 * @param response
	 * @param datePattern
	 */
	public void writeJSONResult(Object result, HttpServletResponse response, String datePattern) {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			Gson gson = new GsonBuilder().setDateFormat(datePattern).create();
			String resMsg = gson.toJson(result);
			logger.info("response:" + resMsg);
			response.getWriter().write(resMsg);
			logger.info("服务器发送响应成功");
		} catch (IOException e) {
			logger.error("response回写失败", e);
		}
	}

	/**
	 * 回写普通字符串类型响应
	 * 
	 * @param result
	 * @param response
	 */
	public void writeTextResult(String result, HttpServletResponse response) {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			response.getWriter().write(result);
			logger.info("response:" + result);
		} catch (IOException e) {
			logger.error("response回写失败", e);
		}
	}

	public void writeJSONArrayResult(@SuppressWarnings("rawtypes") List result, HttpServletResponse response) {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("appliaction/json");
			Gson gson = new Gson();
			response.getWriter().write(gson.toJson(result));
			logger.info("response:{}", gson.toJson(result));
		} catch (IOException e) {
			logger.error("response回写失败", e);
		}
	}

	public void writeJSONArrayResult(String result, HttpServletResponse response) {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("appliaction/json");
			Gson gson = new Gson();
			response.getWriter().write(gson.toJson(result));
		} catch (IOException e) {
			logger.error("response回写失败", e);
		}
	}
}
