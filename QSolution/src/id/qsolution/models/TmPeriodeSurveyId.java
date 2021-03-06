package id.qsolution.models;

// Generated Jan 29, 2014 12:26:12 AM by Hibernate Tools 3.4.0.CR1

/**
 * TmPeriodeSurveyId generated by hbm2java
 */
@SuppressWarnings("serial")

public class TmPeriodeSurveyId implements java.io.Serializable {

	private String tahun;
	private String bulan;
	private String week;

	public TmPeriodeSurveyId() {
	}

	public TmPeriodeSurveyId(String tahun, String bulan, String week) {
		this.tahun = tahun;
		this.bulan = bulan;
		this.week = week;
	}

	public String getTahun() {
		return this.tahun;
	}

	public void setTahun(String tahun) {
		this.tahun = tahun;
	}

	public String getBulan() {
		return this.bulan;
	}

	public void setBulan(String bulan) {
		this.bulan = bulan;
	}

	public String getWeek() {
		return this.week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TmPeriodeSurveyId))
			return false;
		TmPeriodeSurveyId castOther = (TmPeriodeSurveyId) other;

		return ((this.getTahun() == castOther.getTahun()) || (this.getTahun() != null
				&& castOther.getTahun() != null && this.getTahun().equals(
				castOther.getTahun())))
				&& ((this.getBulan() == castOther.getBulan()) || (this
						.getBulan() != null && castOther.getBulan() != null && this
						.getBulan().equals(castOther.getBulan())))
				&& ((this.getWeek() == castOther.getWeek()) || (this.getWeek() != null
						&& castOther.getWeek() != null && this.getWeek()
						.equals(castOther.getWeek())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getTahun() == null ? 0 : this.getTahun().hashCode());
		result = 37 * result
				+ (getBulan() == null ? 0 : this.getBulan().hashCode());
		result = 37 * result
				+ (getWeek() == null ? 0 : this.getWeek().hashCode());
		return result;
	}

}
