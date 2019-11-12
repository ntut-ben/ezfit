package Recipe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import Recipe.model.MateralBean;
import Recipe.model.MethodBean;
import Recipe.model.RecipeBean;
import Recipe.service.MateralService;
import Recipe.service.MethodService;
import Recipe.service.RecipeService;
import createAccount.model.MemberBean;

public class createRecipe {
	public static final String UTF8_BOM = "\uFEFF"; // 定義 UTF-8的BOM字元
	private static ApplicationContext ctx;

	public static void main(String[] args) throws IOException, SQLException, ParseException {

		ctx = new ClassPathXmlApplicationContext("beans.xml");
		SessionFactory factory = ctx.getBean(SessionFactory.class);
		Session session = factory.getCurrentSession();

		RecipeService recipeService = ctx.getBean(RecipeService.class);
		MateralService materalService = ctx.getBean(MateralService.class);
//		MethodService methodService = ctx.getBean(MethodService.class);

		File file = new File("C:/ezfitData/createTable/recipe.data");
		File file2 = new File("C:/ezfitData/createTable/method.data");
		File file3 = new File("C:/ezfitData/createTable/materal.data");
//		File file = new File("C:/Users/tmps8/Desktop/ezfitData/createTable/recipe.data");
//		File file2 = new File("C:/Users/tmps8/Desktop/ezfitData/createTable/method.data");
//		File file3 = new File("C:/Users/tmps8/Desktop/ezfitData/createTable/materal.data");

		int n = 0;
		String line = "";

		Transaction tx = session.beginTransaction();
//		recipe
		try (FileInputStream fis = new FileInputStream(file);
				InputStreamReader isr = new InputStreamReader(fis, "UTF8");
				BufferedReader br = new BufferedReader(isr);) {

				MemberBean mb = new MemberBean();
				session.save(mb);
			
			while ((line = br.readLine()) != null) {
				System.out.println("line=" + line);
				// 去除 UTF8_BOM: \uFEFF
//				if (line.startsWith(UTF8_BOM)) {
//					line = line.substring(1);
				try {

					String[] token = line.split("\\|");
					RecipeBean recipe = new RecipeBean();
					recipe.setChat(Integer.parseInt(token[0]));
					recipe.setFileName(token[1]);
					recipe.setGood(Integer.parseInt(token[2]));
					recipe.setIntroduction(token[3]);
					recipe.setPublished(Boolean.valueOf(token[4]));
					recipe.setRecipeName(token[5]);
					recipe.setSave(Integer.parseInt(token[6]));
					recipe.setServings(Integer.parseInt(token[7]));
					recipe.setSpendTime((token[8]));
//					recipe.setMember(recipeDao.getMemberBeanByMemberId(token[9]));
					recipe.setMember(recipeService.getMemberByMemberId(token[9]));

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date date = sdf.parse("2019-11-11 10:54:17.943000");
					recipe.setRecipeCreateTime(date);
//					session.save(recipe);
					recipeService.insertRecipe(recipe);
				} catch (Exception e) {
					e.printStackTrace();
				}
				++n;
//				}
			}
			tx.commit();
			System.out.println("新增Recipe紀錄，共成功=" + n);
		}

//		method
		List<MethodBean> list = new ArrayList<>();
		line = "";
		factory = ctx.getBean(SessionFactory.class);
		session = factory.getCurrentSession();
		tx = session.beginTransaction();
		try (FileInputStream fis = new FileInputStream(file2);
				InputStreamReader isr = new InputStreamReader(fis, "UTF8");
				BufferedReader br = new BufferedReader(isr);) {

			while ((line = br.readLine()) != null) {
				System.out.println("line=" + line);
				// 去除 UTF8_BOM: \uFEFF
//				if (line.startsWith(UTF8_BOM)) {
//					line = line.substring(1);

				String[] token = line.split("\\|");
				MethodBean method = new MethodBean();
				method.setDetail(token[0]);
				method.setFileName(token[1]);
				RecipeBean recipe = new RecipeBean();
				recipe.setRecipeId(Integer.parseInt(token[2]));
				method.setRecipe(recipe);

//					session.save(method);
//				list.add(method);
				++n;
				session.save(method);
//				}
//				methodService.insertMethod(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
//		tx.commit();
		System.out.println("新增Method紀錄，共成功=" + n);

//		materal
//		factory = ctx.getBean(SessionFactory.class);
//		session = factory.getCurrentSession();
//		tx = session.beginTransaction();
		line = "";
//		tx = session.beginTransaction();
		try (FileInputStream fis = new FileInputStream(file3);
				InputStreamReader isr = new InputStreamReader(fis, "UTF8");
				BufferedReader br = new BufferedReader(isr);) {

			while ((line = br.readLine()) != null) {
				System.out.println("line=" + line);
				// 去除 UTF8_BOM: \uFEFF
//				if (line.startsWith(UTF8_BOM)) {
//					line = line.substring(1);

				String[] token = line.split("\\|");
				MateralBean materal = new MateralBean();
				materal.setMateralName(token[0]);
				materal.setUnit(token[1]);

				RecipeBean recipe = new RecipeBean();
				recipe.setRecipeId(Integer.valueOf(token[2]));
				materal.setRecipe(recipe);

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = sdf.parse("2019-11-11 10:54:17.943000");
				recipe.setRecipeCreateTime(date);

				materalService.insertMateral(materal);
				++n;

//				}
			}
			System.out.println("新增Materal紀錄，共成功=" + n);
		}
		tx.commit();
	}

}
