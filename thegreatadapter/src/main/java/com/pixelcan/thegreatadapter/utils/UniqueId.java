package com.pixelcan.thegreatadapter.utils;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by David Pacioianu on 11/21/16.
 */

public class UniqueId {
    private AtomicLong NEXT_ID = new AtomicLong(0);
    private final long id = NEXT_ID.getAndIncrement();

    protected long getUniqueId() {
        return id;
    }
}
