package shopping;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import _00.utils.HibernateUtils;
import _00.utils.SystemUtils2018;
import shopping.model.CuisineProduct;
import shopping.model.IngredientProduct;
import shopping.model.PlaneProduct;
import shopping.model.ProductCategory;
import shopping.repository.impl.PlaneProductDaoImpl;
import shopping.service.PlaneProductService;
import shopping.service.impl.CuisineProductServiceImpl;
import shopping.service.impl.IngredientProductServiceImpl;
import shopping.service.impl.PlaneProductServiceImpl;
import shopping.service.impl.ProductCategoryServiceImpl;

public class CreateTable {
	public static final String UTF8_BOM = "\uFEFF";

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();

		ProductCategoryServiceImpl categoryServiceImpl = new ProductCategoryServiceImpl();
		CuisineProductServiceImpl cuisineProductServiceImpl = new CuisineProductServiceImpl();
		IngredientProductServiceImpl ingredientProductServiceImpl = new IngredientProductServiceImpl();
		PlaneProductService planeProductService = new PlaneProductServiceImpl();
		List<IngredientProduct> ingredientProducts = new ArrayList<IngredientProduct>();
		List<CuisineProduct> cuisineProducts = new ArrayList<CuisineProduct>();
		List<PlaneProduct> planeProducts = new ArrayList<PlaneProduct>();
		String line = "";
		File fileIngredient = new File("src/main/resources/data/productFood.data");
		File fileCuisine = new File("src/main/resources/data/productCuisine.data");
		File filePlane = new File("src/main/resources/data/productPlane.data");

//		創建商品分類
		String category[] = { "muscle", "fit", "keep", "brute", "vegetable", "poultry", "fruit", "seafood", "egg",
				"rice" };
		List<ProductCategory> list = new ArrayList<ProductCategory>();
		for (int i = 0; i < category.length; i++) {
			ProductCategory productCategory = new ProductCategory(null, category[i]);
			list.add(productCategory);
		}

		Transaction tx = session.beginTransaction();
//		Insert 商品分類 into Table
		categoryServiceImpl.insertFakeData(list);

//		讀取食材商品資料
		try (FileInputStream fis = new FileInputStream(fileIngredient);
				InputStreamReader isr = new InputStreamReader(fis, "UTF8");
				BufferedReader br = new BufferedReader(isr);) {
			while ((line = br.readLine()) != null) {
				// 去除 UTF8_BOM: \uFEFF
				if (line.startsWith(UTF8_BOM)) {
					line = line.substring(1);
				}

				String[] token = line.split("\\|");

				IngredientProduct ingredientProduct = new IngredientProduct();
				ingredientProduct.setName(token[0]);
				ingredientProduct.setPrice(Integer.parseInt(token[1]));
				ingredientProduct.setProductCategory(categoryServiceImpl.getProcutCategoryByCategory(token[2]));
				String imgaeToke[] = token[3].split(",");
				ingredientProduct.setCoverImage(SystemUtils2018.fileToBlob(imgaeToke[0]));
				ingredientProduct.setFileName(token[3]);
				ingredientProduct.setSource(token[4]);
				ingredientProduct.setUnit(token[5]);
				ingredientProduct.setId(null);
				ingredientProduct.setStock(Integer.parseInt(token[6]));
				token[7] = token[7].replace("\\r", System.getProperty("line.separator"));
				ingredientProduct.setIntroduction(token[7]);
				ingredientProduct.setCertification(token[8]);
				ingredientProduct.setStorage(token[9]);
				ingredientProducts.add(ingredientProduct);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

//		Insert 食材商品資料 into Table
		ingredientProductServiceImpl.insertFakeData(ingredientProducts);

//		讀取餐點商品資料
		try (FileInputStream fis = new FileInputStream(fileCuisine);
				InputStreamReader isr = new InputStreamReader(fis, "UTF8");
				BufferedReader br = new BufferedReader(isr);) {
			while ((line = br.readLine()) != null) {
				// 去除 UTF8_BOM: \uFEFF
				if (line.startsWith(UTF8_BOM)) {
					line = line.substring(1);
				}
				Set<IngredientProduct> products = new HashSet<IngredientProduct>();
				String[] token = line.split("\\|");
				CuisineProduct cuisineProduct = new CuisineProduct();
				cuisineProduct.setName(token[0]);
				cuisineProduct.setPrice(Integer.parseInt(token[1]));
				cuisineProduct.setProductCategory(categoryServiceImpl.getProcutCategoryByCategory(token[2]));
				String imgaeToke[] = token[3].split(",");
				cuisineProduct.setCoverImage(SystemUtils2018.fileToBlob(imgaeToke[0]));
				cuisineProduct.setFileName(token[3]);
				cuisineProduct.setId(null);
				cuisineProduct.setMeal(token[4]);
				cuisineProduct.setCalories(Integer.parseInt(token[5]));
				cuisineProduct.setCarbohydrate(Integer.parseInt(token[6]));
				cuisineProduct.setProtein(Integer.parseInt(token[7]));
				cuisineProduct.setFat(Integer.parseInt(token[8]));
				cuisineProduct.setStock(Integer.parseInt(token[9]));
				token[10] = token[10].replace("\\r", System.getProperty("line.separator"));
				cuisineProduct.setIntroduction(token[10]);
				String materialToken[] = token[11].split(",");
				for (String string : materialToken) {
					Integer materialId = Integer.parseInt(string);
					IngredientProduct ingredientProduct = ingredientProductServiceImpl
							.getIngredientProductById(materialId);
					products.add(ingredientProduct);
				}
				cuisineProduct.setIngredientProducts(products);
				cuisineProducts.add(cuisineProduct);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

//		Insert 餐點商品資料 into Table
		cuisineProductServiceImpl.insertFakeData(cuisineProducts);

//		讀取計畫商品資料
		try (FileInputStream fis = new FileInputStream(filePlane);
				InputStreamReader isr = new InputStreamReader(fis, "UTF8");
				BufferedReader br = new BufferedReader(isr);) {
			while ((line = br.readLine()) != null) {
				// 去除 UTF8_BOM: \uFEFF
				if (line.startsWith(UTF8_BOM)) {
					line = line.substring(1);
				}
				String[] token = line.split("\\|");
				PlaneProduct planeProduct = new PlaneProduct();
				planeProduct.setName(token[0]);
				planeProduct.setPrice(Integer.parseInt(token[1]));
				planeProduct.setDays(Integer.parseInt(token[2]));
				planeProduct.setMeals(Integer.parseInt(token[3]));
				planeProduct.setAlias(token[4]);
				planeProduct.setShorthandOfPlane(token[5]);
				planeProducts.add(planeProduct);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		planeProductService.insertFakeData(planeProducts);
		tx.commit();
	}
}
