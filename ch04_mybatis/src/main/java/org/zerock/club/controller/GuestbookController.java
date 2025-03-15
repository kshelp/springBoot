package org.zerock.club.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.club.dto.GuestbookDTO;
import org.zerock.club.dto.PageMaker;
import org.zerock.club.dto.SearchCriteria;
import org.zerock.club.service.GuestbookService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/guestbook")
public class GuestbookController {

  private final GuestbookService service;

  @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Long> create(@RequestBody GuestbookDTO dto) {
    log.info("-----------create-------------------------------");
    log.info(dto);

    Long mno = service.create(dto);

    return new ResponseEntity<>(mno, HttpStatus.OK);
  }

  @GetMapping(value = "/read/{gno}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GuestbookDTO> read(@PathVariable("gno") Long gno) {
    log.info("-----------read-------------------------------");
    log.info("/*** gno: " + gno);

    return new ResponseEntity<>(service.read(gno), HttpStatus.OK);
  }

  @PutMapping(value = "/update", produces = MediaType.TEXT_PLAIN_VALUE)
  public ResponseEntity<String> update(@RequestBody GuestbookDTO dto) {
    log.info("-----------update-------------------------------");
    log.info(dto);

    service.update(dto);

    return new ResponseEntity<>("updated", HttpStatus.OK);
  }

  @DeleteMapping(value = "/delete/{gno}", produces = MediaType.TEXT_PLAIN_VALUE)
  public ResponseEntity<String> delete(@PathVariable("gno") Long gno) {
    log.info("-----------delete-------------------------------");
    log.info(gno);

    service.delete(gno);

    return new ResponseEntity<>("deleted", HttpStatus.OK);

  }

  @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<PageMaker> listPage(SearchCriteria cri) {
    log.info("-----------list-------------------------------");
    log.info("cri: " + cri);

    PageMaker pageMaker = new PageMaker();
    pageMaker.setCri(cri);
    pageMaker.setTotalCount(0);
    pageMaker.setTotalCount(service.listSearchCount(cri));
    pageMaker.setList(service.listSearchCriteria(cri));

    return new ResponseEntity<>(pageMaker, HttpStatus.OK);
  }

}
