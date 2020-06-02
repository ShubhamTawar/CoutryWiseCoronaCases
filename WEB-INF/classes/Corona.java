//for this I have to put my jsoup jar file inside tomcat server

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class Corona extends HttpServlet{

         public  String getData(String c) throws Exception {
        StringBuffer br = new StringBuffer();

        br.append("<html>" +
                "<body style='text-align:center;color:red; background-color:gray;margin-top-200px'>");
        br.append(c.toUpperCase()+"<br>");

        String url = "https://www.worldometers.info/coronavirus/country/" + c + "/";
        Document doc = Jsoup.connect(url).get();
        //#maincounter-wrap
        Elements elements = doc.select("#maincounter-wrap");
        elements.forEach((e) -> {
            String text = e.select("h1").text();
            String count = e.select(".maincounter-number>span").text();
            br.append(text).append(count).append("<br>");
        });
        br.append("</body>" +
                "</html>");
        return br.toString();
    }

   public void doGet(HttpServletRequest req , HttpServletResponse res)throws ServletException , IOException {
   res.setContentType("text/html");
  PrintWriter pw = res.getWriter();
  String country = req.getParameter("country");
 try{
 pw.println(getData(country));
}
catch(Exception e){
  pw.println("Exception occured");
}
//pw.println("hello");
}}







