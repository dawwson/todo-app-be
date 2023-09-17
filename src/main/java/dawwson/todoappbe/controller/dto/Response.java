package dawwson.todoappbe.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Response<T> {
    private int code;
    private String message;
    private T data;
}
