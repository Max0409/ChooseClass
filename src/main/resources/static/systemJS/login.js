
$().ready(function () {

});

$('#login').click(
    function () {
        let s_id = $('#username').val();
        let password = $('#password').val();

        $.ajax({
            type: 'POST',
            url: '/login',
            data: {
                id: s_id,
                password: password
            },
            success: function (result) {
                console.log("success");
                location.reload();
            },
            error: function (xhr) {
                console.log(xhr);
            }

        })
    }
);