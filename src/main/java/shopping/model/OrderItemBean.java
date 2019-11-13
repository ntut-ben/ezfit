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

@Entity
public class OrderItemBean {
	@Expose
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Expose
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "Product_FK")
	private Product product;

	@Expose
	private Integer qty;
	@Expose
	private Integer subTotal;

	@Expose
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_OrderItemBeanID", referencedColumnName = "id")
	List<OrderPlaneItem> planeItems = new ArrayList<OrderPlaneItem>();

	@Expose
	private Date shipDate;

	public OrderItemBean(Integer id, Product product, Integer qty, Integer subTotal) {
		super();
		this.id = id;
		this.product = product;
//		this.orderBean = orderBean;
		this.qty = qty;
		this.subTotal = subTotal;
	}

	public OrderItemBean() {
		super();
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

	public Integer getQty() {
		return qty;
	}

	public List<OrderPlaneItem> getPlaneItems() {
		return planeItems;
	}

	public void setPlaneItems(List<OrderPlaneItem> planeItems) {
		this.planeItems = planeItems;
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

	public Date getShipDate() {
		return shipDate;
	}

	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}

}
