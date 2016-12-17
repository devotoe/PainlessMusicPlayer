package com.doctoror.fuckoffmusicplayer.playlist;

import com.doctoror.fuckoffmusicplayer.R;
import com.doctoror.fuckoffmusicplayer.util.ThemeUtils;
import com.doctoror.fuckoffmusicplayer.widget.ItemTouchHelperViewHolder;
import com.doctoror.fuckoffmusicplayer.widget.TwoLineItemViewHolder;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * View holder for media in playlist
 */
final class PlaylistItemViewHolder extends RecyclerView.ViewHolder implements
        ItemTouchHelperViewHolder {

    final TextView textTitle;
    final TextView textArtist;
    final TextView textDuration;

    @Nullable
    private final Drawable mDefaultBackground;
    private final float mDefaultElevation;

    private final float mElevationSelected;
    private Drawable mSelectedBackground;

    PlaylistItemViewHolder(final View itemView) {
        super(itemView);
        textTitle = (TextView) itemView.findViewById(R.id.textTitle);
        textArtist = (TextView) itemView.findViewById(R.id.textArtist);
        textDuration = (TextView) itemView.findViewById(R.id.textDuration);

        mDefaultBackground = itemView.getBackground();
        mDefaultElevation = ViewCompat.getElevation(itemView);
        mElevationSelected = itemView.getResources()
                .getDimension(R.dimen.list_drag_selected_item_elevation);
    }

    @Override
    public void onItemSelected() {
        itemView.setBackground(getSelectedBackground(itemView.getContext()));
        ViewCompat.setElevation(itemView, mElevationSelected);
    }

    @Override
    public void onItemClear() {
        itemView.setBackground(mDefaultBackground);
        ViewCompat.setElevation(itemView, mDefaultElevation);
    }

    @NonNull
    private Drawable getSelectedBackground(@NonNull final Context context) {
        if (mSelectedBackground != null) {
            return mSelectedBackground;
        }

        mSelectedBackground = new LayerDrawable(new Drawable[]{
                new ColorDrawable(ThemeUtils.getColor(context.getTheme(),
                        android.R.attr.windowBackground)),
                new ColorDrawable(ContextCompat.getColor(context, R.color.dividerBackground))
        });

        return mSelectedBackground;
    }
}