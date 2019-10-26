package shopping.repository;

import java.util.List;

import createAccount.model.MemberBean;
import shopping.model.CartItem;
import shopping.model.Product;

public interface CartItemDao {

	public void save(CartItem cartItem);

	public CartItem update(CartItem cartItem);

	public CartItem checkItem(CartItem cartItem);

	public CartItem checkItem(Product productBean, MemberBean memberBean);

	public int delete(MemberBean memberBean);

	public List<CartItem> checkAllItems(MemberBean memberBean);

	public void delete(Product productBean, MemberBean memberBean);

}
