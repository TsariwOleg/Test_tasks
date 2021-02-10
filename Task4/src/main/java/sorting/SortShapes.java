package sorting;

import shapes.Shape;

import java.util.Comparator;
import java.util.List;

public class SortShapes {
    private SortShapes() {
    }

    private static final Comparator<Shape> SORT_SHAPES_BY_AREA_SIZE= (o1, o2) -> {
        if (o1.getAreaSize()==o2.getAreaSize())
            return 0;
        return o1.getAreaSize()>o2.getAreaSize()?-1:1;
    };

    public static void sortShapesByAreaSize(List<Shape> list){
        list.sort(SORT_SHAPES_BY_AREA_SIZE);
    }

}
