package com.edwin.spring.web.queue.delay;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 *
 * @author junming.cao
 * @date 2021/12/4 6:10 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessCell {
    private long id;
    private String name;
    private int type;
}
