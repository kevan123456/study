/*
 * Copyright (C) 2018-2022 Hangzhou JuLingShou Intelligent Technology Co., Ltd. All rights reserved
 */
package com.ws.service.impl;

import com.ws.service.CycleA;
import com.ws.service.CycleB;
import lombok.Data;

/**
 * 循环依赖问题-思考
 *
 * @author wangshun
 * @date 2024-05-13
 * @see
 * @since 1.0.0
 */
@Data
public class CycleAImpl implements CycleA {
    private CycleB cycleB;
}
