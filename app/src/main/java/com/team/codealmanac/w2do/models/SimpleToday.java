package com.team.codealmanac.w2do.models;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Choi Jaeung on 2017-01-21.
 */

@IgnoreExtraProperties
public class SimpleToday {
    public int today_sequence;
    public String content;
    public boolean check_state;

    public SimpleToday(){

    }

    public SimpleToday(int today_sequence, String content, boolean check_state) {
        this.today_sequence = today_sequence;
        this.content = content;
        this.check_state = check_state;
    }
}
