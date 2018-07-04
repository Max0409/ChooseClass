
//user login
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
                localStorage.setItem('user', s_id);
                window.location.href = "index.html";
            },
            error: function (xhr) {
                console.log(xhr);
            }

        })
    }
);


//manager login
$('#managerLogin').click(
    function () {
        let managerName = $('#managerName').val();
        let managerPass = $('#managerPass').val();

        $.ajax({
            type: 'POST',
            url: '/user/manager_login',
            data: {
                id: managerName,
                password: managerPass
            },
            success: function (result) {
                localStorage.setItem('user', managerName);
                window.location.href = "manager.html";
            },
            error: function (xhr) {
                console.log(xhr);
            }

        })
    }
);


function check() {
    if (localStorage.getItem('user') === null) {
        window.location.href = 'index.html';
    } else {
        document.getElementById('user').innerText = localStorage.getItem('user');
    }
}