/**
 *
 */
console.log("chat2.js진입");

// function autoClosingAlert(selector, delay){
//     var alert =$(selector).alert();
//     alert.show();
//     window.setTimeout(function(){alert.hide()},delay);
// }
// function submitFunction(){
//     var fromID = '<%= userID %>';
//     var toID = '<%= toID %>';
//     var chatContent = $('#chatContent').val();
//     $.ajax({
//         type : "POST",
//         url : ".chatSubmitServlet",
//         data:{
//             from: encodeURIComponent(fromID),
//             toID: encodeURIComponent(toID),
//             chatContent: encodeURIComponent(chatContent)
//         },
//         success: function(result){
//             if(result ==1  ){
//                 autoClosingAlert('#successMessage',2000);
//             }else if (result ==0){
//                 autoClosingAlert('#dangerMessage',2000);
//             }else{
//                 autoClosingAlert('#warningMessage', 2000);
//             }
//         }
//     });
//     $('#chatContent').val('');
// }
// 현재 로그인한 사용자 ID를 전역 변수로 저장
let currentUserID = document.getElementById("chatName").value; //프린시펄 username부터 input으로 넘어온값

//호출해서 등록
document.getElementById("chatSubmitBtn").addEventListener("click", () => {
    console.log("chatSubmitBtn 리스너 진입");

    const chatContent = document.getElementById("chatContent").value;
    // console.log("1 " + document.getElementById("chatContent").value);
    const chatName = document.getElementById("chatName").value;

    // URL에서 toID 값을 가져오기
    const urlParams = new URLSearchParams(window.location.search);
    const toID = urlParams.get("toID"); // 'toID' 파라미터의 값을 가져옵니다.

    let chatData = {
        // bno: bnoVal,
        fromID: chatName,
        chatContent: chatContent, //여기 왼쪽 단어가 중요함 디비 컬럼이랑 맞춰야함
        toID: toID,
    };
    console.log("3 ", chatData);
    postComment(chatData).then((result) => {
        console.log("8 ", result);
        if (result > 0) {
            //alert("채팅글 insert 완료");
        } else {
            alert("채팅 insert 실패");
        }

        // printCommentList(bnoVal);
        //1:1 채팅글 출력
        printChatList(chatData);
        document.getElementById("chatContent").value = "";
        document.getElementById("chatContent").focus();
    });
});

// 보내는 함수
async function postComment(chatData) {
    try {
        console.log("4는 ", chatData);
        const url = "/chaturl/chat2";
        const config = {
            method: "post",
            headers: {
                "content-type": "application/json; charset=utf-8",
            },
            body: JSON.stringify(chatData),
        };
        console.log("5는 ", config);
        const resp = await fetch(url, config);
        const result = await resp.json();
        // const result = await resp.text(); //isOk
        return result;
    } catch (error) {
        console.log(error);
    }
}

//댓글 리스트 출력 함수
function printChatList(chatData) {
    spreadChatListFromServer(chatData).then((result) => {
        console.log("printChatList 출력함수 진입");

        const ul = document.getElementById("chatList2");
        console.log("printChatListd의 result는 ", result);
        console.log("result.length는 ", result.length);
        //console.log("result.chatList는 " , result.chatList);
        //console.log("result.chatList.length는 " , result.chatList.length);
        if (result.length > 0) {
            //대소문자 꼭 맞춰야함 위 아래

            //다시 댓글을 뿌릴 때 기존 값 삭제 1page 경우
            // if (page == 1) {
            //     ul.innerText = "";
            // }
            ul.innerText = "";
            let str = "";
            for (let chatdto of result) {
                let name1;
                if (chatdto.fromID == currentUserID) {
                    name1 = "나";
                } else {
                    name1 = chatdto.fromID;
                }

                str += `<div class="row">`;
                str += `<div class="col-lg-12">`;
                str += `<div class="media" >`;
                str += `<div><a class="pull-left" href="#">`;
                str += `<img class="media-object img-circle" style="width: 30px; height:30px;" src="/resources/img/anoyicon.png" alt="">`;

                str += `<span class="media-heading">`;
                str += `${name1}  <span class="small pull-rigth style="left-right: 30px;">  ${chatdto.chatTime}</span>`;
                str += `</span>`;

                str += `</a></div>`;
                str += `<div class="media-body" style="float: center">`;

                str += `<div style="text-align: left;">${chatdto.chatContent}</div>`;
                str += `</div>`;
                str += `</div>`;
                str += `</div>`;
                str += `</div>`;
            }
            ul.innerHTML += str;

            const chatList = document.getElementById("chatList2");
            chatList.scrollTop = chatList.scrollHeight;

            //str += `</ul>`;

            // //댓글 페이징 코드
            // let moreBtn = document.getElementById('moreBtn');
            // console.log(moreBtn, result.pgvo.pageNo, result.endPage);
            // //db에서 pgvo + list 같이 가져와야 값을 줄 수 있음.
            // if (result.pgvo.pageNo < result.endPage) {
            //     moreBtn.style.visibility = 'visible'; //버튼 표시
            //     moreBtn.dataset.page = page + 1;
            // } else {
            //     moreBtn.style.visibility = 'hidden'; //버튼 숨김
            // }
        } else {
            ul.innerText = "글이 없습니다.";
        }
    });
}

//채팅글 요청 함수
async function spreadChatListFromServer(chatData) {
    try {
        const url = "/chaturl/list2/";
        const resp = await fetch(url);
        const result = await resp.json(); //리스트 받음
        console.log("spreadChatListFromServer의 result는 ", result);
        return result;
    } catch (error) {
        console.log("에러진입");
        console.log(error);
    }
}

function getInfiniteChat() {
    setInterval(function () {
        printChatList();
    }, 2500000);
}

document.addEventListener("DOMContentLoaded", (event) => {
    getInfiniteChat();
});
