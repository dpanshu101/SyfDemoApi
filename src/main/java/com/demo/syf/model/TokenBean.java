package com.demo.syf.model;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class TokenBean {
private String access_token;
private long expires_in;
private String token_type;
private String scope;
private String refresh_token;
private String account_id;
private String account_username;
private Date refreshDate;
public String getAccess_token() {
	return access_token;
}
public void setAccess_token(String access_token) {
	this.access_token = access_token;
}
public long getExpires_in() {
	return expires_in;
}
public void setExpires_in(long expires_in) {
	this.expires_in = expires_in;
}
public String getToken_type() {
	return token_type;
}
public void setToken_type(String token_type) {
	this.token_type = token_type;
}
public String getScope() {
	return scope;
}
public void setScope(String scope) {
	this.scope = scope;
}
public String getRefresh_token() {
	return refresh_token;
}
public void setRefresh_token(String refresh_token) {
	this.refresh_token = refresh_token;
}
public String getAccount_id() {
	return account_id;
}
public void setAccount_id(String account_id) {
	this.account_id = account_id;
}
public String getAccount_username() {
	return account_username;
}
public void setAccount_username(String account_username) {
	this.account_username = account_username;
}
public Date getRefreshDate() {
	return refreshDate;
}
public void setRefreshDate(Date refreshDate) {
	this.refreshDate = refreshDate;
}




}
