package com.vitorjorge.movies.movies.utils;

import android.content.Context;
import android.content.pm.PackageInfo;

import java.util.zip.CheckedOutputStream;

/**
 * Created by vitorjorge on 16/03/17.
 */

public class GetInfo {

    public int count = 0;


    public  GetInfo() {


    }

    public String getInformation(Context context){

        try{

            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            String version = pInfo.versionName;
            return version;


        }catch (Exception e){

        }
        return "Error";

    }



}
