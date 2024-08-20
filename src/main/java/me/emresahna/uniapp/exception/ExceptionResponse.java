package me.emresahna.uniapp.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ExceptionResponse {
    private HttpStatus status;
    private int code;
    private String path;
    private OffsetDateTime timestamp;
    private List<String> detail;
}
