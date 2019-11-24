package pl.put.poznan.transformer.logic;

public class Inverse extends TextTransformerDecorator{

	public Inverse(TransformInterface inner) {
		super(inner);
	}
	
	public String transform_text(String transform_object) {
		StringBuilder after_reverse=new StringBuilder(transform_object);
		after_reverse=after_reverse.reverse();
		transform_object=after_reverse.toString();
		
		return transform_object;
	}

}
