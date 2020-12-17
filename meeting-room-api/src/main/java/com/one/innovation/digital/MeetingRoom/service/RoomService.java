package com.one.innovation.digital.MeetingRoom.service;


import com.one.innovation.digital.MeetingRoom.exception.ResourceNotFoundException;
import com.one.innovation.digital.MeetingRoom.model.dto.RoomDTO;
import com.one.innovation.digital.MeetingRoom.model.entity.Room;
import com.one.innovation.digital.MeetingRoom.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RoomService {

    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;

    public List<RoomDTO> getAll() {
        return roomRepository.findAll().stream()
                            .map(room -> modelMapper.map(room, RoomDTO.class))
                            .collect(Collectors.toList());
    }


    public ResponseEntity <RoomDTO> getById(long id) throws ResourceNotFoundException {
        Room room = verifyIfExist(id);
        return ResponseEntity.ok().body( modelMapper.map( room, RoomDTO.class ) );
    }



    public Room createRoom(RoomDTO roomDTO) {
        Room roomToSave = modelMapper.map(roomDTO, Room.class);
        return roomRepository.save(roomToSave);
    }


    public ResponseEntity<RoomDTO> updateRoom(long id, RoomDTO roomDTO) throws ResourceNotFoundException {
        verifyIfExist(id);
        Room roomToUpdate = modelMapper.map(roomDTO, Room.class);
        Room updatedRoom = roomRepository.save(roomToUpdate);
        return ResponseEntity.ok().body(modelMapper.map(updatedRoom, RoomDTO.class));

    }

    private Room verifyIfExist(long id) throws ResourceNotFoundException {
        return roomRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Room not found:" + id));
    }

    public Map<String, Boolean> deleteRoom(long id) throws ResourceNotFoundException {
       Room roomToDelete = verifyIfExist(id);
        roomRepository.delete(roomToDelete);
        Map<String ,Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
