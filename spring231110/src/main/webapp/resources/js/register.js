const joinBtn = document.getElementsByClassName('joinBtn')[0];
const checkIdResult = document.getElementById('checkIdResult');
const checkPwResult = document.getElementById('checkPwResult');
const checkPhoneResult = document.getElementById('checkPhoneResult');
const checkBirthResult = document.getElementById('checkBirthResult');
const checkImgResult = document.getElementById('checkImgResult');
const reg = new RegExp("^(?=.*[a-zA-Z])(?=.*[0-9]).{8,25}$"); //비밀번호 정규 표현식
const reg2 = new RegExp("^([0-9][0-9]|20\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$"); //생년월일 정규 표현식
const reg3 = new RegExp("^01[0-9]{1}-[0-9]{3,4}-[0-9]{4}$"); //전화번호 정규 표현식

checkIdResult.value = false;
checkPwResult.value = false;
checkPhoneResult.value = false;
checkBirthResult.value = false;

//아이디 중복확인 버튼 click
document.getElementById('checkId').addEventListener('click', ()=>{
    const id = document.getElementById('id').value;
    if(id != ''){
        let empId = {
            id: id
        };

        postId(empId).then(result=>{
            if(result>0){
                alert('사용 가능한 아이디입니다.');
                checkIdResult.value = true;
            }else{
                alert('이미 존재하는 아이디입니다.');
                checkIdResult.value = false;
            }
        })
    }else{
        alert('아이디를 입력해주세요.');
        checkIdResult.value = false;
    }
})

//controller에서 아이디 중복확인 체크
//중복된 아이디가 없다면 1 return
async function postId(empId){
    try {
        const url = '/member/checkId';
        const config = {
            method: 'post',
            headers:{
                'content-type':'application/json; charset=utf-8'
            },
            body:JSON.stringify(empId)
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

//submit 차단 경우의 수
document.querySelector('.form').addEventListener("submit", (e)=>{
    //부서 선택을 하지 않았을 때
    if(document.getElementsByClassName('selectInput')[0].value == '부서 선택'){
        alert('부서를 선택해주세요.');
        e.preventDefault();
    }
    //아이디 중복체크에서 true를 받지 못했을 때
    else if(checkIdResult.value == 'false'){
        alert('아이디 중복체크를 확인해주세요.');
        e.preventDefault();
    }
    //비밀번호 정규표현식에서 true를 받지 못했을 때
    else if(checkPwResult.value == 'false' || checkPwResult.value == '') {
        alert("비밀번호는 최소 8 자, 문자와 숫자가 포함되어야 합니다.");
        e.preventDefault();
    }
    //전화번호 정규표현식에서 true를 받지 못했을 때
    else if(checkPhoneResult.value == 'false' || checkPhoneResult.value == '') {
        alert("전화번호의 형식을 맞춰 입력해주십시오.");
        e.preventDefault();
    }
    //생년월일 정규표현식에서 true를 받지 못했을 때
    else if(checkBirthResult.value == 'false' || checkBirthResult.value == ''){
        alert("올바른 생년월일을 입력해주십시오.");
        e.preventDefault();
    }
    //그 외 빈칸이 있을 때
    else if(document.getElementById('empNm').value == '' || document.getElementById('addr').value == '' ){
        alert('빈칸이 없도록 작성해주십시오.');
        e.preventDefault();
    }
    //첨부파일이 정규표현식에 위반될 때
    else if(checkImgResult.value == 'false'){
        alert('업로드 불가능한 파일입니다.');
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
//전화번호 정규 표현식 확인
document.getElementById('phone').addEventListener('input', ()=>{
    const phoneCheck = document.getElementsByClassName('phoneCheck')[0];
    if(!reg3.test(document.getElementById('phone').value)){
        phoneCheck.classList.add('block');
        checkPhoneResult.value = false;
    }else{
        phoneCheck.classList.remove('block');
        checkPhoneResult.value = true;
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

//실행 파일, 이미지 파일에 대한 정규표현식
const regExp = new RegExp("\.(exe|sh|bat|js|msi|dll)$"); //실행 파일 막기
const regExpImg = new RegExp("\.(jpg|jpeg|png|gif)$"); //이미지 파일 받기
const maxSize = 1024 * 1024 * 20;

function fileValidation(fileName, fileSize){
    if(!regExpImg.test(fileName)){ //이미지 파일이 아니면
        return 0;
    }else if(regExp.test(fileName)){ //실행 파일이라면
        return 0;
    }else if(fileSize > maxSize){
        return 0;
    }else{
        return 1;
    }
}

document.addEventListener('change', (e)=>{
    if(e.target.id == 'file'){
        const fileObj = document.getElementById('file').files;
        console.log(fileObj);
        let validResult = fileValidation(fileObj[0].name, fileObj[0].size); //0 또는 1로 리턴됨
        if(validResult == 0){
            alert('업로드 불가능한 파일입니다.');
            checkImgResult = false;
        }else{
            checkImgResult = true;
        }
    }
})
