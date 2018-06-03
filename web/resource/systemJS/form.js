$().ready(function () {
    load();
});

function load() {
    $.ajax({
        type: 'GET',
        url: '/B_Subject',
        success: function (result) {
            console.log(result);
            for (let i = 0; i < result.length; i++) {
                let course = result[i];
                $('#college').append(
                    '<tr>' +
                    '<td>' + course.id + '</td>' +
                    '<td>' + course.name + '</td>' +
                    '<td>' + course.teacher + '</td>' +
                    '<td>' + course.time + '</td>' +
                    '<td>' + course.location + '</td>' +
                    '<td><button class="btn btn-link">选课</button></td>' +
                    '</tr>'
                );
            }

        },
        error: function (xhr, status, error) {
            console.log(xhr.status);
        }

    })
}