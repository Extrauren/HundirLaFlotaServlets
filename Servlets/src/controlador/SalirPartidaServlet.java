package controlador;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SalirPartidaServlet
 */
public class SalirPartidaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalirPartidaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    /**
     Este  servlet  se  ejecutara  cuando  pulsemos  el  enlace  ”Salir” de
	 las  figuras  anteriores.  El  servlet  ejecutara  el  mismo  codigo  que
	 NuevaPartidaServlet,  pero efectuara la redireccion final a la pagina de entrada del juego
	 index.html
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/hyml");    			//marcamos el tipo del contenido
		
		HttpSession sesion = request.getSession(true);
		sesion.setAttribute("partida", null);
		sesion.invalidate();								//invalidamos la sesion
		
		response.sendRedirect("index.html");
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
