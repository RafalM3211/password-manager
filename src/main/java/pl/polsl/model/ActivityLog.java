/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.model;

import pl.polsl.enums.Action;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



/**
 *
 * @author cyrax
 */
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActivityLog {
    private LocalDateTime data;
    private Action action;
    private String origin;
    private String details;
    private int userId;
}
