

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckUserName
 */
@WebServlet("/CheckUserName")
public class CheckUserName extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CheckUserName() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out=response.getWriter();
		String username=request.getParameter("username");
		if(username.equals("aaa")){
			out.print("GET:用户名重复");
		}else{
			out.print("GET:用户名可以使用");
		}
		out.close();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html; charset=utf-8");
		resp.setHeader("Cache-Control", "no-cache");
		PrintWriter out=resp.getWriter();
		String username=req.getParameter("username");
		if(username.equals("aaa")){
			out.print("POST:用户名重复");
		}else{
			out.print("POST:用户名可以使用");
		}
		out.close();
	}
}
