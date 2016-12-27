<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<html lang="en">
	<jsp:include page="../menu/header.jsp" />

	<body>
		<jsp:include page="../menu/topMenu.jsp" />
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span10">
					<form accept-charset="UTF-8" action="" class="form-horizontal" id="addPoll" method="post">
						<legend>Create a Poll</legend>
						<div class="control-group string required">
							<label class="string required control-label" for="subject">
								<abbr title="required">*</abbr> Subject
							</label>
							<div class="controls">
								<input class="string required span12" id="subject" name="subject" type="text">
							</div>
						</div>
						<div class="control-group text required">
							<label class="text required control-label" for="content"> 
								<abbr title="required">*</abbr> Content
							</label>
							<div class="controls">
								<textarea class="text required span12" id="content" name="content" rows="5" ></textarea>
							</div>
						</div>
						<div class="control-group string required">
							<label class="string required control-label" for="tags">
								<abbr title="required">*</abbr> Options
							</label>
							<div class="controls">
								<div id="optionlist" class="optionlist">
								    <div><input type="text" name="options"><button class="btn add_field_button">Add<i class="icon-plus"></i></button></div>
								</div>
							</div>
						</div>						
						<div class="control-group string required">
							<label class="string required control-label" for="tags">
								<abbr title="required">*</abbr> Categories
							</label>
							<div class="controls">
								<c:forEach items="${poll.tags}" var="tag">
									<a href="<c:url value="/tags/view?name=${tag}"/>">${tag}</a>
								</c:forEach>
							</div>
						</div>
						<div class="form-actions">
							<input class="btn btn-primary" name="commit" type="submit" value="Create Poll">
							<a class="btn btn-danger" href="<c:url value="/"/>">Cancel</a> 
						</div>
					</form>
				</div><!--/span-->
				<jsp:include page="../menu/rightMenu.jsp" />
			</div><!--/row-->
			<hr>
			<footer>
				<p>&copy; Abzal 2014</p>
			</footer>
		</div><!--/.fluid-container-->
		<jsp:include page="../menu/includeScripts.jsp" />
		<script>
			$(function() {
				$('#addTag').live( 'click',
					function() {
						$( '<span id="tag"><input type="text" id="tags" size="10" name="tags" value="" placeholder="Input Tag" />'
							 + '<a href="#" id="remTag" class="icon-remove"></a></span>' 
						 ).appendTo('#taglist');
						return false;
					});
	
				$('#remTag').live('click', function() {
					$(this).parents('span').remove();
					return false;
				});
				
				var max_fields      = 10; //maximum input boxes allowed
			    var wrapper         = $(".optionlist"); //Fields wrapper
			    var add_button      = $(".add_field_button"); //Add button ID
			    
			    var x = 1; //initlal text box count
			    $(add_button).click(function(e){ //on add input button click
			        e.preventDefault();
			        if(x < max_fields){ //max input box allowed
			            x++; //text box increment
			            $(wrapper).append('<div><input type="text" name="options"/><a href="#" class="icon-remove remove_field"></a></div>'); //add input box
			        }
			    });
			    
			    $(wrapper).on("click",".remove_field", function(e){ //user click on remove text
			        e.preventDefault(); $(this).parent('div').remove(); x--;
			    })
			});
		</script>
</body>
</html>