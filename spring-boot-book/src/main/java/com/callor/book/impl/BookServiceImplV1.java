package com.callor.book.impl;

import com.callor.book.model.BookVO;
import com.callor.book.persistence.BookDao;
import com.callor.book.service.BookService;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BookServiceImplV1 implements BookService {

    private final BookDao bookDao;
    public BookServiceImplV1(BookDao bookDao) {
        this.bookDao = bookDao;
    } //alt + insert 누르기

    @Override
    public List<BookVO> selectAll() {
        //조건에 관계없이 데이터를 모두 SELECT 하기
        List<BookVO> bookList = bookDao.findAll();
        return bookList;
    }


    @Override
    public BookVO findById(String isbn) {
    /* JPA 의 findById() 는 return type 이 Optional<T> 이다.
       Optional type 은 데이터가 null 인 경우를 좀 더 쉽게 처리할 수 있도록 도와주는 도구이다.
     */
        Optional<BookVO> opBookVO = bookDao.findById(isbn);

        /* Optional 데이터에서 실제 필요한 데이터(bookVO) 를 추출하기 위해
           get() method 를 사용할 수 있는데, 보통은 orElse() 와 같은 method 를 사용하여
           데이터(bookVO)가 null 인 경우 다른 값을 생성해 절대 데이터가 null 이 될 수 없도록 처리할 수 있다.
         */
        BookVO bookVO = opBookVO.orElse(new BookVO());
        return bookVO;
    }

    @Override
    public int insert(BookVO bookVO) {
        log.debug("서비스 insert{}",bookVO);
        /* mybatis 에서는 insert 를 수행한 후 int 값을 return 한다.
        이 때 return 하는 값은 몇 개의 데이터가 추가되었는지 알려주는 지표이다.
        하지만, spring-data(JPA) 에서는 Save 를 실행한 후
        다시 자기자신(save 를 실행한 데이터)를 return 한다.
         */
        //insert 와 update 를 같이 진행한다.
        BookVO result = bookDao.save(bookVO);
        return 0;
    }

    @Override
    public int update(BookVO bookVO) {
        return 0;
    }

    @Override
    public int delete(String isbn) {
        return 0;
    }

    @Override
    public List<BookVO> findByTitle(String title) {
        return null;
    }

    @Override
    public List<BookVO> findByComp(String comp) {
        return null;
    }

    @Override
    public List<BookVO> findByAuthor(String author) {
        return null;
    }
}
