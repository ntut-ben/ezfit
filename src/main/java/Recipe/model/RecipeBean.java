package Recipe.model;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.google.gson.annotations.Expose;

import createAccount.model.MemberBean;

//import org.hibernate.annotations.Table;

@Entity
@Table(name = "Recipe")
public class RecipeBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private Integer recipeId;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ownerId")
	@Expose
	private MemberBean member;
	@Expose
	private String recipeName;
	@Expose
	private String introduction;
	private Blob recipePic;
	@Expose
	private String fileName;
	@Expose
	private Integer servings;
	@Expose
	private String spendTime;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	@Expose
	private Date recipeCreateTime;
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Expose
	private Date lastModify;
	@Expose
	private Boolean published;
	@Expose
	private Integer good;
	@Expose
	private Integer save;
	@Expose
	private Integer chat;
	
	@OneToMany(mappedBy = "recipe", cascade =  CascadeType.ALL )
	private Set<MethodBean> method = new HashSet<>();
	@OneToMany(mappedBy = "recipe",cascade = {CascadeType.ALL})
	private Set<MateralBean> materal = new HashSet<>();
	@OneToMany(mappedBy = "recipe",cascade = CascadeType.ALL)
	private Set<FollowedRecipeBean> followedRecipe = new HashSet<>();
	@OneToMany(mappedBy = "recipe",cascade = CascadeType.ALL)
	private Set<GoodBean> goodBean = new HashSet<>();
	@OneToMany(mappedBy = "recipe",cascade = CascadeType.ALL)
	private Set<BoardBean> boardbran = new HashSet<>();
	
	public RecipeBean() {
	}

	




	public RecipeBean(Integer recipeId, MemberBean member, String recipeName, String introduction, Blob recipePic,
			String fileName, Integer servings, String spendTime, Date recipeCreateTime, Date lastModify,
			Boolean published, Integer good, Integer save, Integer chat) {
		super();
		this.recipeId = recipeId;
		this.member = member;
		this.recipeName = recipeName;
		this.introduction = introduction;
		this.recipePic = recipePic;
		this.fileName = fileName;
		this.servings = servings;
		this.spendTime = spendTime;
		this.recipeCreateTime = recipeCreateTime;
		this.lastModify = lastModify;
		this.published = published;
		this.good = good;
		this.save = save;
		this.chat = chat;
	}






	public RecipeBean(Integer recipeId, MemberBean ownerId, String recipeName, String introduction, Blob recipePic,
		String fileName, Date recipeCreateTime, Date lastModify, Boolean published, Integer good, Integer save) {
	super();
	this.recipeId = recipeId;
	this.member = ownerId;
	this.recipeName = recipeName;
	this.introduction = introduction;
	this.recipePic = recipePic;
	this.fileName = fileName;
	this.recipeCreateTime = recipeCreateTime;
	this.lastModify = lastModify;
	this.published = published;
	this.good = good;
	this.save = save;
}

	
	
	
	
	
	
	




	public Integer getRecipeId() {
		return recipeId;
	}


	public void setRecipeId(Integer recipeId) {
		this.recipeId = recipeId;
	}


	public MemberBean getMember() {
		return member;
	}


	public void setMember(MemberBean member) {
		this.member = member;
	}


	public String getRecipeName() {
		return recipeName;
	}


	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}


	public String getIntroduction() {
		return introduction;
	}


	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}


	public Blob getRecipePic() {
		return recipePic;
	}




	public void setRecipePic(Blob recipePic) {
		this.recipePic = recipePic;
	}




	public String getFileName() {
		return fileName;
	}




	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public Date getRecipeCreateTime() {
		return recipeCreateTime;
	}


	public void setRecipeCreateTime(Date recipeCreateTime) {
		this.recipeCreateTime = recipeCreateTime;
	}


	public Date getLastModify() {
		return lastModify;
	}


	public void setLastModify(Date lastModify) {
		this.lastModify = lastModify;
	}




	public Boolean getPublished() {
		return published;
	}




	public void setPublished(Boolean published) {
		this.published = published;
	}




	public Integer getGood() {
		return good;
	}




	public void setGood(Integer good) {
		this.good = good;
	}




	public Integer getSave() {
		return save;
	}




	public void setSave(Integer save) {
		this.save = save;
	}




	public Integer getChat() {
		return chat;
	}




	public void setChat(Integer chat) {
		this.chat = chat;
	}




	public Set<MethodBean> getMethod() {
		return method;
	}




	public void setMethod(Set<MethodBean> method) {
		this.method = method;
	}




	public Set<MateralBean> getMateral() {
		return materal;
	}




	public void setMateral(Set<MateralBean> materal) {
		this.materal = materal;
	}




	public Set<FollowedRecipeBean> getFollowedRecipe() {
		return followedRecipe;
	}




	public void setFollowedRecipe(Set<FollowedRecipeBean> followedRecipe) {
		this.followedRecipe = followedRecipe;
	}




	public Set<GoodBean> getGoodBean() {
		return goodBean;
	}




	public void setGoodBean(Set<GoodBean> goodBean) {
		this.goodBean = goodBean;
	}




	public Set<BoardBean> getBoardbran() {
		return boardbran;
	}




	public void setBoardbran(Set<BoardBean> boardbran) {
		this.boardbran = boardbran;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	
	
	
	
	public Integer getServings() {
		return servings;
	}






	public void setServings(Integer servings) {
		this.servings = servings;
	}






	public String getSpendTime() {
		return spendTime;
	}






	public void setSpendTime(String spendTime) {
		this.spendTime = spendTime;
	}






	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RecipeBean [recipeId=");
		builder.append(recipeId);
		builder.append(", member=");
		builder.append(member);
		builder.append(", recipeName=");
		builder.append(recipeName);
		builder.append(", introduction=");
		builder.append(introduction);
		builder.append(", recipePic=");
		builder.append(recipePic);
		builder.append(", fileName=");
		builder.append(fileName);
		builder.append(", servings=");
		builder.append(servings);
		builder.append(", spendTime=");
		builder.append(spendTime);
		builder.append(", recipeCreateTime=");
		builder.append(recipeCreateTime);
		builder.append(", lastModify=");
		builder.append(lastModify);
		builder.append(", published=");
		builder.append(published);
		builder.append(", good=");
		builder.append(good);
		builder.append(", save=");
		builder.append(save);
		builder.append(", chat=");
		builder.append(chat);
		builder.append("]");
		return builder.toString();
	}






	






	
	
	
	
}
