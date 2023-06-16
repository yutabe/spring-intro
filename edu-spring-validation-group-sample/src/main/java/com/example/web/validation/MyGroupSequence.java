package com.example.web.validation;

import jakarta.validation.GroupSequence;

@GroupSequence({Group1.class, Group2.class, Group3.class})
public interface MyGroupSequence {
}
