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
			if (index != -1) {

				String idUser = content.substring(index + 1, content.indexOf('"', index + 2));
				num = content.indexOf('"', index + 2) + 1;

				// GET TWEET
				index = content.indexOf('"', num);
				String tweet = content.substring(index + 1, content.indexOf('"', index + 2));
				num = content.indexOf('"', index + 2) + 1;

				String pic = picTwit(tweet);
				tweet = formatTweet(tweet);

				System.out.println(idUser);
				System.out.println(tweet);
				System.out.println(pic);
				nombreMap.put("id" + i, idUser);
				nombreMap.put("tweet" + i, tweet);
				nombreMap.put("pic" + i, pic);

			}else {
				nombreMap = null;
				i=10;
			}

		}

		/*
		 * System.out.println(content.substring(index + 1, content.indexOf('"', index +
		 * 2))); System.out.println(content.indexOf('"'));
		 * System.out.println(content.charAt(content.indexOf('"') + 1));
		 */
		// nombreMap.put("content", content);
		return nombreMap;
	}

	private static String formatTweet(String tweet) {
		String regex = "(((pic).)+(([0-9a-z_-]+\\.)+(aero|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|ac|ad|ae|af|ag|ai|al|am|an|ao|aq|ar|as|at|au|aw|ax|az|ba|bb|bd|be|bf|bg|bh|bi|bj|bm|bn|bo|br|bs|bt|bv|bw|by|bz|ca|cc|cd|cf|cg|ch|ci|ck|cl|cm|cn|co|cr|cu|cv|cx|cy|cz|cz|de|dj|dk|dm|do|dz|ec|ee|eg|er|es|et|eu|fi|fj|fk|fm|fo|fr|ga|gb|gd|ge|gf|gg|gh|gi|gl|gm|gn|gp|gq|gr|gs|gt|gu|gw|gy|hk|hm|hn|hr|ht|hu|id|ie|il|im|in|io|iq|ir|is|it|je|jm|jo|jp|ke|kg|kh|ki|km|kn|kp|kr|kw|ky|kz|la|lb|lc|li|lk|lr|ls|lt|lu|lv|ly|ma|mc|md|me|mg|mh|mk|ml|mn|mn|mo|mp|mr|ms|mt|mu|mv|mw|mx|my|mz|na|nc|ne|nf|ng|ni|nl|no|np|nr|nu|nz|nom|pa|pe|pf|pg|ph|pk|pl|pm|pn|pr|ps|pt|pw|py|qa|re|ra|rs|ru|rw|sa|sb|sc|sd|se|sg|sh|si|sj|sj|sk|sl|sm|sn|so|sr|st|su|sv|sy|sz|tc|td|tf|tg|th|tj|tk|tl|tm|tn|to|tp|tr|tt|tv|tw|tz|ua|ug|uk|us|uy|uz|va|vc|ve|vg|vi|vn|vu|wf|ws|ye|yt|yu|za|zm|zw|arpa)(:[0-9]+)?((\\/([~0-9a-zA-Z\\#\\+\\%@\\.\\/_-]+))?(\\?[0-9a-zA-Z\\+\\%@\\/&\\[\\];=_-]+)?)?))\\b";
		tweet = tweet.replaceAll("\\\\n", " ");

		tweet = tweet.replaceAll("\\n", " ");

		tweet = tweet.replaceAll("\n", " ");

		tweet = tweet.replaceAll(regex, " ");

		return tweet;
	}

	private static String picTwit(String tweet) {
		String pic = " ";
		int index = tweet.indexOf("pic.twitter.com/", 0);
		if (index != -1) {
			pic = tweet.substring(index, index + 26);
		}
		System.out.println(pic);
		return pic;
	}

}
