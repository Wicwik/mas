import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class A extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		String arg=req.getParameter("arg");
		int b = new Integer(arg);
		pw.println("a="+(MySystem.calculate()+b)+";");
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}