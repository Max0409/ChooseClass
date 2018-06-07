
$().ready(function () {
    check();
});

$('#login').click(
    function () {
        let s_id = $('#username').val();
        let password = $('#password').val();

        $.ajax({
            type: 'POST',
            url: '/user/login',
            data: {
                id: s_id,
                password: password
            },
            success: function (result) {
                $('#loginAndSign').hide();
                document.getElementById("user").innerText = s_id;
                $('#welcomeUser').show();
            },
            error: function (xhr) {
                console.log(xhr);
            }

        })
    }
);

function check() {
    $.ajax({
        type: 'POST',
        url: '/user/getLoginId',
        success: function (result) {
            console.log(result);
            if (result === null) {
                $('#loginAndSign').show();
                $('#welcomeUser').hide();
            } else {
                $('#loginAndSign').hide();
                document.getElementById("user").innerText = result;
                $('#welcomeUser').show();

            }
        },
        error: function (xhr) {
            console.log(xhr);
        }
    })
}