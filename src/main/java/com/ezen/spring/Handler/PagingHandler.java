package com.ezen.spring.Handler;

import com.ezen.spring.domain.PagingVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PagingHandler {
    private int startPage;
    private int endPage;
    private int realEndPage;
    private boolean prev, next;

    private int totalCount;
    private PagingVO pgvo;

    public  PagingHandler(PagingVO pgvo, int totalCount){
        this.pgvo = pgvo;
        this.totalCount = totalCount;

        // 1~10 => 10 / 11~20 => 20 / 21~30 => 30 ...
        // (현재 페이지번호 / 10) 올린 후 * 10
        this.endPage = (int)Math.ceil(pgvo.getPageNo() / 10.0)*10;
        this.startPage = endPage - 9;

        this.realEndPage = (int)Math.ceil(totalCount / (double)pgvo.getQty());

        if(realEndPage < endPage){
            this.endPage = realEndPage;
        }

        this.prev = this.startPage > 1;     // 1 11 21
        this.next = this.endPage < realEndPage;
    }
}
