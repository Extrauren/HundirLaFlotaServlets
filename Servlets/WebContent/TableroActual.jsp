<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="modelo.Partida"%>
<!-- Importamos la partida. (Comentario en HTML)-->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tabldero Actual</title>
<!-- Como ha de ser la tabla. (Comentario en HTML)-->
<style type="text/css">
table {
	width: 100%;
	heigth: 100%;
}

#blanco {
	background-color: white
}

#rojo {
	background-color: red
}

#naranja {
	background-color: orange
}

#azul {
	background-color: blue
}
</style>

</head>
<body>
	<h1>Hundir la Flota</h1>
	<h3>NUEVA PARTIDA</h3>
	<%
		//creamos la partida como en los servlets. La sacamos de la sesion

		session = request.getSession(true); //obtenemos la sesion
		Partida partida = (Partida) session.getAttribute("partida");

		final int TOCADO = -2, HUNDIDO = -3, AGUA = -1; //inicializamos los valores base
		int fila = -1, col = -1; //inicializamos fila y col por valores que nunca deverán tener

		//obtenemos los valores de fila y columna al pulsar una casilla
		if (request.getParameter("casilla") != null) {
			String vectorCasillas[] = request.getParameter("casilla").split("#");
			fila = Integer.parseInt(vectorCasillas[0]);
			col = Integer.parseInt(vectorCasillas[1]);
		}

		if (partida.getDisparos() == 0) {
			out.println("NUEVA PARTIDA<br>");
			//acabo la partida
		} else if (partida.getBarcosRestantes() == 0) {
			out.println("GAME OVER<br>");
			//Si ya se habia disparado casilla
		} else if ((boolean) request.getAttribute("marcada")) {
			out.println("Pagina de resultados del disparo en (" + (fila + 1) + "," + (char) (col + 65)
					+ "): Ya había sido disparada" + "<br>");
			//Primera vez disparo casilla
		} else {
			out.println("Pagina de resultados del disparo en (" + (fila + 1) + "," + (char) (col + 65) + "): Ok!<br>"); //no se puede usar c
		}
		
		//<br> para que no haga un salto de linea grande
		out.println("Barcos navegando: " + partida.getBarcosRestantes() + "<br>");
		out.println("Barcos hundidos: " + (6 - partida.getBarcosRestantes()) + "<br>");
		out.println("Numero de disparos efectuados: " + partida.getDisparos() + "<br>");

		out.println("<form style=\"text-align:center\" method=\"get\" action=\"HundirFlotaServlet\">");

		

		out.println("<table>"); //comienza la tabla

		out.println("<tr>");
		out.println("<td></td>"); //no necesitamos hacer un caso especial en el bucle si lo añadimo vacio aqui

		//camienza la constrccion

		char c = 'A';
		//dibuja la primera fila de la matriz * A B C D E F G H  
		for (int a = 0; a < 8; a++) {
			out.println("<th>" + c + "</th>");
			c++;
		}
		out.println("</tr>");
		String color = null;
		//crea el resto de la matriz, botones y las letras de las coordenadas
		for (int i = 0; i < 8; i++) {
			out.println("<tr>");
			out.println("<th>" + (i + 1) + "</th>");
			for (int j = 0; j < 8; j++) {

				if (partida.casillaDisparada(i, j)) { //si se ha disparamos pintamos
					switch (partida.getCasilla(i, j)) {
					case AGUA:
						color = "azul";
						break;
					case TOCADO:
						color = "naranja";
						break;
					case HUNDIDO:
						color = "rojo";
						break;
					}

				} else { ///si no ha sido disparada pintamos blanco
					color = "blanco";
				}

				out.println("<td id=\"" + color + "\"" + "><input type=\"radio\" name=\"casilla\" value=\"" + i
						+ "#" + j + "\"required> </td>"); // radiobuttons required para el prueba casilla 
				
			}
			out.println("</tr>");
		}

		out.println("</table>"); // se acaba la tabla										
		out.println("<input type=\"submit\" value=\"Prueba casilla\">");
		out.println("</form>");
	%>
	<br>
	<a href="/HundirLaFlotaServlet/SolucionPartidaServlet">Muestra
		solucion</a>
	<br>
	<a href="/HundirLaFlotaServlet/NuevaPartidaServlet">Nueva partida</a>
	<br>
	<a href="/HundirLaFlotaServlet/SalirPartidaServlet">Salir</a>
	<br>

</body>
</html>