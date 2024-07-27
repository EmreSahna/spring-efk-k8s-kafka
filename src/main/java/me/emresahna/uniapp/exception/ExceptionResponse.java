package me.emresahna.uniapp.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ExceptionResponse {
    private Integer status;
    private String code;
    private String path;
    private OffsetDateTime timestamp;
    private List<String> detail;
}
