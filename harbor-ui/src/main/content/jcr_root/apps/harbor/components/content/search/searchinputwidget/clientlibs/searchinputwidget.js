jQuery(document).ready(function ($) {

    //TODO: Look into ensuring behavior is attached as the widget is edited in author mode
    $('form[data-form-purpose="search"]').submit(function (e) {
        var currentForm = $(this);

        if (currentForm.data('submission-type') === 'asynchronous') {
            e.preventDefault();

            var searchPage = currentForm.attr('action');
            var queryString = $('input[name="query"]', this).val();

            if (searchPage !== '#') {
                searchPage = searchPage + '#';
            }

            window.location = searchPage + queryString;
        }
    })
});