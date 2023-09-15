
//function go(page){
//	const limit = $('#viewcount').val();
//	/* const data = 'limit=${limit}&state=ajax&page=${page}';*/
//	const data = {limit: limit, state:"ajax", page: page}
//	ajax(data);
//}




//총 2페이지 페이징 처리된 경우
//이전 1 2 다음
//현재 페이지가 1페이지인 경우 아래와 같은 페이징 코드가 필요
//<li class="page-item"><a class="page-link gray">이전&nbsp;</a></li>
//<li class="page-item active"><a class="page-link">1</a></li>
//<li class="page-item"><a class="page-link" href="javascript:go(2)">2</a></li>
//<li class="page-item"><a class="page-link" href="javascript:go(2)">다음&nbsp;</a></li>

//현재 페이지가 2페이지인 경우 아래와 같은 페이징 코드가 필요
//<li class="page-item"><a class="page-link" href="javascript:go(1)">이전&nbsp;</a></li>
//<li class="page-item"><a class="page-link" href="javascript:go(1)">1</a></li>
//<li class="page-item active"><a class="page-link">2</a></li>
//<li class="page-item"><a class="page-link gray">다음&nbsp;</a></li>


/*
function setPaging(href, digit, page){
	let active = "";
	let gray = "";
	if(href=="") { //href가 빈 문자열인 경우
		if(isNaN(digit)){ //이전&nbsp; 또는 다음&nbsp;
			gray="gray";	// 11, 20번 라인 처럼 href 속성이 없고 <a>요소의 textnode가 숫자가 아닌 경우			
		} else {
			active = "actvie"; // 12, 19번 라인 처럼 href 속성이 없고 <a>요소의 textnode가 숫자인 경우
		}
	}
	let output = `<li class="page-item ${active}">`;
	//let anchor = "<a class='page-link " + gray + "'" + href + ">" + digit + "</a></li>";
	//2 let anchor = `<a class="page-link ${gray}" ${href}>${digit}</a></li>`;
	let anchor = `<a class="page-link ${gray}" href="${href}" data-page="${page}">${digit}</a></li>`;
	output += anchor;
	return output;
	
}
*/


/*
function ajax(sdata) {
    console.log(sdata);
    
    $.ajax({
        type: "POST",
        data: sdata,
        url: "PostList.bo",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        cache: false,
        success: function(data) {
            $("#viewcount").val(data.limit);
            $("thead").find("span").text("글 개수 : " + data.listcount);
            
            if (data.listcount > 0) { // 총 갯수가 0보다 큰 경우
                
                let num = data.listcount - (data.page - 1) * data.limit;
                console.log(num);
                let output = "";
                
                let digit = '이전&nbsp;'
                let href = "";
                if (data.page > 1){
					href = 'href=javascript:go(' + (data.page - 1) + ')';
				}
				output += setPaging(href, digit);
				
				for(let i = data.startpage; i <= data.endpage; i++){
					digit = i;
					href = "";
					if (i != data.page){
						href = 'href=javascript:go(' + i + ')';
					}
					output += setPaging(href, digit);
				}
				
				digit = '&nbsp;다음&nbsp;';
				href = "";
				if(data.page < data.maxpage){
					href = 'href=javascript:go(' + (data.page + 1) + ')';
				}
				output += setPaging(href, digit);
				
				$('.pagination').append(output)
            } // if(data.listcount) > 0 end
            
        }, // success end 
        error : function() {
			console.log('에러')
		}
    })// ajax end
}// function ajax end
*/

/*$(function() {
	$('#writeArticleButton').click(function() {
		$(this).hide();	
		$(".postwriteform").show();
		
		var urlParams = new URLSearchParams(window.location.search);
		var boardNum = urlParams.get('board_num');
		
		$(".submit").click(function(){
			
		
		var write_subject = $('[name="subject"]').val();
		var write_content = $('[name="content"]').val();
		var board_num = boardNum;
		var post_file = $("#post_file").val();
			
			$.ajax({
				type: "POST",
				url: "PostAddAction.bo",
				data: {
					"board_num" : board_num,
					"write_subject": write_subject,
					"write_content": write_content,
					"post_file" : post_file
					
					
				},
				success: function(response){
					if (response == "success"){
						alert("글 작성이 완료되었습니다.");
						window.history.back();
					} else {
						alert("글 작성이 실패했습니다.");
					}
				},
				error: function() {
					alert("글 작성중 오류가 발생했습니다.");
				}
				
				
			});
			
		});

	});
});*/


/*
$(function() {
   $("#writeArticleButton").click(function(){
      location.href="PostWrite.bo";
   })
   
})
*/