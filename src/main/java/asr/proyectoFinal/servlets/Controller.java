package asr.proyectoFinal.servlets;

import java.io.IOException;

import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import asr.proyectoFinal.dao.CloudantTweetStore;

import asr.proyectoFinal.services.ScrapTweets;
import asr.proyectoFinal.services.TAnalysis;

/**
 * Servlet implementation class Controller
 */
@WebServlet(urlPatterns = { "/listar", "/insertar", "/tanalysis", "/texttospeech", "/getTweets" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		CloudantTweetStore store = new CloudantTweetStore();
		// System.out.println(request.getServletPath());

		switch (request.getServletPath()) {
		case "/getTweets":
			request.setAttribute("tweet", "true");
			String username = request.getParameter("username");

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

			break;
		case "/listar":
			String listar = "False";
			String tmp = "-";
			if (store.getDB() != null) {
				listar = "True";
				tmp = store.getAll().toString();
				request.setAttribute("cloudant", store.getAll());
			}
			/*
			 * else out.println("No hay DB");
			 */
			request.setAttribute("listar", listar);
			System.out.println(tmp);
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

}
