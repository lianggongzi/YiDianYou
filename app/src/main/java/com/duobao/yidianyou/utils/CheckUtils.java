package com.duobao.yidianyou.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author je
 * @version 1.0
 * @email 281627668@qq.com
 * @date 2017/10/31
 * @desc 数据检查工具类
 */


public class CheckUtils {
    /**
     * 是否为空
     *
     * @return
     */

    private CheckUtils(){};

    public static boolean isEmpty(Object o) {
        if (null == o)
            return true;
        if (o instanceof String)
            return "".equals(o) ? true : false;

        if (o instanceof List)
            return ((List) o).size() == 0 ? true : false;

        if (o instanceof Map)
            return ((Map) o).size() == 0 ? true : false;
        if (o instanceof String[])
            return ((String[]) o).length    == 0 ? true : false;
        if (o instanceof int[])
            return ((int[]) o).length == 0 ? true : false;
        if (o instanceof Set)
            return ((Set) o).size() == 0 ? true : false;

        return false;
    }

    /**
     * 是否不为空
     *
     * @return
     */
    public static boolean isNotEmpty(Object o) {
        return !isEmpty(o);
    }
}
