console.log("chat2.js진입");

// 현재 로그인한 사용자 ID를 전역 변수로 저장
let currentUserID = document.getElementById("chatName").value; //프린시펄 username부터 input으로 넘어온값

const chatName = document.getElementById("chatName").value;

// URL에서 toID 값을 가져오기
const urlParams = new URLSearchParams(window.location.search);
const toID = urlParams.get("toID"); // 'toID' 파라미터의 값을 가져옵니다.
let chatData = {
    fromID: chatName, //여기 왼쪽 단어가 중요함 디비 컬럼이랑 맞춰야함
    toID: toID,
    chatContent: "",
};
//호출해서 등록
// document.getElementById("chatSubmitBtn").addEventListener("click", () => {
//     console.log("chatSubmitBtn 리스너 진입");
//     const chatContent = document.getElementById("chatContent").value;
//     chatData = {
//         fromID: chatName, //여기 왼쪽 단어가 중요함 디비 컬럼이랑 맞춰야함 //위의 내용 사라지고 다시 덮어쓰는듯
//         toID: toID,
//         chatContent: chatContent,
//     };

//     console.log("3 ", chatData);
//     postComment(chatData).then((result) => {
//         console.log("8 ", result);
//         if (result > 0) {
//             //alert("채팅글 insert 완료");
//         } else {
//             alert("채팅 insert 실패");
//         }

//         // printCommentList(bnoVal);
//         //1:1 채팅글 출력
//         printChatList(chatData);
//         document.getElementById("chatContent").value = "";
//         document.getElementById("chatContent").focus();
//     });
// });

// 보내는 함수
// async function postComment(chatData) {
//     try {
//         console.log("4는 ", chatData);
//         const url = "/chaturl/chat2";
//         const config = {
//             method: "post",
//             headers: {
//                 "content-type": "application/json; charset=utf-8",
//             },
//             body: JSON.stringify(chatData),
//         };
//         console.log("5는 ", config);
//         const resp = await fetch(url, config);
//         const result = await resp.json();
//         // const result = await resp.text(); //isOk
//         return result;
//     } catch (error) {
//         console.log(error);
//     }
// }

//댓글 리스트 출력 함수
// function printChatList(chatData) {
//     spreadChatListFromServer(chatData).then((result) => {
//         console.log("printChatList 출력함수 진입");

//         const ul = document.getElementById("chatList2");
//         console.log("printChatListd의 result는 ", result);
//         console.log("result.length는 ", result.length);

//         if (result.length > 0) {
//             //대소문자 꼭 맞춰야함 위 아래

//             ul.innerText = "";
//             let str = "";
//             for (let chatdto of result) {
//                 let name1;
//                 if (chatdto.fromID == currentUserID) {
//                     name1 = "나";
//                 } else {
//                     name1 = chatdto.fromID;
//                 }

//                 str += `<div class="row">`;
//                 str += `<div class="col-lg-12">`;
//                 str += `<div class="media" >`;
//                 str += `<div><a class="pull-left" href="#">`;
//                 str += `<img class="media-object img-circle" style="width: 30px; height:30px;" src="/resources/img/anoyicon.png" alt="">`;

//                 str += `<span class="media-heading">`;
//                 str += `${name1}  <span class="small pull-rigth style="left-right: 30px;">  ${chatdto.chatTime}</span>`;
//                 str += `</span>`;

//                 str += `</a></div>`;
//                 str += `<div class="media-body" style="float: center">`;

//                 str += `<div style="text-align: left;">${chatdto.chatContent}</div>`;
//                 str += `</div>`;
//                 str += `</div>`;
//                 str += `</div>`;
//                 str += `</div>`;
//             }
//             ul.innerHTML += str;

//             const chatList = document.getElementById("chatList2");
//             chatList.scrollTop = chatList.scrollHeight;
//         } else {
//             ul.innerText = "글이 없습니다.";
//         }
//     });
// }

// 읽지 않은 메시지 관련 함수
async function getUnread(currentUserID) {
    try {
        console.log("비동기 getUnread 함수 진입");
        console.log("230줄의 currentUserID는", currentUserID);
        const url = "/chaturl/chatUnread";
        const chatData = { toID: currentUserID }; //이렇게 해야 컨트롤러가 인식하기 시작함 //ChatDTO형식의 변수(db컬럼명)개념으로 인식하기 시작
        const config = {
            method: "post",
            headers: {
                "content-type": "application/json; charset=utf-8",
            },
            body: JSON.stringify(chatData),
        };
        console.log("5는 ", config);
        const resp = await fetch(url, config);
        const result = await resp.text(); //isOk
        // return result;
        console.log("안읽은 글 개수는 " + result);
        if (result >= 1) {
            showUnread(result);
        } else {
            showUnread("");
        }
        console.log(" getUnread(currentUserID) 정상동작완료");
    } catch (error) {
        console.log(error);
    }
}

function showUnread(result) {
    console.log("showUnread(result)함수 진입 " + result);
    // $("#unread").html(result);
    document.getElementById("unread").innerHTML = result;
    console.log("showUnread(result)함수 탈출 " + result);
}

function getInfiniteChat() {
    setInterval(function () {
        // printChatList(chatData);
        chatBoxFunction(currentUserID);
    }, 3000);
}

document.addEventListener("DOMContentLoaded", (event) => {
    getInfiniteChat();
    getUnread(currentUserID);
    chatBoxFunction(currentUserID);
    // getInfiniteBox();
});

// 누가누가 나한테 메시지 보냈나 //내가 아직 확인 못한 메시지 확인용
async function chatBoxFunction(currentUserID) {
    try {
        console.log("비동기 chatBoxFunction 함수 진입");
        console.log("242줄의 currentUserID는", currentUserID);
        const url = "/chaturl/getBox"; //아직 안만든듯231119 23시59분 chatBox--->getBox
        const chatData = { toID: currentUserID }; //이렇게 해야 컨트롤러가 인식하기 시작함 //ChatDTO형식의 변수(db컬럼명)개념으로 인식하기 시작
        const config = {
            method: "post",
            headers: {
                "content-type": "application/json; charset=utf-8",
            },
            body: JSON.stringify(chatData),
        };
        console.log("5는 ", config);
        const resp = await fetch(url, config);
        const result = await resp.json(); //isOk

        const u = document.getElementById("boxTable");
        if (result.length > 0) {
            //대소문자 꼭 맞춰야함 위 아래

            //다시 댓글을 뿌릴 때 기존 값 삭제 1page 경우
            // if (page == 1) {
            //     ul.innerText = "";
            // }
            u.innerText = "";
            let str = "";
            console.log("chatBoxFunction의 내부 result는 " + result);
            for (let chatdto of result) {
                let name1;
                if (chatdto.fromID == currentUserID) {
                    name1 = "나";
                } else {
                    name1 = chatdto.fromID;
                }

                // str += `<tr onclick="location.href=\'chat2.jsp?toID>`;
                // str += `${name1}`;
                // str += `<td style="width: 150px;"><h5>`;
                // str += `${chatdto.fromID}`;
                // str += `</h5></td>`;
                // str += `<h5>`;
                // str += `${chatdto.chatContent}`;
                // str += `</h5>`;
                // str += `<div class="pull-right">`;
                // str += `${chatdto.chatTime}`;
                // str += `</div></td></tr>`;

                // <tr onclick="location.href='chat2.jsp?toID=<some_id>'">
                //     <td style="width: 150px;">
                //         <h5>Test</h5>
                //     </td>
                //     <td>
                //         <h5>Test</h5>
                //     </td>
                //     <td>
                //         <div class="pull-right">Test</div>
                //     </td>
                // </tr>

                str += `<tr onclick="location.href='chat2?toID=${chatdto.fromID}&fromID=${chatdto.toID}'">`;
                str += `    <td style="width: 150px;">`;
                str += `        <h5>${chatdto.fromID}</h5>`;
                str += `    </td>`;
                str += `    <td>`;
                str += `        <h5>${chatdto.chatContent}</h5>`;
                str += `    </td>`;
                str += `    <td>`;
                str += `        <div class="pull-right">${chatdto.chatTime}</div>`;
                str += `    </td>`;
                str += `</tr>`;
            }
            u.innerHTML += str;

            const boxTable = document.getElementById("boxTable");
            boxTable.scrollTop = boxTable.scrollHeight;
        } else {
            u.innerText = "메시지가 없습니다.";
        }
    } catch (error) {
        console.log(error);
    }
}
