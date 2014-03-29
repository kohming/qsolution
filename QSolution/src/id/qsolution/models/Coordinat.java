package id.qsolution.models;

import java.io.Serializable;


public class Coordinat implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 149050364028178154L;
	private String xcoord;
	private String ycoord;
	private boolean locked = false;

	public String getXcoord() {
		return xcoord;
	}

	public void setXcoord(String xcoord) {
		this.xcoord = xcoord;
	}

	public String getYcoord() {
		return ycoord;
	}

	public void setYcoord(String ycoord) {
		this.ycoord = ycoord;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

}
