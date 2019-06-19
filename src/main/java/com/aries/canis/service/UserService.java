package com.aries.canis.service;

import com.aries.user.gaea.contact.model.UserInfo;

import java.util.List;

public interface UserService {
    List<UserInfo> getAdminList();

    List<UserInfo> checkAdminExist(String nickname, String phoneNumber);

    List<UserInfo> checkAdminIsAdmin(String nickname, String phoneNumber);

    void addAdmin(Long id);

    void reset(Long id);
}
