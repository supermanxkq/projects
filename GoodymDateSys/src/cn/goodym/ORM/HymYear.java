package cn.goodym.ORM;

/**
 * HymYear entity. @author MyEclipse Persistence Tools
 */

public class HymYear implements java.io.Serializable {

	// Fields

	private Integer id;
	private String year;

	// Constructors

	/** default constructor */
	public HymYear() {
	}

	/** full constructor */
	public HymYear(String year) {
		this.year = year;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

}