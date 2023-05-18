package com.example.SwipeRest.service.impl;

//import com.example.Swipe.Admin.entity.Contractor;
import com.example.SwipeRest.dto.BlackListDTO;
import com.example.SwipeRest.entity.User;
import com.example.SwipeRest.enums.TypeUser;
import com.example.SwipeRest.mapper.BlackLIstMapper;
import com.example.SwipeRest.mapper.UserMapper;
import com.example.SwipeRest.repository.UserRepo;
import com.example.SwipeRest.service.UserService;
import com.example.SwipeRest.specification.BlackListSpecification;
import com.example.SwipeRest.specification.UserSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final BlackLIstMapper blackLIstMapper;



    public List<User> findAllByType(TypeUser typeUser){
        return userRepo.findAllByTypeUserAndBlackListIsFalse(typeUser);
    }
//    public Page<UserDTO> specificationForBlackList(String keyWord, Pageable pageable){
//        BlackListSpecification blackListSpecification = BlackListSpecification.builder().keyWord(keyWord).build();
//        return userRepo.findAll(blackListSpecification,pageable).map(userMapper);
//    }
    public List<User> findTen(){
        List<User> users = new ArrayList<>();
        List<User> allUser = userRepo.findAll();
        for (int i = 0; i<2;i++){
            users.add(allUser.get(i));
        }
        return users;
     }
//    public Page<User> findAllByTypePagination(TypeUser typeUser, Pageable pageable,String keyWord){
//        if (!keyWord.equals("null")) {
//            UserSpecification blackListSpecification = UserSpecification.builder().keyWord(keyWord).typeUser(typeUser).build();
//            return userRepo.findAll(blackListSpecification, pageable);
//        }
//        return userRepo.findAllByTypeUserAndBlackListIsFalse(typeUser,pageable);
//    }
//    public Page<BlackListDTO> blackList(Pageable pageable, String keyWord){
//        if (!keyWord.equals("null")) {
//            BlackListSpecification blackListSpecification = BlackListSpecification.builder().keyWord(keyWord).build();
//            return userRepo.findAll(blackListSpecification, pageable).map(blackLIstMapper);
//        }
//        else return userRepo.findAllByBlackListIsTrue(pageable).map(blackLIstMapper);
//    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public User findById(int id) {
        Optional<User> user = userRepo.findById(id);
        if(user.isPresent()){
            return user.get();
        }
        else {
            return User.builder().build();
        }
    }

    @Override
    public void saveEntity(User user) {

        userRepo.save(user);

    }

    @Override
    public void deleteById(int id) {
        userRepo.deleteById(id);
    }

    @Override
    public void updateEntity(User user, int id) {
        Optional<User> userOptional = userRepo.findById(id);
        if (userOptional.isPresent()){
            User updateUser = userOptional.get();
            if(user.getFilename()!=null){
                updateUser.setFilename(user.getFilename());
            }
            if(user.getName()!=null){
                updateUser.setName(user.getName());
            }
            if(user.getSurname()!=null){
                updateUser.setSurname(user.getSurname());
            }
            if(user.getMail()!=null){
                updateUser.setMail(user.getMail());
            }
            if (user.getNumber() != null) {
                updateUser.setNumber(user.getNumber());
            }
            if(user.getAgent()!=null){
                updateUser.setAgent(user.getAgent());
            }
            updateUser.setBlackList(user.isBlackList());


            userRepo.saveAndFlush(updateUser);
        }
    }
}
