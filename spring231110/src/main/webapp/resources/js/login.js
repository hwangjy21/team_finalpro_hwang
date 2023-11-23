//회원가입 페이지 설명란
document.getElementById('registerATag').addEventListener('mouseover', ()=>{
    document.getElementById('registerEx').classList.add('view');
    document.getElementById('registerATag').addEventListener('mouseleave', ()=>{
        document.getElementById('registerEx').classList.remove('view');
    })
})
//비밀번호 변경 페이지 설명란
document.getElementById('modifyPwdATag').addEventListener('mouseover', ()=>{
    document.getElementById('modifyEx').classList.add('view');
    document.getElementById('modifyPwdATag').addEventListener('mouseleave', ()=>{
        document.getElementById('modifyEx').classList.remove('view');
    })
})