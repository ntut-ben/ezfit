package Recipe.model;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "Method")
public class MethodBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private Integer id;
	@Expose
	private String detail;
	private Blob pic;
	@Expose
	private String fileName;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "recipeId")
	private RecipeBean recipe;

	public MethodBean() {
	}

//
////	public MethodBean(Integer id, Integer recipeId, String detail, Blob pic, String fileName) {
////		this.id = id;
////		this.recipeId = recipeId;
////		this.detail = detail;
////		this.pic = pic;
////		this.fileName = fileName;
////	}

//	public MethodBean(Integer id, RecipeBean recipe, String detail, Blob pic, String fileName) {
//		this.id = id;
//		this.recipe = recipe;
//		this.detail = detail;
//		this.pic = pic;
//		this.fileName = fileName;
//	}

	public MethodBean(Integer id, String detail, Blob pic, String fileName) {
		super();
		this.id = id;
		this.detail = detail;
		this.pic = pic;
		this.fileName = fileName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

//	public Integer getRecipeId() {
//		return recipeId;
//	}
//
//
//	public void setRecipeId(Integer recipeId) {
//		this.recipeId = recipeId;
//	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Blob getPic() {
		return pic;
	}

	public void setPic(Blob pic) {
		this.pic = pic;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public RecipeBean getRecipe() {
		return recipe;
	}

	public void setRecipe(RecipeBean recipe) {
		this.recipe = recipe;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MethodBean [id=");
		builder.append(id);
		builder.append(", detail=");
		builder.append(detail);
		builder.append(", pic=");
		builder.append(pic);
		builder.append(", fileName=");
		builder.append(fileName);
		builder.append(", recipe=");
		builder.append(recipe);
		builder.append("]");
		return builder.toString();
	}

}
