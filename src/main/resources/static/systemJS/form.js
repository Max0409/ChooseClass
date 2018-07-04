$().ready(function () {
    // check();
    $('#own').click();
});


$('#own').click(function () {
    $('#own').addClass("active-menu");
    $('#other').removeClass("active-menu");
    $('#view').text("专业选课");
    $('#share').hide();
    $('#college').empty();
    load();
});

$('#other').click(function () {
    $('#other').addClass("active-menu");
    $('#own').removeClass("active-menu");
    $('#view').text("跨院系选课");
    $('#share').show();
    $('#college').empty();
});

$('#check').click(function () {
    let dep = $('input[name=dep]:checked').val();

    getShareCourses(dep);

});

//加载所有课程信息
function load() {
    $.ajax({
        type: 'GET',
        url: '/user/B_Subject',
        data: {
            s_id: localStorage.getItem("user"),
        },
        success: function (result) {
            let s_id = $("#user").val();
            let resultList = parseXML(result).getElementsByTagName("b:课程");
            for (let i = 0; i < resultList.length; i++) {
                let c_id = resultList[i].getElementsByTagName("b:编号")[0].firstChild.nodeValue;
                if (checkChoice(s_id, c_id)) {
                    $('#college').append(
                        '<tr>' +
                        '<td>' + resultList[i].getElementsByTagName("b:编号")[0].firstChild.nodeValue + '</td>' +
                        '<td>' + resultList[i].getElementsByTagName("b:名称")[0].firstChild.nodeValue + '</td>' +
                        '<td>' + resultList[i].getElementsByTagName("b:课时")[0].firstChild.nodeValue + '</td>' +
                        '<td>' + resultList[i].getElementsByTagName("b:学分")[0].firstChild.nodeValue + '</td>' +
                        '<td>' + resultList[i].getElementsByTagName("b:老师")[0].firstChild.nodeValue + '</td>' +
                        '<td>' + resultList[i].getElementsByTagName("b:地点")[0].firstChild.nodeValue + '</td>' +
                        '<td>' +
                        '<label id="chosen_' + i + '">已选择</label>' +
                        '</td>' +
                        '</tr>'
                    );
                } else {
                    $('#college').append(
                        '<tr>' +
                        '<td>' + resultList[i].getElementsByTagName("b:编号")[0].firstChild.nodeValue + '</td>' +
                        '<td>' + resultList[i].getElementsByTagName("b:名称")[0].firstChild.nodeValue + '</td>' +
                        '<td>' + resultList[i].getElementsByTagName("b:课时")[0].firstChild.nodeValue + '</td>' +
                        '<td>' + resultList[i].getElementsByTagName("b:学分")[0].firstChild.nodeValue + '</td>' +
                        '<td>' + resultList[i].getElementsByTagName("b:老师")[0].firstChild.nodeValue + '</td>' +
                        '<td>' + resultList[i].getElementsByTagName("b:地点")[0].firstChild.nodeValue + '</td>' +
                        '<td>' +
                        '<button class="btn btn-link" id="choose_' + i + '">选课</button>' +
                        '</td>' +
                        '</tr>' +
                        '<script>' +
                        '$("#choose_' + i + '").click(function() {' +
                        'chooseClass("' + resultList[i].getElementsByTagName("b:编号")[0].firstChild.nodeValue + '")' +
                        '});' +
                        '</script>'
                    );
                }
            }
        },
        error: function (xhr, status, error) {
            console.log(xhr.status);
        }

    })
}


//获取共享课程
function getShareCourses(dep) {
    $.ajax({
        type: 'GET',
        url: '/user/getUserShareCourses',
        data: {
            sId: localStorage.getItem("user"),
            dep: dep
        },
        success: function (result) {
            let s_id = $("#user").val();
            let resultList = parseXML(result).getElementsByTagName("b:课程");
            for (let i = 0; i < resultList.length; i++) {
                let c_id = resultList[i].getElementsByTagName("b:编号")[0].firstChild.nodeValue;
                if (checkChoice(s_id, c_id)) {
                    $('#college').append(
                        '<tr>' +
                        '<td>' + resultList[i].getElementsByTagName("b:编号")[0].firstChild.nodeValue + '</td>' +
                        '<td>' + resultList[i].getElementsByTagName("b:名称")[0].firstChild.nodeValue + '</td>' +
                        '<td>' + resultList[i].getElementsByTagName("b:课时")[0].firstChild.nodeValue + '</td>' +
                        '<td>' + resultList[i].getElementsByTagName("b:学分")[0].firstChild.nodeValue + '</td>' +
                        '<td>' + resultList[i].getElementsByTagName("b:老师")[0].firstChild.nodeValue + '</td>' +
                        '<td>' + resultList[i].getElementsByTagName("b:地点")[0].firstChild.nodeValue + '</td>' +
                        '<td>' +
                        '<label id="chosen_' + i + '">已选择</label>' +
                        '</td>' +
                        '</tr>'
                    );
                } else {
                    $('#college').append(
                        '<tr>' +
                        '<td>' + resultList[i].getElementsByTagName("b:编号")[0].firstChild.nodeValue + '</td>' +
                        '<td>' + resultList[i].getElementsByTagName("b:名称")[0].firstChild.nodeValue + '</td>' +
                        '<td>' + resultList[i].getElementsByTagName("b:课时")[0].firstChild.nodeValue + '</td>' +
                        '<td>' + resultList[i].getElementsByTagName("b:学分")[0].firstChild.nodeValue + '</td>' +
                        '<td>' + resultList[i].getElementsByTagName("b:老师")[0].firstChild.nodeValue + '</td>' +
                        '<td>' + resultList[i].getElementsByTagName("b:地点")[0].firstChild.nodeValue + '</td>' +
                        '<td>' +
                        '<button class="btn btn-link" id="choose_' + i + '">选课</button>' +
                        '</td>' +
                        '</tr>' +
                        '<script>' +
                        '$("#choose_' + i + '").click(function() {' +
                        'chooseClass("' + resultList[i].getElementsByTagName("b:编号")[0].firstChild.nodeValue + '")' +
                        '});' +
                        '</script>'
                    );
                }
            }
        },
        error: function (xhr, status, error) {
            console.log(xhr.status);
        }

    })
}

//选课
function chooseClass(c_id) {
    let s_id = localStorage.getItem('user');
    $.ajax({
        type: 'POST',
        url: '/user/chooseCourse',
        data: {
            sId: s_id,
            cId: c_id
        },
        success: function (result) {
            if (result) {
                location.reload();
            } else {
              alert("已选择该课程，不要重复选课");
            }
        },
        error: function (xhr, status, error) {
            console.log(xhr.status);
        }
    })
}