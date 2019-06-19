package com.aries.canis.service.impl;

import com.aries.canis.service.UserService;
import com.aries.user.gaea.client.model.GaeaResponse;
import com.aries.user.gaea.client.utils.UserUtils;
import com.aries.user.gaea.contact.model.UserInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public List<UserInfo> getAdminList() {
        GaeaResponse gaeaResponse = UserUtils.getUserListByBizType(3);
        if (gaeaResponse == null || gaeaResponse.getData() == null) {
            return new ArrayList<>();
        }
        List<UserInfo> userInfoList = (List) gaeaResponse.getData();
        return userInfoList;
    }

    @Override
    public List<UserInfo> checkAdminExist(String nickname, String phoneNumber) {
        GaeaResponse gaeaResponse = UserUtils.getUserListByBizType(0);
        if (gaeaResponse == null || gaeaResponse.getData() == null) {
            return Collections.emptyList();
        }
        List<UserInfo> userInfoList = (List) gaeaResponse.getData();
        List<UserInfo> list = userInfoList.stream()
                .filter(userInfo -> userInfo.getNickname().equals(nickname) && userInfo.getPhoneNumber().equals(phoneNumber))
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public List<UserInfo> checkAdminIsAdmin(String nickname, String phoneNumber) {
        GaeaResponse gaeaResponse = UserUtils.getUserListByBizType(3);
        if (gaeaResponse == null || gaeaResponse.getData() == null) {
            return Collections.emptyList();
        }
        List<UserInfo> userInfoList = (List) gaeaResponse.getData();
        List<UserInfo> list = userInfoList.stream()
                .filter(userInfo -> userInfo.getNickname().equals(nickname) && userInfo.getPhoneNumber().equals(phoneNumber))
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public void addAdmin(Long id) {
        GaeaResponse gaeaResponse = UserUtils.updateUserInfoByBizType(id, 3);
    }

    @Override
    public void reset(Long id) {
        UserUtils.updateUserInfoByBizType(id, 0);
    }
}
