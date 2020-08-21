package com.wlliu.blog.service.user.service.impl;

import com.alibaba.csp.sentinel.slots.block.BlockException;

public class BlockHandler {
    public static String blockHandler(BlockException e){
        return "blockHandler"+e.toString();
    }
}
