package shopping.repository;

import java.util.List;

import shopping.model.PlaneProduct;

public interface PlaneProductDao {
	public void insertFakeData(List<PlaneProduct> planeProducts);

	List<PlaneProduct> getAllPlaneProducts();
}
