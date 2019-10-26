package shopping.model;

import java.sql.Blob;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import com.google.gson.annotations.Expose;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class PlaneProduct extends Product {

	@Expose
	@Column(nullable = false)
	private Integer meals;
	@Expose
	@Column(nullable = false)
	private Integer days;
	@Expose
	@Column(nullable = false)
	private String alias;
	@Expose
	@Column(nullable = false)
	private String shorthandOfPlane;

	public PlaneProduct(Integer id, String name, Integer price, Blob coverImage, String fileName,
			ProductCategory productCategory, Integer stock, String introduction, List<CartItem> cartItems,
			Integer meals, Integer days) {
		this.meals = meals;
		this.days = days;
	}

	public PlaneProduct() {
	}

	public Integer getMeals() {
		return meals;
	}

	public void setMeals(Integer meals) {
		this.meals = meals;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getShorthandOfPlane() {
		return shorthandOfPlane;
	}

	public void setShorthandOfPlane(String shorthandOfPlane) {
		this.shorthandOfPlane = shorthandOfPlane;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PlaneProduct [meals=");
		builder.append(meals);
		builder.append(", days=");
		builder.append(days);
		builder.append("]");
		return builder.toString();
	}

}
