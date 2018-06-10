
$('#person_info_btn').click(function () {

    document.getElementById("view").innerText = "我的信息";

    $('#person_info').show();
    $('#course_table').hide();

});


function loadPersonalClass() {
    let s_id = $("#user").val();
    $.ajax({
        type: 'POST',
        url:'/user/',
        data:{
            s_id: s_id
        },
        success: function (result) {

        },
        error: function (xhr) {
            console.log(xhr);
        }
    })
}

$().ready(function () {
    check();
    loadPersonalClass();
});


function deleteSubject(s_id, c_id) {
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