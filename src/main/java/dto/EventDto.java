package dto;

import lombok.Builder;
import lombok.Data;
import model.Event;

@Data
@Builder
public class EventDto {

    private Long id;
//    private Long created;
//    private Long updated;
    private String created;
    private String updated;
//    private FileDto fileDto;

    public static EventDto getEntity(Event event) {
        return EventDto.builder()
                .id(event.getId())
                .created(event.getCreated())
                .updated(event.getUpdated())
                .build();
    }

}
