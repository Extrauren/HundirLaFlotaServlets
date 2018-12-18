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
 * Servlet implementation class HundirLaFlotaServlet
 */
public class HundirLaFlotaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HundirLaFlotaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    
    
    /**Cargar la sesion o crearla si no existe
    *Si la sesion no contiene el atributo ’partida’
    *Crear la partida
    *sino
    *Obtener la partida en juego de la sesion
    *Obtener de la peticion la posicion del radio button pulsado
    *Usar el metodo del objeto Partida para disparar sobre la casilla elegida
    *Guardar el objeto Partida en la sesion
    *Redirigir la ejecucion al fichero JSP que construira la Vista a mostrar al usuario
    */
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/hyml");    			//marcamos el tipo del contenido
		
		HttpSession sesion = request.getSession(true);
		Partida partida = null;
		boolean marcada = false;
		
		
		if(sesion.getAttribute("partida")==null) {		
			
			partida = new Partida(8,8,6);			
			
		}else {												
			
			partida = (Partida) sesion.getAttribute("partida");		
			if(request.getParameter("casilla")!=null) {
				String coordenadas[] = request.getParameter("casilla").split("#"); 
				int fila = Integer.parseInt(coordenadas[0]);
				int col =  Integer.parseInt(coordenadas[1]);
				marcada = partida.casillaDisparada(fila, col);			//miramos en la matriz creada anteriormente 		
				partida.pruebaCasilla(fila, col);									
			}	
			
		}
		
		sesion.setAttribute("partida", partida);		
		request.setAttribute("marcada", marcada);		//devolvemos si se ha pulsado o no	
		
		RequestDispatcher vista = request.getRequestDispatcher("TableroActual.jsp");
		vista.forward(request, response);							
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
