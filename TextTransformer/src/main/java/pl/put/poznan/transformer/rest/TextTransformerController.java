package pl.put.poznan.transformer.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import pl.put.poznan.transformer.logic.TextTransformer;


@Controller
@RequestMapping("/{text}")
public class TextTransformerController {

    private static final Logger logger = LoggerFactory.getLogger(TextTransformerController.class);
    
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public String get(@PathVariable String text,
                              //@RequestParam(value="transforms", defaultValue="upper,escape") String[] transforms, 
                              Model model) {
    	
    	TextTransformer transformer = new TextTransformer();
    	JSONParser parser = new JSONParser();
    	 try {//load transformations and text from json
             Object obj = parser.parse(new FileReader("transformData.txt"));
             JSONObject jsonObject = (JSONObject) obj;
  
             transformer.setTextToChange((String) jsonObject.get("textToChange"));
             logger.debug("GET Method");
             logger.debug(transformer.getTextToChange());
             JSONArray transformsList = (JSONArray) jsonObject.get("Transforms List");
             String transformString = transformsList.toString().replace("[", "");
             transformString = transformString.toString().replace("]", "");
             transformString = transformString.toString().replace("\"", "");
             transformer.setTransforms(transformString.replace("transform: ", "").split(","));
             for(String a: transformer.getTransforms()) {
            	 logger.debug(a);
             }
             logger.debug("2");
         } catch (Exception e) {
             e.printStackTrace();
         }
        //logger.debug(Arrays.toString(transforms));
    	 
        // do the transformation, you should run your logic here, below just a silly example
        //Tutaj zróbcie swoje transformacje z obiektem transformer. W nim są zapisane transformacje do wykonania. 
    	 //Format w jakim zapiszecie transformacje zostawiam wam(muszą być tylko wypisywane po przecinku
        transformer.transform(transformer.getTextToChange());
		model.addAttribute("transformerObject", transformer);//transformer will go to variable transformerObject in main.jsp
        return "main";
    }

    @SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public String post(@ModelAttribute("transformerObject")TextTransformer transformer,
                      Model model) {

    	//save transformations and text to jsonObject
    	logger.debug("POST method");
    	JSONObject obj = new JSONObject();
    	obj.put("textToChange", transformer.getTextToChange());
    	JSONArray transformsList = new JSONArray();
    	
        for (String a : transformer.getTransforms()) {
        	transformsList.add("transform: " + a);
        	logger.debug(a);
        }
        obj.put("Transforms List", transformsList);
        logger.debug(transformer.getTextToChange());
        
        try (FileWriter file = new FileWriter("transformData.txt")) {//save transformations and text to json file
			file.write(obj.toJSONString());
			//System.out.println("Successfully Copied JSON Object to File...");
			//System.out.println("\nJSON Object: " + obj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "redirect:/hello";
    }



}


