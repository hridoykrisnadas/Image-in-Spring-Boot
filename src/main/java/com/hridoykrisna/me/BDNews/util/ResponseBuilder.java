package com.hridoykrisna.me.BDNews.util;

import com.hridoykrisna.me.BDNews.util.ResponseDTO.ErrorResponseDto;
import com.hridoykrisna.me.BDNews.util.ResponseDTO.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
public final class ResponseBuilder {

    private static List<ErrorResponseDto> getCustomError(BindingResult bindingResult) {
        List<ErrorResponseDto> dtoList = new ArrayList<>();
        bindingResult.getFieldErrors().forEach(fieldError -> {
            ErrorResponseDto errorResponseDTO = ErrorResponseDto.builder()
                    .field(fieldError.getField())
                    .message(fieldError.getDefaultMessage())
                    .build();
            dtoList.add(errorResponseDTO);
        });
        return dtoList;
    }

    public static ResponseDto getFailureMessage(BindingResult result, String message) {
        return ResponseDto.builder()
                .message(message)
                .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .errorResponseDTO(getCustomError(result))
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timestamp(new Date().getTime())
                .build();
    }


    public static ResponseDto getFailureMessage(HttpStatus status, String message) {
        return ResponseDto.builder()
                .message(message)
                .status(status.getReasonPhrase())
                .statusCode(status.value())
                .timestamp(new Date().getTime())
                .build();
    }

    public static ResponseDto getSuccessMessage(HttpStatus status, String message, Object content) {
        return ResponseDto.builder()
                .message(message)
                .status(status.getReasonPhrase())
                .content(content)
                .statusCode(status.value())
                .timestamp(new Date().getTime())
                .build();
    }

    public static ResponseDto getSuccessMessage(HttpStatus status, String message, Object content, int numberOfElement) {
        return ResponseDto.builder()
                .message(message)
                .status(status.getReasonPhrase())
                .content(content)
                .statusCode(status.value())
                .numberOfElement(numberOfElement)
                .timestamp(new Date().getTime())
                .build();
    }

    public static ResponseDto getSuccessMessage(HttpStatus status, String message, Object content, int numberOfElement, int rowCount) {
        return ResponseDto.builder()
                .message(message)
                .status(status.getReasonPhrase())
                .content(content)
                .statusCode(status.value())
                .numberOfElement(numberOfElement)
                .rowCount(rowCount)
                .timestamp(new Date().getTime())
                .build();
    }
}
