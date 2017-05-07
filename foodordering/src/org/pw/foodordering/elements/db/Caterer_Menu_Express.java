package org.pw.foodordering.elements.db;

import java.sql.Timestamp;

public class Caterer_Menu_Express {
	Integer cme_id;
	Integer caterer_item_code;
	Integer address1_alc_id;
	Integer ame_id;
	Integer service_location;
	Integer quantity_released;
	Integer quantity_ordered;
	Integer quantity_delivered;
	Integer quantity_cancelled;
	Integer quantity_available;
	Integer ave_id;
	double list_price;
	double min_price;
	double cost_price;
	Timestamp Valid_from;
	Timestamp Valid_until;
	int active_sataus;
	Timestamp created_date;
	Integer created_by;
	String created_ip;
	Timestamp modified_date;
	String modified_ip;
	public Integer getCme_id() {
		return cme_id;
	}
	public void setCme_id(Integer cme_id) {
		this.cme_id = cme_id;
	}
	public Integer getCaterer_item_code() {
		return caterer_item_code;
	}
	public void setCaterer_item_code(Integer caterer_item_code) {
		this.caterer_item_code = caterer_item_code;
	}
	public Integer getAddress1_alc_id() {
		return address1_alc_id;
	}
	public void setAddress1_alc_id(Integer address1_alc_id) {
		this.address1_alc_id = address1_alc_id;
	}
	public Integer getAme_id() {
		return ame_id;
	}
	public void setAme_id(Integer ame_id) {
		this.ame_id = ame_id;
	}
	public Integer getService_location() {
		return service_location;
	}
	public void setService_location(Integer service_location) {
		this.service_location = service_location;
	}
	public Integer getQuantity_released() {
		return quantity_released;
	}
	public void setQuantity_released(Integer quantity_released) {
		this.quantity_released = quantity_released;
	}
	public Integer getQuantity_ordered() {
		return quantity_ordered;
	}
	public void setQuantity_ordered(Integer quantity_ordered) {
		this.quantity_ordered = quantity_ordered;
	}
	public Integer getQuantity_delivered() {
		return quantity_delivered;
	}
	public void setQuantity_delivered(Integer quantity_delivered) {
		this.quantity_delivered = quantity_delivered;
	}
	public Integer getQuantity_cancelled() {
		return quantity_cancelled;
	}
	public void setQuantity_cancelled(Integer quantity_cancelled) {
		this.quantity_cancelled = quantity_cancelled;
	}
	public Integer getQuantity_available() {
		return quantity_available;
	}
	public void setQuantity_available(Integer quantity_available) {
		this.quantity_available = quantity_available;
	}
	public Integer getAve_id() {
		return ave_id;
	}
	public void setAve_id(Integer ave_id) {
		this.ave_id = ave_id;
	}
	public double getList_price() {
		return list_price;
	}
	public void setList_price(double list_price) {
		this.list_price = list_price;
	}
	public double getMin_price() {
		return min_price;
	}
	public void setMin_price(double min_price) {
		this.min_price = min_price;
	}
	public double getCost_price() {
		return cost_price;
	}
	public void setCost_price(double cost_price) {
		this.cost_price = cost_price;
	}
	public Timestamp getValid_from() {
		return Valid_from;
	}
	public void setValid_from(Timestamp valid_from) {
		Valid_from = valid_from;
	}
	public Timestamp getValid_until() {
		return Valid_until;
	}
	public void setValid_until(Timestamp valid_until) {
		Valid_until = valid_until;
	}
	public int getActive_sataus() {
		return active_sataus;
	}
	public void setActive_sataus(int active_sataus) {
		this.active_sataus = active_sataus;
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
	public String getCreated_ip() {
		return created_ip;
	}
	public void setCreated_ip(String created_ip) {
		this.created_ip = created_ip;
	}
	public Timestamp getModified_date() {
		return modified_date;
	}
	public void setModified_date(Timestamp modified_date) {
		this.modified_date = modified_date;
	}
	public String getModified_ip() {
		return modified_ip;
	}
	public void setModified_ip(String modified_ip) {
		this.modified_ip = modified_ip;
	}
	

}
