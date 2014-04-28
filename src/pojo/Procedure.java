package pojo;
// Generated 2014-4-22 10:37:58 by Hibernate Tools 3.4.0.CR1

/**
 * Procedure generated by hbm2java
 */
public class Procedure implements java.io.Serializable {

	private Integer id;
	private Machine machine;
	private Product product;
	private Integer stepNo;
	private String stepName;
	private Float unitTime;  //单个零件需要的时间，单位：min

	public Procedure() {
	}

	public Procedure(Machine machine, Product product) {
		this.machine = machine;
		this.product = product;
	}

	public Procedure(Machine machine, Product product, Integer stepNo,
			String stepName, Float unitTime) {
		this.machine = machine;
		this.product = product;
		this.stepNo = stepNo;
		this.stepName = stepName;
		this.unitTime = unitTime;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Machine getMachine() {
		return this.machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getStepNo() {
		return this.stepNo;
	}

	public void setStepNo(Integer stepNo) {
		this.stepNo = stepNo;
	}

	public String getStepName() {
		return this.stepName;
	}

	public void setStepName(String stepName) {
		this.stepName = stepName;
	}

	public Float getUnitTime() {
		return this.unitTime;
	}

	public void setUnitTime(Float unitTime) {
		this.unitTime = unitTime;
	}

}
