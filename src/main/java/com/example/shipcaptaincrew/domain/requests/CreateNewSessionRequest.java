package com.example.shipcaptaincrew.domain.requests;

import lombok.Data;

import java.util.LinkedList;

@Data
public class CreateNewSessionRequest {
    LinkedList<String> selectedNames = new LinkedList<>();
}
