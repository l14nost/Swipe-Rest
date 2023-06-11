package com.example.SwipeRest.service.impl;

//import com.example.Swipe.Admin.entity.SalesDepartment;
import com.example.SwipeRest.controller.ApartmentController;
import com.example.SwipeRest.entity.UserAddInfo;
import com.example.SwipeRest.repository.UserAddInfoRepo;
import com.example.SwipeRest.service.UserAddInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAddInfoServiceImpl implements UserAddInfoService {
    private final UserAddInfoRepo userAddInfoRepo;
    private Logger log = LoggerFactory.getLogger(UserAddInfoServiceImpl.class);

    @Override
    public List<UserAddInfo> findAll() {
        log.info("All userAddInfo");
        return userAddInfoRepo.findAll();
    }

    @Override
    public UserAddInfo findById(int id) {
        log.info("Find userAddInfo "+id);
        return userAddInfoRepo.findById(id).orElse(null);
    }

    @Override
    public void saveEntity(UserAddInfo userAddInfo) {
        userAddInfoRepo.save(userAddInfo);
        log.info("UserAddInfo save");
    }

    @Override
    public void deleteById(int id) {
        userAddInfoRepo.deleteById(id);
        log.info("UserAddInfo delete "+id);

    }
    @Override
    public void updateEntity(UserAddInfo userAddInfo, int id) {
        Optional<UserAddInfo> userOptional = userAddInfoRepo.findById(id);
        if (userOptional.isPresent()) {
            UserAddInfo updateUser = userOptional.get();
            if (userAddInfo.getDateSub() != null) {
                updateUser.setDateSub(userAddInfo.getDateSub());
            }
            if (userAddInfo.getTypeNotification() != null) {
                updateUser.setTypeNotification(userAddInfo.getTypeNotification());
            }
            updateUser.setCallSms(userAddInfo.isCallSms());
            userAddInfoRepo.saveAndFlush(updateUser);
            log.info("UserAddInfo update "+id);

        }
    }
}
