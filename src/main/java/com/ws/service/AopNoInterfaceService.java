/*
 * Copyright (C) 2018-2022 Hangzhou JuLingShou Intelligent Technology Co., Ltd. All rights reserved
 */
package com.ws.service;

import org.springframework.stereotype.Service;

/**
 * @author wangshun
 * @date 2024-05-16
 * @see
 * @since 1.0.0
 */
@Service
public class AopNoInterfaceService {

    public String getById(Long id) {
        //int r = 1 / 0;
        return "8";
    }

    public String getNameById(Long id) {
        return "miaomiao";
    }
}
