package shopping.model;

import java.sql.Blob;
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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.google.gson.annotations.Expose;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private Integer id;
	@Column(nullable = false)
	@Expose
	private String name;
	@Column(nullable = false)
	@Expose
	private Integer price;
	protected Blob coverImage;
	@Expose
	private String fileName;
	@Expose
	@ManyToOne
	@JoinColumn(name = "FK_ProductCategory")
	protected ProductCategory productCategory;
	@Expose
	private Integer stock;
	@Expose
	@Lob
	private String introduction;
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<CartItem> cartItems = new ArrayList<CartItem>();

	public Product(Integer id, String name, Integer price, Blob coverImage, String fileName,
			ProductCategory productCategory, Integer stock, String introduction, List<CartItem> cartItems) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.coverImage = coverImage;
		this.fileName = fileName;
		this.productCategory = productCategory;
		this.stock = stock;
		this.introduction = introduction;
		this.cartItems = cartItems;
	}

	public Product() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Blob getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(Blob coverImage) {
		this.coverImage = coverImage;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Product [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", price=");
		builder.append(price);
		builder.append(", coverImage=");
		builder.append(coverImage);
		builder.append(", fileName=");
		builder.append(fileName);
		builder.append(", productCategory=");
		builder.append(productCategory);
		builder.append(", stock=");
		builder.append(stock);
		builder.append(", introduction=");
		builder.append(introduction);
		builder.append("]");
		return builder.toString();
	}

}
