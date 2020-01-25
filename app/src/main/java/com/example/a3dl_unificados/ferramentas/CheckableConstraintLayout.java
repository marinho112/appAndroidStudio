package com.example.a3dl_unificados.ferramentas;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.LinearLayout;

import com.example.a3dl_unificados.R;

public class CheckableConstraintLayout extends ConstraintLayout implements Checkable {


        private boolean checked = false;
        private static final int[] CHECKED_STATE_SET = { android.R.attr.state_checked };

        public CheckableConstraintLayout(Context context) {
            super(context);
        }

        public CheckableConstraintLayout(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        // Requer API level 11
/*    public CheckableLinearLayout(Context context, AttributeSet attrs,
                                   int defStyle) {
          super(context, attrs, defStyle);
      }*/

        @Override
        protected int[] onCreateDrawableState(int extraSpace) {
            final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
            if (isChecked())
                mergeDrawableStates(drawableState, CHECKED_STATE_SET);
            return drawableState;
        }

        @Override
        public boolean isChecked(){
            return checked;
        }

        @Override
        public void setChecked(boolean checked) {
            this.checked = checked;
            refreshDrawableState();
            if(checked==true){
                setBackgroundColor(Color.LTGRAY);
            }else{
                setBackgroundColor(Color.WHITE);
            }
        }

        @Override
        public void toggle() {
            setChecked(!checked);
        }
    }