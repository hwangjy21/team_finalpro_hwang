console.log("js 들어옴");

document.addEventListener('DOMContentLoaded', function () {
    const modal = document.getElementById("modal");
    const modalCloseBtn = document.getElementById("close");
    const confirmBtn = document.getElementById("confirm");
    const clubName_m = document.getElementById("clubName_m");
    const modalContent = document.getElementById("modalContent");
    const modalMember_info = document.getElementById("modalMember_info");
    const close = document.getElementById("close");
    const confirm = document.getElementById("confirm");

    let clubCd;

    function modalOn() {
        modal.style.display = "flex";
    }

    function modalOff() {
        modal.style.display = "none";
    }

    const list = document.querySelector(".cards").children;

    for (let i = 0; i < list.length; i++) {
        const btn = list[i].querySelector('button');

        btn.addEventListener("click", e => {
            confirm.style.display = "flex";
            close.style.width = "49%";
            modalMember_info.innerText = "";
            console.log("버튼 누름");
            clubCd = e.currentTarget.getAttribute("data-clubCd");
            const clubNm = e.currentTarget.getAttribute("data-clubNm");
            const clubIntro = e.currentTarget.getAttribute("data-clubIntro");
            const memberLimitCnt = e.currentTarget.getAttribute("data-memberLimitCnt");
            const memberCnt = e.currentTarget.getAttribute("data-memberCnt");

            clubName_m.innerText = clubNm;
            modalContent.innerText = clubIntro;
            modalMember.innerText = "수용 인원 : " + memberLimitCnt + " , 남은 인원 : " + (parseInt(memberLimitCnt) - parseInt(memberCnt));

            if (parseInt(memberLimitCnt) - parseInt(memberCnt) === 0) {
                confirm.style.display = "none";
                close.style.width = "100%";
                modalMember_info.innerText = "아쉽지만, 인원이 다 차 동호회에 들어갈 수 없습니다.";
            }

            modalOn();
        });
    }

    modalCloseBtn.addEventListener("click", e => {
        modalOff();
    });

    confirmBtn.addEventListener("click", async (e) => {
        try {
            const userId = getUserIdFromSession();
            const approvalResult = await approval_club(clubCd, userId);

            if (approvalResult === 'success') {
                modalOff();
                Swal.fire({
                    icon: 'success',
                    title: '가입 신청이 완료되었습니다.',
                }).then((result) => {
                    if (result.isConfirmed) {
                    }
                });
            } else {
                console.error('Club approval failed');
            }
        } catch (err) {
            console.error(err);
        }
    });

    async function approval_club(clubCd, userId) {
        try {
            const url = '/approval/' + clubCd+'/'+userId;
            const config = {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json; charset=utf-8'
                },
                body: JSON.stringify({ clubCd: clubCd, userId: userId })
            };

            const resp = await fetch(url, config);
            const result = await resp.text();
            return result;
        } catch (err) {
            console.error(err);
        }
    }

    function getUserIdFromSession() {
        return document.getElementById("id").value;
    }
});
