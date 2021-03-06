package com.frank.vmall.delegates.web.event;

import android.support.annotation.NonNull;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/9/3 0003.
 */

public class EventManager {

    private static final HashMap<String, Event> EVENTS = new HashMap<>();

    private EventManager() {

    }

    private static class Holder {
        private static final EventManager EVENT_MANAGER = new EventManager();
    }

    public static EventManager getInstance() {
        return Holder.EVENT_MANAGER;
    }

    public EventManager addEvent(@NonNull String name, @NonNull Event event) {
        EVENTS.put(name, event);
        return this;
    }

    public Event createEvent(@NonNull String action) {
        final Event event = EVENTS.get(action);
        if (event == null) {
            return new UndefinedEvent();
        }
        return event;
    }

}
