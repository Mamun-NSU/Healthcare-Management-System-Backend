package com.healthcareapp.doctordatamanagementservice.models;

import com.healthcareapp.doctordatamanagementservice.enums.Specialities;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class SpecialityQueryDTO {
    private Specialities specialities;
}
