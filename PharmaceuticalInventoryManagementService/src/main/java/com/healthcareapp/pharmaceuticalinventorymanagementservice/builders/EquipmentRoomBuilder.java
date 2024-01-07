package com.healthcareapp.pharmaceuticalinventorymanagementservice.builders;

import com.healthcareapp.pharmaceuticalinventorymanagementservice.entities.EquipmentRoom;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.enums.RoomStatus;

public class EquipmentRoomBuilder {
    private Integer roomNo;
    private RoomStatus roomStatus;

    public EquipmentRoomBuilder withRoomNo(Integer roomNo) {
        this.roomNo = roomNo;
        return this;
    }

    public EquipmentRoomBuilder withRoomStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
        return this;
    }

    public EquipmentRoom build() {
        EquipmentRoom equipmentRoom = new EquipmentRoom();
        equipmentRoom.setRoomNo(roomNo);
        equipmentRoom.setRoomStatus(roomStatus);
        return equipmentRoom;
    }
}

