package wizorle.mykotlin.Test0710;

import android.support.annotation.LayoutRes;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by EdgeDi
 * 2018/7/10 11:12
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface bindLayout {

    @LayoutRes
    int value();

}
