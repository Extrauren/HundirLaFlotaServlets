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
 * Servlet implementation class NuevaPartidaServlet
 */
public class NuevaPartidaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NuevaPartidaServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	/**
     Este servlet se ejecutara cuando pulsemos el enlace “NuevaPartida” de las figuras
	 anteriores. El servlet obtendra la sesion actual y la invalidara, “borrando” ası cualquier atributo
	 guardado en la misma. Finalmente, se redirigira la ejecucion al servlet
	 HundirFlotaServlet, que hemos creado en un ejercicio anterior, para que cree y arranque una nueva partida.
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");    			//marcamos el tipo del contenido
		
		HttpSession sesion = request.getSession(true);
		sesion.removeAttribute("partida");					//removeAttribute, no getAttribute
		sesion.invalidate();								//invalidamos la sesion actual para crear otra
		
		//Mandamos a hundir la flota servlet pero como no hay una sesion la crea
		RequestDispatcher vista = request.getRequestDispatcher("HundirFlotaServlet"); 
		vista.forward(request, response);	
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
