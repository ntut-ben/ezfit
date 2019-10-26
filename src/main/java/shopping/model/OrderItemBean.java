package shopping.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.google.gson.annotations.Expose;

@Entity
public class OrderItemBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Expose
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "Product_FK")
	private Product product;
//	@JoinColumn(name = "Order_FK")
//	@ManyToOne(cascade = CascadeType.PERSIST)
//	private OrderBean orderBean;
	@Expose
	private Integer qty;
	@Expose
	private Integer subTotal;

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

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Integer getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(Integer subTotal) {
		this.subTotal = subTotal;
	}

}
