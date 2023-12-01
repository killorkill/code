package com.example.banlinhkien.converter;


import com.example.banlinhkien.entity.Role;
import com.example.banlinhkien.models.RoleDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter {
    private ModelMapper modelMapper;

    public RoleConverter() {
        modelMapper = new ModelMapper();
    }

    public RoleDTO toDTO(Role role) {
        RoleDTO roleDTO = modelMapper.map(role, RoleDTO.class);

        return roleDTO;
    }

    public Role toEntity(RoleDTO roleDTO) {
        Role role = modelMapper.map(roleDTO, Role.class);

        return role;
    }
}
