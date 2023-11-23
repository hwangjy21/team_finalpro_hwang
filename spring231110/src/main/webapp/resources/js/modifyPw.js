const joinBtn = document.getElementsByClassName('joinBtn')[0];
const checkPwResult = document.getElementById('checkPwResult');
const reg = new RegExp("^(?=.*[a-zA-Z])(?=.*[0-9]).{8,25}$"); //비밀번호 정규 표현식

checkPwResult.value = false;

//submit 차단 경우의 수
document.querySelector('.form').addEventListener("submit", (e)=>{
    //비밀번호 정규표현식에서 true를 받지 못했을 때
    if(checkPwResult.value == 'false' || checkPwResult.value == '') {
        alert("비밀번호는 최소 8 자, 문자와 숫자가 포함되어야 합니다.");
        e.preventDefault();
    }
    //입력받은 두 비밀번호가 일치하지 않을 때
    else if(document.getElementById('pw').value != document.getElementById('pw2').value){
        alert('비밀번호가 일치하지 않습니다.');
        e.preventDefault();
    }
    //그 외 빈칸이 있을 때
    else if(document.getElementById('pw').value == '' || document.getElementById('pw2').value == '' ){
        alert('빈칸이 없도록 작성해주십시오.');
        e.preventDefault();
    }
})

//비밀번호 정규 표현식 확인
document.getElementById('pw').addEventListener('input', ()=>{
    const pwCheck = document.getElementsByClassName('passwordCheck')[0];
    const pwCheckOk = document.getElementsByClassName('passwordCheckOk')[0];
    if(!reg.test(document.getElementById('pw').value)){
        pwCheck.classList.add('block');
        pwCheckOk.classList.remove('block');
        checkPwResult.value = false;
    }else{
        pwCheck.classList.remove('block');
        pwCheckOk.classList.add('block');
        checkPwResult.value = true;
    }
})
//비밀번호 일치 확인
document.getElementById('pw2').addEventListener('input', ()=>{
    const pwCheck2 = document.getElementsByClassName('passwordCheck2')[0];
    const pwCheckOk2 = document.getElementsByClassName('passwordCheckOk2')[0];
    if(document.getElementById('pw').value != document.getElementById('pw2').value){
        pwCheck2.classList.add('block');
        pwCheckOk2.classList.remove('block');
    }else{
        pwCheck2.classList.remove('block');
        pwCheckOk2.classList.add('block');
    }
})