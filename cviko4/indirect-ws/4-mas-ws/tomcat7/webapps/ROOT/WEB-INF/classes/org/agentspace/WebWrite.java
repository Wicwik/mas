package org.agentspace;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

public class WebWrite extends HttpServlet {
  
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
  	Space space = Space.getInstance();
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
    Enumeration<String> parameterNames = req.getParameterNames();
    while (parameterNames.hasMoreElements()) {
      String key = (String) parameterNames.nextElement();
      String val = req.getParameter(key);
      String[] atts = val.split("\\s*,\\s*");
      String value = atts[0];
      double validity = 0;
      if (atts.length > 1)
        try {
          validity = new Double(atts[1]);
        } catch (NumberFormatException ee) {
        }
      float priority = 1.0f;
      if (atts.length > 2) 
        try {
          priority = new Float(atts[2]);
        } catch (NumberFormatException ee) {
        }
      if (validity == 0.0) space.write(key,value,priority);
      else space.write(key,value,validity,priority);
    }
		pw.println("ok"+"<BR/>");
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    doGet(req,res);
	}
	
}

//open url http://www.agentspace.org:8080/write?name=value,validity,priority&name=value,validity&name=value
//open url http://www.agentspace.org:8080/read?name&name&name
 