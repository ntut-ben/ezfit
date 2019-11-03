package shopping.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.google.gson.annotations.Expose;

import createAccount.model.MemberBean;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class OrderBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private Integer id;

	@Expose
	@Column(nullable = false)
	Timestamp createTime;

	@Expose
	@Column(nullable = false)
	Integer totalAmount;

	@Expose
	@Column(nullable = false)
	String subscriberName;

	@Expose
	@Column(nullable = false)
	String shippingName;

	@Expose
	@Column(nullable = false)
	String subscriberCity;

	@Expose
	@Column(nullable = false)
	String subscriberDistrict;

	@Expose
	@Column(nullable = false)
	String subscriberAddress;

	@Expose
	@Column(nullable = false)
	String subscriberZipCode;

	@Expose
	@Column(nullable = false)
	String subscriberEmail;

	@Expose
	@Column(nullable = false)
	String subscriberPhone;

	@Expose
	@Column(nullable = false)
	String shippingCity;

	@Expose
	@Column(nullable = false)
	String shippingDistrict;

	@Expose
	@Column(nullable = false)
	String shippingAddress;

	@Expose
	@Column(nullable = false)
	String shippingPhone;

	@Expose
	@Column(nullable = false)
	String shippingZipCode;

	@Expose
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "FK_OrderID", referencedColumnName = "id")
	List<OrderItemBean> orderItemBeans = new ArrayList<OrderItemBean>();

	@Expose
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "FK_MemberID")
	MemberBean memberBean = new MemberBean();

	@JoinColumn(name = "FK_GroupBuyBeanID")
	@ManyToOne(cascade = CascadeType.PERSIST)
	private GroupBuyBean groupBuyBean = new GroupBuyBean();

	public OrderBean(Integer id, Timestamp createTime, Integer totalAmount, String subscriberName, String shippingName,
			String subscriberCity, String subscriberDistrict, String subscriberAddress, String subscriberEmail,
			String subscriberPhone, String shippingCity, String shippingDistrict, String shippingAddress,
			String shippingPhone) {
		super();
		this.id = id;
		this.createTime = createTime;
		this.totalAmount = totalAmount;
		this.subscriberName = subscriberName;
		this.shippingName = shippingName;
		this.subscriberCity = subscriberCity;
		this.subscriberDistrict = subscriberDistrict;
		this.subscriberAddress = subscriberAddress;
		this.subscriberEmail = subscriberEmail;
		this.subscriberPhone = subscriberPhone;
		this.shippingCity = shippingCity;
		this.shippingDistrict = shippingDistrict;
		this.shippingAddress = shippingAddress;
		this.shippingPhone = shippingPhone;
	}

	public OrderBean() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getSubscriberName() {
		return subscriberName;
	}

	public void setSubscriberName(String subscriberName) {
		this.subscriberName = subscriberName;
	}

	public String getShippingName() {
		return shippingName;
	}

	public void setShippingName(String shippingName) {
		this.shippingName = shippingName;
	}

	public String getSubscriberCity() {
		return subscriberCity;
	}

	public void setSubscriberCity(String subscriberCity) {
		this.subscriberCity = subscriberCity;
	}

	public String getSubscriberDistrict() {
		return subscriberDistrict;
	}

	public void setSubscriberDistrict(String subscriberDistrict) {
		this.subscriberDistrict = subscriberDistrict;
	}

	public String getSubscriberAddress() {
		return subscriberAddress;
	}

	public void setSubscriberAddress(String subscriberAddress) {
		this.subscriberAddress = subscriberAddress;
	}

	public String getSubscriberEmail() {
		return subscriberEmail;
	}

	public void setSubscriberEmail(String subscriberEmail) {
		this.subscriberEmail = subscriberEmail;
	}

	public String getSubscriberPhone() {
		return subscriberPhone;
	}

	public void setSubscriberPhone(String subscriberPhone) {
		this.subscriberPhone = subscriberPhone;
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

	public List<OrderItemBean> getOrderItemBeans() {
		return orderItemBeans;
	}

	public void setOrderItemBeans(List<OrderItemBean> orderItemBeans) {
		this.orderItemBeans = orderItemBeans;
	}

	public String getSubscriberZipCode() {
		return subscriberZipCode;
	}

	public void setSubscriberZipCode(String subscriberZipCode) {
		this.subscriberZipCode = subscriberZipCode;
	}

	public String getShippingZipCode() {
		return shippingZipCode;
	}

	public void setShippingZipCode(String shippingZipCode) {
		this.shippingZipCode = shippingZipCode;
	}

	public MemberBean getMemberBean() {
		return memberBean;
	}

	public void setMemberBean(MemberBean memberBean) {
		this.memberBean = memberBean;
	}

	public GroupBuyBean getGroupBuyBean() {
		return groupBuyBean;
	}

	public void setGroupBuyBean(GroupBuyBean groupBuyBean) {
		this.groupBuyBean = groupBuyBean;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderBean [id=");
		builder.append(id);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", totalAmount=");
		builder.append(totalAmount);
		builder.append(", subscriberName=");
		builder.append(subscriberName);
		builder.append(", shippingName=");
		builder.append(shippingName);
		builder.append(", subscriberCity=");
		builder.append(subscriberCity);
		builder.append(", subscriberDistrict=");
		builder.append(subscriberDistrict);
		builder.append(", subscriberAddress=");
		builder.append(subscriberAddress);
		builder.append(", subscriberEmail=");
		builder.append(subscriberEmail);
		builder.append(", subscriberPhone=");
		builder.append(subscriberPhone);
		builder.append(", shippingCity=");
		builder.append(shippingCity);
		builder.append(", shippingDistrict=");
		builder.append(shippingDistrict);
		builder.append(", shippingAddress=");
		builder.append(shippingAddress);
		builder.append(", shippingPhone=");
		builder.append(shippingPhone);
		builder.append("]");
		return builder.toString();
	}

}
