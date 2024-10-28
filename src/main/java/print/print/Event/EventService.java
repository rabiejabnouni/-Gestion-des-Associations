package print.print.Event;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import print.print.Auth.Model.AppUser;
import print.print.Auth.Rpository.AppUserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private EventMapper eventMapper;

    // CREATE
    public EventResponceDTO createEvent(EventRequestDTO requestDTO) {
        EventEntity entity= eventMapper.convertToEventEntity(requestDTO);
        EventEntity entity1= eventRepository.save(entity);
         return eventMapper.convertToEventResponse(entity1);
    }

    // READ
    public List<EventResponceDTO> getAllEvents() {
        List<EventEntity> entity1= eventRepository.findAll();
        return entity1.stream().map(eventMapper::convertToEventResponse).collect(Collectors.toList());
    }

    public EventResponceDTO getEventById(Long id) {
        Optional<EventEntity> entity1= eventRepository.findById(id);
        return eventMapper.convertToEventResponse(entity1.get());
    }




    // DELETE
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    // ADD APPUSER TO PARTICIPANTS LIST
    public EventEntity addParticipant(Long eventId, Long userId) {
        EventEntity event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found with id " + eventId));
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));

        event.getParticipants().add(user);
        return eventRepository.save(event);
    }
}

