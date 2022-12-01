package dto;

import lombok.Builder;
import lombok.Data;
import model.Event;

@Data
@Builder
public class EventDto {

    private Long id;
    private String created;
    private String updated;
    private FileDto fileDto;
    private UserDto userDto;

    public static EventDto getEntity(Event event) {
        return EventDto.builder()
                .id(event.getId())
                .created(event.getCreated())
                .updated(event.getUpdated())
                .build();
    }

}
