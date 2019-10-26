package shopping.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import com.google.gson.annotations.Expose;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class IngredientProduct extends Product {

	@Expose
	@Column(nullable = false)
	private String unit;
	@Expose
	@Column(nullable = false)
	private String source;
	@Expose
	@Column(nullable = false)
	private String certification;
	@Expose
	@Column(nullable = false)
	private String storage;

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getCertification() {
		return certification;
	}

	public void setCertification(String certification) {
		this.certification = certification;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("IngredientProduct [unit=");
		builder.append(unit);
		builder.append(", source=");
		builder.append(source);
		builder.append(", certification=");
		builder.append(certification);
		builder.append(", storage=");
		builder.append(storage);
		builder.append("]");
		return builder.toString();
	}

}
