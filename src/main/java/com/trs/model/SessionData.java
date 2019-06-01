package com.trs.model;

public class SessionData {
	
	
	public String SessionUserName;
	public String SessionID;
	public long SessionCreationTime;
	
	
	public String getSessionUserName() {
		return SessionUserName;
	}
	public void setSessionUserName(String sessionUserName) {
		SessionUserName = sessionUserName;
	}
	public String getSessionID() {
		return SessionID;
	}
	public void setSessionID(String sessionID) {
		SessionID = sessionID;
	}
	public long getSessionCreationTime() {
		return SessionCreationTime;
	}
	public void setSessionCreationTime(long sessionCreationTime) {
		SessionCreationTime = sessionCreationTime;
	} 

}
