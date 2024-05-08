function check(){
   if(document.getElementById("title").value.trim().length == 0){
              alert("제목이 입력되지 않았습니다")
              document.getElementById("title").focus();
              return false;
}
   if(document.getElementById("contents").value.length == 0){
              alert("내용이 입력되지 않았습니다")
              document.getElementById("contents").focus();
              return false;
}
alert("입력 완료")
document.getElementById("frm").focus();
return true;
}

function res(){
alert("처음부터 다시 입력합니다")
document.getElementById("frm").reset();
document.getElementById("frm").focus();
}

//    function addToCart() {
//        // 현재 로그인한 사용자의 정보를 가져오기 위해 서버로부터 Ajax 요청을 보냄
//        fetch('/current-user')
//            .then(response => response.json())
//            .then(data => {
//                if (data.loggedIn) {
//                    // 만약 로그인한 사용자라면 true 반환하여 폼 제출
//                    return true;
//                } else {
//                    // 만약 로그인한 사용자가 아니라면 로그인 페이지로 이동하고 false 반환하여 폼 제출 중지
//                    window.location.href = '/user/login';
//                    return false;
//                }
//            })
//            .catch(error => {
//                console.error('Error:', error);
//                // 에러가 발생한 경우 폼 제출 중지
//                return false;
//            });
//    }