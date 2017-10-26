package com.cuemby.attendance.domain.factories;

import java.util.UUID;

public class UUIDCreator {

	private static UUIDCreator instance = null;

	private UUIDCreator() {
	}

	public static UUIDCreator getInstance() {
		if (instance == null) {
			instance = new UUIDCreator();
		}
		return instance;
	}

	public static String randomUUID() {
		return UUID.randomUUID().toString();
	}

}
