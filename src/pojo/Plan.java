package pojo;
// Generated 2014-3-18 15:22:04 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * Plan generated by hbm2java
 */
public class Plan implements java.io.Serializable {

	private Integer id;
	private Machine machine;
	private Product product;
	private String name;
	private Date fromTime;
	private Date toTime;
	private Integer expectation;
	
	//枚举
	//0: 初始化，未开始
	//1: 进行中
	//2: 已完成
	private Integer status;

	public Plan() {
	}

	public Plan(String name) {
		this.name = name;
	}

	public Plan(Machine machine, Product product, String name, Date fromTime,
			Date toTime, Integer expectation, Integer status) {
		this.machine = machine;
		this.product = product;
		this.name = name;
		this.fromTime = fromTime;
		this.toTime = toTime;
		this.expectation = expectation;
		this.status = status;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getFromTime() {
		return this.fromTime;
	}

	public void setFromTime(Date fromTime) {
		this.fromTime = fromTime;
	}

	public Date getToTime() {
		return this.toTime;
	}

	public void setToTime(Date toTime) {
		this.toTime = toTime;
	}

	public Integer getExpectation() {
		return this.expectation;
	}

	public void setExpectation(Integer expectation) {
		this.expectation = expectation;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
