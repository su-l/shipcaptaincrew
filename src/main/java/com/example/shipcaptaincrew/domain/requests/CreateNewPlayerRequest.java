package com.example.shipcaptaincrew.domain.requests;

import lombok.Data;
import lombok.NonNull;
import org.springframework.web.bind.annotation.ModelAttribute;

@Data
public class CreateNewPlayerRequest {
    String nameInputField;
}
