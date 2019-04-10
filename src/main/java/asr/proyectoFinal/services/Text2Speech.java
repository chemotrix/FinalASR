package asr.proyectoFinal.services;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.watson.developer_cloud.language_translator.v3.LanguageTranslator;
import com.ibm.watson.developer_cloud.language_translator.v3.model.TranslateOptions;
import com.ibm.watson.developer_cloud.language_translator.v3.model.TranslationResult;
import com.ibm.watson.developer_cloud.service.security.IamOptions;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.SynthesizeOptions;
import com.ibm.watson.developer_cloud.text_to_speech.v1.util.WaveUtils;

public class Text2Speech
{
	public static String text2Speech(String text)
	{
		
		//TextToSpeech asd = new TextToSpeech()
		IamOptions options = new IamOptions.Builder()
			    .apiKey("U67NKpgcFqf42ouUUcElJ5fVmWMgKemN44YSa3lPCKfr")
			    .build();

		
		LanguageTranslator languageTranslator = new LanguageTranslator("2019-04-09");
		languageTranslator.setUsernameAndPassword("f9517b48-edbb-4d41-a984-9333637a96bb","vdMRqsy1jOFV");

		languageTranslator.setEndPoint("https://gateway.watsonplatform.net/language-translator/api");
		
		return "0";
	}
}