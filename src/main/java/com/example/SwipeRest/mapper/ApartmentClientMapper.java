//package com.example.SwipeRest.mapper;
//
//import com.example.SwipeRest.dto.ApartmentDTO;
//import com.example.SwipeRest.entity.Apartment;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//
//import java.util.function.Function;
//
//
//public class ApartmentClientMapper implements Function<Apartment, ApartmentDTO> {
////    public Agent toEntity(AgentDTO agentDTO){
////        return Agent.builder()
//////                .idAgent(agentDTO.get)
////                .number(agentDTO.getNumber())
////                .mail(agentDTO.getMail())
////                .surname(agentDTO.getSurname())
////                .name(agentDTO.getName())
////                .type(agentDTO.getTypeAgent())
//////                .users(agentDTO.getUsers().stream().map(userMapper).toList())
////                .build();
////    }
//
//    @Override
//    public ApartmentDTO apply(Apartment apartment) {
//        return ApartmentDTO.builder()
//                .idApartment(apartment.getIdApartment())
//                .calculation(apartment.getCalculation())
//                .commission(apartment.getCommission())
//                .communicationType(apartment.getCommunicationType())
//                .countRoom(apartment.getCountRoom())
//                .description(apartment.getDescription())
//                .foundingDocument(apartment.getFoundingDocument())
//                .heatingType(apartment.getHeatingType())
//                .price(apartment.getPrice())
//                .layout(apartment.getLayout())
//                .state(apartment.getState())
//                .kitchenArea(apartment.getKitchenArea())
//                .type(apartment.getType())
//                .userDTO(userMapper.apply(apartment.getUser()))
//                .mainPhoto(apartment.getMainPhoto())
//                .totalArea(apartment.getTotalArea())
//                .number(apartment.getNumber())
//                .build();
//
//    }
//}
