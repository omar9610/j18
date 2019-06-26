package magazinestore.controller.frontend.shoppingcart;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import magazinestore.entity.Rivista;

public class ShoppingCartTest {
	private static ShoppingCart cart;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		 cart = new ShoppingCart();
		Rivista rivista = new Rivista(1);
		rivista.setPrice(10);
		cart.addItem(rivista);	
		cart.addItem(rivista);
	}
	
	@Test
	public void testAddItem() {
		
		Map<Rivista, Integer> items = cart.getItems();
		int quantity = items.get(new Rivista(1));
		assertEquals(2, quantity);
	}
	
	@Test
	public void testRemoveItem() {
		cart.removeItem(new Rivista(1));
		
		assertTrue(cart.getItems().isEmpty());
	}
	
	@Test
	public void testRemoveItem2() {
		Rivista rivista2 = new Rivista(2);
		cart.addItem(rivista2);
		
		cart.removeItem(new Rivista(2));
		
		assertNull(cart.getItems().get(rivista2));
	}
	
	@Test
	public void testGetTotalQuantity() {
		Rivista rivista3 = new Rivista(3);
		cart.addItem(rivista3);
		cart.addItem(rivista3);
		cart.addItem(rivista3);
		
		assertEquals(5, cart.getTotalQuantity());
	}
	
	@Test
	public void testGetTotalAmount1() {
		ShoppingCart cart = new ShoppingCart();
		assertEquals(0.0f, cart.getTotalAmount(), 0.0f);
	}
	@Test
	public void testGetTotalAmount2() {		
		assertEquals(20.0f, cart.getTotalAmount(), 0.0f);
	}	
	
	@Test
	public void testClear() {
		cart.clear();
		
		assertEquals(0, cart.getTotalQuantity());
	}
	
	@Test
	public void testUpdateCart() {
		ShoppingCart cart = new ShoppingCart();
		Rivista rivista1 = new Rivista(1);
		Rivista rivista2 = new Rivista(2);
		
		cart.addItem(rivista1);
		cart.addItem(rivista2);
		
		int[] rivistaIds = {1, 2};
		int[] quantities = {3, 4};
		
		cart.updateCart(rivistaIds, quantities);
		
		assertEquals(7, cart.getTotalQuantity());
		
	}
}
