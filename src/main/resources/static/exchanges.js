$(function() {// 初始化内容
    alert("aaaa");
    $.ajax({
        type: 'POST',
        url: "/coin/24hr",
        //data: data,
        success: function (data) {
            let innerData = data.data;
            for(let i=1;i<innerData.length;i++){
                var content=$("#initTable").html()+createTr(i,innerData[i]);
                $("#initTable").html(content);
            }
        },
        //dataType: "";
    });
});

function createTr(num,obj){
    var line="<tr>";
    line+="<td>"+num+"</td>"
    line+="<td>"+obj.symbol+"</td>"
    line+="<td>"+obj.symbol+"</td>"
    line+="<td>"+obj.symbol+"</td>"
    line+="<td>"+obj.count+"</td>"
    line+="</tr>";
}