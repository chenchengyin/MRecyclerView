package com.demotest.testapp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/25.
 */
public class MakeLovelyData {

    public static List<String> makeDatas(){
        List datas = new ArrayList();
        for(int i=0;i<400;i++){
            datas.add(""+i);
        }
        return datas;
    }
}
