package asr.proyectoFinal.servlets;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.Buffer;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;
import com.ibm.watson.developer_cloud.text_to_speech.v1.util.WaveUtils;

import asr.proyectoFinal.dao.CloudantPalabraStore;
import asr.proyectoFinal.dominio.Palabra;
import asr.proyectoFinal.services.Traductor;
import asr.proyectoFinal.services.ScrapTweets;
import asr.proyectoFinal.services.TAnalysis;
import asr.proyectoFinal.services.Text2Speech;

/**
 * Servlet implementation class Controller
 */
@WebServlet(urlPatterns = { "/listar", "/insertar", "/tanalysis", "/texttospeech", "/getTweets" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(Controller.class.getName());

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CloudantPalabraStore store = new CloudantPalabraStore();
		// System.out.println(request.getServletPath());

		switch (request.getServletPath()) {
		case "/getTweets":
			request.setAttribute("tweet", "true");
			String username = request.getParameter("username");
			String tone = "";
			request.setAttribute("username", username);
			if (username == null)
				request.setAttribute("usernameNull", "True");
			Map<String, String> sctw = ScrapTweets.get_tweets(username);
			System.out.println(sctw);
			if (sctw != null) {

				for (Map.Entry<String, String> entry : sctw.entrySet()) {
					// System.out.println(entry.getKey() + "/" + entry.getValue());
					request.setAttribute(entry.getKey(), entry.getValue());
				}
			} else {
				request.setAttribute("map", "true");
			}
			// request.setAttribute("sctw", sctw);

			break;
		case "/listar":
			String listar = "True";
			if (store.getDB() != null)
				listar = "True";
			request.setAttribute("cloudant", store.getAll());
			/*
			 * else out.println("No hay DB");
			 */
			request.setAttribute("listar", listar);
			break;
		
		case "/tanalysis":
			String tanalysis = "True";
			String textanalysis = request.getParameter("text");
			String s = TAnalysis.get_sentiment(textanalysis);
			request.setAttribute("tanalysis", tanalysis);
			request.setAttribute("sentiment", s);
			break;

		}
		request.getRequestDispatcher("/info.jsp").forward(request, response);
	}

	private void close(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (IOException e) {
				// ignore
			}
		}
	}
}

/*
 * 
 * 
 * 
 * case "/insertar": //out = response.getWriter();
 * //out.println("<html><head><meta charset=\"UTF-8\"></head><body>"); Palabra
 * palabra = new Palabra(); String parametro = request.getParameter("palabra");
 * request.setAttribute("Palabra", palabra); String insertar = "True";
 * if(parametro==null) {
 * //out.println("usage: /insertar?palabra=palabra_a_traducir");
 * request.setAttribute("usage", "usage: /insertar?palabra=palabra_a_traducir");
 * } else { if(store.getDB() == null) { request.setAttribute("Palabra",
 * palabra); //out.println(String.format("Palabra: %s", palabra)); } else {
 * request.setAttribute("insertarSuccess",
 * String.format("Almacenada la palabra: %s", palabra.getName())); parametro =
 * Traductor.translate(parametro, "es", "en", false);
 * palabra.setName(parametro); store.persist(palabra);
 * //out.println(String.format("Almacenada la palabra-----: %s",
 * palabra.getName())); } } //out.println("asds`dksapkdsdaspdaspdsada</html>");
 * rd.forward(request, response); break;
 * 
 * case "/texttospeech":
 * 
 * String frase = request.getParameter("frase");
 * 
 * if(frase==null) { //out.println("usage: /text2speech?frase=frase"); } else {
 * //boolean download =
 * "true".equalsIgnoreCase(request.getParameter("download")); Boolean download =
 * true; InputStream in = null; try {
 * 
 * 
 * in = Text2Speech.toSpeech(frase, new Voice(), "audio/wav");
 * 
 * if (download) { response.setHeader("content-disposition",
 * "attachment; filename=transcript.wav"); }
 * 
 * outstream = response.getOutputStream(); byte[] buffer = new byte[2048]; int
 * read; while ((read = in.read(buffer)) != -1) { outstream.write(buffer, 0,
 * read); }
 * 
 * 
 * 
 * } catch (Exception e) {
 * 
 * // Log something and return an error message logger.log(Level.SEVERE,
 * "got error: " + e.getMessage());
 * response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage()); }
 * finally { close(in); close(outstream); }
 * 
 * }
 * 
 * break;
 * 
 * 
 * 
 * 
 */
