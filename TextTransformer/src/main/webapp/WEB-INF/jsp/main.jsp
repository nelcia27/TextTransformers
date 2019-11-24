<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="includes/header.jsp"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <h1>Text Transformer</h1>
<div class="card">
  <h5 class="card-header">TextTransformer card</h5>
  <div class="card-body">
    <form:form modelAttribute = "transformerObject">
    
	  <div class="form-group">
	    <form:label path = "transforms">Text to transform</form:label>
	    <form:input path = "transforms" type="transforms" class="form-control" id="transforms" aria-describedby="transformsHelp" placeholder="Enter transformations here"/>
	    <small id="transformsHelp" class="form-text text-muted">Please, enter here your transformations.</small>
	  </div>
	  <div class="form-group">
	    <form:label path = "textToChange">Text to transform</form:label>
	    <form:input path = "textToChange" type="textToChange" class="form-control" id="textToChange" aria-describedby="textHelp" placeholder="Enter text to transform here"/>
	    <small id="textHelp" class="form-text text-muted">Please, enter here your text.</small>
	  </div>
	  <button type="submit" class="btn btn-primary">Submit</button>
	</form:form>
  </div>
</div>
<%@ include file="includes/footer.jsp"%>