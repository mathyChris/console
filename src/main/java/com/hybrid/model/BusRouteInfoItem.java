package com.hybrid.model;


// DTO(data transfer object) VO(value object), model1
public class BusRouteInfoItem {
	
	
//    <busRouteId>4662800</busRouteId>
//    <busRouteNm>6628</busRouteNm>
//    <edStationNm>여의도</edStationNm>
//    <firstBusTm>20151029042000</firstBusTm>
//    <firstLowTm>20151029233000</firstLowTm>
//    <lastBusTm>20151029233000</lastBusTm>
//    <lastBusYn>0</lastBusYn>
//    <lastLowTm>20151029233000</lastLowTm>
//    <length>27.1</length>
//    <routeType>4</routeType>
//    <stStationNm>외발산동</stStationNm>
//    <term>9</term>
	
	private String busRouteId, busRouteNm, edStationNm, firstBusTm, firstLowTm, lastBusTm ; 
	private boolean lastBusYn; 
	private String lastLowTm; 
	private float length ; 
	private int routeType; 
	private String stStationNm; 
	private String term ;
	
	
	public String getBusRouteId() {
		return busRouteId;
	}
	public void setBusRouteId(String busRouteId) {
		this.busRouteId = busRouteId;
	}
	public String getBusRouteNm() {
		return busRouteNm;
	}
	public void setBusRouteNm(String busRouteNm) {
		this.busRouteNm = busRouteNm;
	}
	public String getEdStationNm() {
		return edStationNm;
	}
	public void setEdStationNm(String edStationNm) {
		this.edStationNm = edStationNm;
	}
	public String getFirstBusTm() {
		return firstBusTm;
	}
	public void setFirstBusTm(String firstBusTm) {
		this.firstBusTm = firstBusTm;
	}
	public String getFirstLowTm() {
		return firstLowTm;
	}
	public void setFirstLowTm(String firstLowTm) {
		this.firstLowTm = firstLowTm;
	}
	public String getLastBusTm() {
		return lastBusTm;
	}
	public void setLastBusTm(String lastBusTm) {
		this.lastBusTm = lastBusTm;
	}
	public boolean isLastBusYn() {
		return lastBusYn;
	}
	public void setLastBusYn(boolean lastBusYn) {
		this.lastBusYn = lastBusYn;
	}
	public String getLastLowTm() {
		return lastLowTm;
	}
	public void setLastLowTm(String lastLowTm) {
		this.lastLowTm = lastLowTm;
	}
	public float getLength() {
		return length;
	}
	public void setLength(float length) {
		this.length = length;
	}
	public int getRouteType() {
		return routeType;
	}
	public void setRouteType(int routeType) {
		this.routeType = routeType;
	}
	public String getStStationNm() {
		return stStationNm;
	}
	public void setStStationNm(String stStationNm) {
		this.stStationNm = stStationNm;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	} 
	
	
	
	
	

}
