package Recipe.repository.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import Recipe.model.RecipeBean;
import Recipe.repository.RecipeDao;
import _00.utils.DateYearTime;
import createAccount.model.MemberBean;

@Repository
public class RecipeDao_Impl implements RecipeDao {
	SessionFactory factory;

	@Autowired
	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}

	RecipeBean recipe = null;

	public RecipeDao_Impl() {
	}

//	用recipeId找RecipeBean，用在新增method，輸入字串(recipeId)，回傳RecipeBean型態，來讓Hibernate新增recipeId這個欄位
	@Override
	public RecipeBean getRecipeByRecipeId(String s) {
		Session session = factory.getCurrentSession();
		try {
			String hql = "FROM RecipeBean R WHERE R.recipeId = :pk";
			Query query = session.createQuery(hql);
			query.setParameter("pk", Integer.valueOf(s));
			recipe = (RecipeBean) query.getSingleResult();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return recipe;
	}

	@Override
	public MemberBean getMemberBeanByMemberId(String s) {
		Session session = factory.getCurrentSession();
		MemberBean member = new MemberBean();
		try {
			String hql = "FROM MemberBean M WHERE M.pkey = :pk";
			Query query = session.createQuery(hql);
			query.setParameter("pk", Integer.valueOf(s));
			member = (MemberBean) query.getSingleResult();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}

	@Override
	public MemberBean getMemberBeanByMemberName(String memberName) {
		Session session = factory.getCurrentSession();
		MemberBean member = new MemberBean();
		String hql = "FROM MemberBean M WHERE M.name = :memberName";
		try {
			member = (MemberBean) session.createQuery(hql).setParameter("memberName", memberName).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}

	// 查詢食譜讚數
	@Override
	public int searchGood(String recipeId) {
		int n = 0;
		long l = 0;
		String hql = "SELECT COUNT(GD) FROM GoodBean GD WHERE GD.recipe = :recipe";
		try {
			Session session = factory.getCurrentSession();
			RecipeBean rb = session.get(RecipeBean.class, Integer.valueOf(recipeId));
			l = (long) session.createQuery(hql).setParameter("recipe", rb).getSingleResult();
			n = (int) l;
		} catch (Exception e) {
			e.printStackTrace();
			n = 0;
		}
		return n;
	}

	@Override
	public int searchGood(RecipeBean recipe) {
		int n = 0;
		long l = 0;
		String hql = "SELECT COUNT(GD) FROM GoodBean GD WHERE GD.recipe = :recipe";
		try {
			Session session = factory.getCurrentSession();
			RecipeBean rb = session.get(RecipeBean.class, recipe.getRecipeId());
			l = (long) session.createQuery(hql).setParameter("recipe", rb).getSingleResult();
			n = (int) l;
		} catch (Exception e) {
			e.printStackTrace();
			n = 0;
		}
		return n;
	}

//	查詢熱門食譜
	@SuppressWarnings("unchecked")
	public List<RecipeBean> searchHotRecipe() {
		Session session = factory.getCurrentSession();
		List<RecipeBean> list = new ArrayList<>();
		String hql = "FROM RecipeBean RB WHERE RB.published = :bool ORDER BY RB.save DESC";
		list = session.createQuery(hql).setParameter("bool", true).setMaxResults(4).getResultList();
		return list;
	}

//	查詢食譜收藏數
	@Override
	public int searchSave(String recipeId) {
		int n = 0;
		long l = 0;
		String hql = "SELECT COUNT(FB) FROM FollowedRecipeBean FB WHERE FB.recipe = :recipe";
		try {
			Session session = factory.getCurrentSession();
			RecipeBean rb = session.get(RecipeBean.class, Integer.valueOf(recipeId));
			l = (long) session.createQuery(hql).setParameter("recipe", rb).getSingleResult();
			n = (int) l;
		} catch (Exception e) {
			e.printStackTrace();
			n = 0;
		}
		return n;
	}

	@Override
	public int searchSave(RecipeBean recipe) {
		int n = 0;
		long l = 0;
		String hql = "SELECT COUNT(FB) FROM FollowedRecipeBean FB WHERE FB.recipe = :recipe";
		try {
			Session session = factory.getCurrentSession();
			RecipeBean rb = session.get(RecipeBean.class, recipe.getRecipeId());
			l = (long) session.createQuery(hql).setParameter("recipe", rb).getSingleResult();
			n = (int) l;
		} catch (Exception e) {
			e.printStackTrace();
			n = 0;
		}
		return n;
	}

//	查詢食譜留言數
	@Override
	public int searchChat(String recipeId) {
		int n = 0;
		long l = 0;
		String hql = "SELECT COUNT(BB) FROM BoardBean BB WHERE BB.recipe = :recipe";
		try {
			Session session = factory.getCurrentSession();
			RecipeBean rb = session.get(RecipeBean.class, Integer.valueOf(recipeId));
			l = (long) session.createQuery(hql).setParameter("recipe", rb).getSingleResult();
			n = (int) l;
		} catch (Exception e) {
			e.printStackTrace();
			n = 0;
		}
		return n;
	}

	@Override
	public int searchChat(RecipeBean recipe) {
		int n = 0;
		long l = 0;
		String hql = "SELECT COUNT(BB) FROM BoardBean BB WHERE BB.recipe = :recipe";
		try {
			Session session = factory.getCurrentSession();
			RecipeBean rb = session.get(RecipeBean.class, recipe.getRecipeId());
			l = (long) session.createQuery(hql).setParameter("recipe", rb).getSingleResult();
			n = (int) l;
		} catch (Exception e) {
			e.printStackTrace();
			n = 0;
		}
		return n;
	}

//	=====傳入會員 找食譜=====
	@Override
	@SuppressWarnings("unchecked")
	public List<RecipeBean> selectRecipe(MemberBean member, boolean bool) {
		List<RecipeBean> list = new ArrayList<RecipeBean>();
		Integer memberId = member.getPkey();
		String hql = "FROM RecipeBean RB WHERE RB.ownerId = :member AND  RB.published = :bool";
		try {
			Session session = factory.getCurrentSession();
			list = session.createQuery(hql).setParameter("member", memberId).setParameter("bool", bool).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<RecipeBean> selectRecipe(String member, boolean bool) {
		List<RecipeBean> list = new ArrayList<RecipeBean>();
//		Integer memberId = Integer.valueOf(member);
		String hql = "FROM RecipeBean RB WHERE RB.member = :member AND  RB.published = :bool";
		try {
			Session session = factory.getCurrentSession();
			list = session.createQuery(hql).setParameter("member", getMemberBeanByMemberId(member))
					.setParameter("bool", bool).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

//	=====傳入字串(RecipeName 找食譜)===== return set<RecipeBean>關於查詢功能，SET將在Service處理
	@Override
	@SuppressWarnings("unchecked")
	public List<RecipeBean> selectRecipeByOwnerName(String memberName) {
		List<RecipeBean> list = new ArrayList<>();
		try {
			Session session = factory.getCurrentSession();

			MemberBean mb = new MemberBean();
//		要透過memberName找到member
			mb = getMemberBeanByMemberName(memberName);
//		========================

//		String hql = "FROM RecipeBean RB WHERE RB.recipeName LIKE :name OR RB.member = :mb";
			String hql = "FROM RecipeBean RB WHERE RB.member = :mb AND  RB.published = :bool";
			list = session.createQuery(hql).setParameter("mb", mb).setParameter("bool", true).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 用食譜名找
	@SuppressWarnings("unchecked")
	@Override
	public List<RecipeBean> selectRecipeByRecipeName(String recipeName) {
		List<RecipeBean> list = new ArrayList<>();
		try {
			Session session = factory.getCurrentSession();

//		========================

			String hql = "FROM RecipeBean RB WHERE RB.recipeName LIKE :recipeName AND  RB.published = :bool";
			list = session.createQuery(hql).setParameter("recipeName", "%" + recipeName + "%")
					.setParameter("bool", true).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

//	=======刪除食譜==========
	@Override
	public int deleteRecipe(String recipeId) {
		int n = 0;
		try {
			Session session = factory.getCurrentSession();
			RecipeBean rb = session.get(RecipeBean.class, Integer.valueOf(recipeId));
			session.delete(rb);
			++n;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}

	@Override
	public int deleteRecipe(RecipeBean recipeId) {
		int n = 0;
		try {
			Session session = factory.getCurrentSession();
			RecipeBean rb = session.get(RecipeBean.class, recipeId.getRecipeId());
			session.delete(rb);
			++n;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}

//	========新增食譜========
	@Override
	public int insertRecipe(RecipeBean recipe) {
		int n = 0;
		try {
			Session session = factory.getCurrentSession();
			session.save(recipe);
			++n;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}

//	========修改食譜========
	@Override
	public int updateRecipe(RecipeBean recipe) {
		int n = 0;
		try {
			Session session = factory.getCurrentSession();
			session.saveOrUpdate(recipe);
			++n;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RecipeBean> weekNew(Date monday, Date sunday) {
		Session session = factory.getCurrentSession();
		List<RecipeBean> list = new ArrayList<>();
		String hql = "FROM RecipeBean RB WHERE RB.published = :published AND RB.recipeCreateTime BETWEEN :day1 AND :day2 ORDER BY RB.recipeCreateTime DESC";
		try {
			list = session.createQuery(hql).setParameter("day1", monday).setParameter("day2", sunday)
					.setParameter("published", true).setMaxResults(4).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RecipeBean> selectRecipePerYear(String memberId, boolean published, String year) {
		List<RecipeBean> list = new ArrayList<RecipeBean>();

//		Integer memberId = Integer.valueOf(member);
		String hql = "FROM RecipeBean RB WHERE RB.member = :member AND  RB.published = :bool AND RB.recipeCreateTime BETWEEN :day1 AND :day2";
		DateYearTime dyt = new DateYearTime();
		Date begin = dyt.getBeginOfTheYear(year);
		Date last = dyt.getEndOfTheYear(year);

		try {
			Session session = factory.getCurrentSession();
			list = session.createQuery(hql).setParameter("member", getMemberBeanByMemberId(memberId))
					.setParameter("bool", published).setParameter("day1", begin).setParameter("day2", last)
					.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
