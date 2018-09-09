package web.service;

import java.util.ArrayList;
import java.util.List;

public class Page<T> {
    public static int page;

    public List<T> get(int i, int j, List<T> objs) {
        List<T> result = new ArrayList<>();
        int size = objs.size();
        page = size % j == 0 ? size / j : size / j + 1;
        for (int n = 0 + (i - 1) * j; n < i * j; n++) {
            if (n >= size)
                break;
            result.add(objs.get(n));
        }
        return result;
    }
}
