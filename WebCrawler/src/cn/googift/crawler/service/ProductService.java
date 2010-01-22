package cn.googift.crawler.service;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import cn.googift.crawler.data.Product;

public class ProductService {
	
	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	public ProductService() {
    }
	
	public Product create(Product p) {
		String logInfo = "created [" + p.getUrl() + "]";
		EntityManagerHelper.log("saving new product[" + p.getUrl() + "]", Level.INFO, null);
		
		p.setGuid(UUID.randomUUID().toString());
		p.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		
		try {
			EntityManagerHelper.beginTransaction();
			getEntityManager().persist(p);
			EntityManagerHelper.commit();
			
			EntityManagerHelper.log("Successfully " + logInfo, Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("Failed " + logInfo, Level.SEVERE, re);
		}
		finally
		{
			EntityManagerHelper.closeEntityManager();
		}
		return p;
	}
	
	
	public Product update(Product p) {
		String logInfo = "updated [" + p.getUrl() + "]";
		p.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		try {
			EntityManagerHelper.beginTransaction();
			getEntityManager().merge(p);
			EntityManagerHelper.commit();
			
			EntityManagerHelper.log("Successfully " + logInfo, Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("Failed " + logInfo, Level.SEVERE, re);
		}
		finally
		{
			EntityManagerHelper.closeEntityManager();
		}
		
		return p;
	}
	
	public Product save(Product p) {
		String logInfo = "saved [" + p.getUrl() + "]";
		
		Product aP = this.getProductByUrl(p.getUrl());
		if (null == aP)
		{
			p.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		}
		else
		{
			p.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		}
		
		try {
			EntityManagerHelper.beginTransaction();
			getEntityManager().merge(p);
			EntityManagerHelper.commit();
			
			EntityManagerHelper.log("Successfully " + logInfo, Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("Failed " + logInfo, Level.SEVERE, re);
		}
		finally
		{
			EntityManagerHelper.closeEntityManager();
		}
		return p;
	}

	public Product find(String guid) {
	    return getEntityManager().find(Product.class, guid);
	}	
	
	public Product getProductByUrl(String url) {
		Query q = getEntityManager().createQuery(
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
			String logInfo = "removed [" + p.getUrl() + "]";
			try {
				EntityManagerHelper.beginTransaction();
				getEntityManager().remove(p);
				EntityManagerHelper.commit();
				
				EntityManagerHelper.log("Successfully " + logInfo, Level.INFO, null);
			} catch (RuntimeException re) {
				EntityManagerHelper.log("Failed " + logInfo, Level.SEVERE, re);
				throw re;
			}
			finally
			{
				EntityManagerHelper.closeEntityManager();
			}
		}
	}

	public Collection<Product> findAll() {
		Query query = getEntityManager().createQuery("SELECT p FROM Product p");
		return (Collection<Product>) query.getResultList();
	}

}
