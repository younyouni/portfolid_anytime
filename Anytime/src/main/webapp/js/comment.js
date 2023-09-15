
function getList() {

	console.log("post_num : " + $("#post_num").val());
	console.log("anonymous : " + anonymous);
	console.log("currentUserNum : " + currentUserNum);
	console.log("writerNum : " + writerNum);
	$.ajax({
		type: "post",
		url: "CommentList.bo",
		data: {
			"post_num": $("#post_num").val(),
			"anonymous": anonymous,
			"currentUserNum": currentUserNum,
			"writerNum": writerNum,
		},
		dataType: "json",
		success: function(rdata) {
			let output = "";
			let writername = "";

			if (rdata.postlist.length > 0) {
				$(rdata.postlist).each(function() {
					const lev = this.re_lev;
					console.log("this.re_lev : " + this.re_lev);
					let comment_reply = '';
					if (lev == 0) {
						comment_reply = 'parent';
					} else if (lev == 1) {
						comment_reply = 'child';
					} else if (lev == 2) {
						comment_reply = 'grandchild';
					}
					console.log("comment_reply : " + comment_reply);
					if (anonymous == 0) {
						writername = this.nickname;
					} else if (anonymous == 1 && this.num == writerNum) {
						writername = "익명(글쓴이)";
					} else {
						writername = "익명";
					}
					console.log("writername : " + writername);

					$('div.comments').children().not('form').remove();

					output += '<article id="' + this.comments_num + '" class="' + comment_reply + '">   '
						+ '  <img src="https://cf-fpi.everytime.kr/0.png" class="picture medium">      '
						+ '  <h3 class="medium">' + writername + '</h3>                           '
						+ '       <ul class="status">                                          '
					if (lev < 2) {
						output += '          <li class="childcomment">                           '
							+ '            <a href="javascript:replyform(' + this.comments_num + ',' + lev
							+ '            ,' + this.re_seq + ',' + this.re_ref + ')" class="reply">   '
							+ '            대댓글</a></li>                                    '
					}
					if (currentUserNum == this.num) { // 작성자만 수정 삭제가 되도록 //admin도 접근 가능하게 하려면 변경해줘야함
						output += '         <li class="modify">                                     '

							+ '            <a class="modify" href="javascript:updateForm(' + this.comments_num + ')">      '
							+ '             수정</a></li>                                          '
							+ '         <li class="del"><a class="del" href="javascript:del(' + this.comments_num + ')">'
							+ '            삭제</a></li>                                                '
					} else {
						output += '         <li class="commentvote">공감</li>                              '
						output += '         <li class="messagesend" data-modal="messageSend" data-comment-id="1423439802" data-is-anonym="1">쪽지</li>'
						output += '         <li class="abuse">신고</li>'
					}
					output += '      </ul>                                                   '
						+ '      <hr>                                                   '
						+ '      <p class="large">' + this.content + '</p>                        '
						+ '      <time class="medium">' + this.comments_date + '</time>               '
						+ '      <ul class="status commentvotestatus">                           '
						+ '         <li class="vote commentvote" style="display: none;">            '
						+ this.like_count + '</li>                              '
						+ '      </ul>                                                   '
						+ '      </article>                                                ';

				})//each end
				$('.writecomment').before(output);
			}//if(rdata.postlist.length>0)
			else { //댓글 1개가 있는 상태에서 삭제하는 경우 갯수는 0이라  if문을 수행하지 않고 이곳으로 옵니다.
				//이곳에서 아래의 두 영역을 없앱니다.
				$('.comments').find('article').remove();
			}
		}//success end
	});//ajax end
}//function(getList) end

function del(comments_num) {//num : 댓글 번호
	console.log("삭제할 코멘트 : " + comments_num);
	if (confirm('정말 삭제하시겠습니까')) {
		$.ajax({
			url: 'CommentDelete.bo',
			data: { comments_num: comments_num },
			success: function(rdata) {
				if (rdata == 1) {
					getList();
				}
			}
		});
	}
}//function(del) end

//답글 달기 폼
function replyform(comment_num, lev, seq, ref) {
	$(this).off('click'); // 중복 클릭 방지
	$('.status').addClass('disabled');
	console.log($('.status').attr('class'))

	let comment_reply = '';
	if (lev == 0) {
		comment_reply = ' parent';
	} else if (lev == 1) {
		comment_reply = ' child';
	} else if (lev == 2) {
		comment_reply = ' grandchild';
	}
	console.log("대댓글 : " + comment_num + ' lev : ' + comment_reply + ' , ' + lev + ' seq : ' + seq + ' ref : ' + ref)

	// 클릭된 a 요소의 상위에 위치한 article 엘리먼트를 찾음
	const $comment_num = $('#' + comment_num); // 수정된 부분

	// article 엘리먼트의 id 속성 값을 가져와서 출력
	console.log($comment_num.attr('id'));

	//글쓰기 영역 복사합니다.
	const output = $('.writecomment').clone().addClass(comment_reply).addClass('clone');

	console.log(output.attr('class'));
	//선택한 글 뒤에 답글 폼 생성합니다.
	$comment_num.after(output); // 이 부분이 수정되었습니다.

	const $comment_num_next = $comment_num.next();

	console.log($comment_num_next.find('.text').attr('placeholder'));
	//답글 폼의  <textarea>의 속성 'placeholder'를 '답글을 남겨보세요'로 바꾸어 줍니다.
	$comment_num_next.find('.text').attr('placeholder', '대댓글을 남겨보세요');

	//답글 폼의  '.btn-cancel'을 보여주고 클래스 'reply-cancel'를 추가합니다.
	$comment_num_next.find('.cancel').addClass('reply-cancel').attr('title', '대댓글 취소');
	console.log($comment_num_next.find('.cancel').attr('class'));

	//답글 폼의 '.btn-register'에  클래스 'reply' 추가합니다.
	//속성 'data-ref'에 ref, 'data-lev'에 lev, 'data-seq'에 seq값을 설정합니다.
	//등록을 답글 완료로 변경합니다.
	$comment_num_next.find('.submit').attr('data-ref', ref).attr('data-lev', lev).attr('data-seq', seq).attr('title', '대댓글 완료').removeClass('submit_origin').addClass('reply_submit');

	$('li.cancel').click(function() {
		$(this).closest('.writecomment.clone').remove();

		$('.status').removeClass('disabled');

	});
}//function(replyform) end

function updateForm(comments_num) { //comments_num : 수정할 댓글 글번호
	$(this).off('click');
	$('.status').addClass('disabled');

	let $comments_num = $('#' + comments_num);
	let lev_class = $comments_num.attr('class');

	//선택한 내용을 구합니다.
	const content = $comments_num.find('p.large').text();
	console.log("수정할 content : " + content);

	//$comments_num.css('display', 'none'); //수정하려는 댓글 영역 숨겨요

	//$('.writecomment').clone() : 기본 글쓰기 영역 복사합니다.
	//글이 있던 영역에 글을 수정할 수 있는 폼으로 바꿉니다.
	$comments_num.after($('.writecomment').clone().addClass('clone').addClass(lev_class).attr('data-id', comments_num));

	//수정 폼의 <textarea>에 내용을 나타냅니다.
	$('.writecomment.clone .text').attr('value', content);
	$('.writecomment.clone li.submit').attr('title', '수정완료');

	//'.modify' 영역에 수정할 글 번호를 속성 'data-id'에 나타내고 클래스 'update'를 추가합니다.
	$('.writecomment.clone li.submit').attr('data-id', comments_num).addClass('update').removeClass('submit_origin');

	//수정 후 수정완료를 클릭한 경우
	$('.writecomment').off('click', '.submit_origin').on('click', '.update', function() {
		const content = $(this).parent().parent().find('.text').val();
		if (!content) {//내용없이 등록 클릭한 경우
			alert("수정할 글을 입력하세요");
			return;
		}
		const comments_num = $(this).attr('data-id');
		console.log("수정할 comments_num : " + comments_num);
		console.log("수정할 content : " + content);

		if (confirm("댓글을 수정하시겠습니까?")) {
			$.ajax({
				url: 'CommentUpdate.bo',
				data: { comments_num: comments_num, content: content },
				success: function(rdata) {
					if (rdata == 1) {
						$('.article').find('.clone').remove();
						$('div.comments article').remove();

						getList();
					}//if
				}//success
			});//ajax
		}
	});//수정 후 수정완료를 클릭한 경우

	//수정 후 취소 버튼을 클릭한 경우
	$('li.cancel').click(function() {
		//댓글 번호를 구합니다.
		const comments_num = $(this).next().attr('data-id');
		const selector = '#' + comments_num;

		$(this).closest('.writecomment.clone').remove();

		//숨겨두었던 댓글 영역 보여줍니다.
		$(selector).css('display', 'block');

		$('.status').removeClass('disabled');

	});
}//function(updateForm) end

$(document).ready(function() {

	getList();

	$('article').off('click', 'li.reply_submit').on('click', 'li.submit_origin', function() {
		const content = $(this).parent().parent().find('.text').val();
		if (!content) {//내용없이 등록 클릭한 경우
			alert("댓글을 입력하세요");
			return;
		}

		if (confirm("댓글을 등록하시겠습니까?")) {
			$(this).off('click'); // 중복 클릭 방지
			$.ajax({
				url: 'CommentAdd.bo',  //원문 등록
				data: {
					"userid": userid,
					"content": content,
					"post_num": $("#post_num").val()
				},
				type: 'post',
				success: function(rdata) {
					if (rdata == 1) {
						getList();
					}
				}
			})//ajax
			$('.text').val('');//text 초기화
		}
	});// $('article li.submit').click(function()


	//답글쓰기 후 취소 버튼을 클릭한 경우
	$('li.cancel').click(function() {
		$(this).parent().parent().remove();
		$(".status").removeClass('disabled'); //더 보기 영역 보이도록 합니다.
	})//답글쓰기  후 취소 버튼을 클릭한 경우

	$(document).on('click', '.clone .reply_submit', function(e) {
		const content = $(this).parent().parent().find('.text').val();
		if (!content) {
			alert("대댓글을 입력하세요");
			return;
		}
		const re_ref = $(this).attr('data-ref');
		const re_lev = $(this).attr('data-lev');
		const re_seq = $(this).attr('data-seq');
		if (confirm("대댓글을 등록하시겠습니까?")) {
			$(this).off('click'); // 중복 클릭 방지
			$.ajax({
				url: 'CommentReply.bo',
				data: {
					content: content,
					post_num: $("#post_num").val(),
					re_lev: re_lev,
					re_ref: re_ref,
					re_seq: re_seq
				},
				type: 'post',
				success: function(rdata) {
					if (rdata == 1) {
						$('div.comments').children().not('form').remove();
						$('.reply_submit').parent().parent().remove();
						getList();
					} else {
						alert('댓글 등록에 실패하였습니다.');
					}
				}
			})
		}
	})

});