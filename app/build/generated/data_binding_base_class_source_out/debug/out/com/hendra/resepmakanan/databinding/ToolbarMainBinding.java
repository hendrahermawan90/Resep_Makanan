// Generated by view binder compiler. Do not edit!
package com.hendra.resepmakanan.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.hendra.resepmakanan.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ToolbarMainBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final RelativeLayout imgProfile;

  private ToolbarMainBinding(@NonNull FrameLayout rootView, @NonNull RelativeLayout imgProfile) {
    this.rootView = rootView;
    this.imgProfile = imgProfile;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ToolbarMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ToolbarMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.toolbar_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ToolbarMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.imgProfile;
      RelativeLayout imgProfile = ViewBindings.findChildViewById(rootView, id);
      if (imgProfile == null) {
        break missingId;
      }

      return new ToolbarMainBinding((FrameLayout) rootView, imgProfile);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
