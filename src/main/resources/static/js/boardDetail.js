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
    modBtn.classList.add('btn','btn-outline-warning');   // class="btn btn-outline-warning"
    modBtn.innerText="submit";                               // <button type="submit" class="btn btn-outline-warning"></button>

    // form 태그의 자식 요소로 추가 - form 에 가장 마지막에 추가
    document.getElementById('modForm').appendChild(modBtn);
});

document.getElementById('delBtn').addEventListener('click', () => {
    // location.href="/board/delete?bno=${bvo.bno}";
    // delForm이 submit으로 전송
    document.getElementById('delForm').submit();
});