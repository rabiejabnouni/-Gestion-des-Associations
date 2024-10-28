package print.print.Event;

import com.sun.jdi.request.EventRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import print.print.Auth.Model.AppUser;
import print.print.Auth.Service.AppUserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class EventMapper {
    @Autowired
    private AppUserService appUserService;
    public EventResponceDTO convertToEventResponse(EventEntity event) {
        List<String> participantNames = event.getParticipants().stream()
                .map(AppUser::getFirstName) // ou un autre attribut de l'utilisateur
                .collect(Collectors.toList());

        return new EventResponceDTO(event.getName(), event.getAddress(),
                event.getDate(),
                event.getDuration(), event.getCapacity(),participantNames);
    }
    public EventEntity convertToEventEntity(EventRequestDTO event) {
        List<String> participantNames = event.getParticipants();
         List<AppUser> appUsers= participantNames.stream().map(appUserService::UserByUsername).collect(Collectors.toList());
        LocalDateTime date= LocalDateTime.now();
        return new EventEntity(event.getName(), event.getAddress(),
               date,
                event.getDuration(),
                event.getCapacity());
    }

}
