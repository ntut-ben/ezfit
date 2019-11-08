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
import javax.persistence.UniqueConstraint;

import createAccount.model.MemberBean;

@Entity
@Table(name = "Good" ,uniqueConstraints={@UniqueConstraint(columnNames={"recipeId","memberId"})})
public class GoodBean implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pk;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "recipeId")
	private RecipeBean recipe;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "memberId")
	private MemberBean member;


	public GoodBean() {}



	public GoodBean(Integer pk, RecipeBean recipe, MemberBean member) {
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



	public static long getSerialversionuid() {
		return serialVersionUID;
	}

//	@Override
//	public String toString() {
//		StringBuilder builder = new StringBuilder();
//		builder.append("GoodBean [pk=");
//		builder.append(pk);
//		builder.append(", recipe=");
//		builder.append(recipe);
//		builder.append(", member=");
//		builder.append(member);
//		builder.append("]");
//		return builder.toString();
//	}


	



	
	
	
	
}
