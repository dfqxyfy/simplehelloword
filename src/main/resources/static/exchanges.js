var page =1;
var limit=5;
var basContent;
$(function() {// 初始化内容
    //alert("aaaa");
    basContent=$("#initTable").html();
    req(page,limit);
});

function createTr(num,obj){
    var line="<tr>";
    line+="<td>"+num+"</td>"
    line+="<td>"+obj.symbol+"</td>"
    line+="<td>"+obj.symbol+"</td>"
    line+="<td>"+obj.symbol+"</td>"
    line+="<td>"+obj.weightedAvgPrice+"</td>"
    line+="<td>"+obj.count+"</td>"
    line+="</tr>";
    return line;
}

function prePage(){
    page--;
    if(page<=0){
        page=1;
    }
    req(page,limit);
}
function nextPage() {
    page++;
    req(page,limit);
}

function req(page,limit){
    var da={};
    da.page=page;
    da.limit=limit;
    $.ajax({
        type: 'GET',
        url: "/coin/24hr",
        dataType: "json",
        data: da,
        success: function (data) {
            $("#initTable").html(basContent);
            let innerData = data.data;
            for(let i=0;i<innerData.length;i++){
                var content=$("#initTable").html()+createTr(i,innerData[i]);
                $("#initTable").html(content);
            }
        }
    });
}