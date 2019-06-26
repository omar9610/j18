package magazinestore.entity;
// Generated 27-apr-2019 17.51.55 by Hibernate Tools 5.2.12.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@NamedQueries({
    @NamedQuery(name = "Category.findAll", query = "SELECT c FROM Categoria c ORDER BY c.name"),
    @NamedQuery(name = "Category.findByName", query = "SELECT c FROM Categoria c WHERE c.name = :name"),
    @NamedQuery(name = "Category.countAll", query = "SELECT Count(*) FROM Utenti Categoria")
})
@Table(name = "categoria", catalog = "magazinestoredb")
public class Categoria implements java.io.Serializable {

	private Integer categoriaId;
	private String name;
	private Set<Rivista> rivista = new HashSet<Rivista>(0);

	public Categoria() {
	}

	public Categoria(String name) {
		this.name = name;
	}

	public Categoria(String name, Set<Rivista> rivista) {
		this.name = name;
		this.rivista = rivista;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "categoria_id", unique = true, nullable = false)
	public Integer getCategoriaId() {
		return this.categoriaId;
	}

	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
	}

	@Column(name = "name", nullable = false, length = 30)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "categoria")
	public Set<Rivista> getRivista() {
		return this.rivista;
	}

	public void setRivista(Set<Rivista> rivista) {
		this.rivista = rivista;
	}

}
