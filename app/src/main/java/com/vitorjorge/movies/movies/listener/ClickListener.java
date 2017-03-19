package com.vitorjorge.movies.movies.listener;

import android.view.View;

/**
 * Created by vitorjorge on 12/03/17.
 */

public interface ClickListener {

    void onClick(View view, int position);

    void onLongClick(View view, int position);
}
