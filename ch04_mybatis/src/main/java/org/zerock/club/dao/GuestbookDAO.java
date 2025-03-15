package org.zerock.club.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.zerock.club.dto.Criteria;
import org.zerock.club.dto.SearchCriteria;
import org.zerock.club.vo.Guestbook;

@Mapper
public interface GuestbookDAO {

    public Long create(Guestbook guestbook);

    public Guestbook read(Long gno);

    public void update(Guestbook guestbook);

    public void delete(Long gno);

    public List<Guestbook> listCriteria(Criteria cri);

    public int listCriteriaCount(Criteria cri);

    public List<Guestbook> listSearch(SearchCriteria cri);

    public int listSearchCount(SearchCriteria cri);

}
