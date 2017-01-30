/*
 * Copyright (c) 2017 Author(s). All rights reserved.
 * 
 * @Author	- Anand Sivadas
 * @Date	- 16 Jan, 2017
 * 
 * 
 */
package com.sivadas.anand.quickfeed.integration.domain;

import java.io.Serializable;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The Class BaseDTO.
 */
public class BaseDTO implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8801271436743568717L;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		try {
			return toJsonString(this);
		} catch (Exception e) {
			return super.toString();
		}
	}
	
	/**
	 * To json string.
	 *
	 * @param jsonObject the JSON object
	 * @return the string
	 * @throws Exception the exception
	 */
//	@TODO date is not getting printed properly in the JSON string
	protected static String toJsonString(Object jsonObject) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
//		map.registerModule(new Jdk8Module());
//		map.registerModule(new JSR310Module());
		return mapper.writeValueAsString(jsonObject);
	}
}
