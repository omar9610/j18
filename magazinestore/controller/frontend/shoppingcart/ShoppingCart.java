package magazinestore.controller.frontend.shoppingcart;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import magazinestore.entity.Rivista;

public class ShoppingCart {
	private Map<Rivista, Integer> cart = new HashMap<>();
	
	public void addItem(Rivista rivista) {
		if (cart.containsKey(rivista)) {
			Integer quantity = cart.get(rivista) + 1;
			cart.put(rivista, quantity);			
		} else {
			cart.put(rivista, 1);
		}
	}
	
	public void removeItem(Rivista rivista) {
		cart.remove(rivista);
	}
	
	public int getTotalQuantity() {
		int total = 0;
		
		Iterator<Rivista> iterator = cart.keySet().iterator();
		
		while (iterator.hasNext()) {
			Rivista next = iterator.next();
			Integer quantity = cart.get(next);
			total += quantity;
		}
		
		return total;
	}
	
	public float getTotalAmount() {
		float total = 0.0f;
		
		Iterator<Rivista> iterator = cart.keySet().iterator();
		
		while (iterator.hasNext()) {
			Rivista rivista = iterator.next();
			Integer quantity = cart.get(rivista);
			float subTotal = quantity * rivista.getPrice();
			total += subTotal;
		}		
		
		return total;
	}
	
	
	public void updateCart(int[] rivistaIds, int[] quantities) {
		for (int i = 0; i < rivistaIds.length; i++) {
			Rivista key = new Rivista(rivistaIds[i]);
			Integer value = quantities[i];
			cart.put(key, value);
		}
	}
	
	public void clear() {
		cart.clear();
	}
	
	public int getTotalItems() {
		return cart.size();
	}
	
	public Map<Rivista,Integer> getItems() {
		return this.cart;
	}
	

}
