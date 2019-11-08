package Recipe.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.google.gson.annotations.Expose;

import createAccount.model.MemberBean;

@Entity
@Table(name = "Board")
public class BoardBean implements Serializable{


	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private Integer boardId;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "recipeId")
	private RecipeBean recipe;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "memberId")
	@Expose
	private MemberBean member;
	@Expose
	private String detail;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	@Expose
	private Date boardCreateTime;

	public BoardBean() {}

	public BoardBean(Integer boardId, RecipeBean recipe, MemberBean member, String detail, Date boardCreateTime) {
		super();
		this.boardId = boardId;
		this.recipe = recipe;
		this.member = member;
		this.detail = detail;
		this.boardCreateTime = boardCreateTime;
	}

	public Integer getBoardId() {
		return boardId;
	}

	public void setBoardId(Integer boardId) {
		this.boardId = boardId;
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

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Date getBoardCreateTime() {
		return boardCreateTime;
	}

	public void setBoardCreateTime(Date boardCreateTime) {
		this.boardCreateTime = boardCreateTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BoardBean [boardId=");
		builder.append(boardId);
		builder.append(", recipe=");
		builder.append(recipe);
		builder.append(", member=");
		builder.append(member);
		builder.append(", detail=");
		builder.append(detail);
		builder.append(", boardCreateTime=");
		builder.append(boardCreateTime);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
