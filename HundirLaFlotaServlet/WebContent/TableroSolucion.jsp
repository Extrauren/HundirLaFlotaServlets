<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="modelo.Partida"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Solucion</title>
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
	<h3>Solución Partida</h3>
	<%
		final int AGUA = -1;
		Partida partida = (Partida) request.getAttribute("partida");

		//Mensajes de control
		out.println("Barcos nabegando: " + partida.getBarcosRestantes() + "<br>");
		out.println("Barcos hundidos: " + (6 - partida.getBarcosRestantes()) + "<br>");
		out.println("Numero de disparos efectuados: " + partida.getDisparos() + "<br>");
		
		out.println("<form style=\"text-align:center\" method=\"get\" action=\"HundirFlotaServlet\">");

		//montamos el tablero resuelto

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

				if (partida.getCasilla(i, j) == AGUA) { //si se ha disparamos pintamos
					if (partida.getCasilla(i, j) == AGUA) { //dado que no hay mas opiones quito el switch por un if
						color = "azul"; //no hay nada pues agua
					}
				} else {
					color = "rojo"; //hay cosas pues rojo
				}

				out.println("<td id=\"" + color + "\"" + " </td>"); // radiobuttons required para el prueba casilla 
			}
			out.println("</tr>");
		}

		out.println("</table>"); // se acaba la tabla
		out.println("</form>");
	%>
	
	<br><a href="NuevaPartidaServlet">Nueva partida</a><br>
	<a href="SalirPartidaServlet">Salir</a><br>
	
	

</body>
</html>