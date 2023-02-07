package br.com.una.Gesinc.Controller;

import br.com.una.Gesinc.Domain.Action;
import br.com.una.Gesinc.Domain.Incident;
import br.com.una.Gesinc.Domain.Users;
import br.com.una.Gesinc.Dto.ActionDto;
import br.com.una.Gesinc.Dto.IncidentDto;
import br.com.una.Gesinc.Enum.Status;
import br.com.una.Gesinc.Form.IncidentForm;
import br.com.una.Gesinc.Repository.ActionRepository;
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
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/incident")
public class IncidentController {

    @Autowired
    private IncidentRepository incidentRepository;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ActionRepository actionRepository;

    /**
     * Sem parametro : Retorna a lista de todos os incidentes cadastrados
     * Com parametro:  Retorna a lista de todos os incidentes abertos por este solicitante
     * @param userId
     * @param pagination
     * @return
     */
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

    /**
     * Cadastro de um novo incidente
     * @param incidentForm
     * @param uriBuilder
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_ADM', 'ROLE_REQUESTER', 'ROLE_ATTENDANT')")
    @PostMapping
    @CacheEvict(value = "incidentList", allEntries = true)
    public ResponseEntity<IncidentDto> register (@RequestBody IncidentForm incidentForm, UriComponentsBuilder uriBuilder){

        Incident incident = incidentForm.convertToEntity(userRepository);
        incidentRepository.save(incident);

        URI uri = uriBuilder.path("/incident/{id}").buildAndExpand(incident.getId()).toUri();
        return ResponseEntity.created(uri).body(new IncidentDto(incident));

    }

    /**
     * Retorna um unico incidente de acordo com o Id passado
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<IncidentDto> detail(@PathVariable Long id) {
        Optional<Incident> incidentOptional = incidentRepository.findById(id);

        if (incidentOptional.isPresent()) {
            List<Action> actionList = this.actionRepository.findByIncidentId(incidentOptional.get().getId());
            if(actionList != null){
                return ResponseEntity.ok(new IncidentDto(
                        incidentOptional.get(), actionList.stream().map(ActionDto::new).collect(Collectors.toList())));
            }
            return ResponseEntity.ok(new IncidentDto(incidentOptional.get()));
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Atualiza o incidente do Id passado por parametro
     * @param incidentForm
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    @CacheEvict(value = "incidentList", allEntries = true)
    public ResponseEntity<?> update (@RequestBody IncidentForm incidentForm, @PathVariable Long id){

        Optional<Incident> optionalIncident = incidentRepository.findById(id);

        if (optionalIncident.isPresent()) {

            if(optionalIncident.get().getStatus() != Status.OPENED){
                return ResponseEntity.unprocessableEntity().body("Only OPENED incidents can be updated");
            }
            Incident incident = incidentForm.update(optionalIncident.get(), userRepository);
            incidentRepository.save(incident);
            return ResponseEntity.ok(new IncidentDto(optionalIncident.get()));
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * fechando um incident, somente ADM ou Attendant podem fazer isso
     * @param userId
     * @param incidentId
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_ADM', 'ROLE_ATTENDANT')")
    @PutMapping("/close/{userId}/{incidentId}")
    @CacheEvict(value = "incidentList", allEntries = true)
    public ResponseEntity<?> closeIncident (@PathVariable Long userId, @PathVariable Long incidentId){

        Optional<Incident> optionalIncident = incidentRepository.findById(incidentId);

        if (optionalIncident.isPresent()) {
            optionalIncident.get().closeIncident();
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * verifica o usuario solicitando a exclusao do incidente, se este tem a permissao ADM
     * @param incidentId
     * @return
     */
    @Secured("ROLE_ADM")
    @DeleteMapping("/{incidentId}")
    public ResponseEntity<?> delete (@PathVariable Long incidentId){
        Optional<Incident> optionalIncident = incidentRepository.findById(incidentId);

        if (optionalIncident.isPresent()) {
            incidentRepository.deleteById(incidentId);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * adiciona uma acao ao incidente
     * @param actionDto
     * @param incidentId
     * @return
     */
    @PutMapping("/action/{incidentId}")
    public ResponseEntity<?> addAction (@RequestBody ActionDto actionDto, @PathVariable Long incidentId){
        Optional<Incident> optionalIncident = incidentRepository.findById(incidentId);

        if (optionalIncident.isPresent()){
            Incident incident = optionalIncident.get();

            Optional<Users> optionalUser = userRepository.findById(actionDto.getUserId());

            Action action = new Action(actionDto.getDescription(), incident, LocalDateTime.now(), optionalUser.get(), actionDto.getSolution());

            actionRepository.save(action);

            if(!action.getSolution()){
                incident.setStatus(Status.INPROGRESS);
            }
            else{
                incident.setStatus(Status.CONCLUDED);
                incident.closeIncident();
            }
            incidentRepository.save(incident);
            return ResponseEntity.ok(new IncidentDto(incident));
        }
        return ResponseEntity.notFound().build();
    }
}
