// Generated by view binder compiler. Do not edit!
package com.hendra.resepmakanan.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.hendra.resepmakanan.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ListItemFilterFoodBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final CardView cvFilterMeal;

  @NonNull
  public final ImageView imgThumb;

  @NonNull
  public final TextView tvMeal;

  private ListItemFilterFoodBinding(@NonNull CardView rootView, @NonNull CardView cvFilterMeal,
      @NonNull ImageView imgThumb, @NonNull TextView tvMeal) {
    this.rootView = rootView;
    this.cvFilterMeal = cvFilterMeal;
    this.imgThumb = imgThumb;
    this.tvMeal = tvMeal;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static ListItemFilterFoodBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ListItemFilterFoodBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.list_item_filter_food, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ListItemFilterFoodBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      CardView cvFilterMeal = (CardView) rootView;

      id = R.id.imgThumb;
      ImageView imgThumb = ViewBindings.findChildViewById(rootView, id);
      if (imgThumb == null) {
        break missingId;
      }

      id = R.id.tvMeal;
      TextView tvMeal = ViewBindings.findChildViewById(rootView, id);
      if (tvMeal == null) {
        break missingId;
      }

      return new ListItemFilterFoodBinding((CardView) rootView, cvFilterMeal, imgThumb, tvMeal);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
