package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	/*private static final long serialVersionUID = 1L;
       
    *//**
     * @see HttpServlet#HttpServlet()
     *//*
    public TestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
*/
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println(req.getParameterMap());
		System.out.println("do Post!");
		PrintWriter pwOut = resp.getWriter();
		pwOut.println("do Post!!!");
		pwOut.println(req.getParameterMap());
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map<String,String[]> m = req.getParameterMap();
		Iterator<String> it = m.keySet().iterator();
		String result ="두두두 get!!";
		
		while(it.hasNext()) {
			String key = it.next();
			result += key+":"+req.getParameter(key);
			
		}
		doProcess(resp,result+req.getParameterMap());
		/*System.out.println(req.getParameterMap());
		System.out.println("do get!");
		PrintWriter pwOut = resp.getWriter();
		pwOut.println("두두두 겟! !!!");
		pwOut.println(req.getParameterMap());*/
		
		
		
	}
	public void doProcess(HttpServletResponse resp, String writeStr) throws IOException{
		
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter pwOut= resp.getWriter();
		pwOut.print(writeStr);
		
	}

	

}
