package br.com.una.Gesinc.Controller;

import br.com.una.Gesinc.Domain.Incident;
import br.com.una.Gesinc.Dto.IncidentDto;
import br.com.una.Gesinc.Repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/incident")
public class IncidentController {

    @Autowired
    private IncidentRepository incidentRepository;

    @GetMapping
    @Cacheable(value = "incidentList")
    public Page<IncidentDto> list(@RequestParam(required = false) Long userId,
          @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable pagination) {

        Page<Incident> incidents;
        if(userId == null){
            incidents = incidentRepository.findAll(pagination);
        }
        else{
            incidents = incidentRepository.findByRequesterId(userId, pagination);
        }
        return IncidentDto.convert(incidents);
    }
}
