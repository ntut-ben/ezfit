package Recipe.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
@Entity
@Table(name = "Keyword")
public class KeyWordBean implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private Integer Pk;
	@Expose
	private String keyWord;
	@Expose
	private Integer count;
	
	
	public KeyWordBean() {
	}



	public KeyWordBean(Integer pk, String keyWord,Integer count) {
		super();
		Pk = pk;
		this.keyWord = keyWord;
		if(count == null) {
			this.count = 0;
		}else {
			this.count = count;
		}
	}



	public Integer getPk() {
		return Pk;
	}



	public void setPk(Integer pk) {
		Pk = pk;
	}



	public String getKeyWord() {
		return keyWord;
	}



	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}



	public Integer getCount() {
		return count;
	}



	public void setCount(Integer count) {
		this.count = count;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("KeyWordBean [Pk=");
		builder.append(Pk);
		builder.append(", keyWord=");
		builder.append(keyWord);
		builder.append(", count=");
		builder.append(count);
		builder.append("]");
		return builder.toString();
	}




	
	
	
	
	
	
	
	
	
	
}
