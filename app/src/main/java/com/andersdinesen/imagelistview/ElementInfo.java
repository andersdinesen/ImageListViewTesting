package com.andersdinesen.imagelistview;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ad on 26-05-2015.
 */
public class ElementInfo implements Parcelable {
    private String displayName = null;
    private String remoteFileName = null;
    private Bitmap bitmap = null;

    public ElementInfo(){

    }
    public ElementInfo(String displayName, String remoteFileName, Bitmap bitmap){
        this.displayName = displayName;
        this.remoteFileName = remoteFileName;
        this.bitmap = bitmap;
    }

    public ElementInfo(Parcel in) {
        this.displayName = in.readString();
        this.remoteFileName = in.readString();
        this.bitmap = in.readParcelable(ElementInfo.class.getClassLoader());
    }

    public ElementInfo(Parcel in, ClassLoader classLoader) {
        this.displayName = in.readString();
        this.remoteFileName = in.readString();
        this.bitmap = in.readParcelable(classLoader);
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getRemoteFileName() {

        return remoteFileName;
    }

    public void setRemoteFileName(String remoteFileName) {
        this.remoteFileName = remoteFileName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getDisplayName());
        dest.writeString(getRemoteFileName());
        dest.writeParcelable(getBitmap(), flags);
    }

    public static final Parcelable.Creator<ElementInfo> CREATOR = new Parcelable.ClassLoaderCreator<ElementInfo>(){
        @Override

        public ElementInfo createFromParcel(Parcel source, ClassLoader loader) {
            return new ElementInfo(source, loader);
        }

        public ElementInfo createFromParcel(Parcel in){
            return new ElementInfo(in);
        }

        @Override
        public ElementInfo[] newArray(int size) {
            return new ElementInfo[size];
        }
    };
}
