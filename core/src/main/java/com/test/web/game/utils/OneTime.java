package com.test.web.game.utils;

import java.util.concurrent.atomic.AtomicBoolean;

public class OneTime {

    private final AtomicBoolean flag = new AtomicBoolean(true);

    public void use(Runnable block) {
        if (flag.getAndSet(false)) {
            block.run();
        }
    }
}
