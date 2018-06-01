$().ready(function () {
    load();
});

function load() {
    $.ajax({
        type: 'GET',
        url: '/B_Subject',
        success: function (result) {
            console.log(result);
        },
        error: function (xhr, status, error) {
            console.log(xhr.status);
        }

    })
}