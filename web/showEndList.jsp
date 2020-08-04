<%@ page import="domain.Commodity" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <style type="text/css">
            td{
                text-align : center;
            }
        </style>
    </head>
    <body>
        <table border="1" align="center" width="60%" height=""60%>
            <tr>
                <th>商品名称</th><th>商品单价</th>
            </tr>
            <%
                //HashMap<Commodity,int>    重写Commodity  equals和hashCode
                ArrayList<Commodity> shppingCar = (ArrayList<Commodity>)session.getAttribute("shoppingCar");
                float sumPrice = (float)request.getAttribute("sumPrice");
                for(Commodity commodity:shppingCar){
                    out.write("<tr>");
                    out.write("<td>"+commodity.getCname()+"</td>");
                    out.write("<td>"+commodity.getCprice()+"</td>");
                    out.write("</tr>");
                }
            %>
            <tr>
                <td colspan="2">总计:<%=sumPrice%></td>
            </tr>
        </table>
    </body>
</html>
