package com.example.demo.modal.dto;

import com.example.demo.modal.Instructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {

    private String className;
    private Instructor instructor;
    private Date startDate;
    private Date endDate;
    private String timeFrame;

}
