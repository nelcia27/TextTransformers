package pl.put.poznan.transformer.logic;
import java.util.Arrays; 

/**
 * This is just an example to show that the logic should be outside the REST service.
 */
public class TextTransformer {

	private String textToChange;
    private String[] transforms;
    public TextTransformer(){
    }
    public TextTransformer(String[] transforms){
        this.setTransforms(transforms);
    }

    public String transform(String text){
    	TransformInterface t = new Inverse(new TextTransformerDecoratorHelper() );
    	//zrobic listę mozliwych transformacji
    	if(Arrays.asList(this.transforms).contains("inverse")) {
    		text=t.transform_text(text); 
    		//tu wybrać odpowiedni element z listy i na nim wywołać text_transform
    	}
    		
       //System.out.println(text);
    	this.textToChange=text;
        return text;
    }

	public String[] getTransforms() {
		return transforms;
	}

	public void setTransforms(String[] transforms) {
		this.transforms = transforms;
	}
	public String getTextToChange() {
		return textToChange;
	}
	public void setTextToChange(String textToChange) {
		this.textToChange = textToChange;
	}
}
