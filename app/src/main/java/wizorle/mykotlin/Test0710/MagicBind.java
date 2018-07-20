package wizorle.mykotlin.Test0710;

import android.app.Activity;

/**
 * Created by EdgeDi
 * 2018/7/10 11:13
 */

public class MagicBind {

    public static void inject(Activity activity) {
        BindLayout(activity);
    }

    private static void BindLayout(Activity activity) {
        Class cla = activity.getClass();
        bindLayout bindLayout = (bindLayout) cla.getAnnotation(bindLayout.class);
        if (bindLayout != null) {
            activity.setContentView(bindLayout.value());
        }
    }
}