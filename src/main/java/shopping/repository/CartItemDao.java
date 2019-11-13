package shopping.repository;

import java.util.List;

import createAccount.model.MemberBean;
import shopping.model.CartItem;
import shopping.model.GroupBuyBean;
import shopping.model.Product;

public interface CartItemDao {

	void save(CartItem cartItem);

	CartItem update(CartItem cartItem);

	CartItem checkItem(CartItem cartItem);

	CartItem checkItem(Product productBean, MemberBean memberBean);

	CartItem checkItem(Product productBean, MemberBean memberBean, GroupBuyBean groupBuyBean);

	List<CartItem> checkAllItems(GroupBuyBean groupBuyBean, MemberBean memberBean);

	int delete(MemberBean memberBean);

	List<CartItem> checkAllItems(MemberBean memberBean);

	void delete(Integer id, MemberBean memberBean);

	void delete(Product productBean, MemberBean memberBean, GroupBuyBean groupBuyBean);

	CartItem checkItem(Integer cartId, MemberBean memberBean);

	public void remove(Product productBean, MemberBean memberBean);

}
