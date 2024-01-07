package com.healthcareapp.pharmaceuticalinventorymanagementservice.models;

import com.healthcareapp.pharmaceuticalinventorymanagementservice.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicineQueryDTO {
    private Category category;
}
