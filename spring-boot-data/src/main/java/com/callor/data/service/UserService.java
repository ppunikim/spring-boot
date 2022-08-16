package com.callor.data.service;

import com.callor.data.model.UserVO;

import java.util.List;

public interface UserService {
    public List<UserVO> selectAll();
    public UserVO findById(Long seq);
    public void insert(UserVO userVO);
    public void update(UserVO userVO);
    public void delete(Long seq);       //번호를 가지고 삭제할 것 이므로 Long 사용하기

}//end class
