package shop.mtcoding.hibernatestudy.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Slf4j
@Repository
public class CustomerNativeQueryRepository {
    private EntityManager em;

    public CustomerNativeQueryRepository(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public void save(Customer customer) {
        Query query = em.createNativeQuery("INSERT INTO customer (name, tel) VALUES (:name, :tel)");
        query.setParameter("name", customer.getName());
        query.setParameter("tel", customer.getTel());
        query.executeUpdate();
    }

    @Transactional
    public void update(Customer customer) {
        Query query = em.createNativeQuery("UPDATE customer SET name = :name, tel = :tel WHERE id = :id");
        query.setParameter("name", customer.getName());
        query.setParameter("tel", customer.getTel());
        query.setParameter("id", customer.getId());
        query.executeUpdate();
    }

    @Transactional
    public void delete(Customer customer) {
        Query query = em.createNativeQuery("DELETE FROM customer WHERE id = :id");
        query.setParameter("id", customer.getId());
        query.executeUpdate();
    }

    public Customer findById(Long id) {
        Query query = em.createNativeQuery("SELECT * FROM customer WHERE id = :id", Customer.class);
        query.setParameter("id", id);
        return (Customer) query.getSingleResult();
    }

    public List<Customer> findAll(int page) {
        final int row = 2;
        Query query = em.createNativeQuery("select * from customer limit :page, :row", Customer.class);
        query.setParameter("page", page*row);
        query.setParameter("row", row);
        return query.getResultList();
    }
}
