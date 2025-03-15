package org.zerock.club.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.club.entity.Memo;

@SpringBootTest
public class MemoRepositoryTests {

    @Autowired
    MemoRepository memoRepository;

    @Test
    public void testClass() {
        System.out.println("/*** testClass(): " + memoRepository.getClass().getName());
    }

    @Test
    public void testInsertDummies() {
        // IntStream.rangeClosed(1, 100).forEach(i -> {
        for (int i = 1; i <= 100; i++) {
            Memo memo = Memo.builder().memoText("Sample..." + i).build();
            memoRepository.save(memo);
        }
    }

    @Test
    public void testSelect() {
        Long mno = 98L; // DB에 존재하는 mno
        Memo memo = memoRepository.findById(mno).get();
        System.out.println("/*** testSelect(): " + memo);
    }

    @Test
    public void testUpdate() {
        Memo memo = Memo.builder().mno(100L).memoText("Update Text").build();
        System.out.println("/*** testUpdate(): " + memoRepository.save(memo));
    }

    @Test
    public void testDelete() {
        Long mno = 100L; // DB에 존재하는 mno
        memoRepository.deleteById(mno);
    }

    @Test
    public void testPageDefault() {
        // 1페이지 10개
        Pageable pageable = PageRequest.of(0, 10);
        Page<Memo> result = memoRepository.findAll(pageable);

        System.out.println("---------------------------------------");
        System.out.println("result: " + result);
        System.out.println("Total Count: " + result.getTotalElements());
        System.out.println("Page Number: " + result.getNumber());
        System.out.println("Page Size: " + result.getSize());
        System.out.println("has next page?: " + result.hasNext());
        System.out.println("first page?: " + result.isFirst());
        System.out.println("---------------------------------------");

        for (Memo memo : result.getContent()) {
            System.out.println(memo);
        }
    }

    @Test
    public void testSort() {
        Sort sort1 = Sort.by("mno").descending();
        Pageable pageable = PageRequest.of(0, 10, sort1);
        Page<Memo> result = memoRepository.findAll(pageable);

        for (Memo memo : result.getContent()) {
            System.out.println(memo);
        }

        // result.get().forEach(memo -> {
        // System.out.println(memo);
        // });
    }

    @Test
    public void testQueryMethods() {
        List<Memo> list = memoRepository.findByMnoBetweenOrderByMnoDesc(70L, 80L);

        for (Memo memo : list) {
            System.out.println(memo);
        }
    }

    @Test
    public void testQueryMethodWithPagable() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());
        Page<Memo> result = memoRepository.findByMnoBetween(45L, 50L, pageable);

        result.get().forEach(memo -> System.out.println(memo));
    }

    @Commit
    @Transactional
    @Test
    public void testDeleteQueryMethods() {
        memoRepository.deleteMemoByMnoLessThan(10L);
    }

}
