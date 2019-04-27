package asr.proyectoFinal.services;

import java.util.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class ScrapTweets {

	private static HttpURLConnection con;

	public static Map<String, String> get_tweets(String NombreUsuario) {

		String url = "https://eu-gb.functions.cloud.ibm.com/api/v1/namespaces/alu.comillas.edu_dev/actions/get_tweets?blocking=true&result=true";
		String usernameColonPassword = "3d9fda08-e508-42b8-bb24-efc75878c68e:FEtIy9LWQkvGe4CUYtw79ZUMnBnZLpQNaFQk5dqo69vQwNWJdhbNsOhUPE2KCSzf";
		String basicAuthPayload = "Basic " + Base64.getEncoder().encodeToString(usernameColonPassword.getBytes());

		Map<String, String> nombreMap = new HashMap<String, String>();

		try {

			URL myurl = new URL(url);
			con = (HttpURLConnection) myurl.openConnection();
			con.setDoOutput(true);

			con.addRequestProperty("Authorization", basicAuthPayload);
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestMethod("POST");

			OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());

			String pet = "{\"user\":\"" + NombreUsuario + "\"}";

			wr.write(pet);
			wr.flush();
			StringBuilder content;

			try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {

				String line;
				content = new StringBuilder();

				while ((line = in.readLine()) != null) {
					content.append(line);
					content.append(System.lineSeparator());
				}
			}
			System.out.println(content.toString());

			nombreMap = convertToMap(nombreMap, content.toString());

			// System.out.println(jsonObject.get("age"));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return nombreMap;
	}

	private static Map<String, String> convertToMap(Map<String, String> nombreMap, String content) {
		// TODO Auto-generated method stub
		int num = 0;
		int index = 0;
		for (int i = 0; i < 10; i++) {
			// GET ID USER
			index = content.indexOf('"', num);
			String idUser = content.substring(index + 1, content.indexOf('"', index + 2));
			System.out.println(idUser);
			num = content.indexOf('"', index + 2) + 1;
			
			//GET TWEET
			index = content.indexOf('"', num);
			String tweet = content.substring(index + 1, content.indexOf('"', index + 2));
			System.out.println(tweet);
			num = content.indexOf('"', index + 2) + 1;
			
			nombreMap.put("id"+i, idUser);
			nombreMap.put("tweet"+i, tweet);
		}

		System.out.println(content.substring(index + 1, content.indexOf('"', index + 2)));
		System.out.println(content.indexOf('"'));
		System.out.println(content.charAt(content.indexOf('"') + 1));
		//nombreMap.put("content", content);
		return nombreMap;
	}

	public static Object readJsonSimpleDemo(String filename) throws Exception {
		FileReader reader = new FileReader(filename);
		JSONParser jsonParser = new JSONParser();
		return jsonParser.parse(reader);
	}
}
