package com.example.shipcaptaincrew.domain;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Player {
    final String name;
    int score;
}
