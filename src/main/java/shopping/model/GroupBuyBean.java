package shopping.model;

import java.sql.Date;
import java.sql.Timestamp;
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

import createAccount.model.MemberBean;

@Entity
public class GroupBuyBean {
	@Id
	@Expose
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Expose
	Timestamp createTime;
	@Expose
	private String groupName;

	@Expose
	private String groupAlias;

	@Expose
	private String initiatorName;

	@Expose
	@Column(nullable = false)
	private Integer role;

	@Expose
	private Integer status;

	@Expose
	private Date deadLine;

	@Expose
	private String shippingCity;
	@Expose
	private String shippingZipCode;

	@Expose
	private String shippingDistrict;

	@Expose
	private String shippingAddress;

	@Expose
	private String shippingPhone;
	
	@Expose
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_InitiatorID")
	private GroupBuyBean initiator;

	
	@OneToMany(mappedBy = "initiator")
	private Set<GroupBuyBean> joiner = new HashSet<GroupBuyBean>();

	@Expose
	@OneToMany(mappedBy = "groupBuyBean")
	Set<OrderBean> orderBeans = new HashSet<OrderBean>();

	@OneToMany(mappedBy = "groupBuyBean")
	Set<CartItem> cartItems = new HashSet<CartItem>();


	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "FK_MemberID", nullable = false)
	MemberBean memberBean = new MemberBean();

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

	public String getInitiatorName() {
		return initiatorName;
	}

	public void setInitiatorName(String initiatorName) {
		this.initiatorName = initiatorName;
	}

	public String getShippingZipCode() {
		return shippingZipCode;
	}

	public void setShippingZipCode(String shippingZipCode) {
		this.shippingZipCode = shippingZipCode;
	}

	public Set<OrderBean> getOrderBeans() {
		return orderBeans;
	}

	public void setOrderBeans(Set<OrderBean> orderBeans) {
		this.orderBeans = orderBeans;
	}

	public MemberBean getMemberBean() {
		return memberBean;
	}

	public void setMemberBean(MemberBean memberBean) {
		this.memberBean = memberBean;
	}

	public Set<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Set<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}
