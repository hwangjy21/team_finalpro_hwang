const joinBtn = document.getElementsByClassName('joinBtn')[0];
const checkBirthResult = document.getElementById('checkBirthResult');
const reg2 = new RegExp("^([0-9][0-9]|20\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$"); //생년월일 정규 표현식

checkBirthResult.value = false;

//submit 차단 경우의 수
document.querySelector('.form').addEventListener("submit", (e)=>{
    //부서 선택을 하지 않았을 때
    if(document.getElementsByClassName('selectInput')[0].value == '부서 선택'){
        alert('부서를 선택해주세요.');
        e.preventDefault();
    }
    //생년월일 정규표현식에서 true를 받지 못했을 때
    else if(checkBirthResult.value == 'false' || checkBirthResult.value == ''){
        alert("올바른 생년월일을 입력해주십시오.");
        e.preventDefault();
    }
    //그 외 빈칸이 있을 때
    else if(document.getElementById('empNm').value == '' || document.getElementById('id').value == '' ){
        alert('빈칸이 없도록 작성해주십시오.');
        e.preventDefault();
    }
    //성공했을 떄
    else{
    	alert('본인인증이 완료되었습니다.');
    }
})

//생년월일 정규 표현식 확인
document.getElementById('birth').addEventListener('input', ()=>{
    const birthCheck = document.getElementsByClassName('birthCheck')[0];
    if(!reg2.test(document.getElementById('birth').value)){
        birthCheck.classList.add('block');
        checkBirthResult.value = false;
    }else{
        birthCheck.classList.remove('block');
        checkBirthResult.value = true;
    }
})