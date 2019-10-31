package shopping.service;

import java.util.List;

import createAccount.model.MemberBean;
import shopping.model.CartItem;
import shopping.model.GroupBuyBean;
import shopping.model.Product;

public interface CartItemService {
	public void saveOrUpdate(CartItem cartItem);

	public CartItem modifyQTY(Integer id, MemberBean memberBean, Integer qty);

	public List<CartItem> checkAllItems(MemberBean memberBean);

	public void delete(Integer id, MemberBean memberBean);

	public void delete(Integer id, MemberBean memberBean, GroupBuyBean groupBuyBean);

	public int delete(MemberBean memberBean);

	public CartItem checkItem(Product productBean, MemberBean memberBean);

	public CartItem checkItem(Product productBean, MemberBean memberBean, GroupBuyBean groupBuyBean);

	public CartItem checkItem(Integer cartId, MemberBean memberBean);

	public List<CartItem> checkAllItems(GroupBuyBean groupBuyBean, MemberBean memberBean);
}
