
package com.ajay.gurukulX.userDomin;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Role {
    ADMIN("Admin"),
    STUDENT("Student"),
    PRINCIPAL("Principal"),
    COLLEGE_ADMIN("College Admin"),
    TEACHER("Teacher"),
    EXAM_COORDINATOR("ExamCoordinator"),
    EXAM_CONTROLLER("ExamController");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static Role fromValue(String value) {
        for (Role role : Role.values()) {
            if (role.value.equalsIgnoreCase(value)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid Role: " + value);
    }
}


