package com.classvar.manager.application.common;

import com.classvar.manager.application.dto.request.CreateManagerDto;
import com.classvar.manager.application.dto.response.GetManagerDto;
import com.classvar.manager.domain.Manager;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ManagerMapper {

    GetManagerDto toManagerInfoDto(Manager manager);

    default List<Manager> toManagers(CreateManagerDto dto) {
        List<Manager> managers = new ArrayList<>();
        for (String email : dto.getEmails()) {
            managers.add(new Manager(dto.getCourseId(), email));
        }

        return managers;
    }
}
