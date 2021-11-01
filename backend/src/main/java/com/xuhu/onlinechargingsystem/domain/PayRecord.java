package com.xuhu.onlinechargingsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayRecord {
    private String username;
    private Date date;
    private int electricity;
    private int fee;
}
