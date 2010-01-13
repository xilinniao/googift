package cn.googift.crawler.data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import junit.framework.TestCase;

public class ProductTest extends TestCase {
	Product p;
	List<String> categories;
	List<String> resLinks;
	
	public void setUp()
	{
		p = new Product();
		categories = new ArrayList<String>();
		categories.add("aa1");
		categories.add("aa2");
		categories.add("aa3");
		p.setCategories(categories);
		
		
		resLinks = new LinkedList<String>();
		resLinks.add("http://www.baidu.com");
		resLinks.add("http://www.google.com");
		resLinks.add("http://www.aaa.com");
		p.setPicLinks(resLinks);
	}
	
	public void testGetResLink()
	{
		System.out.println(p.getResLinkFromResLinks());
	}
	
	public void testGetCategory()
	{
		System.out.println(p.getCategoryFromCategories());
	}
	
}
