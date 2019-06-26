package magazinestore.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import magazinestore.entity.RivistaOrder;

public class OrderDAO extends JpaDAO<RivistaOrder> implements GenericDAO<RivistaOrder> {

	@Override
	public RivistaOrder create(RivistaOrder order) {
		
		order.setOrderDate(new Date());		
		order.setStatus("Processing");
		order.setPaymentMethod("Pagamento quando arriva l'ordine");
		
		return super.create(order);
	}

	@Override
	public RivistaOrder update(RivistaOrder order) {
		return super.update(order);
	}

	@Override
	public RivistaOrder get(Object ordineId) {
		return super.find(RivistaOrder.class, ordineId);
	}

	public RivistaOrder get(Integer ordineId, Integer clienteId) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("ordineId", ordineId);
		parameters.put("clienteId",clienteId);
		
		List<RivistaOrder> result = super.findWithNamedQuery("RivistaOrder.findByIdAndCustomer", parameters );
		
		if (!result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}
	
	@Override
	public void delete(Object ordineId) {
		super.delete(RivistaOrder.class, ordineId);		
	}

	@Override
	public List<RivistaOrder> listAll() {
		return super.findWithNamedQuery("RivistaOrder.findAll");
	}

	@Override
	public long count() {	
		return super.countWithNamedQuery("RivistaOrder.countAll");
	}
	
	public List<RivistaOrder> listByCustomer(Integer clienteId) {
		return super.findWithNamedQuery("RivistaOrder.findByCustomer","clienteId", clienteId);
	}
	
	public List<RivistaOrder> listMostRecentSales() {
		return super.findWithNamedQuery("RivistaOrder.findAll", 0, 3);
	}

	public long countOrderDetailByRivista(int rivistaId) {
		return super.countWithNamedQuery("dettagli_ordine.countByRivistak", "rivistaId", rivistaId);
	}
	
	public long countByCustomer(int customerId) {
		return super.countWithNamedQuery("OrdineRevista.countByCustomer", "customerId", customerId);
	}

}
