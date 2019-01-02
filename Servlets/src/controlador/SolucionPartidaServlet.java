package controlador;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Partida;

/**
 * Servlet implementation class SolucionPartidaServlet
 */
public class SolucionPartidaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SolucionPartidaServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */


	/**Este servlet se ejecutara cuando pulsemos el enlace “Muestra solucion” de
    la figura anterior. El servlet no modificara el estado del modelo (la partida), sino que redirigira
    la ejecucion al fichero
    TableroSolucion.jsp
    que mostrara la solucion de la partida.
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/hyml");    			//marcamos el tipo del contenido
		HttpSession sesion = request.getSession(true);
		Partida partida = (Partida) sesion.getAttribute("partida");
		sesion.invalidate();								//Al solucionar la partida la invalidamos para evitar trampas

		request.setAttribute("partida", partida);     		// setAtribute(name, object);
		
		RequestDispatcher vista = request.getRequestDispatcher("TableroSolucion.jsp"); //dirigimos al tablero solucion
		vista.forward(request, response);					

		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
