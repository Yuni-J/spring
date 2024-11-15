console.log("boardDetail.js in");

document.getElementById('listBtn').addEventListener('click', () => {
    //리스크로 이동
    location.href="/board/list"
});

document.getElementById('modBtn').addEventListener('click', () => {
    //title, content의 readonly를 해지  readonly = true / false
    document.getElementById('title').readOnly = false;
    document.getElementById('content').readOnly = false;

    //modBtn delBtn 삭제
    document.getElementById("modBtn").remove();
    document.getElementById("delBtn").remove();

    //modBtn => submit 버튼으로 변경 추가
    let modBtn = document.createElement('button');           // <button></button>
    modBtn.setAttribute('type','submit');                    // <button type="submit"></button>
    modBtn.setAttribute('id', 'regBtn');              
    modBtn.classList.add('btn','btn-outline-warning');   // class="btn btn-outline-warning"
    modBtn.innerText="submit";                               // <button type="submit" class="btn btn-outline-warning"></button>

    // form 태그의 자식 요소로 추가 - form 에 가장 마지막에 추가
    document.getElementById('modForm').appendChild(modBtn);

    // file-x 버튼 disable 해지
    let fileDelBtn = document.querySelectorAll('.file-x');
    console.log(fileDelBtn);
    for(let delBtn of fileDelBtn){
        // delBtn.disabled = false;
        delBtn.style.display = "block";
    }

    // 파일 업로드 버튼 disabled 해지
    document.getElementById('trigger').style.display = "block";

});

document.getElementById('delBtn').addEventListener('click', () => {
    // location.href="/board/delete?bno=${bvo.bno}";
    // delForm이 submit으로 전송
    document.getElementById('delForm').submit();
});

//비동기 삭제
document.addEventListener('click', (e)=>{
    if(e.target.classList.contains('file-x')){
        console.log(e.target);
        let uuid = e.target.dataset.uuid;
        fileRemoveToServer(uuid).then(result => {
            if(result > 0){
                alert("파일 삭제 성공");
                e.target.closest('li').remove();
            }
        })
    }
});


async function fileRemoveToServer(uuid) {
    try {
        const url = "/board/file/"+uuid;
        const config = {
            method: 'delete'
        }
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
    
}