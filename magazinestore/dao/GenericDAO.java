/**
 * Interfaccia che definisce i metodi generici per tutte le sottoclassi nel dao package*/

package magazinestore.dao;

import java.util.List;
/*Il parametro <T> sta per tipo generico (per qualsiasi tipo>*/
public interface GenericDAO<E> {
    E create(E type);   //usiamo un tipo generico per indicare un'entità
    E update(E type);   //
    E get(Object id);   //ha come parametro l'ID dell'ogetto dell'entità
    void delete(Object id);//
    List<E> listAll();  //ritorna la lista di entità
    long  count();      //ritorna numero totale di entità (numero totale di righe in una tabella)
}
