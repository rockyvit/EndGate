package org.pw.foodordering.elements.db;


import java.sql.Timestamp;

public class Caterer_Menu_Map {
	Integer cmm_id;
	Integer caterer_code;
	Integer item_code;
	String item_description;
	String foodtype_name;
	String photo;
	Integer calories;
	Integer fat;
	Integer fiber;
	Integer protien;
	Integer carbohydrates;
	Integer active_sataus;
	double list_price;
	Timestamp created_date;
	Integer created_by;
	Timestamp modified_date;
	Integer modified_by;
	
	
	public Integer getCmm_id() {
		return cmm_id;
	}
	public void setCmm_id(Integer cmm_id) {
		this.cmm_id = cmm_id;
	}
	public Integer getCaterer_code() {
		return caterer_code;
	}
	public void setCaterer_code(Integer caterer_code) {
		this.caterer_code = caterer_code;
	}
	public Integer getItem_code() {
		return item_code;
	}
	public void setItem_code(Integer item_code) {
		this.item_code = item_code;
	}
	public String getItem_description() {
		return item_description;
	}
	public void setItem_description(String item_description) {
		this.item_description = item_description;
	}
	public String getFoodtype_name() {
		return foodtype_name;
	}
	public void setFoodtype_name(String foodtype_name) {
		this.foodtype_name = foodtype_name;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Integer getCalories() {
		return calories;
	}
	public void setCalories(Integer calories) {
		this.calories = calories;
	}
	public Integer getFat() {
		return fat;
	}
	public void setFat(Integer fat) {
		this.fat = fat;
	}
	public Integer getFiber() {
		return fiber;
	}
	public void setFiber(Integer fiber) {
		this.fiber = fiber;
	}
	public Integer getProtien() {
		return protien;
	}
	public void setProtien(Integer protien) {
		this.protien = protien;
	}
	public Integer getCarbohydrates() {
		return carbohydrates;
	}
	public void setCarbohydrates(Integer carbohydrates) {
		this.carbohydrates = carbohydrates;
	}
	public Integer getActive_sataus() {
		return active_sataus;
	}
	public void setActive_sataus(Integer active_sataus) {
		this.active_sataus = active_sataus;
	}
	public double getList_price() {
		return list_price;
	}
	public void setList_price(double list_price) {
		this.list_price = list_price;
	}
	public Timestamp getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Timestamp created_date) {
		this.created_date = created_date;
	}
	public Integer getCreated_by() {
		return created_by;
	}
	public void setCreated_by(Integer created_by) {
		this.created_by = created_by;
	}
	public Timestamp getModified_date() {
		return modified_date;
	}
	public void setModified_date(Timestamp modified_date) {
		this.modified_date = modified_date;
	}
	public Integer getModified_by() {
		return modified_by;
	}
	public void setModified_by(Integer modified_by) {
		this.modified_by = modified_by;
	}

}
