package magazinestore.entity;
// Generated 27-apr-2019 19.55.30 by Hibernate Tools 5.2.12.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.NamedQueries;

@Entity
@Table(name = "ordine_rivista",catalog = "magazinestoredb")

@NamedQueries({
	@NamedQuery(name = "RivistaOrder.findAll", query = "SELECT ri FROM RivistaOrder ri ORDER BY ri.orderDate DESC"),
	@NamedQuery(name = "RivistaOrder.countAll", query = "SELECT COUNT(*) FROM RivistaOrder"),
	@NamedQuery(name = "RivistaOrder.findByCustomer",  query = "SELECT ri FROM RivistaOrder ri WHERE ri.cliente.clienteId =:clienteId ORDER BY ri.orderDate DESC"),
	@NamedQuery(name = "RivistaOrder.findByIdAndCustomer",query = "SELECT ri FROM RivistaOrder ri WHERE ri.ordineId =:ordineId AND ri.cliente.clienteId =:clienteId"),
	@NamedQuery(name = "RivistaOrder.countByCustomer",query = "SELECT COUNT(ri.ordineId) FROM RivistaOrder ri WHERE ri.cliente.clienteId =:clienteId")})

public class RivistaOrder implements java.io.Serializable {

	private int ordineId;
	private Cliente cliente;
	private Date orderDate;
	private String shippingAddress;
	private String recipientName;
	private String recipientPhone;
	private String paymentMethod;
	private float total;
	private String status;
	private Set<DettagliOrdine> dettagliOrdines = new HashSet<DettagliOrdine>(0);

	public RivistaOrder() {
	}

	public RivistaOrder(Cliente cliente, Date orderDate, String shippingAddress, String recipientName,
			String recipientPhone, String paymentMethod, float total, String status) {
	
		this.cliente = cliente;
		this.orderDate = orderDate;
		this.shippingAddress = shippingAddress;
		this.recipientName = recipientName;
		this.recipientPhone = recipientPhone;
		this.paymentMethod = paymentMethod;
		this.total = total;
		this.status = status;
	}

	public RivistaOrder(Cliente cliente, Date orderDate, String shippingAddress, String recipientName,
			String recipientPhone, String paymentMethod, float total, String status,
			Set<DettagliOrdine> dettagliOrdines) {
		this.cliente = cliente;
		this.orderDate = orderDate;
		this.shippingAddress = shippingAddress;
		this.recipientName = recipientName;
		this.recipientPhone = recipientPhone;
		this.paymentMethod = paymentMethod;
		this.total = total;
		this.status = status;
		this.dettagliOrdines = dettagliOrdines;
	}

	@Id
	
	@GeneratedValue(strategy = IDENTITY)
	
	@Column(name = "ordine_id", unique = true, nullable = false)
	public int getOrdineId() {
		return this.ordineId;
	}

	public void setOrdineId(int ordineId) {
		this.ordineId = ordineId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cliente_id",nullable = false)
	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "order_date", nullable = false, length = 19)
	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Column(name = "shipping_address", nullable = false, length = 256)
	public String getShippingAddress() {
		return this.shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	@Column(name = "recipient_name", nullable = false, length = 30)
	public String getRecipientName() {
		return this.recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	@Column(name = "recipient_phone", nullable = false, length = 15)
	public String getRecipientPhone() {
		return this.recipientPhone;
	}

	public void setRecipientPhone(String recipientPhone) {
		this.recipientPhone = recipientPhone;
	}

	@Column(name = "payment_method", nullable = false, length = 40)
	public String getPaymentMethod() {
		return this.paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@Column(name = "total", nullable = false, precision = 12, scale = 0)
	public float getTotal() {
		return this.total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	@Column(name = "status", nullable = false, length = 20)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "rivistaOrder",cascade = CascadeType.ALL,orphanRemoval = true)
	public Set<DettagliOrdine> getDettagliOrdines() {
		return this.dettagliOrdines;
	}

	public void setDettagliOrdines(Set<DettagliOrdine> dettagliOrdines) {
		this.dettagliOrdines = dettagliOrdines;
	}
	
	@Transient
	public int getRivistaCopies() {
		int total = 0;
		
		for (DettagliOrdine orderDetail : dettagliOrdines) {
			total += orderDetail.getQuantity();
		}
		
		return total;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ordineId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RivistaOrder other = (RivistaOrder) obj;
		if (ordineId != other.ordineId)
			return false;
		return true;
	}



}
