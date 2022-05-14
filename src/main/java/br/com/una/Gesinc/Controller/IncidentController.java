package br.com.una.Gesinc.Controller;

import br.com.una.Gesinc.Domain.Incident;
import br.com.una.Gesinc.Dto.IncidentDto;
import br.com.una.Gesinc.Enum.UserType;
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
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/incident")
public class IncidentController {

    @Autowired
    private IncidentRepository incidentRepository;

    @Autowired
    private UserRepository userRepository;

    /*
    * Sem parametro : Retorna a lista de todos os incidentes cadastrados
    * Com parametro:  Retorna a lista de todos os incidentes abertos por este solicitante
    * */
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

    /*
    * Cadastro de um novo incidente*/
    @PostMapping
    @Transactional
    @CacheEvict(value = "incidentList", allEntries = true)
    public ResponseEntity<IncidentDto> register (@RequestBody IncidentForm incidentForm, UriComponentsBuilder uriBuilder){

        Incident incident = incidentForm.convertToEntity(userRepository);
        incidentRepository.save(incident);

        URI uri = uriBuilder.path("/incident/{id}").buildAndExpand(incident.getId()).toUri();
        return ResponseEntity.created(uri).body(new IncidentDto(incident));

    }

    /*
    * Retorna um unico incidente de acordo com o Id passado*/
    @GetMapping("/{id}")
    public ResponseEntity<IncidentDto> detalhar(@PathVariable Long id) {
        Optional<Incident> incidentOptional = incidentRepository.findById(id);

        if (incidentOptional.isPresent()) {
            return ResponseEntity.ok(new IncidentDto(incidentOptional.get()));
        }
        return ResponseEntity.notFound().build();
    }

    /*
    * Atualiza o incidente do Id passado por parametro*/
    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "incidentList", allEntries = true)
    public ResponseEntity<IncidentDto> update (@RequestBody IncidentForm incidentForm, @PathVariable Long id){

        Optional<Incident> optionalIncident = incidentRepository.findById(id);

        if (optionalIncident.isPresent()) {

            Incident incident = incidentForm.update(optionalIncident.get(), userRepository);
            incidentRepository.save(incident);
            return ResponseEntity.ok(new IncidentDto(optionalIncident.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/close/{userId}/{incidentId}")
    @Transactional
    @CacheEvict(value = "incidentList", allEntries = true)
    public ResponseEntity<?> closeIncident (@PathVariable Long userId, @PathVariable Long incidentId){

        //somente ADM ou ATTENDANT podem fechar um incidente
        Boolean isRequester = userRepository.getById(userId).getUserType() == UserType.REQUESTER;
        Optional<Incident> optionalIncident = incidentRepository.findById(incidentId);

        if(isRequester == true){
            return ResponseEntity.badRequest().body("Only ADM or ATTENDANT can close an incident");
        }
        if (optionalIncident.isPresent()) {
            optionalIncident.get().closeIncident();
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    /*
    * verifica o usuario solicitando a exclusao do incidente, se este tem a permissao ADM
    * */
    @DeleteMapping("/{userId}/{incidentId}")
    @Transactional
    @CacheEvict(value = "incidentList", allEntries = true)
    public ResponseEntity<?> delete (@PathVariable Long userId, @PathVariable Long incidentId){

        Boolean isAdm = userRepository.getById(userId).getUserType() == UserType.ADM;
        Optional<Incident> optionalIncident = incidentRepository.findById(incidentId);

        if(isAdm == false){
            return ResponseEntity.badRequest().body("Only ADM can delete an incident");
        }
        if (optionalIncident.isPresent()) {
            incidentRepository.deleteById(incidentId);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
