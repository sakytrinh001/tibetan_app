package com.android.dfr.tibetan_dfr.adapters;

/**
 * Created by bpncool on 2/24/2016.
 */
/**
 * interface to listen changes in state of sections
 */
public interface SectionStateChangeListener {
    void onSectionStateChanged(Section section, boolean isOpen);
    void onSelectSection(Section section);
}