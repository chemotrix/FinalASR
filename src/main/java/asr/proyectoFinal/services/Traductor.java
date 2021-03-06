package asr.proyectoFinal.services;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.watson.developer_cloud.language_translator.v3.LanguageTranslator;
import com.ibm.watson.developer_cloud.language_translator.v3.model.TranslateOptions;
import com.ibm.watson.developer_cloud.language_translator.v3.model.TranslationResult;
import com.ibm.watson.developer_cloud.service.security.IamOptions;


public class Traductor
{
	public static String translate(String palabra, String sourceModel, String destModel, boolean conversational)
	{
		String traduccionPrimera = new String();
		try {
			String model;
			if(sourceModel.equals("en") || destModel.equals("en"))
			{
				model=sourceModel+"-"+destModel;
				if(conversational) 
					model+="-conversational";
			}
			else
				return translate(translate(palabra,sourceModel,"en",conversational),"en",destModel,conversational); //translate to english, then to dest
	
			//LanguageTranslator languageTranslator = new LanguageTranslator("2019-04-09");
			//languageTranslator.setUsernameAndPassword("f9517b48-edbb-4d41-a984-9333637a96bb","vdMRqsy1jOFV");
			LanguageTranslator service = new LanguageTranslator("2019-04-09");
	
			IamOptions iamOptions = new IamOptions.Builder()
				.apiKey("G9wJTWrKgssyVKv_d9Gbx8H5yi80qV-dnm7L6rqw1uMx")
				.build();
	
			service.setIamCredentials(iamOptions);
			
			service.setEndPoint("https://gateway-lon.watsonplatform.net/language-translator/api");
			
			TranslateOptions translateOptions = new 
			TranslateOptions.Builder() 
			 .addText(palabra)
			 .modelId(model)
			 .build();
	
			TranslationResult translationResult = service.translate(translateOptions).execute();
			
			System.out.println(translationResult);
	
			String traduccionJSON = translationResult.toString();
			JsonParser parser = new JsonParser();
			JsonObject rootObj =
			parser.parse(traduccionJSON).getAsJsonObject();
			JsonArray traducciones = rootObj.getAsJsonArray("translations");
			traduccionPrimera = palabra;
			if(traducciones.size()>0)
			traduccionPrimera = traducciones.get(0).getAsJsonObject().get("translation").getAsString();
		} catch (Exception e) {
			traduccionPrimera = "ERROR";
			System.out.println(">>>>>><<<<<<");
			e.printStackTrace();
			// TODO: handle exception
		}
		return traduccionPrimera;
	}
}