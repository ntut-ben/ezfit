package shopping.service.impl;

import java.util.List;

import createAccount.model.MemberBean;
import shopping.model.CartItem;
import shopping.model.Product;
import shopping.repository.impl.CartItemDaoImpl;
import shopping.repository.impl.ProductDaoImpl;
import shopping.service.CartItemService;

public class CartItemServiceImpl implements CartItemService {

	CartItemDaoImpl cartItemDaoImpl;
	ProductDaoImpl productDaoImpl;

	public CartItemServiceImpl() {
		cartItemDaoImpl = new CartItemDaoImpl();
		productDaoImpl = new ProductDaoImpl();
	}

	@Override
	public void saveOrUpdate(CartItem cartItem) {
//		CartItem cartItemEvict = null;
//		cartItemEvict = cartItemDaoImpl.checkItem(cartItem);
//
//		if (cartItemEvict == null) {
//			cartItemDaoImpl.save(cartItem);
//		} else {
//			Integer qty = cartItem.getQty() + cartItemEvict.getQty();
//			Integer subTotal = qty * cartItem.getProduct().getPrice();
//			cartItemEvict.setQty(qty);
//			cartItemEvict.setSubTotal(subTotal);
//			cartItemDaoImpl.update(cartItemEvict);
//		}

		cartItemDaoImpl.update(cartItem);

	}

	@Override
	public void delete(Integer id, MemberBean memberBean) {
		Product productBean = productDaoImpl.getProductById(id);
		cartItemDaoImpl.delete(productBean, memberBean);

	}

	@Override
	public List<CartItem> checkAllItems(MemberBean memberBean) {

		return cartItemDaoImpl.checkAllItems(memberBean);
	}

	@Override
	public CartItem modifyQTY(Integer id, MemberBean memberBean, Integer qty) {
		CartItem cartItemEvict = null;
		Product productBean = productDaoImpl.getProductById(id);
		cartItemEvict = cartItemDaoImpl.checkItem(productBean, memberBean);
		cartItemEvict.setQty(qty);
		Integer subTotal = qty * cartItemEvict.getProduct().getPrice();
		cartItemEvict.setSubTotal(subTotal);
		return cartItemDaoImpl.update(cartItemEvict);

	}

	@Override
	public int delete(MemberBean memberBean) {
		return cartItemDaoImpl.delete(memberBean);

	}

	@Override
	public CartItem checkItem(Product productBean, MemberBean memberBean) {

		return cartItemDaoImpl.checkItem(productBean, memberBean);
	}

}
