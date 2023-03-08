package org.agentspace;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

public class WebRead extends HttpServlet {
  
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
  	Space space = Space.getInstance();
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
    Enumeration<String> parameterNames = req.getParameterNames();
    while (parameterNames.hasMoreElements()) {
      String key = (String) parameterNames.nextElement();
      String value = (String) space.read(key);
      pw.println(key+"="+value+"<BR>");
    }
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    doGet(req,res);
	}
	
}

//open url http://www.agentspace.org:8080/write?name=value,validity,priority&name=value,validity&name=value
//open url http://www.agentspace.org:8080/read?name&name&name
 