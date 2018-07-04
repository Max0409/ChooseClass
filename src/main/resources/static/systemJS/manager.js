$().ready(function () {
    // check();
    $('#all').click();
});

$('#add').click(function () {
    $('#course_table').hide();
    $('#course_info').show();

    $('#add').addClass("active-menu");
    $('#all').removeClass("active-menu");
    $('#view').text("添加课程");

});

$('#all').click(function () {
    $('#course_table').show();
    $('#course_info').hide();

    $('#all').addClass("active-menu");
    $('#add').removeClass("active-menu");
    $('#view').text("全部课程");
    loadAllCourse();
});

$('#addCourse').click(function () {
    addCourse();
});

function addCourse() {
    let name = $('#name').val();
    let time = parseInt($('#period').val());
    let score = parseInt($('#credit').val());
    let teacher = $('#teacher').val();
    let location = $('#location').val();
    let share = $('input[name="share"]:checked').val();

    
    $.ajax({
        type: 'POST',
        url: '/user/managerAddSubject',
        data: {
            name: name,
            time: time,
            score: score,
            teacher: teacher,
            location: location,
            share: share
        },
        success: function (result) {
            console.log(result);
            if (result === "true") {
                alert("添加成功！");
                window.location.reload();
            } else {
                alert("添加失败！请查看信息填写是否有错");
            }

        },
        error: function (xhr) {
            console.log(xhr.status);
            console.log(xhr.error);
        }
    })
}


function loadAllCourse() {
    $.ajax({
        type: 'POST',
        url: '/user/B_Subject',
        data: {
            s_id: localStorage.getItem("user")
        },
        dataType: 'text',
        success: function (result) {
            let resultList = parseXML(result).getElementsByTagName("b:课程");
            for (let i = 0; i < resultList.length; i++) {
                let share = "";
                let color = "lightskyblue";
                if (resultList[i].getElementsByTagName("b:共享")[0].firstChild.nodeValue === "Y") {
                    share = "已共享";
                } else {
                    share = "未共享";
                    color = "gray";
                }
                $('#course_table').append(
                    '<tr>' +
                    '<td>' + resultList[i].getElementsByTagName("b:编号")[0].firstChild.nodeValue + '</td>' +
                    '<td>' + resultList[i].getElementsByTagName("b:名称")[0].firstChild.nodeValue + '</td>' +
                    '<td>' + resultList[i].getElementsByTagName("b:课时")[0].firstChild.nodeValue + '</td>' +
                    '<td>' + resultList[i].getElementsByTagName("b:学分")[0].firstChild.nodeValue + '</td>' +
                    '<td>' + resultList[i].getElementsByTagName("b:老师")[0].firstChild.nodeValue + '</td>' +
                    '<td>' + resultList[i].getElementsByTagName("b:地点")[0].firstChild.nodeValue + '</td>' +
                    '<td><label style="color: '+ color +'">'+ share +'</label></td>' +
                    '<td>' +
                    '<button class="btn btn-link" id="delete_' + i + '">删除</button>' +
                    '</td>' +
                    '</tr>' +
                    '<script>' +
                    '$("#delete_' + i + '").click(function() {' +
                    'deleteCourse("' + resultList[i].getElementsByTagName("b:编号")[0].firstChild.nodeValue + '")' +
                    '});' +
                    '</script>'
                );
            }


        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest.status);
            console.log(XMLHttpRequest.readyState);
        }

    })
}

function deleteCourse(cno) {
    $.ajax({
        type: 'GET',
        url: '/user/managerDeleteSubject',
        data: {
            id: cno
        },
        success: function (result) {
            if (result) {
                location.reload();
            } else {
                alert("请勿重复删除！");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest.status);
            console.log(XMLHttpRequest.readyState);
        }
    })
}