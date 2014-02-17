/**
 * LocationPojo.java [V 1.0.0]
 * classes:com.dxt.poc.server.pojo.LocationPojo
 * QULIPENG Create 2013-5-30 下午1:16:31
 */
package edu.buaa.automanager.bean;

import java.io.Serializable;

/**
 * 经纬度实体类 com.dxt.poc.server.pojo.LocationPojo
 * 
 * @author QULIPENG <br/>
 *         create at 2013-5-30 下午1:23:17
 */
public class LocationPojo implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1142574776175870877L;

	// 经度
	private Double longitude = null;

	// 纬度
	private Double latitude = null;

	// 时间
	private String date = null;
	
	private boolean isCheck = false;

	/**
	 * @return 经度
	 */
	public Double getLongitude()
	{
		return longitude;
	}

	/**
	 * @param longitude 经度
	 */
	public void setLongitude(Double longitude)
	{
		this.longitude = longitude;
	}

	/**
	 * @return 纬度
	 */
	public Double getLatitude()
	{
		return latitude;
	}

	/**
	 * @param latitude 纬度
	 */
	public void setLatitude(Double latitude)
	{
		this.latitude = latitude;
	}

	/**
	 * @return 时间
	 */
	public String getDate()
	{
		return date;
	}

	/**
	 * @param date 时间
	 */
	public void setDate(String date)
	{
		this.date = date;
	}

	public boolean isCheck()
	{
		return isCheck;
	}

	public void setCheck(boolean isCheck)
	{
		this.isCheck = isCheck;
	}
	

}
