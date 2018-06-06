$().ready(function () {
    load();
});

function load() {
    $.ajax({
        type: 'GET',
        url: '/user/B_Subject',
        success: function (result) {
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
                    '$("#choose_' + i + '").click(function() {' +
                    'chooseClass("' + course.id + '", ' + i + ')' +
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

function chooseClass(c_id, i) {
    console.log(i);
    let s_id = $("#user").val();
    $.ajax({
        type: 'POST',
        url: '/user/B_Subject',
        data: {
            s_id: s_id,
            c_id: c_id
        },
        success: function (result) {
            $('#choose_' + i).innerText = "退课";

        },
        error: function (xhr, status, error) {
            console.log(xhr.status);
        }
    })
}