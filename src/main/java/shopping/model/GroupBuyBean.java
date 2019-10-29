package shopping.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.google.gson.annotations.Expose;

@Entity
public class GroupBuyBean {
	@Id
	@Expose
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Expose
	@Column(nullable = false)
	private String groupName;

	@Expose
	private String groupAlias;
	
	@Expose
	private String initiatorName;
	
	@Expose
	@Column(nullable = false)
	private Integer role;

	@Expose
	@Column(nullable = false)
	private Integer status;

	@Expose
	@Column(nullable = false)
	private Date deadLine;

	@Expose
	@Column(nullable = false)
	private String shippingCity;

	@Expose
	@Column(nullable = false)
	private String shippingDistrict;

	@Expose
	@Column(nullable = false)
	private String shippingAddress;

	@Expose
	@Column(nullable = false)
	private String shippingPhone;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_InitiatorID")
	private GroupBuyBean initiator;

	@OneToMany(mappedBy = "initiator")
	private Set<GroupBuyBean> joiner = new HashSet<GroupBuyBean>();

	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "FK_GroupBuyID", referencedColumnName = "id")
	Set<OrderBean> orderBeans = new HashSet<OrderBean>();

	public GroupBuyBean(Integer id, String groupName, String groupAlias, Integer role, Integer status, Date deadLine,
			String shippingCity, String shippingDistrict, String shippingAddress, String shippingPhone,
			GroupBuyBean initiator, Set<GroupBuyBean> joiner) {
		super();
		this.id = id;
		this.groupName = groupName;
		this.groupAlias = groupAlias;
		this.role = role;
		this.status = status;
		this.deadLine = deadLine;
		this.shippingCity = shippingCity;
		this.shippingDistrict = shippingDistrict;
		this.shippingAddress = shippingAddress;
		this.shippingPhone = shippingPhone;
		this.initiator = initiator;
		this.joiner = joiner;
	}

	public GroupBuyBean() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupAlias() {
		return groupAlias;
	}

	public void setGroupAlias(String groupAlias) {
		this.groupAlias = groupAlias;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
	}

	public String getShippingCity() {
		return shippingCity;
	}

	public void setShippingCity(String shippingCity) {
		this.shippingCity = shippingCity;
	}

	public String getShippingDistrict() {
		return shippingDistrict;
	}

	public void setShippingDistrict(String shippingDistrict) {
		this.shippingDistrict = shippingDistrict;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getShippingPhone() {
		return shippingPhone;
	}

	public void setShippingPhone(String shippingPhone) {
		this.shippingPhone = shippingPhone;
	}

	public GroupBuyBean getInitiator() {
		return initiator;
	}

	public void setInitiator(GroupBuyBean initiator) {
		this.initiator = initiator;
	}

	public Set<GroupBuyBean> getJoiner() {
		return joiner;
	}

	public void setJoiner(Set<GroupBuyBean> joiner) {
		this.joiner = joiner;
	}

}
