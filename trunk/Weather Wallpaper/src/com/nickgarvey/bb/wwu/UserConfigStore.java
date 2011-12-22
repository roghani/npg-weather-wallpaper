package com.nickgarvey.bb.wwu;

public class UserConfigStore extends ObjectStore {

	String userAreaCode;
	
	private static UserConfigStore _instance = null;

	private static final long KEY = "UserConfigStore".hashCode();
	
	private UserConfigStore() {

		super(KEY);

		if (super.objToStore == null) {
			userAreaCode = null;
		} else {
			userAreaCode = (String) super.objToStore;
		}

	}
	
	public static UserConfigStore getInstance() {

		if (_instance == null) {
			_instance = new UserConfigStore();
		}

		return _instance;

	}

	public void setUserAreaCode(String areaCode) {
		this.userAreaCode = areaCode;
		super.save();
	}

	public String getUserAreaCode() {
		if (1==1) return "14623";
		return userAreaCode;
	}

	protected Object getInitialObj() {
		return null;
	}

}