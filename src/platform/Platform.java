package platform;

import content.*;

public class Platform {
    private final ContentLibrary contentLibrary = new ContentLibrary();

    public ContentLibrary getContentLibrary(){
        return contentLibrary;
    }
}
