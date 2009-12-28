package cn.googift.crawler.service;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import cn.googift.crawler.data.Product;




public class ProductService {
	protected EntityManager em;
	
	public ProductService(EntityManager em) {
	    this.em = em;
    }
	
	public Product create(Product p) {
		em.getTransaction().begin();
		p.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		em.merge(p);
		em.flush();
		em.getTransaction().commit();
			
		return p;
	}
	
	
	public Product update(Product p) {
		em.getTransaction().begin();
		p.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		em.merge(p);
		em.flush();
		em.getTransaction().commit();
			
		return p;
	}

	public Product find(String guid) {
	    return em.find(Product.class, guid);
	}	
	
	public Product getProductByUrl(String url) {
		Query q = em.createQuery(
				"SELECT p FROM Product p WHERE p.url = :url");
		q = q.setParameter("url",url);
		List<Product> list =q.getResultList();
		if (null != list && !list.isEmpty())
			return list.get(0);
		
		return null;
	}
	
	public void remove(String guid) {
	    Product p = find(guid);
		if (p != null) {
			  em.getTransaction().begin();
		      em.remove(p);
		      em.flush();
		      em.getTransaction().commit();
		}
	}

	public Collection<Product> findAll() {
		Query query = em.createQuery("SELECT p FROM Product p");
		return (Collection<Product>) query.getResultList();
	}

}
