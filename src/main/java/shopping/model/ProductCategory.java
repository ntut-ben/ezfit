package shopping.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table
public class ProductCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	@Expose
	@Column(nullable = false, unique = true)
	String category;

	@OneToMany(mappedBy = "productCategory", cascade = CascadeType.ALL)
	Set<Product> products = new HashSet<Product>();

	public ProductCategory(Integer id, String category) {
		super();
		this.id = id;
		this.category = category;
	}

	public ProductCategory() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProductCategory [id=");
		builder.append(id);
		builder.append(", category=");
		builder.append(category);
		builder.append("]");
		return builder.toString();
	}

}
