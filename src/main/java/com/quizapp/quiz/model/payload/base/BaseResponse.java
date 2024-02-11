package com.quizapp.quiz.model.payload.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.quizapp.quiz.core.utils.time.PayloadLocalDateTimeDeserializer;
import com.quizapp.quiz.core.utils.time.PayloadLocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data

@Builder
@NoArgsConstructor
@AllArgsConstructor

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> implements Serializable {

    @JsonProperty("responseCode")
    private String responseCode;

    @JsonProperty("responseMessage")
    private String responseMessage;

    @JsonProperty("payload")
    private T payload;

    @JsonProperty("time_stamp")
    @JsonSerialize(using = PayloadLocalDateTimeSerializer.class)
    @JsonDeserialize(using = PayloadLocalDateTimeDeserializer.class)
    private LocalDateTime timeStamp;
}
