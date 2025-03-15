package org.zerock.club.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.club.dao.GuestbookDAO;
import org.zerock.club.dto.Criteria;
import org.zerock.club.dto.PageMaker;
import org.zerock.club.dto.SearchCriteria;
import org.zerock.club.vo.Guestbook;

@SpringBootTest
public class GuestbookDAOTests {

    @Autowired
    private GuestbookDAO dao;

    @Test
    public void testCreate() {
        Guestbook guestbook = Guestbook.builder()
                .title("Sample Title...")
                .content("Sample Content...")
                .writer("user1")
                .regdate(LocalDateTime.now())
                .moddate(LocalDateTime.now())
                .build();
        dao.create(guestbook);
        System.out.println("/*** guestbook: " + guestbook);
    }

    @Test
    public void testRead() {
        Long gno = 307L;
        Guestbook guestbook = dao.read(gno);
        System.out.println("/*** guestbook: " + guestbook);
    }

    @Test
    public void testUpdate() {
        Guestbook guestbook = Guestbook.builder()
                .gno(307L)
                .title("Update Title")
                .content("Update Content")
                .build();
        dao.update(guestbook);
    }

    @Test
    public void testDelete() {
        Long gno = 307L; // DB에 존재하는 mno
        dao.delete(gno);
    }

    @Test
    public void testListCriteria() {
        Criteria cri = new Criteria();
        cri.setPage(1);

        List<Guestbook> list = dao.listCriteria(cri);

        PageMaker pageMaker = new PageMaker();
        pageMaker.setCri(cri);
        pageMaker.setTotalCount(dao.listCriteriaCount(cri));
        pageMaker.setList(list);

        System.out.println("---------------------------------------");
        System.out.println("목록: " + pageMaker.getList());
        System.out.println("전체 갯수: " + pageMaker.getTotalCount());
        System.out.println("페이지당 갯수: " + pageMaker.getDisplayPageNum());
        System.out.println("첫번째 페이지: " + pageMaker.getStartPage());
        System.out.println("현재 페이지: " + pageMaker.getCri().getPage());
        System.out.println("마지막 페이지: " + pageMaker.getEndPage());
        System.out.println(">: " + pageMaker.isNext());
        System.out.println("<: " + pageMaker.isPrev());
        System.out.println("---------------------------------------");
    }

    @Test
    public void testListSearchCriteria() {
        SearchCriteria cri = new SearchCriteria();
        cri.setPage(2);
        cri.setType("tcw");
        cri.setKeyword("user2");

        List<Guestbook> list = dao.listSearch(cri);

        PageMaker pageMaker = new PageMaker();
        pageMaker.setCri(cri);
        pageMaker.setTotalCount(dao.listSearchCount(cri));
        pageMaker.setList(list);

        System.out.println("---------------------------------------");
        System.out.println("목록: " + pageMaker.getList());
        System.out.println("전체 갯수: " + pageMaker.getTotalCount());
        System.out.println("페이지당 갯수: " + pageMaker.getDisplayPageNum());
        System.out.println("첫번째 페이지: " + pageMaker.getStartPage());
        System.out.println("현재 페이지: " + pageMaker.getCri().getPage());
        System.out.println("마지막 페이지: " + pageMaker.getEndPage());
        System.out.println(">: " + pageMaker.isNext());
        System.out.println("<: " + pageMaker.isPrev());
        System.out.println("---------------------------------------");
    }

}
