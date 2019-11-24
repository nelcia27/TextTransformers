package pl.put.poznan.transformer.logic;

abstract class TextTransformerDecorator implements TransformInterface {
	public String transform_object;
	private TransformInterface core;
	
	public TextTransformerDecorator(TransformInterface inner) {
        core = inner;
    }

    public String transform_text(String transform_object) {
        core.transform_text(transform_object);
        return transform_object;
    }
}
