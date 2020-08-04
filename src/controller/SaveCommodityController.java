package controller;

import domain.Commodity;
import service.CommodityService;
import util.MySpring;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class SaveCommodityController extends HttpServlet {

    private CommodityService service = MySpring.getBean("service.CommodityService");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.处理一下字符集
        request.setCharacterEncoding("UTF-8");
        //2.获取请求提交发送过来的信息
        //传递过来的flag 继续购物、结算清单
        String flag = request.getParameter("flag");
        //所有勾选商品的对应cid编号
        String[] cids = request.getParameterValues("cid");
        //3.去session范围内找寻购物车对象
        HttpSession session = request.getSession();
        ArrayList<Commodity> shoppingCar = (ArrayList<Commodity>)session.getAttribute("shoppingCar");
        if(shoppingCar==null){
            shoppingCar = new ArrayList<Commodity>();
            session.setAttribute("shoppingCar",shoppingCar);
        }
        //4.循环遍历每一个cid  通过cid找到对应的商品对象  对象存起来---集合ArrayList
        for(String cid:cids){
            Commodity commodity = service.selectOne(Integer.parseInt(cid));
            shoppingCar.add(commodity);
        }
        //4.根据传递过来的flag 进行判断
        if(flag.equals("继续购物")){//查询所有种类
            request.getRequestDispatcher("selectAllKind").forward(request,response);
        }else{
            request.getRequestDispatcher("calculatePrice").forward(request,response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
