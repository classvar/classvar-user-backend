package com.classvar.manager.application.dto.response;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetManagerListDto {

  List<GetManagerDto> managers = new ArrayList<>();
}
