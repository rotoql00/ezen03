/**
 * 
 */
var question1 = "여기는 왜 질문을하는곳인가요?";
var answer1 = "그러게요 질문 하지마세요 화나니까";

var qusetion2 = "임석현은 바보인가요?";
var answer2 = "네 확실한 바보입니다";

var qusetion3  = "겨울은 왜추운가요?";
var answer3 = "겨울이니까 추워요";

var qusetion4 = "오늘 저녁은 무엇일까요?";
var answer4 = "맛있는거요";

$('.question01').html(question1);
$('.answer01').html(answer1);
$('.question02').html(question2);
$('.answer02').html(answer2);
$('.question03').html(question3);
$('.answer03').html(answer3);
$('.question04').html(question4);
$('.answer04').html(answer4);



//게시판 리스트 페이지
$(function(){
	$(".numlist").click(function(e){
		e.preventDefault();
		
		$(".numlist").not(this).removeClass("active");
		
		$(this).addClass("active");
		
	});
	
});