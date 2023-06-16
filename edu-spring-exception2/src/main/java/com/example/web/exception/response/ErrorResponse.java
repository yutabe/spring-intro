package com.example.web.exception.response;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {

    private String message;

    private List<Detail> details;

    public ErrorResponse(String message) {
        this.message = message;
        this.details = new ArrayList<>();
    }

    public String getMessage() {
        return message;
    }

    public List<Detail> getDetails() {
        return details;
    }

    public void addDetail(String target, String message) {
        details.add(new Detail(target, message));
    }

    private static class Detail {
        private String target;
        private String message;

        public Detail(String target, String message) {
            this.target = target;
            this.message = message;
        }

        public String getTarget() {
            return target;
        }

        public String getMessage() {
            return message;
        }
    }
}
