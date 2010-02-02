package cn.googift.web;

import cn.googift.search.Searcher;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QueryServlet extends HttpServlet {
    private Searcher searcher = new Searcher();

    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher("result.jsp");
        boolean advanced = (httpServletRequest.getParameter("advanced") != null);
        int totalNum;
        String q;
        System.out.println("advanced = " + advanced);
        if (advanced) {
            String name = httpServletRequest.getParameter("name");
            String lowPrice = httpServletRequest.getParameter("lowPrice");
            String highPrice = httpServletRequest.getParameter("highPrice");
            String keyword = httpServletRequest.getParameter("keyword");
            totalNum = searcher.search(name, lowPrice, highPrice, keyword);
            q = name != null ? name : keyword;
        } else {
            q = httpServletRequest.getParameter("q");
            totalNum = searcher.search(q);
        }

        httpServletRequest.setAttribute("q", q);
        httpServletRequest.setAttribute("totalHits", totalNum);
        httpServletRequest.setAttribute("searcher", searcher);
        requestDispatcher.forward(httpServletRequest, httpServletResponse);
    }
}
