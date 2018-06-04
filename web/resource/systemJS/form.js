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
                    '<td><button class="btn btn-link" id="choose_'+ i +'">选课</button></td>' +
                    '</tr>' +
                    '<script>' +
                    'let s_id = $("#user").val();' +
                    '$("#choose_"' + i + ').click(function() {' +
                    'chooseClass(s_id, course.id)' +
                    '});' +
                    '</script>'
                );
            }

        },
        error: function (xhr, status, error) {
            console.log(xhr.status);
        }

    })
}

function chooseClass(s_id, c_id) {
    $.ajax({
        type: 'POST',
        url: '/chooseSubject',
        data: {
            s_id: s_id,
            c_id: c_id
        },
        success: function (result) {

        },
        error: function (xhr, status, error) {
            console.log(xhr.status);
        }
    })
}