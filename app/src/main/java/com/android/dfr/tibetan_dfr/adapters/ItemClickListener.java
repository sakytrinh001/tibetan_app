package com.android.dfr.tibetan_dfr.adapters;

import com.android.dfr.tibetan_dfr.models.Item;

/**
 * Created by lenovo on 2/23/2016.
 */
public interface ItemClickListener {
    void itemClicked(Item item);
    void itemClicked(Section section);
    void itemClickedSection(Section section, int position);
}
