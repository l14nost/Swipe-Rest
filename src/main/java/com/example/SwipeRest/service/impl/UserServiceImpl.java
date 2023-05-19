package com.example.SwipeRest.service.impl;

//import com.example.Swipe.Admin.entity.Contractor;
import com.example.SwipeRest.dto.BlackListDTO;
import com.example.SwipeRest.dto.UserDTO;
import com.example.SwipeRest.entity.Agent;
import com.example.SwipeRest.entity.User;
import com.example.SwipeRest.entity.UserAddInfo;
import com.example.SwipeRest.enums.TypeUser;
import com.example.SwipeRest.mapper.AgentMapper;
import com.example.SwipeRest.mapper.BlackLIstMapper;
import com.example.SwipeRest.mapper.UserAddInfoMapper;
import com.example.SwipeRest.mapper.UserMapper;
import com.example.SwipeRest.repository.AgentRepo;
import com.example.SwipeRest.repository.UserAddInfoRepo;
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
    private final UserAddInfoRepo userAddInfoRepo;
    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final AgentMapper agentMapper;
    private final BlackLIstMapper blackLIstMapper;
    private final AgentRepo agentRepo;
    private final UserAddInfoMapper userAddInfoMapper;



    public List<UserDTO> findAllByType(TypeUser typeUser){
        return userRepo.findAllByTypeUserAndBlackListIsFalse(typeUser).stream().map(userMapper).toList();
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
    public String addDTO(UserDTO userDTO){
        try {
            User user = userMapper.toEntity(userDTO);
            if (userDTO.getAgent()!=null) {
                Agent agent = agentMapper.toEntity(userDTO.getAgent());
                agentRepo.saveAndFlush(agent);
                user.setAgent(agent);
            }
            userRepo.save(user);
            return "Save";
        }
        catch (Exception e){
            return "No Save";
        }
    }
    public List<UserDTO> findAllDTO() {
    return userRepo.findAll().stream().map(userMapper).toList();
}
    public List<BlackListDTO> blackListDTO(){
        return userRepo.findAllByBlackListIsTrue().stream().map(blackLIstMapper).toList();
    }
    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }
    public UserDTO findByIdDTO(int id) {
        Optional<User> user = userRepo.findById(id);
        if(user.isPresent()){
            return userMapper.apply(user.get());
        }
        else {
            return null;
        }
    }
    @Override
    public User findById(int id) {
        Optional<User> user = userRepo.findById(id);
        if(user.isPresent()){
            return user.get();
        }
        else {
            return null;
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
//            if(user.getAgent()!=null){
//                updateUser.setAgent(user.getAgent());
//            }
            updateUser.setBlackList(user.isBlackList());


            userRepo.saveAndFlush(updateUser);
        }
    }
}
