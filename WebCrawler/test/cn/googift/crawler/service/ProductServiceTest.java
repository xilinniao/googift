package cn.googift.crawler.service;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import cn.googift.crawler.data.Product;



import junit.framework.TestCase;

public class ProductServiceTest extends TestCase {

	EntityManagerFactory emf;
	EntityManager em;
	ProductService ps;
	String url = "http://www.jindong.com/productid=10";
	static String uuid = UUID.randomUUID().toString();
	
	protected void setUp() throws Exception {
        emf = Persistence.createEntityManagerFactory("crawler");
        em =  emf.createEntityManager();
        ps = new ProductService(em);
	}

	protected void tearDown() throws Exception {
		em.close();
		emf.close();
	}
	
	public void aatestCreateProduct()
	{
		Product p = new Product(url,"Dell Desktop");
		p.setGuid(uuid);
		System.out.println("CreateProduct with UUID " + uuid);
		ps.create(p);
		
		Product retrive = ps.find(p.getGuid());
		
		assertTrue(retrive != null);
		assertEquals(retrive.getGuid(), p.getGuid());
		assertEquals(retrive.getUrl(), p.getUrl());
	}
	
	public void testSaveProduct()
	{
//		Product p = new Product();
//		p.setUrl(url);
//		p.setName("wldandan");
//		ps.save(p);
	}
	
	public void testGetProductByUrl() throws InterruptedException
	{
		Thread.sleep(1000);
		
		Product p = ps.getProductByUrl("http://www.360buy.com/product/187670.html");
		//assertNull(p.getUpdatedAt());
		
//		String newName = "HP laptop"; 
//		p.setName(newName);
		ps.update(p);
		
//		Product retrive = ps.find(p.getGuid());
//		assertTrue(retrive != null);
//		assertEquals(retrive.getGuid(), p.getGuid());
//		assertEquals(retrive.getUrl(), p.getUrl());
//		
//		assertNotNull(retrive.getUpdatedAt());
//		assertEquals(retrive.getUrl(), p.getUrl());
//		assertEquals(retrive.getName(),newName);
	}
	
	public void testRemoveProduct()
	{
//		ps.remove(uuid);
//		System.out.println("RemoveProduct with UUID " + uuid);
//		Product retrive = ps.find(uuid);
//		assertNull(retrive);
	}

}
