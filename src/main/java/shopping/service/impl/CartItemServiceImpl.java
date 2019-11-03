package shopping.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import createAccount.model.MemberBean;
import shopping.model.CartItem;
import shopping.model.GroupBuyBean;
import shopping.model.Product;
import shopping.repository.CartItemDao;
import shopping.repository.ProductDao;
import shopping.repository.impl.CartItemDaoImpl;
import shopping.repository.impl.ProductDaoImpl;
import shopping.service.CartItemService;

@Service
public class CartItemServiceImpl implements CartItemService {

	CartItemDao cartItemDaoImpl;
	ProductDao productDaoImpl;

	@Autowired
	public CartItemServiceImpl(CartItemDao cartItemDaoImpl, ProductDao productDaoImpl) {
		this.cartItemDaoImpl = cartItemDaoImpl;
		this.productDaoImpl = productDaoImpl;
	}

	@Override
	@Transactional
	public void saveOrUpdate(CartItem cartItem) {

		cartItemDaoImpl.update(cartItem);

	}

	@Override
	@Transactional
	public void delete(Integer id, MemberBean memberBean) {

		cartItemDaoImpl.delete(id, memberBean);

	}

	@Override
	@Transactional
	public List<CartItem> checkAllItems(MemberBean memberBean) {

		return cartItemDaoImpl.checkAllItems(memberBean);
	}

	@Override
	@Transactional
	public CartItem modifyQTY(Integer cartId, MemberBean memberBean, Integer qty) {
		CartItem cartItemEvict = null;
		cartItemEvict = cartItemDaoImpl.checkItem(cartId, memberBean);
		cartItemEvict.setQty(qty);
		Integer subTotal = qty * cartItemEvict.getProduct().getPrice();
		cartItemEvict.setSubTotal(subTotal);
		return cartItemDaoImpl.update(cartItemEvict);

	}

	@Override
	@Transactional
	public int delete(MemberBean memberBean) {
		return cartItemDaoImpl.delete(memberBean);

	}

	@Override
	@Transactional
	public CartItem checkItem(Product productBean, MemberBean memberBean) {

		return cartItemDaoImpl.checkItem(productBean, memberBean);
	}

	@Override
	@Transactional
	public CartItem checkItem(Integer cartId, MemberBean memberBean) {
		return cartItemDaoImpl.checkItem(cartId, memberBean);
	}

	@Override
	@Transactional
	public List<CartItem> checkAllItems(GroupBuyBean groupBuyBean, MemberBean memberBean) {

		return cartItemDaoImpl.checkAllItems(groupBuyBean, memberBean);
	}

	@Override
	@Transactional
	public CartItem checkItem(Product productBean, MemberBean memberBean, GroupBuyBean groupBuyBean) {

		return cartItemDaoImpl.checkItem(productBean, memberBean, groupBuyBean);
	}

	@Override
	@Transactional
	public void delete(Integer id, MemberBean memberBean, GroupBuyBean groupBuyBean) {
		Product productBean = productDaoImpl.getProductById(id);
		cartItemDaoImpl.delete(productBean, memberBean, groupBuyBean);
	}

	@Override
	public void remove(Integer id, MemberBean memberBean) {
		Product productBean = productDaoImpl.getProductById(id);
		cartItemDaoImpl.remove(productBean, memberBean);

	}

}
