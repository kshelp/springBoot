package org.zerock.club.service;

import java.util.List;

import org.zerock.club.dto.Criteria;
import org.zerock.club.dto.GuestbookDTO;
import org.zerock.club.dto.SearchCriteria;
import org.zerock.club.vo.Guestbook;

public interface GuestbookService {

  public Long create(GuestbookDTO dto);

  public GuestbookDTO read(Long gno);

  public void update(GuestbookDTO dto);

  public void delete(Long gno);

  public List<GuestbookDTO> listCriteria(Criteria cri);

  public int listCountCriteria(Criteria cri);

  public List<GuestbookDTO> listSearchCriteria(SearchCriteria cri);

  public int listSearchCount(SearchCriteria cri);

  default Guestbook dtoToVO(GuestbookDTO dto) {
    return Guestbook.builder()
        .gno(dto.getGno())
        .title(dto.getTitle())
        .content(dto.getContent())
        .writer(dto.getWriter())
        .regdate(dto.getRegdate())
        .moddate(dto.getModdate())
        .build();
  }

  default GuestbookDTO voToDTO(Guestbook guestbook) {
    return GuestbookDTO.builder()
        .gno(guestbook.getGno())
        .title(guestbook.getTitle())
        .content(guestbook.getContent())
        .writer(guestbook.getWriter())
        // .regdate(guestbook.getRegdate())
        // .moddate(guestbook.getModdate())
        .build();
  }

}
