package com.one.innovation.digital.MeetingRoom.controller;

import com.one.innovation.digital.MeetingRoom.exception.ResourceNotFoundException;
import com.one.innovation.digital.MeetingRoom.model.dto.RoomDTO;
import com.one.innovation.digital.MeetingRoom.model.entity.Room;
import com.one.innovation.digital.MeetingRoom.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value ="/api/v1/room")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RoomController {

    private RoomService roomService;

    @GetMapping
    public List<RoomDTO> getAll() {
        return roomService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDTO> getById(@PathVariable long id) throws ResourceNotFoundException {
        return roomService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Room create(@Valid @RequestBody RoomDTO roomDTO) {
        return roomService.createRoom(roomDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity <RoomDTO> updateRoom(@PathVariable long id, @Valid @RequestBody RoomDTO roomDTO) throws ResourceNotFoundException {
        return roomService.updateRoom(id, roomDTO);
    }
    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteRoom(@PathVariable long id) throws ResourceNotFoundException {
        return roomService.deleteRoom(id);
    }
}
