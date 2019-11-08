package Recipe.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

import shopping.model.IngredientProduct;

@Entity
@Table(name = "Materal")
public class MateralBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private Integer materal_Id;
	@Expose
	private String materalName;
	@Expose
	private String unit;
//	@Expose
//	@ManyToOne(cascade = CascadeType.PERSIST)
	@ManyToOne
	@JoinColumn(name = "recipe_Id")
	private RecipeBean recipe;
	
	
	
	@Expose
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "IngProductId")
	private IngredientProduct ingredientProduct;


	public MateralBean() {}

	





public MateralBean(Integer materal_Id, String materalName, String unit, RecipeBean recipe,
			IngredientProduct ingredientProduct) {
		super();
		this.materal_Id = materal_Id;
		this.materalName = materalName;
		this.unit = unit;
		this.recipe = recipe;
		this.ingredientProduct = ingredientProduct;
	}







public MateralBean(Integer materal_Id, String materalName, RecipeBean recipe, IngredientProduct ingredientProduct) {
		super();
		this.materal_Id = materal_Id;
		this.materalName = materalName;
		this.recipe = recipe;
		this.ingredientProduct = ingredientProduct;
		unit = "";
	}







public Integer getMateral_Id() {
	return materal_Id;
}


public void setMateral_Id(Integer materal_Id) {
	this.materal_Id = materal_Id;
}


public String getMateralName() {
	return materalName;
}


public void setMateralName(String materalName) {
	this.materalName = materalName;
}


public RecipeBean getRecipe() {
	return recipe;
}


public void setRecipe(RecipeBean recipe) {
	this.recipe = recipe;
}


public IngredientProduct getIngredientProduct() {
	return ingredientProduct;
}


public void setIngredientProduct(IngredientProduct ingredientProduct) {
	this.ingredientProduct = ingredientProduct;
}


public static long getSerialversionuid() {
	return serialVersionUID;
}







public String getUnit() {
	return unit;
}







public void setUnit(String unit) {
	this.unit = unit;
}







@Override
public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("MateralBean [materal_Id=");
	builder.append(materal_Id);
	builder.append(", materalName=");
	builder.append(materalName);
	builder.append(", unit=");
	builder.append(unit);
	builder.append("]");
	return builder.toString();
}











	
}
