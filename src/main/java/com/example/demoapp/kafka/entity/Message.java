package com.example.demoapp.kafka.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Message {

    /**
     * classe che definisce il json inviato nei topic kafka
     */

    @JsonProperty("activity_partition")
    private String activity_partition;

    @JsonProperty("activity_key")
    private String activity_key;

    @JsonProperty("username")
    private String username;

    @JsonProperty("activity_date")
    private java.sql.Timestamp activity_date;

    public Message() {
    }

    public Message(String partition, String key, String username, java.sql.Timestamp date) {
        this.activity_partition = partition;
        this.activity_key = key;
        this.username = username;
        this.activity_date = date;
    }
}
