package com.example.SwipeRest.mapper;

import com.example.SwipeRest.dto.ClientDTO;
import com.example.SwipeRest.entity.User;
import com.example.SwipeRest.enums.Role;

public class UserMapper{
    public static User toEntity(ClientDTO clientDTO){
        User user = User.builder()
                .idUser(clientDTO.getIdUser())
                .name(clientDTO.getName())
                .surname(clientDTO.getSurname())
                .typeUser(clientDTO.getTypeUser())
                .role(Role.USER)
                .mail(clientDTO.getMail())
                .filename(clientDTO.getFileName())
                .number(clientDTO.getNumber())
                .blackList(false)
                .role(clientDTO.getRole())
                .build();
        if (clientDTO.getAgent()!=null){
            user.setAgent(AgentMapper.toEntity(clientDTO.getAgent()));
        }
        if(clientDTO.getUserAddInfo()!=null){
            user.setUserAddInfo(UserAddInfoMapper.toEntity(clientDTO.getUserAddInfo()));
        }
        return user;
    }

    public static ClientDTO apply(User user) {

        ClientDTO build = ClientDTO.builder().idUser(user.getIdUser())
                .name(user.getName())
                .mail(user.getMail())
                .surname(user.getSurname())
//                .agentDTO(agentMapper.apply(user.getAgent()))
//                .userAddInfoDTO(userAddInfoMapper.apply(user.getUserAddInfo()))
                .typeUser(user.getTypeUser())
                .fileName(user.getFilename())
                .number(user.getNumber())
                .blackList(user.isBlackList())
                .role(user.getRole())
//                .black_list(user.isBlackList())
                .build();
        if (user.getAgent()!=null){
            build.setAgent(AgentMapper.apply(user.getAgent()));
        }
        if (user.getUserAddInfo()!=null){
            build.setUserAddInfo(UserAddInfoMapper.apply(user.getUserAddInfo()));
        }

        return build;

    }
}
