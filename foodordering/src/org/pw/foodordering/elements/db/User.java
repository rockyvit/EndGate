package org.pw.foodordering.elements.db;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Collection;

public class User
{
	
	Integer usr_id;
	  public Integer getUsr_id() {
		return usr_id;
	}
	public void setUsr_id(Integer usr_id) {
		this.usr_id = usr_id;
	}
	public String getAuthority_type() {
		return authority_type;
	}
	public void setAuthority_type(String authority_type) {
		this.authority_type = authority_type;
	}
	String authority_type;
	  String login_id;
	  String password;
	  String first_name;
	  String middle_name;
	  String last_name;
	  String photo;
	  String dob;
	  String gender;
	  String email_secret_key;
	  Integer email_verified;
	  String Phone;
	  String phone_secret_key;
	  Integer phone_verified;
	  String address1;
	  String address1_city;
	  String address1_state;
	  Integer address1_zip;
	  String address1_landmark;
	  Integer address1_alc_id;
	  String address2;
	  String address2_city;
	  String address2_state;
	  Integer address2_zip;
	  String address2_landmark;
	  Integer address2_alc_id;
	  Integer order_timely_delivered_count;
	  Integer order_placed_count;
	  Integer order_delayed_delivered_count;
	  Integer order_cancelled_count;
	  String referral_code;
	  Integer referral_count;
	  Integer enabled;
	  Integer false_count;
	  String created_ip;
	  Timestamp creation_date;
	  Timestamp account_expiry;
	  String modified_ip;
	  Timestamp last_accessed;
	  Integer flag;
	  String country_code;
	
  
	
	public String getCountry_code() {
		return country_code;
	}
	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}
	public String getLogin_id() {
		return login_id;
	}
	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getMiddle_name() {
		return middle_name;
	}
	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getEmail_secret_key() {
		return email_secret_key;
	}
	public void setEmail_secret_key(String email_secret_key) {
		this.email_secret_key = email_secret_key;
	}
	public Integer getEmail_verified() {
		return email_verified;
	}
	public void setEmail_verified(Integer email_verified) {
		this.email_verified = email_verified;
	}
	
	
	public Integer getPhone_verified() {
		return phone_verified;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getPhone_secret_key() {
		return phone_secret_key;
	}
	public void setPhone_secret_key(String phone_secret_key) {
		this.phone_secret_key = phone_secret_key;
	}
	public void setPhone_verified(Integer phone_verified) {
		this.phone_verified = phone_verified;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress1_city() {
		return address1_city;
	}
	public void setAddress1_city(String address1_city) {
		this.address1_city = address1_city;
	}
	public String getAddress1_state() {
		return address1_state;
	}
	public void setAddress1_state(String address1_state) {
		this.address1_state = address1_state;
	}
	public Integer getAddress1_zip() {
		return address1_zip;
	}
	public void setAddress1_zip(Integer address1_zip) {
		this.address1_zip = address1_zip;
	}
	public String getAddress1_landmark() {
		return address1_landmark;
	}
	public void setAddress1_landmark(String address1_landmark) {
		this.address1_landmark = address1_landmark;
	}
	public Integer getAddress1_alc_id() {
		return address1_alc_id;
	}
	public void setAddress1_alc_id(Integer address1_alc_id) {
		this.address1_alc_id = address1_alc_id;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getAddress2_city() {
		return address2_city;
	}
	public void setAddress2_city(String address2_city) {
		this.address2_city = address2_city;
	}
	public String getAddress2_state() {
		return address2_state;
	}
	public void setAddress2_state(String address2_state) {
		this.address2_state = address2_state;
	}
	public Integer getAddress2_zip() {
		return address2_zip;
	}
	public void setAddress2_zip(Integer address2_zip) {
		this.address2_zip = address2_zip;
	}
	public String getAddress2_landmark() {
		return address2_landmark;
	}
	public void setAddress2_landmark(String address2_landmark) {
		this.address2_landmark = address2_landmark;
	}
	public Integer getAddress2_alc_id() {
		return address2_alc_id;
	}
	public void setAddress2_alc_id(Integer address2_alc_id) {
		this.address2_alc_id = address2_alc_id;
	}
	public Integer getOrder_timely_delivered_count() {
		return order_timely_delivered_count;
	}
	public void setOrder_timely_delivered_count(Integer order_timely_delivered_count) {
		this.order_timely_delivered_count = order_timely_delivered_count;
	}
	public Integer getOrder_placed_count() {
		return order_placed_count;
	}
	public void setOrder_placed_count(Integer order_placed_count) {
		this.order_placed_count = order_placed_count;
	}
	public Integer getOrder_delayed_delivered_count() {
		return order_delayed_delivered_count;
	}
	public void setOrder_delayed_delivered_count(Integer order_delayed_delivered_count) {
		this.order_delayed_delivered_count = order_delayed_delivered_count;
	}
	public Integer getOrder_cancelled_count() {
		return order_cancelled_count;
	}
	public void setOrder_cancelled_count(Integer order_cancelled_count) {
		this.order_cancelled_count = order_cancelled_count;
	}
	public String getReferral_code() {
		return referral_code;
	}
	public void setReferral_code(String referral_code) {
		this.referral_code = referral_code;
	}
	public Integer getReferral_count() {
		return referral_count;
	}
	public void setReferral_count(Integer referral_count) {
		this.referral_count = referral_count;
	}
	
	public Integer getEnabled() {
		return enabled;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	public Integer getFalse_count() {
		return false_count;
	}
	public void setFalse_count(Integer false_count) {
		this.false_count = false_count;
	}
	public String getCreated_ip() {
		return created_ip;
	}
	public void setCreated_ip(String created_ip) {
		this.created_ip = created_ip;
	}
	public Timestamp getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Timestamp creation_date) {
		this.creation_date = creation_date;
	}
	public Timestamp getAccount_expiry() {
		return account_expiry;
	}
	public void setAccount_expiry(Timestamp account_expiry) {
		this.account_expiry = account_expiry;
	}
	public String getModified_ip() {
		return modified_ip;
	}
	public void setModified_ip(String modified_ip) {
		this.modified_ip = modified_ip;
	}
	public Timestamp getLast_accessed() {
		return last_accessed;
	}
	public void setLast_accessed(Timestamp last_accessed) {
		this.last_accessed = last_accessed;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	 
	
}
