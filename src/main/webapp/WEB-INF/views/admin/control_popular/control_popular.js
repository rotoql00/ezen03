/*
* FileName: control-popular.js
* Description: 상품판매현황을 위한 JS
* Author: 임석현, 임채린
* */

/*$(function () {
    $('select').niceSelect();
});


function submitEnter(e) {
    if (e.keyCode === 13) {

        document.getElementById('search').submit();
    }
}*/

//json 대괄호 따옴표 없애기
function trim(obj) {
	var a = $("#output_group1").val().replace(/\[|\]|"/gi, "");
	$("#output_group1").val(a);
}


document.addEventListener('DOMContentLoaded', function () {

    // First Example
    UIkit.util.on('#sortable1', 'moved', function () {
        var children 	= this.children;
        var num  		= this.children.length;
        var order		= [ ];

        for (var i = 0; i < num; i++) {
            order.push(children[i].id);
        }
        document.getElementById('output').value = JSON.stringify(order);
    });

    // Second Example with groups

    // Serialize Groups at StartUp
    ReOrder('group1');


    // Move Events
    UIkit.util.on('#group1', 'moved', function () {
        ReOrder(this.id);
    });
    UIkit.util.on('#group1', 'removed', function () {
        ReOrder(this.id);
    });
    UIkit.util.on('#group1', 'added', function () {
        ReOrder(this.id);
    });
    UIkit.util.on('#group2', 'moved', function () {
        ReOrder(this.id);
    });
    UIkit.util.on('#group2', 'removed', function () {
        ReOrder(this.id);
    });
    UIkit.util.on('#group2', 'added', function () {
        ReOrder(this.id);
    });

    function ReOrder(id){
        var el 			= document.getElementById(id),
            children 	= el.children,
            num  		= el.children.length,
            order		= [ ],
            output 	= 'output_'+id;

        console.log(el.id);

        if ( num > 0) {
            for (var i = 0; i < num; i++) {
                order.push(children[i].id);
            }
            document.getElementById(output).value = JSON.stringify(order);
        }
        else {
            document.getElementById(output).value = 'Empty';
        }
    }

});