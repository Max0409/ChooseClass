$().ready(function () {
    check();
    loadPersonalClass();
});


$('#person_info_btn').click(function () {

    document.getElementById("view").innerText = "我的信息";

    $('#person_info').show();
    $('#course_table').hide();

});

//加载个人课程
function loadPersonalClass() {
    let s_id = $("#user").val();
    $.ajax({
        type: 'POST',
        url:'/user/B_Subject',
        data:{
            s_id: s_id
        },
        async: false,
        dataType: 'text',
        success: function (result) {
            let resultList = parseXML(result).getElementsByTagName("b:课程");
            console.log(resultList);
            for (let i = 0; i < resultList.length; i++) {
                let c_id = resultList[i].getElementsByTagName("b:编号")[0].firstChild.nodeValue;
                if (checkChoice(s_id, c_id)) {
                    $('#course_table').append(
                        '<tr>' +
                        '<td>' + resultList[i].getElementsByTagName("b:编号")[0].firstChild.nodeValue + '</td>' +
                        '<td>' + resultList[i].getElementsByTagName("b:名称")[0].firstChild.nodeValue + '</td>' +
                        '<td>' + resultList[i].getElementsByTagName("b:课时")[0].firstChild.nodeValue + '</td>' +
                        '<td>' + resultList[i].getElementsByTagName("b:学分")[0].firstChild.nodeValue + '</td>' +
                        '<td>' + resultList[i].getElementsByTagName("b:老师")[0].firstChild.nodeValue + '</td>' +
                        '<td>' + resultList[i].getElementsByTagName("b:地点")[0].firstChild.nodeValue + '</td>' +
                        '<td>' +
                        '<button class="btn btn-link" id="choose_'+ i +'">退课</button>' +
                        '</td>' +
                        '</tr>' +
                        '<script>' +
                        '$("#choose_' + i + '").click(function() {' +
                        'deleteSubject("' + resultList[i].getElementsByTagName("b:编号")[0].firstChild.nodeValue + '")' +
                        '});' +
                        '</script>'
                    );
                }
            }
        },
        error: function (xhr) {
            console.log(xhr);
        }
    })
}


function deleteSubject(c_id) {
    let s_id = $("#user").val();
    $.ajax({
        type: 'POST',
        url: '/user/deleteSubject',
        data: {
            s_id: s_id,
            c_id: c_id
        },
        success: function (result) {
            if (result) {
                location.reload();
            } else {
                alert("退课失败");
            }
        },
        error: function (xhr) {
            console.log(xhr);
        }
    })
}