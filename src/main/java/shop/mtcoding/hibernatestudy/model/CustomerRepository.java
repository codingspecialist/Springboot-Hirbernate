package shop.mtcoding.hibernatestudy.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Slf4j
@Repository
public class CustomerRepository {
    private EntityManager em;

    public CustomerRepository(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public void save(Customer customer) {
        em.persist(customer);
    }

    @Transactional
    public void update(Customer customer) {
        em.merge(customer);
    }

    @Transactional
    public void delete(Customer customer) {
        em.remove(customer);
    }

    public Customer findById(Long id) {
        return em.find(Customer.class, id);
    }

    public List<Customer> findAll(int page) {
        final int row = 2;
        return em.createQuery("select ct from Customer ct", Customer.class)
                .setFirstResult(page*row)
                .setMaxResults(row)
                .getResultList();
    }
}
