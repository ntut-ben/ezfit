package shopping.service;

import java.util.List;

import shopping.model.PlaneProduct;

public interface PlaneProductService {
	public void insertFakeData(List<PlaneProduct> planeProducts);

	List<PlaneProduct> getAllPlaneProducts();
}
