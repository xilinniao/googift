package cn.googift.web;

import cn.googift.search.Searcher;
import cn.googift.crawler.data.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class QueryServlet extends HttpServlet {
    private Searcher searcher = new Searcher();
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher("result.jsp");
        String q = httpServletRequest.getParameter("q");
        System.out.println("q = " + q);
        List<Product> products = searcher.search(q, 0, 10);
        httpServletRequest.setAttribute("results", products);
        requestDispatcher.forward(httpServletRequest, httpServletResponse);
    }
}
