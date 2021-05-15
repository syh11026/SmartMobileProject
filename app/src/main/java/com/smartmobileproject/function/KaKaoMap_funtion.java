package com.smartmobileproject.function;

import com.smartmobileproject.activity.R;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

public class KaKaoMap_funtion {

    public void addCustomMarker(MapView mapview , MapPoint mapPoint){
        MapPOIItem custommarker = new MapPOIItem();
        custommarker.setItemName("Custom Marker");
        custommarker.setTag(1);
        custommarker.setMapPoint(mapPoint);
        custommarker.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        custommarker.setCustomImageResourceId(R.drawable.test);
        custommarker.setCustomImageAutoscale(true);
        custommarker.setCustomImageAnchor(0.5f,1.0f);
        mapview.addPOIItem(custommarker);
    }

}

