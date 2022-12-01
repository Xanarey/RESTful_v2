package dto;

import lombok.Builder;
import lombok.Data;
import model.User;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class UserDto {

    private Long id;
    private String name;
    private List<EventDto> eventDtoList;

    public static UserDto getEntity(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }

    public void setEventDtoList(List<EventDto> eventDtoList) {
        this.eventDtoList = eventDtoList;
    }
}
