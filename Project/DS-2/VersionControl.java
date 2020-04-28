package distributedsystems;

public class VersionControl implements java.io.Serializable {

    private Linked_List version1;
    private Linked_List version2;

    public VersionControl () {

        version1 = null;
        version2 = null;
    }

    public void setLatestVersion (Linked_List list) {

        version1 = list;
    }

    public void setPreviousVersion (Linked_List list) {

        version2 = list;
    }

    public Linked_List getLatestVersion () {

        return version1;
    }

    public Linked_List getPreviousVersion () {

        return version2;
    }
}