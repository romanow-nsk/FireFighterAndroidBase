package romanow.abc.android.menu;

import android.content.Intent;

import java.io.BufferedReader;
import java.util.ArrayList;

import firefighter.core.constants.Values;
import firefighter.core.entity.subjectarea.Facility;
import firefighter.core.entity.subjectarea.arrays.FacilityList;
import romanow.abc.android.I_ArchiveMultiSelector;
import romanow.abc.android.MainActivity;
import romanow.abc.android.R;
import romanow.abc.android.service.AppData;
import romanow.abc.android.service.NetBackDefault;
import romanow.abc.android.service.NetCall;
import romanow.abc.android.yandexmap.MapActivity340;
import romanow.abc.android.yandexmap.MapFilesActivity;
import romanow.abc.android.FileDescription;
import romanow.abc.android.FileDescriptionList;


public class MIMap extends MenuItem {
    private AppData ctx;
    public MIMap(MainActivity main0) {
        super(main0);
        ctx = AppData.ctx();
        main.addMenuList(new MenuItemAction("Показать на карте") {
            @Override
            public void onSelect() {
                Intent intent = new Intent();
                intent.setClass(main.getApplicationContext(), MapActivity340.class);
                main.startActivity(intent);
                new NetCall<FacilityList>().call(main,ctx.getService().getFacilityList(ctx.loginSettings().getSessionToken(), Values.GetAllModeActual,2), new NetBackDefault(){
                    @Override
                    public void onSuccess(Object val) {
                        for(Facility facility : (FacilityList)val){
                            if (facility.getGPS().gpsValid())
                                ctx.sendGPS(facility.getGPS(), R.drawable.building,facility.getTitle(),true);
                        }
                    }});
                }
            });
        }
}
