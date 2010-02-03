package cn.googift.crawler.data;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductTest extends TestCase {
	Product p;
	List<String> categories;
	Map<String, String> resLinks;
	
	public void setUp()
	{
		p = new Product();
		categories = new ArrayList<String>();
		categories.add("aa1");
		categories.add("aa2");
		categories.add("aa3");
		p.setCategories(categories);


		resLinks = new HashMap<String, String>();
		resLinks.put("http://www.baidu.com/little", "http://www.baidu.com/little");
		resLinks.put("http://www.google.com/little", "http://www.google.com/little");
		resLinks.put("http://www.aaa.com/little", "http://www.aaa.com/little");
		p.setPicLinks(resLinks);
	}

	public void testGetResLink()
	{
		System.out.println(p.getPicLinks());
	}

	public void testGetCategory()
	{
		System.out.println(p.getCategoryFromCategories());
	}
	
}
