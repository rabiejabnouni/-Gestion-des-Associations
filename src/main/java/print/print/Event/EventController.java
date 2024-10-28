package print.print.Event;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private EventMapper eventMapper;

    // CREATE Event
    @PostMapping
    public ResponseEntity<EventResponceDTO> createEvent(@RequestBody EventRequestDTO eventRequestDTO) {
        EventResponceDTO eventResponse = eventService.createEvent(eventRequestDTO);
        return ResponseEntity.ok(eventResponse);
    }

    // READ all Events
    @GetMapping
    public ResponseEntity<List<EventResponceDTO>> getAllEvents() {
        List<EventResponceDTO> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    // READ Event by ID
    @GetMapping("/getById")
    public ResponseEntity<EventResponceDTO> getEventById(@RequestParam Long id) {
        EventResponceDTO eventResponse = eventService.getEventById(id);
        return ResponseEntity.ok(eventResponse);
    }

    // DELETE Event by ID
    @DeleteMapping("/deleteById")
    public ResponseEntity<Void> deleteEvent(@RequestParam Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

    // ADD AppUser to Event Participants
    @PostMapping("/addUser")
    public ResponseEntity<EventResponceDTO> addParticipant(@RequestParam Long eventId, @RequestParam Long userId) {
        EventEntity updatedEvent = eventService.addParticipant(eventId, userId);
        EventResponceDTO eventResponse = eventMapper.convertToEventResponse(updatedEvent); // Conversion si nécessaire
        return ResponseEntity.ok(eventResponse);
    }
}

