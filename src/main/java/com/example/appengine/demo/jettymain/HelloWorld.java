package com.example.appengine.demo.jettymain;



import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;

@WebServlet
public class HelloWorld extends HttpServlet {


//    public void handle(String target, Request baseRequest,
//                       HttpServletRequest request, HttpServletResponse response)
//            throws IOException, ServletException {
//        response.setContentType("text/html; charset=utf-8");
//        response.setStatus(HttpServletResponse.SC_OK);
//        response.getWriter().println("<h1>Welcome to Jetty Application</h1>");
//        baseRequest.setHandled(true);
//    }
@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    String iiif = request.getParameter("iiif");
    System.out.println("1.request param iiif is: "+iiif);
    String blackURLS = System.getenv("blackURLS");
    System.out.println("blackURLS: "+blackURLS);

        // Set response content type
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        String title = "Imagebox Server  ";
        String docType =
                "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor = \"#f0f0f0\">\n" +
                "<h1 align = \"center\">" + title + "</h1>\n" +
                "<ul>\n" +
                "  <li><b>URL</b>: "
                + request.getParameter("iiif") + "\n" +
                "  <li><b>blackURLS</b>: "
                + blackURLS + "\n" +
                "</ul>\n" +
                "</body>" +
                "</html>"
        );
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        context.addServlet(HelloWorld.class, "/iiif/*");
        server.setHandler(context);

    //  server.setHandler(new HelloWorld());
        server.start();
        System.out.println("Started: ");
        server.join();
    }
}


