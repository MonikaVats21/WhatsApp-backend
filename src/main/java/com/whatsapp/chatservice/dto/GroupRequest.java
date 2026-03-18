package com.whatsapp.chatservice.dto;

import lombok.Data;
import java.util.List;

@Data
public class GroupRequest {
    private String name;
    private String description;
    private Long createdBy;
    private List<Long> memberIds;
}