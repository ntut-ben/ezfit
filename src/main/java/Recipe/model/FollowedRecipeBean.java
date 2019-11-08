package Recipe.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

import createAccount.model.MemberBean;
@Entity
@Table(name="FollowedRecipe")
public class FollowedRecipeBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private Integer pk;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "recipeId")
//	@Expose
	private RecipeBean recipe;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "followerId")
//	@Expose
	private MemberBean member;
	
	
	
	
	
	public FollowedRecipeBean() {}





	public FollowedRecipeBean(Integer pk, RecipeBean recipe, MemberBean member) {
		super();
		this.pk = pk;
		this.recipe = recipe;
		this.member = member;
	}





	public Integer getPk() {
		return pk;
	}





	public void setPk(Integer pk) {
		this.pk = pk;
	}





	public RecipeBean getRecipe() {
		return recipe;
	}





	public void setRecipe(RecipeBean recipe) {
		this.recipe = recipe;
	}





	public MemberBean getMember() {
		return member;
	}





	public void setMember(MemberBean member) {
		this.member = member;
	}





	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FollowedRecipeBean [pk=");
		builder.append(pk);
		builder.append(", recipe=");
		builder.append(recipe);
		builder.append(", member=");
		builder.append(member);
		builder.append("]");
		return builder.toString();
	}






	
	
	
	
	
	
	
	
	
	
	
}
