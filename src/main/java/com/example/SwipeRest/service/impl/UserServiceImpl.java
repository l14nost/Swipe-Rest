package com.example.SwipeRest.service.impl;


import com.example.SwipeRest.controller.ApartmentController;
import com.example.SwipeRest.dto.BlackListDTO;
import com.example.SwipeRest.dto.ClientDTO;
import com.example.SwipeRest.entity.Agent;
import com.example.SwipeRest.entity.User;
import com.example.SwipeRest.enums.TypeUser;
import com.example.SwipeRest.mapper.AgentMapper;
import com.example.SwipeRest.mapper.BlackLIstMapper;
import com.example.SwipeRest.mapper.UserMapper;
import com.example.SwipeRest.repository.UserRepo;
import com.example.SwipeRest.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserAddInfoServiceImpl userAddInfoService;
    private final AgentServiceImpl agentService;
    private final UserRepo userRepo;
    private Logger log = LoggerFactory.getLogger(UserServiceImpl.class);



    public List<ClientDTO> findAllByType(TypeUser typeUser){
        log.info("All "+typeUser);
        return userRepo.findAllByTypeUserAndBlackListIsFalse(typeUser).stream().map(UserMapper::apply).collect(Collectors.toList());
    }

    public String addDTO(ClientDTO clientDTO){
            User user = UserMapper.toEntity(clientDTO);
            if (clientDTO.getAgent()!=null) {
                Agent agent = AgentMapper.toEntity(clientDTO.getAgent());
                agentService.saveEntity(agent);
                log.info("Agent for user save");
                user.setAgent(agent);
            }
            userRepo.save(user);
            log.info("User save");

            return "Save";
    }
    public List<BlackListDTO> blackListDTO(){
        log.info("Blacklist");
        return userRepo.findAllByBlackListIsTrue().stream().map(BlackLIstMapper::apply).collect(Collectors.toList());
    }
    @Override
    public List<User> findAll() {
        log.info("All user");
        return userRepo.findAll();
    }
    public ClientDTO findByIdDTO(int id) {
        Optional<User> user = userRepo.findById(id);
        if(user.isPresent()){
            log.info("User find "+id);
            return UserMapper.apply(user.get());
        }
        else {
            log.info("User not find "+id);
            return null;
        }
    }
    @Override
    public User findById(int id) {
        Optional<User> user = userRepo.findById(id);
        if(user.isPresent()){
            log.info("User find "+id);
            return user.get();
        }
        else {
            log.info("User not find "+id);
            return null;
        }
    }

    @Override
    public void saveEntity(User user) {
        userRepo.save(user);
        log.info("User save");

    }

    @Override
    public void deleteById(int id) {
        userRepo.deleteById(id);
        log.info("User delete "+id);

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
                if (updateUser.getAgent()!=null) {
                    agentService.updateEntity(user.getAgent(), user.getAgent().getIdAgent());
                    log.info("Agent update "+user.getAgent().getIdAgent());

                }
            }
            updateUser.setBlackList(user.isBlackList());


            userRepo.saveAndFlush(updateUser);
            log.info("User update "+id);
        }
    }
    public String updateDto(ClientDTO clientDTO, int id) {
        User user = UserMapper.toEntity(clientDTO);
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
            if (user.getUserAddInfo()!=null){
                if (updateUser.getUserAddInfo()!=null) {
                    userAddInfoService.updateEntity(user.getUserAddInfo(), updateUser.getUserAddInfo().getIdUserAddInfo());
                    log.info("UserAddInfo update "+updateUser.getUserAddInfo().getIdUserAddInfo());
                }


            }
            if (user.getAgent()!=null){
                agentService.updateEntity(user.getAgent(), updateUser.getAgent().getIdAgent());
                log.info("Agent update "+updateUser.getAgent().getIdAgent());

            }
            updateUser.setBlackList(user.isBlackList());
            userRepo.saveAndFlush(updateUser);
            log.info("User update "+id);

            return "Success update:\n" +
                    "Update user:\n"+UserMapper.apply(updateUser);

        }
        return "Something went wrong";
    }

    public String addToBlackList(int id) {
        User user = userRepo.findById(id).orElseThrow();
        user.setBlackList(true);
        userRepo.saveAndFlush(user);
        log.info("User add to blackList "+id);
        return "Success add";
    }
    public String removeFromBlackList(int id) {
        User user = userRepo.findById(id).orElseThrow();
        user.setBlackList(false);
        userRepo.saveAndFlush(user);
        log.info("User remove from blackList "+id);
        return "Success remove";
    }
}
