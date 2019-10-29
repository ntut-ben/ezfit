package createAccount.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import shopping.model.GroupBuyBean;
import shopping.model.OrderBean;

@Entity
@Table(name = "`Member`") // Table要移掉``
public class MemberBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer pkey;
	String email;
	String password;
	String name;
	String gender;
	Double height;
	Double weight;
	Date birthday;
	String address;
	String tel;

	@OneToMany
	@JoinColumn(name = "FK_MemberID", referencedColumnName = "pkey")
	List<OrderBean> orderBeans = new ArrayList<OrderBean>();

	@OneToMany
	@JoinColumn(name = "FK_MemberID", referencedColumnName = "pkey")
	List<GroupBuyBean> groupBuyBeans = new ArrayList<GroupBuyBean>();

	Blob memberImage;
	Timestamp registerTime;

	public MemberBean(Integer pkey, String email, String password, String name, String gender, Double height,
			Double weight, Date birthday, String address, String tel, Blob memberImage, Timestamp registerTime) {
		super();
		this.pkey = pkey;
		this.email = email;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.height = height;
		this.weight = weight;
		this.birthday = birthday;
		this.address = address;
		this.tel = tel;
		this.memberImage = memberImage;
		this.registerTime = registerTime;
	}

	public MemberBean() {
		super();
	}

//	public MemberBean(Integer pkey, String email, String password, String name, Timestamp registerTime) {
//		super();
//		this.pkey = pkey;
//		this.email = email;
//		this.password = password;
//		this.name = name;
//		this.registerTime = registerTime;
//	}

	public Integer getPkey() {
		return pkey;
	}

	public void setPkey(Integer pkey) {
		this.pkey = pkey;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Blob getMemberImage() {
		return memberImage;
	}

	public void setMemberImage(Blob memberImage) {
		this.memberImage = memberImage;
	}

	public Timestamp getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Timestamp registerTime) {
		this.registerTime = registerTime;
	}
}
