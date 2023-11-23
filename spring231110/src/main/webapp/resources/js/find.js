console.log("find.js진입");

let currentUserID = document.getElementById("chatName").value; //프린시펄 username부터 input으로 넘어온값

//호출해서 등록
document.getElementById("findFcBtn").addEventListener("click", () => {
    console.log("findFcBtn 리스너 진입");

    // 현재 로그인한 사용자 ID를 전역 변수로 저장
    // let currentUserID = document.getElementById("username").value;
    // let empNm = document.getElementById("empNm").value;
    // console.log("empNm은 " + empNm);

    const findID = document.getElementById("findID").value;
    // console.log("1 " + document.getElementById("chatContent").value);
    // const chatName = document.getElementById("chatName").value;
    // console.log("2 " + document.getElementById("chatName").value);
    let chatData = {
        // bno: bnoVal,
        toID: findID,
        // chatContent: chatContent, //여기 왼쪽 단어가 중요함 디비 컬럼이랑 맞춰야함
    };
    console.log("3 ", chatData);
    postComment(chatData).then((result) => {
        console.log("8번째 ", result);
        if (result.length > 0) {
            alert("친구 ID가 조회는 됨");
        } else {
            alert("친구 조회 실패");
        }

        // printCommentList(bnoVal);
        //전체 채팅글 출력
        printChatFriendList(chatData);
        // document.getElementById("chatContent").value = "";
        // document.getElementById("chatContent").focus();
    });
});

// 보내는 함수
async function postComment(chatData) {
    try {
        console.log("4는 ", chatData);
        const url = "/chaturl/find";
        const config = {
            method: "post",
            headers: {
                "content-type": "application/json; charset=utf-8",
            },
            body: JSON.stringify(chatData),
        };
        console.log("5는 ", config);
        const resp = await fetch(url, config);
        // const result = await resp.text(); //isOk
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }
}

//사원id 검색 리스트 출력 함수
function printChatFriendList(chatData) {
    // spreadChatListFromServer(chatData).then((result) => {
    postComment(chatData).then((result) => {
        //한번더 같은 함수 씀 이번엔 안에 데이터까지 확인하려고
        console.log("printChatFriendList 출력함수 진입");

        const ul = document.getElementById("friendResult");
        console.log("result는 ", result);
        console.log("result.length는 ", result.length);
        //console.log("result.chatList는 " , result.chatList);
        //console.log("result.chatList.length는 " , result.chatList.length);
        if (result.length > 0) {
            //대소문자 꼭 맞춰야함 위 아래

            ul.innerText = "";
            let str = "";
            for (let chatdto of result) {
                // let name1;
                // if (chatdto.fromID == currentUserID) {
                //     name1 = "나";
                // } else {
                //     name1 = chatdto.fromID;
                // }

                str += `<thead>`;
                str += `<tr>`;
                str += `<th><h4>검색결과</h4></th>`;
                str += `</tr>`;
                str += `</head>`;
                str += `<tbody>`;
                str += `<tr>`;
                str += `<td style="text-align: center;">`;
                str += `<h3> ${chatdto.id} </h3>`;
                str += `<a href="/chaturl/chat2?toID=${chatdto.id}&fromID=${currentUserID}" class="btn btn-primary pull-right">`;
                str += `메시지보내기`;
                str += `</td>`;
                str += `</tr>`;
                str += `</tbody>`;

                //     <tbody>
                // 	<c:forEach items="${list }" var="bvo">
                // 		<tr>

                // 			<th><a href="/board/detail?bno=${bvo.bno}">${bvo.bno }</a></th>
                // 			<td><a href="/board/detail?bno=${bvo.bno}">${bvo.writer }</a></td>
                // 			<td><a href="/board/detail?bno=${bvo.bno}">${bvo.title }</a></td>
                // 			<td><a href="/board/detail?bno=${bvo.bno}">${bvo.readCount }</a></td>
                // 			<td><a href="/board/detail?bno=${bvo.bno}">${bvo.cmtQty }</a></td>
                // 			<td><a href="/board/detail?bno=${bvo.bno}">${bvo.hasFile }</a></td>

                // 		</tr>
                // 	</c:forEach>
                // </tbody>
            }
            ul.innerHTML += str;

            // const chatList = document.getElementById("chatList");
            // chatList.scrollTop = chatList.scrollHeight;
        } else {
            ul.innerText = "없습니다.";
        }
    });
}

// 요청 함수
async function spreadChatListFromServer(chatData) {
    try {
        const url = "/chaturl/find";
        const resp = await fetch(url);
        const result = await resp.json(); //리스트 받음
        console.log("spreadChatListFromServer result는 ", result);
        return result;
    } catch (error) {
        console.log("에러진입");
        console.log(error);
    }
}

// function getInfiniteChat() {
//     setInterval(function () {
//         printChatList();
//     }, 2500);
// }

// document.addEventListener("DOMContentLoaded", (event) => {
//     getInfiniteChat();
// });
