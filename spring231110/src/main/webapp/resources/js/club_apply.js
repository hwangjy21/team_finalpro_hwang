

console.log("js 들어옴");
document.addEventListener('DOMContentLoaded', function () {
    const modal = document.getElementById("modal");
    const modalOpenBtn = document.getElementById("modal-open");
    const modalCloseBtn = document.getElementById("close");
    const confirmBtn = document.getElementById("confirm");
    const modalTitle = document.getElementById("modalTitle");
    const modalContent = document.getElementById("modalContent");


modal.style.display = "none";

function modalOn() {
    modal.style.display = "flex";
}

function isModalOn() {
    return modal.style.display === "flex";
}

function modalOff() {
    modal.style.display = "none";
}

const closeBtn = document.getElementById("close");
closeBtn.addEventListener("click", e => {
    modalOff();
});

modalOpenBtn .addEventListener("click", e => {

console.log("버튼 누름");
 modalOn();
  const clubNm = modalOpenBtn.getAttribute("data-club-nm");
   
});


detailBtn.addEventListener("click", e => { // 수정
    isModalOn();
});
});