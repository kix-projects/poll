$(function() {

    $('body').on('loadPatials', function() {
        $('body').find('[data-include]').each(function(index, element) {
            var incluseStr = $(element).attr('data-include');
            $(element).removeAttr('data-include');
            incluseStr.split(',').forEach(function(partial) {
                $.ajax({
                    url: partial,

                    success: function(data){
                        $(element).append(data);
                        $('body').trigger('loadPatials');
                    }
                });
            });
        });
    });

    $('body').trigger('loadPatials');

});