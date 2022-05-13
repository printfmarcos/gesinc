package br.com.una.Gesinc.Controller;

import br.com.una.Gesinc.Domain.Incident;
import br.com.una.Gesinc.Dto.IncidentDto;
import br.com.una.Gesinc.Form.IncidentForm;
import br.com.una.Gesinc.Repository.IncidentRepository;
import br.com.una.Gesinc.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/incident")
public class IncidentController {

    @Autowired
    private IncidentRepository incidentRepository;

    @Autowired
    private UserRepository userRepository;

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
        return IncidentDto.convertToDto(incidents);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<IncidentDto> register (@RequestBody IncidentForm incidentForm, UriComponentsBuilder uriBuilder){

        System.out.println(incidentForm.getAttendant());
        System.out.println(incidentForm.getRequester());
        System.out.println(incidentForm.getDescription());
        System.out.println(incidentForm.getPriority());
        System.out.println("--------------- dados do form passando corretamete------------");

        Incident incident = incidentForm.convertToEntity(userRepository);

        incidentRepository.save(incident);

        System.out.println("--------------- dados do incidente novo cadastrado corretamente------------");
        System.out.println(incident);

        URI uri = uriBuilder.path("/incident/{id}").buildAndExpand(incident.getId()).toUri();

        return ResponseEntity.created(uri).body(new IncidentDto(incident));

//        return ResponseEntity.ok(new IncidentDto(incident));

    }
}
