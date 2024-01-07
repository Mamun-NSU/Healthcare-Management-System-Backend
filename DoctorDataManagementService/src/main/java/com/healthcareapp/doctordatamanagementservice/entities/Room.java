package com.healthcareapp.doctordatamanagementservice.entities;

import com.healthcareapp.doctordatamanagementservice.enums.RoomStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "room_id")
    private UUID roomId;

    @Column(name = "room_no")
    private int roomNo;
    @Column(name = "room_status")
    private RoomStatus roomStatus;

    // Constructors, getters, and setters can be added as needed.
}
