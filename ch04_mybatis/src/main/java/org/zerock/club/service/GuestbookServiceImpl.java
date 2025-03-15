package org.zerock.club.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.club.dao.GuestbookDAO;
import org.zerock.club.dto.Criteria;
import org.zerock.club.dto.GuestbookDTO;
import org.zerock.club.dto.SearchCriteria;
import org.zerock.club.vo.Guestbook;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class GuestbookServiceImpl implements GuestbookService {

    private final GuestbookDAO dao;

    @Override
    public Long create(GuestbookDTO dto) {
        dto.setRegdate(LocalDateTime.now());
        dto.setModdate(LocalDateTime.now());

        Guestbook guestbook = dtoToVO(dto);

        log.info("==========================");
        log.info(guestbook);

        Long gno = dao.create(guestbook);

        return gno;
    }

    @Override
    public GuestbookDTO read(Long gno) {
        return voToDTO(dao.read(gno));
    }

    @Override
    public void update(GuestbookDTO dto) {
        dao.update(dtoToVO(dto));
    }

    @Override
    public void delete(Long gno) {
        dao.delete(gno);
    }

    @Override
    public List<GuestbookDTO> listCriteria(Criteria cri) {
        List<Guestbook> list = dao.listCriteria(cri);
        return list.stream().map(vo -> voToDTO(vo)).toList();
    }

    @Override
    public int listCountCriteria(Criteria cri) {
        return dao.listCriteriaCount(cri);
    }

    @Override
    public List<GuestbookDTO> listSearchCriteria(SearchCriteria cri) {
        List<Guestbook> list = dao.listSearch(cri);
        return list.stream().map(vo -> voToDTO(vo)).toList();
    }

    @Override
    public int listSearchCount(SearchCriteria cri) {
        return dao.listSearchCount(cri);
    }

}
