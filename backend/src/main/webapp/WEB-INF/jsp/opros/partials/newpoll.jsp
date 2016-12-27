<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<div class="vp-newpoll">
    <h3>Добавить опрос</h3>

<form accept-charset="UTF-8" action="" class="form-horizontal" id="addPoll" method="post" enctype="multipart/form-data"> 
    <div class="vp-newpoll-title">
        <label for="vp-newpoll-title">Заголовок <sup>*</sup></label>
        <input id="vp-newpoll-title" type="text" value="" name="subject" />
    </div>

    <div class="vp-newpoll-category">
        <label for="vp-newpoll-category">Категория <sup>*</sup></label>
            <div>
                <select id="vp-newpoll-category" name="category">
                    <option value="sport">Спорт</option>
                    <option value="science">Наука и Техника</option>
                    <option value="politics">Политика</option>
                    <option value="economics">Экономика</option>
                    <option value="tourism">Отдых и Туризм</option>
                    <option value="family">Семья и Общество</option>
                    <option value="showb">Шоу-бизнес</option>
                    <option value="beauty">Красота</option>
                </select>
            </div>
    </div>

    <div class="vp-newpoll-text">
        <label for="vp-newpoll-text">Текст опроса</label>
        <textarea id="vp-newpoll-text" name="content"></textarea>
    </div>


    <div class="vp-newpoll-attachment-image">
        <label for="vp-attachment-image">Изображение:</label>
        <input id="vp-attachment-image" type="file" name="imageData" />
    </div>
    <div class="vp-newpoll-attachment-youtube">
        <label for="vp-attachment-youtube">Код ролика YouTube:</label>
        <input id="vp-attachment-youtube" type="text" name="youtube" />
    </div>


    <div class="vp-newpoll-answers">
        <label for="vp-newpoll-category">Варианты ответов <sup>*</sup></label>
        <ul class="optionlist">
            <li><input type="text" name="options" /></li>
            <li><input type="text" name="options" /></li>
            <li><input type="text" name="options" /><a href="#">&times;</a></li>
        </ul>
        <button class="vp-button vp-button-addanswer">+</button>
    </div>

    <div class="vp-newpoll-buttons">
        <button class="vp-button vp-button-viewpoll">Предпросмотр</button>
        <button class="vp-button vp-button-addpoll" type="submit">Добавить</button>
    </div>
</form>
	<jsp:include page="../../menu/includeScripts.jsp" />
		<script>
			$(function() {
				var max_fields      = 10; //maximum input boxes allowed
			    var wrapper         = $(".optionlist"); //Fields wrapper
			    var add_button      = $(".vp-button-addanswer"); //Add button ID
			    
			    var x = 1; //initlal text box count
			    $(add_button).click(function(e){ //on add input button click
			        e.preventDefault();
			        if(x < max_fields){ //max input box allowed
			            x++; //text box increment
			            $(wrapper).append('<li><input type="text" name="options"/><a href="#" class="icon-remove remove_field"></a></li>'); //add input box
			        }
			    });
			    
			    $(wrapper).on("click",".remove_field", function(e){ //user click on remove text
			        e.preventDefault(); $(this).parent('div').remove(); x--;
			    })
			});
		</script>
</div>