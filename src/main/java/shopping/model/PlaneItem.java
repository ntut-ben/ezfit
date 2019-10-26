package shopping.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PlaneItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "Product_FK")
	private CuisineProduct cuisineProduct;

	public PlaneItem() {
		super();
	}

	public PlaneItem(Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CuisineProduct getCuisineProduct() {
		return cuisineProduct;
	}

	public void setCuisineProduct(CuisineProduct cuisineProduct) {
		this.cuisineProduct = cuisineProduct;
	}

}
