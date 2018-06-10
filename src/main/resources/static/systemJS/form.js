$().ready(function () {
    load();
    check();
});

//加载所有课程信息
function load() {
    $.ajax({
        type: 'POST',
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
                    '<td>' +
                    '<button class="btn btn-link" id="choose_'+ i +'">选课</button>' +
                    '<label id="chosen_' + i + '" style="display: none">已选择</label>' +
                    '</td>' +
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

//选课
function chooseClass(c_id, i) {
    let s_id = $("#user").val();
    $.ajax({
        type: 'POST',
        url: '/user/B_Subject',
        data: {
            s_id: s_id,
            c_id: c_id
        },
        success: function (result) {
            if (result) {
                $('#chosen_' + i).show();
                $('#choose_' + i).hide();
            } else {
              alert("已选择该课程，不要重复选课");
            }
        },
        error: function (xhr, status, error) {
            console.log(xhr.status);
        }
    })
}