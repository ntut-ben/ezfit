package shopping.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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
public class CartItem {
	@Expose
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Expose
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "Product_FK")
	private Product product;
	@JoinColumn(name = "Member_FK")
	@ManyToOne(cascade = CascadeType.PERSIST)
	private MemberBean memberBean;
	@Expose
	private Integer qty;
	@Expose
	private Integer subTotal;
	@Expose
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_CartItemID", referencedColumnName = "id")
	List<PlaneItem> planeItems = new ArrayList<PlaneItem>();
	@Expose
	private Date shipDate;

	@JoinColumn(name = "FK_GroupBuyBeanID")
	@ManyToOne(cascade = CascadeType.PERSIST)
	private GroupBuyBean groupBuyBean;

	public CartItem(Integer id, Product product, MemberBean memberBean, Integer qty, Integer subTotal) {
		super();
		this.id = id;
		this.product = product;
		this.memberBean = memberBean;
		this.qty = qty;
		this.subTotal = subTotal;
	}

	public Date getShipDate() {
		return shipDate;
	}

	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}

	public CartItem() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public MemberBean getMemberBean() {
		return memberBean;
	}

	public void setMemberBean(MemberBean memberBean) {
		this.memberBean = memberBean;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Integer getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(Integer subTotal) {
		this.subTotal = subTotal;
	}

	public List<PlaneItem> getPlaneItems() {
		return planeItems;
	}

	public void setPlaneItems(List<PlaneItem> planeItems) {
		this.planeItems = planeItems;
	}

}
