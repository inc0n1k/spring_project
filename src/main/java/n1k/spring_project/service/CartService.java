package n1k.spring_project.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import n1k.spring_project.json.CartItemJSON;
import n1k.spring_project.sup.FullCartCookie;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CartService {
	//work
	public FullCartCookie updateCart(FullCartCookie fullCartCookie, long id, int new_count) {

		int count = getCartCount(fullCartCookie.getCount());
		if (new_count < 0 || new_count == 0 || count == 0) return fullCartCookie;

		FullCartCookie fullCartCookieResult = new FullCartCookie();
		List<CartItemJSON> cartItems = getCartItemList(fullCartCookie.getCart());

		if (count == 1) {
			if (cartItems.get(0).getId().equals(id)) {
				cartItems.get(0).setCount(new_count);
			}
		} else {
			for (CartItemJSON cartItem : cartItems) {
				if (cartItem.getId().equals(id)) {
					cartItem.setCount(new_count);
					break;
				}
			}
		}
		String jsonString = null;
		try {
			jsonString = new ObjectMapper().writeValueAsString(cartItems);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
//		fullCartCookieResult.setCart(URLEncoder.encode(jsonString, StandardCharsets.UTF_8));
		fullCartCookieResult.setCart(jsonString);
//		fullCartCookieResult.setCount(URLEncoder.encode(String.valueOf(count), StandardCharsets.UTF_8));
		fullCartCookieResult.setCount(String.valueOf(count));

		return fullCartCookieResult;

	}//close updateCart

	//work
	public FullCartCookie addToCart(FullCartCookie fullCartCookie, long id) {
		int count = getCartCount(fullCartCookie.getCount());
		FullCartCookie fullCartCookieResult = new FullCartCookie();
		List<CartItemJSON> cartItems = getCartItemList(fullCartCookie.getCart());
		switch (count) {
			case 0 -> {
				cartItems.add(new CartItemJSON(id, 1));
				count = 1;
			}
			case 1 -> {
				if (cartItems.get(0).getId().equals(id)) {
					cartItems.get(0).setCount(cartItems.get(0).getCount() + 1);
				} else {
					cartItems.add(new CartItemJSON(id, 1));
					count++;
				}
			}
			default -> {
				boolean key = false;
				for (CartItemJSON cartItem : cartItems) {
					if (cartItem.getId().equals(id)) {
						cartItem.setCount(cartItem.getCount() + 1);
						key = true;
						break;
					}
				}
				if (!key) {
					cartItems.add(new CartItemJSON(id, 1));
					count++;
				}
			}
		}
		String jsonString = null;
		try {
			jsonString = new ObjectMapper().writeValueAsString(cartItems);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		assert jsonString != null;
//		fullCartCookieResult.setCart(URLEncoder.encode(jsonString, StandardCharsets.UTF_8));
		fullCartCookieResult.setCart(jsonString);
//		fullCartCookieResult.setCount(URLEncoder.encode(String.valueOf(count), StandardCharsets.UTF_8));
		fullCartCookieResult.setCount(String.valueOf(count));
		return fullCartCookieResult;
	}//close addToCart
	//work
	public FullCartCookie removeFromCart(FullCartCookie fullCartCookie, long id) {
		List<CartItemJSON> cartItemJSONList = getCartItemList(fullCartCookie.getCart());
		int count = getCartCount(fullCartCookie.getCount());
		String jsonString = "";
		if (count == 1 && cartItemJSONList.size() != 0 && id == cartItemJSONList.get(0).getId()) {
			return null;
		} else {
			cartItemJSONList.removeIf(element -> element.getId().equals(id));
		}
		try {
			jsonString = new ObjectMapper().writeValueAsString(cartItemJSONList);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return new FullCartCookie(String.valueOf(count - 1), jsonString);
	}

	//work
	public List<CartItemJSON> getCartItemList(String cart_cookie) {
		List<CartItemJSON> cartItems = new ArrayList<>();
		String jsonString = URLDecoder.decode(cart_cookie, StandardCharsets.UTF_8);
		try {
			switch (jsonString.lastIndexOf("{")) {
				case -1 -> {
					return cartItems;
				}
				case 0 -> cartItems.add(new ObjectMapper().
						readerFor(CartItemJSON.class).readValue(jsonString));
				default -> cartItems.addAll(Arrays.asList(new ObjectMapper().
						readerFor(CartItemJSON[].class).readValue(jsonString)));
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return cartItems;
	}//close getCartItemList

	//work
	public Integer getCartCount(String count_cookie) {
		return count_cookie == null || count_cookie.isEmpty() ?
				0 : Integer.parseInt(URLDecoder.decode(count_cookie, StandardCharsets.UTF_8));
	}//close getCartCount

}//close CartService