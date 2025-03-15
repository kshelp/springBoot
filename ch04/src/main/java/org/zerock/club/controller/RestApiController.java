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
import org.zerock.club.dto.PageRequestDTO;
import org.zerock.club.dto.PageResultDTO;
import org.zerock.club.entity.Guestbook;
import org.zerock.club.service.GuestbookService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/api/guestbook/")
@RequiredArgsConstructor
public class RestApiController {

    private final GuestbookService service; // final로 선언

    @PostMapping(value = "/create")
    public ResponseEntity<Long> create(@RequestBody GuestbookDTO dto) {
        log.info("-----------create-------------------------------");
        log.info(dto);

        Long gno = service.register(dto);

        return new ResponseEntity<>(gno, HttpStatus.OK);
    }

    @GetMapping(value = "/read/{gno}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GuestbookDTO> read(@PathVariable("gno") Long gno) {
        log.info("-----------read-------------------------------");
        log.info(gno);
        return new ResponseEntity<>(service.read(gno), HttpStatus.OK);
    }

    @PutMapping(value = "/update", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> modify(@RequestBody GuestbookDTO dto) {
        log.info("-----------update-------------------------------");
        log.info(dto);

        service.modify(dto);

        return new ResponseEntity<>("modified", HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{gno}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> remove(@PathVariable("gno") Long gno) {
        log.info("-----------remove-------------------------------");
        log.info(gno);

        service.remove(gno);

        return new ResponseEntity<>("removed", HttpStatus.OK);
    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResultDTO<GuestbookDTO, Guestbook>> getList(PageRequestDTO dto) {
        log.info("-----------getList-------------------------------");
        log.info(dto);

        return new ResponseEntity<>(service.getList(dto), HttpStatus.OK);
    }

}
