package shopping.model;

import java.sql.Blob;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import com.google.gson.annotations.Expose;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class CuisineProduct extends Product {

	@Expose
	@Column(nullable = false)
	private String meal;
	@Expose
	@Column(nullable = false)
	private Integer calories;
	@Expose
	@Column(nullable = false)
	private Integer carbohydrate;
	@Expose
	@Column(nullable = false)
	private Integer protein;
	@Expose
	@Column(nullable = false)
	private Integer fat;
	@Expose
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "CuisineDetail", joinColumns = @JoinColumn(name = "cuisine_id", nullable = false, updatable = false), inverseJoinColumns = @JoinColumn(name = "ingredient_id", nullable = false, updatable = false))
	private Set<IngredientProduct> ingredientProducts = new HashSet<IngredientProduct>();

	public CuisineProduct(Integer id, String name, Integer price, Blob coverImage, String fileName,
			ProductCategory productCategory, Integer stock, String introduction, List<CartItem> cartItems, String meal,
			Integer calories, Integer carbohydrate, Integer protein, Integer fat,
			Set<IngredientProduct> ingredientProducts) {
		super(id, name, price, coverImage, fileName, productCategory, stock, introduction, cartItems);
		this.meal = meal;
		this.calories = calories;
		this.carbohydrate = carbohydrate;
		this.protein = protein;
		this.fat = fat;
		this.ingredientProducts = ingredientProducts;
	}

	public CuisineProduct() {
		super();
	}

	public String getMeal() {
		return meal;
	}

	public void setMeal(String meal) {
		this.meal = meal;
	}

	public Integer getCalories() {
		return calories;
	}

	public void setCalories(Integer calories) {
		this.calories = calories;
	}

	public Integer getCarbohydrate() {
		return carbohydrate;
	}

	public void setCarbohydrate(Integer carbohydrate) {
		this.carbohydrate = carbohydrate;
	}

	public Integer getProtein() {
		return protein;
	}

	public void setProtein(Integer protein) {
		this.protein = protein;
	}

	public Integer getFat() {
		return fat;
	}

	public void setFat(Integer fat) {
		this.fat = fat;
	}

	public Set<IngredientProduct> getIngredientProducts() {
		return ingredientProducts;
	}

	public void setIngredientProducts(Set<IngredientProduct> ingredientProducts) {
		this.ingredientProducts = ingredientProducts;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CuisineProduct [meal=");
		builder.append(meal);
		builder.append(", calories=");
		builder.append(calories);
		builder.append(", carbohydrate=");
		builder.append(carbohydrate);
		builder.append(", protein=");
		builder.append(protein);
		builder.append(", fat=");
		builder.append(fat);
		builder.append(", ingredientProducts=");
		builder.append(ingredientProducts);
		builder.append("]");
		return builder.toString();
	}

}
