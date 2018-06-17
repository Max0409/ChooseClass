function parseXML(xmlFile){
    // 创建解析XML后的DOM对象
    let xmlDoc = null;
    // 根据不同浏览器进行解析
    if(window.DOMParser){
        // 其他浏览器
        let parser = new DOMParser();
        xmlDoc = parser.parseFromString(xmlFile,"application/xml");
    }else{
        // IE浏览器
        xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
        xmlDoc.async = false;
        xmlDoc.loadXML(xmlFile);
    }
    return xmlDoc;
}

function checkChoice(s_id, c_id) {
    let isChoice = false;
    $.ajax({
        type: 'POST',
        url: '/user/isChoose',
        data: {
            s_id: s_id,
            c_id: c_id,
        },
        async: false,
        success: function (result) {
            console.log(result)
            isChoice = result;
        },
        error: function (xhr) {
            console.log(xhr);
        }
    });

    return isChoice;
}
