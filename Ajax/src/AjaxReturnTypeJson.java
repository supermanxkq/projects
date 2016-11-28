

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AjaxReturnType
 */
@WebServlet("/AjaxReturnType")
public class AjaxReturnTypeJson extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxReturnTypeJson() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out=response.getWriter();
		String username=request.getParameter("username");
		if(username.equals("aaa")){
			//一个json数据
			//out.print("{'res':'该用户不可以使用','name':'张三'}");
			out.print("[{'res':'该用户可以使用1','name':'李四1'}],[{'res':'该用户可以使用1','name':'李四1'}]");
		}else{
			//多个json数据
			out.print("[{'res':'该用户可以使用2','name':'李四2'}],[{'res':'该用户可以使用2','name':'李四2'}]");
		}
		out.close();
	}

}
