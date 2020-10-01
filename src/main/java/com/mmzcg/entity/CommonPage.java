package com.mmzcg.entity;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

@Data
public class CommonPage<T,I> extends Page<T> {

    private I param;

}
