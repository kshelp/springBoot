package org.zerock.club.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Guestbook {
    private Long gno;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime regdate;
    private LocalDateTime moddate;
}
