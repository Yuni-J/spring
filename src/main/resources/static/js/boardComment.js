console.log("boardComment.js in");
console.log(bnoVal);
console.log(nickName);

document.getElementById('cmtAddBtn').addEventListener('click', ()=>{
    const cmtText = document.getElementById('cmtText');
    const cmtWriter = document.getElementById('cmtWriter');

    if(cmtText.value == null || cmtWriter.innerText == ''){
        alert('댓글을 입력해주세요');
        cmtText.focus();
        return false;
    }

    let cmtDate = {
        bno: bnoVal,
        writer: cmtWriter.innerText,
        content: cmtText.value
    }
    console.log(cmtDate);

    postCommentToServer(cmtDate).then(result => {
        if(result > 0){
            alert('댓글 등록 성공');
            cmtText.value = "";
        }
        spreadCommentList(bnoVal);
    })
});

function spreadCommentList(bnoVal, page=1){
    getCommentListFromServer(bnoVal,page).then(result => {
        console.log("ph > ", result);
        //댓글 뿌리기
        const ul = document.getElementById('cmtListArea');

        if(result.cmtList.length > 0){
            if(page == 1){
                ul.innerHTML = ""; //반복 전에 기존 샘플 버리기(더보기 버튼에 의한 누적 불가능)
            }
            //댓글 목록 뿌리기
            for(let cvo of result.cmtList){
                let li = `<li class="list-group-item" data-cno="${cvo.cno}">`;
                li += `<div class="list-group-item">`;
                li += `<div class="fw-bold">${cvo.writer}</div>`;
                li += `${cvo.content}<div>`;
                li += `<span class="badge text-bg-light rounded-pill">regDate : ${cvo.regDate}</span>`
                //li += `<span class="badge text-bg-light rounded-pill">${cvo.modAt}</span>`
                if(nickName === cvo.writer){
                    //수정 삭제 버튼 추가
                    li += `<button type="button" data-cno=${cvo.cno} class="btn btn-outline-warning btn-sm mod" data-bs-toggle="modal" data-bs-target="#myModal">%</button>`;
                    li += `<button type="button" data-cno=${cvo.cno} class="btn btn-outline-danger btn-sm del">X</button>`;
                }
                li += `</li>`;
                ul.innerHTML += li;
            }
            // page 처리
            let moreBtn = document.getElementById('moreBtn');
            // 전체 페이지가 현재 페이지보다 크다면(=> 나와야 할 페이지가 존재한다면)
            if(result.pgvo.pageNo < result.realEndPage){
                moreBtn.style.visibility = 'visible';   //버튼 표시
                moreBtn.dataset.page = page + 1;        //1 페이지 증가
            } else{
                moreBtn.style.visibility = 'hidden';    //버튼 숨김
            }
        } else{
            ul.innerHTML = `<li class="list-group-item">Comment List Empty</li>`
        }
    })
};


document.addEventListener('click', (e)=>{
    // 더보기
    if(e.target.id == 'moreBtn'){
        let page = parseInt(e.target.dataset.page);
        spreadCommentList(bnoVal, page);
    }
    // 삭제
    if(e.target.classList.contains('del')){
        let li = e.target.closest('li');
        console.log(li);
        let cno = li.dataset.cno;
        removeCommentToServer(cno).then(result => {
            if(result > 0){
                alert("댓글 삭제 성공");
            } else{
                alert("댓글 삭제 실패");
            }
            spreadCommentList(bnoVal);
        })
    }
    // 수정
    if(e.target.classList.contains('mod')){
        let li = e.target.closest('li');
        let modWriter = li.querySelector('.fw-bold').innerText;
        document.getElementById('cmtWriterMod').value = modWriter;
        let cmtText = li.querySelector('.fw-bold').nextSibling;             //쿼리 값의 같은 부모의 다른 형제 값
        document.getElementById('cmtTextMod').value = cmtText.nodeValue;    //그냥 넣으면 Object로 나옴

        // 수정 버튼 id = cmtModBtn dataset 달기
        document.getElementById('cmtModBtn').setAttribute("data-cno", li.dataset.cno);
    }
    if(e.target.id == 'cmtModBtn'){
        let cmtModData = {
            cno: e.target.dataset.cno,
            content: document.getElementById('cmtTextMod').value
        }
        console.log(cmtModData);
        //비동기 처리
        updateCommentToServer(cmtModData).then(result => {
            if(result == '1'){
                alert("댓글 수정 성공");
            } else{
                alert("댓글 수정 실패");
            }
            //모달창 닫기
            document.querySelector('.btn-close').click();
            spreadCommentList(bnoVal);
        })

    }
});

// 수정_비동기
async function updateCommentToServer(cmtModData) {
    try {
        const url = "/comment/modify";
        const config = {
            method: 'put',
            headers: {
                'Content-Type' : 'application/json; charset=utf-8'
            },
            body: JSON.stringify(cmtModData)
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

// 삭제_비동기
async function removeCommentToServer(cno) {
    try {
        const url = "/comment/remove/"+cno;
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

async function getCommentListFromServer(bnoVal, page) {
    try {
        const resp = await fetch("/comment/list/"+bnoVal+"/"+page);
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }
}

async function postCommentToServer(cmtData) {
    try {
        const url = "/comment/post";
        const config = {
            method: "post",
            headers: {
                'Content-Type' : 'application/json; charset = utf-8'
            },
            body: JSON.stringify(cmtData)
        };
        const resp = await fetch(url, config);
        console.log(resp);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

