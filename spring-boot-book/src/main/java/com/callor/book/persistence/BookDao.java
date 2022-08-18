package com.callor.book.persistence;

import com.callor.book.model.BookVO;
import org.springframework.data.jpa.repository.JpaRepository;
                                //CRUD 가 자동으로 만들어진다.
public interface BookDao extends JpaRepository<BookVO,String> {

}
