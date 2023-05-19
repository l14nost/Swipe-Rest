package com.example.SwipeRest.mapper;

import com.example.SwipeRest.dto.ClientDTO;
import com.example.SwipeRest.dto.UserDTO;
import com.example.SwipeRest.entity.User;
import com.example.SwipeRest.entity.UserAddInfo;
import com.example.SwipeRest.enums.Role;
import com.example.SwipeRest.enums.TypeUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;
@Component
@RequiredArgsConstructor
public class UserMapper implements Function<User, UserDTO> {
    private final AgentMapper agentMapper;
    private final UserAddInfoMapper userAddInfoMapper;
    public User toEntity(UserDTO userDTO){
        User user = User.builder()
//                .idUser(userDTO.getIdUser())
                .name(userDTO.getName())
                .surname(userDTO.getSurname())
                .typeUser(userDTO.getTypeUser())
//                .agent(agentMapper.toEntity(userDTO.getAgent()))
                .role(Role.USER)
                .mail(userDTO.getMail())
                .filename(userDTO.getFileName())
//                .userAddInfo(userAddInfoMapper.toEntity(userDTO.getUserAddInfo()))
                .build();
        if (userDTO.getAgent()!=null){
            user.setAgent(agentMapper.toEntity(userDTO.getAgent()));
        }
        if(userDTO.getUserAddInfo()!=null){
            user.setUserAddInfo(userAddInfoMapper.toEntity(userDTO.getUserAddInfo()));
        }
        return user;
    }

    @Override
    public UserDTO apply(User user) {

        UserDTO build = UserDTO.builder().idUser(user.getIdUser())
                .name(user.getName())
                .mail(user.getMail())
                .surname(user.getSurname())
//                .agentDTO(agentMapper.apply(user.getAgent()))
//                .userAddInfoDTO(userAddInfoMapper.apply(user.getUserAddInfo()))
                .typeUser(user.getTypeUser())
                .fileName(user.getFilename())
                .number(user.getNumber())
//                .black_list(user.isBlackList())
                .build();
        if (user.getAgent()!=null){
            build.setAgent(agentMapper.apply(user.getAgent()));
        }
        if (user.getUserAddInfo()!=null){
            build.setUserAddInfo(userAddInfoMapper.apply(user.getUserAddInfo()));
        }

        return build;

    }
}
