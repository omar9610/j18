package magazinestore.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import magazinestore.entity.Cliente;
import magazinestore.entity.DettagliOrdine;
import magazinestore.entity.RivistaOrder;
import magazinestore.entity.Rivista;

public class OrderDAOTest {
	private static OrderDAO orderDAO;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		orderDAO = new OrderDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		orderDAO.close();
	}

	@Test
	public void testCreateRivistaOrder2() {
		RivistaOrder order = new RivistaOrder();
		Cliente customer = new Cliente();
		customer.setClienteId(3);
		
		order.setCliente(customer);
		order.setRecipientName(" ahmed gad");
		order.setRecipientPhone("123456789");
		order.setShippingAddress("piazza liberta, Corsico, Milano");
		
		Set<DettagliOrdine> orderDetails = new HashSet<>();
		DettagliOrdine orderDetail1 = new DettagliOrdine();
		
		Rivista rivista1 = new Rivista(2);
		orderDetail1.setRivista(rivista1);
		orderDetail1.setQuantity(2);
		orderDetail1.setSubtotal(30.0f);
		orderDetail1.setRivistaOrder(order);
		
		
		orderDetails.add(orderDetail1);

		Rivista rivista2 = new Rivista(1);
		DettagliOrdine orderDetail2 = new DettagliOrdine();
		orderDetail2.setRivista(rivista2);
		orderDetail2.setQuantity(2);
		orderDetail2.setSubtotal(60);
		orderDetail2.setRivistaOrder(order);
		
		orderDetails.add(orderDetail2);
		
		order.setDettagliOrdines(orderDetails);
		
	   orderDAO.create(order);
		
		assertTrue(order.getOrdineId() > 0 && order.getDettagliOrdines().size() == 2);
	}	
	
	@Test
	public void testCreateRivistaOrder() {
		RivistaOrder order = new RivistaOrder();
		Cliente customer = new Cliente();
		customer.setClienteId(7);
		
		order.setCliente(customer);
		order.setRecipientName("Nam Ha Minh");
		order.setRecipientPhone("123456789");
		order.setShippingAddress("123 South Street, New York, USA");
		
		Set<DettagliOrdine> orderDetails = new HashSet<>();
		DettagliOrdine orderDetail = new DettagliOrdine();
		
		Rivista rivista = new Rivista(2);
		orderDetail.setRivista(rivista);
		orderDetail.setQuantity(2);
		orderDetail.setSubtotal(30.0f);
		orderDetail.setRivistaOrder(order);
		
		orderDetails.add(orderDetail);
		
		order.setDettagliOrdines(orderDetails);
		
		orderDAO.create(order);
		
		assertTrue(order.getOrdineId() > 0);
		
	}

	@Test
	public void testUpdateBookOrderShippingAddress() {
		Integer orderId = 22;
		RivistaOrder order = orderDAO.get(orderId);
		order.setShippingAddress("New Shipping Address");
		
		orderDAO.update(order);
		
		RivistaOrder updatedOrder = orderDAO.get(orderId);
		
		assertEquals(order.getShippingAddress(), updatedOrder.getShippingAddress());
		
	}
	
	@Test
	public void testUpdateBookOrderDetail() {
		Integer orderId = 22;
		RivistaOrder order = orderDAO.get(orderId);
		
		Iterator<DettagliOrdine> iterator = order.getDettagliOrdines().iterator();
		
		while (iterator.hasNext()) {
			DettagliOrdine orderDetail = iterator.next();
			if (orderDetail.getRivista().getRivistaId() == 2) {
				orderDetail.setQuantity(3);
				orderDetail.setSubtotal(120);
			}
		}
			
		
		orderDAO.update(order);
		
		RivistaOrder updatedOrder = orderDAO.get(orderId);
		
		iterator = order.getDettagliOrdines().iterator();
		
		int expectedQuantity = 3;
		float expectedSubtotal = 120;
		int actualQuantity = 0;
		float actualSubtotal = 0;
		
		while (iterator.hasNext()) {
			DettagliOrdine orderDetail = iterator.next();
			if (orderDetail.getRivista().getRivistaId() == 2) {
				actualQuantity = orderDetail.getQuantity();
				actualSubtotal = orderDetail.getSubtotal();
			}
		}		
		
		assertEquals(expectedQuantity, actualQuantity);
		assertEquals(expectedSubtotal, actualSubtotal, 0.0f);
		
	}	

	@Test
	public void testGet() {
		Integer orderId = 22;
		RivistaOrder order = orderDAO.get(orderId);
		System.out.println(order.getRecipientName());
		System.out.println(order.getRecipientPhone());
		System.out.println(order.getShippingAddress());
		System.out.println(order.getStatus());
		System.out.println(order.getTotal());
		System.out.println(order.getPaymentMethod());
		
		
		assertEquals(1, order.getDettagliOrdines().size());
	}

	@Test
	public void testGetByIdAndCustomerNull() {
		Integer orderId = 22;
		Integer customerId = 30;
	
		RivistaOrder order = orderDAO.get(orderId, customerId);
		
		assertNull(order);
	}

	@Test
	public void testGetByIdAndCustomerNotNull() {
		Integer orderId = 22;
		Integer customerId = 3;
	
		RivistaOrder order = orderDAO.get(orderId, customerId);
		
		assertNotNull(order);
	}	
	
	@Test
	public void testDeleteOrder() {
		int orderId = 21;
		orderDAO.delete(orderId);
		
		RivistaOrder order = orderDAO.get(orderId);
		
		assertNull(order);
	}

	@Test
	public void testListAll() {
		List<RivistaOrder> listOrders = orderDAO.listAll();
		
		for (RivistaOrder order : listOrders) {
			System.out.println(order.getOrdineId() + " - " + order.getCliente().getFullname()
					+ " - " + order.getTotal() + " - " + order.getStatus());
			for (DettagliOrdine detail : order.getDettagliOrdines()) {
				Rivista rivista = detail.getRivista();
				int quantity = detail.getQuantity();
				float subtotal = detail.getSubtotal();
				System.out.println("\t" + rivista.getTitle() + " - " + quantity + " - " + subtotal);
			}
		}
		
		assertTrue(listOrders.size() > 0);
	}

	@Test
	public void testListByCustomerNoOrders() {
		Integer customerId = 2;
		List<RivistaOrder> listOrders = orderDAO.listByCustomer(customerId);
		
		assertTrue(listOrders.isEmpty());
	}
	
	@Test
	public void testListByCustomerHaveOrders() {
		Integer customerId = 3;
		List<RivistaOrder> listOrders = orderDAO.listByCustomer(customerId);
		
		assertTrue(listOrders.size() > 0);
	}	
	
	@Test
	public void testCount() {
		long totalOrders = orderDAO.count();
		assertEquals(3, totalOrders);
	}

	@Test
	public void testListMostRecentSales() {
		List<RivistaOrder> recentOrders = orderDAO.listMostRecentSales();
		
		assertEquals(3, recentOrders.size());
	}
	
	@Test
	public void testCountOrderDetailByBookNotFound() {
		int rivistaId = 999;
		long orderCount = orderDAO.countOrderDetailByRivista(rivistaId);
		
		assertEquals(0, orderCount);
	}
	
	@Test
	public void testCountOrderDetailByBookFound() {
		int rivistaId = 2;
		long orderCount = orderDAO.countOrderDetailByRivista(rivistaId);
		
		assertEquals(3, orderCount);
	}
	
	@Test
	public void testCountByCustomerNotFound() {
		int customerId = 999;
		long orderCount = orderDAO.countByCustomer(customerId);
		
		assertEquals(0, orderCount);
	}
	
	@Test
	public void testCountByCustomerFound() {
		int customerId = 8;
		long orderCount = orderDAO.countByCustomer(customerId);
		
		assertEquals(2, orderCount);
	}	
}