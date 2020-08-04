package controller;

import domain.Commodity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class CalculatePriceController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取购物车对象
        HttpSession session = request.getSession();
        ArrayList<Commodity> shoppingCar = (ArrayList<Commodity>) session.getAttribute("shoppingCar");
        //计算价格
        float sumPrice = 0;
        for (Commodity commodity : shoppingCar) {
            sumPrice += commodity.getCprice();
        }
        //将计算好的价钱存起来
        request.setAttribute("sumPrice", sumPrice);
        //转发做最后列表展示
        request.getRequestDispatcher("showEndList.jsp").forward(request, response);
    }
}
